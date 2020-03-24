package wupei.dumpinfo.dtquery.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wupei.dumpinfo.dtquery.domain.ClusterDo;

import java.util.List;

@Repository
public interface ClusterDao {
    @ResultType(ClusterDo.class)
    @Select("SELECT clusterName, IPs FROM file_record WHERE clusterName=#{clusterName}")
    ClusterDo getCluster(@Param("clusterName") String clusterName);

    @ResultType(ClusterDo.class)
    @Select("SELECT clusterName, IPs FROM cluster")
    List<ClusterDo> getClusterList();
}
