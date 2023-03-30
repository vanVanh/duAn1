package vn.edu.poly.project_android_1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import vn.edu.poly.project_android_1.DAO.FoodDao;
import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.adapter.SearchAdapter;
import vn.edu.poly.project_android_1.object.Product;
import vn.edu.poly.project_android_1.until.Server;


public class SearchFragment extends Fragment {

    private EditText ed_search;
    private RecyclerView recyclerView;
    private ArrayList<Product> list = new ArrayList<>();
    private FoodDao dao;
    private SearchAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findviewbyid(view);
        search();
    }

    private void findviewbyid(View view){
        ed_search = view.findViewById(R.id.ed_search);
        recyclerView = view.findViewById(R.id.recycelview_search);
        dao = new FoodDao();
        adapter= new SearchAdapter(getContext());
    }

    private void search(){
        ed_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String value = ed_search.getText().toString().trim();
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    dao.getsearch(getContext(), Server.linksearchproduct,value, adapter);
                    LinearLayoutManager  manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }

                return false;
            }
        });
    }
}