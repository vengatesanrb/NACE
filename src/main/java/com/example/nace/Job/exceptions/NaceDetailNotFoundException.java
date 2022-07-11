package com.example.nace.Job.exceptions;

public class NaceDetailNotFoundException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private static Long id;
   public NaceDetailNotFoundException(){}
   public NaceDetailNotFoundException(Long id){
      this.id = id;
   }

}
