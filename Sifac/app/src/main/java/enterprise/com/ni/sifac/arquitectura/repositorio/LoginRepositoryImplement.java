package enterprise.com.ni.sifac.arquitectura.repositorio;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Log;

import com.ni.sifac.dao.Adquisicion;
import com.ni.sifac.dao.Cartera;
import com.ni.sifac.dao.CarteraDao;
import com.ni.sifac.dao.Catalog;
import com.ni.sifac.dao.CatalogDao;
import com.ni.sifac.dao.Categoria;
import com.ni.sifac.dao.CategoriaDao;
import com.ni.sifac.dao.Ciudad;
import com.ni.sifac.dao.CiudadDao;
import com.ni.sifac.dao.Configuration;
import com.ni.sifac.dao.ConfigurationDao;
import com.ni.sifac.dao.Customer;
import com.ni.sifac.dao.CustomerDao;
import com.ni.sifac.dao.Descuento;
import com.ni.sifac.dao.DescuentoDao;
import com.ni.sifac.dao.Producto;
import com.ni.sifac.dao.ProductoDao;

import java.util.List;
import enterprise.com.ni.sifac.Actividades.DaoAPP;
import enterprise.com.ni.sifac.R;
import enterprise.com.ni.sifac.arquitectura.modelo.authenticationRequest;
import enterprise.com.ni.sifac.arquitectura.modelo.authenticationResponse;
import enterprise.com.ni.sifac.arquitectura.modelo.carteraResponse;
import enterprise.com.ni.sifac.arquitectura.modelo.productoResponse;
import enterprise.com.ni.sifac.lib.EventBus;
import enterprise.com.ni.sifac.lib.Events;
import enterprise.com.ni.sifac.lib.GreenRobotEventBus;
import enterprise.com.ni.sifac.servicio.ConfigurationService;
import enterprise.com.ni.sifac.servicio.SifacService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by STARK on 15/06/2016.
 */
public class LoginRepositoryImplement implements LoginRepository {

    private final String TAG = this.getClass().getSimpleName();
    public final static String PARAM_USER_PASS = "USER_PASS";
    private AccountManager mAccountManager;
    Retrofit retrofit = null;
    SifacService service;

    public LoginRepositoryImplement() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ConfigurationService.getURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    @Override
    public void signIn(String username, String password) {

        authenticationRequest request = new authenticationRequest(username, password);
        service = retrofit.create(SifacService.class);
        Call<authenticationResponse> authenticationResponseCall = service.AutenticarUsuario(request);

        authenticationResponseCall.enqueue(new Callback<authenticationResponse>() {

            @Override
            public void onResponse(Call<authenticationResponse> call, Response<authenticationResponse> response) {
                if (response.body() != null) {
                    onresponse(response);
                    GetValoresCatalogos();
                    GetProductos();
                    GetDescuentos();
                    GetCategoriasProductos();
                    GetCiudadesByPais();
                    //postEvent(Events.onSigInSuccess);
                } else {
                    postEvent(Events.onSigInError, "Usuario Invalido");
                }
            }

            @Override
            public void onFailure(Call<authenticationResponse> call, Throwable t) {
                postEvent(Events.onSigInError, "Usuario Invalido");
            }
        });
    }

    @Override
    public void checkSession(Context context) {

        boolean islogin = false;
        // Getting all registered Our Application Accounts;
        try {
            Account[] accounts = AccountManager.get(context).getAccountsByType(context.getString(R.string.auth_type));
            for (Account account : accounts) {
                islogin = true;
                break;
            }
        } catch (Exception e) {
            Log.i(TAG, "Exception:" + e);
            postEvent(Events.onFailToRecoverySession, e.getMessage());
        }

        if (!islogin) {
            postEvent(Events.onFailToRecoverySession, "Usuario Invalido");
        } else
            postEvent(Events.onSuccessToRecoverySession);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }

    private void postEvent(int type, String errorMessage) {
        Events event = new Events();
        event.setEventype(type);
        if (errorMessage != null) {
            event.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }

    private void onresponse(Response<authenticationResponse> response) {
        authenticationResponse _response = response.body();
        if (_response.getTieneAcceso()) {
            // Eliminamos All
            ConfigurationDao d = DaoAPP.getSession().getConfigurationDao();
            d.deleteAll();
            // LLenamos nuestra Tabla
            Configuration configuration = new Configuration();
            configuration.setClave(response.body().getClave());
            configuration.setLogin(response.body().getLogin());
            configuration.setHasAccess(response.body().getTieneAcceso());
            configuration.setSsgCuentaID(response.body().getSsgCuentaID().toString());
            configuration.setObjEmpleadoID(response.body().getObjEmpleadoID());
            DaoAPP.getSession().getConfigurationDao().insertInTx(configuration);

            this.GetClientesByCobradorId(configuration.getObjEmpleadoID());
            this.GetCarteraByCobradorId(configuration.getObjEmpleadoID());


        }
    }

    private void GetValoresCatalogos() {
        Call<List<Catalog>> catalogResponseCall = service.GetValoresCatalogos();
        catalogResponseCall.enqueue(new Callback<List<Catalog>>() {
            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                if (response.body() != null) {
                    //Clear all data if exist
                    CatalogDao dao = DaoAPP.getSession().getCatalogDao();
                    dao.deleteAll();

                    List<Catalog> list = (List<Catalog>) response.body();

                    for (Catalog catalog : list) {
                        DaoAPP.getSession().getCatalogDao().insertInTx(catalog);
                    }
                    postEvent(Events.onSigInSuccess);
                }
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable t) {
                postEvent(Events.onSigInError, "Error Sincronizando Catalogos");
            }
        });
    }

