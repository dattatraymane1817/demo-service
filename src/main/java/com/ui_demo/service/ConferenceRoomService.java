package com.ui_demo.service;

import com.ui_demo.dto.ConferenceRoom.ConferenceRoomRequestDto;
import com.ui_demo.dto.ConferenceRoom.ConferenceRoomResponseDto;
import com.ui_demo.dto.PdfUploadResponseDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.transaction.annotation.Transactional;


import java.io.InputStream;
import java.util.List;

@Path("/api/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ConferenceRoomService {

    @POST
    ConferenceRoomResponseDto createRoom(ConferenceRoomRequestDto request);

    @GET
    List<ConferenceRoomResponseDto> getRooms();

    @GET
    @Path("/{id}")
    ConferenceRoomResponseDto getRoomById(
            @PathParam("id") Long id
    );

    @PUT
    @Path("/{id}")
    ConferenceRoomResponseDto updateRoom(
            @PathParam("id") Long id,
            ConferenceRoomRequestDto request
    );

    @PUT
    @Path("/{id}/deactivate")
    String deactivateRoom(
            @PathParam("id") Long id
    );

    @GET
    @Path("/available")
    List<ConferenceRoomResponseDto> getAvailableRooms(
            @QueryParam("date") String date,
            @QueryParam("startTime") String startTime,
            @QueryParam("endTime") String endTime,
            @QueryParam("capacity") Integer capacity,
            @QueryParam("location") String location,
            @QueryParam("facilities") String facilities,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size
    );

//    @GET
//    @Path("/{id}/booking-history")
//    List<ConferenceRoomResponseDto> getBookingHistory(
//            @PathParam("id") Long roomId
//    );
//
//    @Transactional(readOnly = true)
//    List<ConferenceRoomResponseDto> getAvailableRooms(Long roomId);

//    @Transactional(readOnly = true)
//    List<ConferenceRoomResponseDto> getAvailableRooms(
//            String date, String startTime, String endTime,
//            Integer capacity, String location, String facilities, int page, int size);

    @GET
    @Path("/exists")
    public  boolean existsByRoomCode(@QueryParam("roomCode") String roomCode) ;


//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Path("/upload-pdf")
//    <FormDataContentDisposition>
//    PdfUploadResponseDto uploadPdf(
//            @org.jboss.resteasy.annotations.jaxrs.FormParam("file") InputStream fileInputStream,
//            @org.jboss.resteasy.annotations.jaxrs.FormParam("file") FormDataContentDisposition fileDetail
//    );
//
//
//    PdfUploadResponseDto uploadPdf(MultipartFormDataInput input);
}