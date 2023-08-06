package com.example.todoapp.controller;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.persistence.TodoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
    @Autowired
    private TodoRepository todoRepo;
    @GetMapping("/")
    public String todos(Model model){
        List<Todo> todos = todoRepo.findAll();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @PostMapping("/addTodo")
    public String add(@RequestParam("todo")String todo){
        Todo newTodo = new Todo();
        newTodo.setTodo(todo);
        todoRepo.save(newTodo);
        return "redirect:/";
    }
}
