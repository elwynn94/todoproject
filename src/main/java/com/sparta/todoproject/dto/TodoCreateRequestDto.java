package com.sparta.todoproject.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class TodoCreateRequestDto {
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 200)
    private String title;

    private String contents;

    @Email(message = "이메일 형식으로 입력해주세요.")
    private String username;

    private String date;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private Long password;
}