package wupei.dumpinfo.dtquery.controlle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
