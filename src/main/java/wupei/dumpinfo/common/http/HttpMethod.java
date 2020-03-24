package wupei.dumpinfo.common.http;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

public enum HttpMethod {
    /**
     * Http PUT 方法
     */
    PUT,
    /**
     * Http GET 方法
     */
    GET;

    /**
     * 根据url生成HttpRequestBase
     * @param url 目标url
     * @return HttpRequestBase
     */
    public HttpRequestBase newHttpRequestBase(String url){
        HttpRequestBase requestBase = null;
        switch (this){
            case GET:
                requestBase = new HttpGet(url);
                break;
            case PUT:
                requestBase = new HttpPut(url);
                break;
            default:
                break;
        }
        return requestBase;
    }
}
