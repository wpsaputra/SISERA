package com.virtualbox.torchick.rog.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.virtualbox.torchick.rog.Activity.PublikasiActivity;
import com.virtualbox.torchick.rog.Model.Publikasi;
import com.virtualbox.torchick.rog.R;

import java.util.List;

/**
 * Created by Torchick on 27/03/2017.
 */

public class PublikasiAdapter extends RecyclerView.Adapter<PublikasiAdapter.MyViewHolder> {
    private List<Publikasi> publikasiList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView cover_img;
        public TextView judul, isbn, tanggal_rilis;
        public RelativeLayout relative_layout_row;

        public MyViewHolder(View view) {
            super(view);
            cover_img = (ImageView) view.findViewById(R.id.cover_img);
            judul = (TextView) view.findViewById(R.id.judul);
            isbn = (TextView) view.findViewById(R.id.isbn);
            tanggal_rilis = (TextView) view.findViewById(R.id.tanggal_rilis);
            relative_layout_row = (RelativeLayout) view.findViewById(R.id.relative_layout_row);
        }
    }

    public PublikasiAdapter(List<Publikasi> publikasiList, Context mContext) {
        this.publikasiList = publikasiList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.publikasi_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Publikasi publikasi = publikasiList.get(position);
        holder.judul.setText(publikasi.getJudul_ind());
        holder.isbn.setText(publikasi.getNo_publikasi());
        holder.tanggal_rilis.setText(publikasi.getTgl_rilis());

        applyCoverPicture(holder, publikasi);
        // apply click events
        applyClickEvents(holder, position);

    }

    private void applyClickEvents(MyViewHolder holder, final int position) {
        holder.relative_layout_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i =  new Intent(mContext, DetailActivity.class);
//                Publikasi p = publikasiList.get(position);
                // insert db to history
//                ModelPublikasi mp = new ModelPublikasi(mContext);
//                mp.insert(p);
//                Log.d("Sqlite", String.valueOf(mp.getAll().size()));

//                i.putExtra("publikasi", p);
//                mContext.startActivity(i);

                Intent i =  new Intent(mContext, PublikasiActivity.class);
                Publikasi p = publikasiList.get(position);

                i.putExtra("publikasi", p);
                mContext.startActivity(i);




            }
        });

    }

    @Override
    public int getItemCount() {
        return publikasiList.size();
    }

    private void applyCoverPicture(MyViewHolder holder, Publikasi publikasi) {

        if (!TextUtils.isEmpty(publikasi.getFile_cover())) {
//            Glide.with(mContext).load("https://sultra.bps.go.id/website/cover_publikasi/"+publikasi.getFile_cover())
            Glide.with(mContext).load(publikasi.getFile_cover())
                    .placeholder(R.drawable.not_available)
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.cover_img);
            holder.cover_img.setColorFilter(null);
        }
    }

}
