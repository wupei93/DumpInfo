package wupei.dumpinfo.dtquery.controlle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wupei.dumpinfo.common.domain.Response;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProxyController {

    @RequestMapping("/proxy/{host}")
    public Response proxy(@Path("host") String host, HttpServletRequest request, HttpServletRequest response){
        
    }
}
