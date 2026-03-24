/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.servidor_formularios_pkm.excepciones;

/**
 *
 * @author rafael-cayax
 */
public class PkmException extends RuntimeException {
    private MensajeError mensaje;

    /**
     * Creates a new instance of <code>PkmException</code> without detail
     * message.
     */
    public PkmException() {
    }

    /**
     * Constructs an instance of <code>PkmException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PkmException(String msg) {
        super(msg);
    }
    
    public PkmException(MensajeError mensaje){
        this.mensaje = mensaje;
    }

    public MensajeError getMensaje() {
        return mensaje;
    }
    
}
