package main.java.spring.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class DownloadController {

    @RequestMapping(value = "/download", method = RequestMethod.POST, produces = "application/pdf")
    private void getFile(HttpServletResponse response) throws IOException {
        File file = new File("src/main/webapp/com/uploads/Application_Schengen_Visa.pdf");
        FileInputStream stream = new FileInputStream(file);

        IOUtils.copy(stream, response.getOutputStream());

        stream.close();
    }

    @RequestMapping(value = "/downloadZip", method = RequestMethod.POST, produces = "application/zip")
    private void getFileZip(HttpServletResponse response) throws IOException {
        ZipEntry entry = new ZipEntry("Application.pdf");
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        FileInputStream fileInputStream = new FileInputStream("src/main/webapp/com/uploads/Application_Schengen_Visa.pdf");

        zipOutputStream.putNextEntry(entry);
        IOUtils.copy(fileInputStream, zipOutputStream);

        fileInputStream.close();
        zipOutputStream.closeEntry();
        zipOutputStream.close();
    }
}
