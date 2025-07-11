package com.github.diogocerqueiralima.authorizationserver.controllers;

import com.github.diogocerqueiralima.authorizationserver.exceptions.AgreementException;
import com.github.diogocerqueiralima.authorizationserver.exceptions.PasswordMatchException;
import com.github.diogocerqueiralima.authorizationserver.exceptions.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(
            HttpServletRequest request, MethodArgumentNotValidException ex, RedirectAttributes redirectAttributes
    ) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = fieldError == null ? "An error occurred" : fieldError.getDefaultMessage();
        String path = request.getRequestURI();

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:" + path;
    }

    @ExceptionHandler({ AgreementException.class, PasswordMatchException.class, UserAlreadyExistsException.class })
    public String handleFormError(HttpServletRequest request, Exception e, RedirectAttributes redirectAttributes) {

        String path = request.getRequestURI();

        redirectAttributes.addFlashAttribute("message", e.getMessage());
        return "redirect:" + path;
    }

}
