package com.web.todo;

import com.web.enums.TodoStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo findById(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        todoRepository.deleteById(id);
    }

    public Map<TodoStatus, List<Todo>> findAllByStatus() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().collect(Collectors.groupingBy(Todo::getStatus));
    }

    public void update(Todo todo) {
        Todo existingTodo = todoRepository.findById(todo.getId()).orElseThrow(
                () -> new IllegalArgumentException("Todo not found")
        );

        existingTodo.setTitle(todo.getTitle());
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setStatus(todo.getStatus());

        todoRepository.save(existingTodo);
    }
}
