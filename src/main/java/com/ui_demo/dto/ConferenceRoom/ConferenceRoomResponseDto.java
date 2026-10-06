package com.ui_demo.dto.ConferenceRoom;

import com.ui_demo.enums.RoomStatus;
import com.ui_demo.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ConferenceRoomResponseDto {

    private Long id;

    private String roomCode;

    private String roomName;

    private String locationFloor;

    private Integer capacity;

    private RoomType roomType;

    private Set<String> facilities = new HashSet<>();

    private RoomStatus status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}