package wupei.dumpinfo.dtquery.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wupei.dumpinfo.common.utils.Pair;
import wupei.dumpinfo.common.dt.DtKey;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.dtquery.service.CTService;
import wupei.dumpinfo.dtquery.service.CommonService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@Slf4j
public class CTServiceImpl implements CTService {
    @Autowired
    private CommonService commonService;

    @Override
    public Response queryChunk(List<String> sockets, String chunkId, HttpServletResponse servletResponse) {
        return commonService.queryTableKey(DtKey.CHUNK,sockets,
                servletResponse, new Pair("chunkId", chunkId));
    }

}
