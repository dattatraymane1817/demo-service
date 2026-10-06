package com.ui_demo.service.serviceImpl;

import com.ui_demo.dto.ConferencRoomBooking.ConferenceRoomBookingResponseDto;
import com.ui_demo.enums.BookingStatus;
import com.ui_demo.mapper.ConferenceRoomBookingMapper;
import com.ui_demo.repository.DashboardRepository;
import com.ui_demo.service.AdminDashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final DashboardRepository dashboardRepository;

    public AdminDashboardServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public int getTodayBookingCount(Long employeeId) {
        if (employeeId == null) {
            return (int) dashboardRepository.countByMeetingDate(
                    LocalDate.now()
            );
        }

        return (int) dashboardRepository.countByEmployeeIdAndMeetingDate(
                employeeId,
                LocalDate.now()
        );
    }

    @Override
    public int getConfirmedBookingCount(Long employeeId) {
        if (employeeId == null) {
            return (int) dashboardRepository.countByStatus(
                    BookingStatus.CONFIRMED
            );
        }

        return (int) dashboardRepository.countByEmployeeIdAndStatus(
                employeeId,
                BookingStatus.CONFIRMED
        );
    }

    @Override
    public int getCancelledBookingCount(Long employeeId) {
        if (employeeId == null) {
            return (int) dashboardRepository.countByStatus(
                    BookingStatus.CANCELLED
            );
        }

        return (int) dashboardRepository.countByEmployeeIdAndStatus(
                employeeId,
                BookingStatus.CANCELLED
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConferenceRoomBookingResponseDto> getUpcomingBookings(Long employeeId
    ) {

        if (employeeId == null) {
            return dashboardRepository
                    .findByMeetingDateGreaterThanEqualOrderByMeetingDateAscStartTimeAsc(
                            LocalDate.now()
                    )
                    .stream()
                    .map(ConferenceRoomBookingMapper::toDto)
                    .toList();
        }

        return dashboardRepository
                .findByEmployeeIdAndMeetingDateGreaterThanEqualOrderByMeetingDateAscStartTimeAsc(
                        employeeId,
                        LocalDate.now()
                )
                .stream()
                .map(ConferenceRoomBookingMapper::toDto)
                .toList();
    }
}