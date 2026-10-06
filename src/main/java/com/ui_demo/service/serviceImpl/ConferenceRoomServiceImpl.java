package com.ui_demo.service.serviceImpl;

import com.ui_demo.dto.ConferenceRoom.ConferenceRoomRequestDto;
import com.ui_demo.dto.ConferenceRoom.ConferenceRoomResponseDto;
import com.ui_demo.entity.ConferenceRoom;
import com.ui_demo.enums.RoomStatus;
import com.ui_demo.exception.RoomAvailabilityException;
import com.ui_demo.exception.RoomNotFoundException;
import com.ui_demo.mapper.ConferenceRoomMapper;
import com.ui_demo.mapper.ConferenceRoomReqResMapper;
import com.ui_demo.repository.ConferenceRoomRepo;
import com.ui_demo.service.ConferenceRoomService;
import com.ui_demo.specification.ConferenceRoomSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ui_demo.specification.ConferenceRoomSpecification.*;

@Service
@Transactional
public class ConferenceRoomServiceImpl implements ConferenceRoomService {

    private final ConferenceRoomRepo conferenceRoomRepo;
    private final ConferenceRoomReqResMapper conferenceRoomReqResMapper;
    private final ConferenceRoomMapper conferenceRoomMapper;

    public ConferenceRoomServiceImpl(ConferenceRoomRepo conferenceRoomRepo, ConferenceRoomReqResMapper conferenceRoomReqResMapper, ConferenceRoomMapper conferenceRoomMapper) {
        this.conferenceRoomRepo = conferenceRoomRepo;
        this.conferenceRoomReqResMapper = conferenceRoomReqResMapper;
        this.conferenceRoomMapper = conferenceRoomMapper;
    }
    @Override
    public ConferenceRoomResponseDto createRoom(ConferenceRoomRequestDto request) {
        if (request.getCapacity() == null || request.getCapacity() <= 0) {
            throw new IllegalArgumentException("Room capacity must be greater than 0");
        }


        if (conferenceRoomRepo.existsByRoomCode(request.getRoomCode())) {
            throw new RoomAvailabilityException("Room code already exists");
        }
        ConferenceRoom room = conferenceRoomReqResMapper.toEntity(request);
//        room.setStatus(RoomStatus.AVAILABLE);
        ConferenceRoom savedRoom = conferenceRoomRepo.save(room);

        return conferenceRoomReqResMapper.toDto(savedRoom);
    }

