package wupei.dumpinfo.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.common.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/currentUser")
    public Response<User> currentUser(){
        return Response.<User>ok()
                .data(User.builder().userId(0).name("guest").build())
                .build();
    }
}
