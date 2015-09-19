package passbolt.com.passbolt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
    private static final String CAT_ICON = "CAT_ICON";


    private static final String ITEM_CAT = "ITEM_CAT";
    private static final String ITEM_NAME = "ITEM_NAME";
    private static final String ITEM_URL = "ITEM_URL";
    private static final String ITEM_ICON = "ITEM_ICON";

    private static final String ITEM_INSTANCE_USERNAME = "ITEM_INSTANCE_USERNAME";
    private static final String ITEM_INSTANCE_PASSWORD = "ITEM_INSTANCE_PASSWORD";

    public Database(Context context) {
        super(context, "passbolt.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s VARCHAR PRIMARY KEY, %s VARCHAR)", CATEGORY_TABLE, CAT_NAME, CAT_ICON);
        db.execSQL(sql);
        sql = String.format("create table %s (%s VARCHAR PRIMARY KEY, %s VARCHAR NOT NULL, %s VARCHAR NOT NULL, %s VARCHAR)", ITEM_TABLE, ITEM_NAME, ITEM_CAT, ITEM_URL, ITEM_ICON);
        db.execSQL(sql);
        sql = String.format("create table %s (%s VARCHAR, %s VARCHAR NOT NULL, %s VARCHAR NOT NULL, PRIMARY KEY (%s, %s))", ITEM_INSTANCE_TABLE, ITEM_NAME, ITEM_INSTANCE_USERNAME, ITEM_INSTANCE_PASSWORD, ITEM_NAME, ITEM_INSTANCE_USERNAME);
        db.execSQL(sql);

        storeCategory("Social Networks", "social");
        storeCategory("Financial Banks", "bank");
        storeCategory("Email Accounts", "email");
        storeCategory("Utilities", "other");

        storeItem("Social Networks", "Facebook", "https://www.facebook.com/", "fb");
        storeItem("Social Networks", "Google+", "https://plus.google.com/", "goog");
        storeItem("Social Networks", "LinkedIn", "https://www.linkedin.com/", "link");
        storeItem("Social Networks", "Twitter", "https://www.twitter.com/", "twit");

        storeItem("Financial Banks", "TD Bank", "https://www.google.com/", "td");
        storeItem("Financial Banks", "Scotiabank", "https://www.google.com/", "scot");
        storeItem("Financial Banks", "CIBC", "https://www.google.com/", "cibc");
        storeItem("Financial Banks", "RBC", "https://www.google.com/", "rbc");

        storeItem("Email Accounts", "Gmail", "https://www.google.com/", "gmail");
        storeItem("Email Accounts", "UWaterloo Nexus", "https://www.google.com/", "nexus");
        storeItem("Email Accounts", "Yahoo", "https://www.google.com/", "yaho");
        storeItem("Email Accounts", "Outlook", "https://www.google.com/", "outl");

        storeItem("Utilities", "Fido", "https://www.google.com/", "fido");
        storeItem("Utilities", "Skype", "https://www.google.com/", "skyp");
        storeItem("Utilities", "Rogers", "https://www.google.com/", "rogr");
        storeItem("Utilities", "Bell", "https://www.google.com/", "bell");

        storeItemInstance("Gmail", "hackthenorth4@gmail.com", "Passbolt");
        storeItemInstance("Gmail", "vishal.shubham@gmail.com", "Passbolt");
        storeItemInstance("Gmail", "waterloopassbolt@gmail.com", "waterlo321");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeCategory(String category, String icon){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d(DEBUGTAG, "Category: " + category + " Icon: " + icon);
        values.put(CAT_NAME, category);
        values.put(CAT_ICON, icon);
        db.insert(CATEGORY_TABLE, null, values);
        db.close();
    }

    public void storeItem(String category, String item, String url, String icon){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d(DEBUGTAG, "Category: " + category + " :::::: IItem: " + item + " :::::: Url: " + url + " :::::: Icon: " + icon);
        values.put(ITEM_NAME, item);
        values.put(ITEM_CAT, category);
        values.put(ITEM_URL, url);
        values.put(ITEM_ICON, icon);
        db.insert(ITEM_TABLE, null, values);
        db.close();
    }

    public void storeItemInstance(String item, String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d(DEBUGTAG, "IItem: " + item + " :::::: Username: " + username + " :::::: Password: " + password);
        values.put(ITEM_NAME, item);
        values.put(ITEM_INSTANCE_USERNAME, username);
        values.put(ITEM_INSTANCE_PASSWORD, password);
        db.insert(ITEM_INSTANCE_TABLE, null, values);
        db.close();
    }

    public List<Node_Category> getCategory(){
        List<Node_Category> categories = new ArrayList<Node_Category>();

        SQLiteDatabase db = getWritableDatabase();

        String sql = String.format("select * from %s", CATEGORY_TABLE);

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            String category = cursor.getString(0);
            String icon = cursor.getString(1);
            categories.add(new Node_Category(category, icon));
        }
        db.close();
        return categories;
    }

    public List<Node_Item> getItem(String category){
        List<Node_Item> items = new ArrayList<Node_Item>();

        SQLiteDatabase db = getWritableDatabase();

        String sql = String.format("select * from %s where ITEM_CAT = %s", ITEM_TABLE, category);

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            String item_name = cursor.getString(0);
            String item_category = cursor.getString(1);
            String item_url = cursor.getString(2);
            String item_icon = cursor.getString(3);
            items.add(new Node_Item(item_category, item_name, item_url, item_icon));
        }
        db.close();
        return items;
    }

    public List<Node_Item> getItemInstance(String item_instance){
        List<Node_Item> items = new ArrayList<Node_Item>();

        SQLiteDatabase db = getWritableDatabase();

        String sql = String.format("select * from %s where ITEM_CAT = %s", ITEM_TABLE, category);

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            String item_name = cursor.getString(0);
            String item_category = cursor.getString(1);
            String item_url = cursor.getString(2);
            String item_icon = cursor.getString(3);
            items.add(new Node_Item(item_category, item_name, item_url, item_icon));
        }
        db.close();
        return items;
    }
}
