
package guicafeteriautm.clases;

/**
 *
 * @author Santi
 */
public class Empleado extends Usuario {
    private int idEmpleado;
    private String nombre;
    private String turno;

    public Empleado(int idEmpleado, String usuario, String nombre, String turno) {
        this.idEmpleado = idEmpleado;
        this.setUsuario(usuario);
        this.nombre = nombre;
        this.turno = turno;
    }

    public Empleado(String usuario, String nombre, String turno) {
        this.setUsuario(usuario);
        this.nombre = nombre;
        this.turno = turno;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    
}
