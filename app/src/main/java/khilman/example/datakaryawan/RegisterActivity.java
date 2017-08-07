package khilman.example.datakaryawan;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLException;

import khilman.example.datakaryawan.Model.DBDataSource;
import khilman.example.datakaryawan.Model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    AppCompatActivity activity = RegisterActivity.this;
    NestedScrollView nestedScrollView;
    EditText txtName, txtEmail, txtPwd, txtPwdCheck;
    TextView linkLogin;
    Button btnReg;
    private DBDataSource dataSource;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dataSource = new DBDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initViews();
        initListeners();

        initObjects();

    }

    private void initListeners() {
        btnReg.setOnClickListener(this);
        linkLogin.setOnClickListener(this);
    }
    private void initObjects() {
        user = new User();

    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedReg);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPwd = (EditText) findViewById(R.id.txtPwd);
        txtPwdCheck = (EditText) findViewById(R.id.txtPwdCheck);
        linkLogin = (TextView) findViewById(R.id.linkLogin);
        btnReg = (Button) findViewById(R.id.btnReg);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnReg:
                proccessRegister();
                break;
            case R.id.linkLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }

    private void proccessRegister() {
        if (txtName.getText().length() <= 0 || txtEmail.getText().length() <= 0  || txtPwd.getText().length() <= 0 || txtPwdCheck.getText().length() <= 0 ){
            Toast.makeText(activity, "Semua input wajib diisi !", Toast.LENGTH_LONG).show();
            return;
        }
        if (!txtPwd.getText().toString().equalsIgnoreCase(txtPwdCheck.getText().toString())){
            Toast.makeText(activity, "Konfirmasi katasandi tidak cocok !", Toast.LENGTH_SHORT).show();
            return;
        }
        postDataToSQLite();
    }

    private void postDataToSQLite() {
        String nama = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String pwd = txtPwd.getText().toString();

        User user = null;

        user = dataSource.createUser(nama, email, pwd);
        //Snackbar.make(nestedScrollView, nama + "" + email + "" +pwd, Snackbar.LENGTH_LONG).show();
        Toast.makeText(activity, "Registrasi berhasil! Silahkan login.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        clearInput();
        /*if (nama != null && email != null && pwd != null) {


        }*/

    }
    private void clearInput(){
        txtName.setText("");
        txtEmail.setText("");
        txtPwd.setText("");
        txtPwdCheck.setText("");
    }
}   
