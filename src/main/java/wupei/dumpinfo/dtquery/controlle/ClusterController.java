package wupei.dumpinfo.dtquery.controlle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.dtquery.domain.ClusterDo;
import wupei.dumpinfo.dtquery.service.ClusterService;

import java.util.List;

@RestController
@RequestMapping("/cluster")
public class ClusterController {
    @Autowired
    private ClusterService clusterService;

    @GetMapping("/getClusterList")
    public Response<List<ClusterDo>> getClusterList() {
        return clusterService.getClusterList();
    }

    @GetMapping("/getCluster")
    public Response<ClusterDo> getCluster(@RequestParam("clusterName") String clusterName) {
        return clusterService.getCluster(clusterName);
    }

    @PostMapping("/saveCluster")
    public Response saveCluster(@RequestBody ClusterDo clusterDo) {
        return clusterService.saveCluster(clusterDo);
    }

    @GetMapping("/queryVDCs")
    public Response queryVDCs(@RequestParam("ips") String ips) {
        try{
            String[] ipList = ips.split(" ");
            return clusterService.queryVDCs(ipList);
        } catch (Exception e){
            return Response.error().data(e).build();
        }
    }
}
