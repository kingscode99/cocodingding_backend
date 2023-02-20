package com.sparta.serviceteam4444.controller.detail;

import com.sparta.serviceteam4444.dto.wedRtc_openvidu.GetRoomResponseDto;
import com.sparta.serviceteam4444.dto.wedRtc_openvidu.RoomCreateRequestDto;
import com.sparta.serviceteam4444.dto.wedRtc_openvidu.RoomCreateResponseDto;
import com.sparta.serviceteam4444.service.wedRtc_openvidu.RoomService;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
@RequestMapping("/detail")
@Api(tags = {"deatil"})
public class OpenviduController {

    private final RoomService roomService;

    //방 생성
    @ApiOperation(value = "화상채팅방 생성")
    @PostMapping("/room")
    public RoomCreateResponseDto createRoom(@RequestBody RoomCreateRequestDto roomCreateRequestDto) throws OpenViduJavaClientException, OpenViduHttpException {
        return roomService.createRoom(roomCreateRequestDto);
    }
    //방 입장 동시에 방 정보 return
    @ApiOperation(value = "방 입장")
    @PostMapping("/room/{roomId}")
    public RoomCreateResponseDto enterRoom(@PathVariable Long roomId) throws OpenViduJavaClientException, OpenViduHttpException {
        return roomService.enterRoom(roomId);
    }
}