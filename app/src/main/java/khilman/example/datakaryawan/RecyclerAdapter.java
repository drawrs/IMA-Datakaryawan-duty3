package khilman.example.datakaryawan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import khilman.example.datakaryawan.Model.DBHelper;
import khilman.example.datakaryawan.Model.Karyawan;
import khilman.example.datakaryawan.Model.User;

/**
 * Created by root on 03/08/17.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    // deklarasi variable context
    private final Context context;
    LayoutInflater inflater;
    ArrayList<User> values;

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public RecyclerAdapter(Context context, ArrayList<User> values){
        this.values = values;
        this.context = context;

        // buat database
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  inflater.inflate(R.layout.list_user, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.tv1.setText("Cobaaa");
        holder.tv1.setText(values.get(position).getName());
      //  holder.tv1.setOnClickListener(clickListener);
        holder.tv2.setText(values.get(position).getEmail());
       // holder.imageView.setImageResource(R.mipmap.ic_launcher_round);
       // holder.imageView.setOnClickListener(clickListener);
        //holder.tv2.setOnClickListener(clickListener);
       // holder.tv1.setTag(holder);
        //holder.imageView.setTag(holder);
    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // memberi aksi saat cardview di click berdasarkan posisi tertentu
            RecyclerViewHolder vholder = (RecyclerViewHolder) view.getTag();
            int position = vholder.getPosition();
//                Intent intent = new Intent(context, PlaceDetail.class);
//                intent.putExtra("postDesc", desc[position]);
//                intent.putExtra("postTitle", desc[position]);
//                intent.putExtra("postImage", "" + thumbId[position]);
//                context.startActivity(intent);
            Toast.makeText(context, "Menu ini berada di posisi " + position, Toast.LENGTH_LONG).show();
        }
    };
    @Override
    public int getItemCount() {
        return values.size();
    }
}
