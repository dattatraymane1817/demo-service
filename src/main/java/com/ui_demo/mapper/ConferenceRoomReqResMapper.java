package com.ui_demo.mapper;

import com.ui_demo.dto.ConferenceRoom.ConferenceRoomRequestDto;
import com.ui_demo.dto.ConferenceRoom.ConferenceRoomResponseDto;
import com.ui_demo.entity.ConferenceRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ConferenceRoomReqResMapper {

    ConferenceRoomResponseDto toDto(ConferenceRoom entity);
    ConferenceRoom toEntity(ConferenceRoomRequestDto dto);
    void updateConferenceRoom(ConferenceRoomRequestDto dto, @MappingTarget ConferenceRoom entity);
}
