package com.sportuenteller.olympic.rest.auth.ui;

import com.sportuenteller.olympic.rest.auth.application.login.CheckLoginResult;
import com.sportuenteller.olympic.rest.auth.application.login.LoginResult;
import com.sportuenteller.olympic.rest.auth.application.login.LoginService;
import com.sportuenteller.olympic.common.code.MemberType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginRestController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/member/login", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public LoginResult generalLogin(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest.getUserId(), loginRequest.getPassword());
    }

    @RequestMapping(value = "/member/login/facebook", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public LoginResult facebooklLogin(@RequestBody LoginRequest loginRequest) {
        return loginService.login(MemberType.facebook, loginRequest.getUserId(), loginRequest.getName());
    }

    @RequestMapping(value = "/member/login/google", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public LoginResult googleLogin(@RequestBody LoginRequest loginRequest) {
        return loginService.login(MemberType.google, loginRequest.getUserId(), loginRequest.getName());
    }

    @RequestMapping(value = "/member/login/twitter", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public LoginResult twitterLogin(@RequestBody LoginRequest loginRequest) {
        return loginService.login(MemberType.twitter, loginRequest.getUserId(), loginRequest.getName());
    }

    @RequestMapping(value = "/member/login/{sessionKey}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public CheckLoginResult loginCheck(@PathVariable("sessionKey") String sessionKey) {
        return loginService.checkLogin(sessionKey);
    }

}
