package com.ui_demo.service;

import com.ui_demo.dto.ConferencRoomBooking.ConferenceRoomBookingRequestDto;
import com.ui_demo.dto.ConferencRoomBooking.ConferenceRoomBookingResponseDto;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ConferenceRoomBookingService {

    @POST
    ConferenceRoomBookingResponseDto createBooking(@Valid ConferenceRoomBookingRequestDto request);

    @GET
    @Path("/my")
    List<ConferenceRoomBookingResponseDto> getBookings(@QueryParam("employeeId") Long employeeId);

    @GET
    @Path("/{id}")
    ConferenceRoomBookingResponseDto getBookingById(@PathParam("id") Long id);

    @PUT
    @Path("/{id}/cancel")
    ConferenceRoomBookingResponseDto cancelBooking(@PathParam("id") Long id);

    @GET
    List<ConferenceRoomBookingResponseDto> getBookings(@QueryParam("roomId") Long roomId, @QueryParam("date") String date);

}