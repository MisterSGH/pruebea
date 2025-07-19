package guicafeteriautm.clases;

/**
 *Clase usuario, permite e l manejo de los usuarios en el login y en el formulario CRUD de usuarios
 * @author 00045
 */
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String password;
    private String email;
    private String rol;
    
    public Usuario(String usuario, String password, String email, String rol){
          this.idUsuario = 0;
        this.usuario=usuario;
        this.password =password;
        this.email = email;
        this.rol = rol;
    }

     public Usuario(){
           this.idUsuario = 0;
        this.usuario="";
        this.password ="";
        this.email = "";
        this.rol = "";
     }
    
    // Constructor
    public Usuario(int idUsuario, String usuario, String password, String email, String rol) {
        this.idUsuario = idUsuario;
        this.usuario=usuario;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    //Setters  
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

}