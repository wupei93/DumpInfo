package wupei.dumpinfo.dtquery.service;

import wupei.dumpinfo.common.domain.Response;

import java.io.OutputStream;

public interface ProxyService {

    Response proxy(String host, String url, OutputStream outputStream);
}
