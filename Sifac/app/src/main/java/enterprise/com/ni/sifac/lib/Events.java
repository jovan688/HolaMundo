package enterprise.com.ni.sifac.lib;

/**
 * Created by STARK on 15/06/2016.
 */
public class Events {

    public  final static int onSigInError =0;
    public  final static int onSigInSuccess =1;
    public  final static int onSuccessToRecoverySession =2;
    public  final static int onFailToRecoverySession =3;

    private  int eventype;
    private  String errorMessage;

    public int getEventype() {
        return eventype;
    }

    public void setEventype(int eventype) {
        this.eventype = eventype;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
