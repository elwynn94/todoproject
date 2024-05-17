package com.sparta.todoproject.controller;

import com.sparta.todoproject.dto.TodoCreateRequestDto;
import com.sparta.todoproject.dto.TodoResponseDto;
import com.sparta.todoproject.dto.TodoUpdateRequestDto;
import com.sparta.todoproject.entity.Todo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final Map<Long, Todo> todoList = new HashMap<>();

    @PostMapping("")
    public TodoResponseDto createTodo(@Valid @RequestBody TodoCreateRequestDto requestDto) {
        Todo todo = new Todo(requestDto);

        Long maxId = !todoList.isEmpty() ? Collections.max(todoList.keySet()) + 1 : 1;
        todo.setId(maxId);
        todoList.put(todo.getId(), todo);

        return new TodoResponseDto(todo);

    }


    @GetMapping("")
    public List<TodoResponseDto> getAllTodo(){
        List<TodoResponseDto> todoResponseDtoList = new ArrayList<>(todoList.values().stream().map(TodoResponseDto::new).toList());
        todoResponseDtoList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return todoResponseDtoList;
    }

    @GetMapping("/param")
    public TodoResponseDto getTodoById(@RequestParam Long id){
        if(todoList.containsKey(id)){
            Todo todo = todoList.get(id);
            return new TodoResponseDto(todo);
        } else {
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }
    }

    @PutMapping("/param")
    public TodoResponseDto updateTodo(@RequestParam Long id, @RequestParam Long password, @RequestBody TodoUpdateRequestDto requestDto){
        if(todoList.containsKey(id)){
            Todo todo = todoList.get(id);
            if(todo.getPassword().equals(password)){
                todo.update(requestDto);
                return new TodoResponseDto(todo);
            }
            else{
                throw new IllegalArgumentException("비밀번호가 다릅니다.");
            }
        }
        else{
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }
    }
    @DeleteMapping("/param")
    public String deleteTodo(@RequestParam Long id, @RequestParam Long password){
        if(todoList.containsKey(id)){
            Todo todo = todoList.get(id);
            if(todo.getPassword().equals(password)){
                todoList.remove(id);
                return "삭제되었습니다";
            }
            else{
                throw new IllegalArgumentException("비밀번호가 다릅니다.");
            }
        }
        else{
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }
    }
}