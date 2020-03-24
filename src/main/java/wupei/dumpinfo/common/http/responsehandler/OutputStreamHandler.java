package wupei.dumpinfo.common.http.responsehandler;

import org.apache.http.HttpResponse;

import java.io.InputStream;
import java.io.OutputStream;

public class OutputStreamHandler extends AbstractResponseHandler {
    private OutputStream outputStream;

    public OutputStreamHandler(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    @Override
    public Object handleResponse(HttpResponse response) {
        try {
            preHandleResponse(response);
            InputStream inputStream = response.getEntity().getContent();
            byte[] buffer = new byte[1024];
            while(inputStream.read(buffer) != -1){
                outputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
