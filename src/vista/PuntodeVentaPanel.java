package vista;

import controlador.Ctrl_RegistrarVenta;
import controlador.EnvioCorreos;
import controlador.VentaController;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DetalleCompraDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleCompra;
import modelo.Producto;
import modelo.ProductoDAO;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import modelo.DetalleVenta;
import modelo.DetalleVentaDAO;
import modelo.Venta;
import modelo.VentaDAO;
import java.sql.Connection;
import modelo.UsuariosDAO;
import controlador.LoginController;
import controlador.VentaPDF;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import modelo.CabeceraVenta;
import modelo.DetalleVentaPdf;

public class PuntodeVentaPanel extends javax.swing.JPanel {
    private Connection conexion;
    private ProductoDAO productoDAO;
    private UsuariosDAO usuariosDAO;
    private VentaDAO ventaDAO;
    private VentaController ventaController;
    private List<Producto> productosSeleccionados;
    private DefaultTableModel modeloTabla;
    private String nombreUsuarioActual;
    private LoginController loginController;
    private String cargoUsuario;
    

    public PuntodeVentaPanel(Connection conexion, String nombreUsuarioActual, String cargoUsuario) {
        this.conexion = conexion;
        initComponents();
        if (this.conexion == null) {
            System.err.println("Error: La conexión es null en PuntodeVentaPanel");
        }
        this.productoDAO = new ProductoDAO(conexion);
        this.nombreUsuarioActual = nombreUsuarioActual;
        this.cargoUsuario = cargoUsuario;
        usuariosDAO = new UsuariosDAO(conexion);
        this.ventaDAO = new VentaDAO(conexion);
        this.ventaController = new VentaController(ventaDAO, conexion);
        inicializarModeloTabla();
        productosSeleccionados = new ArrayList<>();
        this.loginController = new LoginController(conexion);
    }

