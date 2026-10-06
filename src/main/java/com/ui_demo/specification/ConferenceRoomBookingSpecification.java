package com.ui_demo.specification;

import com.ui_demo.entity.ConferenceRoomBooking;
import com.ui_demo.enums.BookingStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class ConferenceRoomBookingSpecification {
    public static Specification<ConferenceRoomBooking> hasRoom(Long roomId) {

        return (root, query,cb)->cb.equal(root.get("room").get("id"),roomId);
    }
    public static Specification<ConferenceRoomBooking> hasEmployee(Long employeeId) {
        return (root,query,cb)->cb.equal(root.get("employee").get("id"),employeeId);
    }


    public static Specification<ConferenceRoomBooking> hasConferenceRoomBooking(Long conferenceRoomBookingId) {
        return (root,query,cb)->cb.equal(root.get("id"),conferenceRoomBookingId);
    }
    public static Specification<ConferenceRoomBooking> hasConferenceRoom(LocalDate meetingDate) {
        return (root,query,cb)->cb.equal(root.get("meetingDate"),meetingDate);
    }
    public static Specification<ConferenceRoomBooking> datesBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> cb.between(root.get("meetingDate"), startDate, endDate);
    }
    public static Specification<ConferenceRoomBooking> attAttendeesGreaterThanEqualOrEquals(Integer attendeeId) {
        return (root, query, cb) -> cb.equal(root.get("attendees"), attendeeId);
    }
    //        get all my bookings
        public static Specification<ConferenceRoomBooking> getMyBookings (Long empId){
            return (root, query, cb) -> cb.equal(root.get("employee").get("id"), empId);

        }


    public static Specification<ConferenceRoomBooking> overlappingBooking(Long roomId, @NotNull(message = "Meeting date is required") @FutureOrPresent(message = "Booking date cannot be in the past") LocalDate meetingDate, @NotNull(message = "Start time is required") LocalTime startTime, @NotNull(message = "End time is required") LocalTime endTime) {

        return hasRoom(roomId)
                .and(hasMeetingDate(meetingDate))
                .and(hasStatus(BookingStatus.CONFIRMED))
                .and(startsBefore(endTime))
                .and(endsAfter(startTime));
    }

    private static Specification<ConferenceRoomBooking> endsAfter(LocalTime startTime) {
        return (root, query, cb) ->
                cb.greaterThan(root.get("endTime"), startTime);
    }

    private static Specification<ConferenceRoomBooking> startsBefore(LocalTime endTime) {
        return (root, query, cb) ->
                cb.lessThan(root.get("startTime"), endTime);
    }

    public static Specification<ConferenceRoomBooking> hasStatus(BookingStatus confirmed) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), BookingStatus.CONFIRMED);
    }

    public static Specification<ConferenceRoomBooking> hasMeetingDate(@NotNull(message = "Meeting date is required") @FutureOrPresent(message = "Booking date cannot be in the past") LocalDate meetingDate) {
        return (root, query, cb) -> cb.equal(root.get("meetingDate"), meetingDate);
    }
}

