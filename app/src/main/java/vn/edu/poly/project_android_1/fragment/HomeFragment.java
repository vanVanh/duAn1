package vn.edu.poly.project_android_1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.activity.ShowAllDrinksActivity;
import vn.edu.poly.project_android_1.activity.ShowAllFoodsActivity;
import vn.edu.poly.project_android_1.adapter.ProductAdapter;
import vn.edu.poly.project_android_1.DAO.FoodDao;
import vn.edu.poly.project_android_1.until.Server;

public class HomeFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private RecyclerView food, drinks, bigSale;
    private RelativeLayout showall_food, showall_drinks, showall_bigSale;
    private ProductAdapter adapter;
    private FoodDao dao;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewid(view);
        getViewFLipper();
        getFood();
        getDrinks();

        showall_drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), ShowAllDrinksActivity.class);
                startActivity(intent1);
            }
        });

        showall_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getContext(), ShowAllFoodsActivity.class);
                startActivity(intent2);
            }
        });

    }

    private void findViewid(View view){
        viewFlipper = view.findViewById(R.id.viewflipper);
        food = view.findViewById(R.id.recycelview_food);
        drinks = view.findViewById(R.id.recycelview_drinks);
//        bigSale = view.findViewById(R.id.recycelview_bigsale);
        showall_food = view.findViewById(R.id.showall_food);
        showall_drinks = view.findViewById(R.id.showall_drinks);
//        showall_bigSale = view.findViewById(R.id.showall_bigsale);

        dao = new FoodDao();

    }

    private void getViewFLipper()
    {
        ArrayList<Integer> arrayQuangCao = new ArrayList<>();
        arrayQuangCao.add(R.drawable.garan);
        arrayQuangCao.add(R.drawable.nem);
        arrayQuangCao.add(R.drawable.s2);
        arrayQuangCao.add(R.drawable.trasua);
        arrayQuangCao.add(R.drawable.tratac);



        for (int i=0; i < arrayQuangCao.size();i++)
        {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(arrayQuangCao.get(i));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in);
        Animation animation_out = AnimationUtils.loadAnimation(getContext(),R.anim.slide_out);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }

    private void getFood(){
        adapter = new ProductAdapter(getActivity());
        dao.getFoods(getContext(), Server.linkGetDoAn, adapter);
        LinearLayoutManager managerfood = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        food.setLayoutManager(managerfood);
        food.setAdapter(adapter);

    }

    private void getDrinks(){
        adapter = new ProductAdapter(getActivity());
        dao.getDrinks(getContext(), Server.linkgetDoUong, adapter);
        LinearLayoutManager managerdrinks = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        drinks.setLayoutManager(managerdrinks);
        drinks.setAdapter(adapter);
    }



}