    @Override 
    @Transactional(readOnly = true)
    public List<ConferenceRoomResponseDto> getRooms() {

        return conferenceRoomRepo.findAll()
                .stream()
                .map(conferenceRoomMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ConferenceRoomResponseDto getRoomById(Long id) {

        ConferenceRoom room = conferenceRoomRepo.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("Room not found: " + id));
        return conferenceRoomReqResMapper.toDto(room);
    }

    @Override
    public ConferenceRoomResponseDto updateRoom(Long id, ConferenceRoomRequestDto request) {
        ConferenceRoom room = conferenceRoomRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found: " + id));
//        room.setRoomCode(request.getRoomCode());
//        room.setRoomName(request.getRoomName());
//        room.setLocationFloor(request.getLocationFloor());
//        room.setCapacity(request.getCapacity());
//        room.setRoomType(request.getRoomType());
//        room.setFacilities(request.getFacilities());
//        room.setStatus(request.getStatus());
        if(conferenceRoomRepo.existsByRoomCode(request.getRoomCode())){
            throw new RoomAvailabilityException("Room code already exists");

        }

//        if(request.getRoomCode()==room.getRoomCode()) {
//            throw new RoomAvailabilityException("Room code already exists");
//        }
conferenceRoomReqResMapper.updateConferenceRoom(request, room);
        ConferenceRoom updatedRoom = conferenceRoomRepo.save(room);
        return conferenceRoomReqResMapper.toDto(updatedRoom);
    }
    @Override
    public String deactivateRoom(Long id) {

        ConferenceRoom room = conferenceRoomRepo.findById(id)
                .orElseThrow(() ->
                        new RoomNotFoundException("Room not found with id: " + id)
                );
        if(room.getStatus() == RoomStatus.AVAILABLE) {
            room.setStatus(RoomStatus.INACTIVE);
            conferenceRoomRepo.save(room);
        }


        return "room with give id deactivated "+room.getId()+"successfully";
    }

    @Override
    public List<ConferenceRoomResponseDto> getAvailableRooms(String date, String startTime, String endTime, Integer capacity, String location, String facilities, Integer page, Integer size) {

        Pageable pageable =
                PageRequest
                        .of(page, size, Sort.by("capacity").ascending());

       Specification<ConferenceRoom> specification= hasStatus(RoomStatus.AVAILABLE);
       if(capacity!=null) {
           specification.and(capacityGreaterThanEqual(capacity));
       }

       if(location!=null && !location.isBlank()) {
       specification= specification.and(hasLocationFloor(location));
       }
       if(facilities!=null && !facilities.isBlank()) {
           String[] facilityList = facilities.split(",");
        for(String facility : facilityList) {
            if(!facility.isBlank()) {
                specification=specification.and(
                        ConferenceRoomSpecification.hasFacility(facility.trim()));
            }
        }
       }
        List<ConferenceRoomResponseDto> list = conferenceRoomRepo.findByStatusAndCapacityGreaterThanEqual(RoomStatus.AVAILABLE, capacity, pageable)
                .stream()
                .map(conferenceRoomReqResMapper::toDto)
                .toList();

        list.forEach(s->s.setCapacity(s.getCapacity() != null ? s.getCapacity() : 0));

        return list;
        
       
    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<ConferenceRoomResponseDto> getAvailableRooms(
//            String date, String startTime, String endTime,
//            Integer capacity, String location, String facilities, int page, int size) {
//
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("capacity").ascending());
//
//        return conferenceRoomRepo.findByStatus(RoomStatus.AVAILABLE)
//                .stream()
//                .map(conferenceRoomReqResMapper::toDto)
//                .toList();
//    }


//    private ConferenceRoomResponseDto toResponseDto(ConferenceRoom room) {
//        ConferenceRoomResponseDto dto=new ConferenceRoomResponseDto();
//
//        dto.setId(room.getId());
//        dto.setRoomCode(room.getRoomCode());
//        dto.setRoomName(room.getRoomName());
//        dto.setLocationFloor(room.getLocationFloor());
//        dto.setCapacity(room.getCapacity());
//        dto.setRoomType(room.getRoomType());
//        dto.setFacilities(room.getFacilities());
//        dto.setStatus(room.getStatus());
//
//        return dto;
//    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<ConferenceRoomResponseDto> getAvailableRooms(Long roomId) {
//       List<ConferenceRoom> conferenceRooms = conferenceRoomRepo.findAll().stream()
//                .filter(room -> room.getStatus() == RoomStatus.AVAILABLE)
//                .toList();
//        return conferenceRooms.stream()
//                .map(conferenceRoomReqResMapper::toDto)
//                .toList();
//    }

    @Override
    public boolean existsByRoomCode(String roomCode) {
        return conferenceRoomRepo.existsByRoomCode(roomCode);
    }

//    @Override
//    public <FormDataContentDisposition> PdfUploadResponseDto uploadPdf(InputStream fileInputStream, FormDataContentDisposition fileDetail) {
//        return null;
//    }
//
//    @Override
//    public PdfUploadResponseDto uploadPdf(MultipartFormDataInput input) {
//        Map<String, List<InputPart>> formParts = input.getFormDataMap();
//        List<InputPart> fileParts = formParts.get("file");
//
//        if (fileParts == null || fileParts.isEmpty()) {
//            throw new IllegalArgumentException("No file provided under form field 'file'");
//        }
//
//        InputPart filePart = fileParts.get(0);
//        String fileName = extractFileName(filePart.getHeaders());
//
//        if (fileName == null || !fileName.toLowerCase().endsWith(".pdf")) {
//            throw new IllegalArgumentException("Only PDF files are allowed");
//        }
//
//        try {
//            byte[] bytes = filePart.getBody(InputStream.class, null).readAllBytes();
//
//            // Persist it however you need — disk, S3, DB blob, etc.
//            Path uploadDir = Paths.get("uploads");
//            Files.createDirectories(uploadDir);
//            Files.write(uploadDir.resolve(fileName), bytes);
//
//            PdfUploadResponseDto response = new PdfUploadResponseDto();
//            response.setFileName(fileName);
//            response.setFileSize(bytes.length);
//            response.setMessage("File uploaded successfully");
//            return response;
//
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to read uploaded file", e);
//        }
//    }
//
//    private String extractFileName(MultivaluedMap<String, String> headers) {
//        String disposition = headers.getFirst("Content-Disposition");
//        if (disposition == null) return null;
//        for (String part : disposition.split(";")) {
//            part = part.trim();
//            if (part.startsWith("filename")) {
//                return part.split("=")[1].trim().replaceAll("\"", "");
//            }
//        }
//        return null;
//    }


}