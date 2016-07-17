package enterprise.com.ni.sifac.servicio;

import com.ni.sifac.dao.Cartera;
import com.ni.sifac.dao.Catalog;
import com.ni.sifac.dao.Categoria;
import com.ni.sifac.dao.Ciudad;
import com.ni.sifac.dao.Customer;
import com.ni.sifac.dao.Descuento;
import com.ni.sifac.dao.Producto;

import java.util.List;

import enterprise.com.ni.sifac.arquitectura.modelo.authenticationRequest;
import enterprise.com.ni.sifac.arquitectura.modelo.authenticationResponse;
import enterprise.com.ni.sifac.arquitectura.modelo.carteraResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by STARK on 15/06/2016.
 */
public interface SifacService {

    @POST("AutenticarUsuario")
    Call<authenticationResponse> AutenticarUsuario(@Body authenticationRequest request);


    @GET("GetClientesByCobradorId/{CobradorId}")
    Call<List<Customer>> GetClientesByCobradorId(@Path("CobradorId") Integer CobradorId);

    @GET("GetValoresCatalogos")
    Call<List<Catalog>>GetValoresCatalogos();

    @GET("GetProductos")
    Call<List<Producto>>GetProductos();

    @GET("GetCarteraByCobradorId/{CobradorId}")
    Call<List<carteraResponse>> GetCarteraByCobradorId(@Path("CobradorId") Integer CobradorId);

    @GET("GetDescuentos")
    Call<List<Descuento>>GetDescuentos();

    @GET("GetCategoriasProductos")
    Call<List<Categoria>>GetCategoriasProductos();


    @GET("GetCiudadesByPais/{PaisId}")
    Call<List<Ciudad>>GetCiudadesByPais(@Path("PaisId") Integer PaisID);


}
