package com.ui_demo.repository;

import com.ui_demo.entity.ConferenceRoomBooking;
import com.ui_demo.enums.BookingStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface ConferenceRoomBookingRepo extends JpaRepository<ConferenceRoomBooking, Long>, JpaSpecificationExecutor<ConferenceRoomBooking> {
    boolean existsByRoomIdAndMeetingDateAndStatusAndStartTimeLessThanAndEndTimeGreaterThan(
            Long roomId,
            LocalDate meetingDate,
            BookingStatus status,
            LocalTime endTime,
            LocalTime startTime
    );

    List<ConferenceRoomBooking> findByRoomIdOrderByMeetingDateDescStartTimeDesc(
            Long roomId
    );

    @Query("""
        SELECT COUNT(b) > 0
        FROM ConferenceRoomBooking b
        WHERE b.room.id = :roomId
          AND b.meetingDate = :meetingDate
          AND b.status = com.ui_demo.enums.BookingStatus.CONFIRMED
          AND b.startTime < :endTime
          AND b.endTime > :startTime
        """)
    boolean existsOverlappingBooking(
            @Param("roomId") Long roomId,
            @Param("meetingDate") LocalDate meetingDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    List<ConferenceRoomBooking> findByRoomIdAndMeetingDate(
            Long roomId,
            LocalDate meetingDate
    );

    List<ConferenceRoomBooking> findByStatus(BookingStatus status);

    List<ConferenceRoomBooking> findByEmployeeId(Long employeeId);
}
