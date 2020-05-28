package wupei.dumpinfo.dtquery.controlle;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.common.http.UrlBuilder;
import wupei.dumpinfo.dtquery.service.ProxyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
@Slf4j
public class ProxyController {

    @Autowired
    private ProxyService proxyService;

    @RequestMapping("/proxy/**")
    public Response proxy(HttpServletRequest request, HttpServletResponse response){
        String url = request.getRequestURI();
        String host = request.getParameter("host");
        String method = request.getParameter("method");
        Preconditions.checkArgument(StringUtils.isNotBlank(host), "host is blank");
        Map<String, String[]> parameterMap = request.getParameterMap();
        UrlBuilder urlBuilder = UrlBuilder.getBuilder(url);
        for(Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if("host".equals(entry.getKey())) {
                continue;
            }
            urlBuilder.appendParam(entry.getKey(), entry.getValue()[0]);
        }
        OutputStream output = null;
        try {
            output = response.getOutputStream();
        } catch (IOException e) {
            return Response.error().data(e).build();
        }
        return proxyService.proxy(method, host,
                urlBuilder.build().substring(url.indexOf("proxy")+5), output);
    }
}
