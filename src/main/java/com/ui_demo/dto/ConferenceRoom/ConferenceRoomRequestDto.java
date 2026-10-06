package com.ui_demo.dto.ConferenceRoom;

import com.ui_demo.enums.RoomStatus;
import com.ui_demo.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ConferenceRoomRequestDto {

    @NotBlank(message = "Room code is required")
    private String roomCode;

    @NotBlank(message = "Room name is required")
    private String roomName;

    @NotBlank(message = "Location/Floor is required")
    private String locationFloor;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than 0")
    private Integer capacity;

    @NotNull(message = "Room type is required")
    private RoomType roomType;

    private Set<String> facilities = new HashSet<>();

    @NotNull(message = "Status is required")
    private RoomStatus status;
}