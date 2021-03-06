package com.hackathon.apps.nfcreader.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.hackathon.apps.nfcreader.GlobalData;
import com.hackathon.apps.nfcreader.OffersActivty;
import com.hackathon.apps.nfcreader.OffersAdapter;
import com.hackathon.apps.nfcreader.R;
import com.hackathon.apps.nfcreader.model.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.view.View.GONE;
import static com.hackathon.apps.nfcreader.R.layout.offers_fragment;

/**
 * Created by ruthvik on 21/09/2017.
 */

public class OffersFragment extends Fragment implements View.OnClickListener {

    TextView title;
    ListView offersList;
    Button showMore;
    int showCount;
    OffersAdapter adapter;
    boolean isOffers = false;

    private static final String LIST_COUNT = "ListCount";
    private static final String IS_OFFERS = "isOffers";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(LIST_COUNT))
                showCount = savedInstanceState.getInt(LIST_COUNT);
            if(savedInstanceState.containsKey(IS_OFFERS))
                isOffers = savedInstanceState.getBoolean(IS_OFFERS);
        }
        else if(getArguments() != null){
            showCount = getArguments().getInt(LIST_COUNT, 2);
            isOffers = getArguments().getBoolean(IS_OFFERS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.offers_fragment, container, false);
        title = (TextView) view.findViewById(R.id.fragment_title);
        offersList = (ListView) view.findViewById(R.id.mylist);
        showMore = (Button) view.findViewById(R.id.load_more);

        if(showCount > 2)
            showMore.setVisibility(GONE);

        ArrayList<Product> products = new ArrayList<Product>();
        if(isOffers) {
            adapter = new OffersAdapter(getContext(), GlobalData.promotions, showCount);
        } else {
//            adapter = new OffersAdapter(getContext(), GlobalData.coupons);
        }
        offersList.setAdapter(adapter);

        showMore.setOnClickListener(this);
        return view;
    }

    public static OffersFragment NewInstance(int showCount, boolean isOffers){
        OffersFragment offersFragment = new OffersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LIST_COUNT, showCount);
        bundle.putBoolean(IS_OFFERS, isOffers);
        offersFragment.setArguments(bundle);
        return  offersFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load_more:
                Toast.makeText(getContext(), "load more clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), OffersActivty.class);
                startActivity(intent);
                break;
        }
    }
}
