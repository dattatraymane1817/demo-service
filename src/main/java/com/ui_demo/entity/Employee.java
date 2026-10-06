package com.ui_demo.entity;

import com.ui_demo.enums.EmployeeRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Employee extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole role;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<ConferenceRoomBooking> bookings = new ArrayList<>();
}