    private  void GetProductos(){
        Call<List<Producto>> productoResponseCall = service.GetProductos();
        productoResponseCall.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.body() != null) {
                    //Clear all data if exist
                    ProductoDao dao = DaoAPP.getSession().getProductoDao();
                    dao.deleteAll();

                    List<Producto> list = (List<Producto>) response.body();

                    for (Producto producto : list) {
                        DaoAPP.getSession().getProductoDao().insertInTx(producto);
                    }
                    postEvent(Events.onSigInSuccess);

                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                postEvent(Events.onSigInError, "Error Sincronizando Productos");
            }
        });


    }

    private void GetClientesByCobradorId(int cobradorID){
        Call<List<Customer>> customerResponseCall = service.GetClientesByCobradorId(cobradorID);
        customerResponseCall.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if( response.body() != null){
                    //Clear all data if exist
                    CustomerDao dao = DaoAPP.getSession().getCustomerDao();
                    dao.deleteAll();

                    List<Customer> list =(List<Customer>) response.body();
                    for (Customer customer:list) {
                        DaoAPP.getSession().getCustomerDao().insertInTx(customer);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                postEvent(Events.onSigInError,"Error Sincronizando Clientes");
            }
        });
    }

    private void GetCarteraByCobradorId(int cobradorID) {
        Call<List<carteraResponse>> carteraResponseCall = service.GetCarteraByCobradorId(cobradorID);
        carteraResponseCall.enqueue(new Callback<List<carteraResponse>>() {

            @Override
            public void onResponse(Call<List<carteraResponse>> call, Response<List<carteraResponse>> response) {
                if( response.body() != null) {
                    //Clear all data if exist
                    CarteraDao dao = DaoAPP.getSession().getCarteraDao();
                    dao.deleteAll();

                    List<carteraResponse> list = (List<carteraResponse>) response.body();
                    for (carteraResponse cartera : list) {
                        Cartera c = new Cartera();
                        c.setCedula(cartera.getCedula());
                        c.setCiudad(cartera.getCiudad());
                        c.setClienteID(cartera.getClienteID());
                        c.setDireccion(cartera.getDireccion());
                        c.setFechaAbono(cartera.getFechaAbono());
                        c.setMontoCuota(cartera.getMontoCuota());
                        c.setNombreCompleto(cartera.getNombreCompleto());
                        c.setOjbCobradorID(cartera.getOjbCobradorID());
                        c.setOrdenCobro(cartera.getOrdenCobro());
                        c.setOjbCobradorID(cartera.getOjbCobradorID());
                        c.setPais(cartera.getPais());
                        c.setRutaAsignada(cartera.getRutaAsignada());
                        c.setSaldo(cartera.getSaldo());
                        c.setStbRutaID(cartera.getStbRutaID());
                        DaoAPP.getSession().getCarteraDao().insertInTx(c);

                        // Llenamos nuestra tabla detalle Cliente - Producto
                        for ( productoResponse productoresp : cartera.getProductoResponses()) {
                            Adquisicion adquisicion = new Adquisicion();
                            adquisicion.setClienteID(c.getClienteID());
                            adquisicion.setSivProductoID(productoresp.getSivProductoID());
                            DaoAPP.getSession().getAdquisicionDao().insertInTx(adquisicion);
                        }

                    }
                    postEvent(Events.onSigInSuccess);
                }
            }

            @Override
            public void onFailure(Call<List<carteraResponse>> call, Throwable t) {
                postEvent(Events.onSigInError,"Error Sincronizando Cartera");
            }
        });


    }

    private void GetDescuentos(){
        Call<List<Descuento>> descuentoResponseCall = service.GetDescuentos();
        descuentoResponseCall.enqueue(new Callback<List<Descuento>>() {

            @Override
            public void onResponse(Call<List<Descuento>> call, Response<List<Descuento>> response) {
                if (response.body() != null) {
                    DescuentoDao dao =DaoAPP.getSession().getDescuentoDao();
                    dao.deleteAll();

                    List<Descuento> descuentos = (List<Descuento>)response.body();
                    for (Descuento descuento : descuentos) {
                        dao.insertInTx(descuento);
                    }
                    postEvent(Events.onSigInSuccess);
                }
            }

            @Override
            public void onFailure(Call<List<Descuento>> call, Throwable t) {
                postEvent(Events.onSigInError, "Error Sincronizando Descuentos");
            }
        });

    }

    private void GetCategoriasProductos(){
        Call<List<Categoria>> categoriaResponseCall = service.GetCategoriasProductos();
        categoriaResponseCall.enqueue(new Callback<List<Categoria>>() {

            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.body() != null) {
                    CategoriaDao dao =DaoAPP.getSession().getCategoriaDao();
                    dao.deleteAll();

                    List<Categoria> categorias = (List<Categoria>)response.body();
                    for (Categoria categoria : categorias) {
                        dao.insertInTx(categoria);
                    }
                    postEvent(Events.onSigInSuccess);
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                postEvent(Events.onSigInError, "Error Sincronizando Categoria de Producto");
            }
        });

    }

    private void GetCiudadesByPais(){
        Call<List<Ciudad>> ciudadResponseCall = service.GetCiudadesByPais(558);
        ciudadResponseCall.enqueue(new Callback<List<Ciudad>>() {

            @Override
            public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                if (response.body() != null) {
                    CiudadDao dao =DaoAPP.getSession().getCiudadDao();
                    dao.deleteAll();

                    List<Ciudad> ciudades = (List<Ciudad>)response.body();
                    for (Ciudad ciudad : ciudades) {
                        dao.insertInTx(ciudad);
                    }
                    postEvent(Events.onSigInSuccess);
                }
            }

            @Override
            public void onFailure(Call<List<Ciudad>> call, Throwable t) {
                postEvent(Events.onSigInError, "Error Sincronizando Ciudades");
            }
        });

    }
}
