package com.sportuenteller.olympic.rest.rank.ui;

import com.sportuenteller.olympic.rest.rank.application.RequestTotalRankService;
import com.sportuenteller.olympic.rest.rank.application.TotalRankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TotalRankRestController {
    @Autowired
    RequestTotalRankService service;

    @RequestMapping(value = "/rank/{sessionKey}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TotalRankResult myRanking(@PathVariable("sessionKey") String sessionKey) {
        return service.findTotalRankList(sessionKey);
    }
}
