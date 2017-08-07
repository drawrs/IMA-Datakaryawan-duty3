package khilman.example.datakaryawan.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LogWriter;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 02/08/17.
 */

public class DBDataSourceEmp {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumn = { DBHelper.KARYAWAN_ID,DBHelper.KARYAWAN_NAME, DBHelper.KARYAWAN_EMAIL, DBHelper.KARYAWAN_DEVELOPMENT, DBHelper.KARYAWAN_PT_NAME};
    public DBDataSourceEmp(Context context){
        dbHelper = new DBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public List<Karyawan> AllKaryawan(){
        List<Karyawan> users = new ArrayList<Karyawan>();
        String selectQuery = "SELECT * FROM " + DBHelper.TABLE_KARYAWAN + " ORDER BY _id DESC";

        Log.e("INFOO", selectQuery);
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            do{
                Karyawan u = new Karyawan();
                u.setId(c.getInt((c.getColumnIndex(DBHelper.KARYAWAN_ID))));
                u.setName(c.getString((c.getColumnIndex(DBHelper.KARYAWAN_NAME))));
                u.setEmail(c.getString((c.getColumnIndex(DBHelper.KARYAWAN_EMAIL))));
                u.setDevelpment(c.getString((c.getColumnIndex(DBHelper.KARYAWAN_DEVELOPMENT))));
                u.setPt_name(c.getString((c.getColumnIndex(DBHelper.KARYAWAN_PT_NAME))));
                users.add(u);

            } while (c.moveToNext());
        }
        return users;
    }

    public void deteleKaryawan(Karyawan u){
        String strFilter =  "_id = " + u.getId();
        database.delete(DBHelper.TABLE_KARYAWAN, strFilter, null);
    }
    public void updateKaryawan(Karyawan u){
        Log.w("INFO", "updateKaryawan:" + u.getId());
        String strFilter = "_id = " + u.getId();
        ContentValues args = new ContentValues();
        args.put(DBHelper.KARYAWAN_NAME, u.getName());
        args.put(DBHelper.KARYAWAN_EMAIL, u.getEmail());
        args.put(DBHelper.KARYAWAN_DEVELOPMENT, u.getDevelpment());
        args.put(DBHelper.KARYAWAN_PT_NAME, u.getPt_name());
        database.update(DBHelper.TABLE_KARYAWAN, args, strFilter, null);
    }
    public Karyawan createKaryawan (String name, String email, String development, String pt_name){
        ContentValues values = new ContentValues();
        values.put(DBHelper.KARYAWAN_NAME, name);
        values.put(DBHelper.KARYAWAN_EMAIL, email);
        values.put(DBHelper.KARYAWAN_DEVELOPMENT, development);
        values.put(DBHelper.KARYAWAN_PT_NAME, pt_name);

        long insertId = database.insert(DBHelper.TABLE_KARYAWAN, null, values);
        Cursor cursor = database.query(DBHelper.TABLE_KARYAWAN,
                allColumn, DBHelper.KARYAWAN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        Karyawan newKaryawan = cursorToEmp(cursor);
        cursor.close();
        return newKaryawan;
    }
    private Karyawan cursorToEmp(Cursor cursor) {
        //buat objek brg baru
        Karyawan karyawan = new Karyawan();

        karyawan.setId(cursor.getLong(0));
        karyawan.setName(cursor.getString(1));
        karyawan.setEmail(cursor.getString(2));
        karyawan.setDevelpment(cursor.getString(3));
        karyawan.setPt_name(cursor.getString(4));

        return karyawan;

    }

    public static boolean delete(long id, SQLiteDatabase database) {
        //SQLiteDatabase database = new SQLiteDatabase;
        database.delete(DBHelper.TABLE_KARYAWAN, DBHelper.KARYAWAN_ID + " = " + id, null );
        return true;
    }
}
