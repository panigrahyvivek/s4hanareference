package com.sap.cloud.sdk.demo.controllers;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;

@RestController
public class HelloWorldController
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello")
    protected void sayHello( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException
    {
        logger.info("I am running!");
        response.getWriter().write("Hello World!!!!!");
    }    
}
