package wupei.dumpinfo.dtquery.controlle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.dtquery.service.ChunkService;

@RestController
@RequestMapping("/chunk")
public class ChunkController {

    @Autowired
    private ChunkService chunkService;

    @RequestMapping("/queryChunkLocation")
    public Response getChunkLocation(@RequestParam("host") String host,
            @RequestParam("chunkId") String chunkId){
        return chunkService.getChunkLocation(host,chunkId);
    }
}
