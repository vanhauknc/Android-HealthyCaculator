package com.example.healthcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin,btnDk;
    private EditText edtUsername,edtPass;

    public static final String fnUsername="";
    UserDB userDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        setEvent();



    }

    private void setControl(){

        btnLogin = findViewById(R.id.btnlogin);
        btnDk = findViewById(R.id.btndk);
        edtUsername=findViewById(R.id.edtuser2);
        edtPass=findViewById(R.id.edtpass2);

    }

    private void setEvent(){
        userDB = new UserDB(this);
        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Indangki = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(Indangki);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (edtUsername.getText().toString().trim().equals("")) {
                        edtUsername.setError("Bạn phải nhập username !");
                        edtUsername.requestFocus();
                        return;
                    } else {
                        if (edtUsername.getText().toString().trim().length() < 4) {
                            edtUsername.setError("Username phải có ít nhất 4 kí tự !");
                            edtUsername.requestFocus();
                            return;
                        }
                    }


                    if (edtPass.getText().toString().trim().equals("")) {
                        edtPass.setError("Bạn phải nhập password !");
                        edtPass.requestFocus();
                        return;
                    } else {

                        if (edtPass.getText().toString().trim().length() < 4) {
                            edtPass.setError("PassWord phải có ít nhất 4 kí tự !");
                            edtPass.requestFocus();
                            return;
                        }

                    }

                    User us = new User();

                    us.setUsername(edtUsername.getText().toString().trim());
                    us.setPassword(edtPass.getText().toString().trim());

                   if (userDB.checkuser(us)){

                       Toast.makeText(getApplicationContext(),"Đăng nhập thành công !",Toast.LENGTH_LONG).show();
                       Intent Indangki = new Intent(LoginActivity.this,MainActivity.class);
                       Indangki.putExtra(fnUsername,us.getUsername());
                       startActivity(Indangki);

                   }else {
                       Toast.makeText(getApplicationContext(),"Sai tài khoản hoặc mật khẩu ! Vui lòng kiểm tra lại.",Toast.LENGTH_LONG).show();
                   }



                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                }

                }

        });




    }




}

