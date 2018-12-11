package com.shunmai.zryp.ui.userinfo.account;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.bean.addrbean.RegionBean;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.ShareUtils;
import com.ysy.commonlib.utils.StringUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.view.addressdialog.AddressSelector;
import com.shunmai.zryp.view.addressdialog.BottomDialog;
import com.shunmai.zryp.view.addressdialog.OnAddressSelectedListener;
import com.shunmai.zryp.view.wheel.OnWheelChangedListener;
import com.shunmai.zryp.view.wheel.WheelView;
import com.shunmai.zryp.view.wheel.XmlParserHandler;
import com.shunmai.zryp.view.wheel.adapters.ArrayWheelAdapter;
import com.shunmai.zryp.view.wheel.adapters.CityModel;
import com.shunmai.zryp.view.wheel.adapters.DistrictModel;
import com.shunmai.zryp.view.wheel.adapters.ProvinceModel;
import com.shunmai.zryp.view.wheel.adapters.RevertAddressEntity;
import com.shunmai.zryp.viewmodel.AddressDetailViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityAddressDetailBinding;
import com.ysy.commonlib.base.TResponse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AddressDetailActivity extends SwipeBackActivity<ActivityAddressDetailBinding> implements OnWheelChangedListener, View.OnClickListener, onResponseListener<TResponse<String>>, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener, AddressSelector.onSelectorAreaPositionListener {
    /**
     * 所有省
     */
    protected String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<>();

    /**
     * key - 区 values - 邮编
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<>();

    /**
     * 当前省的名称
     */
    protected String mCurrentProviceName;
    /**
     * 当前市的名称
     */
    protected String mCurrentCityName;
    /**
     * 当前区的名称
     */
    protected String mCurrentDistrictName = "";

    /**
     * 当前区的邮政编码
     */
    protected String mCurrentZipCode = "";

    /**
     * 是否为修改地址
     */
    private boolean isChange = false;

    private int regionType = 1;
    private RegionBean province;
    private RegionBean city;
    private RegionBean county;
    private RegionBean street;
    private String provinceName;
    private String cityName;
    private String countyName;
    private String streetName;
    private RevertAddressEntity addressEntity = new RevertAddressEntity();
    private AddressDetailViewModel viewModel;
    private AddressListBean.DataBean dataBean;
    private BottomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_detail);
        viewModel = ViewModelProviders.of(this).get(AddressDetailViewModel.class);
        initData();
        showContentView();
        bindingView.tvAddress.setOnClickListener(this);
        bindingView.btnSubmit.setOnClickListener(this::onClick);
        String data = getIntent().getStringExtra("data");
        regionType = getIntent().getIntExtra("regionType", 1);
        if (data != null && !data.equals("")) {
            dataBean = new Gson().fromJson(data, AddressListBean.DataBean.class);
            bindingView.etName.setText(dataBean.getUsername());
            bindingView.etPhone.setText(dataBean.getMobile());
            bindingView.tvAddress.setText(dataBean.getAddr());
            bindingView.etAddress.setText(dataBean.getDetailAddress());
            isChange = true;
        }
    }

    private void initData() {
        // 添加change事件
        bindingView.idProvince.addChangingListener(this);
        // 添加change事件
        bindingView.idCity.addChangingListener(this);
        // 添加change事件
        bindingView.idDistrict.addChangingListener(this);
        setUpData();
    }

    private void setUpData() {
        initProvinceDatas();
        bindingView.idProvince.setViewAdapter(new ArrayWheelAdapter<>(this, mProvinceDatas));
        // 设置可见条目数量
        bindingView.idProvince.setVisibleItems(7);
        bindingView.idCity.setVisibleItems(7);
        bindingView.idDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    private void initDatas() {

        if (addressEntity != null) {
            String _name = addressEntity.getConsignee();
            String _mobile = addressEntity.getMobile();
            String _pro = addressEntity.getProvince();
            String _city = addressEntity.getCity();
            String _con = addressEntity.getConsignee();
            String _ad = addressEntity.getAddress();
            if (!StringUtils.isEmptyString(_name)) {
                bindingView.etName.setText(_name);
            }
            if (!StringUtils.isEmptyString(_mobile)) {
                bindingView.etPhone.setText(_mobile);
            }
            if (!StringUtils.isEmptyString(_pro)) {
                String _s = _pro + " " + _city + " " + _con;
                bindingView.tvAddress.setText(_s);
            }
            if (!StringUtils.isEmptyString(_ad)) {
                bindingView.etAddress.setText(_ad);
            }
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = bindingView.idCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        bindingView.idDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        bindingView.idDistrict.setCurrentItem(0);

        //县
        int districtIndex = bindingView.idDistrict.getCurrentItem();
        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[districtIndex];
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = bindingView.idProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        bindingView.idCity.setViewAdapter(new ArrayWheelAdapter<>(this, cities));
        bindingView.idCity.setCurrentItem(0);
        updateAreas();
    }

    /**
     * 解析省市区的XML数据
     */

    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }


    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == bindingView.idProvince) {
            updateCities();
        } else if (wheel == bindingView.idCity) {
            updateAreas();
        } else if (wheel == bindingView.idDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
        bindingView.tvAddress.setText(mCurrentProviceName + " " + mCurrentCityName + " " + mCurrentDistrictName);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_address: {
                hideKeyboard(this);
//                if (bindingView.llWheel.getVisibility() == View.VISIBLE) {
//                    bindingView.llWheel.setVisibility(View.GONE);
//                } else {
//                    bindingView.llWheel.setVisibility(View.VISIBLE);
//                }
                if (dialog != null) {
                    dialog.show();
                } else {
                    dialog = new BottomDialog(this, viewModel, regionType);
                    dialog.setOnAddressSelectedListener(this);
                    dialog.setDialogDismisListener(this);
                    dialog.setTextSize(14);//设置字体的大小
                    dialog.setIndicatorBackgroundColor(R.color.fontRed);//设置指示器的颜色
                    dialog.setTextSelectedColor(R.color.fontNormal);//设置字体获得焦点的颜色
                    dialog.setTextUnSelectedColor(R.color.fontRed);//设置字体没有获得焦点的颜色
//            dialog.setDisplaySelectorArea("31",1,"2704",1,"2711",0,"15582",1);//设置已选中的地区
                    dialog.setSelectorAreaPositionListener(this);
                    dialog.show();
//                    if (dataBean!=null&&canSelect){
//                        dialog.setDisplaySelectorArea(provinceName,dataBean.getProvinceCode(),cityName,dataBean.getCityCode(),countyName,dataBean.getCountryCode(),streetName,dataBean.getTownCode());
//                        canSelect=false;
//                    }
                }
                break;
            }
            case R.id.btn_submit: {
                HashMap<String, Object> data = checkEmpty();
                if (data != null) {
                    if (!isChange) {
                        viewModel.addAddress(data, this);
                    } else {
                        viewModel.changeAddress(data, this);
                    }
                }
            }
        }
    }

    private HashMap<String, Object> checkEmpty() {
        String name = bindingView.etName.getText().toString().trim();
        String phone = bindingView.etPhone.getText().toString().trim();
        String address_detail = bindingView.etAddress.getText().toString().trim();
        String address = bindingView.tvAddress.getText().toString().trim();
        if (!name.equals("")) {
            if (!phone.equals("")) {
                if (!address_detail.equals("")) {
                    if (!address.equals("省、市、区")) {
                        if (province != null && city != null && county != null) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("username", name);
                            hashMap.put("mobile", phone);
                            hashMap.put("postcode", mCurrentZipCode);
                            hashMap.put("detailAddress", address_detail);
                            hashMap.put("isDefault", 0);
                            hashMap.put("provinceCode", province.getId());
                            hashMap.put("cityCode", city.getId());
                            hashMap.put("countryCode", county.getId());
                            hashMap.put("isOutAddress", regionType);
                            if (street == null) {
                                hashMap.put("townCode", 0);
                                hashMap.put("addr", province.getName() + " " + city.getName() + " " + county.getName() + " ");
                            } else {
                                hashMap.put("townCode", street.getId());
                                hashMap.put("addr", province.getName() + " " + city.getName() + " " + county.getName() + " " + street.getName() + " ");
                            }
                            hashMap.put("userid", ShareUtils.getUserInfo().getUserId());
                            if (isChange) {
                                hashMap.put("id", dataBean.getId());
                            }
                            return hashMap;
                        } else {
                            ToastUtils.showToast("请重新选择收货地区！");
                        }
                    } else {
                        ToastUtils.showToast("请选择收货地区！");
                    }
                } else {
                    ToastUtils.showToast("请输入详细收货地址！");
                }
            } else {
                ToastUtils.showToast("请输入手机号码！");
            }

        } else {
            ToastUtils.showToast("请输入收货人名称！");
        }
        return null;
    }

    /**
     * 隐藏键盘的方法
     *
     * @param context
     */
    public static void hideKeyboard(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);               // 隐藏软键盘
        imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
    }


    @Override
    public void onSuccess(TResponse<String> stringTResponse) {
        ToastUtils.showToast("提交成功！");
        setResult(RESULT_OK);
        onBackPressed();
    }

    @Override
    public void onFailed(Throwable throwable) {
        ToastUtils.showToast("地址提交错误");
        throwable.printStackTrace();
    }

    @Override
    public void onAddressSelected(RegionBean province, RegionBean city, RegionBean county, RegionBean street) {
        this.province = province;
        this.city = city;
        this.county = county;
        this.street = street;
        String addrStr="";
        if (this.street != null) {
            addrStr = province.getName() + " " + city.getName() + " " + county.getName() + " " + street.getName() + " ";
        } else if (province != null && city != null && county != null) {
            addrStr = province.getName() + " " + city.getName() + " " + county.getName() + " ";
        }
        if (dialog != null) {
            dialog.dismiss();
        }
        bindingView.tvAddress.setText(addrStr);
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void selectorAreaPosition(int provincePosition, int cityPosition, int countyPosition, int streetPosition) {

    }
}
