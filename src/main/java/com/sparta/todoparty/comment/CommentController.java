package com.sparta.todoparty.comment;

import com.sparta.todoparty.CommonResponseDto;
import com.sparta.todoparty.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    // 댓글 생성
    @PostMapping()
    public ResponseEntity<CommentResponseDto> postComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto responseDto = commentService.createComment(commentRequestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(responseDto);
    }

    // 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> getComment(@PathVariable Long commentId) {
        try {
            CommentResponseDto responseDto = commentService.getComment(commentId);
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }


    // 댓글 수정(text)
    @PutMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> putComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        try {
            CommentResponseDto responseDto = commentService.updateComment(userDetails.getUser(), commentId, commentRequestDto);
            return ResponseEntity.ok().body(responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        try {
            commentService.deleteComment(userDetails.getUser(), commentId);
            return ResponseEntity.ok().body(new CommonResponseDto("댓글 삭제가 완료되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
