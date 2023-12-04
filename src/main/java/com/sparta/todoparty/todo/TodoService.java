package com.sparta.todoparty.todo;


import com.sparta.todoparty.user.User;
import com.sparta.todoparty.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 생성
    public TodoResponseDto createTodo(TodoRequestDto dto, User user) {
        Todo todo = new Todo(dto);
        todo.setUser(user);

        var saved = todoRepository.save(todo);

        return new TodoResponseDto(saved);
    }

    // 조회
    public TodoResponseDto getTodoDto(Long todoId) {
        Todo todo = getTodo(todoId);

        return new TodoResponseDto(todo);
    }

//    // 목록 조회
//    public List<TodoResponseDto> getTodoList() {
//        return todoRepository.findAllByOrderByCreateDateDesc().stream()
//                .map(TodoResponseDto::new)
//                .collect(Collectors.toList());
//    }

    public Map<UserDto, List<TodoResponseDto>> getUserTodoMap() {
        Map<UserDto, List<TodoResponseDto>>  userTodoMap = new HashMap<>();

        List<Todo> todoList = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));


        todoList.forEach(todo -> {
            var userDto = new UserDto(todo.getUser());
            var todoDto = new TodoResponseDto(todo);
            if(userTodoMap.containsKey(userDto)) {
                // 유저 할일목록에 항목 추가
                userTodoMap.get(userDto).add(todoDto);
            } else {
                // 유저 할일목록을 새로 추가
                userTodoMap.put(userDto, new ArrayList<>(List.of(todoDto)));
            }
        });

        return userTodoMap;
    }

    // 수정
    @Transactional
    public TodoResponseDto updateTodo(Long todoId, TodoRequestDto todoRequestDto, User user) {
        Todo todo = getUserTodo(todoId, user);

        todo.setTitle(todoRequestDto.getTitle());
        todo.setContent(todoRequestDto.getContent());

        return new TodoResponseDto(todo);
    }

    // 완료 처리
    @Transactional
    public TodoResponseDto completeTodo(Long todoId, User user) {
        Todo todo = getUserTodo(todoId, user);

        todo.complete(); // 완료 처리

        return new TodoResponseDto(todo);

    }

    // 삭제
    public void deletePost(User user, Long todoId) {
        Todo post = getUserTodo(todoId, user);

        todoRepository.delete(post);
    }

    public Todo getTodo(Long todoId) {

        return todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할일 ID 입니다."));

    }


    public Todo getUserTodo(Long todoId, User user) {
        Todo todo = getTodo(todoId);

        if(!user.getId().equals(todo.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }

        return todo;
    }
}
