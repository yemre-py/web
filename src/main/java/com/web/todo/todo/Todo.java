package com.web.todo.todo;

import com.web.enums.TodoStatus;
import com.web.todo.update.Update;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = " Bu alan boş bırakılamaz.")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Bu alan boş bırakılamaz")
    private String description;

    private TodoStatus status;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Update> updates;
}
