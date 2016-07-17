package enterprise.com.ni.sifac.arquitectura.presentador;
import android.content.Context;
import enterprise.com.ni.sifac.lib.*;

/**
 * Created by STARK on 14/06/2016.
 */
public interface LoginPresenter {

    void onCreated();
    void onDestroy();
    void checkAuthenticatedUser(Context context);
    void validateLogin(String username , String password);
    void onEventMainThread(Events event);
}
