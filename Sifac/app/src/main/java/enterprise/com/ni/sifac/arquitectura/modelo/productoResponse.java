package enterprise.com.ni.sifac.arquitectura.modelo;

/**
 * Created by STARK on 23/06/2016.
 */
public class productoResponse {

    private  Integer SivProductoID ;

    public Integer getSivProductoID() {
        return SivProductoID;
    }

    public void setSivProductoID(Integer sivProductoID) {
        SivProductoID = sivProductoID;
    }

    public productoResponse(Integer sivProductoID) {
        SivProductoID = sivProductoID;
    }
}
