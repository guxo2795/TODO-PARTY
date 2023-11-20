package com.sparta.todoparty.todo;


import com.sparta.todoparty.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoResponseDto createPost(TodoRequestDto dto, User user) {
        Todo todo = new Todo(dto);
        todo.setUser(user);

        todoRepository.save(todo);

        return new TodoResponseDto(todo);
    }

}
