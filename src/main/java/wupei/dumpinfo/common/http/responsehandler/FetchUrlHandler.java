package wupei.dumpinfo.common.http.responsehandler;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import wupei.dumpinfo.common.dt.DtConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dump all key的时候找到具体在哪个dt上，用于showvalue=gpb
 */
@Slf4j
public class FetchUrlHandler extends AbstractResponseHandler<String> {

    private boolean showvalue;

    public FetchUrlHandler(boolean showvalue) {
        this.showvalue = showvalue;
    }

    @Override
    public String handleResponse(HttpResponse response){
        BufferedReader reader = null;
        try{
            preHandleResponse(response);
            reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String line = null;
            String preLine = null;
            while((line = reader.readLine()) != null){
                if(line.contains(DtConstant.SCHEMA_TYPE)){
                    Preconditions.checkNotNull(preLine);
                    int showvalueLen = "showvalue=gpb".length();
                    int start = preLine.lastIndexOf("http");
                    int end = preLine.lastIndexOf("showvalue=gpb") + showvalueLen;
                    if(!showvalue){
                        end -= showvalueLen + 1;
                    }
                    return preLine.substring(start, end);
                }
                preLine = line;
            }
        } catch (Exception e) {
            log.error(e.toString());
        } finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
        }
        return null;
    }
}
