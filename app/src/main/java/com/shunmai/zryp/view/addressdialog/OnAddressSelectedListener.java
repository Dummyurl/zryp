package com.shunmai.zryp.view.addressdialog;


import com.shunmai.zryp.bean.addrbean.RegionBean;

public interface OnAddressSelectedListener {
    void onAddressSelected(RegionBean province, RegionBean city, RegionBean county, RegionBean street);
}
