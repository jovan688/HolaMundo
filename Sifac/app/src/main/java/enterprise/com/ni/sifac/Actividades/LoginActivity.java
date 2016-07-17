package enterprise.com.ni.sifac.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import enterprise.com.ni.sifac.R;
import enterprise.com.ni.sifac.arquitectura.presentador.LoginPresenter;
import enterprise.com.ni.sifac.arquitectura.presentador.LoginPresenterImplement;
import enterprise.com.ni.sifac.arquitectura.vista.LoginView;


public class LoginActivity extends AccountAuthenticatorActivity implements LoginView {


    @Bind(R.id.imageLogo)
    ImageView imageLogo;
    @Bind(R.id.txtusername)
    EditText txtusername;
    @Bind(R.id.txtpassword)
    EditText txtpassword;
    @Bind(R.id.btnSignIn)
    Button btnSignIn;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    LoginPresenter loginPresenter;

    private final String TAG = this.getClass().getSimpleName();
    public final static String PARAM_USER_PASS = "USER_PASS";
    private String mAuthTokenType;
    private AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImplement(this);
        loginPresenter.onCreated();
        loginPresenter.checkAuthenticatedUser(this);

        mAccountManager = AccountManager.get(this);
    }

    @Override
    public void enableInputs() {
        SetInputs(true);
    }

    @Override
    public void disableInputs() {
        SetInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void authenticate() {

        String authtoken = String.valueOf("123");
        String accountName = txtusername.getText().toString();

        if (mAuthTokenType == null)
            mAuthTokenType = getString(R.string.auth_type);

        Bundle data = new Bundle();
        data.putString(AccountManager.KEY_ACCOUNT_NAME, accountName);
        data.putString(AccountManager.KEY_ACCOUNT_TYPE, mAuthTokenType);
        data.putString(AccountManager.KEY_AUTHTOKEN, authtoken);
        data.putString(PARAM_USER_PASS, txtpassword.getText().toString());
        // Some extra data about the user
        Bundle userData = new Bundle();
        userData.putString("UserID", "25");
        data.putBundle(AccountManager.KEY_USERDATA, userData);

        //Make it an intent to be passed back to the Android Authenticator
        final Intent res = new Intent();
        res.putExtras(data);

        try
        {
            //Create the new account with Account Name and TYPE
            final Account account = new Account(accountName, mAuthTokenType);
            //Add the account to the Android System
            if (mAccountManager.addAccountExplicitly(account, txtpassword.getText().toString(), userData)) {
                // worked
                Log.d(TAG, "Account added");
                mAccountManager.setAuthToken(account, mAuthTokenType, authtoken);
                setAccountAuthenticatorResult(data);

                goToMainScreen();
            }
            else {
                // guess not
                Log.d(TAG, "Account NOT added");
            }
        }
        catch(Exception ex){
            Log.d("HUYERROR", ex.getMessage());
        }
    }

    @OnClick(R.id.btnSignIn)
    @Override
    public void handleSignIn() {
        attemptLogin();
    }

    @Override
    public void goToMainScreen() {

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void loginError(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void SetInputs(boolean enabled){
        btnSignIn.setEnabled(enabled);
        txtpassword.setEnabled(enabled);
        txtusername.setEnabled(enabled);
    }

    private  void attemptLogin(){
        txtusername.setError(null);
        txtpassword.setError(null);

        String username = txtusername.getText().toString();
        String password = txtpassword.getText().toString();

        boolean cancel = false;
        EditText focus = null;

        if(TextUtils.isEmpty(username)){
            txtusername.setError(getString(R.string.login_message_username_Error));
            focus = txtusername;
            cancel = true;
        }

        else if(TextUtils.isEmpty(password)){
            txtpassword.setError(getString(R.string.login_message_password_Error));
            focus = txtpassword;
            cancel = true;
        }

        if(cancel){
            focus.requestFocus();
        }
        else {
            loginPresenter.validateLogin(username,password);
        }
    }



}
