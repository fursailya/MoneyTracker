package tracker.app.fursa.moneytracker.adapter;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tracker.app.fursa.moneytracker.R;
import tracker.app.fursa.moneytracker.data.Product;
import tracker.app.fursa.moneytracker.db.DatabaseManager;

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
        holder.mTextViewType.setText(product.getType());

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView mCardProduct;
        TextView mTextViewProduct;
        TextView mTextViewPrice;
        TextView mTextViewDate;
        TextView mTextViewType;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardProduct = (CardView) itemView.findViewById(R.id.cardProduct);
            mTextViewProduct = (TextView) itemView.findViewById(R.id.mTextViewProductName);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.mTextViewPrice);
            mTextViewDate = (TextView) itemView.findViewById(R.id.mTextViewDate);
            mTextViewType = (TextView) itemView.findViewById(R.id.mTextViewType);

            mCardProduct.setOnClickListener(this);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                StateListAnimator stateListAnimator = AnimatorInflater
                        .loadStateListAnimator(itemView.getContext(), R.drawable.touch);
                mCardProduct.setStateListAnimator(stateListAnimator);
            }
        }

        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Удалить?!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Да", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Product product = new Product();
                            product.setTitle(mTextViewProduct.getText().toString());
                            product.setPrice(Integer.parseInt(mTextViewPrice.getText().toString()));
                            DatabaseManager.getInstance(view.getContext()).remove(product, view.getContext());
                            mProductList.remove(getAdapterPosition());
                            notifyDataSetChanged();

                        }
                    }).show();


        }
    }
}
