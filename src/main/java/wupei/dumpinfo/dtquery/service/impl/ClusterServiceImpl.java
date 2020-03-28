package wupei.dumpinfo.dtquery.service.impl;

import com.jcraft.jsch.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.common.utils.NioUtils;
import wupei.dumpinfo.common.utils.Pair;
import wupei.dumpinfo.common.utils.SshUtils;
import wupei.dumpinfo.dtquery.dao.ClusterDao;
import wupei.dumpinfo.dtquery.domain.ClusterDo;
import wupei.dumpinfo.dtquery.service.ClusterService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    @Override
    public Response saveCluster(ClusterDo clusterDo) {
        clusterDao.saveCluster(clusterDo);
        return Response.<List<ClusterDo>>ok().build();
    }

    @Override
    public Response<List<Pair<String, String>>> queryVDCs(String[] ipList) {
        List<Pair<String, String>> vdcList = Arrays.asList(ipList).stream().parallel().map(ip -> {
            String url = "curl -L \"http://`sudo ifconfig | grep \"inet addr:169\" | cut -d : -f 2|cut -d \" \" -f  1`:9101/diagnostic/getVdcFromNode?nodeId="+ip+"\"";

            Channel channel = null;
            InputStream in = null;
            try {
                channel = SshUtils.execCmd(ip, url);
                in = channel.getInputStream();
                ByteBuffer buffer = NioUtils.readFromInputStream(in, 10000);
                return new Pair<String, String>(ip,new String(buffer.array(), 0, buffer.limit()));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return new Pair<String, String>(ip,e.toString());
            } finally {
                channel.disconnect();
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }).collect(Collectors.toList());
        return Response.<List<Pair<String, String>>>ok().data(vdcList).build();
    }

}
