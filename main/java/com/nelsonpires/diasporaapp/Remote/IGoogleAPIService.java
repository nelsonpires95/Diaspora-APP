package com.nelsonpires.diasporaapp.Remote;

import com.nelsonpires.diasporaapp.model.MyPlaces;
import com.nelsonpires.diasporaapp.model.PlaceDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IGoogleAPIService {
    @GET
    Call<MyPlaces> getNearbyPlaces(@Url String url);


    @GET
    Call<PlaceDetail> getDetailPage(@Url String url);


}
