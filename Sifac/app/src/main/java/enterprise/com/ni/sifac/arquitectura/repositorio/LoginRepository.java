package enterprise.com.ni.sifac.arquitectura.repositorio;

import android.content.Context;

/**
 * Created by STARK on 14/06/2016.
 */
public interface LoginRepository {

    void signIn(String username, String password );

    void  checkSession(Context context);

}
