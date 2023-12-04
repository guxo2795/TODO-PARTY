//package com.sparta.todoparty.comment;
//
//import com.sparta.todoparty.todo.TodoRepository;
//import com.sparta.todoparty.todo.TodoRequestDto;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import jakarta.validation.ValidatorFactory;
//import jakarta.validation.constraints.NotEmpty;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class CommentEntityTest {
//
//    @Autowired
//    CommentRepository commentRepository;
//
//    private ValidatorFactory factory;
//    private Validator validator;
//
//    @BeforeEach
//    void setup() {
//        factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Test
//    @DisplayName("CommentEntity 생성 성공")
//    void test1() {
//        // given
////        @NotNull
////        private Long todoId;
////        @NotEmpty
////        private String text;
//        Long todoId = 1L;
//        String text= "testtext";
//
//        CommentRequestDto requestDto = new CommentRequestDto(
//                todoId,
//                text
//        );
//
//        // when
//        Set<ConstraintViolation<CommentRequestDto>> violations = validator.validate(requestDto);
//        // validator.validate(requestDto)
//        // => requestDto가 유효한지 판단
//        // 비어있다면 오류x, 값이 있다면 오류o
//
//        // then
//        assertThat(violations).isEmpty();
//    }
//
//    @Test
//    @DisplayName("CommentEntity 생성 실패 - todoId")
//    void test2() {
//        // given
//        Long todoId = null;
//        String text = "testtext";
//
//        CommentRequestDto requestDto = new CommentRequestDto(
//                todoId,
//                text
//        );
//
//        // when
//        Set<ConstraintViolation<CommentRequestDto>> violations = validator.validate(requestDto);
//
//        // then
//        assertThat(violations).extracting("message").contains("널이어서는 안됩니다");
//    }
//
//    @Test
//    @DisplayName("CommentEntity 생성 실패 - text")
//    void test3() {
//        // given
//        Long todoId = 1L;
//        String text = "";
//
//        CommentRequestDto requestDto = new CommentRequestDto(
//                todoId,
//                text
//        );
//
//        // when
//        Set<ConstraintViolation<CommentRequestDto>> violations = validator.validate(requestDto);
//
//        // then
//        assertThat(violations).extracting("message").contains("비어 있을 수 없습니다");
//    }
//
//
//}