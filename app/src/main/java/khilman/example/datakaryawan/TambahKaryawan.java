package khilman.example.datakaryawan;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import khilman.example.datakaryawan.Model.DBDataSource;
import khilman.example.datakaryawan.Model.DBDataSourceEmp;
import khilman.example.datakaryawan.Model.Karyawan;

public class TambahKaryawan extends AppCompatActivity implements View.OnClickListener{
    AppCompatActivity activity = TambahKaryawan.this;
    NestedScrollView nestedScrollView;
    EditText txtName, txtEmail, txtDev, txtPT;

    Button btnAddEmp;
    private DBDataSourceEmp dataSource;
    Karyawan karyawan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_karyawan);

        dataSource = new DBDataSourceEmp(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initViews();
        initListeners();
        initObjects();
    }

    private void initObjects() {
        karyawan = new Karyawan();
    }

    private void initListeners() {
        btnAddEmp.setOnClickListener(this);
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedReg);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDev = (EditText) findViewById(R.id.txtDev);
        txtPT = (EditText) findViewById(R.id.txtPT);

        btnAddEmp = (Button) findViewById(R.id.btnAddEmp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddEmp:
                postToSQLite();
                break;
        }
    }

    private void postToSQLite() {
        String nama = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String development = txtDev.getText().toString();
        String nama_pt = txtPT.getText().toString();
        //Log.e("INFOO", "" + development + "" + nama_pt);
        Karyawan karyawan = null;
        karyawan = dataSource.createKaryawan(nama, email, development, nama_pt);
        Toast.makeText(activity, "Berhasil ditambahkan !", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(TambahKaryawan.this, ListKaryawan.class));
        clearInput();
        finish();
    }
    private void clearInput(){
        txtName.setText("");
        txtEmail.setText("");
        txtDev.setText("");
        txtPT.setText("");
    }
}
