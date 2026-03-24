/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor_formularios_pkm.servicios;

import com.mycompany.servidor_formularios_pkm.excepciones.MensajeError;
import com.mycompany.servidor_formularios_pkm.excepciones.NotFoundException;
import com.mycompany.servidor_formularios_pkm.excepciones.PkmException;
import com.mycompany.servidor_formularios_pkm.modelos.ArchivoPkm;
import com.mycompany.servidor_formularios_pkm.repositorio.ArchivoPkmDAO;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ArchivoServicio {
    private final ArchivoPkmDAO repo = new ArchivoPkmDAO();
    
    public void agregarNuevoArchivo(ArchivoPkm archivo){
        if(!archivo.esArchivoValido()){
            var mensaje = new MensajeError("ingrese correctamente los datos solicitados.");
            throw new PkmException(mensaje);
        }
        repo.insertarElemento(archivo);
    }
    
    public List<ArchivoPkm> obtenerTodosLosFormularios(){
        return repo.obtenerTodos();
    }
    
    public ArchivoPkm obtenerPorId(int id){
        return repo.obtenerPkm(id).orElseThrow(() -> new NotFoundException(new MensajeError("formulario no encontrado")));
    }
}
