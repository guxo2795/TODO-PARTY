package com.sparta.todoparty.todo;


import com.sparta.todoparty.CommonResponseDto;
import com.sparta.todoparty.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 생성
    @PostMapping
    public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto todoRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto responseDto =  todoService.createPost(todoRequestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(responseDto);
    }

    // 단건 조회
    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> getTodo(@PathVariable Long todoId) {
        try {
            TodoResponseDto responseDto = todoService.getTodoById(todoId);
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 목록 조회
//    @GetMapping
//    public ResponseEntity<List<TodoListResponseDto>> getTodoList() {
//        List<TodoListResponseDto> response = new ArrayList<>();
//
//        Map<UserDto, List<TodoResponseDto>> responseDtoMap = todoService.getUserTodoMap();
//
//        responseDtoMap.forEach((key, value) -> response.add(new TodoListResponseDto(key, value)));
//
//        return ResponseEntity.ok().body(response);
//    }

    // 목록 조회
    @GetMapping()
    public ResponseEntity<List<TodoResponseDto>> getTodoList() {
        List<TodoResponseDto> responseDto = todoService.getTodoList();
        return ResponseEntity.ok().body(responseDto);
    }

    // 수정(제목, 작성내용)
    @PutMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            TodoResponseDto responseDto = todoService.updateTodo(todoId, todoRequestDto, userDetails.getUser());
            return ResponseEntity.ok().body(responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 완료 처리
    @PatchMapping("/{todoId}/complete")
    public ResponseEntity<CommonResponseDto> patchTodo(@PathVariable Long todoId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            TodoResponseDto responseDto = todoService.completeTodo(todoId, userDetails.getUser());
            return ResponseEntity.ok().body(responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 삭제
    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long todoId) {
        try {
            todoService.deletePost(userDetails.getUser(), todoId);
            return ResponseEntity.ok().body(new CommonResponseDto("게시물 삭제가 완료되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}
