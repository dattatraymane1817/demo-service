package com.ui_demo.config;




import com.ui_demo.exception.*;

import com.ui_demo.service.serviceImpl.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.context.annotation.Configuration;


@Configuration
public class jerseyConfig extends ResourceConfig {
    public jerseyConfig() {
        register(MultipartFormDataInput.class);
        packages("com.ui_demo.service.serviceImpl");
        register(ConferenceRoomServiceImpl.class);
        register(ConferenceRoomBookingServiceImpl.class);
        register(EmployeeLoginServiceImpl.class);
        register(AdminDashboardServiceImpl.class);
        register(NotFoundExceptionMapper.class);
        register(BadRequestExceptionMapper.class);
        register(RoomAvailabilityExceptionMapper.class);
        register(BookingExceptionMapper.class);
        register(GenericExceptionMapper.class);
    }
}