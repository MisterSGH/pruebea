package guicafeteriautm;

import guicafeteriautm.clases.ConexionBD; // Clase que gestiona la conexión a la base de datos
import guicafeteriautm.clases.Usuario; // Modelo de datos Usuario, que representa un registro de la tabla 'usuario
import guicafeteriautm.clases.UsuarioBD;// Importa la clase DAO (Data Access Object) para operaciones CRUD sobre objetos Usuario
import java.sql.Connection; // Manejo de conexión a la base de datos
import java.sql.PreparedStatement; // Preparación segura de sentencias SQL con parámetros
import java.sql.ResultSet; // Recolección de resultados de consultas SQL
import java.sql.SQLException; // Manejo de excepciones relacionadas con SQL
import java.util.ArrayList;// Librería para manejar listas dinámicas de objetos
import java.util.logging.Level; // Niveles de log (INFO, WARNING, SEVERE, etc.)
import java.util.logging.Logger; // Registro de mensajes y excepciones
import javax.swing.JOptionPane; // Mostrar mensajes, confirmaciones, advertencias, etc.
import javax.swing.table.DefaultTableModel; // Modelo de datos para mostrar en una tabla (JTable)



/**11/JUL/2025
 *
 * @author santi
 */
public class frmGestionUsuarios extends javax.swing.JInternalFrame {

ConexionBD objetoConexionBD = new ConexionBD(); // creacion del objeto de base de datos
Connection conn; // objeto de conexion
PreparedStatement stmt = null; // variable para la sentencia sql
ResultSet rs = null; //variable para resultado de consulta sql
    
    public frmGestionUsuarios()throws ClassNotFoundException {
        initComponents();
        llenarDataTable();
        btnAgregar.setEnabled(false);
    }

    public final void llenarDataTable() throws ClassNotFoundException {
    ArrayList<Usuario> arregloUsuarios = new ArrayList<>();
    String sql = "SELECT idUsuario, usuario, password, email, rol FROM usuario ";
    String dato = "";

    try {
        conn = objetoConexionBD.conexionDataBase();

        // Filtrado según el radio button seleccionado
        if (rbdEmpleados.isSelected()) {
            sql += "WHERE rol='Empleado'";
        } else if (rbdClientes.isSelected()) {
            sql += "WHERE rol='Cliente'";
        } else if (rbdAdministrador.isSelected()) {
            sql += "WHERE rol='Administrador'";
        } else if (rbdUsuario.isSelected()) {
            sql += "WHERE usuario=?";
            dato = txtDatoBuscar.getText().trim();
        }

        // Preparar y asignar parámetros si es necesario
        stmt = conn.prepareStatement(sql);
        if (rbdUsuario.isSelected()) {
            stmt.setString(1, dato);
        }

        rs = stmt.executeQuery();

        // Verificar si hay resultados
        boolean tieneDatos = false;
        while (rs.next()) {
            tieneDatos = true;
            Usuario usuario = new Usuario(
                rs.getInt("idUsuario"),
                rs.getString("usuario"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("rol")
            );
            arregloUsuarios.add(usuario);
        }

        if (!tieneDatos) {
            JOptionPane.showMessageDialog(null, "¡Datos no localizados!");
        }

        // Actualizar tabla
        DefaultTableModel modelo = (DefaultTableModel) tblUsuarios.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        for (Usuario item : arregloUsuarios) {
            Object[] fila = {
                item.getIdUsuario(),
                item.getUsuario(),
                item.getPassword(),
                item.getEmail(),
                item.getRol()
            };
            modelo.addRow(fila);
        }

        tblUsuarios.setModel(modelo);

        // Seleccionar la primera fila si hay datos
        if (tblUsuarios.getRowCount() > 0) {
            tblUsuarios.setRowSelectionInterval(0, 0);
            llenarTextBox();
        }

    } catch (SQLException ex) {
        Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Cerrar recursos de forma segura
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    public void llenarTextBox(){
    int fila=tblUsuarios.getSelectedRow();
    
    if(fila>=0){
        txtIdUsuario.setText(tblUsuarios.getValueAt(fila,0).toString());
        txtUsuario.setText(tblUsuarios.getValueAt(fila,1).toString());
        txtPassword.setText(tblUsuarios.getValueAt(fila,2).toString());
        txtEMAIL.setText(tblUsuarios.getValueAt(fila,3).toString());
        cmbRol.setSelectedItem(tblUsuarios.getValueAt(fila,4).toString());
    }
    
    }
            
 /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgfiltrar = new javax.swing.ButtonGroup();
        frmBuscar = new javax.swing.JDialog();
        plnDatoBuscar = new javax.swing.JPanel();
        rbdUsuario = new javax.swing.JRadioButton();
        rbdEMAIL = new javax.swing.JRadioButton();
        lblDatoaBuscar = new javax.swing.JLabel();
        txtDatoBuscar = new javax.swing.JTextField();
        btnBuscarDato = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btgBuscar = new javax.swing.ButtonGroup();
        pnlRelacionEmpleados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        pnlConsultar = new javax.swing.JPanel();
        rbdEmpleados = new javax.swing.JRadioButton();
        rbdClientes = new javax.swing.JRadioButton();
        rbdAdministrador = new javax.swing.JRadioButton();
        rbdTodos = new javax.swing.JRadioButton();
        btnVer = new javax.swing.JButton();
        pnlCampos = new javax.swing.JPanel();
        lbIIdUsusario = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblEMAIL = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtEMAIL = new javax.swing.JTextField();
        cmbRol = new javax.swing.JComboBox<>();
        pnlAcciones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        plnDatoBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecciona el dato a buscar:"));

        btgBuscar.add(rbdUsuario);
        rbdUsuario.setText("Usuario");

        btgBuscar.add(rbdEMAIL);
        rbdEMAIL.setText("EMAIL");

        lblDatoaBuscar.setText("Dato buscar:");

        txtDatoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatoBuscarActionPerformed(evt);
            }
        });

