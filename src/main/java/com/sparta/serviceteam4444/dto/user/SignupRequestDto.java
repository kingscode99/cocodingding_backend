package com.sparta.serviceteam4444.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    private String nickname;
    private String email;
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}