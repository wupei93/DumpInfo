package wupei.dumpinfo.dtquery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.dtquery.dao.ClusterDao;
import wupei.dumpinfo.dtquery.domain.ClusterDo;
import wupei.dumpinfo.dtquery.service.ClusterService;

import java.util.List;

@Service
public class ClusterServiceImpl implements ClusterService {

    @Autowired
    private ClusterDao clusterDao;

    @Override
    public Response<ClusterDo> getCluster(String clusterName) {
        ClusterDo cluster = clusterDao.getCluster(clusterName);
        return Response.<ClusterDo>ok().data(cluster).build();
    }

    @Override
    public Response<List<ClusterDo>> getClusterList() {
        List<ClusterDo> clusters = clusterDao.getClusterList();
        return Response.<List<ClusterDo>>ok().data(clusters).build();
    }
}
