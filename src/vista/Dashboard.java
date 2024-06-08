/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Conexion;
import controlador.LoginController;
import controlador.UsuarioController;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Dashboard extends javax.swing.JFrame {

    private static Dashboard instance;
    private CardLayout cardLayout;
    private String cargoUsuario;
    private Connection conexion;
    private String nombreUsuarioActual;
    private int idUsuarioLogueado;
    private LoginController loginController;
    private UsuarioController usuarioController;
    private Login loginFrame;

   private Dashboard(int idUsuarioLogueado, String nombreUsuarioActual, Login loginFrame) {
        initComponents();
        this.idUsuarioLogueado = idUsuarioLogueado;
        this.nombreUsuarioActual = nombreUsuarioActual;
        this.loginFrame = loginFrame;

        // Inicializa la conexión
        Conexion con = new Conexion();
        conexion = con.conectar();

        // Inicializa el controlador de usuario
        this.usuarioController = new UsuarioController(conexion);

        // Obtén el cargo del usuario
        this.cargoUsuario = usuarioController.obtenerCargoPorNombreUsuario(nombreUsuarioActual);
        System.out.println("Cargo del usuario obtenido: " + cargoUsuario);

        initCustomComponents();
        configurarAccesos();
    }

   public static Dashboard getInstance(int idUsuarioLogueado, String nombreUsuarioActual, Login loginFrame) {
    if (instance == null) {
        instance = new Dashboard(idUsuarioLogueado, nombreUsuarioActual, loginFrame);
    }
    return instance;
    }
   public void actualizarAccesos() {
    configurarAccesos();
}

    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    private void initCustomComponents() {
        // Inicializa el CardLayout
        cardLayout = (CardLayout) DashboardPanel.getLayout();

        // Crea y agrega los paneles al CardLayout, pasando la conexión
        DashboardPanel.add(new DashboardMainPanel(conexion), "DashboardMain");
        DashboardPanel.add(new ProductosPanel(conexion, nombreUsuarioActual, cargoUsuario), "Productos");
        DashboardPanel.add(new CategoriasPanel(conexion), "Categorias");
        DashboardPanel.add(new ComprasPanel(conexion), "Compras");
        DashboardPanel.add(new ProveedoresPanel(conexion), "Proveedores");
        DashboardPanel.add(new UsuariosPanel(conexion), "Usuarios");
        DashboardPanel.add(new PuntodeVentaPanel(conexion, nombreUsuarioActual, cargoUsuario), "PuntoDeVenta");
        DashboardPanel.add(new RegistroVentasPanel(conexion), "RegistroVentas");

        cardLayout.show(DashboardPanel, "DashboardMain");

    }

    private void configurarAccesos() {
        System.out.println("Cargo del usuario: " + cargoUsuario);  // Verifica el valor de cargoUsuario

    if ("Empleado".equalsIgnoreCase(cargoUsuario)) {
        System.out.println("Configurando accesos para el cargo: " + cargoUsuario);

        // Ocultar los botones de acceso a los paneles de usuarios y proveedores
        lblUsuarios.setVisible(false);
        lblProveedores.setVisible(false);

        // Ocultar los paneles de usuarios y proveedores
        for (Component componente : DashboardPanel.getComponents()) {
            if (componente instanceof UsuariosPanel || componente instanceof ProveedoresPanel) {
                System.out.println("Ocultando panel de usuarios o proveedores");
                componente.setVisible(false);
            }
        }

        // Deshabilitar la edición en las tablas de los paneles de usuarios y proveedores
        for (Component componente : DashboardPanel.getComponents()) {
            if (componente instanceof JPanel) {
                JPanel panel = (JPanel) componente;
                for (Component subComponente : panel.getComponents()) {
                    if (subComponente instanceof JTable) {
                        JTable tabla = (JTable) subComponente;
                        tabla.setEnabled(false);
                    }
                }
            }
        }

        // Habilitar la edición en las tablas del panel de punto de venta
        for (Component componente : DashboardPanel.getComponents()) {
            if (componente instanceof JPanel) {
                JPanel panel = (JPanel) componente;
                if ("PuntoDeVenta".equals(panel.getName())) {
                    for (Component subComponente : panel.getComponents()) {
                        if (subComponente instanceof JTable) {
                            JTable tabla = (JTable) subComponente;
                            tabla.setEnabled(true);
                        }
                    }
                }
            }
        }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelMenu = new javax.swing.JPanel();
        lblProductos = new javax.swing.JLabel();
        lblProveedores = new javax.swing.JLabel();
        lblUsuarios = new javax.swing.JLabel();
        lblCategorias = new javax.swing.JLabel();
        lblPuntodeVenta = new javax.swing.JLabel();
        lblRegistroVentas = new javax.swing.JLabel();
        lblLogOut = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();
        DashboardPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 229, 65));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setText("Ferreteria");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 490, 70));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 70));

        PanelMenu.setBackground(new java.awt.Color(153, 153, 153));
        PanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProductos.setBackground(new java.awt.Color(204, 204, 204));
        lblProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inventario.png"))); // NOI18N
        lblProductos.setText("Productos");
        lblProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProductosMouseClicked(evt);
            }
        });
        PanelMenu.add(lblProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 160, 60));

        lblProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/proveedor.png"))); // NOI18N
        lblProveedores.setText("Proveedores");
        lblProveedores.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProveedoresMouseClicked(evt);
            }
        });
        PanelMenu.add(lblProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 160, 60));

        lblUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/grupo.png"))); // NOI18N
        lblUsuarios.setText("Usuarios");
        lblUsuarios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsuariosMouseClicked(evt);
            }
        });
        PanelMenu.add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 160, 60));

        lblCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/opciones.png"))); // NOI18N
        lblCategorias.setText("Categorias");
        lblCategorias.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCategoriasMouseClicked(evt);
            }
        });
        PanelMenu.add(lblCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 160, 60));

        lblPuntodeVenta.setBackground(new java.awt.Color(204, 204, 204));
        lblPuntodeVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/punto-de-venta.png"))); // NOI18N
        lblPuntodeVenta.setText("Punto de venta");
        lblPuntodeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblPuntodeVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPuntodeVentaMouseClicked(evt);
            }
        });
        PanelMenu.add(lblPuntodeVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 60));

        lblRegistroVentas.setBackground(new java.awt.Color(204, 204, 204));
        lblRegistroVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/caja-registradora.png"))); // NOI18N
        lblRegistroVentas.setText("Registro de ventas");
        lblRegistroVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblRegistroVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistroVentasMouseClicked(evt);
            }
        });
        PanelMenu.add(lblRegistroVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 160, 70));

        lblLogOut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLogOut.setForeground(new java.awt.Color(51, 51, 51));
        lblLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-sesion.png"))); // NOI18N
        lblLogOut.setText("     Salir");
        lblLogOut.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogOutMouseClicked(evt);
            }
        });
        PanelMenu.add(lblLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 160, 50));

        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/panel.png"))); // NOI18N
        lblDashboard.setText("Dashboard");
        lblDashboard.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDashboardMouseClicked(evt);
            }
        });
        PanelMenu.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 60));

        jPanel1.add(PanelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 160, 550));

        DashboardPanel.setBackground(new java.awt.Color(204, 204, 204));
        DashboardPanel.setLayout(new java.awt.CardLayout());
        jPanel1.add(DashboardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 940, 550));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lblLogOutMouseClicked

    private void lblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductosMouseClicked
        // TODO add your handling code here:
        cardLayout.show(DashboardPanel, "Productos");
    }//GEN-LAST:event_lblProductosMouseClicked

    private void lblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCategoriasMouseClicked
        // TODO add your handling code here:
        cardLayout.show(DashboardPanel, "Categorias");
    }//GEN-LAST:event_lblCategoriasMouseClicked

    private void lblProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProveedoresMouseClicked
        // TODO add your handling code here:
        cardLayout.show(DashboardPanel, "Proveedores");
    }//GEN-LAST:event_lblProveedoresMouseClicked

    private void lblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuariosMouseClicked
        // TODO add your handling code here:
            cardLayout.show(DashboardPanel, "Usuarios");
    }//GEN-LAST:event_lblUsuariosMouseClicked

    private void lblPuntodeVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPuntodeVentaMouseClicked
        // TODO add your handling code here:
       cardLayout.show(DashboardPanel, "PuntoDeVenta");
    }//GEN-LAST:event_lblPuntodeVentaMouseClicked

    private void lblRegistroVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistroVentasMouseClicked
        // TODO add your handling code here:
       cardLayout.show(DashboardPanel, "RegistroVentas");
    }//GEN-LAST:event_lblRegistroVentasMouseClicked

    private void lblDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseClicked
        // TODO add your handling code here:
        cardLayout.show(DashboardPanel, "DashboardMain");
    }//GEN-LAST:event_lblDashboardMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashboardPanel;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCategorias;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblLogOut;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblPuntodeVenta;
    private javax.swing.JLabel lblRegistroVentas;
    private javax.swing.JLabel lblUsuarios;
    // End of variables declaration//GEN-END:variables

    private void cerrarSesion() {
        // Cerrar el JFrame actual (Dashboard)
        // Cerrar el JFrame actual (Dashboard)
        this.dispose();

        // Reiniciar la sesión mostrando una nueva instancia del JFrame del login
        Login loginFrame = new Login();
        loginFrame.setVisible(true);

        // Actualizar la referencia al Login en el Dashboard
        Dashboard.getInstance(idUsuarioLogueado, nombreUsuarioActual, loginFrame);
    }
  

}
