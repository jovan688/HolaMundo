package enterprise.com.ni.sifac.Actividades;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.ni.sifac.dao.ConfigurationDao;
import com.ni.sifac.dao.DaoMaster;
import com.ni.sifac.dao.DaoSession;

/**
 * Created by STARK on 29/05/2016.
 */
public class DaoAPP extends Application {


    static DaoSession session ;

    @Override
    public void onCreate() {
        super.onCreate();
        // INIT DABABASE SCHEMA
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"SifacDB",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        session = daoMaster.newSession();
        //ConfigurationDao configurationDao = session.getConfigurationDao();
    }

    public static DaoSession getSession(){
        return  session;
    }

}
