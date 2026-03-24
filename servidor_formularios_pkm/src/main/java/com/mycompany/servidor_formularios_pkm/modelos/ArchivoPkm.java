/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor_formularios_pkm.modelos;

/**
 *
 * @author rafael-cayax
 */
public class ArchivoPkm {
    private int id;
    private String autor;
    private String fecha;
    private String nombre;
    private String pkm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPkm() {
        return pkm;
    }

    public void setPkm(String pkm) {
        this.pkm = pkm;
    }
    
    public boolean esArchivoValido(){
        return pkm != null && !pkm.isBlank() && autor != null && !autor.isBlank()
                && nombre != null && !nombre.isBlank();
    }
    
    
}
