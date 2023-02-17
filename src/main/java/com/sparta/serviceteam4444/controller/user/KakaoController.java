package com.sparta.serviceteam4444.controller.user;

import com.sparta.serviceteam4444.service.user.KakaoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Kakao"})
@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;


}
