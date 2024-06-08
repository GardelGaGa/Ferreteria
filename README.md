# Proyecto Ferretería

Este proyecto es una aplicación en Java para gestionar una ferretería. Incluye funcionalidades para la gestión de productos, ventas, compras, proveedores, usuarios y categorías. La aplicación también ofrece interfaces gráficas de usuario y generación de reportes en PDF.

## Tabla de Contenidos
- [Estructura del Proyecto](#estructura-del-proyecto)
  - [Configuración del Proyecto](#configuración-del-proyecto)
  - [Controladores](#controladores-srcontrolador)
  - [Vistas](#vistas-srvista)
  - [Imágenes](#imágenes-srimg)
- [Patrón MVC](#patrón-mvc)
- [Funcionalidades Principales](#funcionalidades-principales)
- [Requisitos del Sistema](#requisitos-del-sistema)
- [Instalación y Uso](#instalación-y-uso)
  - [Instrucciones para Compilar](#instrucciones-para-compilar)
  - [Instrucciones para Ejecutar](#instrucciones-para-ejecutar)
- [Ejemplos de Uso](#ejemplos-de-uso)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Estructura del Proyecto

### Configuración del Proyecto
- **`build.xml`**: Archivo de configuración para la construcción del proyecto con Apache Ant.
- **`manifest.mf`**: Archivo de manifiesto para el empaquetado del proyecto.
- **`README.md`**: Archivo de documentación del proyecto.
- **`nbproject/*`**: Archivos de configuración del proyecto NetBeans.

### Controladores (`src/controlador/`)
Estos archivos manejan la lógica de negocio de la aplicación.
- **`CategoriaController.java`**: Controlador para gestionar categorías.
- **`CompraController.java`**: Controlador para gestionar compras.
- **`Conexion.java`**: Clase para manejar la conexión a la base de datos.
- **`Ctrl_RegistrarVenta.java`**: Controlador para registrar ventas.
- **`EnvioCorreos.java`**: Clase para enviar correos electrónicos.
- **`LoginController.java`**: Controlador para la autenticación de usuarios.
- **`Main.java`**: Clase principal que inicia la aplicación.
- **`ProductoController.java`**: Controlador para gestionar productos.
- **`ProveedorController.java`**: Controlador para gestionar proveedores.
- **`PruebaConexion.java`**: Clase para probar la conexión a la base de datos.
- **`UsuarioController.java`**: Controlador para gestionar usuarios.
- **`VentaController.java`**: Controlador para gestionar ventas.
- **`VentaPDF.java`**: Clase para generar PDFs de ventas.

### Vistas (`src/vista/`)
Estos archivos corresponden a las interfaces gráficas de usuario.
- **`AcercaDeDialog.java`**: Diálogo sobre información de la aplicación.
- **`AgregarCategoriaPanel.form` y `AgregarCategoriaPanel.java`**: Panel para agregar categorías.
- **`AgregarProveedorPanel.form` y `AgregarProveedorPanel.java`**: Panel para agregar proveedores.
- **`AgregarUsuarioPanel.form` y `AgregarUsuarioPanel.java`**: Panel para agregar usuarios.
- **`BuscarPanel.form` y `BuscarPanel.java`**: Panel para buscar elementos.
- **`CategoriaPanel.form` y `CategoriaPanel.java`**: Panel para gestionar categorías.
- **`ClientesPanel.form` y `ClientesPanel.java`**: Panel para gestionar clientes.
- **`CompraPanel.form` y `CompraPanel.java`**: Panel para gestionar compras.
- **`Dashboard.form` y `Dashboard.java`**: Panel principal de la aplicación.
- **`LoginPanel.form` y `LoginPanel.java`**: Panel de inicio de sesión.
- **`PrincipalFrame.form` y `PrincipalFrame.java`**: Ventana principal de la aplicación.
- **`ProductoPanel.form` y `ProductoPanel.java`**: Panel para gestionar productos.
- **`ProveedoresPanel.form` y `ProveedoresPanel.java`**: Panel para gestionar proveedores.
- **`PuntodeVentaPanel.form` y `PuntodeVentaPanel.java`**: Panel para el punto de venta.
- **`RegistroVentasPanel.form` y `RegistroVentasPanel.java`**: Panel para registrar ventas.
- **`SeleccionarFecha.form` y `SeleccionarFecha.java`**: Panel para seleccionar fechas.
- **`TablaAdhesivosySelladores.form` y `TablaAdhesivosySelladores.java`**: Tablas para adhesivos y selladores.
- **`TablaElectricidad.form` y `TablaElectricidad.java`**: Tablas para productos de electricidad.
- **`TablaFerreteriaGeneral.form` y `TablaFerreteriaGeneral.java`**: Tablas para productos de ferretería general.
- **`TablaFontaneria.form` y `TablaFontaneria.java`**: Tablas para productos de fontanería.
- **`TablaHerramientas.form` y `TablaHerramientas.java`**: Tablas para herramientas.
- **`TablaIluminacion.form` y `TablaIluminacion.java`**: Tablas para productos de iluminación.
- **`TablaJardineria.form` y `TablaJardineria.java`**: Tablas para productos de jardinería.
- **`TablaMaterialesConstruccion.form` y `TablaMaterialesConstruccion.java`**: Tablas para materiales de construcción.
- **`TablaPinturas.form` y `TablaPinturas.java`**: Tablas para pinturas.
- **`TablaSeguridad.form` y `TablaSeguridad.java`**: Tablas para productos de seguridad.
- **`UsuariosPanel.form` y `UsuariosPanel.java`**: Panel para gestionar usuarios.

### Imágenes (`src/img/`)
Estos archivos corresponden a los recursos gráficos utilizados en la aplicación.
- **`actualizar-flecha.png`**: Icono para la acción de actualizar.
- **`adhesivos.png`**: Icono para la categoría de adhesivos.
- **`agregar.png`**: Icono para la acción de agregar.
- **`bote-de-pintura.png`**: Icono para la categoría de pinturas.
- **`buscar.png`**: Icono para la acción de buscar.
- **`caja-de-herramientas.png`**: Icono para la categoría de herramientas.
- **`caja-registradora.png`**: Icono para la acción de registrar ventas.
- **`candado-de-seguridad.png`**: Icono para la categoría de seguridad.
- **`cerrar-sesion.png`**: Icono para la acción de cerrar sesión.
- **`comercio-electronico.png`**: Icono general para comercio electrónico.
- **`disquete.png`**: Icono para la acción de guardar.
- **`...`**: Otros iconos relacionados con las diferentes funcionalidades y categorías de la ferretería.

## Patrón MVC

La aplicación sigue el patrón de diseño Modelo-Vista-Controlador (MVC), que separa la lógica de negocio, la presentación y el control de la aplicación en componentes distintos para facilitar la gestión y escalabilidad del código.

- **Modelo (Model)**: Representa los datos y la lógica de negocio. En este proyecto, los modelos están representados implícitamente en los controladores y las conexiones a la base de datos.
- **Vista (View)**: Maneja la presentación de los datos y la interfaz de usuario. Las vistas están ubicadas en el paquete `src/vista` y utilizan formularios (.form) y clases Java (.java) para renderizar la interfaz gráfica.
- **Controlador (Controller)**: Actúa como un intermediario entre el modelo y la vista, manejando la lógica de la aplicación y la interacción del usuario. Los controladores están ubicados en el paquete `src/controlador` y se encargan de gestionar las operaciones CRUD y la lógica de negocio.

## Funcionalidades Principales
1. **Conexión a Base de Datos**: `Conexion.java` y `PruebaConexion.java` manejan la conexión a la base de datos.
2. **Gestión de Categorías, Productos, Proveedores y Usuarios**: Varios controladores (`CategoriaController.java`, `ProductoController.java`, `ProveedorController.java`, `UsuarioController.java`) y sus respectivas vistas gestionan estas entidades.
3. **Gestión de Ventas y Compras**: `CompraController.java`, `VentaController.java` y `Ctrl_RegistrarVenta.java` gestionan las compras y ventas, incluyendo la generación de PDFs (`VentaPDF.java`).
4. **Interfaz de Usuario**: Diversos paneles y formularios (`.form` y `.java` en `vista`) crean la interfaz gráfica de usuario, proporcionando funcionalidades como inicio de sesión, punto de venta, gestión de inventario, etc.
5. **Envío de Correos**: `EnvioCorreos.java` se encarga del envío de correos electrónicos.

## Requisitos del Sistema
- **Java Development Kit (JDK)**: Necesario para compilar y ejecutar el proyecto.
- **NetBeans IDE**: Recomendado para el desarrollo y manejo del proyecto.
- **Base de Datos**: Configuración requerida para la conexión a la base de datos en `Conexion.java`.

## Instalación y Uso

### Instrucciones para Compilar
1. Clona este repositorio:
    ```sh
    git clone https://github.com/tu-usuario/ferreteria.git
    ```
2. Importa el proyecto en tu IDE preferido (recomendado NetBeans).
3. Configura la base de datos según las especificaciones en `src/controlador/Conexion.java`.

### Instrucciones para Ejecutar
1. Compila y construye el proyecto usando Apache Ant o el sistema de construcción de tu IDE.
    ```sh
    ant build
    ```
2. Ejecuta la clase `Main.java` para iniciar la aplicación.
    ```sh
    java -cp dist/Ferreteria.jar controlador.Main
    ```

## Ejemplos de Uso

### Gestión de Productos
1. **Agregar Producto**:
   - Navega a la sección de productos.
   - Llena el formulario de "Agregar Producto" con la información requerida (nombre, categoría, precio, etc.).
   - Haz clic en "Guardar" para añadir el producto al inventario.
   
2. **Buscar Producto**:
   - Usa el panel de búsqueda para encontrar productos por nombre, categoría u otros criterios.
   - Los resultados de la búsqueda se mostrarán en una tabla.

### Gestión de Ventas
1. **Registrar Venta**:
   - Ve al panel de "Punto de Venta".
   - Selecciona los productos vendidos y la cantidad.
   - Completa la información del cliente y el método de pago.
   - Haz clic en "Registrar Venta" para completar la transacción.
   - Un recibo en PDF se generará automáticamente y estará disponible para su descarga.

### Gestión de Compras
1. **Registrar Compra**:
   - Accede al panel de compras.
   - Ingresa los detalles de la compra, incluyendo el proveedor y los productos adquiridos.
   - Haz clic en "Guardar" para registrar la compra en el sistema.

### Envío de Correos
1. **Notificaciones por Correo**:
   - La aplicación permite enviar correos electrónicos automáticos para notificaciones importantes, como confirmaciones de ventas o alertas de stock.
   - Configura el servidor de correo en `EnvioCorreos.java` para habilitar esta función.

## Contribuciones
Las contribuciones son bienvenidas. Para contribuir, sigue estos pasos:
1. Haz un fork de este repositorio.
2. Crea una rama para tu nueva función o corrección de errores (`git checkout -b feature/nueva-funcion`).
3. Haz tus cambios y commitea (`git commit -m 'Agrega nueva función'`).
4. Empuja tu rama (`git push origin feature/nueva-funcion`).
5. Abre un Pull Request en este repositorio.


## Contacto
Para cualquier duda o consulta, puedes contactarnos a través de:
- **Correo Electrónico**: valentinosantosperez@gmail.com y alaguzman.06@gmail.com
- **GitHub Issues**: https://github.com/GardelGaGa y https://github.com/AlaryGuzman
