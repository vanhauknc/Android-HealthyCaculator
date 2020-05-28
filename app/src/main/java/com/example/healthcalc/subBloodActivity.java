package com.example.healthcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class subBloodActivity extends AppCompatActivity {

    Spinner spGioitinh;
    EditText edtChieucao,edtCannang;
    Button btnTinhtoan,btnReset;
    TextView txtBlood,txtNote;
    ImageView imgTt;

    ArrayList listgt = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_blood);
        setControl();
        setEvent();

    }

    public void setControl(){
        spGioitinh= findViewById(R.id.bloodspGioitinh);
        edtChieucao=findViewById(R.id.bloodChieucao);
        edtCannang= findViewById(R.id.bloodCannang);
        btnTinhtoan = findViewById(R.id.bloodTinhtoan);
        btnReset=findViewById(R.id.bloodReset);
        txtBlood=findViewById(R.id.txtblood);
        txtNote=findViewById(R.id.txtnoteblood);
        imgTt=findViewById(R.id.imgTtblood);

    }
    public void  setEvent(){

        listgt.add("Nam");
        listgt.add("Nữ");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listgt);
        spGioitinh.setAdapter(adapter);

        btnTinhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(edtChieucao.getText().toString().equals("")){
                    edtChieucao.setError("Bạn phải nhập trường này");
                    edtChieucao.requestFocus();
                    return;
                }
                else{
                    if(Integer.parseInt(edtChieucao.getText().toString()) < 1){
                        edtChieucao.setError("Chiều cao không được nhỏ hơn 1");
                        edtChieucao.requestFocus();
                        return;
                    }
                }


                if(edtCannang.getText().toString().equals("")){
                    edtCannang.setError("Bạn phải nhập trường ngày");
                    edtCannang.requestFocus();
                    return;
                }
                else{
                    if(Integer.parseInt(edtCannang.getText().toString()) < 1){
                        edtCannang.setError("Cân nặng không được nhỏ hơn 1");
                        edtCannang.requestFocus();
                        return;
                    }
                }

                int cannang = Integer.parseInt(edtCannang.getText().toString() );
                double thetich;
                if(spGioitinh.getSelectedItem().toString().equals("Nữ")){

                     thetich = cannang*66/1000.0;

                }else {
                    thetich = cannang*71/1000.0;
                }
               double FNthetich = Math.round(thetich*100.0)/100.0;
                txtBlood.setText("Tổng khối lượng máu của bạn là : "+FNthetich+" lít.");
                imgTt.setImageResource(R.drawable.thetichmau);


            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtCannang.setText("");
                edtChieucao.setText("");
                txtNote.setText("");
                imgTt.setImageResource(R.drawable.trang);
            }
        });

    }
}
