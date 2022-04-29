package web.nkblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import web.nkblog.service.MailService;
import web.nkblog.utils.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {
    @Autowired
    private MailService ms;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws IOException {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "index";
    }

    @RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
    public String main() {
        return "main";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @RequestMapping(value = "/404error")
    public String error() {
        return "/error/404error";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(value = "/500error")
    public String error2() {
        return "/error/500error";
    }
}
