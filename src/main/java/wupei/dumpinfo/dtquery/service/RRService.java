package wupei.dumpinfo.dtquery.service;

import wupei.dumpinfo.common.domain.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RRService {
    Response queryRepoReference(List<String> sockets, String chunkId, HttpServletResponse servletResponse);
}
