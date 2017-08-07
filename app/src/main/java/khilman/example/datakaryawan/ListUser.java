package khilman.example.datakaryawan;

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
import khilman.example.datakaryawan.Model.User;

public class ListUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DBDataSource dataSource;
    private ArrayList<User> values;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        // get All column
        dataSource = new DBDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        values = (ArrayList<User>) dataSource.AllUser();

        //ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1, values);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_user);
//        // Adapter
        RecyclerAdapter adapter = new RecyclerAdapter(this, values);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
