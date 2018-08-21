package xx.wechat.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth-1")
public class Auth1Controller {
    @GetMapping("/index.html")
    public String index() {
        return "index";
    }
}
