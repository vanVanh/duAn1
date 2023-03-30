package vn.edu.poly.project_android_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.poly.project_android_1.DAO.BillDao;
import vn.edu.poly.project_android_1.DAO.UserDao;
import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.adapter.BillAdrapter;
import vn.edu.poly.project_android_1.object.Bill;
import vn.edu.poly.project_android_1.object.Product;
import vn.edu.poly.project_android_1.object.User;
import vn.edu.poly.project_android_1.until.List;
import vn.edu.poly.project_android_1.until.Server;

public class BillActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btn_oder;
    private TextView name_user, location_user, name_product, price_product, tongTienHang, tongThanhToan, giaCuoi;
    private ListView lvProduct;
    int tongTien1DoAn = 0;
    int tongTienThanhToan = 0;
    private BillAdrapter billAdrapter;
    private UserDao userDao;
    private LinearLayout ln_locate;
    private BillDao billDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        anhXa();
        xuLyToolBar();

        //set list view----
        lvProduct.setAdapter(billAdrapter);
        //thông tin bill
        DecimalFormat format = new DecimalFormat("###,###,### vnđ");

        for (int i = 0; i< List.listBill.size(); i++){
            Bill bill  = List.listBill.get(i);
            tongTienThanhToan += bill.getTongTienSoSp();
        }

        String nameUser = UserDao.list.get(UserDao.index).getFullName();
        String phoneNumber = UserDao.list.get(UserDao.index).getPhoneNumber();
        String locate =  UserDao.list.get(UserDao.index).getAddress();

        name_user.setText(nameUser +" | " + phoneNumber);
        location_user.setText(locate);

        tongTienHang.setText(format.format(tongTienThanhToan)+"");
        tongThanhToan.setText(format.format(tongTienThanhToan)+"");
        giaCuoi.setText(format.format(tongTienThanhToan)+"");

        // sự kiện oder
        btn_oder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert lên sql webhost---
                billDao.insertData(BillActivity.this,Server.linkInsertBill);
                Intent intent = new Intent(BillActivity.this, OderSuccessfullyActivity.class);
                startActivity(intent);
            }
        });

        ln_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( BillActivity.this,UpdateAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDatafromBuynow(){

    }

    private void anhXa(){
        toolbar = findViewById(R.id.toolbar_bill);
        btn_oder = findViewById(R.id.btn_oder);
        name_user = findViewById(R.id.bill_name_user);
        location_user = findViewById(R.id.bill_loction);
        tongTienHang = findViewById(R.id.bill_tongtienhang);
        tongThanhToan = findViewById(R.id.bill_tv_tongthanhtoan);
        giaCuoi = findViewById(R.id.bill_end);
        lvProduct = findViewById(R.id.bill_lv_product);
        ln_locate = findViewById(R.id.id_locate);
        billAdrapter = new BillAdrapter(getApplicationContext());
        userDao = new UserDao();
        userDao.getData(this, Server.linkselectAccount);
        billDao = new BillDao();
    }

    private void xuLyToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(" Bill");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}