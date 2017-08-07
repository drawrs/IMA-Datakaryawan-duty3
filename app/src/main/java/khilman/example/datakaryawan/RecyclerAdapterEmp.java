package khilman.example.datakaryawan;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import khilman.example.datakaryawan.Model.DBDataSourceEmp;
import khilman.example.datakaryawan.Model.DBHelper;
import khilman.example.datakaryawan.Model.Karyawan;
import khilman.example.datakaryawan.Model.User;

/**
 * Created by root on 03/08/17.
 */

class RecyclerAdapterEmp extends RecyclerView.Adapter<RecyclerViewHolder> {
    // deklarasi variable context
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private final Context context;
    LayoutInflater inflater;
    ArrayList<Karyawan> values;

    public RecyclerAdapterEmp(Context context, ArrayList<Karyawan> values){
        this.values = values;
        this.context = context;
        // buat database
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

        inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  inflater.inflate(R.layout.list_karyawan, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        final String empId = "" + values.get(position).getId();
        final String empName = "" + values.get(position).getName();
        final String empEmail = "" + values.get(position).getEmail();
        final String empDev = "" + values.get(position).getDevelpment();
        final String empPTName = "" + values.get(position).getPt_name();

        holder.empName.setText(values.get(position).getName());
        holder.PTName.setText(values.get(position).getPt_name());
        holder.empDev.setText(values.get(position).getDevelpment());
        holder.imageView.setImageResource(R.mipmap.ic_launcher_round);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailKaryawan(empId, empName, empEmail, empDev, empPTName);
                //intent.putExtra("empName", desc[position]);
            }
        });
        holder.empName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailKaryawan(empId, empName, empEmail, empDev, empPTName);
            }
        });
        //holder.tv2.setOnClickListener(clickListener);
        holder.empName.setTag(holder);
        holder.imageView.setTag(holder);
        holder.imgBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(values.get(position).getId());
            }
        });
    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // memberi aksi saat cardview di click berdasarkan posisi tertentu
            RecyclerViewHolder vholder = (RecyclerViewHolder) view.getTag();
            int position = vholder.getPosition();
            Intent intent = new Intent(context, DetailKaryawan.class);

            context.startActivity(intent);
            Toast.makeText(context, "Menu ini berada di posisi " + position, Toast.LENGTH_LONG).show();
        }
    };
    private void deleteItem(final long empId) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Konfirmasi");
        dialog.setMessage("Hapus karyawan ini ?");
        dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBDataSourceEmp.delete(empId, database);
                Intent intent = new Intent(context, ListKaryawan.class);
                Toast.makeText(context, "Dihapus !", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();

    }

    private void openDetailKaryawan(String id, String name, String email, String dev, String pt_name) {
        Intent intent = new Intent(context, DetailKaryawan.class);
        intent.putExtra("empId", id);
        intent.putExtra("empName", name);
        intent.putExtra("empEmail", email);
        intent.putExtra("empDev", dev);
        intent.putExtra("empPTName", pt_name);
        context.startActivity(intent);
        ListKaryawan.fa.finish();
        //Toast.makeText(context, "mau buka" + email + "" +name + "" + id + "" + dev, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
