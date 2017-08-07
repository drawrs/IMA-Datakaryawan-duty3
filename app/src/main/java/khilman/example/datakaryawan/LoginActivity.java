package khilman.example.datakaryawan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import khilman.example.datakaryawan.Model.DBDataSource;
import khilman.example.datakaryawan.Model.DBDataSourceEmp;
import khilman.example.datakaryawan.Model.DBHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;
    private NestedScrollView nestedScrollView;
    private EditText txtEmail, txtPassword;
    TextView linkReg;
    private Button btnLogin;

    private DBDataSource dataSource;
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    SessionManager sessionManager;
    //private final Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initListener();
        initObjects();
    }

    private void initObjects() {
        // buat database
        dbHelper = new DBHelper(getApplicationContext());
        database = dbHelper.getWritableDatabase();
        sessionManager = new SessionManager(getApplicationContext());
        // check for login
        sessionManager.checkLogin2();
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedLogin);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPwd);
        linkReg = (TextView) findViewById(R.id.linkReg);

        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void initListener() {
        btnLogin.setOnClickListener(this);
        linkReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                proccessLogin();
                break;
            case R.id.linkReg:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void proccessLogin() {
        if(txtEmail.getText().length() <= 0  || txtPassword.getText().length() <= 0){
            Toast.makeText(activity, "Isi kolom email dan password dengan benar!", Toast.LENGTH_SHORT).show();
            return;
        }
        verifyFromSQLite(txtEmail.getText().toString(), txtPassword.getText().toString());
    }

    private boolean verifyFromSQLite(String email, String password) {

        System.out.println(this.checkUser(txtEmail.getText().toString(), txtPassword.getText().toString()));
        String selectQuery = "SELECT*FROM " + DBHelper.TABLE_USER + " WHERE email = '" + email + "' AND password = '" + password + "'";

        Log.e("INFOO", selectQuery + " " + email);
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.getCount() > 0) {
            Toast.makeText(activity, "Login berhasil", Toast.LENGTH_SHORT).show();
            // create session
            sessionManager.createSession(email);

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            clearInput();
            finish();
        } else {
            Toast.makeText(activity, "Login gagal", Toast.LENGTH_SHORT).show();
        }
        return false;

    }

    private void clearInput() {
        txtEmail.setText("");
        txtPassword.setText("");
    }

    private boolean checkUser(String email, String password) {
        System.out.println(email + "" + password);
        return true;
    }
}
