package com.sparta.todoparty.todo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoRequestDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
