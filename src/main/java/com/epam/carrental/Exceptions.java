package com.epam.carrental;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Exceptions {
    private static final Logger log = LogManager.getLogger(Exceptions.class);

    public static void ProcessInternalError(HttpServletResponse response, Exception exception){

        log.error(Logging.makeDescription(exception));

        try {
            response.sendError(500);
        }catch (IOException exceptionSendError){
            log.error(Logging.makeDescription(exceptionSendError));
        }
    }

}
