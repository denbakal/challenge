package main.java.spring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    private void getFile(String param) {
        System.out.println(param);
    }
}
