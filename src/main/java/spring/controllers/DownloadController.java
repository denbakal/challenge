package main.java.spring.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class DownloadController {

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    private void getFile(HttpServletResponse response) throws IOException {
        File file = new File("D:\\WORK\\challenge\\src\\main\\webapp\\com\\uploads\\Application_Schengen_Visa.pdf");
        FileInputStream stream = new FileInputStream(file);

        IOUtils.copy(stream, response.getOutputStream());
        response.setContentType("application/pdf");
        response.flushBuffer();
    }
}
