package wupei.dumpinfo.dtquery.service;

import wupei.dumpinfo.common.domain.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OBService {
    Response queryObjectTableKey(List<String> sockets, String objectId, HttpServletResponse servletResponse);
}
