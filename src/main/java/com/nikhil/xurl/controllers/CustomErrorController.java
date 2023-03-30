package com.nikhil.xurl.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        mav.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        return "error_page";
    }

    public String getErrorPath() {
        return PATH;
    }
}
