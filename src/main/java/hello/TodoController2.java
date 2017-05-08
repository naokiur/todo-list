package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoController2 {

    @RequestMapping(value="list")
    public String list(Model model) {

        return "todo/list";
    }
}
