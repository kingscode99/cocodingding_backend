package com.sparta.serviceteam4444.controller.socket;

import com.sparta.serviceteam4444.dto.socket.ChatRoom;
import com.sparta.serviceteam4444.repository.socket.ChatRoomRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    //모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> rooms(){
        return chatRoomRepository.findAllRoom();
    }

    //채팅방 생성
    @PostMapping("/room")
    public ChatRoom creatRoom(@RequestParam(defaultValue = "값이 넘어오지 않아요") String name){
        log.info(name);
        return chatRoomRepository.createChatRoom(name);
    }

    //특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId){
        return chatRoomRepository.findRoomById(roomId);
    }


//    @ApiOperation(value = "실험용 채팅방 예시 페이지", notes = "무시해도 되며 프론트 작업 완료 시 삭제")
//    @GetMapping("room")
//    public String rooms(Model model) {
//        return "chat/room";
//    }
//
//    @ApiOperation(value = "방 다 보여주기", notes = "방 목록들을 보여준다.")
//    @GetMapping("rooms")
//    @ResponseBody
//    public List<ChatRoom> room() {return chatRoomRepository.findAllRoom();}
//
//    @ApiOperation(value = "방 파기", notes = "채팅방 하나를 판다.")
//    @PostMapping("rooms")
//    @ResponseBody
//    public ChatRoom createRoom(@RequestParam String roomName, String category) {
//        return chatRoomRepository.createChatRoom(roomName, category);
//    }
//
//    @ApiOperation(value = "실험용 입장 시 화면 페이지", notes = "무시해도 되며 프론트 작업 완료 시 삭제")
//    @GetMapping("room/enter/{id}")
//    public String roomDetail(Model model, @PathVariable String id) {
//        model.addAttribute("id", id);
//        return "chat/roomdetail";
//    }
//    @ApiOperation(value = "특정 채팅방 찾기", notes = "아이디 값으로 특정 채팅방 찾기")
//    @GetMapping("room/{id}")
//    @ResponseBody
//    public ChatRoom roomInfo(@PathVariable String id) {
//        return chatRoomRepository.findRoomById(id);
//    }
}
