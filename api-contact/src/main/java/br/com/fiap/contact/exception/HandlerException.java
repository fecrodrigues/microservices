package br.com.fiap.contact.exception;

import static javax.ws.rs.core.Response.Status.*;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.fiap.contact.dto.ErrorResp;

@Provider
public class HandlerException implements ExceptionMapper<Throwable>{

//	@ExceptionHandler(value = {NotFoundException.class})
//	private Response handlerNotFound(NotFoundException nfe) {
//		
//		return Response.status(NOT_FOUND)
//				.entity(new ErrorResp("not_found", nfe.getMessage()))
//				.build();
//	}

	@Override
	public Response toResponse(Throwable exception) {

		if(exception instanceof NotFoundException) {
			return Response.status(NOT_FOUND)
				.entity(new ErrorResp("not_found", exception.getMessage()))
				.build();
		}
		return null;
	}
}
