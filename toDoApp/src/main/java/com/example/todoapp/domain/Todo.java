package com.example.todoapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "todo")
@Setter
@Getter
@ToString
public class Todo {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String todo;
}
