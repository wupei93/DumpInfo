package wupei.dumpinfo.common.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int userId;
    private String name;
    private String avatar;
}
