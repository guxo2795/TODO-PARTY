package com.sparta.todoparty.todo;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {
    private String title;
    private String content;
}
