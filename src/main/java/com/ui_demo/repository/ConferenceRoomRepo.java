package com.ui_demo.repository;

import com.ui_demo.entity.ConferenceRoom;

import com.ui_demo.enums.RoomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Collection;
import java.util.Optional;

public interface ConferenceRoomRepo extends JpaRepository<ConferenceRoom, Long> , JpaSpecificationExecutor<ConferenceRoom> {

    boolean existsByRoomCode(String roomCode);

    Optional<ConferenceRoom> findByRoomCode(String roomCode);

    Page<ConferenceRoom> findByStatusAndCapacityGreaterThanEqual(RoomStatus roomStatus, Integer capacity, Pageable pageable);
    @Query("""
    SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
    FROM ConferenceRoomBooking b
    WHERE b.room.id = :roomId
      AND b.status IN ('CONFIRMED', 'PENDING')
      AND b.meetingDate >= CURRENT_DATE
""")
    boolean existsActiveBookingForRoom(@Param("roomId") Long roomId);

}