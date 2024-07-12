package com.web.todo.todo;

import com.web.enums.TodoStatus;
import com.web.todo.update.Update;
import com.web.todo.update.UpdateRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UpdateRepository updateRepository;

    public TodoService(TodoRepository todoRepository, UpdateRepository updateRepository) {
        this.todoRepository = todoRepository;
        this.updateRepository = updateRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public List<Date> findUpdateDatesByTodoId(int id) {
        List<Update> updates = updateRepository.findByTodoId(id);
        return updates.stream().map(Update::getUpdated).collect(Collectors.toList());
    }

    public Todo save(Todo todo) {
        Update update = new Update();

        update.setDescription(todo.getDescription());
        update.setUpdated(new Date());
        update.setTodo(todo);

        todo.setUpdates(new ArrayList<>(Arrays.asList(update)));
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


        Update newUpdate = new Update();
        newUpdate.setDescription(todo.getDescription());
        newUpdate.setUpdated(new Date());
        newUpdate.setTodo(existingTodo);

        if (existingTodo.getUpdates() == null) {
            existingTodo.setUpdates(new ArrayList<>());
        }
        existingTodo.getUpdates().add(newUpdate);

        todoRepository.save(existingTodo);
    }


}
