package com.sparta.todoparty.todo;

import com.sparta.todoparty.user.User;
import jakarta.validation.constraints.NotEmpty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @Mock
    TodoRepository todoRepository;

    @Test
    @DisplayName("Todo 생성 테스트")
    void createPost() {
        // given
        User user = new User();

        String title = "testTitle";
        String content = "testContent";
        TodoRequestDto requestDto = new TodoRequestDto(
                title,
                content
        );

        Todo todo = new Todo(requestDto);
        todo.setUser(user);

        TodoService todoService = new TodoService(todoRepository);

        // when
        TodoResponseDto result = todoService.createPost(requestDto, user);

        // then
        assertEquals(title, result.getTitle());
        assertEquals(content, result.getContent());
    }

    @Test
    @DisplayName("Todo 조회 테스트")
    void getTodoById() {
        // given
        Long todoId = 1L;

        User user = new User();

        String title = "testTitle";
        String content = "testContent";
        TodoRequestDto requestDto = new TodoRequestDto(
                title,
                content
        );

        Todo todo = new Todo(requestDto);
        todo.setUser(user);

        TodoService todoService = new TodoService(todoRepository);

        given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));

        // when
        TodoResponseDto result = todoService.getTodoById(todoId);

        // then
        assertEquals(title, result.getTitle());
        assertEquals(content, result.getContent());

    }


}
