package enterprise.com.ni.sifac.arquitectura.interactuador;

import android.content.Context;

import enterprise.com.ni.sifac.arquitectura.repositorio.LoginRepository;
import enterprise.com.ni.sifac.arquitectura.repositorio.LoginRepositoryImplement;


/**
 * Created by STARK on 15/06/2016.
 */
public class LoginInteractorImplement implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImplement() {
        this.loginRepository =  new LoginRepositoryImplement();
    }

    @Override
    public void checkSession(Context context) {
        loginRepository.checkSession(context);
    }

    @Override
    public void doSignIn(String username, String password) {
        loginRepository.signIn(username,password);
    }
}
