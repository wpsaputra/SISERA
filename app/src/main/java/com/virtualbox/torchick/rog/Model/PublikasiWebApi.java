package com.virtualbox.torchick.rog.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Torchick on 2/3/2018.
 */

public class PublikasiWebApi implements Parcelable {
    String pub_id,
            title,
            issn,
            sch_date,
            rl_date,
            updt_date,
            cover,
            pdf,
            size;
    public PublikasiWebApi() {
    }

    public String getPub_id() {
        return pub_id;
    }

    public void setPub_id(String pub_id) {
        this.pub_id = pub_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getSch_date() {
        return sch_date;
    }

    public void setSch_date(String sch_date) {
        this.sch_date = sch_date;
    }

    public String getRl_date() {
        return rl_date;
    }

    public void setRl_date(String rl_date) {
        this.rl_date = rl_date;
    }

    public String getUpdt_date() {
        return updt_date;
    }

    public void setUpdt_date(String updt_date) {
        this.updt_date = updt_date;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pub_id);
        dest.writeString(this.title);
        dest.writeString(this.issn);
        dest.writeString(this.sch_date);
        dest.writeString(this.rl_date);
        dest.writeString(this.updt_date);
        dest.writeString(this.cover);
        dest.writeString(this.pdf);
        dest.writeString(this.size);
    }

    protected PublikasiWebApi(Parcel in) {
        this.pub_id = in.readString();
        this.title = in.readString();
        this.issn = in.readString();
        this.sch_date = in.readString();
        this.rl_date = in.readString();
        this.updt_date = in.readString();
        this.cover = in.readString();
        this.pdf = in.readString();
        this.size = in.readString();
    }

    public static final Parcelable.Creator<PublikasiWebApi> CREATOR = new Parcelable.Creator<PublikasiWebApi>() {
        @Override
        public PublikasiWebApi createFromParcel(Parcel source) {
            return new PublikasiWebApi(source);
        }

        @Override
        public PublikasiWebApi[] newArray(int size) {
            return new PublikasiWebApi[size];
        }
    };
}
