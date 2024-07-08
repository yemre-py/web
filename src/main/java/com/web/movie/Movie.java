package com.web.movie;

import com.web.enums.MovieGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Bu Alan Boş Bırakılamaz")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "Bu Alan Boş Bırakılamaz")
    private String description;
    @Column(nullable = false)
    @NotBlank(message = "Bu Alan Boş Bırakılamaz")
    private int year;
    @Column(nullable = false)
    @NotBlank(message = "Bu Alan Boş Bırakılamaz")
    private String director;
    @Column(nullable = false)
    @NotBlank(message = "Bu Alan Boş Bırakılamaz")
    private String actors;
    @Column(nullable = false)
    @NotBlank(message = "Bu Alan Boş Bırakılamaz")
    private MovieGenre genre;
}
