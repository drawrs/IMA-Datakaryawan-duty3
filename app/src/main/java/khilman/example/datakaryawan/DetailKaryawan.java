package khilman.example.datakaryawan;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import khilman.example.datakaryawan.Model.DBDataSourceEmp;
import khilman.example.datakaryawan.Model.Karyawan;

public class DetailKaryawan extends AppCompatActivity {
    TextView empName, empEmail, empDev, empPTName;
    Button btnHapus, btnUbah;
    private DBDataSourceEmp dataSourceEmp;
    Karyawan karyawan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);
        dataSourceEmp = new DBDataSourceEmp(this);
        try {
            dataSourceEmp.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initViews();
        empName.setText(getIntent().getStringExtra("empName"));
        empEmail.setText(getIntent().getStringExtra("empEmail"));
        empDev.setText(getIntent().getStringExtra("empDev"));
        empPTName.setText(getIntent().getStringExtra("empPTName"));

        Log.w("INFOO", getIntent().getStringExtra("empId"));
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deleteItem(Long.parseLong(getIntent().getStringExtra("empId")));
                karyawan = new Karyawan();
                karyawan.setId(Long.parseLong(""+getIntent().getStringExtra("empId")));
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailKaryawan.this);
                dialog.setTitle("Konfirmasi");
                dialog.setMessage("Anda akan menghapus data ini ?");
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataSourceEmp.deteleKaryawan(karyawan);
                        Toast.makeText(DetailKaryawan.this, "Berhasil dihapus !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DetailKaryawan.this, ListKaryawan.class));
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();


            }
        });
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(DetailKaryawan.this);
                dialog.setContentView(R.layout.form_edot);
                final EditText editName = (EditText) dialog.findViewById(R.id.editName);
                final EditText editEmail = (EditText) dialog.findViewById(R.id.editEmail);
                final EditText editDev = (EditText) dialog.findViewById(R.id.editDev);
                final EditText editPT = (EditText) dialog.findViewById(R.id.editPT);
                final Button btnSimpanEdit = (Button) dialog.findViewById(R.id.btnSimpanEdit);

                editName.setText(empName.getText());
                editEmail.setText(empEmail.getText());
                editDev.setText(empDev.getText());
                editPT.setText(empPTName.getText());
                dialog.setTitle("Ubah Data");
                dialog.show();

                btnSimpanEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        empName.setText(editName.getText());
                        empEmail.setText(editEmail.getText());
                        empDev.setText(editDev.getText());
                        empPTName.setText(editPT.getText());
                        karyawan = new Karyawan();
                        karyawan.setId(Long.parseLong(""+getIntent().getStringExtra("empId")));
                        karyawan.setName(""+editName.getText());
                        karyawan.setEmail(""+editEmail.getText());
                        karyawan.setDevelpment(""+editDev.getText());
                        karyawan.setPt_name(""+editPT.getText());

                        dataSourceEmp.updateKaryawan(karyawan);
                        //dataSourceEmp.close();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void initViews() {
        empName = (TextView) findViewById(R.id.empName);
        empEmail = (TextView) findViewById(R.id.empEmail);
        empDev = (TextView) findViewById(R.id.empDev);
        empPTName = (TextView) findViewById(R.id.empPTName);

        btnHapus = (Button) findViewById(R.id.btnHapus);
        btnUbah = (Button) findViewById(R.id.btnUbah);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DetailKaryawan.this, ListKaryawan.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
//            Log.w("Bisa loh", "onOptionsItemSelected: ");
            startActivity(new Intent(DetailKaryawan.this, ListKaryawan.class));
            finish();
            return true;
    }

        return super.onOptionsItemSelected(item);
    }
}
