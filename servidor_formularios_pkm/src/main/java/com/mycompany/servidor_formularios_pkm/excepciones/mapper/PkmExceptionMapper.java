/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor_formularios_pkm.excepciones.mapper;

import com.mycompany.servidor_formularios_pkm.excepciones.PkmException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author rafael-cayax
 */
@Provider
public class PkmExceptionMapper implements ExceptionMapper<PkmException>{
        @Override
    public Response toResponse(PkmException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMensaje())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
