package wupei.dumpinfo.dtquery.service;

import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.common.http.Callback;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Future;

public interface ProxyService {

    Response proxy(String method, String host, String url, OutputStream outputStream);

    Future<?> proxyWithCallback(String host, String url, Callback<InputStream, ?> callback);
}
