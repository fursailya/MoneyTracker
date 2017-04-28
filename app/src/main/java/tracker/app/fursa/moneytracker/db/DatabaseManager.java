package tracker.app.fursa.moneytracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tracker.app.fursa.moneytracker.adapter.RecyclerViewAdapter;
import tracker.app.fursa.moneytracker.application.Utils;
import tracker.app.fursa.moneytracker.data.Product;

/**
 * Created by Ilya Fursa on 27.04.2017.
 */

public class DatabaseManager extends SQLiteOpenHelper implements DatabaseCRUD {

    private static final String DB_NAME = "moneytracker.sqlite";
    private static final String TABLE_NAME = "Products";
    private static final String ROW_ID = "id";
    private static final String ROW_PRODUCT = "product";
    private static final String ROW_DATE = "date";
    private static final int DB_VERSION = 1;
    private static final String ROW_PRICE = "price";

    private static final String LOG_TAG = "DatabaseManager";
    private static final String TOTAL_SUM = "PriceSum";
    //Instance of DatabaseManager class
    private static DatabaseManager instance = null;
    private Calendar calendar;
    private SQLiteDatabase database;

    private DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        database = this.getWritableDatabase();
    }

//    public static void init(Context context) {
//
//    }

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        initTables(sqLiteDatabase);
    }

    private void initTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + ROW_ID + " INTEGER PRIMARY KEY, "
                + ROW_PRODUCT + " TEXT NOT NULL, "
                + ROW_PRICE + " TEXT NOT NULL, "
                + ROW_DATE + " INTEGER NOT NULL);"
        );

        Log.d(LOG_TAG, "CREATE TABLE " + TABLE_NAME + "("
                + ROW_ID + " INTEGER PRIMARY KEY, "
                + ROW_PRODUCT + " TEXT NOT NULL, "
                + ROW_PRICE + " TEXT NOT NULL, "
                + ROW_DATE + " INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        initTables(db);
    }

    @Override
    public void create(Product product) {
        database.beginTransaction();

        ContentValues cv = new ContentValues();
        calendar = Calendar.getInstance();
        cv.put(ROW_PRODUCT, product.getTitle());
        cv.put(ROW_DATE, calendar.getTimeInMillis());
        cv.put(ROW_PRICE, product.getPrice());
        database.insert(TABLE_NAME, null, cv);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    @Override
    public boolean remove(Product product) {
        return false;
    }

    @Override
    public boolean update(Product old, Product newProduct) {
        return false;
    }

    @Override
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int productIndex = cursor.getColumnIndex(ROW_PRODUCT);
            int priceIndex = cursor.getColumnIndex(ROW_PRICE);
            int dateIndex = cursor.getColumnIndex(ROW_DATE);

            do {
                products.add(new Product(
                        cursor.getString(productIndex),
                        cursor.getInt(priceIndex),
                        Utils.getDateAndTime(cursor.getLong(dateIndex))));
            } while (cursor.moveToNext());
        } else {
            cursor.close();
        }

        return products;
    }

    //TODO Not working!! Fix it later!
    @Override
    public int totalSum() {
        Cursor cursor = database.rawQuery("SELECT SUM(" + ROW_PRICE + ") AS PriceSum FROM " + TABLE_NAME + ";", null);
        Log.d(LOG_TAG, "SELECT SUM(" + ROW_PRICE + ") AS " + TOTAL_SUM + " FROM " + TABLE_NAME + ";");
        int sum = 0;
        if(cursor.moveToFirst()) {
            sum = cursor.getInt(cursor.getColumnIndex(TOTAL_SUM));
        }
        return sum;
    }
}
