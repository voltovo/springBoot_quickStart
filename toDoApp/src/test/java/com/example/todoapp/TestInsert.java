package com.example.todoapp;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.persistence.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestInsert {

    @Autowired
    private TodoRepository todoRepo;

    @Test
    public void testInsert(){
        for(int i = 1; i <= 5; i++){
            Todo todo = new Todo();
            todo.setTodo("할일 " + i);
            todoRepo.save(todo);
        }
    }
}
