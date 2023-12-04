package com.sparta.todoparty.todo;

import com.sparta.todoparty.CommonTest;

public interface TodoTest extends CommonTest {

    Long TEST_TODO_ID = 1L;
    String TEST_TODO_TITLE = "title";
    String TEST_TODO_CONTENT = "content";

    TodoRequestDto TEST_TODO_REQUEST_DTO = new TodoRequestDto(TEST_TODO_TITLE, TEST_TODO_CONTENT);

    TodoResponseDto TEST_TODO_RESPONSE_DTO = TodoResponseDto.builder()
            .title(TEST_TODO_TITLE)
            .content(TEST_TODO_CONTENT)
            .build();
    Todo TEST_TODO = Todo.builder()
            .title(TEST_TODO_TITLE)
            .content(TEST_TODO_CONTENT)
            .build();
    Todo TEST_ANOTHER_TODO = Todo.builder()
            .title(ANOTHER_PREFIX + TEST_TODO_TITLE)
            .content(ANOTHER_PREFIX + TEST_TODO_CONTENT)
            .build();
}