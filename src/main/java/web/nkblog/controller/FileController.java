package web.nkblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.nkblog.domain.FileDTO;
import web.nkblog.service.impl.FileServiceimpl;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
public class FileController {

    @Autowired
    FileServiceimpl fs;

    @RequestMapping(value = "/download")
    public void fileDown(HttpServletResponse response, @RequestParam(value = "bno") int bno) throws Exception {
       FileDTO getFile = fs.getFile(bno);
       response.setContentType("application/octet-stream");
       response.setContentLength(getFile.getData().length);
       response.setHeader("Content-Disposition",  "attachment; fileName=\""+ URLEncoder.encode(getFile.getFilename(), "UTF-8")+"\";");
       response.getOutputStream().write(getFile.getData());
       response.getOutputStream().flush();
       response.getOutputStream().close();
    }
}
