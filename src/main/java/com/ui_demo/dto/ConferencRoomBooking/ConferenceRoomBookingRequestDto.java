package com.ui_demo.dto.ConferencRoomBooking;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ConferenceRoomBookingRequestDto {

    @NotNull(message = "Room is required")
    private Long roomId;

    @NotNull(message = "Employee is required")
    private Long employeeId;

    @NotBlank(message = "Meeting title is required")
    private String meetingTitle;

    private String purpose;

    @NotNull(message = "Meeting date is required")
    @FutureOrPresent(message = "Booking date cannot be in the past")
    private LocalDate meetingDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Attendees are required")
    @Positive(message = "Attendees must be greater than 0")
    private Integer attendees;

    private String recurrencePattern;
}