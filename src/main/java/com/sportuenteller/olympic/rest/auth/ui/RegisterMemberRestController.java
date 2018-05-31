package com.sportuenteller.olympic.rest.auth.ui;

import com.sportuenteller.olympic.rest.auth.application.register.CheckDuplicateResult;
import com.sportuenteller.olympic.rest.auth.application.register.RegisterMemberRequest;
import com.sportuenteller.olympic.rest.auth.application.register.RegisterMemberResult;
import com.sportuenteller.olympic.rest.auth.application.register.RegisterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterMemberRestController {
    @Autowired
    RegisterMemberService registerMemberService;

    @RequestMapping(value = "/member/register", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public CheckDuplicateResult checkDuplicate(@RequestBody CheckDuplicateRequest request) {
        return registerMemberService.checkDuplicate(request.getUserId());
    }

    @RequestMapping(value = "/member/register", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public RegisterMemberResult register(@RequestBody RegisterMemberRequest request) {
        return registerMemberService.registerMember(request);
    }
}
