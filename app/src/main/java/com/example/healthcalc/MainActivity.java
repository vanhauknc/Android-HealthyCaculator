package com.example.healthcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String FnUser="";
    ListView lsv ;
    ArrayList <Danhsach> listds = new ArrayList<>();
    int flags[] = {R.drawable.icons8_heart_with_pulse_50px,R.drawable.icons8_exercise_50px,R.drawable.icons8_blood_drawing_50px,R.drawable.icons8_water_50px,R.drawable.icons8_standing_man_50px};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        FnUser = intent.getStringExtra(LoginActivity.fnUsername);
        setControl();
        setEvent();
    }
    private void setControl(){
        lsv=findViewById(R.id.lsvShow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_test,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent Indangki = new Intent(MainActivity.this,AccountActivity.class);
        Indangki.putExtra(FnUser,FnUser);
        switch (item.getItemId()){

            case R.id.one:

                startActivity(Indangki);

             //   Toast.makeText(getApplicationContext(),"Tìm kiếm",Toast.LENGTH_LONG).show();
                return true;
            case  R.id.two:
                startActivity(Indangki);
             //   Toast.makeText(getApplicationContext(),"Sửa",Toast.LENGTH_LONG).show();
                return  true;
            case  R.id.three:
                startActivity(Indangki);
             //   Toast.makeText(getApplicationContext(),"Xóa",Toast.LENGTH_LONG).show();
                return true;
            case  R.id.thoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo ");
                builder.setMessage("Bạn có muốn thoát không ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startActivity(startMain);
                        finish();
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
        return super.onOptionsItemSelected(item);
    }


    private void setEvent(){
        Danhsach ds = new Danhsach();
        ds.setTitle("Chỉ số khối cơ thể (BMI)");
        ds.setDestription("Chỉ ra trọng lượng phù hợp với chiều cao của bạn");
        listds.add(ds);

        Danhsach ds1 = new Danhsach();
        ds1.setTitle("Chỉ số Calo cần thiết");
        ds1.setDestription("Tính toán các yêu cầu Calo hàng ngày của bạn");
        listds.add(ds1);

        Danhsach ds2 = new Danhsach();
        ds2.setTitle("Thể tích máu");
        ds2.setDestription("Chỉ ra khôi lượng máu trong cơ thể của bạn");
        listds.add(ds2);

        Danhsach ds3 = new Danhsach();
        ds3.setTitle("Uống nước");
        ds3.setDestription("Số lít nước bạn nên uống mỗi ngày");
        listds.add(ds3);

        Danhsach ds4 = new Danhsach();
        ds4.setTitle("Trọng lượng lý tưởng");
        ds4.setDestription("Trọng lượng được cho là lý tưởng so với cân nặng của bạn");
        listds.add(ds4);



        final CustomAdapter adapter3 = new CustomAdapter(this,R.layout.listview,listds,flags);
        lsv.setAdapter(adapter3);


        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_LONG).show();
                if(position==0){
                    // chuyen sang trang chi so bmi
                    Intent Indangki = new Intent(MainActivity.this,subBmiActivity.class);
                    startActivity(Indangki);
                }
                if(position==1){
                    Intent Indangki2 = new Intent(MainActivity.this,subCaloActivity.class);
                    startActivity(Indangki2);
                    // chi so calo can thiet
                }
                if(position==2){

                    Intent Indangki3 = new Intent(MainActivity.this,subBloodActivity.class);
                    startActivity(Indangki3);
                    //the tich mau
                }
                if(position==3){
                    //uong nuoc
                    Intent Indangki4 = new Intent(MainActivity.this,subWaterActivity.class);
                    startActivity(Indangki4);
                }
                if(position==4){
                    //trong luong ly tuong

                    Intent Indangki5 = new Intent(MainActivity.this,subWeightActivity.class);
                    startActivity(Indangki5);
                }
            }
        });



    }





}
