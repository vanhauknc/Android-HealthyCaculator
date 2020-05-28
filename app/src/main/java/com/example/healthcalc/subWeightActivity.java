package com.example.healthcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class subWeightActivity extends AppCompatActivity {
    EditText edtChieucao;
    Button btnTinhToan,btnReset;
    TextView txtWeight,txtNote;
    ImageView imageTtweight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_weight);
        setControl();
        setEvent();
    }

    public void setControl(){
        edtChieucao=findViewById(R.id.weightChieucao);
        btnTinhToan=findViewById(R.id.weightTinhtoan);
        btnReset=findViewById(R.id.weightReset);
        txtWeight=findViewById(R.id.weighttxt);
        txtNote=findViewById(R.id.weighttxtnote);
        imageTtweight=findViewById(R.id.weightImg);
    }

    private void setEvent(){

        btnTinhToan.setOnClickListener(new View.OnClickListener() {
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

                int chieucao = Integer.parseInt(edtChieucao.getText().toString());

                double cannang =50 + 0.75*(chieucao-150);

                txtWeight.setText("Trọng lượng lý tưởng của bạn là : "+cannang +" kg.");

                imageTtweight.setImageResource(R.drawable.cannanglytuong);


            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtChieucao.setText("");
                txtWeight.setText("");
                imageTtweight.setImageResource(R.drawable.trang);
            }
        });
    }
}
