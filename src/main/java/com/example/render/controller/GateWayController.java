package com.example.render.controller;


import com.example.render.gateway.ApiGateway;
import com.example.render.token.CheckAuthImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/manage")
public class GateWayController {

    private CheckAuthImpl auth;
    private ApiGateway apiGateway;

    @Autowired
    public GateWayController(CheckAuthImpl auth, ApiGateway apiGateway){
        this.auth = auth;
        this.apiGateway = apiGateway;

    }



    @RequestMapping(value = "/gateway", method = RequestMethod.GET)
    public String gateWayCookieSetup(HttpServletResponse res, HttpServletRequest req) throws ServletException, IOException, URISyntaxException {
         System.out.println("on gateway route");

        auth.Authentication();

        apiGateway.sendCookie(res);


          return "index";
    }
}
