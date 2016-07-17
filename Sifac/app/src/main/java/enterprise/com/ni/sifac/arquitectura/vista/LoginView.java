package enterprise.com.ni.sifac.arquitectura.vista;

/**
 * Created by STARK on 14/06/2016.
 */
public interface LoginView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void authenticate();
    void handleSignIn();
    void goToMainScreen();
    void loginError(String message);

}
