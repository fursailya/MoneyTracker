package tracker.app.fursa.moneytracker.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import tracker.app.fursa.moneytracker.R;
import tracker.app.fursa.moneytracker.db.DatabaseManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTimeFragment extends Fragment {
    public static AllTimeFragment newInstance() {
        Bundle args = new Bundle();
        AllTimeFragment fragment = new AllTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_all_time, container, false);
        TextView mTextViewTotalSum = (TextView) view.findViewById(R.id.mTextViewTotalSum);
        mTextViewTotalSum.setText(String.valueOf(DatabaseManager.getInstance(getActivity()).totalSum()) + " " + getResources().getString(R.string.rub));
        return view;
    }
}
