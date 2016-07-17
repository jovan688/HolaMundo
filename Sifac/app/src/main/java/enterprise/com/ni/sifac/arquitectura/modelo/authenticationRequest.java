package enterprise.com.ni.sifac.arquitectura.modelo;

/**
 * Created by STARK on 15/06/2016.
 */
public class authenticationRequest {

    private String Login;
    private String Clave ;

    public authenticationRequest(String Login ,String Clave){
        this.Login = Login;
        this.Clave = Clave;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

}
