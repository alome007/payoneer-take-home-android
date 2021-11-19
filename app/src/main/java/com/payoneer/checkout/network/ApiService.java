package com.payoneer.checkout.network;

import com.google.gson.JsonObject;
import com.payoneer.checkout.model.LinkResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //send the HTTP request using GET Method
    @GET("/optile/checkout-android/develop/shared-test/lists/listresult.json")
    Call <LinkResults> getPaymentProviders();
}