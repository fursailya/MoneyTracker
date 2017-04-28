package tracker.app.fursa.moneytracker.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tracker.app.fursa.moneytracker.R;
import tracker.app.fursa.moneytracker.adapter.RecyclerViewAdapter;
import tracker.app.fursa.moneytracker.data.Product;
import tracker.app.fursa.moneytracker.db.DatabaseManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment {

    public static Fragment newInstance() {
        TodayFragment todayFragment = new TodayFragment();
        Bundle args = new Bundle();
        todayFragment.setArguments(args);
        return todayFragment;
    }

    public TodayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_today, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerViewProducts);
        RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(init());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        final FloatingActionButton floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.VISIBLE);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy >0) {
                    // Scroll Down
                    if (floatingActionButton.isShown()) {
                        floatingActionButton.hide();
                    }
                }
                else if (dy <0) {
                    // Scroll Up
                    if (!floatingActionButton.isShown()) {
                        floatingActionButton.show();
                    }
                }
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private List<Product> init() {
        List<Product> products = DatabaseManager.getInstance(getActivity()).selectAll();
        return products;
    }

}
