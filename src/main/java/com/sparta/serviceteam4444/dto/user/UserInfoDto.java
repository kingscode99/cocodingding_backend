package com.sparta.serviceteam4444.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoDto {

    private String username;
    private String nickname;

    public UserInfoDto(String username, String nickname) {
        this.username= username;
        this.nickname = nickname;
    }
}