package com.sparta.serviceteam4444.service.user;

import com.sparta.serviceteam4444.dto.user.UserLoginDto;
import com.sparta.serviceteam4444.dto.user.UserResponseDto;
import com.sparta.serviceteam4444.dto.user.UserSignupDto;
import com.sparta.serviceteam4444.entity.user.User;
import com.sparta.serviceteam4444.exception.CheckApiException;
import com.sparta.serviceteam4444.exception.ErrorCode;
import com.sparta.serviceteam4444.repository.user.UserRepository;

import com.sparta.serviceteam4444.jwt.JwtUtil;
import com.sparta.serviceteam4444.service.email.EmailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void signup(UserSignupDto userSignupDto) {

        String userEmail = userSignupDto.getUserEmail();

        String userNickname = userSignupDto.getUserNickname();

        String userPassword = passwordEncoder.encode(userSignupDto.getUserPassword());

        //이메일 중복 검사
        Optional<User> userNameDuplicate = userRepository.findByUserEmail(userSignupDto.getUserEmail());

        if (userNameDuplicate.isPresent()) {
            throw new CheckApiException(ErrorCode.EXITS_EMAIL);
        }

        User user = new User(userEmail, userNickname, userPassword);

        userRepository.save(user);

    }

    public UserResponseDto login(UserLoginDto userLoginDto, HttpServletResponse response){

        String userEmail = userLoginDto.getUserEmail();

        String userPassword = userLoginDto.getUserPassword();

        String data = "로그인 성공";

        int statucode = 200;

        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXITS_USER)
        );

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())){
            throw new CheckApiException(ErrorCode.NOT_EQUALS_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserEmail()));

        return new UserResponseDto(data, statucode, user.getUserEmail(), user.getUserNickname());

    }
}