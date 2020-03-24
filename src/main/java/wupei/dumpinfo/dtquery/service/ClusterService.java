package wupei.dumpinfo.dtquery.service;

import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.dtquery.domain.ClusterDo;

import java.util.List;

public interface ClusterService {
    /**
     * 根据clusterName获取mater node ip
     * @param clusterName
     * @return
     */
    Response<ClusterDo> getCluster(String clusterName);

    Response<List<ClusterDo>> getClusterList();

}