        btnBuscarDato.setText("Buscar");
        btnBuscarDato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDatoActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plnDatoBuscarLayout = new javax.swing.GroupLayout(plnDatoBuscar);
        plnDatoBuscar.setLayout(plnDatoBuscarLayout);
        plnDatoBuscarLayout.setHorizontalGroup(
            plnDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnDatoBuscarLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(plnDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbdEMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(plnDatoBuscarLayout.createSequentialGroup()
                        .addComponent(lblDatoaBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDatoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnDatoBuscarLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(btnBuscarDato)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar)
                .addGap(60, 60, 60))
        );
        plnDatoBuscarLayout.setVerticalGroup(
            plnDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnDatoBuscarLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rbdUsuario)
                .addGap(18, 18, 18)
                .addComponent(rbdEMAIL)
                .addGap(18, 18, 18)
                .addGroup(plnDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatoaBuscar)
                    .addComponent(txtDatoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(plnDatoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarDato)
                    .addComponent(btnCerrar))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frmBuscarLayout = new javax.swing.GroupLayout(frmBuscar.getContentPane());
        frmBuscar.getContentPane().setLayout(frmBuscarLayout);
        frmBuscarLayout.setHorizontalGroup(
            frmBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmBuscarLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(plnDatoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        frmBuscarLayout.setVerticalGroup(
            frmBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmBuscarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(plnDatoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ventana de Gestion De Usuarios");

        pnlRelacionEmpleados.setBorder(javax.swing.BorderFactory.createTitledBorder("Relacion de empleados"));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "idUsuario", "Usuario", "Password", "EMAIL", "Rol"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);
        tblUsuarios.getAccessibleContext().setAccessibleName("Relacion De empleados");
        tblUsuarios.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout pnlRelacionEmpleadosLayout = new javax.swing.GroupLayout(pnlRelacionEmpleados);
        pnlRelacionEmpleados.setLayout(pnlRelacionEmpleadosLayout);
        pnlRelacionEmpleadosLayout.setHorizontalGroup(
            pnlRelacionEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelacionEmpleadosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addGap(156, 156, 156))
        );
        pnlRelacionEmpleadosLayout.setVerticalGroup(
            pnlRelacionEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );

        pnlConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar por:"));

        btgfiltrar.add(rbdEmpleados);
        rbdEmpleados.setText("Empleados");

        btgfiltrar.add(rbdClientes);
        rbdClientes.setText("Clientes");
        rbdClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdClientesActionPerformed(evt);
            }
        });

