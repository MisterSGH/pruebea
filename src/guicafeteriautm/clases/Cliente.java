/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guicafeteriautm.clases;

/**
 *
 * @author SAR
 */
public class Cliente extends Usuario {
    private int idCliente;
    private String nombre;
    private boolean beca;
    private int porcentaje;

    public Cliente(int idCliente, String usuario, String nombre, boolean beca, int porcentaje) {
        this.idCliente=idCliente;
        this.setUsuario(usuario);
        this.nombre = nombre;
        this.beca = beca;
        this.porcentaje = porcentaje;
    }

    public Cliente(String usuario,String nombre, boolean beca, int porcentaje) {
        this.setUsuario(usuario);
        this.nombre = nombre;
        this.beca = beca;
        this.porcentaje = porcentaje;
    }

    public Cliente(String usuario,String nombre, int porcentaje) {
        this.setUsuario("");
        this.nombre = "";
        this.porcentaje =0;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isBeca() {
        return beca;
    }

    public void setBeca(boolean beca) {
        this.beca = beca;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
