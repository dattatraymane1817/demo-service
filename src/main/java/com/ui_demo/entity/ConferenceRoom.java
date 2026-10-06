package com.ui_demo.entity;

import com.ui_demo.enums.RoomStatus;
import com.ui_demo.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "conference_room")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ConferenceRoom extends BaseModel {

    @Column(name = "room_code", nullable = false, unique = true)
    private String roomCode;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "location_floor", nullable = false)
    private String locationFloor;

    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "room_facility", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "facility")
    private Set<String> facilities = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<ConferenceRoomBooking> bookings = new ArrayList<>();

}