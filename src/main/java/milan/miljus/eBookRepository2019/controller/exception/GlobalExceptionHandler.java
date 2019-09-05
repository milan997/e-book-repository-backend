package milan.miljus.eBookRepository2019.controller.exception;

import lombok.extern.slf4j.Slf4j;
import milan.miljus.eBookRepository2019.service.files.exception.FileNotFoundException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO: milan.miljus 2019-07-04 00:30 check what we should pass to response constructors: locale? getLocalizedMessage?

//    @ExceptionHandler()
//    public ResponseEntity handleNotFound(CustomException ex, Locale locale){
//        return buildResponseEntity(new ExceptionResponse(ex.getResourceBundleKey(), locale, NOT_FOUND));
//    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity handleFileNotFoundException(CustomException ex, Locale locale){
        return buildResponseEntity(new ExceptionResponse(ex.getResourceBundleKey(), locale, NOT_FOUND));
    }

    public ResponseEntity handleConflict(CustomException ex, Locale locale){
        return buildResponseEntity(new ExceptionResponse(ex.getResourceBundleKey(), locale, ex.getMessage(), CONFLICT));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException ex, Locale locale) {
        ex.printStackTrace();
        return buildResponseEntity(new ExceptionResponse(ex.getResourceBundleKey(), locale, ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex, Locale locale) {
        ex.printStackTrace();
        return buildResponseEntity(new ExceptionResponse("unexpected.exception", locale, ex.getLocalizedMessage(), INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDenied(Exception ex, Locale locale) {
        return buildResponseEntity(new ExceptionResponse("access.denied", locale, ex.getLocalizedMessage(), FORBIDDEN));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("http.method.not.supported", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("http.media.type.not.supported", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("http.media.type.not.acceptable", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("missing.path.variable", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("missing.servlet.request.parameter", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("servlet.request.binding.exception", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("conversion.not.supported", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("type.mismatch.exception", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("http.message.not.readable", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("http.message.not.writable", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ExceptionResponse response = new ExceptionResponse("invalid.arguments", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST);
        response.setErrors(ex.getBindingResult().getAllErrors()
                .stream()
                .map(e -> ((FieldError)e).getField() + " " + e.getDefaultMessage())
                .collect(Collectors.toList()));
        return buildResponseEntity(response);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ExceptionResponse response = new ExceptionResponse("missing.servlet.request.part", request.getLocale(), ex.getLocalizedMessage(), BAD_REQUEST);
        response.setErrors(Collections.singletonList(ex.getRequestPartName() + "is missing."));
        return buildResponseEntity(response);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("bind.exception", request.getLocale(), ex.getLocalizedMessage(), INTERNAL_SERVER_ERROR));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("no.handler.found", request.getLocale(), ex.getLocalizedMessage(), INTERNAL_SERVER_ERROR));
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return buildResponseEntity(new ExceptionResponse("async.request.timeout", webRequest.getLocale(), ex.getLocalizedMessage(), INTERNAL_SERVER_ERROR));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse("internal.server.error", request.getLocale(), ex.getLocalizedMessage(), INTERNAL_SERVER_ERROR));
    }

    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse exceptionResponse) {
        log.warn("exception --- status: {}, errorKey: {}, errorMessage: {}",
                exceptionResponse.getStatus(), exceptionResponse.getErrorKey(), exceptionResponse.getErrorMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(exceptionResponse.getStatus()));
    }
}
