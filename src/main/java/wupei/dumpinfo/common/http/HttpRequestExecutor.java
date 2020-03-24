package wupei.dumpinfo.common.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import wupei.dumpinfo.common.utils.NamedThreadFactory;

import java.util.concurrent.*;

@Slf4j
public class HttpRequestExecutor {
    private static CloseableHttpClient client = HttpClients.createDefault();

    private static final ExecutorService EXECUTOR =new ThreadPoolExecutor(
            10, 30,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue(),
            NamedThreadFactory.create(HttpRequestExecutor.class));

    public static <T> T execute(HttpRequestBase httpRequestBase, ResponseHandler<? extends T> responseHandler){
        try {
            return client.execute(httpRequestBase, responseHandler);
        } catch (Exception e){
            log.error("Error Url: {}\n{}", httpRequestBase.getURI().toString(), e);
        }
        return null;
    }

    public static <T> T  execute(HttpMethod method, String url, ResponseHandler<? extends T> responseHandler){
        return execute(HttpRequestFactory.newHttpRequest(HttpMethod.GET, url), responseHandler);
    }


}
