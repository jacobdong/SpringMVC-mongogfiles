package com.xcode.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void uploadFileToMongo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("hello");
    }
}
