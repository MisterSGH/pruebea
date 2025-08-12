
package guicafeteriautm.clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author santi
 */
public class EmpleadoBD {
    
    public boolean actualizarEmpleado(Connection conn, int idEmpleado, String nombre, String usuario, String turno) throws SQLException {
    String sql = "UPDATE empleado SET nombre = ?, usuario = ?, turno = ? WHERE idempleado = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nombre);
        stmt.setString(2, usuario);
        stmt.setString(3, turno);
        stmt.setInt(4, idEmpleado);
        return stmt.executeUpdate() > 0;
    }
}

}
