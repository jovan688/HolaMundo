package enterprise.com.ni.sifac.arquitectura.presentador;

import android.content.Context;
import android.util.Log;

import enterprise.com.ni.sifac.arquitectura.interactuador.LoginInteractor;
import enterprise.com.ni.sifac.arquitectura.interactuador.LoginInteractorImplement;
import enterprise.com.ni.sifac.arquitectura.vista.LoginView;
import enterprise.com.ni.sifac.lib.EventBus;
import enterprise.com.ni.sifac.lib.Events;
import enterprise.com.ni.sifac.lib.GreenRobotEventBus;


/**
 * Created by STARK on 15/06/2016.
 */
public class LoginPresenterImplement implements LoginPresenter {

    private EventBus eventbus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImplement(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImplement();
        this.eventbus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreated() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventbus.unregister(this);
    }

    @Override
    public void checkAuthenticatedUser(Context context) {
        if(loginView!=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession(context);
    }

    @Override
    public void validateLogin(String username, String password) {
        if(loginView!=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(username,password);
    }

    @Override
    public void onEventMainThread(Events event) {
        switch (event.getEventype()){
            case Events.onSigInSuccess :
                onSignInSucess();
                break;
            case Events.onSigInError :
                onSignInError(event.getErrorMessage());
                break;
            case Events.onSuccessToRecoverySession:
                onSuccessToRecoverySession();
                break;
            case Events.onFailToRecoverySession :
                onFailToRecoverySession(event.getErrorMessage());
                break;
        }
    }

    private void onFailToRecoverySession(String error) {
        if(loginView!=null){
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.loginError(error);
        }
        Log.e("LoginPresenterImplement","onFailToRecoverySession");
    }

    private void onSignInSucess(){
        if(loginView!=null){
            loginView.hideProgress();
            loginView.authenticate();
        }
    }
    private  void onSuccessToRecoverySession(){
        if(loginView!=null){
            loginView.hideProgress();
            loginView.goToMainScreen();
        }
    }

    private void onSignInError(String error){
        if(loginView!=null){
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.loginError(error);
        }
    }

}
