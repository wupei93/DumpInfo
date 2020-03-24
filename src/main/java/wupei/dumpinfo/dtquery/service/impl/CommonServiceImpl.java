package wupei.dumpinfo.dtquery.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wupei.dumpinfo.common.utils.NamedThreadFactory;
import wupei.dumpinfo.common.utils.Pair;
import wupei.dumpinfo.common.dt.DtKey;
import wupei.dumpinfo.common.http.HttpMethod;
import wupei.dumpinfo.common.http.HttpRequestExecutor;
import wupei.dumpinfo.common.http.Urls;
import wupei.dumpinfo.common.http.responsehandler.FetchUrlHandler;
import wupei.dumpinfo.common.http.responsehandler.OutputStreamHandler;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.dtquery.service.CommonService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    private static final ExecutorService EXECUTOR =new ThreadPoolExecutor(
            10, 30,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue(),
            NamedThreadFactory.create(CommonServiceImpl.class));
    @Override
    public Response queryTableKey(DtKey dtKey, List<String> sockets, HttpServletResponse servletResponse, Pair... parameters) {
        return this.queryTableKey(dtKey, sockets, servletResponse, true, parameters);
    }

    @Override
    public Response queryTableKey(DtKey dtKey, List<String> sockets, HttpServletResponse servletResponse, boolean showvalue, Pair... parameters) {
        List<Future<Boolean>> futureList = new ArrayList<>(sockets.size());
        sockets.forEach( socket -> {
            futureList.add(EXECUTOR.submit(() -> {
                String url = Urls.queryInAllTable(dtKey, socket, parameters);
                String targetUrl = HttpRequestExecutor.execute(HttpMethod.GET, url, new FetchUrlHandler(showvalue));
                if (targetUrl != null) {
                    try {
                        OutputStream outputStream = servletResponse.getOutputStream();
                        //使用代理地址
                        targetUrl = targetUrl.replace(targetUrl.substring(7,24), socket);
                        outputStream.write(targetUrl.getBytes());
                        HttpRequestExecutor.execute(HttpMethod.GET, targetUrl, new OutputStreamHandler(outputStream));
                        return true;
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                        return false;
                    }
                }
                return false;
            }));
        });
        boolean success = false;
        for(Future<Boolean> future : futureList){
            try {
                success &= future.get();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        if(success){
            return Response.ok().build();
        }
        return Response.error().build();
    }
}
