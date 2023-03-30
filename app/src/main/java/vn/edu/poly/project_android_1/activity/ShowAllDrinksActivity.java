package vn.edu.poly.project_android_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.adapter.AllProductAdapter;
import vn.edu.poly.project_android_1.adapter.ProductAdapter;
import vn.edu.poly.project_android_1.DAO.FoodDao;
import vn.edu.poly.project_android_1.until.Server;

public class ShowAllDrinksActivity extends AppCompatActivity {

    private AllProductAdapter adapter;
    private FoodDao dao;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_drinks);

        toolbar = findViewById(R.id.toolbar_showalldrinks);
        recyclerView = findViewById(R.id.recycelview_showalldrinks);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(" All drinks");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dao = new FoodDao();
        adapter = new AllProductAdapter(this);
        dao.getall(this, Server.linkgetDoUong, adapter);
        GridLayoutManager managerdrinks = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(managerdrinks);
        recyclerView.setAdapter(adapter);


    }
}