        btgfiltrar.add(rbdAdministrador);
        rbdAdministrador.setText("Administrador");
        rbdAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdAdministradorActionPerformed(evt);
            }
        });

        btgfiltrar.add(rbdTodos);
        rbdTodos.setText("Todos");

        btnVer.setText("VER");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlConsultarLayout = new javax.swing.GroupLayout(pnlConsultar);
        pnlConsultar.setLayout(pnlConsultarLayout);
        pnlConsultarLayout.setHorizontalGroup(
            pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConsultarLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnVer))
                    .addComponent(rbdClientes)
                    .addComponent(rbdEmpleados)
                    .addComponent(rbdAdministrador)
                    .addComponent(rbdTodos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlConsultarLayout.setVerticalGroup(
            pnlConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(rbdEmpleados)
                .addGap(18, 18, 18)
                .addComponent(rbdClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbdAdministrador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbdTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVer)
                .addGap(12, 12, 12))
        );

        pnlCampos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro seleccionados:"));

        lbIIdUsusario.setText("Id Uusario:");

        lblUsuario.setText("Usuario:");

        lblPassword.setText("Password");

        lblEMAIL.setText("EMAIL:");

        lblRol.setText("Rol:");

        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Cliente", "Administrador" }));
        cmbRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRolActionPerformed(evt);
            }
        });

        pnlAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones en Usuario"));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesLayout = new javax.swing.GroupLayout(pnlAcciones);
        pnlAcciones.setLayout(pnlAccionesLayout);
        pnlAccionesLayout.setHorizontalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAccionesLayout.createSequentialGroup()
                        .addGroup(pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(219, 219, 219))
                    .addGroup(pnlAccionesLayout.createSequentialGroup()
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlAccionesLayout.setVerticalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(lbIIdUsusario)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdUsuario))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario)
                            .addComponent(lblPassword)
                            .addComponent(lblEMAIL)
                            .addComponent(lblRol))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPassword)
                            .addComponent(txtUsuario)
                            .addComponent(txtEMAIL)
                            .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbIIdUsusario)
                            .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPassword)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEMAIL)
                            .addComponent(txtEMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRol)
                            .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRelacionEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlRelacionEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbdAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdAdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbdAdministradorActionPerformed
    private void rbdClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbdClientesActionPerformed
    private void cmbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRolActionPerformed
    private void txtDatoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatoBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoBuscarActionPerformed
    private void btnBuscarDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDatoActionPerformed
        try{
            llenarDataTable();
            btgfiltrar.clearSelection();
            this.txtDatoBuscar.setText("");
            this.frmBuscar.dispose();
            }catch(ClassNotFoundException ex){
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE,null,ex);
}
    }//GEN-LAST:event_btnBuscarDatoActionPerformed
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        //Borrando los criteros de busqueda 
        try{
        this.rbdUsuario.setSelected(false);
        btgfiltrar.clearSelection();
        this.txtDatoBuscar.setText("");
        llenarDataTable();
        this.frmBuscar.dispose();

        }catch(ClassNotFoundException ex){
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_btnCerrarActionPerformed
    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        //Se llenan las cajas de texto con la informacion de los registros del jtable
        llenarTextBox();
    }//GEN-LAST:event_tblUsuariosMouseClicked
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //Se llama al metodo de registrar usuario 
        registrarUsuario();
        //Se desactiva el boton de agregar 
        btnAgregar.setEnabled(false);
        //Se activa el boton de nuevo
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try{
            int idusuario=Integer.parseInt(txtIdUsuario.getText().trim());
            String usuariox = txtUsuario.getText().trim();
            String password = txtPassword.getText().trim();
            String email = txtEMAIL.getText().trim();
            String rol = cmbRol.getSelectedItem().toString();
        
            Usuario usuario = new Usuario(usuariox,password,email,rol);
            UsuarioBD dao = new UsuarioBD();
        
            try(Connection conn = objetoConexionBD.conexionDataBase()){
              boolean actualizado = dao.actualizarUsuario(conn, usuario, idusuario);
            
              if(actualizado){
                JOptionPane.showMessageDialog(null,"¡USUARIO ACTTUALIZADO CORRECTAMENTE!");
                llenarDataTable();     
             }else{        
               JOptionPane.showMessageDialog(null,"¡NO SE ENCONTRO EL USUARIO CON ESE ID!");
             }
            
            }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"ERROR AL ACTUALIZAR USUARIO:"+ex.getMessage());
            }
            
        }catch(NumberFormatException ex){
           JOptionPane.showMessageDialog(null,"ID de usuario invalido.");
        }catch(ClassNotFoundException ex){    
           JOptionPane.showMessageDialog(null, "Error al cargar la Base de Datos:"+ex.getMessage());
        }       
    }//GEN-LAST:event_btnModificarActionPerformed
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    try {
        int idUsuario = Integer.parseInt(txtIdUsuario.getText().trim());
        UsuarioBD dao = new UsuarioBD();
        try(Connection conn = objetoConexionBD.conexionDataBase()){
        int confirmacion = JOptionPane.showConfirmDialog(
        null,
        "¿Está seguro de eliminar este usuario?",
        "Confirmar elimiriación",
        JOptionPane. YES_NO_OPTION
      );

      if (confirmacion == JOptionPane. YES_OPTION) {

        boolean eliminado = dao.eliminarUsuario(conn,idUsuario);
        if (eliminado) {
        JOptionPane.showMessageDialog (null, "¡Usuario eliminado correctamente!");
        llenarDataTable();
      } else {
         JOptionPane.showMessageDialog(null, "No se encontró un usuario con el ID: " + idUsuario);
         }
        }
    } catch(SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar usuario: "+ex.getMessage());
        }
    } catch (NumberFormatException ex){
        JOptionPane.showMessageDialog(null, "ID inválido. Verifique que sea un número.");
    } catch (ClassNotFoundException ex){
        JOptionPane.showMessageDialog(null, "Error al cargar el controlador de base de datos: " + ex.getMessage());
    }   

    }//GEN-LAST:event_btnEliminarActionPerformed
    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        try {
            this.llenarDataTable(); 
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVerActionPerformed
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try{
            llenarDataTable();
            btgfiltrar.clearSelection();
            this.txtDatoBuscar.setText("");
            this.frmBuscar.dispose();   
            }catch(ClassNotFoundException ex){ 
              Logger.getLogger(frmGestionUsuarios.class.getName()).log(Level.SEVERE,null,ex);
            }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       // Se desabilita el boton de nuevo
    btnNuevo.setEnabled(false);
    //Se limpian los campos para que los llenen
    txtIdUsuario.setText("");
    txtUsuario.setText("");
    txtPassword.setText("");
    txtEMAIL.setText("");
    cmbRol.setSelectedItem("Empleado");
    //Se activa el boton para Agregar el nuevo registro
    btnAgregar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    public void registrarUsuario() {
    String usuariox = txtUsuario.getText().trim();
    String password = txtPassword.getText().trim();
    String email = txtEMAIL.getText().trim();
    String rol = cmbRol.getSelectedItem().toString();
    
    Usuario objUsuario = new Usuario(usuariox, password, email, rol);
    UsuarioBD objBD = new UsuarioBD();
    
    try (Connection conn = objetoConexionBD.conexionDataBase()) {
        int idGenerado = objBD.insertarUsuario(conn, objUsuario);
        
        if (idGenerado > 0) {
            JOptionPane.showMessageDialog(null, "¡Usuario registrado correctamente! ID generado: " + idGenerado);
            
            if (rol.equalsIgnoreCase("Cliente")) {
                objBD.insertarCliente(conn, objUsuario, idGenerado);
                JOptionPane.showMessageDialog(null, "¡Cliente insertado correctamente!");
            } else if (rol.equalsIgnoreCase("Empleado")) {
                objBD.insertarEmpleado(conn, objUsuario, idGenerado);
                JOptionPane.showMessageDialog(null, "¡Empleado insertado correctamente!");
            }
            
            llenarDataTable();
            
            if (tblUsuarios.getRowCount() > 0) {
                int ultimaFila = tblUsuarios.getRowCount() - 1;
                tblUsuarios.setRowSelectionInterval(ultimaFila, ultimaFila);
                llenarTextBox();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario.");
        }
        
    } catch (SQLException | ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + ex.getMessage());
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgBuscar;
    private javax.swing.ButtonGroup btgfiltrar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarDato;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JDialog frmBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIIdUsusario;
    private javax.swing.JLabel lblDatoaBuscar;
    private javax.swing.JLabel lblEMAIL;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel plnDatoBuscar;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlConsultar;
    private javax.swing.JPanel pnlRelacionEmpleados;
    private javax.swing.JRadioButton rbdAdministrador;
    private javax.swing.JRadioButton rbdClientes;
    private javax.swing.JRadioButton rbdEMAIL;
    private javax.swing.JRadioButton rbdEmpleados;
    private javax.swing.JRadioButton rbdTodos;
    private javax.swing.JRadioButton rbdUsuario;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtDatoBuscar;
    private javax.swing.JTextField txtEMAIL;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
