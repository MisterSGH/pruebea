
package guicafeteriautm.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Fecha: 04/ago/2025
 * Clase que contiene los métodos para realizar las operaciones CRUD en la tabla cliente.
 * @author Santi
 */
public class ClienteBD {

    public void insertarCliente(Connection conn, Usuario usuario, int idUsuario) throws SQLException {
        String sql = "INSERT INTO cliente(usuario, nombre,beca,porcentaje,idusuario) VALUES (?, ?, ?, ?, ?)"; //se arma la sentencia de la consulta con parametros
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {   //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getUsuario());// Se establecen los parametros 
            stmt.setBoolean(3, false);
            stmt.setInt(4, 0);
            stmt.setInt(5, idUsuario);
            stmt.executeUpdate();
        }
    }

public boolean actualizarCliente(Connection conn, int idCliente, String nombre, String usuario, boolean becado, int porcentaje) throws SQLException {
    String sql = "UPDATE cliente SET nombre = ?, usuario = ?, beca = ?, porcentaje = ? WHERE idcliente = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nombre);
        stmt.setString(2, usuario);
        stmt.setBoolean(3, becado);
        stmt.setInt(4, porcentaje);
        stmt.setInt(5, idCliente);
        return stmt.executeUpdate() > 0;
    }
}

    
    public boolean eliminarCliente(Connection conn, int idCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE idcliente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate() > 0;
        }
    }
}



