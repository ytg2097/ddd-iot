package io.ac.apigatewayplatform.common.config.exception;

import common.exception.BusinessException;
import common.exception.SystemException;
import common.rest.RestResult;
import common.rest.RestStatus;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newHashMap;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;


/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
public class GlobalExceptionHandler extends DefaultErrorWebExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    public GlobalExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {

        Throwable error = super.getError(request);

        return buildResponse(error);
    }

    private Map<String, Object> buildResponse(Throwable error) {

        error.printStackTrace();
        log.error(error.getLocalizedMessage());
        Map<String,Object> response = newHashMap();
        if (error instanceof BusinessException){

            BusinessException exception = (BusinessException) error;
            response.put("status",new RestStatus(exception.getCode(),exception.getMessage()));
            return response;
        }

        if (error instanceof SystemException){

            SystemException exception = (SystemException) error;
            response.put("status",new RestResult<>(new RestStatus(exception.getCode(),exception.getMessage())));
            return response;
        }

        response.put("status",new RestStatus(INTERNAL_SERVER_ERROR.value(),Optional.ofNullable(error.getLocalizedMessage()).orElse("Unknown Error")));
        return response;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        return HttpStatus.valueOf(OK.value());
    }
}

