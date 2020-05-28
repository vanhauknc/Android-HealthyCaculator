package com.example.healthcalc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountActivity extends AppCompatActivity {

    EditText edtUser,edtNewpass,edtOldpass,edtRepass;
    Button btnDoimk,btnXoatk;
    UserDB userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setControl();
        setEvent();

    }

    public void setControl(){

        edtUser = findViewById(R.id.ssuser);
        edtOldpass = findViewById(R.id.ssmkcu);
        edtNewpass = findViewById(R.id.ssmkmoi);
        edtRepass=findViewById(R.id.ssremk);
        btnDoimk=findViewById(R.id.btndoimk);
        btnXoatk=findViewById(R.id.btnxoatk);

    }
    public String nhan="";
    public  void setEvent(){
        userDB = new UserDB(this);
        Intent intent = getIntent();
        nhan = intent.getStringExtra(MainActivity.FnUser);
        edtUser.setText(nhan);



        btnDoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!edtNewpass.getText().toString().trim().equals(edtRepass.getText().toString().trim())){
                    edtRepass.setError("Confirm Password không chính xác !");
                    edtRepass.requestFocus();
                    return;
                }

                if(edtOldpass.getText().toString().equals("")){
                    edtOldpass.setError("Bạn phải nhập Password");
                    edtOldpass.requestFocus();
                    return;
                }else {
                    if (edtOldpass.getText().toString().trim().length() < 4) {
                        edtOldpass.setError("Password phải có ít nhất 4 kí tự !");
                        edtOldpass.requestFocus();
                        return;
                    }
                }



                if(edtNewpass.getText().toString().equals("")){
                    edtNewpass.setError("Bạn phải nhập trường này");
                    edtNewpass.requestFocus();
                    return;
                }else {
                    if (edtNewpass.getText().toString().trim().length() < 4) {
                        edtNewpass.setError("Password phải có ít nhất 4 kí tự !");
                        edtNewpass.requestFocus();
                        return;
                    }

                }

                if(edtRepass.getText().toString().equals("")){
                    edtRepass.setError("Bạn phải nhập trường này");
                    edtRepass.requestFocus();
                    return;
                }else {
                    if (edtRepass.getText().toString().trim().length() < 4) {
                        edtRepass.setError("Password phải có ít nhất 4 kí tự !");
                        edtRepass.requestFocus();
                        return;
                    }
                }

                User us = new User();
                us.setUsername(nhan);
                us.setPassword(edtOldpass.getText().toString().trim());

                if (userDB.checkuser(us)) {


                    us.setPassword(edtNewpass.getText().toString().trim());
               //     Toast.makeText(getApplicationContext(),ua.getUsername()+""+ua.getPassword(),Toast.LENGTH_LONG).show();
                try {

                    if (userDB.doimatkhau(us) != -1) {
                        Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công !", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Đổi mật khẩu thất bại !", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }
                }else {
                    Toast.makeText(getApplicationContext(),"Mật khẩu cũ không chính xác",Toast.LENGTH_LONG).show();
                    edtOldpass.requestFocus();
                }


            }
        });

        btnXoatk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
                builder.setTitle("Thông báo ");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            User us = new User();
                            us.setUsername(nhan);
                            userDB.xoatk(us);
                            Toast.makeText(getApplicationContext(),"Xóa thành công !",Toast.LENGTH_LONG).show();
                            Thread.sleep(2000);
                            Intent Indangki = new Intent(AccountActivity.this,LoginActivity.class);
                            startActivity(Indangki);
                        }catch (Exception ex){
                            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });

    }
}
