package tracker.app.fursa.moneytracker.db;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.List;

import tracker.app.fursa.moneytracker.data.Product;

/**
 * Created by Ilya Fursa on 24.04.2017.
 */

public interface DatabaseCRUD {
    void create(Product product);

    boolean remove(Product product);

    boolean update(Product old, Product newProduct);

    List<Product> selectAll();

    int totalSum();
}
