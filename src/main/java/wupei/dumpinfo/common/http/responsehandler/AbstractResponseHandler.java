package wupei.dumpinfo.common.http.responsehandler;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

public abstract class AbstractResponseHandler<T> implements ResponseHandler<T> {

    public void preHandleResponse(HttpResponse response) throws ClientProtocolException, IOException, ResourceException {
        if(response.getStatusLine().getStatusCode() >= 300){
            throw new ResourceException();
        }
    }
}
