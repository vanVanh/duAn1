package vn.edu.poly.project_android_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.activity.ProductDetailActivity;
import vn.edu.poly.project_android_1.object.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.FoodViewHolder>  {

    private ArrayList<Product> list;
    private Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void getData(ArrayList<Product> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycelview_food, parent , false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Product obj = list.get(position);
        if (obj == null){
            return;
        }

        Picasso.get().load(obj.getImageFood()).
                placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.img);
        holder.name.setText(obj.getName());
        holder.name.setMaxLines(1);
        holder.name.setEllipsize(TextUtils.TruncateAt.END);
        DecimalFormat format = new DecimalFormat("###,###,###");
        holder.price.setText(format.format(obj.getPrice())+" vnđ");

        // click chi tiết sản phẩm
        holder.product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                intent.putExtra("postion",list.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name, price;
        private LinearLayout product;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_food);
            name = itemView.findViewById(R.id.name_food);
            price = itemView.findViewById(R.id.price_food);
            product = itemView.findViewById(R.id.item_product);
        }
    }
}
