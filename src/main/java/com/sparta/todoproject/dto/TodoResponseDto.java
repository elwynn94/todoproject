package com.sparta.todoproject.dto;

import com.sparta.todoproject.entity.Todo;
import lombok.Getter;
@Getter
public class TodoResponseDto{
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;
    private final String date;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.username = todo.getUsername();
        this.date = todo.getDate();
    }
}