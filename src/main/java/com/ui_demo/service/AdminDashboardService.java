package com.ui_demo.service;

import com.ui_demo.dto.ConferencRoomBooking.ConferenceRoomBookingResponseDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/bookings")
@Produces(MediaType.APPLICATION_JSON)
public interface AdminDashboardService {

    @GET
    @Path("/today/count")
    int getTodayBookingCount(
            @QueryParam("employeeId") Long employeeId
    );

    @GET
    @Path("/confirmed/count")
    int getConfirmedBookingCount(
            @QueryParam("employeeId") Long employeeId
    );

    @GET
    @Path("/cancelled/count")
    int getCancelledBookingCount(
            @QueryParam("employeeId") Long employeeId
    );

    @GET
    @Path("/upcoming")
    List<ConferenceRoomBookingResponseDto> getUpcomingBookings(
            @QueryParam("employeeId") Long employeeId
    );
}