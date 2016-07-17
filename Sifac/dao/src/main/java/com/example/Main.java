package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {

    static int DATABASEVERSION = 3;
    static  String DEFAULTJAVAPACKAGE ="com.ni.sifac.dao";

    public  static  void  main(String args[]) throws Exception {
        Schema schema = new Schema(DATABASEVERSION,DEFAULTJAVAPACKAGE);
        schema.enableKeepSectionsByDefault();
        createDataBase(schema);
        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema,args[0]);
    }

    private static void createDataBase(Schema schema) {

        Entity configuracion = schema.addEntity("Configuration");
        configuracion.addIdProperty();
        configuracion.addBooleanProperty("HasAccess");
        configuracion.addStringProperty("SsgCuentaID");
        configuracion.addStringProperty("Login");
        configuracion.addStringProperty("Clave");
        configuracion.addIntProperty("objEmpleadoID");


        Entity Catalog = schema.addEntity("Catalog");
        Catalog.addIdProperty();
        Catalog.addBooleanProperty("Activo");
        Catalog.addBooleanProperty("CatalogoActivo");
        Catalog.addStringProperty("CatalogoDescripcion");
        Catalog.addStringProperty("Codigo");
        Catalog.addStringProperty("Descripcion");
        Catalog.addStringProperty("Nombre");
        Catalog.addIntProperty("StbValorCatalogoID");

        Entity Producto = schema.addEntity("Producto");
        Producto.addIdProperty();
        Producto.addIntProperty("Cantidad_Minima");
        Producto.addFloatProperty("CostoPromedio");
        Producto.addStringProperty("Descripcion");
        Producto.addFloatProperty("Margen_Utilidad_Contado");
        Producto.addFloatProperty("Margen_Utilidad_Credito");
        Producto.addStringProperty("Nombre");
        Producto.addFloatProperty("Precio_Contado");
        Producto.addFloatProperty("Precio_Credito");
        Producto.addIntProperty("SivProductoID");
        Producto.addIntProperty("objCategoriaID");
        Producto.addIntProperty("objMarcaID");


        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("Apellido2");
        customer.addStringProperty("Apellido1");
        customer.addStringProperty("Cedula");
        customer.addStringProperty("Ciudad");
        customer.addIntProperty("ClienteID");
        customer.addStringProperty("Direccion");
        customer.addDateProperty("FechaNacimiento");
        customer.addStringProperty("Genero");
        customer.addStringProperty("Nombre1");
        customer.addStringProperty("Nombre2");
        customer.addIntProperty("OrdenCobro");
        customer.addStringProperty("Pais");
        customer.addStringProperty("RutaAsignada");
        customer.addIntProperty("StbRutaID");
        customer.addStringProperty("Telefonos");
        customer.addIntProperty("objCiudadID");
        customer.addIntProperty("objGeneroID");
        customer.addIntProperty("objPaisID");
        customer.addIntProperty("ojbCobradorID");



        Entity cartera = schema.addEntity("Cartera");
        cartera.addIdProperty();
        cartera.addStringProperty("Cedula");
        cartera.addStringProperty("Ciudad");
        cartera.addIntProperty("ClienteID");
        cartera.addStringProperty("Direccion");
        cartera.addStringProperty("FechaAbono");
        cartera.addFloatProperty("MontoCuota");
        cartera.addStringProperty("NombreCompleto");
        cartera.addStringProperty("OrdenCobro");
        cartera.addStringProperty("Pais");
        cartera.addStringProperty("RutaAsignada");
        cartera.addFloatProperty("Saldo");
        cartera.addStringProperty("SccCuentaID");
        cartera.addIntProperty("StbRutaID");
        cartera.addIntProperty("ojbCobradorID");

        Entity adquisicion = schema.addEntity("Adquisicion");
        adquisicion.addIntProperty("SivProductoID");
        adquisicion.addIntProperty("ClienteID");

        Entity  descuento= schema.addEntity("Descuento");
        descuento.addFloatProperty("DescuentoMaximo");
        descuento.addFloatProperty("DescuentoMinimo");
        descuento.addFloatProperty("PlazoPago");
        descuento.addFloatProperty("SccDescuentoID");

        Entity  categoria= schema.addEntity("Categoria");
        categoria.addIntProperty("CategoriaID");
        categoria.addStringProperty("Descripcion");
        categoria.addStringProperty("Nombre");

        Entity  ciudad= schema.addEntity("Ciudad");
        ciudad.addStringProperty("Nombre");
        ciudad.addIntProperty("StbCiudadID");
        ciudad.addIntProperty("objPaisID");
    }
}
