/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor_formularios_pkm.excepciones;

/**
 *
 * @author rafael-cayax
 */
public class NotFoundException extends RuntimeException{
    private final MensajeError mensaje;

    public NotFoundException(MensajeError mensaje) {
        this.mensaje = mensaje;
    }

    public MensajeError getMensaje() {
        return mensaje;
    }
    
}
