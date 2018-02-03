package com.virtualbox.torchick.rog.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.virtualbox.torchick.rog.Activity.WebActivity;
import com.virtualbox.torchick.rog.Model.LinkDataForm;
import com.virtualbox.torchick.rog.Model.Publikasi;
import com.virtualbox.torchick.rog.R;

import java.util.List;

/**
 * Created by Torchick on 27/03/2017.
 */

public class LinkDataFormAdapter extends RecyclerView.Adapter<LinkDataFormAdapter.MyViewHolder> {
    private List<LinkDataForm> linkDataFormList;
    private Context mContext;
    private Bitmap icon, circle;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView cover_img, icon_img;
        public TextView judul, tanggal_rilis;
        public RelativeLayout relative_layout_row;

        public MyViewHolder(View view) {
            super(view);
            cover_img = (ImageView) view.findViewById(R.id.cover_img);
            icon_img = (ImageView) view.findViewById(R.id.icon_img);
            judul = (TextView) view.findViewById(R.id.judul);
            tanggal_rilis = (TextView) view.findViewById(R.id.tanggal_rilis);
            relative_layout_row = (RelativeLayout) view.findViewById(R.id.relative_layout_row);
        }
    }

    public LinkDataFormAdapter(List<LinkDataForm> linkDataFormList, Context mContext, Bitmap icon, Bitmap circle) {
        this.linkDataFormList = linkDataFormList;
        this.icon = icon;
        this.circle = circle;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.indikator_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LinkDataForm linkDataForm = linkDataFormList.get(position);
        holder.judul.setText(linkDataForm.getJudul());
        holder.tanggal_rilis.setText(linkDataForm.getTgl_ubah());

        applyCoverPicture(holder, linkDataForm);
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

                Intent i =  new Intent(mContext, WebActivity.class);
                LinkDataForm linkDataForm = linkDataFormList.get(position);
                i.putExtra("linkDataForm", linkDataForm);
                mContext.startActivity(i);





            }
        });

    }

    @Override
    public int getItemCount() {
        return linkDataFormList.size();
    }

    private void applyCoverPicture(MyViewHolder holder, LinkDataForm linkDataForm) {

        if(icon!=null&&circle!=null){
            holder.cover_img.setImageBitmap(circle);
            holder.icon_img.setImageBitmap(icon);
            holder.icon_img.setColorFilter(Color.TRANSPARENT);
        }


//        if (!TextUtils.isEmpty(publikasi.getFile_cover())) {
//            Glide.with(mContext).load("https://kendarikota.bps.go.id/website/cover_publikasi/"+publikasi.getFile_cover())
//                    .placeholder(R.drawable.not_available)
//                    .centerCrop()
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(holder.cover_img);
//            holder.cover_img.setColorFilter(null);
//        }

    }

}
