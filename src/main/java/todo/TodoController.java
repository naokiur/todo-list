package todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import todo.domain.service.todo.TodoService;

@Controller
public class TodoController {

    TodoService todoService;

    @ModelAttribute
    public TodoForm setUpForm() {
        TodoForm form = new TodoForm();
        return form;
    }

    @RequestMapping(value = "list")
    public String list(Model model) {
        // TODO Injection
//        Collection<Todo> todos = todoService.findAll();
//        model.addAttribute("todos", todos);

        return "todo/list";
    }
}
