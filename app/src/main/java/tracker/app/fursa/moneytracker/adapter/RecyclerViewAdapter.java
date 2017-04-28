package tracker.app.fursa.moneytracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tracker.app.fursa.moneytracker.R;
import tracker.app.fursa.moneytracker.data.Product;

/**
 * Created by Ilya Fursa on 24.04.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static List<Product> mProductList;

    public RecyclerViewAdapter(List<Product> mProductList) {
        this.mProductList = mProductList;
    }

    public RecyclerViewAdapter() {
    }

    public void updateData(List<Product> products) {
        mProductList.clear();
        mProductList.addAll(products);
        notifyDataSetChanged();
    }

//    public void addItem(int position, Product product) {
//        mProductList.add(position, product);
//        notifyItemInserted(position);
//    }
//
//    public void removeItem(int position) {
//        mProductList.remove(position);
//        notifyItemRemoved(position);
//    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProductList.get(position);
        holder.mTextViewDate.setText(product.getDate());
        holder.mTextViewProduct.setText(product.getTitle());
        holder.mTextViewPrice.setText(String.valueOf(product.getPrice()));

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewProduct;
        TextView mTextViewPrice;
        TextView mTextViewDate;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextViewProduct = (TextView) itemView.findViewById(R.id.mTextViewProductName);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.mTextViewPrice);
            mTextViewDate = (TextView) itemView.findViewById(R.id.mTextViewDate);
        }
    }
}