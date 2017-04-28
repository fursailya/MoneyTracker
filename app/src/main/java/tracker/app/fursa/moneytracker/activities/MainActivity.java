package tracker.app.fursa.moneytracker.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import tracker.app.fursa.moneytracker.R;
import tracker.app.fursa.moneytracker.dialogs.AddProductDialogFragment;
import tracker.app.fursa.moneytracker.fragment.AllTimeFragment;
import tracker.app.fursa.moneytracker.fragment.TodayFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final java.lang.String DIALOG_TAG = "AddProductDialogFragment";

    private FragmentManager fragmentManager;
    private FloatingActionButton mFab;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_today:
                    fragmentManager.beginTransaction().replace(R.id.content, TodayFragment.newInstance()).commit();
                    return true;
                case R.id.navigation_all_time:
                    fragmentManager.beginTransaction().replace(R.id.content, AllTimeFragment.newInstance()).commit();
                    mFab.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, TodayFragment.newInstance()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AddProductDialogFragment addProductDialogFragment = new AddProductDialogFragment();
        addProductDialogFragment.show(getFragmentManager(), DIALOG_TAG);
    }




}
