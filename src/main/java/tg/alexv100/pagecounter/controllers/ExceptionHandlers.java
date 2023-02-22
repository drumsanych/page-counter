package tg.alexv100.pagecounter.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tg.alexv100.pagecounter.data.dto.ExceptionDTO;

@ControllerAdvice
public class ExceptionHandlers {
    //todo
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ExceptionDTO> sendExceptionIndo(RuntimeException exception) {
//        return new ResponseEntity<>(new ExceptionDTO(exception.getMessage()), HttpStatusCode.valueOf(210));
//    }
}
