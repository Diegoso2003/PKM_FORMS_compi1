/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor_formularios_pkm.controllers;

import com.mycompany.servidor_formularios_pkm.modelos.ArchivoPkm;
import com.mycompany.servidor_formularios_pkm.servicios.ArchivoServicio;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author rafael-cayax
 */
@Path("form")
public class ArchivoPkmController {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarFormulario(ArchivoPkm archivo){
        ArchivoServicio service = new ArchivoServicio();
        service.agregarNuevoArchivo(archivo);
        return Response.accepted()
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodo(){
        ArchivoServicio service = new ArchivoServicio();
        return Response.ok(service.obtenerTodosLosFormularios())
                .build();
    }
    
    @Path("{idPkm:\\d+}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerForm(@PathParam("idPkm") int idPkm){
        ArchivoServicio service = new ArchivoServicio();
        return Response.ok(service.obtenerPorId(idPkm))
                .build();
    }
    
}
