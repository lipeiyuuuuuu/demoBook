package com.demoBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: indexController
 * Description:
 * date:  2023-03-15  14:24
 *
 * @author lipeiyu
 * @version 1.0
 */
@Controller
public class indexController {

    @GetMapping("/index")
    public String indexPage(){
        return "index.html";
    }

    @ResponseBody
    @GetMapping("/restControllerTest")
    public String restControllerTest(){
        return "test of restControllerTest";
    }

    @GetMapping("/demoVue1")
    public String demoVue1(){
        return "demoVue.html";
    }
}
