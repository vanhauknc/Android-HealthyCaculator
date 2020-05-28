package com.example.healthcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText userName,passWord,rePassWord;
    private Button btnDangki;
    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setControl();
        setEvent();
        
    }
    
    private void setControl(){
        btnDangki = findViewById(R.id.btndangki);
        userName = findViewById(R.id.edtuser);
        passWord=findViewById(R.id.edtpass);
        rePassWord=findViewById(R.id.edtrepass);
    }
    
    private void setEvent(){
        userDB = new UserDB(this);
        
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().trim().equals("")){
                    userName.setError("Bạn phải nhập username !");
                    userName.requestFocus();
                    return;
                }else {
                    if(userName.getText().toString().trim().length()<4){
                        userName.setError("Username phải có ít nhất 4 kí tự !");
                        userName.requestFocus();
                        return;
                    }
                }
                
                
                if(passWord.getText().toString().trim().equals("")){
                    passWord.setError("Bạn phải nhập password !");
                    passWord.requestFocus();
                    return;
                }else {

                    if(passWord.getText().toString().trim().length()<4){
                        passWord.setError("passWord phải có ít nhất 4 kí tự !");
                        passWord.requestFocus();
                        return;
                    }
                    
                }
                
                
                if(rePassWord.getText().toString().trim().equals("")){
                    rePassWord.setError("Bạn phải nhập Confirm Password !");
                    rePassWord.requestFocus();
                    return;
                }else {
                    if(rePassWord.getText().toString().trim().length()<4){
                        rePassWord.setError("passWord phải có ít nhất 4 kí tự !");
                        rePassWord.requestFocus();
                        return;
                    }
                    
                }
                
                if(!passWord.getText().toString().trim().equals(rePassWord.getText().toString().trim())){
                    rePassWord.setError("Confirm Password không chính xác !");
                    rePassWord.requestFocus();
                    return;
                }
                
                User us = new User();
                us.setUsername(userName.getText().toString().trim());
                us.setPassword(passWord.getText().toString().trim());
                if(userDB.them(us) != -1){
                    Toast.makeText(getApplicationContext(),"Tạo tài khoản thành công !",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Tạo tài khoản thất bại !",Toast.LENGTH_LONG).show();
                }
                
                
                
            }
        });
        
    }
    
    
}
