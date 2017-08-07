package khilman.example.datakaryawan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by root on 03/08/17.
 */

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView tv1, tv2, empName, PTName, empDev;
    ImageView imageView;
    ImageButton imgBtnDel;
    RelativeLayout list_item;
    public RecyclerViewHolder(View itemView){
        super(itemView);

        tv1 = (TextView) itemView.findViewById(R.id.daftar_judul);
        //menampilkan text dari widget CardView pada id daftar_judul
        tv2 = (TextView) itemView.findViewById(R.id.daftar_deskripsi);
        //menampilkan text deskripsi dari widget CardView pada id daftar_deskripsi
        imageView = (ImageView) itemView.findViewById(R.id.daftar_icon);
        //menampilkan gambar atau icon pada widget Cardview pada id daftar_icon
        list_item = (RelativeLayout) itemView.findViewById(R.id.list_item);
        // punya karyawan
        empName = (TextView) itemView.findViewById(R.id.txtEmpName);
        PTName = (TextView) itemView.findViewById(R.id.txtPTName);
        empDev = (TextView) itemView.findViewById(R.id.txtEmpDev);

        imgBtnDel = (ImageButton) itemView.findViewById(R.id.imgBtnDel);
    }
}
