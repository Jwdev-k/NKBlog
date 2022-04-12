package web.nkblog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.nkblog.domain.impl.loginDAO;
import web.nkblog.domain.loginDTO;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class APIController {

    private static final Gson gs = new GsonBuilder().setPrettyPrinting().create();
    private HttpHeaders Headers = new HttpHeaders();
    private loginDAO ld = new loginDAO();

    @GetMapping(value = "users/{id}", produces="text/plain;charset=UTF-8")
    public ResponseEntity<String> getUser(@PathVariable("id") String uid) throws Exception {
        Headers.setContentType(MediaType.APPLICATION_JSON);
        if (uid.equals("all")) {
            return new ResponseEntity<>(gs.toJson(ld.getAllAccountData()), Headers, HttpStatus.OK);
        } else if (ld.getAccountData(uid) != null) {
            return new ResponseEntity<>(gs.toJson(ld.getAccountData(uid)), Headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gs.toJson(new loginDTO("null","null","null")), Headers,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value= "users/{id}/{password}/{password2}", produces="text/plain;charset=UTF-8")
    public ResponseEntity<String> setPassword(@PathVariable("id") String id, @PathVariable("password") String password
            , @PathVariable("password2") String password2, HttpServletResponse response) throws Exception {
        Headers.setContentType(MediaType.APPLICATION_JSON);
        StringBuilder sb = new StringBuilder();
        if (ld.setPassword(id, password, password2)) {
            sb.append(id + "님의 패스워드를 ");
            sb.append(password2 + "으로 변경 하였습니다.");
            log.debug(sb.toString());
            return new ResponseEntity<>(sb.toString(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("", Headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
