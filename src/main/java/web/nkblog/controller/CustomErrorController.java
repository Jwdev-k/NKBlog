package web.nkblog.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public String errorPage(HttpServletRequest request) {
        var status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int errorStatus = Integer.parseInt(status.toString());
            if (errorStatus == HttpStatus.NOT_FOUND.value() || errorStatus == HttpStatus.BAD_REQUEST.value()) {
                return "error/404error";
            }
            if (errorStatus == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500error";
            }
        }
        return "error";
    }
}
