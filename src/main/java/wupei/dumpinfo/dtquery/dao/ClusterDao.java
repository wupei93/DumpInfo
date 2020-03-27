package wupei.dumpinfo.dtquery.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wupei.dumpinfo.dtquery.domain.ClusterDo;

import java.util.List;

@Repository
public interface ClusterDao {
    @ResultType(ClusterDo.class)
    @Select("SELECT clusterName, IPs as ips FROM cluster WHERE clusterName=#{clusterName}")
    ClusterDo getCluster(@Param("clusterName") String clusterName);

    @ResultType(ClusterDo.class)
    @Select("SELECT clusterName, IPs as ips FROM cluster order by clusterName")
    List<ClusterDo> getClusterList();

    @ResultType(Integer.class)
    @Insert("insert into cluster(clusterName, IPs) values(#{clusterDo.clusterName},#{clusterDo.ips}) ON DUPLICATE KEY UPDATE IPs=#{clusterDo.ips}")
    Integer saveCluster(@Param("clusterDo")ClusterDo clusterDo);
}
