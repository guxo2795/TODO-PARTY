//package com.sparta.todoparty.user;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import jakarta.validation.ValidatorFactory;
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
//class UserEntityTest {
//
//    @Autowired
//    UserRepository userRepository;
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
//    @DisplayName("UserEntity 생성 성공")
//    void test1() {
//        // given
////        @Pattern(regexp = "^[a-z0-9]{4,10}$")
////        private String username;
////        @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
////        private String password;
//        String username = "testname";
//        String userpassword = "testpassword";
//
//        UserRequestDto requestDto = new UserRequestDto(
//                username,
//                userpassword
//        );
//
//        // when
//        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(requestDto);
//        // validator.validate(requestDto)
//        // => requestDto가 유효한지 판단
//        // 비어있다면 오류x, 값이 있다면 오류o
//
//        // then
//        assertThat(violations).isEmpty();
//    }
//
//    @Test
//    @DisplayName("UserEntity 생성 실패 - username")
//    void test2() {
//        // given
//        String username = "not user name";
////        @Pattern(regexp = "^[a-z0-9]{4,10}$")
////        private String username;
//        String userpassword = "testpassword";
//
//        UserRequestDto requestDto = new UserRequestDto(
//                username,
//                userpassword
//        );
//
//        // when
//        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(requestDto);
//
//        // then
//        assertThat(violations).extracting("message").contains("\"^[a-z0-9]{4,10}$\"와 일치해야 합니다");
//    }
//
//    @Test
//    @DisplayName("UserEntity 생성 실패 - userpassword")
//    void test3() {
//        // given
//        String username = "testname";
//        String userpassword = "not userpassword";
////        @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
////        private String password;
//
//        UserRequestDto requestDto = new UserRequestDto(
//                username,
//                userpassword
//        );
//
//        // when
//        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(requestDto);
//
//        // then
//        assertThat(violations).extracting("message").contains("\"^[a-zA-Z0-9]{8,15}$\"와 일치해야 합니다");
//    }
//
//
//}