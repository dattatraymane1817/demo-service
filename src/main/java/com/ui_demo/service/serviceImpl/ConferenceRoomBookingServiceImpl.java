package com.ui_demo.service.serviceImpl;

import com.ui_demo.dto.ConferencRoomBooking.ConferenceRoomBookingRequestDto;
import com.ui_demo.dto.ConferencRoomBooking.ConferenceRoomBookingResponseDto;
import com.ui_demo.entity.ConferenceRoom;
import com.ui_demo.entity.ConferenceRoomBooking;
import com.ui_demo.entity.Employee;
import com.ui_demo.enums.BookingStatus;
import com.ui_demo.enums.RoomStatus;
import com.ui_demo.exception.BookingException;
import com.ui_demo.exception.RoomAvailabilityException;
import com.ui_demo.mapper.ConferenceRoomBookingMapper;
import com.ui_demo.repository.ConferenceRoomBookingRepo;
import com.ui_demo.repository.ConferenceRoomRepo;
import com.ui_demo.repository.EmployeeRepo;
import com.ui_demo.service.ConferenceRoomBookingService;

import com.ui_demo.specification.ConferenceRoomBookingSpecification;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ConferenceRoomBookingServiceImpl implements ConferenceRoomBookingService {

    private final ConferenceRoomBookingRepo bookingRepo;
    private final ConferenceRoomRepo roomRepo;
    private final EmployeeRepo employeeRepo;

    public ConferenceRoomBookingServiceImpl(ConferenceRoomBookingRepo bookingRepo, ConferenceRoomRepo roomRepo, EmployeeRepo employeeRepo) {
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    @Transactional
    public ConferenceRoomBookingResponseDto createBooking(ConferenceRoomBookingRequestDto request) {


        ConferenceRoom room = roomRepo.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException("Conference room not found: " + request.getRoomId()));

        Employee employee = employeeRepo.findById(request.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found: " + request.getEmployeeId()));

        if (room.getStatus() != RoomStatus.AVAILABLE) {
            throw new BookingException("Room cannot be booked. Current room status: " + room.getStatus());
        }

        if (!request.getStartTime().isBefore(request.getEndTime())) {

            throw new BadRequestException("Start time must be before end time");
        }

        LocalDateTime requestedStart = LocalDateTime.of(request.getMeetingDate(), request.getStartTime());

        if (requestedStart.isBefore(LocalDateTime.now())) {

            throw new BadRequestException("Booking cannot be created in the past");
        }

        if (request.getAttendees() > room.getCapacity()) {

            throw new BadRequestException("Number of attendees (" + request.getAttendees() + ") exceeds room capacity (" + room.getCapacity() + ")");
        }

//        boolean conflict = bookingRepo.existsOverlappingBooking(room.getId(), request.getMeetingDate(), request.getStartTime(), request.getEndTime());
     Specification<ConferenceRoomBooking> conflictSpecification= ConferenceRoomBookingSpecification.overlappingBooking(room.getId(),request.getMeetingDate(),request.getStartTime(),request.getEndTime());
       boolean conflict= bookingRepo.exists(conflictSpecification);
        if (conflict) {
            throw new RoomAvailabilityException("Room already has a confirmed booking " + "for the requested time");
        }
        ConferenceRoomBooking booking = ConferenceRoomBookingMapper.toEntity(request);
        booking.setRoom(room);
        booking.setEmployee(employee);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setBookingNumber(generateBookingNumber());
        ConferenceRoomBooking savedBooking = bookingRepo.save(booking);
        return ConferenceRoomBookingMapper.toDto(savedBooking);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ConferenceRoomBookingResponseDto> getBookings(Long employeeId) {
        Specification<ConferenceRoomBooking> specification=ConferenceRoomBookingSpecification.hasEmployee(employeeId);
//        List<ConferenceRoomBooking> bookings = bookingRepo.findAll(specification);
        return bookingRepo.findAll(specification)
                .stream()
                .map(ConferenceRoomBookingMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ConferenceRoomBookingResponseDto getBookingById(Long id) {

        ConferenceRoomBooking booking = bookingRepo.findById(id)
                                                       .orElseThrow(() -> new NotFoundException("Booking not found: " + id));

        return ConferenceRoomBookingMapper.toDto(booking);
    }

    @Override
    public ConferenceRoomBookingResponseDto cancelBooking(Long id) {

        ConferenceRoomBooking booking = bookingRepo.findById(id)
                                         .orElseThrow(() -> new NotFoundException("Booking not found: " + id));

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new BadRequestException("Only CONFIRMED bookings can be cancelled");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        ConferenceRoomBooking savedBooking = bookingRepo.save(booking);
        return ConferenceRoomBookingMapper.toDto(savedBooking);
    }

    @Override
    public List<ConferenceRoomBookingResponseDto> getBookings(Long roomId, String date) {

        LocalDate meetingDate = LocalDate.parse(date);
        Specification<ConferenceRoomBooking> specification = ConferenceRoomBookingSpecification.hasRoom(roomId)
                .and(ConferenceRoomBookingSpecification.hasMeetingDate(meetingDate));

        return bookingRepo
                .findAll(specification)
                .stream()
                .map(ConferenceRoomBookingMapper::toDto)
                .toList();
    }
    @Scheduled(fixedRate = 60000)
    public void markCompletedBookings() {

        Specification<ConferenceRoomBooking> specification = ConferenceRoomBookingSpecification.hasStatus(BookingStatus.CONFIRMED);
        List<ConferenceRoomBooking> confirmedBookings = bookingRepo.findAll(specification);
        LocalDateTime now = LocalDateTime.now();

        for (ConferenceRoomBooking booking : confirmedBookings) {
            LocalDateTime bookingEnd = LocalDateTime.of(booking.getMeetingDate(), booking.getEndTime());
            if (!bookingEnd.isAfter(now)) {
                booking.setStatus(BookingStatus.COMPLETED);
                bookingRepo.save(booking);
            }
        }
    }

    private String generateBookingNumber() {

        return "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        
    }
}