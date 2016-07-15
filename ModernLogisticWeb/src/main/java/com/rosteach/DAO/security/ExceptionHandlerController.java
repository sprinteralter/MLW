package com.rosteach.DAO.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

	public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(Exception.class )//
    
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception { //HttpServletResponse resp,
      
    	
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
        		throw e;
        	}
        	
        StringBuilder sb = new  StringBuilder();
        Throwable cause = e.getCause();
        while(cause.getCause() != null) {
        	sb.append(cause.getCause()+"\n\n");
            cause = cause.getCause();
        }
    
            
    	//resp.sendRedirect("error");
      
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", e);
        mav.addObject("url", sb.toString());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        
        return mav;
    }
}