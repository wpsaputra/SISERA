package com.virtualbox.torchick.rog.Network;

import com.google.gson.JsonObject;
import com.virtualbox.torchick.rog.Model.Image;
import com.virtualbox.torchick.rog.Model.LinkDataForm;
import com.virtualbox.torchick.rog.Model.Publikasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Torchick on 27/03/2017.
 */

public interface ApiInterface {
    //Publikasi
//    https://webapi.bps.go.id/v1/api/domain/?type=kabbyprov&prov=7400&key=1f5ea27aa195656fa79ee36110bda985
//    https://webapi.bps.go.id/v1/api/list/?model=publication&domain=7400&key=1f5ea27aa195656fa79ee36110bda985&page=3
    @GET("/trickster/index.php")
    Call<List<Publikasi>> getPublikasi(@Query("r") String address);

    @GET("/trickster/index.php")
    Call<List<Publikasi>> getPublikasiSearch(@Query("r") String address, @Query("id") String searchQuery);

    @GET("/trickster/index.php")
    Call<List<Publikasi>> getPublikasiOffset(@Query("r") String address, @Query("offset") String offset, @Query("limit") String limit);



    //Infografis
//    @GET("/sisera/sisera.json")
    @GET("/project/backup_sisera_lama/sisera/sisera.json")
    Call<List<Image>> getImages(@Query("r") String address);

    //Link Sultradata
    @GET("/project/SISERA_2/data/detailx")
    Call<List<LinkDataForm>> getLinkDataForm(@Query("id") String id);

    @GET("/project/SISERA_2/data/detailxy")
    Call<List<LinkDataForm>> getLinkDataFormSearch(@Query("id") String id);


//    Publikasi web 3.0
    @GET("/v1/api/list/index.php")
    Call<JsonObject> getPublikasiWebApi(@Query("model") String model, @Query("domain") String domain, @Query("page") String page, @Query("key") String apiKey);

    @GET("/v1/api/view/index.php?model=publication&domain=7400&id=1&lang=ind&key=1f5ea27aa195656fa79ee36110bda985")
    Call<JsonObject> getPublikasiWebApiDetail(@Query("id") String pub_id);



}
