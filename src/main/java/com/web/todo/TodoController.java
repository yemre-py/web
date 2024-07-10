package com.web.todo;

import com.web.enums.TodoStatus;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public String todos(Model model) {
        Map<TodoStatus, List<Todo>> groupedStatus = todoService.findAllByStatus();
        model.addAttribute("groupedStatus", groupedStatus);
        return "show-todos";
    }

    @PostMapping("/todos/new")
    public String add(@ModelAttribute @Valid Todo todo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.todo", bindingResult);
            redirectAttributes.addFlashAttribute("todo", todo);
            return "redirect:/todos/new";
        }
        todoService.save(todo);
        redirectAttributes.addFlashAttribute("msg", "Todo added successfully");
        return "redirect:/todos";
    }

    @GetMapping("/todos/new")
    public String newTodo(Model model) {
        if (!model.containsAttribute("todo")) {
            model.addAttribute("todo", new Todo());
        }
        model.addAttribute("status", TodoStatus.values());
        return "add-todos";
    }
}
