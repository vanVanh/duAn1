package vn.edu.poly.project_android_1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager2 = findViewById(R.id.viewpager2);
        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager2.setAdapter(viewPagerAdapter);

        viewPager2.setUserInputEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.item_home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.item_cart:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.item_favourite:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.item_search:
                        viewPager2.setCurrentItem(3);
                        break;
                    case R.id.item_user:
                        viewPager2.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}