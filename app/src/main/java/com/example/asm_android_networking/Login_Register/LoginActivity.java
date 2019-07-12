package com.example.asm_android_networking.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_android_networking.MainActivity;
import com.example.asm_android_networking.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnlogin,btnlwfb,btnlwgg;
    EditText etuser,etpass;
    TextView tvregister, tvforgetpass;
    CheckBox cbsaveacc;

    private SharedPreferences mPrefs;
    private static final String PREES_NAME = "PrefsFile";
    // cờ check me
    private Boolean checkme = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Ánh xạ
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlwfb = (Button) findViewById(R.id.btnlwfb);
        btnlwgg = (Button)findViewById(R.id.btnlwgg);
        etuser = (EditText) findViewById(R.id.etuser);
        etpass = (EditText)findViewById(R.id.etpass);
        cbsaveacc = (CheckBox) findViewById(R.id.cbsaveacc);
        tvforgetpass = (TextView)findViewById(R.id.tvforgetpass);
        tvregister = (TextView)findViewById(R.id.tvregister);
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

    }

    private void checkrememberme() {
        SharedPreferences sp = getSharedPreferences(PREES_NAME,MODE_PRIVATE);
            this.checkme = sp.getBoolean("Prefs_cb",false);
            if(this.checkme){
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
               }else{
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
                break;
            case R.id.btnlwgg:
                break;
        }
    }
}
