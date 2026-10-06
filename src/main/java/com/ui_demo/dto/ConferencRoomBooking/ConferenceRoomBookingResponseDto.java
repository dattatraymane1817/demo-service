package com.ui_demo.dto.ConferencRoomBooking;

import com.ui_demo.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceRoomBookingResponseDto {

    private Long id;

    private String bookingNumber;

    private Long roomId;

    private String roomCode;

    private String roomName;

    private Long employeeId;

    private String employeeName;

    private String employeeEmail;

    private String meetingTitle;

    private String purpose;

    private LocalDate meetingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer attendees;

    private BookingStatus status;

    private String recurrencePattern;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}