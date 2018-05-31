package com.sportuenteller.olympic.rest.rank.ui;

import com.sportuenteller.olympic.rest.rank.application.MyStatusResult;
import com.sportuenteller.olympic.rest.rank.application.RequestMyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyStatusRestController {

    @Autowired
    RequestMyStatusService service;

    @RequestMapping(value = "/vote/status/{sessionKey}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public MyStatusResult voteStatusByPersonal(@PathVariable("sessionKey") String sessionKey) {
        return service.findMyStatus(sessionKey);

    }


}
