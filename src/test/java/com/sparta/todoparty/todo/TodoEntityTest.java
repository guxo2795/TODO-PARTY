package com.sparta.todoparty.todo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoEntityTest {

    @Autowired
    TodoRepository todoRepository;

    private ValidatorFactory factory;
    private Validator validator;

    @BeforeEach
    void setup() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("TodoEntity 생성 성공")
    void test1() {
        // given
//        private String title;
//        private String content;
        String title = "testtitle";
        String content = "testcontent";

        TodoRequestDto requestDto = new TodoRequestDto(
                title,
                content
        );

        // when
        Set<ConstraintViolation<TodoRequestDto>> violations = validator.validate(requestDto);
        // validator.validate(requestDto)
        // => requestDto가 유효한지 판단
        // 비어있다면 오류x, 값이 있다면 오류o

        // then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("TodoEntity 생성 실패 - title")
    void test2() {
        // given
        String title = "";
        String content = "testcontent";

        TodoRequestDto requestDto = new TodoRequestDto(
                title,
                content
        );

        // when
        Set<ConstraintViolation<TodoRequestDto>> violations = validator.validate(requestDto);

        // then
        assertThat(violations).extracting("message").contains("비어 있을 수 없습니다");
    }

    @Test
    @DisplayName("TodoEntity 생성 실패 - content")
    void test3() {
        // given
        String title = "testtitle";
        String content = "";

        TodoRequestDto requestDto = new TodoRequestDto(
                title,
                content
        );

        // when
        Set<ConstraintViolation<TodoRequestDto>> violations = validator.validate(requestDto);

        // then
        assertThat(violations).extracting("message").contains("비어 있을 수 없습니다");
    }


}