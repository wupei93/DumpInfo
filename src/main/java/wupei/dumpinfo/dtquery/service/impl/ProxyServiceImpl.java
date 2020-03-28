package wupei.dumpinfo.dtquery.service.impl;

import com.jcraft.jsch.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.common.http.Callback;
import wupei.dumpinfo.common.utils.NamedThreadFactory;
import wupei.dumpinfo.common.utils.NioUtils;
import wupei.dumpinfo.common.utils.SshUtils;
import wupei.dumpinfo.dtquery.service.ProxyService;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.*;

@Service
@Slf4j
public class ProxyServiceImpl implements ProxyService {

    private static final ExecutorService EXECUTOR =new ThreadPoolExecutor(
            10, 30,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue(),
            NamedThreadFactory.create(ProxyServiceImpl.class));

    @Override
    public Response proxy(String host, String url, OutputStream out) {
        try {
            Channel channel = SshUtils.execCmd(host, proxyUrl(url));
            InputStream in = channel.getInputStream();
            ReadableByteChannel readableByteChannel = Channels.newChannel(in);
            WritableByteChannel writableByteChannel = Channels.newChannel(out);
            NioUtils.transferFromInToOut(readableByteChannel, writableByteChannel, 10000);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Response.error().data(e).build();
        }
        return Response.ok().build();
    }

    public String proxyUrl(String url) {
        String getPrivateIp = "`sudo ifconfig | grep \"inet addr:169\" | cut -d : -f 2|cut -d \' \' -f  1`";
        return "curl -H \'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\' -L \"" +
                "http://" + getPrivateIp + ":9101" + url+"\"";
    }

    @Override
    public Future<?> proxyWithCallback(String host, String url, Callback<InputStream, ?> callback){
        return EXECUTOR.submit(() -> {
            Channel channel = null;
            try {
                channel = SshUtils.execCmd(host, proxyUrl(url));
                InputStream in = channel.getInputStream();
                return callback.call(in);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally{
                if (channel != null) {
                    channel.disconnect();
                }
            }
        });
    }
}
