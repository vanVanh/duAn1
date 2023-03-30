package vn.edu.poly.project_android_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.poly.project_android_1.R;
import vn.edu.poly.project_android_1.activity.ProductDetailActivity;
import vn.edu.poly.project_android_1.object.Product;

public class SearchAdapter  extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;
    private ArrayList<Product> list;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void getData(ArrayList<Product> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_search_product, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Product product = list.get(position);
        if (product == null){
            return;
        }
        Picasso.get().load(product.getImageFood())
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.img);
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice()+"");
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("postion",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView name, price;
        private ConstraintLayout constraintLayout;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            img=  itemView.findViewById(R.id.img_search_product);
            name=  itemView.findViewById(R.id.tv_name_search);
            price=  itemView.findViewById(R.id.tv_price_search);
            constraintLayout=  itemView.findViewById(R.id.item_click_search);
        }
    }

}
