package passbolt.com.passbolt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal on 19/09/2015.
 */
public class Database extends SQLiteOpenHelper {

    private static final String DEBUGTAG = "VC";
    private static final String CATEGORY_TABLE = "CATEGORY_TABLE";
    private static final String ITEM_TABLE = "ITEM_TABLE";
    private static final String ITEM_INSTANCE_TABLE = "ITEM_INSTANCE_TABLE";

    private static final String CAT_NAME = "CAT_NAME";

    private static final String ITEM_CAT = "ITEM_CAT";
    private static final String ITEM_NAME = "ITEM_NAME";

    private static final String ITEM_INSTANCE = "ITEM_INSTANCE";
    public Database(Context context) {
        super(context, "passbolt.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s VARCHAR PRIMARY KEY)", CATEGORY_TABLE, CAT_NAME);
        db.execSQL(sql);
        sql = String.format("create table %s (%s VARCHAR PRIMARY KEY, %s VARCHAR NOT NULL)", ITEM_TABLE, ITEM_NAME, ITEM_CAT);
        db.execSQL(sql);
        sql = String.format("create table %s (%s VARCHAR PRIMARY KEY, %s VARCHAR NOT NULL)", ITEM_TABLE, ITEM_NAME, ITEM_CAT);
        db.execSQL(sql);

        storeCategory("Social Networks");
        storeCategory("Financial Banks");
        storeCategory("Email Accounts");
        storeCategory("Utilities");

        storeItem("Social Networks", "Facebook");
        storeItem("Social Networks", "Google+");
        storeItem("Social Networks", "LinkedIn");
        storeItem("Social Networks", "Twitter");

        storeItem("Financial Banks", "TD Bank");
        storeItem("Financial Banks", "Scotiabank");
        storeItem("Financial Banks", "CIBC");
        storeItem("Financial Banks", "RBC");

        storeItem("Email Accounts", "Gmail");
        storeItem("Email Accounts", "UWaterloo Nexus");
        storeItem("Email Accounts", "Yahoo");
        storeItem("Email Accounts", "Outlook");

        storeItem("Utilities", "Fido");
        storeItem("Utilities", "Skype");
        storeItem("Utilities", "Rogers");
        storeItem("Utilities", "Bell");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeCategory(String category){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d(DEBUGTAG, "Category: " + category);
        values.put(CAT_NAME, category);
        db.insert(CATEGORY_TABLE, null, values);
        db.close();
    }

    public void storeItem(String category, String item){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d(DEBUGTAG, "Category: " + category + " :::::: Item: " + item);
        values.put(ITEM_NAME, item);
        values.put(ITEM_CAT, category);
        db.insert(ITEM_TABLE, null, values);
        db.close();
    }


    public List<String> getCategory(){
        List<String> categories = new ArrayList<String>();

        SQLiteDatabase db = getWritableDatabase();

        String sql = String.format("select * from %s", CATEGORY_TABLE);

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            String category = cursor.getString(0);
            categories.add(category);
        }
        db.close();
        return categories;
    }

    public List<String> getItem(String category){
        List<String> items = new ArrayList<String>();

        SQLiteDatabase db = getWritableDatabase();

        String sql = String.format("select * from %s where ITEM_CAT = %s", ITEM_TABLE, category);

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            String item = cursor.getString(0);
            items.add(item);
        }
        db.close();
        return items;
    }
}
