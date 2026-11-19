package ru.yaharuott.servhack.adapter.in.web;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yaharuott.servhack.domain.exceptions.*;

@RestControllerAdvice
public class GlobalExceptionController {
    public record ErrorResponse(String message) {}

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            CannotFindGroupByIdException.class,
            CannotFindGroupByIdException.class,
            ParticipantsNotFoundException.class,
            GroupsEmptyException.class,
            CannotFindAnyExcursions.class
    }
    )
    public ErrorResponse handleNotFound(RuntimeException exception){
        return new ErrorResponse(
                exception.getMessage()
        );
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ParticipantsAlreadyCheckedInException.class)
    public ErrorResponse handleAlreadyCheckedIn(ParticipantsAlreadyCheckedInException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GroupVisitDateNotEqualsException.class)
    public ErrorResponse handleWrongDate(GroupVisitDateNotEqualsException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse("Некорректные данные");
        return new ErrorResponse(msg);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidStaffCodeException.class)
    public ErrorResponse handleWrongStaffCode(InvalidStaffCodeException ex){
        return new ErrorResponse(ex.getMessage());
    }
}