    private void inicializarModeloTabla() {
        modeloTabla = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Código", "Producto", "Cantidad", "Precio Unitario", "Subtotal"}
        );
        TablaPuntoVenta.setModel(modeloTabla);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        spCantidad = new javax.swing.JSpinner();
        txtCodigo = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblMetodoPago = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        cbMetodoPago = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPuntoVenta = new javax.swing.JTable();
        txtSubtotal = new javax.swing.JTextField();
        lblSubtotal = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblPago = new javax.swing.JLabel();
        txtPago = new javax.swing.JTextField();
        lblCambio = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        GenerarPdf = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstBusqueda = new javax.swing.JList<>();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(940, 550));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 183, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText("Punto de venta");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 184, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, 50));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 550, -1));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigo.setText("Codigo");
        jPanel2.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        lblProducto.setText("Producto");
        jPanel2.add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblCantidad.setText("Cantidad");
        jPanel2.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        lblPrecio.setText("Precio");
        jPanel2.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        jPanel2.add(spCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 90, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jPanel2.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 90, -1));
        jPanel2.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 160, -1));
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 90, -1));

        lblDescripcion.setText("Descripcion");
        jPanel2.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        lblStock.setText("Stock");
        jPanel2.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 40, 20));

        lblMetodoPago.setText("Metodo de pago");
        jPanel2.add(lblMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, 20));
        jPanel2.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 310, -1));
        jPanel2.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 50, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 120, 30));

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        jPanel2.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 120, -1));

        cbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));
        jPanel2.add(cbMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 100, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 550, 127));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaPuntoVenta.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaPuntoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPuntoVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaPuntoVenta);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 580, 310));
        jPanel3.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 33, 151, -1));

        lblSubtotal.setText("Subtotal");
        jPanel3.add(lblSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 14, -1, -1));

        lblIVA.setText("IVA");
        jPanel3.add(lblIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 58, -1, -1));
        jPanel3.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 77, 151, 23));

        lblTotal.setText("Total");
        jPanel3.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 110, -1, -1));

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel3.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 128, 151, 27));

        btnGenerarVenta.setText("Generar venta");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 120, 40));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 265, 90, 30));

        lblPago.setText("Pago con");
        jPanel3.add(lblPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, -1, -1));

        txtPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPagoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagoKeyReleased(evt);
            }
        });
        jPanel3.add(txtPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 58, -1));

        lblCambio.setText("Cambio");
        jPanel3.add(lblCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 170, -1, -1));

        txtCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCambioActionPerformed(evt);
            }
        });
        jPanel3.add(txtCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 58, -1));

        GenerarPdf.setText("Generar ticket");
        GenerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarPdfActionPerformed(evt);
            }
        });
        jPanel3.add(GenerarPdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, 120, 30));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 207, 920, 333));

        lstBusqueda.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstBusquedaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstBusqueda);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 370, 190));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        agregarProducto();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        // TODO add your handling code here:
  
        generarVenta();
        
        // Otras acciones después de calcular el cambio, como mostrar un mensaje de confirmación
        
      
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        modeloTabla.setRowCount(0);
        calcularTotales();
        cancelarVenta();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
    quitarProducto();
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
       

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCambioActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String consulta = txtBuscar.getText();
        List<Producto> productos = null;
        try {
            productos = productoDAO.buscarProductos(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(PuntodeVentaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Producto producto : productos) {
            // Formatear la cadena para cada producto con los componentes separados por " - "
            String item = producto.getCodigo() + " - "
                    + producto.getNombre() + " - "
                    + producto.getPrecioCompra() + " - "
                    + producto.getDescripcion() + " - "
                    + producto.getStock();
            model.addElement(item);
        }
        lstBusqueda.setModel(model);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void TablaPuntoVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPuntoVentaMouseClicked
        // TODO add your handling code here:
        seleccionarProductoDeTabla();
    }//GEN-LAST:event_TablaPuntoVentaMouseClicked

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
      
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void lstBusquedaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstBusquedaValueChanged
        // TODO add your handling code here:
        // Obtener el valor seleccionado de la lista
        String selectedValue = lstBusqueda.getSelectedValue();
        if (selectedValue != null) {
            // Separar los componentes del valor seleccionado por " - "
            String[] parts = selectedValue.split(" - ");
            // Verificar que haya suficientes partes en la cadena
            if (parts.length >= 5) {
                String codigo = parts[0];
                String producto = parts[1];
                String precioCompra = parts[2];
                String descripcion = parts[3];
                String stock = parts[4];

                // Establecer los valores en los campos correspondientes
                txtCodigo.setText(codigo);
                txtProducto.setText(producto);
                txtPrecio.setText(precioCompra);
                txtDescripcion.setText(descripcion);
                txtStock.setText(stock);

                // Opcionalmente, si se requiere una cantidad predeterminada
                spCantidad.setValue(1); // Esto establece el JSpinner en 1
            } else {
                System.err.println("Formato de datos incorrecto en la lista de búsqueda: " + selectedValue);
            }
        }
    }//GEN-LAST:event_lstBusquedaValueChanged

    private void txtPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPagoKeyReleased

    private void txtPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoKeyPressed
       
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        double total = Double.parseDouble(txtTotal.getText());
        double pago = Double.parseDouble(txtPago.getText());
        double cambio = pago - total;

        // Formatear el cambio para que solo muestre dos decimales
        DecimalFormat df = new DecimalFormat("#.00");
        txtCambio.setText(df.format(cambio));
    }
    }//GEN-LAST:event_txtPagoKeyPressed

    private void GenerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarPdfActionPerformed
        // TODO add your handling code here:
        CabeceraVenta cabeceraVenta = new CabeceraVenta();
        DetalleVentaPdf detalleVentaPdf = new DetalleVentaPdf();
        Ctrl_RegistrarVenta controlVenta = new Ctrl_RegistrarVenta();

        String fechaActual = "";
        Date date = new Date();
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);

        if (!cbMetodoPago.getSelectedItem().equals("Seleccione método de pago:")) {
            if (TablaPuntoVenta.getRowCount() > 0) {

                // Registrar cabecera
                cabeceraVenta.setValorPagar(Double.parseDouble(txtTotal.getText()));
                cabeceraVenta.setFechaVenta(fechaActual);
                cabeceraVenta.setEstado(1);
                cabeceraVenta.setFormaPago(cbMetodoPago.getSelectedItem().toString());

                // Calcular el cambio
                double total = Double.parseDouble(txtTotal.getText());
                double pagoCon = Double.parseDouble(txtPago.getText());
                double cambio = pagoCon - total;

                // Almacenar el cambio en txtCambio
                txtCambio.setText(String.valueOf(cambio));

                if (controlVenta.guardar(cabeceraVenta)) {
                    JOptionPane.showMessageDialog(null, "¡Venta Registrada!");

                    // Generar la factura de venta
                    VentaPDF pdf = new VentaPDF(TablaPuntoVenta, txtTotal, txtPago);
                    String rutaArchivoPDF = pdf.generarFacturaPDF();

                    // Mostrar un JOptionPane para solicitar la dirección de correo electrónico
                    String emailTo = JOptionPane.showInputDialog(this, "Por favor, ingresa la dirección de correo electrónico a la que deseas enviar el PDF:");
                    if (emailTo != null && !emailTo.trim().isEmpty()) {
                        // Crear una instancia de la clase EnvioCorreos
                        EnvioCorreos envioCorreos = new EnvioCorreos();

                        // Crear y enviar el correo
                        envioCorreos.createEmail(emailTo, rutaArchivoPDF);

                    } else {
                        JOptionPane.showMessageDialog(this, "No ingresaste ninguna dirección de correo electrónico. El correo electrónico no será enviado.");
                    }

                    // Guardar detalle
                    for (int i = 0; i < TablaPuntoVenta.getRowCount(); i++) {
                        detalleVentaPdf.setIdDetalleVenta(0);
                        detalleVentaPdf.setIdCabeceraVenta(cabeceraVenta.getIdCabeceraventa());
                        detalleVentaPdf.setIdProducto(Integer.parseInt(TablaPuntoVenta.getValueAt(i, 0).toString())); // Suponiendo que la primera columna es el ID del producto
                        detalleVentaPdf.setCantidad(Integer.parseInt(TablaPuntoVenta.getValueAt(i, 2).toString())); // Suponiendo que la tercera columna es la cantidad
                        detalleVentaPdf.setPrecioUnitario(Double.parseDouble(TablaPuntoVenta.getValueAt(i, 3).toString())); // Suponiendo que la cuarta columna es el precio unitario
                        detalleVentaPdf.setSubTotal(Double.parseDouble(TablaPuntoVenta.getValueAt(i, 4).toString())); // Suponiendo que la quinta columna es el subtotal
                        detalleVentaPdf.setDescuento(0.0); // Asumiendo que no hay descuento en esta interfaz
                        detalleVentaPdf.setIva(Double.parseDouble(TablaPuntoVenta.getValueAt(i, 5).toString())); // Suponiendo que la sexta columna es el IVA
                        detalleVentaPdf.setTotalPagar(Double.parseDouble(TablaPuntoVenta.getValueAt(i, 6).toString())); // Suponiendo que la séptima columna es el total a pagar
                        detalleVentaPdf.setEstado(1);

                        if (controlVenta.guardarDetalle(detalleVentaPdf)) {
                            // Limpiar campos de texto
                            txtSubtotal.setText("0.0");
                            txtIVA.setText("0.0");
                            txtTotal.setText("0.0");
                            txtPago.setText("");
                            txtCambio.setText("0.0");

                            // RestarStockProductos(detalleVentaPdf.getIdProducto(), detalleVentaPdf.getCantidad());
                        } else {
                            JOptionPane.showMessageDialog(null, "¡Error al guardar detalle de venta!");
                        }
                    }
                    // Vaciar la lista
                    ((DefaultTableModel) TablaPuntoVenta.getModel()).setRowCount(0);

                } else {
                    JOptionPane.showMessageDialog(null, "¡Error al guardar cabecera de venta!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Seleccione un producto!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Seleccione un método de pago!");
        }
        limpiarFormulario();

    }//GEN-LAST:event_GenerarPdfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GenerarPdf;
    private javax.swing.JTable TablaPuntoVenta;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox<String> cbMetodoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCambio;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblPago;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JList<String> lstBusqueda;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtPago;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void agregarProducto() {
        String codigo = txtCodigo.getText();
        String nombre = txtProducto.getText();
        int cantidad = (int) spCantidad.getValue(); // Obtener la cantidad desde el JSpinner
        double precioCompra = Double.parseDouble(txtPrecio.getText()); // Obtener el precio de compra desde el campo de texto

        // Verificar si los campos no están vacíos y cantidad es mayor que 0
        if (codigo.isEmpty() || nombre.isEmpty() || cantidad <= 0 || precioCompra <= 0) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos y asegúrese de que la cantidad y el precio sean válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar si el producto ya está en la tabla
        boolean productoExistente = false;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (modeloTabla.getValueAt(i, 0).equals(codigo)) {
                // Producto encontrado, actualizar cantidad y total
                int cantidadActual = (int) modeloTabla.getValueAt(i, 2);
                int nuevaCantidad = cantidadActual + cantidad;
                modeloTabla.setValueAt(nuevaCantidad, i, 2);
                modeloTabla.setValueAt(precioCompra * nuevaCantidad, i, 4);
                productoExistente = true;
                break;
            }
        }

        if (!productoExistente) {
            // Crear fila para agregar a la tabla
            Object[] row = new Object[]{
                codigo,
                nombre,
                cantidad,
                precioCompra,
                precioCompra * cantidad
            };

            // Agregar fila al modelo de la tabla
            modeloTabla.addRow(row);

            // Crear el producto con el precio de compra y agregarlo a la lista de productos seleccionados
            Producto producto = new Producto(codigo, nombre, precioCompra);
            productosSeleccionados.add(producto);
        }

        // Calcular totales después de agregar o actualizar el producto
        calcularTotales();
     
    }

    private void quitarProducto() {
        int filaSeleccionada = TablaPuntoVenta.getSelectedRow();
        if (filaSeleccionada >= 0) {
            productosSeleccionados.remove(filaSeleccionada);
            modeloTabla.removeRow(filaSeleccionada);
            calcularTotales();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para quitar.");
        }
    }

    private void generarVenta() {
        try {
            // Parsear y obtener valores del formulario
            double subtotal = Double.parseDouble(txtSubtotal.getText());
            double iva = Double.parseDouble(txtIVA.getText());
            double total = Double.parseDouble(txtTotal.getText());
            double pago = Double.parseDouble(txtPago.getText());
            double cambio = Double.parseDouble(txtCambio.getText());
            String metodoPago = cbMetodoPago.getSelectedItem().toString();

            // Obtener ID del usuario
            int idUsuario = usuariosDAO.obtenerIdUsuarioActual("admin");
            if (idUsuario == -1) {
                JOptionPane.showMessageDialog(this, "No se pudo obtener el ID del usuario.");
                return;
            }

            // Crear una lista para los detalles de la venta
            List<DetalleVenta> detallesVenta = new ArrayList<>();
            int rowCount = TablaPuntoVenta.getRowCount();

            // Crear un mapa para almacenar la cantidad total vendida por cada producto
            Map<String, Integer> cantidadTotalVendidaPorProducto = new HashMap<>();

            // Iterar sobre cada fila de la tabla para procesar los detalles de venta
            for (int i = 0; i < rowCount; i++) {
                String codigoProducto = TablaPuntoVenta.getValueAt(i, 0).toString();
                int cantidadVendida = Integer.parseInt(TablaPuntoVenta.getValueAt(i, 2).toString());
                Producto producto = productoDAO.obtenerProductoPorCodigo(codigoProducto);

                if (producto != null) {
                    int stockDisponible = producto.getStock();

                    if (cantidadVendida > 0) {
                        // Verificar si la cantidad vendida es mayor que el stock disponible
                        if (cantidadVendida <= stockDisponible) {
                            // Crear un nuevo detalle de venta y agregarlo a la lista
                            DetalleVenta detalle = new DetalleVenta(producto.getCodigo(), cantidadVendida, producto.getPrecioVenta(), cantidadVendida * producto.getPrecioVenta());
                            detallesVenta.add(detalle);

                            System.out.println("Procesando detalle: " + producto.getCodigo() + ", Cantidad vendida: " + cantidadVendida);

                            // Actualizar la cantidad total vendida por producto
                            cantidadTotalVendidaPorProducto.put(codigoProducto, cantidadTotalVendidaPorProducto.getOrDefault(codigoProducto, 0) + cantidadVendida);
                        } else {
                            JOptionPane.showMessageDialog(this, "La cantidad vendida del producto " + producto.getCodigo() + " es mayor que el stock disponible.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "La cantidad del producto " + producto.getCodigo() + " no puede ser cero.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El producto con código " + codigoProducto + " no existe.");
                }
            }

            // Crear una nueva venta
            Venta venta = new Venta(subtotal, iva, total, metodoPago, pago, cambio, new Date(), idUsuario);
            boolean ventaGuardada = ventaController.agregarVenta(venta, detallesVenta);

            if (ventaGuardada) {
                // Actualizar el stock de los productos vendidos
                for (DetalleVenta detalle : detallesVenta) {
                    String codigoProducto = detalle.getCodigoProducto();
                    int cantidadVendida = detalle.getCantidad();

                    // Se actualiza el stock en el método agregarVenta de VentaController
                }
                JOptionPane.showMessageDialog(this, "Venta generada correctamente.");
                // limpiarFormulario(); // Asumiendo que hay un método para limpiar el formulario después de una venta exitosa
            } else {
                JOptionPane.showMessageDialog(this, "Error al generar la venta.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PuntodeVentaPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al generar la venta: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los números: " + ex.getMessage());
        }
    }

    private void calcularTotales() {
        double subtotal = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            subtotal += (double) modeloTabla.getValueAt(i, 4);
        }
        double iva = subtotal * 0.16;
        double total = subtotal + iva;

        txtSubtotal.setText(String.format("%.2f", subtotal));
        txtIVA.setText(String.format("%.2f", iva));
        txtTotal.setText(String.format("%.2f", total));
    }


    

    private void calcularCambio() {
        try {
            double pago = Double.parseDouble(txtPago.getText());
            double total = Double.parseDouble(txtTotal.getText());
            double cambio = pago - total;
            txtCambio.setText(String.format("%.2f", cambio));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTablaProductos(List<Producto> productos) {
        DefaultTableModel model = (DefaultTableModel) TablaPuntoVenta.getModel();
        model.setRowCount(0); // Limpiar tabla existente

        for (Producto producto : productos) {
            Object[] row = new Object[]{
                producto.getIdProducto(),
                producto.getCodigo(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getFkCategoria(),
                producto.getProveedor(),
                producto.getPrecioCompra(),
                producto.getPrecioVenta(),
                producto.getStock()
            };
            model.addRow(row);
        }
    }

   

   
     private void agregarProductoATablaVentas(Producto producto) {
        boolean productoExiste = false;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (modeloTabla.getValueAt(i, 0).equals(producto.getCodigo())) {
                int cantidadActual = (int) modeloTabla.getValueAt(i, 2);
                modeloTabla.setValueAt(cantidadActual + 1, i, 2);
                double precioUnitario = (double) modeloTabla.getValueAt(i, 3);
                modeloTabla.setValueAt(precioUnitario * (cantidadActual + 1), i, 4);
                productoExiste = true;
                break;
            }
        }
        if (!productoExiste) {
            Object[] row = new Object[]{
                producto.getCodigo(),
                producto.getNombre(), 
                1, // Cantidad inicial
                producto.getPrecioVenta(),
                producto.getPrecioVenta() // Subtotal inicial (1 * Precio)
            };
            modeloTabla.addRow(row);
        }
    }
      
        private void seleccionarProductoDeTabla() {
        int filaSeleccionada = TablaPuntoVenta.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String codigo = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
            String producto = modeloTabla.getValueAt(filaSeleccionada, 1).toString();
            String cantidad = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
            String precio = modeloTabla.getValueAt(filaSeleccionada, 3).toString();
            txtCodigo.setText(codigo);
            txtProducto.setText(producto);
            spCantidad.setValue(Integer.parseInt(cantidad));
            txtPrecio.setText(precio);
        }
    }
       private void cancelarVenta() {
        // Limpiar la lista de productos seleccionados, la tabla y los campos
        productosSeleccionados.clear();
        limpiarFormulario();
    }
       private void limpiarFormulario() {
        txtCodigo.setText("");
        txtProducto.setText("");
        spCantidad.setValue(1);
        txtPrecio.setText("");
        txtDescripcion.setText("");
        txtStock.setText("");
        modeloTabla.setRowCount(0);
        txtSubtotal.setText("");
        txtIVA.setText("");
        txtTotal.setText("");
        cbMetodoPago.setSelectedIndex(0);
        txtPago.setText("");
        txtCambio.setText("");
    }
        private boolean validarFormulario() {
        if (txtTotal.getText().isEmpty() || txtPago.getText().isEmpty() || txtCambio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (Double.parseDouble(txtPago.getText()) < Double.parseDouble(txtTotal.getText())) {
            JOptionPane.showMessageDialog(this, "El pago debe ser mayor o igual al total.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private int getIdUsuario() {
        int idUsuario = -1;

        try {
            // Usa UsuariosDAO para obtener el ID del usuario basado en el nombre de usuario
            idUsuario = usuariosDAO.obtenerIdUsuarioActual(nombreUsuarioActual);
        } catch (SQLException ex) {
            System.out.println("Error al obtener el ID del usuario actual: " + ex.getMessage());
            ex.printStackTrace();
        }

        return idUsuario;
    }

}
