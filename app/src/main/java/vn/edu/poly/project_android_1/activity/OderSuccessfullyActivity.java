package vn.edu.poly.project_android_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import vn.edu.poly.project_android_1.DAO.FoodDao;
import vn.edu.poly.project_android_1.DAO.UserDao;
import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.adapter.AllProductAdapter;
import vn.edu.poly.project_android_1.until.Server;

public class OderSuccessfullyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AllProductAdapter adapter;
    private FoodDao dao;
    private UserDao userDao;
    private Button btn_backtohome,btn_xemdonhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excuted_pay);

        recyclerView = findViewById(R.id.recycelview_success);
        btn_backtohome = findViewById(R.id.btn_backtohome);
        btn_xemdonhang = findViewById(R.id.btn_xemdonhang);

        dao = new FoodDao();
        adapter = new AllProductAdapter(this);
        dao.getall(this, Server.linkselectall, adapter);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        userDao = new UserDao();

        btn_backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OderSuccessfullyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //xem lich su mua hang
        btn_xemdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.getID(OderSuccessfullyActivity.this, Server.linkselectAccount,LoginActivity.name);//nhap dung se getid = dao.index;
                Intent intent = new Intent(OderSuccessfullyActivity.this,LichsumuahangActivity.class);
                intent.putExtra("id",UserDao.idAccount);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OderSuccessfullyActivity.this, MainActivity.class);
        startActivity(intent);
    }
}