package khilman.example.datakaryawan;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

import khilman.example.datakaryawan.Model.DBDataSource;
import khilman.example.datakaryawan.Model.DBDataSourceEmp;
import khilman.example.datakaryawan.Model.Karyawan;
import khilman.example.datakaryawan.Model.User;

public class ListKaryawan extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DBDataSourceEmp dataSource;
    private ArrayList<Karyawan> values;
    public static Activity fa;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_karyawan);
        // biar bisa di finish dari activity lain
        fa = this;
        // get All column
        dataSource = new DBDataSourceEmp(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        values = (ArrayList<Karyawan>) dataSource.AllKaryawan();
        //Log.e("infoo", "" + values.get(1) );
        ArrayAdapter<Karyawan> arrayAdapter = new ArrayAdapter<Karyawan>(this,android.R.layout.simple_list_item_1, values);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        // Adapter
        RecyclerAdapterEmp adapter = new RecyclerAdapterEmp(this, values);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListKaryawan.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.w("LLLL", "onNavigationItemSelected: " + item );
        return false;
    }
}
