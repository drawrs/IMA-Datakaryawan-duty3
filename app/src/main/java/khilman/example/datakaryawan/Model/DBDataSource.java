package khilman.example.datakaryawan.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by root on 02/08/17.
 */

public class DBDataSource{
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumn = { DBHelper.USER_ID, DBHelper.USER_NAME, DBHelper.USER_EMAIL, DBHelper.USER_PASSWORD};
    public DBDataSource(Context context){
        dbHelper = new DBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public List<User> AllUser(){
        List<User> users = new ArrayList<User>();
        String selectQuery = "SELECT*FROM " + DBHelper.TABLE_USER;

        Log.e("INFOO", selectQuery);
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            do{
                User u = new User();
                u.setId(c.getInt((c.getColumnIndex(DBHelper.USER_ID))));
                u.setName(c.getString((c.getColumnIndex(DBHelper.USER_NAME))));
                u.setEmail(c.getString((c.getColumnIndex(DBHelper.USER_EMAIL))));
                users.add(u);

            } while (c.moveToNext());
        }
        return users;
    }
    public User createUser (String name, String email, String password){
        ContentValues values = new ContentValues();
        values.put(DBHelper.USER_NAME, name);
        values.put(DBHelper.USER_EMAIL, email);
        values.put(DBHelper.USER_PASSWORD, password);

        long insertId = database.insert(DBHelper.TABLE_USER, null, values);
        Cursor cursor = database.query(DBHelper.TABLE_USER,
                allColumn, DBHelper.USER_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }
    private User cursorToUser(Cursor cursor) {
        //buat objek brg baru
        User user = new User();

        user.setId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        user.setEmail(cursor.getString(2));
        user.setPassword(cursor.getString(3));

        return user;
    }

}
