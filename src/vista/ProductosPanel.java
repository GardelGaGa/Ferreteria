
package vista;

import controlador.CategoriaController;
import controlador.ProductoController;
import controlador.UsuarioController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import modelo.ProductoDAO;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.CategoriaDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import modelo.Usuario;

public class ProductosPanel extends javax.swing.JPanel {

    private Connection conexion;
    private ProductoDAO productoDAO;
    private CategoriaDAO categoriaDAO;
    private ProductoController productoController;
    private CategoriaController categoriaController;
    private String cargoUsuario;
      private String nombreUsuarioActual;
      private String nombreUsuario;

     public ProductosPanel(Connection conexion, String nombreUsuario, String cargoUsuario)  {
        this.conexion = conexion;
        this.nombreUsuario = nombreUsuario;
        this.cargoUsuario = cargoUsuario;
        this.nombreUsuarioActual = nombreUsuarioActual;
        initComponents();
        initializeDatabaseConnection();
        productoController = new ProductoController(conexion);
        this.productoDAO = new ProductoDAO(conexion);
        categoriaController = new CategoriaController(conexion); // Inicializar el controlador de categorías
        inicializarTablaProductos();
        cargarCategorias(); // Agregar esta línea para inicializar la tabla de productos
        
        JButton[] botones = {btnGuardar, btnNuevo, btnEditar, btnEliminar};
        if (!"Administrador".equals(cargoUsuario)) {
            ocultarBotones(botones);
        }
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

    private void inicializarTablaProductos() {
        try {
            // Configurar el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) TablaProductos.getModel();
            model.setColumnIdentifiers(new String[]{"ID", "Código", "Nombre", "Descripción", "ID Categoría", "Proveedor", "Precio Compra", "Precio Venta", "Stock"});

            // Actualizar la tabla con los datos de los productos
            actualizarTablaProducto();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al inicializar la tabla de productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Agregar el evento MouseClicked a la tabla TablaProducto
        
    }

    private void configurarAccesos() {
        UsuarioController usuarioController = new UsuarioController(conexion);
        String cargo = usuarioController.obtenerCargoPorNombreUsuario(nombreUsuarioActual);

        System.out.println("Cargo del usuario: " + cargo);

        if (!"Administrador".equals(cargo)) {
            // Ocultar botones solo para usuarios que no son Administradores
            btnGuardar.setVisible(false);
            btnNuevo.setVisible(false);
            btnEditar.setVisible(false);
            btnEliminar.setVisible(false);
        }

    }

    private void ocultarBotones(JButton[] botones) {
        for (JButton boton : botones) {
            boton.setVisible(false);
        }
        System.out.println("Botones ocultados correctamente.");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstBusqueda = new javax.swing.JList<>();
        panelBotones = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        lblProveedor = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        lblPrecioCompra = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        lblPrecioVenta = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(940, 550));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Productos");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 0, 163, 54));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 230, 30));

        lstBusqueda.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstBusquedaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstBusqueda);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 450, 50));

        panelBotones.setBackground(new java.awt.Color(255, 255, 255));
        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigo.setText("Codigo");
        jPanel3.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel3.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 29, 158, -1));

        lblNombre.setText("Nombre");
        jPanel3.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, -1, -1));
        jPanel3.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 73, 158, -1));

        lblDescripcion.setText("Descripcion");
        jPanel3.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 102, -1, -1));
        jPanel3.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 121, 158, -1));

        lblCategoria.setText("Categoria");
        jPanel3.add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        lblStock.setText("Stock");
        jPanel3.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 198, -1, -1));
        jPanel3.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 217, 158, -1));

        lblProveedor.setText("Proveedor");
        jPanel3.add(lblProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 246, -1, -1));
        jPanel3.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 265, 158, -1));

        lblPrecioCompra.setText("Precio Compra");
        jPanel3.add(lblPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 294, -1, -1));
        jPanel3.add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 313, 158, -1));

        lblPrecioVenta.setText("Precio Venta");
        jPanel3.add(lblPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 338, -1, -1));

        txtPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaActionPerformed(evt);
            }
        });
        jPanel3.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 357, 158, -1));

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
        jPanel3.add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 160, -1));

        panelBotones.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 390));

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaProductos);

        panelBotones.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 640, 390));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete.png"))); // NOI18N
        btnGuardar.setText("   Guardar");
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelBotones.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 120, 50));

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnNuevo.setText("  Nuevo");
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        panelBotones.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 130, 50));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setText("  Eliminar");
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelBotones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, -1, 50));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        panelBotones.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 110, 50));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        panelBotones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 410, 120, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            // Obtener datos del formulario
            String codigo = txtCodigo.getText();
            String nombre = txtNombreProducto.getText();
            String descripcion = txtDescripcion.getText();

            // Obtener el ID de la categoría seleccionada
            Object selectedItem = cbCategoria.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int fkCategoria;
        if (selectedItem instanceof Integer) {
            fkCategoria = (Integer) selectedItem;
        } else {
            // Si no es un Integer, intenta convertirlo a Integer (por ejemplo, si es un String)
            fkCategoria = Integer.parseInt(selectedItem.toString());
        }

            String proveedor = txtProveedor.getText();
            double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
            double precioVenta = precioCompra * 1.16; // Calcular precio de venta
            int stock = Integer.parseInt(txtStock.getText());

            // Crear un nuevo producto
            Producto nuevoProducto = new Producto(0, codigo, nombre, descripcion, fkCategoria, proveedor, precioCompra, precioVenta, stock);

            // Guardar el producto en la base de datos
            productoController.agregarProducto(nuevoProducto);

            // Actualizar la tabla de productos
            actualizarTablaProducto();

            // Limpiar los campos del formulario
            limpiarCamposProductos();

            JOptionPane.showMessageDialog(this, "Producto guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        limpiarCamposProductos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            // Obtener el ID del producto seleccionado
            int idProducto = obtenerIdSeleccionado();

            // Eliminar el producto de la base de datos
            productoController.eliminarProducto(idProducto);

            // Actualizar la tabla de productos
            actualizarTablaProducto();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = (DefaultTableModel) TablaProductos.getModel();
            int filaSeleccionada = TablaProductos.getSelectedRow();

            if (filaSeleccionada >= 0) {
                int idProducto = (int) model.getValueAt(filaSeleccionada, 0);
                String codigo = txtCodigo.getText();
                String nombre = txtNombreProducto.getText();
                String descripcion = txtDescripcion.getText();

                // Obtener el ID de la categoría seleccionada directamente del ComboBox
                int fkCategoria = (int) cbCategoria.getSelectedItem();

                String proveedor = txtProveedor.getText();
                double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
                double precioVenta = calcularPrecioVenta(precioCompra); // Calcular precio de venta
                int stock = Integer.parseInt(txtStock.getText());

                // Crear un nuevo objeto Producto con los datos actualizados
                Producto productoActualizado = new Producto(idProducto, codigo, nombre, descripcion, fkCategoria, proveedor, precioCompra, precioVenta, stock);

                // Actualizar el producto en la base de datos
                productoController.actualizarProducto(productoActualizado);

                // Actualizar la tabla de productos
                actualizarTablaProducto();

                // Limpiar los campos del formulario
                limpiarCamposProductos();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void TablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductosMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = TablaProductos.getSelectedRow();

        if (filaSeleccionada >= 0) {
            DefaultTableModel model = (DefaultTableModel) TablaProductos.getModel();

            // Obtener los datos del producto seleccionado en la tabla
            Object codigoObj = model.getValueAt(filaSeleccionada, 1);
            String codigo = codigoObj != null ? codigoObj.toString() : "";

            Object nombreObj = model.getValueAt(filaSeleccionada, 2);
            String nombre = nombreObj != null ? nombreObj.toString() : "";

            Object descripcionObj = model.getValueAt(filaSeleccionada, 3);
            String descripcion = descripcionObj != null ? descripcionObj.toString() : "";

            Object fkCategoriaObj = model.getValueAt(filaSeleccionada, 4);
            int fkCategoria = fkCategoriaObj != null ? (int) fkCategoriaObj : 0;

            Object proveedorObj = model.getValueAt(filaSeleccionada, 5);
            String proveedor = proveedorObj != null ? proveedorObj.toString() : "";

            Object precioCompraObj = model.getValueAt(filaSeleccionada, 6);
            double precioCompra = precioCompraObj != null ? (double) precioCompraObj : 0.0;

            Object precioVentaObj = model.getValueAt(filaSeleccionada, 7);
            double precioVenta = precioVentaObj != null ? (double) precioVentaObj : 0.0;

            Object stockObj = model.getValueAt(filaSeleccionada, 8);
            int stock = stockObj != null ? (int) stockObj : 0;

            // Llenar los campos de texto con los datos obtenidos
            txtCodigo.setText(codigo);
            txtNombreProducto.setText(nombre);
            txtDescripcion.setText(descripcion);
            txtProveedor.setText(proveedor);
            txtPrecioCompra.setText(String.valueOf(precioCompra));
            txtPrecioVenta.setText(String.valueOf(precioVenta));
            txtStock.setText(String.valueOf(stock));

            // Buscar el índice correspondiente al ID de la categoría del producto en el JComboBox
            for (int i = 0; i < cbCategoria.getItemCount(); i++) {
                if ((int) cbCategoria.getItemAt(i) == fkCategoria) {
                    cbCategoria.setSelectedIndex(i);
                    break;
                }
            }
        }
    }//GEN-LAST:event_TablaProductosMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        String consulta = txtBuscar.getText();
        List<Producto> productos = null;
        try {
            productos = productoDAO.buscarProductos(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(PuntodeVentaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Actualizar el modelo de la lista de búsqueda
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Producto producto : productos) {
            // Formatear la cadena para cada producto con los componentes separados por " - "
            String item = producto.getCodigo() + " - "
                    + producto.getNombre() + " - "
                    + producto.getDescripcion() + " - "
                    + producto.getStock() + " - "
                    + producto.getProveedor() + " - "
                    + producto.getPrecioCompra() + " - "
                    + producto.getPrecioVenta();
            model.addElement(item);
        }
        lstBusqueda.setModel(model);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void lstBusquedaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstBusquedaValueChanged

        String selectedValue = lstBusqueda.getSelectedValue();
        if (selectedValue != null) {
            // Separar los componentes del valor seleccionado por " - "
            String[] parts = selectedValue.split(" - ");
            // Verificar que haya suficientes partes en la cadena
            if (parts.length >= 7) {
                String codigo = parts[0];
                String producto = parts[1];
                String descripcion = parts[2];
                String stock = parts[3];
                String proveedor = parts[4];
                String precioCompra = parts[5];
                String precioVenta = parts[6];

                // Buscar en la tabla el producto 
                // Establecer los valores en los campos correspondientes
                txtCodigo.setText(codigo);
                txtNombreProducto.setText(producto);
                txtDescripcion.setText(descripcion);
                txtStock.setText(stock);
                txtProveedor.setText(proveedor);
                txtPrecioCompra.setText(precioCompra);
                txtPrecioVenta.setText(precioVenta);

            } else {
                System.err.println("Formato de datos incorrecto en la lista de búsqueda: " + selectedValue);
            }
        }
    }//GEN-LAST:event_lstBusquedaValueChanged

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        actualizarTablaProducto();
        limpiarCamposProductos();
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProductos;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<Integer> cbCategoria;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecioCompra;
    private javax.swing.JLabel lblPrecioVenta;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblStock;
    private javax.swing.JList<String> lstBusqueda;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
private double calcularPrecioVenta(double precioCompra) {
        // Calcular el precio de venta como el precio de compra más el 16%
        return precioCompra * 1.16;
    }

    private void limpiarCamposProductos() {
        txtCodigo.setText("");
        txtNombreProducto.setText("");
        txtDescripcion.setText("");
        txtProveedor.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtStock.setText("");
        cbCategoria.setSelectedIndex(0);
    }

    private void actualizarTablaProducto() {
        List<Producto> productos = productoController.obtenerTodosProductos();
        if (productos != null) {
            DefaultTableModel model = (DefaultTableModel) TablaProductos.getModel();
            model.setRowCount(0);

            for (Producto producto : productos) {
                model.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getFkCategoria(),
                    producto.getProveedor(),
                    producto.getPrecioCompra(),
                    producto.getPrecioVenta(),
                    producto.getStock()
                });
            }
        }

    }

    

    private int obtenerIdSeleccionado() {
        // Obtiene el modelo de la tabla de productos
        DefaultTableModel model = (DefaultTableModel) TablaProductos.getModel();

        // Obtiene el índice de la fila seleccionada
        int selectedRowIndex = TablaProductos.getSelectedRow();

        // Verifica si se ha seleccionado una fila válida
        if (selectedRowIndex >= 0) {
            // Obtiene el ID del producto de la fila seleccionada
            return (int) model.getValueAt(selectedRowIndex, 0); // Considerando que el ID del producto está en la columna 0
        }
        // Si no se ha seleccionado ninguna fila, devuelve -1
        return -1;
    }

    private void cargarCategorias() {
        List<Categoria> categorias = categoriaController.obtenerTodasCategorias();

        // Limpiamos el ComboBox de categorías
        cbCategoria.removeAllItems();

        // Añadimos cada ID de categoría al ComboBox
        for (Categoria categoria : categorias) {
            cbCategoria.addItem(categoria.getIdCategoria());
        }
    }
    private Map<String, Categoria> categoriasMap = new HashMap<>();

}
