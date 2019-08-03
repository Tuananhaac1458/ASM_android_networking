package com.example.asm_android_networking.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_android_networking.R;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etemailregister, etpassregister,etrepassregister,etusername;
    Button btnregister, btncannel;

    private Boolean status_register = false;
    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("http://192.168.0.103:3000/");
        }catch (URISyntaxException e){
            throw new RuntimeException( e );
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /// Ánh xạ///
        etemailregister = (EditText) findViewById(R.id.etemailregister);
        etpassregister = findViewById(R.id.etpassregister);
        etrepassregister = findViewById(R.id.etrepassregister);
        etusername = findViewById(R.id.etusernameregester);

        btnregister = (Button) findViewById(R.id.btnregister);
        btncannel = findViewById(R.id.btncannel);


        mSocket.connect();
        mSocket.on("Register",onRegister);
        btnregister.setOnClickListener(this);
        btncannel.setOnClickListener(this);

    }

    Emitter.Listener onRegister = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String data = args[0].toString();
            if (data == "true") {
               status_register = true;
            }else {
                status_register = false;
            }
        }
    };

    private void register(){
       String name =  etusername.getText().toString().trim();
        String email =  etemailregister.getText().toString().trim();
        String pass =  etpassregister.getText().toString().trim();
        String repass =  etrepassregister.getText().toString().trim();

        if(!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !repass.isEmpty()){
            if(!pass.equals(repass)){
                Log.d("pass", pass );
                Log.d("repass", repass );
                Toast.makeText(getBaseContext(),"Mật khẩu nhập lại không đúng",Toast.LENGTH_LONG).show();
                return;
            }else{
                mSocket.emit( "Register",email,pass,name,null);
            }
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnregister:
                register();
                if (status_register == true){
                    Toast.makeText(getBaseContext(),"Đăng kí thành công",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else
                    Toast.makeText(getBaseContext(),"Email đã tồn tại",Toast.LENGTH_LONG).show();
                break;
            case R.id.btncannel:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;
        }
    }
}
