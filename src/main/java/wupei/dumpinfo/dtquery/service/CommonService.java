package wupei.dumpinfo.dtquery.service;

import wupei.dumpinfo.common.utils.Pair;
import wupei.dumpinfo.common.dt.DtKey;
import wupei.dumpinfo.common.domain.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CommonService {

    Response queryTableKey(DtKey dtKey,
                           List<String> sockets,
                           HttpServletResponse servletResponse,
                           Pair... parameters);

    Response queryTableKey(DtKey dtKey, List<String> sockets,
                                  HttpServletResponse servletResponse,
                                  boolean showvalue, Pair... parameters);
}
