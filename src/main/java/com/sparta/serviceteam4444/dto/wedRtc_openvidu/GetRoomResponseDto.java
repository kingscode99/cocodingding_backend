package com.sparta.serviceteam4444.dto.wedRtc_openvidu;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetRoomResponseDto {
    private String roomTitle;
    private String sessionId;
}