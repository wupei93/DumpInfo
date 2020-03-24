package wupei.dumpinfo.common.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response<T> {
    private int status;
    private T data;

    public static <T> ResponseBuilder<T> ok(){
        return Response.<T>builder().status(200);
    }

    public static <T> ResponseBuilder<T> error(){
        return Response.<T>builder().status(500);
    }
}
