package wupei.dumpinfo.dtquery.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wupei.dumpinfo.common.utils.Pair;
import wupei.dumpinfo.common.dt.DtKey;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.dtquery.service.CommonService;
import wupei.dumpinfo.dtquery.service.OBService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@Slf4j
public class OBServiceImpl implements OBService {

    @Autowired
    private CommonService commonService;

    @Override
    public Response queryObjectTableKey(List<String> sockets, String objectId, HttpServletResponse servletResponse){
        return commonService.queryTableKey(DtKey.OBJECT_TABLE_KEY,sockets,
                servletResponse, new Pair("objectId", objectId));
    }

}
