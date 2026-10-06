package com.ui_demo.entity;

import com.ui_demo.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "room_booking")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ConferenceRoomBooking extends BaseModel {

    @Column(name = "booking_number", nullable = false, unique = true)
    private String bookingNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private ConferenceRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "meeting_title", nullable = false)
    private String meetingTitle;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "meeting_date", nullable = false)
    private LocalDate meetingDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer attendees;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(name = "recurrence_pattern")
    private String recurrencePattern;
}