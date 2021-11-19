package com.payoneer.checkout.network;

import static com.google.common.truth.Truth.assertThat;
import static com.payoneer.checkout.utils.Constants.TAG;

import android.util.Log;

import com.payoneer.checkout.model.LinkResults;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallTest {
    @Test
    public void can_successfully_fetch_data(){
        ApiService apiService= RetrofitInstance.getRetrofitClient().create(ApiService.class);
        Call<LinkResults> apiCall = apiService.getPaymentProviders();
        try {
            Response<LinkResults> response= apiCall.execute();
            LinkResults results = response.body();
            assertThat(results.getNetworks().getApplicable()).isNotNull();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void api_call_returns_status_200(){
        ApiService apiService= RetrofitInstance.getRetrofitClient().create(ApiService.class);
        Call<LinkResults> apiCall = apiService.getPaymentProviders();
        try {
            Response<LinkResults> response= apiCall.execute();
            assertThat(response.code()).isEqualTo(200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}