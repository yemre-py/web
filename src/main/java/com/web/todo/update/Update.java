package com.web.todo.update;

import com.web.todo.todo.Todo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Update {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @UpdateTimestamp
    private Date updated;

    @NotBlank(message = "bu alan boş bırakılamaz.")
    private String description;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;
}
