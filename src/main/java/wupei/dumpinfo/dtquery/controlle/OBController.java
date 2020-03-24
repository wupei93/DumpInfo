package wupei.dumpinfo.dtquery.controlle;

import org.springframework.web.bind.annotation.*;
import wupei.dumpinfo.common.domain.Response;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/dtquery/ob")
public class OBController {

    @GetMapping("/OBJECT_TABLE_KEY")
    @ModelAttribute
    public Response queryObjectTableKey(@RequestParam String objectId, HttpServletResponse servletResponse){
        return null;
    }
}
