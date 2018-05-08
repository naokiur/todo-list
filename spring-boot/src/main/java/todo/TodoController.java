package todo;

import java.util.Collection;

import javax.validation.groups.Default;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import todo.TodoForm.TodoCreate;
import todo.TodoForm.TodoFinish;
import todo.domain.model.Todo;
import todo.domain.service.todo.TodoService;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    MessageSource messageSource;

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
    public String create(@Validated({Default.class, TodoCreate.class}) TodoForm todoForm, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        Todo todo = new Todo();
        BeanUtils.copyProperties(todoForm, todo);

        try {
            todoService.create(todo);
            // TODO Add Exception.
        } catch (Exception e) {
            e.printStackTrace();
        }

        attributes.addFlashAttribute("result", "Created successfully!");

        return "redirect:list";
    }

    @RequestMapping(value = "finish", method = RequestMethod.POST)
    public String finish(@Validated({Default.class, TodoFinish.class}) TodoForm todoForm, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        try {
            todoService.finish(todoForm.getTodoId());
            // TODO Add Exception.
        } catch (Exception e) {
            e.printStackTrace();
        }

        attributes.addFlashAttribute("result", "Finished Successfully!");

        return "redirect:list";
    }
}
