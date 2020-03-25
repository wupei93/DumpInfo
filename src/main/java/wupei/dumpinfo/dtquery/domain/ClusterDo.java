package wupei.dumpinfo.dtquery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClusterDo {

    private String clusterName;
    private String ips;
}
