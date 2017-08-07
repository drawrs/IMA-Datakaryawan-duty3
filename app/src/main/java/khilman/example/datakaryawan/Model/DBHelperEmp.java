package khilman.example.datakaryawan.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by root on 04/08/17.
 */

public class DBHelperEmp extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tb_karyawan";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DEVELOPMENT = "development";
    public static final String COLUMN_PT_NAME = "pt_name";
    public static final String db_name = "data_karyawan_beta.db";
    //
    public static final int db_version = 1;
    public static final String db_create = "CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
            + COLUMN_NAME + " VARCHAR(50) NOT NULL, "
            + COLUMN_EMAIL + " VARCHAR(50) NOT NULL, "
            + COLUMN_DEVELOPMENT + " VARCHAR(50) NOT NULL"
            + COLUMN_PT_NAME + " VARCHAR(50) NOT NULL);";

    public DBHelperEmp(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + "to"
                +newVersion+ ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

}
