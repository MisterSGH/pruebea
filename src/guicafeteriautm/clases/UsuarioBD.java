package guicafeteriautm.clases;
//Clase que contiene las operaciones crud para el manejo de los usuarios del Sistema
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Fecha 02/ julio /25 Clase que contiene los metodos para realizar las
 * operaciones CRUD en la tabla de usuario
 *  @author Gricelda Rodriguez R
 */
public class UsuarioBD {

    public int insertarUsuario(Connection conn, Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (usuario, password, email, rol) VALUES (?, ?, ?, ?)";  //se arma la sentencia de la consulta con parametros
        //  Statement.RETURN_GENERATED_KEYS --> constante que  regresa el id generado despues de insertar
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getUsuario());    // Se establecen los parametros 
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getRol());
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {// se obteniene el idGenerado
                    if (rs.next()) {
                        return rs.getInt(1);   
                    }
                }
            }
        }
        return -1;
    }

    public void insertarCliente(Connection conn, Usuario usuario, int idUsuario) throws SQLException {
        String sql = "INSERT INTO cliente (nombre, beca, porcentaje, idusuario) VALUES (?, ?, ?, ?)"; //se arma la sentencia de la consulta con parametros
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {   //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setString(1, usuario.getUsuario());   // Se establecen los parametros 
            stmt.setBoolean(2, false);
            stmt.setInt(3, 0);
            stmt.setInt(4, idUsuario);
            stmt.executeUpdate();
        }
    }

    public void insertarEmpleado(Connection conn, Usuario usuario, int idUsuario) throws SQLException {
        String sql = "INSERT INTO empleado (nombre, turno, idusuario) VALUES (?, ?, ?)";  //se arma la sentencia de la consulta con parametros
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {   //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setString(1, usuario.getUsuario()); // Se establecen los parametros 
            stmt.setString(2, "");
            stmt.setInt(3, idUsuario);
            stmt.executeUpdate();
        }
    }

    public boolean eliminarUsuario(Connection conn, int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?"; //se arma la sentencia de la consulta
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {  //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setInt(1, idUsuario); 
            return stmt.executeUpdate() > 0;  //se ejecuta la consulta y se recibe en el result set --> rs
        }
    }

    public boolean actualizarUsuario(Connection conn, Usuario usuario, int idUsuario) throws SQLException {
        String sql = "UPDATE usuario SET usuario = ?, password = ?, email = ?, rol = ? WHERE idUsuario = ?"; //se arma la sentencia de la consulta
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {  //se prepara la sentencia SQL mediante la variable statement--> stmt
            stmt.setString(1, usuario.getUsuario()); // Se establecen los parametros 
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getRol());
            stmt.setInt(5, idUsuario);
            return stmt.executeUpdate() > 0;  //se ejecuta la consulta y se recibe en el result set --> rs
        }
    }

    public ResultSet consultarUsuarioLogin(Connection conn, String usuario, String password) throws SQLException {
        ResultSet rs;
        PreparedStatement stmt;
        String sql = "SELECT *  from usuario WHERE usuario = ? AND password = ?";   //se arma la sentencia de la consulta
        stmt = conn.prepareStatement(sql);//se prepara la sentencia SQL mediante la variable statement--> stmt
        stmt.setString(1, usuario);       //se establecen los campos de consulta
        stmt.setString(2, password);
        rs = stmt.executeQuery(); //se ejecuta la consulta y se recibe en el result set --> rs

        return rs;
    }

}