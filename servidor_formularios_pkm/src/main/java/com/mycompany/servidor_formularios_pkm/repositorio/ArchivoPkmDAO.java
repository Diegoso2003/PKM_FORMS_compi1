/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor_formularios_pkm.repositorio;

import com.mycompany.servidor_formularios_pkm.PoolConnections;
import com.mycompany.servidor_formularios_pkm.excepciones.MensajeError;
import com.mycompany.servidor_formularios_pkm.excepciones.PkmException;
import com.mycompany.servidor_formularios_pkm.modelos.ArchivoPkm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ArchivoPkmDAO {
    
    public void insertarElemento(ArchivoPkm archivo){
        String query = "INSERT INTO ArchivoPkm (nombre, autor, pkm)"
                + "VALUES(?, ?, ?)";
        try(PreparedStatement st = PoolConnections.getInstance().getConnection().prepareStatement(query)){
            st.setString(1, archivo.getNombre());
            st.setString(2, archivo.getAutor());
            st.setString(3, archivo.getPkm());
            st.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            var mensaje = new MensajeError("ingresar correctamente los datos solicitados");
            throw new PkmException(mensaje);
        }
    }
    
    public List<ArchivoPkm> obtenerTodos(){
        var lista = new LinkedList<ArchivoPkm>();
        String query = "SELECT nombre, autor, fecha, idPkm FROM ArchivoPkm";
        try(Statement stmt = PoolConnections.getInstance().getConnection().createStatement();
                ResultSet result = stmt.executeQuery(query)){
            while(result.next()){
                var archivo = new ArchivoPkm();
                archivo.setAutor(result.getString("autor"));
                archivo.setId(result.getInt("idPkm"));
                archivo.setNombre(result.getString("nombre"));
                LocalDateTime fecha = result.getTimestamp("fecha").toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                archivo.setFecha(fecha.format(formatter));
                lista.add(archivo);
            }
        }catch(SQLException e){
            var mensaje = new MensajeError("hubo un error al conseguir los formularios, intente más tarde");
            throw new PkmException(mensaje);
        }
        return lista;
    }
    
    public Optional<ArchivoPkm> obtenerPkm(int id){
        String query = "SELECT pkm FROM ArchivoPkm WHERE idPkm = ?";
        try(PreparedStatement stmt = PoolConnections.getInstance().getConnection().prepareStatement(query)){
            stmt.setInt(1, id);
            try(ResultSet result = stmt.executeQuery()){
                if (result.next()) {
                    var archivo = new ArchivoPkm();
                    archivo.setPkm(result.getString("pkm"));
                    return Optional.of(archivo);
                }
            }
        }catch(SQLException e){
            var mensaje = new MensajeError("hubo un error al conseguir el formulario, intente más tarde");
            throw new PkmException(mensaje);
        }
        return Optional.empty();
    }
}
