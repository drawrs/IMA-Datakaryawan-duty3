package khilman.example.datakaryawan.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by root on 03/08/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    // Table names
    public static final String TABLE_USER = "tb_user";
    public static final String TABLE_KARYAWAN = "tb_karyawan";

    // User table colom
    public static final String USER_ID = "_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_NAME = "name";
    // Karyawan table
    public static final String KARYAWAN_ID = "_id";
    public static final String KARYAWAN_NAME = "name";
    public static final String KARYAWAN_EMAIL = "email";
    public static final String KARYAWAN_DEVELOPMENT = "development";
    public static final String KARYAWAN_PT_NAME = "pt_name";

    public static final String db_name ="data_karyawan_beta.db";
    // versi db
    public static final int db_version=1;
    // buat table
    private static final String CREATE_USER_TABLE = "CREATE TABLE "
            + TABLE_USER + " ("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_NAME +" VARCHAR(50) NOT NULL, "
            + USER_EMAIL + " VARHCAR(50) NOT NULL, "
            + USER_PASSWORD + " VARHCAR(50) NOT NULL);";

    public static final String CREATE_KARYAWAN_TABLE = "CREATE TABLE "
            + TABLE_KARYAWAN + " ("
            + KARYAWAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KARYAWAN_NAME + " VARCHAR(50) NOT NULL, "
            + KARYAWAN_EMAIL + " VARCHAR(50) NOT NULL, "
            + KARYAWAN_DEVELOPMENT + " VARCHAR(50) NOT NULL, "
            + KARYAWAN_PT_NAME + " VARCHAR(50) NOT NULL);";

    public DBHelper(Context context){
        super(context, db_name, null, db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_KARYAWAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + "to"
                +newVersion+ ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_KARYAWAN);
        onCreate(db);
    }

}
