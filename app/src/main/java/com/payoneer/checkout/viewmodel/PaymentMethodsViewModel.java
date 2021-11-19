package com.payoneer.checkout.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.payoneer.checkout.model.LinkResults;
import com.payoneer.checkout.network.ApiService;
import com.payoneer.checkout.network.RetrofitInstance;
import static com.payoneer.checkout.utils.Constants.TAG;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodsViewModel  extends ViewModel {

    private MutableLiveData<LinkResults> mutableLiveData;
    public PaymentMethodsViewModel(){
        mutableLiveData= new MutableLiveData<>();
    }

    public MutableLiveData<LinkResults> getPaymentMethodsObserver(){
        return mutableLiveData;
    }

    public void getPaymentMethods(){
        ApiService apiService= RetrofitInstance.getRetrofitClient().create(ApiService.class);
        Call<LinkResults> apiCall = apiService.getPaymentProviders();
        Log.d(TAG, apiCall.request().url().toString());
        apiCall.enqueue(new Callback<LinkResults>() {
            @Override
            public void onResponse(Call<LinkResults> call, Response<LinkResults> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LinkResults> call, Throwable t) {
                //if it fails send a null response
                Log.d(TAG, t.getMessage());
                mutableLiveData.postValue(null);
            }
        });
    }
}