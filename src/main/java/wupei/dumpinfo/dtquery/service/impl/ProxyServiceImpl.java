package wupei.dumpinfo.dtquery.service.impl;

import com.jcraft.jsch.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.common.utils.NioUtils;
import wupei.dumpinfo.common.utils.SshUtils;
import wupei.dumpinfo.dtquery.service.ProxyService;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

@Service
@Slf4j
public class ProxyServiceImpl implements ProxyService {

    @Override
    public Response proxy(String host, String url, OutputStream out) {
        try {
            Channel channel = SshUtils.execCmd(host, proxyUrl(url));
            InputStream in = channel.getInputStream();
            ReadableByteChannel readableByteChannel = Channels.newChannel(in);
            WritableByteChannel writableByteChannel = Channels.newChannel(out);
            NioUtils.transferFromInToOut(readableByteChannel, writableByteChannel, 5000);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Response.error().data(e).build();
        }
        return Response.ok().build();
    }

    public String proxyUrl(String url) {
        String getPrivateIp = "`sudo ifconfig | grep 169 | cut -d : -f 2|cut -d \' \' -f  1`";
        return "curl -L \"http://" + getPrivateIp + ":9101" + url+"\"";
    }

}
