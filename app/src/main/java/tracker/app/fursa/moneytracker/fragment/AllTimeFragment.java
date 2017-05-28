package tracker.app.fursa.moneytracker.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tracker.app.fursa.moneytracker.R;
import tracker.app.fursa.moneytracker.db.DatabaseManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTimeFragment extends Fragment {
    private static final String TOTAL_MEDICINE = "Медицина";
    private static final String TOTAL_SERVICE = "ЖКХ";
    private static final String TOTAL_ENTERTAINMENT = "Развлечения";
    private static final String TOTAL_PRODUCTS = "Продукты";
    private static final String TOTAL_TRANSPORT = "Транспорт";
    private static final String TOTAL_EDUCATION = "Учеба";
    private static final String TOTAL_OTHER = "Другие";

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
        TextView mTextViewTotalMedicine = (TextView) view.findViewById(R.id.mTextViewTotalMedicine);
        TextView mTextViewTotalService = (TextView) view.findViewById(R.id.mTextViewTotalService);
        TextView mTextViewTotalEntertainment = (TextView) view.findViewById(R.id.mTextViewTotalEntertainment);
        TextView mTextViewTotalProducts = (TextView) view.findViewById(R.id.mTextViewTotalProducts);
        TextView mTextViewTotalTransport = (TextView) view.findViewById(R.id.mTextViewTotalTransport);
        TextView mTextViewTotalEducation = (TextView) view.findViewById(R.id.mTextViewTotalEducation);
        TextView mTextViewTotalOther = (TextView) view.findViewById(R.id.mTextViewTotalOther);


        mTextViewTotalSum.setText(String.valueOf(DatabaseManager.getInstance(getActivity()).totalSum()) + " " + getResources().getString(R.string.rub));
        mTextViewTotalMedicine.setText(DatabaseManager.getInstance(getActivity()).getTotalByServiceTitle(TOTAL_MEDICINE) + " " + getResources().getString(R.string.rub));
        mTextViewTotalService.setText(DatabaseManager.getInstance(getActivity()).getTotalByServiceTitle(TOTAL_SERVICE) + " " + getResources().getString(R.string.rub));
        mTextViewTotalEntertainment.setText(DatabaseManager.getInstance(getActivity()).getTotalByServiceTitle(TOTAL_ENTERTAINMENT) + " " + getResources().getString(R.string.rub));
        mTextViewTotalProducts.setText(DatabaseManager.getInstance(getActivity()).getTotalByServiceTitle(TOTAL_PRODUCTS) + " " + getResources().getString(R.string.rub));
        mTextViewTotalTransport.setText(DatabaseManager.getInstance(getActivity()).getTotalByServiceTitle(TOTAL_TRANSPORT) + " " + getResources().getString(R.string.rub));
        mTextViewTotalEducation.setText(DatabaseManager.getInstance(getActivity()).getTotalByServiceTitle(TOTAL_EDUCATION) + " " + getResources().getString(R.string.rub));
        mTextViewTotalOther.setText(DatabaseManager.getInstance(getActivity()).getTotalByServiceTitle(TOTAL_OTHER) + " " + getResources().getString(R.string.rub));

        return view;
    }
}
