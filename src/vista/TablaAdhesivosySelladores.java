/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import controlador.CategoriaController;
import controlador.ProductoController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.CategoriaDAO;
import modelo.Categoria;
import modelo.Producto;
import modelo.ProductoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author alary
 */
public class TablaAdhesivosySelladores extends javax.swing.JDialog {

    private Connection conexion;
    private ProductoDAO productoDAO;
    private CategoriaDAO categoriaDAO;
    private Categoria categoria;
    private ProductoController productoController;
    private CategoriaController categoriaController;
    private int idCategoria = 9;

    /**
     * Creates new form TablaAdhesivosySelladores
     */
    public TablaAdhesivosySelladores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initializeDatabaseConnection();
        productoController = new ProductoController(conexion);
        this.productoDAO = new ProductoDAO(conexion);
        categoriaController = new CategoriaController(conexion); // Inicializar el controlador de categorías
        inicializarTablaProductos(idCategoria);
    }

    private void initializeDatabaseConnection() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ferreteria", "root", "RobinPanzon1");
            productoController = new ProductoController(conexion);
            categoriaController = new CategoriaController(conexion); // Inicializar el controlador de categorías
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void inicializarTablaProductos(int idCategoria) {
        String sql = "SELECT * FROM producto WHERE fk_categoria = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCategoria);
            try (ResultSet resultSet = statement.executeQuery()) {
                DefaultTableModel model = (DefaultTableModel) tblAdhesivosSelladores.getModel();
                model.setColumnIdentifiers(new String[]{"ID", "Código", "Nombre", "Descripción", "ID Categoría", "Proveedor", "Precio Compra", "Precio Venta", "Stock"});
                model.setRowCount(0);

                while (resultSet.next()) {
                    model.addRow(new Object[]{
                        resultSet.getInt("idProducto"),
                        resultSet.getString("codigo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("fk_categoria"),
                        resultSet.getString("proveedor"),
                        resultSet.getDouble("precioCompra"),
                        resultSet.getDouble("precioVenta"),
                        resultSet.getInt("stock")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la tabla de productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdhesivosSelladores = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnSalir1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAdhesivosSelladores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAdhesivosSelladores);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 780, 400));

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 80, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 500));

        jPanel3.setBackground(new java.awt.Color(51, 102, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Adhesivos y Selladores");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 452, 80));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 810, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed

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
            java.util.logging.Logger.getLogger(TablaAdhesivosySelladores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaAdhesivosySelladores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaAdhesivosySelladores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaAdhesivosySelladores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TablaAdhesivosySelladores dialog = new TablaAdhesivosySelladores(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAdhesivosSelladores;
    // End of variables declaration//GEN-END:variables
}
