package com.payoneer.checkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.payoneer.checkout.R;
import com.payoneer.checkout.model.Applicable;

import java.util.ArrayList;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder> {
    ArrayList<Applicable> paymentOptionItems = new ArrayList();
    Context context;
    @NonNull
    @Override
    public PaymentMethodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_method_item, null);
        return new ViewHolder(view);
    }

    // Constructor
    public PaymentMethodsAdapter(Context context, ArrayList<Applicable> optionsArrayList){
        this.paymentOptionItems=optionsArrayList;
        this.context=context;

    }

    //Binding views
    @Override
    public void onBindViewHolder(@NonNull PaymentMethodsAdapter.ViewHolder holder, int position) {
        Applicable options = paymentOptionItems.get(position);

        //
        holder.label.setText(options.getLabel());

        //load Image URL into holder.logo using Glide

        Glide.with(context)
                .load(options.getLinks().get("logo"))
                .into(holder.logo);

        //toggle showing input fields
        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.infoView.getVisibility()==View.VISIBLE){
                    holder.infoView.setVisibility(View.GONE);
                }   else{
                    holder.infoView.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentOptionItems.size();
    }

    //initializing views
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        ImageView logo;
        RelativeLayout infoView;
        LinearLayout parentLinear;
        public ViewHolder(@NonNull View v) {
            super(v);
            label=v.findViewById(R.id.label);
            logo=v.findViewById(R.id.logo);
            infoView=v.findViewById(R.id.info_view);
            parentLinear=v.findViewById(R.id.parentLinear);
        }
    }
}