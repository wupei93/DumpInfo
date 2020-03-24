package wupei.dumpinfo.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.dtquery.service.CTService;
import wupei.dumpinfo.dtquery.service.OBService;
import wupei.dumpinfo.dtquery.service.RRService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private OBService obService;

    @Autowired
    private CTService ctService;

    @Autowired
    private RRService rrService;

    private static List<String> sockets = new ArrayList<>(3);
    static {
        sockets.add("10.247.99.224:3000");
        sockets.add("10.247.99.224:3001");
        sockets.add("10.247.99.224:3002");
    }

    @GetMapping("")
    @ModelAttribute
    public Response testRoot(HttpServletRequest req){
        return Response.ok().data(req.getRequestURI()).build();
    }

    @GetMapping("/OBJECT_TABLE_KEY")
    @ModelAttribute
    public Response queryObjectTableKey(@RequestParam String objectId, HttpServletResponse servletResponse){
        return obService.queryObjectTableKey(sockets, objectId, servletResponse);
    }

    @GetMapping("/CHUNK")
    @ModelAttribute
    public Response queryChunk(@RequestParam String chunkId, HttpServletResponse servletResponse){
        return ctService.queryChunk(sockets, chunkId, servletResponse);
    }

    @GetMapping("/REPO_REFERENCE")
    @ModelAttribute
    public Response queryRepoReference(@RequestParam String chunkId, HttpServletResponse servletResponse){
        return rrService.queryRepoReference(sockets, chunkId, servletResponse);
    }

    @GetMapping("/hello")
    public Response hello(){
        return Response.ok().data("hello!").build();
    }
}
