package com.sparta.serviceteam4444.service.wedRtc_openvidu;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sparta.serviceteam4444.dto.wedRtc_openvidu.CreateRoomResponseDto;
import com.sparta.serviceteam4444.dto.wedRtc_openvidu.CreateRoomRequestDto;
import com.sparta.serviceteam4444.dto.wedRtc_openvidu.RoomMemberResponseDto;
import com.sparta.serviceteam4444.dto.wedRtc_openvidu.RoomPasswordRequestDto;
import com.sparta.serviceteam4444.entity.user.User;
import com.sparta.serviceteam4444.entity.webRtc_openvidu.BenUser;
import com.sparta.serviceteam4444.entity.webRtc_openvidu.Room;
import com.sparta.serviceteam4444.entity.webRtc_openvidu.RoomMember;
import com.sparta.serviceteam4444.exception.CheckApiException;
import com.sparta.serviceteam4444.exception.ErrorCode;
import com.sparta.serviceteam4444.repository.wedRtc_openvidu.BenUserRepository;
import com.sparta.serviceteam4444.repository.wedRtc_openvidu.RoomUserRepository;
import com.sparta.serviceteam4444.repository.wedRtc_openvidu.RoomRepository;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final RoomUserRepository roomUserRepository;

    private OpenVidu openVidu;

    @Value("${openvidu.url}")
    private String OPENVIDU_URL;

    @Value("${openvidu.secret}")
    private String OPENVIDU_SECRET;

    @PostConstruct
    public OpenVidu openVidu(){
        return openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    public CreateRoomResponseDto createRoom(CreateRoomRequestDto createRoomRequestDto,
                                            HttpServletRequest request) throws OpenViduJavaClientException, OpenViduHttpException{

        return null;

    }

//    //SDK의 진입점인 OpenVidu 객체
//    private OpenVidu openVidu;
//
//    //세션 이름과 OpenVidu Session 개체를 쌍으로 구성하는 컬렉션
//    private Map<String, Session> mapSessions = new ConcurrentHashMap<>();
//
//    //세션 이름과 토큰을 쌍으로 구성하는 컬렉션(내부 맵 쌍 토큰과 관련된 역할)
//    private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();
//
//    //OpenVidu 서버가 수신 중인 URL
//    @Value("${openvidu.url}")
//    private String OPENVIDU_URL;
//
//    //OpenVidu 서버와 공유된 비밀
//    @Value("${openvidu.secret}")
//    private String OPENVIDU_SECRET;
//
//    public ResponseEntity<JsonObject> getToken(String sessionNameParam, HttpSession httpSession) throws ParseException {
//
//        //사용자가 HttpSession으로 기록되었는지 확인하고 진행
//
//        JSONObject sessionJSON = (JSONObject) new JSONParser().parse(sessionNameParam);
//
//        // 화상채팅을 연결하기 위해서
//        String sessionName = (String) sessionJSON.get("sessionName");
//
//        //사용자와 연결된 역할
////        OpenViduRole role =
//
//        // 사용자가 비디오 통화에 연결할 때 다른 사용자에게 전달되는 선택적 데이터
//        // 로그인 시 HttpSession 개체에 저장한 값을 가진 JSON
//        String serverData = "test";
//
//        //서버Data 및 역할을 사용하여 connectionProperties 개체 구축
//        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
//                .type(ConnectionType.WEBRTC)
//                .data(serverData)
//                .build();
//
//        JSONObject responseJSON = new JSONObject();
//
//        //세션이 존재하는 경우
//        if (this.mapSessions.get(sessionName) == null){
//
//            System.out.println("New Session" + sessionName);
//
//            try {
//                //새 OpenVidu 세션 생성
//                Session session = this.openVidu.createSession();
//
//                //최근에 만든 connectionProperties로 새로운 연결 생성
//                String token = this.mapSessions.get(sessionName).createConnection(connectionProperties).getToken();
//
//                //컬렉션에 세션 및 토큰 저장
//                this.mapSessions.put(sessionName, session);
//                this.mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
//                this.mapSessionNamesTokens.get(sessionName).put(token, OpenViduRole.PUBLISHER);
//
//                //토큰으로 응답 준비
//                responseJSON.put(0, token);
//
//                // 클라이언트에 응답 반환
//                return new ResponseEntity<>(responseJSON, HttpStatus.OK);
//            }catch (Exception e){
//                return getErrorResponse(e);
//            }
//
//        }
//
//    }


//    private final RoomRepository roomRepository;
//
//    private final RoomUserRepository roomUserRepository;
//
//    private final BenUserRepository benUserRepository;
//
//    //SDK의 진입점인 OpenVidu 객체
//    private OpenVidu openVidu;
//
//    //OpenVidu 서버가 수신하는 URL
//    @Value("${openvidu.url}")
//    private String OPENVIDU_URL;
//
//    //OpenVidu 서버와 공유되는 secret 키
//    @Value("${openvidu.secret}")
//    private String OPENVIDU_SECRET;
//
//    //Openvicu 객체에 url과 secret 키를 넣어주기
//    //한번만 적용
//    @PostConstruct
//    public OpenVidu openVidu(){
//        return openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
//    }
//
//    //방 만들기
//    public CreateRoomResponseDto createRoom(CreateRoomRequestDto createRoomRequestDto, HttpServletRequest request)
//            throws OpenViduJavaClientException, OpenViduHttpException {
//
//        //userNickname 가져오기
//        User user = new User();
//
//        //room 생성하기 - createNewToken 메소드에 openvidu 세션 생성 메소드 포함.
//        CreateRoomResponseDto newToken = createNewToken(user);
//
//        //체팅방 빌드
//        Room room = Room.builder()
//                .sessionId("test")
////                .roomTitle(createRoomRequestDto.getRoomName())
////                .masterUserNickname(user.getNickname())
//                .build();
//
//        roomRepository.save(room);
//
//        //체팅방 인원 빌드
//        RoomMember roomMembers = RoomMember.builder()
//                .sessionId("test") //수정
////                .userId(user.getId())
////                .nickname(user.getNickname())
////                .enterRoomToken(newToken.getSessionId())
//                .build();
//
//        //체팅방 인원 저장하기
//        roomUserRepository.save(roomMembers);
//
//        //masterUser 인지 아닌지를 판단 후 user 추가하기
//        boolean roomMaster;
//
//        List<RoomMember> roomMemberList = roomUserRepository.findAllBySessionId(newToken.getSessionId());
//        List<RoomMemberResponseDto> roomMemberResponseDtoList = new ArrayList<>();
//
//        //체팅방 인원 추가
//        for(RoomMember roomMember: roomMemberList){
//            if(user != null){
//                //roomMaster인지 아닌지 판별
//                roomMaster = Objects.equals(roomMember.getNickname(), user.getNickname());
//            }else {
//                roomMaster = false;
//            }
//            roomMemberResponseDtoList.add(new RoomMemberResponseDto(roomMember, roomMaster));
//        }
//
//        Long currentMember = roomUserRepository.countAllBySessionId(newToken.getSessionId());
//
//        room.updateCurrentMember(currentMember);
//
//        //체팅방 저장
//        roomRepository.save(room);
//
//        // 저장된 채팅방의 roomId는 OpenVidu 채팅방의 세션 아이디로써 생성 후 바로 해당 채팅방의 세션 아이디와
//        // 오픈 비두 서버에서 미디어 데이터를 받아올 떄 사용할 토큰을 리턴.
//        // 채팅방 생성 후 최초 채팅방 생성자는 채팅방에 즉시 입장할 것으로 예상 -> 채팅방이 보여지기 위한 정보들을 리턴
//        return CreateRoomResponseDto.builder()
//                .sessionId("test")
////                .roomName(newToken.getRoomName())
////                .masterNickname(user.getNickname())
////                .currentMember(currentMember)
////                .roomMemberResponseDtoList(roomMemberResponseDtoList)
//                .token(newToken.getToken())
//                .build();
//    }
//
//
////    //체팅방 생성시 코튼 발급.
////    private CreateRoomResponseDto createNewToken(User user)throws OpenViduJavaClientException, OpenViduHttpException {
////        //사용자 연결 시 닉네임 전달
////        String userNickName = user.getNickname();
////        //받은 닉네임으로 ConnectionProperties 빌드
////        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
////                .type(ConnectionType.WEBRTC)
////                .data(userNickName).build();
////        //새로운 Openvidu 세션(체팅방)생성
////        Session session =openVidu.createSession();
////        //토큰 받기
////        String token =session.createConnection(connectionProperties).getToken();
////        //빌드 리턴.
////        return CreateRoomResponseDto.builder()
////                .sessionId(session.getSessionId())
////                .token(token)
////                .build();
////    }
//
//    public RoomMemberResponseDto enterRoom(String roomId,
//                                           HttpServletRequest request,
//                                           RoomPasswordRequestDto roomPasswordRequestDto) throws OpenViduJavaClientException, OpenViduHttpException {
//        //토큰 검증 및 멤버 객체 가져오기
//        User user = new User();
//
//        //방 유무 확인
//        Room room = roomRepository.findById(roomId).orElseThrow(
//                () -> new CheckApiException(ErrorCode.NOT_EXITS_ROOM)
//        );
//
//        //방에서 강퇴당한 유저인지 확인
//        BenUser benUser = benUserRepository.findByUserIdAndRoomId(user.getId(), roomId);
//
//        if(benUser != null){
//            throw new CheckApiException(ErrorCode.BEN_ROOM);
//        }
//
//        //방 인원 초과
//
//        //룸 멤버 확인
//
//        //방에 비밀번호 있는지 확인
//
//        //채팅방 입장 시 토큰 발급
//        String enterRoomToken = enterRoomCreateSession(user, "test");
//
//        RoomMember roomMember = RoomMember.builder()
//                .sessionId("test")
////                .userId(user.getKakaoId())
////                .nickname(user.getNickname())
////                .enterRoomToken(enterRoomToken)
//                .build();
//
//        //채팅방 인원 저장
//        roomUserRepository.save(roomMember);
//
//        boolean roomMaster = false;
//
//        List<RoomMember> roomMemberList = roomUserRepository.findAllBySessionId("test");
//        List<RoomMemberResponseDto> roomMemberResponseDtoList = new ArrayList<>();
//
//        //채팅방 인원 추가
//        for (RoomMember addRoomMember : roomMemberList){
//            //방장
//            if (user != null){
//                roomMaster = Objects.equals(addRoomMember.getNickname(), user.getNickname());
//            }
//            //방장 X
//            else {
//                roomMaster = false;
//            }
//            roomMemberResponseDtoList.add(new RoomMemberResponseDto(addRoomMember, roomMaster));
//        }
//
//        Long currentUser = roomUserRepository.countAllBySessionId(room.getSessionId());
//
//        room.updateCurrentMember(currentUser);
//
//        roomRepository.save(room);
//
//        return RoomMemberResponseDto.builder()
////                .roomMemberId(roomMember.getRoomMemberId())
//                .sessionId("test")
////                .userNickname(roomMember.getNickname())
////                .enterRoomToken(roomMember.getEnterRoomToken())
////                .roomMaster(roomMaster)
//                .build();
//
//    }
//
//    //방 생성 시 토큰 발급
//    private CreateRoomResponseDto createNewToken(User user) throws
//            OpenViduJavaClientException, OpenViduHttpException{
//
//        //사용자 연결 시 닉네임 전달
//        String serverData = user.getNickname();
//
//        //serverData를 사용해서 connectionProperties 객체 빌드
//        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
//                .type(ConnectionType.WEBRTC)
//                .data("user_data")
//                .build();
//
//        //새로운 openvidu 세션(방) 생성
//        Session session = openVidu.createSession();
//
//        String token = session.createConnection(connectionProperties).getToken();
//
//        return CreateRoomResponseDto.builder()
//                .sessionId(session.getSessionId())
//                .token(token)
//                .build();
//    }
//
//    //방 입장 시 토큰 발급
//    private String enterRoomCreateSession(User user, String test) throws OpenViduJavaClientException, OpenViduHttpException{
//
//        String serverData = user.getNickname();
//
//        //serverData를 사용해서 connectionProperties 객체 빌드
//        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
//                .type(ConnectionType.WEBRTC)
//                .data("user_data")
//                .build();
//
//        openVidu.fetch();
//
//        //openvidu에 활성화된 세션을 모두 가져와 리스트에 담음
//        List<Session> activeSessionList = openVidu.getActiveSessions();
//
//        //1. Request : 다른 유저가 타겟 채팅방에 입장하기 위한 타겟 채팅방의 세션 정보, 입장 요청하는 유저 정보
//        Session session = null;
//
//        //활성화된 session의 sessionId들을 registerReqChatRoom에서 리턴한 sessionId(입장할 채팅방 SessionId)와 비교
//        //같으면 해당 session으로 새로운 토큰 생성
//        for (Session getSession : activeSessionList){
//            if(getSession.getSessionId().equals(test)){
//                session = getSession;
//                break;
//            }
//        }
//
//        if (session == null){
//            throw new CheckApiException(ErrorCode.NOT_EXITS_ROOM);
//        }
//
//        //2. Openvidu에 유저 토큰 발급 요청 : 오픈비두 서버에 요청 유저가 타겟 채팅방에 입장할 수 있는 토큰을 발급 요청
//        //토큰을 가져옴
//        return session.createConnection(connectionProperties).getToken();
//    }
}