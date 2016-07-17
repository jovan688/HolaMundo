package enterprise.com.ni.sifac.servicio;

/**
 * Created by STARK on 15/06/2016.
 */
public class ConfigurationService {

    private static String URL = "http://sifacc.azurewebsites.net/SifaccService.svc/";

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        ConfigurationService.URL = URL;
    }

}
