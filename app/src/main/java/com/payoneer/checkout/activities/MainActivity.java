package com.payoneer.checkout.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.payoneer.checkout.R;
import com.payoneer.checkout.adapter.PaymentMethodsAdapter;
import com.payoneer.checkout.model.Applicable;
import com.payoneer.checkout.model.LinkResults;
import com.payoneer.checkout.utils.Loader;
import com.payoneer.checkout.viewmodel.PaymentMethodsViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Applicable> paymentMethods;
    PaymentMethodsAdapter methodAdapter;
    Toolbar toolbar;
    private PaymentMethodsViewModel paymentMethodViewModel;
    RecyclerView recyclerView;
    FrameLayout noInternet;
    Loader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the UI
        initUi();

        //Loading the Data from API
        initData();

        noInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //retry loading the data
                initData();
            }
        });

        //toolbar back arrow
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Initializing the UI
    private void initUi() {
        paymentMethods = new ArrayList<>();
        methodAdapter = new PaymentMethodsAdapter(this, paymentMethods);
        recyclerView = findViewById(R.id.payment_recyclerview);
        noInternet=findViewById(R.id.frame_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loader=new Loader();
        toolbar=findViewById(R.id.tool_bar);
    }

    //Loading the Data from API
    private void initData() {
        loader.show(getSupportFragmentManager(),"api_call_loading");
        paymentMethodViewModel= ViewModelProviders.of(this).get(PaymentMethodsViewModel.class);
        paymentMethodViewModel.getPaymentMethodsObserver().observe(this, linkResults -> {
            loader.dismiss();
            //checking to ensure that the returned data is not null
            if (linkResults!=null){

                //Populate the recycler view
                paymentMethods.addAll(linkResults.getNetworks().getApplicable());
                recyclerView.setAdapter(methodAdapter);
            }else {
                //the API returned null, due to network timeout
                noInternet.setVisibility(View.VISIBLE);
            }
        });

        //make API call
        paymentMethodViewModel.getPaymentMethods();
    }
}