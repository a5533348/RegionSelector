package cn.xdeveloper.regionselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.xdeveloper.regionselector.db.RegionDao;

/**
 * 地区选择
 * Created by Laiyimin on 2016/9/7.
 */
public class RegionSelectActivity extends AppCompatActivity implements BaseQuickAdapter.OnRecyclerViewItemClickListener {

    public static final String REGION_PROVINCE = "region_province";
    public static final String REGION_CITY = "region_city";
    public static final String REGION_AREA = "region_area";
    private static final int RESULT_CODE_SUCCESS = 200;


    private RecyclerView mRecyclerView;
    private RegionAdapter mAdapter;
    private RegionDao mRegionDao;

    private List<RegionModel> mList;

    private List<RegionModel> mProvinceList;
    private List<RegionModel> mCityList;
    private List<RegionModel> mAreaList;
    private int state = 0;

    private String mProvince;
    private String mCity;
    private String mArea;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_region);

        mRegionDao = new RegionDao(this);

        mList = new ArrayList<>();
        mAdapter = new RegionAdapter(mList);
        mAdapter.setOnRecyclerViewItemClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        setTitle("选择省份");
        mProvinceList = mRegionDao.loadProvinceList();
        mAdapter.addData(mProvinceList);
    }

    @Override
    public void onItemClick(View view, int position) {
        RegionModel region = mAdapter.getItem(position);

        if (state == 0) {
            setTitle("选择城市");
            mCityList = mRegionDao.loadCityList(region.getId());

            mList.clear();
            mAdapter.addData(mCityList);

            mProvince = region.getName();
            state++;
        } else if (state == 1) {
            setTitle("选择地区");
            mAreaList = mRegionDao.loadCountyList(region.getId());
            mCity = region.getName();

            if (mAreaList.size() == 0) {
                //防止有的城市没有县级
                finishSelect();

            } else {
                mList.clear();
                mAdapter.addData(mAreaList);

                state++;
            }


        } else if (state == 2) {
            mArea = region.getName();
            state++;

            finishSelect();
        }
    }

    /**
     * 完成
     */
    private void finishSelect() {
        Intent data = new Intent();
        data.putExtra(REGION_PROVINCE, mProvince);
        data.putExtra(REGION_CITY, mCity);
        data.putExtra(REGION_AREA, mArea);

        setResult(RESULT_CODE_SUCCESS, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (state == 0) {
            super.onBackPressed();
        }

        if (state == 1) {
            setTitle("选择省份");
            mList.clear();
            mAdapter.addData(mProvinceList);
            state--;
        } else if (state == 2) {
            setTitle("选择城市");
            mList.clear();
            mAdapter.addData(mCityList);
            state--;
        }
    }
}
