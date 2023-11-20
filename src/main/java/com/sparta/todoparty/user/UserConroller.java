package com.sparta.todoparty.user;


import com.sparta.todoparty.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserConroller {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        try {
            userService.signup(userRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().
                    body(new CommonResponseDto("중복된 username 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        // 정상적으로 생성되었을때의 응답
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

}
