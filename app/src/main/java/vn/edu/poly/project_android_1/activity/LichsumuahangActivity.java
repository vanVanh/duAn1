package vn.edu.poly.project_android_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import vn.edu.poly.project_android_1.DAO.BillDao;
import vn.edu.poly.project_android_1.DAO.UserDao;
import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.adapter.BillAdrapter;
import vn.edu.poly.project_android_1.until.Server;

public class LichsumuahangActivity extends AppCompatActivity {

    private ListView lvLichmuahang;
    private BillAdrapter billAdrapter;
    private BillDao billDao;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsumuahang);
        //anh xa---
        lvLichmuahang = findViewById(R.id.lv_lichsumuahang);
        toolbar = findViewById(R.id.toolbar_lichsumuahang);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int id_user = getIntent().getIntExtra("id",0);
        Toast.makeText(this, ""+id_user, Toast.LENGTH_SHORT).show();

        billAdrapter = new BillAdrapter(this);
        billDao = new BillDao();
        billDao.getBill(this, Server.linkSelectBill,id_user,billAdrapter);
        lvLichmuahang.setAdapter(billAdrapter);
    }

}