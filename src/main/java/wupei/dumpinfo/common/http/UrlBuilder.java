package wupei.dumpinfo.common.http;

import com.google.common.base.Preconditions;
import wupei.dumpinfo.common.utils.Pair;
import wupei.dumpinfo.common.utils.ParamKey;

public class UrlBuilder {

    private boolean hasAppendedParam = false;
    private StringBuilder stringBuffer;

    public static UrlBuilder getBuilder(String baseUrl){
        UrlBuilder urlBuilder = new UrlBuilder();
        if(baseUrl.contains("=")){
            urlBuilder.hasAppendedParam = true;
        }
        urlBuilder.stringBuffer = new StringBuilder(baseUrl);
        return urlBuilder;
    }

    public UrlBuilder appendParam(String key, String value){
        String prefix = "&";
        if(!hasAppendedParam){
            prefix = "?";
            hasAppendedParam = true;
        }
        stringBuffer.append(prefix);
        stringBuffer.append(key);
        stringBuffer.append("=");
        stringBuffer.append(value);
        return this;
    }

    public UrlBuilder appendPath(String path){
        Preconditions.checkArgument( !hasAppendedParam,
                "已经拼接过参数了，不能再拼接路径！当前url:", stringBuffer.toString());
        stringBuffer.append("/");
        stringBuffer.append(path);
        return this;
    }

    public String build(){
        return stringBuffer.toString();
    }

    public UrlBuilder appendParams(Pair[] params) {
        for(Pair param : params){
            appendParam(param.getLeft().toString(), param.getRight().toString());
        }
        appendParam("showvalue", "gpb");
        return this;
    }

    public UrlBuilder appendShowvalue() {
        return appendParam(ParamKey.SHOW_VALUE, "gpb");
    }
}
