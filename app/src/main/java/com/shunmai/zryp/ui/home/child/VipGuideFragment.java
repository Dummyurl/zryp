package com.shunmai.zryp.ui.home.child;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shunmai.zryp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VipGuideFragment extends Fragment {


    public VipGuideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vip_guide, container, false);
    }

}