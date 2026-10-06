package com.ui_demo.mapper;

import com.ui_demo.dto.ConferenceRoom.ConferenceRoomRequestDto;
import com.ui_demo.dto.ConferenceRoom.ConferenceRoomResponseDto;
import com.ui_demo.entity.ConferenceRoom;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRoomMapper {

    public ConferenceRoom toEntity(ConferenceRoomRequestDto request) {

        ConferenceRoom room = new ConferenceRoom();
        room.setRoomCode(request.getRoomCode());
        room.setRoomName(request.getRoomName());
        room.setLocationFloor(request.getLocationFloor());
        room.setCapacity(request.getCapacity());
        room.setRoomType(request.getRoomType());
        room.setFacilities(request.getFacilities());
        room.setStatus(request.getStatus());

        return room;
    }

    public void updateEntity(ConferenceRoom room, ConferenceRoomRequestDto request) {

        room.setRoomCode(request.getRoomCode());
        room.setRoomName(request.getRoomName());
        room.setLocationFloor(request.getLocationFloor());
        room.setCapacity(request.getCapacity());
        room.setRoomType(request.getRoomType());
        room.setFacilities(request.getFacilities());
        room.setStatus(request.getStatus());

    }

    public ConferenceRoomResponseDto toDto(ConferenceRoom room) {

        ConferenceRoomResponseDto response = new ConferenceRoomResponseDto();
        response.setId(room.getId());
        response.setRoomCode(room.getRoomCode());
        response.setRoomName(room.getRoomName());
        response.setLocationFloor(room.getLocationFloor());
        response.setCapacity(room.getCapacity());
        response.setRoomType(room.getRoomType());
        response.setFacilities(room.getFacilities());
        response.setStatus(room.getStatus());
        response.setCreatedDate(room.getCreatedDate());
        response.setUpdatedDate(room.getUpdatedDate());

        return response;
    }
}