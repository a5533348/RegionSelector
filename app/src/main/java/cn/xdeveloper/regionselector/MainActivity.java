package cn.xdeveloper.regionselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.xdeveloper.regionselector.util.DBCopyUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REGION_REQUEST_CODE = 001;

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = (TextView) findViewById(R.id.tv_result);
        findViewById(R.id.btn_select).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_select:
                DBCopyUtil.copyDataBaseFromAssets(this, "region.db");

                startActivityForResult(new Intent(this, RegionSelectActivity.class), REGION_REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REGION_REQUEST_CODE && resultCode == 200) {
            String province = data.getStringExtra(RegionSelectActivity.REGION_PROVINCE);
            String city = data.getStringExtra(RegionSelectActivity.REGION_CITY);
            String area = data.getStringExtra(RegionSelectActivity.REGION_AREA);

            tv_result.setText(province + " " + city + " " + area);
        }
    }
}
