package com.example.healthcalc;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;


public class subBmiActivity extends AppCompatActivity {

    private NotificationHelper mNotificationHelper;
    Spinner spGioitinh;
    EditText edtTuoi,edtChieucao,edtCannang;
    Button btnTinhtoan,btnReset;
    TextView txtBmi,txtNote;
    ImageView imgTt;

    ArrayList listgt = new ArrayList();
    MediaStore.Audio audio;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_bmi);

        setControl();
        setEvent();
    }


    private void setControl(){
        spGioitinh = findViewById(R.id.spGioitinh);
        edtTuoi=findViewById(R.id.bmiTuoi);
        edtCannang=findViewById(R.id.bmiCannang);
        edtChieucao=findViewById(R.id.bmiChieucao);
        btnTinhtoan=findViewById(R.id.bmiTinhtoan);
        btnReset=findViewById(R.id.bmiReset);
        txtBmi=findViewById(R.id.txtbmi);
        txtNote=findViewById(R.id.txtnote);
        imgTt=findViewById(R.id.imgTt);


    }
    private void setEvent(){

        mNotificationHelper = new NotificationHelper(this);
        listgt.add("Nam");
        listgt.add("Nữ");
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listgt);
        spGioitinh.setAdapter(adapter);

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


                String test = edtChieucao.getText().toString();
                test = test.substring(0,1)+"."+test.substring(1,test.length());

                double chieucao = Double.parseDouble(test);
                int cannang = Integer.parseInt(edtCannang.getText().toString() );
                double bmi = cannang/(chieucao*chieucao);
               double fnbmi =  Math.round(bmi*10.0)/10.0;


               txtBmi.setText("Chỉ số BMI của bạn : "+fnbmi);
               String tinhtrang="";

               //nam
                 int flag=0;
               if(fnbmi<18.5){
                   tinhtrang = "Tình trạng cơ thể của bạn đang gầy hơn so với mức tiêu chuẩn - cơ thể đang thiếu chất bạn cần tăng cân để đảm bảo sức khỏe !";
                   imgTt.setImageResource(R.drawable.gay);

                    flag=1;

                   //gay
                   //nguy co phat trieu benh thap
               }
               if(fnbmi>18.5 && fnbmi<24.9){
                   tinhtrang = "Tình trạng cân nặng của bạn đang ở mức bình thường, tiếp tục duy trì nhé !";
                   imgTt.setImageResource(R.drawable.binhthuong);
                   flag=2;
                   //binh thuong
                   //nguy co phat trien benh binh thuong
               }
               if(fnbmi>25){

                   tinhtrang = "Opps ! Bạn đang ở trong tình trạng Béo , Hãy giảm cân ngay để giảm nguy cơ mắc các bệnh liên quan đến bèo phì !";
                   imgTt.setImageResource(R.drawable.beo);
                   flag=3;
                   //beo
                   //cao
               }

               // nu
                //19-24

                if (spGioitinh.getSelectedItem().toString().equals("Nữ")){

                    if(fnbmi<19){
                        tinhtrang = "Tình trạng cơ thể của bạn đang gầy hơn so với mức tiêu chuẩn - cơ thê đang thiếu chất bạn cần tăng cân để đảm bảo sức khỏe !";
                        imgTt.setImageResource(R.drawable.gay);
                        //gay
                        //nguy co phat trieu benh thap
                        flag=1;
                    }
                    if(fnbmi>19 && fnbmi<24){
                        tinhtrang = "Tình trạng cân nặng của bạn đang ở mức bình thường, tiếp tục duy trì nhé !";
                        imgTt.setImageResource(R.drawable.binhthuong);
                        //binh thuong
                        //nguy co phat trien benh binh thuong
                        flag=2;
                    }
                    if(fnbmi>24){
                        //beo
                        //cao
                        tinhtrang = "Opps ! Bạn đang ở trong tình trạng Béo , Hãy giảm cân ngay để giảm nguy cơ mắc các bệnh liên quan đến bèo phì !";
                        imgTt.setImageResource(R.drawable.beo);
                        flag=3;
                    }

                }


                try{
                    if(flag==1){
                        MediaPlayer mPlayer = MediaPlayer.create(subBmiActivity.this, R.raw.gayy);
                        mPlayer.start();
                    }
                    if(flag==2){
                        MediaPlayer mPlayer = MediaPlayer.create(subBmiActivity.this, R.raw.binhthuong);
                        mPlayer.start();
                    }
                    if(flag==3){
                        MediaPlayer mPlayer = MediaPlayer.create(subBmiActivity.this, R.raw.beoo);
                        mPlayer.start();
                    }

                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),ex+"",Toast.LENGTH_LONG).show();
                }

                txtNote.setText(tinhtrang);





               // Toast.makeText(getApplicationContext(),fnbmi+"|"+tinhtrang,Toast.LENGTH_LONG).show();


            }
        });





        //btn Reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {



                edtTuoi.setText("");
                edtCannang.setText("");
                edtChieucao.setText("");
                txtNote.setText("");
                txtBmi.setText("");
                imgTt.setImageResource(R.drawable.trang);



            }
        });






    }





}





