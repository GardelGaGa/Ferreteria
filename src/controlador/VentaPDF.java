package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentaPDF {

    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    private JTable tablaPuntoVenta;
    private JTextField txtTotal;
    private JTextField txtPago;

    public VentaPDF(JTable tablaPuntoVenta, JTextField txtTotal, JTextField txtPago) {
        this.tablaPuntoVenta = tablaPuntoVenta;
        this.txtTotal = txtTotal;
        this.txtPago = txtPago;
    }

    // Método para generar la factura de venta
    public String generarFacturaPDF() {
        String rutaArchivo = null;
        try {
            // cargar la fecha y hora actual
            Date date = new Date();
            // Formato para el nombre del archivo con fecha y hora
            SimpleDateFormat formatterNombreArchivo = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
            String fechaHoraActual = formatterNombreArchivo.format(date);
            // Formato para mostrar solo la fecha en el contenido del PDF
            SimpleDateFormat formatterContenidoPDF = new SimpleDateFormat("yyyy/MM/dd");
            String fechaActual = formatterContenidoPDF.format(date);

            // cambiar el formato de la fecha de / a _ para el nombre del archivo
            String fechaHoraNueva = fechaHoraActual.replace("/", "_").replace(":", "").replace(" ", "_");

            // generar un número de factura aleatorio
            Random rand = new Random();
            int facturaNum = rand.nextInt(1000);  // Genera un número aleatorio entre 0 y 999

            String nombreArchivoPDFVenta = "Venta_" + "_" + fechaHoraNueva + "_folio_" + facturaNum + ".pdf";
            rutaArchivo = "src/pdf/" + nombreArchivoPDFVenta;

            FileOutputStream archivo;
            File file = new File(rutaArchivo);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image img = Image.getInstance("src/img/logo1.png");
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE); // agregar nueva línea
            // Aquí se usa fechaActual que contiene solo la fecha
            fecha.add("No.Venta: " + facturaNum + "\nFecha: " + fechaActual + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0); // quitar el borde de la tabla
            // tamaño de las celdas
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            // agregar celdas
            Encabezado.addCell(img);

//cambiar por nuestros datos 
            String ruc = "0987654321001";
            String nombre = "Ferreteria Tacolote";
            String telefono = "9515766839";
            String direccion = "Francisco I. Madero, Instituto Tecnologico de Oaxaca, 68033 Oaxaca de Juárez, Oax.";
            String razon = "Todo lo que necesitas, justo a la mano";

            Encabezado.addCell(""); // celda vacía
            Encabezado.addCell("RUC: " + ruc + "\nNOMBRE: " + nombre + "\nTELÉFONO: " + telefono + "\nDIRECCIÓN: " + direccion + "\nRAZÓN SOCIAL: " + razon);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            // ESPACIO EN BLANCO
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);

            // AGREGAR LOS PRODUCTOS
            PdfPTable tablaProducto = new PdfPTable(5);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            float[] ColumnaProducto = new float[]{15f, 50f, 15f, 20f, 20f};
            tablaProducto.setWidths(ColumnaProducto);

            tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell producto1 = new PdfPCell(new Phrase("Codigo: ", negrita));
            PdfPCell producto2 = new PdfPCell(new Phrase("Producto: ", negrita));
            PdfPCell producto3 = new PdfPCell(new Phrase("Unidades: ", negrita));
            PdfPCell producto4 = new PdfPCell(new Phrase("Precio unidad: ", negrita));
            PdfPCell producto5 = new PdfPCell(new Phrase("Precio total: ", negrita));

            // quitar bordes
            producto1.setBorder(0);
            producto2.setBorder(0);
            producto3.setBorder(0);
            producto4.setBorder(0);
            producto5.setBorder(0);

            // agregar color al encabezado del producto
            producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto5.setBackgroundColor(BaseColor.LIGHT_GRAY);

            // agregar celda a la tabla
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            tablaProducto.addCell(producto4);
            tablaProducto.addCell(producto5);

            // Obtener datos de la tabla
            DefaultTableModel model = (DefaultTableModel) tablaPuntoVenta.getModel();
            double totalVenta = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                String codigo = model.getValueAt(i, 0).toString();
                String descripcion = model.getValueAt(i, 1).toString();
                String unidades = model.getValueAt(i, 2).toString();
                String precioUnitario = model.getValueAt(i, 3).toString();
                String precioTotal = model.getValueAt(i, 4).toString();

                tablaProducto.addCell(codigo);
                tablaProducto.addCell(descripcion);
                tablaProducto.addCell(unidades);
                tablaProducto.addCell(precioUnitario);
                tablaProducto.addCell(precioTotal);

                totalVenta += Double.parseDouble(precioTotal);

            }
            PdfPCell celdaIVA = new PdfPCell(new Phrase("IVA: ", negrita));
            celdaIVA.setColspan(4); // Hacer que la celda ocupe 4 columnas
            celdaIVA.setBorder(0);
            celdaIVA.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablaProducto.addCell(celdaIVA);

// Aquí puedes calcular el IVA en base a tu total y agregarlo a la tabla
            double iva = totalVenta * 0.16; // Este es un ejemplo, debes calcular el IVA real

// Formatear el IVA para limitar a dos decimales
            String ivaFormateado = String.format("%.2f", iva);

            tablaProducto.addCell(ivaFormateado);

            doc.add(tablaProducto);

            // Total a pagar
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + txtTotal.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

// Cantidad con la que se pagó
            Paragraph infoPago = new Paragraph();
            infoPago.add(Chunk.NEWLINE);
            infoPago.add("Pagado con: " + txtPago.getText());
            infoPago.setAlignment(Element.ALIGN_RIGHT);
            doc.add(infoPago);

// Calcular el cambio
            double total = Double.parseDouble(txtTotal.getText());
            double pago = Double.parseDouble(txtPago.getText());
            double cambio = pago - total;

// Formatear el cambio para limitar a dos decimales
            String cambioFormateado = String.format("%.2f", cambio);

// Cambio
            Paragraph infoCambio = new Paragraph();
            infoCambio.add(Chunk.NEWLINE);
            infoCambio.add("Cambio: " + cambioFormateado);
            infoCambio.setAlignment(Element.ALIGN_RIGHT);
            doc.add(infoCambio);

// Mensaje
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("¡Gracias por su compra!");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            // cerrar el documento
            // cerrar el documento
            doc.close();
            archivo.close();

            Desktop.getDesktop().open(file);
            //  guardarVentaEnBaseDeDatos(idUsuario, metodoPago);

        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
        return rutaArchivo;
    }
}
