package com.ui_demo.repository;

import com.ui_demo.entity.ConferenceRoomBooking;
import com.ui_demo.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DashboardRepository extends JpaRepository<ConferenceRoomBooking, Long> {

    long countByMeetingDate(LocalDate meetingDate);

    long countByStatus(BookingStatus status);

    List<ConferenceRoomBooking> findByMeetingDateGreaterThanEqualOrderByMeetingDateAscStartTimeAsc(
            LocalDate meetingDate
    );

    long countByEmployeeIdAndMeetingDate(
            Long employeeId,
            LocalDate meetingDate
    );

    long countByEmployeeIdAndStatus(
            Long employeeId,
            BookingStatus status
    );

    List<ConferenceRoomBooking> findByEmployeeIdAndMeetingDateGreaterThanEqualOrderByMeetingDateAscStartTimeAsc(
            Long employeeId,
            LocalDate meetingDate
    );
}