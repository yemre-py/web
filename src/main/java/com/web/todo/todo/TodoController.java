package com.web.todo.todo;

import com.web.enums.TodoStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
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

        if (!model.containsAttribute("todo")) {
            model.addAttribute("todo", new Todo());
        }
        model.addAttribute("status", TodoStatus.values());
        return "todos";
    }

    @PostMapping("/todos")
    public String add(@ModelAttribute @Valid Todo todo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.todo", bindingResult);
            redirectAttributes.addFlashAttribute("todo", todo);
            return "redirect:/todos";
        }
        todoService.save(todo);
        redirectAttributes.addFlashAttribute("msg", "Todo added successfully");
        return "redirect:/todos";
    }

    @PostMapping("/todos/edit")
    public String edit(@ModelAttribute @Valid Todo todo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.todo", bindingResult);
            redirectAttributes.addFlashAttribute("todo", todo);
            return "redirect:/todos";
        }
        todoService.update(todo);
        redirectAttributes.addFlashAttribute("msg", "Todo edited successfully");
        return "redirect:/todos";
    }

    @GetMapping("/todos/{id}/updates")
    @ResponseBody
    public List<Date> getUpdates(@PathVariable int id) {
        return todoService.findUpdateDatesByTodoId(id);
    }

    @GetMapping("/api/todos/{todoId}/updates")
    public ResponseEntity<List<Date>> getTodoUpdates(@PathVariable int todoId) {
        List<Date> updateDates = todoService.findUpdateDatesByTodoId(todoId);
        return ResponseEntity.ok(updateDates);
    }
}
