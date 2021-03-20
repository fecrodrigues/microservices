package br.com.fiap.contact.exception;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.fiap.contact.dto.ErrorResp;

@Provider
public class HandlerNotFound implements ExceptionMapper<NotFoundException>{

	@Override
	public Response toResponse(NotFoundException nfe) {

		return Response.status(NOT_FOUND)
			.entity(new ErrorResp("not_found", NOT_FOUND.getStatusCode(), nfe.getMessage()))
			.build();
	}
}
