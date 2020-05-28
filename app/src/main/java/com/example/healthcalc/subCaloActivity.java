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

public class subCaloActivity extends AppCompatActivity {

    Spinner spGioitinh,spHoatdong;
    EditText edtTuoi,edtChieucao,edtCannang;
    Button btnTinhtoan,btnReset;
    TextView txtCalo,txtNote;
    ImageView imgTt;

    ArrayList listgt = new ArrayList();
    ArrayList listhoatdot = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_calo);
        setControl();
        setEvent();
    }

    public void setControl(){
        spGioitinh=findViewById(R.id.calospGioitinh);
        spHoatdong=findViewById(R.id.calospHoatdong);
        edtTuoi=findViewById(R.id.caloTuoi);
        edtChieucao=findViewById(R.id.caloChieucao);
        edtCannang=findViewById(R.id.caloCannang);
        btnTinhtoan=findViewById(R.id.caloTinhtoan);
        btnReset=findViewById(R.id.caloReset);
        txtCalo=findViewById(R.id.txtcalo);
        txtNote=findViewById(R.id.txtnotecalo);
        imgTt=findViewById(R.id.imgTtcalo);



    }

    public void setEvent(){

        listgt.add("Nam");
        listgt.add("Nữ");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listgt);
        spGioitinh.setAdapter(adapter);

        listhoatdot.add("Ít vận động");
        listhoatdot.add("Vừa phải");
        listhoatdot.add("Chăm chỉ");
        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listhoatdot);
        spHoatdong.setAdapter(adapter2);


        btnTinhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtTuoi.getText().toString().equals("")){
                    edtTuoi.setError("Bạn phải nhập trường này");
                    edtTuoi.requestFocus();
                    return;
                }else{
                    if(Integer.parseInt(edtTuoi.getText().toString()) < 1){
                        edtTuoi.setError("Tuổi không được bé hơn 1");
                        edtTuoi.requestFocus();
                        return;
                    }
                }

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

                int cannang = Integer.parseInt(edtCannang.getText().toString());
                int chieucao = Integer.parseInt(edtChieucao.getText().toString());
                int tuoi = Integer.parseInt(edtTuoi.getText().toString());
                double FNBMR;
                if(spGioitinh.getSelectedItem().toString().equals("Nữ")){
                    double BMR = 55.1 + 9.563*cannang + 1.850*chieucao - 4.676*tuoi;
                    FNBMR=BMR;
                }else {
                    double BMR = 66.5 + 13.75*cannang + 5.003*chieucao - 6.755*tuoi;
                    FNBMR=BMR;
                }

                if (spHoatdong.getSelectedItem().toString().equals("Ít vận động")){
                    FNBMR =FNBMR*1.2;
                }
                if(spHoatdong.getSelectedItem().toString().equals("Vừa phải")){
                    FNBMR=FNBMR*1.375;
                }
                if(spHoatdong.getSelectedItem().toString().equals("Chăm chỉ")){
                    FNBMR=FNBMR*1.55;
                }
                FNBMR = Math.round(FNBMR*10.0)/10.0;
           //     Toast.makeText(getApplicationContext(),FNBMR+"",Toast.LENGTH_LONG).show();

                txtCalo.setText("Ước tính với nhu cầu calo hàng ngày của bạn : "+FNBMR+" calo");
                txtNote.setText("Số calo này sẽ là số lượng calo bạn có thể ăn hàng ngày và duy trì trọng lượng hiện tại." +
                        "Để giảm cân,bạn sẽ cần phải nạp vào ít calo hoặc phải đốt cháy nhiều calo hơn so với kết quả này." +
                        "Tăng cân, bạn sẽ phải thực hiện nạp nhiều calo hơn so với kết quả này");



            }


        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTuoi.setText("");
                edtCannang.setText("");
                edtChieucao.setText("");
                txtNote.setText("");
                txtCalo.setText("");

            }
        });



    }
}
