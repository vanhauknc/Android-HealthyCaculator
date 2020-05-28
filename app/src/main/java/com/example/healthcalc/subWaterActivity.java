package com.example.healthcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class subWaterActivity extends AppCompatActivity {

    EditText edtCannang;
    Button btnTinhtoan,btnReset;
    TextView txtWater,txtNote;
    ImageView imgWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_water);
        setControl();
        setEvent();
    }

    private void setControl(){
        edtCannang=findViewById(R.id.waterCannang);
        btnTinhtoan=findViewById(R.id.waterTinhtoan);
        btnReset=findViewById(R.id.waterReset);
        txtWater=findViewById(R.id.txtwater);
        txtNote=findViewById(R.id.txtnotewater);
        imgWater=findViewById(R.id.imgTtwater);

    }
    private void setEvent(){

        btnTinhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                int cannang = Integer.parseInt(edtCannang.getText().toString());
                double solit = cannang*0.03;

                txtWater.setText("Nhu cầu nước hàng ngày của bạn : "+solit+" lít.");

                txtNote.setText("Tăng thêm 2-3 ly nước nếu bạn tập thể dục hoặc điều kiện môi trường của bạn nóng");
                imgWater.setImageResource(R.drawable.nuoc);

            }

        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCannang.setText("");
                txtNote.setText("");
                txtWater.setText("");
                imgWater.setImageResource(R.drawable.trang);
            }
        });

    }
}
