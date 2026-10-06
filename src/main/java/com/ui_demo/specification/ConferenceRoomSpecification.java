package com.ui_demo.specification;

import com.ui_demo.entity.ConferenceRoom;
import com.ui_demo.enums.RoomStatus;
import org.springframework.data.jpa.domain.Specification;

public class ConferenceRoomSpecification {
    public static Specification<ConferenceRoom> withStatus(RoomStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<ConferenceRoom> capacityGreaterThanEqual(Integer capacity){
        return (root,query,cb)-> cb.greaterThanOrEqualTo(root.get("capacity"),capacity);
    }

    public static Specification<ConferenceRoom> hasLocation(String location){
        return (root,query,cb)-> cb.equal(root.get("location"), location);
    }

    public static Specification<ConferenceRoom> hasRoomCode(String roomCode){
        return (root,query,cb)-> cb.equal(root.get("roomCode"), roomCode);
    }

    public static Specification<ConferenceRoom> hasStatus(RoomStatus roomStatus) {
        return (root,query,cb)-> cb.equal(root.get("status"), roomStatus);
    }

    public static Specification<ConferenceRoom> hasLocationFloor(String location) {
        return (root,query,cb)-> cb.equal(root.get("locationFloor"), location);
    }
    public static Specification<ConferenceRoom> hasFacility(String facility) {
        return (root,query,cb)->{
            query.distinct(true);
            Class<?> resultType = query.getResultType();
            return cb.isMember(facility,root.get("facility"));
        };
    }
}
