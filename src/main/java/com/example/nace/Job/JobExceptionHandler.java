package com.example.nace.Job;

import com.example.nace.Job.exceptions.NaceAlreadyExistException;
import com.example.nace.Job.exceptions.NaceDetailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JobExceptionHandler {

    @ExceptionHandler(value= NaceDetailNotFoundException.class)
    public ResponseEntity<Object> exception(){
        return new ResponseEntity<>("NACE Details not Found for this id! ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= NaceAlreadyExistException.class)
    public ResponseEntity<Object> jobExists(){
        return new ResponseEntity<>("This job already exists or please check the order id!", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value= IllegalArgumentException.class)
    public ResponseEntity<Object> argumentException(){
        return new ResponseEntity<>("Please check the job details!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
