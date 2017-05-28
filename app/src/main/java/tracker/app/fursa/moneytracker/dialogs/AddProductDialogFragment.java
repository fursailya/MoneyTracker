package tracker.app.fursa.moneytracker.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import tracker.app.fursa.moneytracker.R;
import tracker.app.fursa.moneytracker.adapter.RecyclerViewAdapter;
import tracker.app.fursa.moneytracker.data.Product;
import tracker.app.fursa.moneytracker.db.DatabaseManager;

/**
 * Created by Ilya Fursa on 28.04.2017.
 */

public class AddProductDialogFragment extends DialogFragment {

    public static AddProductDialogFragment newInstance() {
        Bundle args = new Bundle();
        AddProductDialogFragment fragment = new AddProductDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = inflater.inflate(R.layout.add_product_dialog_fragment, null);

        final EditText mEditTextProduct = (EditText) view.findViewById(R.id.mEditTextProduct);
        final EditText mEditTextPrice = (EditText) view.findViewById(R.id.mEditTextPrice);
        final Spinner mTypeSpinner = (Spinner) view.findViewById(R.id.mTypeSpinner);

        builder.setView(view);
        builder.setTitle(R.string.title_new_product);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Product product = new Product();
                product.setTitle(mEditTextProduct.getText().toString());
                product.setPrice(Integer.parseInt(mEditTextPrice.getText().toString()));
                product.setType(mTypeSpinner.getSelectedItem().toString());
                DatabaseManager.getInstance(getActivity()).create(product);
                new RecyclerViewAdapter().updateData(DatabaseManager.getInstance(getActivity()).selectAll());
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        return builder.create();

    }
}
