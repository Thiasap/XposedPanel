package com.bit747.xposedpanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class moduleAdapter extends BaseAdapter {
    private List<moduleData> mDatas = null;
    private Context mContext = null;

    public moduleAdapter(Context context, List<moduleData> datas){
        mDatas = datas;
        mContext = context;
    }
    @Override//获取适配器中数据集中数据的条目数
    public int getCount() {
        return mDatas.size();
    }

    @Override//获取数据集中与指定索引对应的数据项
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override//获取数据集中指定索引对应的项的id
    public long getItemId(int i) {
        return i;
    }

    @Override//获取指定索引的列表Item的view
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null){
            vh = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_main, null);
            vh.moduleName = view.findViewById(R.id.tv_item_name);
            view.setTag(vh);
        } else {
            vh = (ViewHolder)view.getTag();
        }
        vh.moduleName.setText(mDatas.get(i).moduleName);

        return view;
    }

    public final class ViewHolder
    {
        public TextView id;
        public TextView moduleName;
    }

}
