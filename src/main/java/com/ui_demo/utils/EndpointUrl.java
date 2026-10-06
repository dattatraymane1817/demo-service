package com.ui_demo.utils;

public final class EndpointUrl {

    public static final String GET_USER_BY_ID = "/user/get/{id}";

    public static final String USER_REGISTER_ENDPOINT = "/user/register";

    public static final String USER_LOGIN_ENDPOINT = "/user/login";

    private EndpointUrl() {

    }
    public static final String ROOMS = "/api/rooms";

    public static final String ROOM_BY_ID = ROOMS + "/%d";
    public static final String AVAILABLE_ROOMS = ROOMS + "/available";
    public static final String ROOM_BOOKING_HISTORY = ROOMS + "/%d/booking-history";
    public static final String BOOKINGS = "/api/bookings";
    public static final String BOOKING_BY_ID = BOOKINGS + "/%d";
    public static final String CANCEL_BOOKING = BOOKINGS + "/%d/cancel";
}