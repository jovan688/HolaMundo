package enterprise.com.ni.sifac.arquitectura.modelo;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by STARK on 23/06/2016.
 */
public class carteraResponse {


    private String Cedula;
    private String Ciudad;
    private Integer ClienteID;
    private String Direccion;
    private String FechaAbono;
    private Float MontoCuota;
    private String NombreCompleto;
    private String OrdenCobro;
    private String Pais;
    private String RutaAsignada;
    private Float Saldo;
    private String SccCuentaID;
    private Integer StbRutaID;
    private Integer ojbCobradorID;
    private List< productoResponse> Productos;

    public carteraResponse() {
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public Integer getClienteID() {
        return ClienteID;
    }

    public void setClienteID(Integer clienteID) {
        ClienteID = clienteID;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getFechaAbono() {
        return FechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        FechaAbono = fechaAbono;
    }

    public Float getMontoCuota() {
        return MontoCuota;
    }

    public void setMontoCuota(Float montoCuota) {
        MontoCuota = montoCuota;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getOrdenCobro() {
        return OrdenCobro;
    }

    public void setOrdenCobro(String ordenCobro) {
        OrdenCobro = ordenCobro;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getRutaAsignada() {
        return RutaAsignada;
    }

    public void setRutaAsignada(String rutaAsignada) {
        RutaAsignada = rutaAsignada;
    }

    public Float getSaldo() {
        return Saldo;
    }

    public void setSaldo(Float saldo) {
        Saldo = saldo;
    }

    public String getSccCuentaID() {
        return SccCuentaID;
    }

    public void setSccCuentaID(String sccCuentaID) {
        SccCuentaID = sccCuentaID;
    }

    public Integer getStbRutaID() {
        return StbRutaID;
    }

    public void setStbRutaID(Integer stbRutaID) {
        StbRutaID = stbRutaID;
    }

    public Integer getOjbCobradorID() {
        return ojbCobradorID;
    }

    public void setOjbCobradorID(Integer ojbCobradorID) {
        this.ojbCobradorID = ojbCobradorID;
    }

    public List<productoResponse> getProductoResponses() {
        return Productos;
    }


}
