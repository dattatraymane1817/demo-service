package com.ui_demo.mapper;

import com.ui_demo.dto.ConferencRoomBooking.ConferenceRoomBookingRequestDto;
import com.ui_demo.dto.ConferencRoomBooking.ConferenceRoomBookingResponseDto;
import com.ui_demo.entity.ConferenceRoomBooking;

public final class ConferenceRoomBookingMapper {

    private ConferenceRoomBookingMapper() {
    }

    public static ConferenceRoomBooking toEntity(ConferenceRoomBookingRequestDto dto) {

        ConferenceRoomBooking booking = new ConferenceRoomBooking();

        booking.setMeetingTitle(dto.getMeetingTitle());
        booking.setPurpose(dto.getPurpose());
        booking.setMeetingDate(dto.getMeetingDate());
        booking.setStartTime(dto.getStartTime());
        booking.setEndTime(dto.getEndTime());
        booking.setAttendees(dto.getAttendees());
//        booking.setRecurrencePattern(dto.getRecurrencePattern());

        return booking;
    }

    public static ConferenceRoomBookingResponseDto toDto(ConferenceRoomBooking booking) {

        ConferenceRoomBookingResponseDto dto = new ConferenceRoomBookingResponseDto();
        dto.setId(booking.getId());
        dto.setBookingNumber(booking.getBookingNumber());
        if (booking.getRoom() != null) {
            dto.setRoomId(booking.getRoom().getId());
            dto.setRoomCode(booking.getRoom().getRoomCode());
            dto.setRoomName(booking.getRoom().getRoomName());
        }

        if (booking.getEmployee() != null) {
            dto.setEmployeeId(booking.getEmployee().getId());
            dto.setEmployeeName(booking.getEmployee().getName());
            dto.setEmployeeEmail(booking.getEmployee().getEmail());
        }

        dto.setMeetingTitle(booking.getMeetingTitle());
        dto.setPurpose(booking.getPurpose());
        dto.setMeetingDate(booking.getMeetingDate());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        dto.setAttendees(booking.getAttendees());
        dto.setStatus(booking.getStatus());
//        dto.setRecurrencePattern(booking.getRecurrencePattern());
        dto.setCreatedDate(booking.getCreatedDate());
        dto.setUpdatedDate(booking.getUpdatedDate());

        return dto;
    }
}