package todo;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import todo.domain.model.Todo;
import todo.domain.service.todo.TodoService;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @ModelAttribute
    public TodoForm setUpForm() {
        TodoForm form = new TodoForm();
        return form;
    }

    @RequestMapping(value = "list")
    public String list(Model model) {
        Collection<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);

        return "list";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid TodoForm todoForm, BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        Todo todo = new Todo();
        BeanUtils.copyProperties(todoForm, todo);
        todoService.create(todo);

        return "redirect:list";
    }
}
