package com.example.asm_android_networking.Login_Register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_android_networking.MainActivity;
import com.example.asm_android_networking.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN = 123;
    LoginButton btnlwfb;
   SignInButton btnlwgg;
    Button btnlogin;
    EditText etuser,etpass;
    TextView tvregister, tvforgetpass;
    CheckBox cbsaveacc;
    private CallbackManager callbackManager;
    private SharedPreferences mPrefs;
    private static final String PREES_NAME = "PrefsFile";
    // cờ check me
    private Boolean checkme = false;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Ánh xạ
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlwfb = (LoginButton) findViewById(R.id.btnlwfb);
        btnlwgg = (SignInButton) findViewById(R.id.btnlwgg);

        etuser = (EditText) findViewById(R.id.etuser);
        etpass = (EditText)findViewById(R.id.etpass);
        cbsaveacc = (CheckBox) findViewById(R.id.cbsaveacc);
        tvforgetpass = (TextView)findViewById(R.id.tvforgetpass);
        tvregister = (TextView)findViewById(R.id.tvregister);
        callbackManager = CallbackManager.Factory.create();
        // Create file lưu pass và user
        mPrefs = getSharedPreferences(PREES_NAME,MODE_PRIVATE);

        //Check remember me
        checkrememberme();



        // set user and pass

        etuser.setText("admin");
        etpass.setText("admin");

        // Even button
        btnlogin.setOnClickListener(this);
        btnlwfb.setOnClickListener(this);
        btnlwgg.setOnClickListener(this);
//google

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void checkrememberme() {
        SharedPreferences sp = getSharedPreferences(PREES_NAME,MODE_PRIVATE);
            this.checkme = sp.getBoolean("Prefs_cb",false);
            if(this.checkme){
                if (sp.contains("Prefs_user")){

                    if (sp.contains("Prefs_user")){
                        String u = sp.getString("Prefs_user","");
                        etuser.setText(u);
                    }
                    if (sp.contains("Prefs_pass")){
                        String p = sp.getString("Prefs_pass","");
                        etpass.setText(p);
                    }
                    if (sp.contains("Prefs_cb")){
                        Boolean u = sp.getBoolean("Prefs_cb",false);
                        cbsaveacc.setChecked(u);
                    }
                    functionlogin();
                }else if(checkfblogin()){
                    functionlogin();
                }
            }
    }

    private void functionlogin() {
           if (validate()){
               if(etuser.getText().toString().equals("admin") && etpass.getText().toString().equals("admin")){
                   if (cbsaveacc.isChecked()){
                       Boolean statuscb = cbsaveacc.isChecked();
                       SharedPreferences.Editor editor = mPrefs.edit();
                       editor.putString("Prefs_user",etuser.getText().toString());
                       editor.putString("Prefs_pass",etpass.getText().toString());
                       editor.putBoolean("Prefs_cb", statuscb);
                       editor.apply();
                   }else {
                       mPrefs.edit().clear().apply();
                   }
                   startActivity(new Intent(this, MainActivity.class));
                   etuser.getText().clear();
                   etpass.getText().clear();
                   finish();
                   return;
               }else if (checkfblogin()){
                   startActivity(new Intent(this, MainActivity.class));
                   finish();
                   return;
               }else if (checkgglogin()){
                   startActivity(new Intent(this, MainActivity.class));
                   finish();
                   return;
               }
               else{
                   Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
               }
           }
    }
    private Boolean validate() {
        if (etuser.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Username không được trống",Toast.LENGTH_LONG).show();
            return false;
        }if (etpass.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Password không được trống",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // btnlogin function
            case R.id.btnlogin:
                functionlogin();
                break;
            case  R.id.btnlwfb:
                functionlwfb();
                break;
            case R.id.btnlwgg:
                functionlwgg();
                break;
        }
    }

    private void functionlwgg() {
      try {
          Intent signInIntent = mGoogleSignInClient.getSignInIntent();
          startActivityForResult(signInIntent, RC_SIGN_IN);
      }catch (Exception x){
          Log.w("gg",  x.getMessage());
      }
    }

    private void functionlwfb() {
        btnlwfb.setReadPermissions(Arrays.asList("email","public_profile"));
        btnlwfb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                functionlogin();
            }

            @Override
            public void onCancel() {

            }
            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    //---------------- give result from server-----------------//

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN && checkgglogin()) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("errgoogle", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        String idgoogle = account.getId();
        String name = account.getDisplayName();
        String email = account.getEmail();

        Log.d("idgoogle", idgoogle);
        Log.d("namegg", name);
        Log.d("emailgg", email);

        functionlogin();
    }
    //-----------------------------------------//




////-------------- set AccessToken ----------------//////
    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken==null){

            }else{
                loaduserprofile(currentAccessToken);
            }
        }
    };
//---------------------------------------------------------///

    //--------------------Load user Facebook from Accesstoken--------------------//
    private void loaduserprofile(AccessToken newAccessToken){
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String firstname = object.getString("first_name");
                    String lastname = object.getString("last_name");
                    String id = object.getString("id");
                    String email = object.getString("email");


                    Log.d("firstname", firstname);
                    Log.d("lastname", lastname);
                    Log.d("id", id);
                    Log.d("email", email);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields","id,email,first_name,last_name");
        request.setParameters(bundle);
        request.executeAsync();
    }
    //-------------------------------------------------------------//


    //-----------------check status loginfb----------//
    private Boolean checkfblogin(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        return isLoggedIn;
    }

    //-------------------------------------------------------------//

    //-----------------check status loginfb----------//
    private Boolean checkgglogin(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            return true;
        }else
            return false;
    }
    //-------------------------------------------------------------//

}
