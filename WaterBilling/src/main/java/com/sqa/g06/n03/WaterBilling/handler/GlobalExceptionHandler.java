package com.sqa.g06.n03.WaterBilling.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler
    public ResponseEntity<ResponseError> handleError(AppError e){
        return ResponseEntity.status(e.getStatusCode()).body(
          new ResponseError("Failed!", e.getStatusCode(), e.getMessage())
        );
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<ResponseError> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = Objects.requireNonNull(ex.getRootCause()).getMessage();
        // Tìm kiếm thông tin lỗi vi phạm ràng buộc unique
        if (errorMessage.contains("Duplicate entry")) {
            return ResponseEntity.status(400).body(new ResponseError("Failed!", 400, "Existed!"));
        }
        // Các thông tin lỗi khác
        else {
            return ResponseEntity.status(400).body(new ResponseError("Failed!", 400, "Bad request!"));
        }
    }

    @ExceptionHandler
    public ResponseEntity<ResponseError> globalError(Exception e){
        return ResponseEntity.status(400).body(
                new ResponseError("Failed!", 400, "Bad request!")
        );
    }
}
