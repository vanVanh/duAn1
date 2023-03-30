package vn.edu.poly.project_android_1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.project_android_1.DAO.UserDao;
import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.activity.DoiMatKhauActivity;
import vn.edu.poly.project_android_1.activity.GioiThieuActivity;
import vn.edu.poly.project_android_1.activity.InformationDetailActivity;
import vn.edu.poly.project_android_1.activity.LichsumuahangActivity;
import vn.edu.poly.project_android_1.activity.LoginActivity;
import vn.edu.poly.project_android_1.activity.UpdateAddressActivity;
import vn.edu.poly.project_android_1.until.CheckConnection;
import vn.edu.poly.project_android_1.until.Server;


public class UserFragment extends Fragment {

    private Button btn_logout;
    private TextView tvThongTinChiTiet, tvDoiMatKhau, tvDiaChi, tvLichSuMuaHang, tvGioiThieu;
    private UserDao userDao;

    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);

        // log out
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Do you want to logout app?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finishAffinity();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
                builder.show();
            }
        });

        // thông tin chi tiết
        tvThongTinChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InformationDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });

        tvDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DoiMatKhauActivity.class);
                getActivity().startActivity(intent);
            }
        });
        tvDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateAddressActivity.class);
                getActivity().startActivity(intent);
            }
        });
        //lich sử mua hàng
        tvLichSuMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.getID(getActivity(), Server.linkselectAccount,LoginActivity.name);//nhap dung se getid = dao.index;
                Intent intent = new Intent(getActivity(), LichsumuahangActivity.class);
                intent.putExtra("id",UserDao.idAccount);
                getActivity().startActivity(intent);
            }
        });
        //ac giới thiệu
        tvGioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GioiThieuActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    private void anhXa(View view){
        btn_logout = view.findViewById(R.id.btn_logout);
        tvThongTinChiTiet = view.findViewById(R.id.tvThongTinChiTiet);
        tvDoiMatKhau = view.findViewById(R.id.tvDoiMatKhau);
        tvDiaChi = view.findViewById(R.id.tvDiaChi);
        tvLichSuMuaHang = view.findViewById(R.id.tvLichSuMuaHang);
        tvGioiThieu = view.findViewById(R.id.tvGioiThieu);
        userDao = new UserDao();
    }


}