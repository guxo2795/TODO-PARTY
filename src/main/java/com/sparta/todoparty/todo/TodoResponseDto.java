package com.sparta.todoparty.todo;


import com.sparta.todoparty.CommonResponseDto;
import com.sparta.todoparty.user.UserDto;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TodoResponseDto extends CommonResponseDto {
    private Long id;
    private String title;
    private String content;
    private Boolean isCompleted;
    private UserDto user;
    private LocalDateTime createDate;

    public TodoResponseDto(String msg, Integer statusCode) {
        super(msg, statusCode);
    }

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.user = new UserDto(todo.getUser());
        this.createDate = todo.getCreateDate();
    }
}
