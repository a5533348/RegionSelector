package cn.xdeveloper.regionselector;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Laiyimin on 2016/9/2.
 */
public class RegionAdapter extends BaseQuickAdapter<RegionModel> {

    public RegionAdapter(List<RegionModel> data) {
        super(R.layout.item_list_region, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, RegionModel regionModel) {
        holder.setText(R.id.name, regionModel.getName());
    }
}
