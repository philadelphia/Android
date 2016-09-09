package com.example.myapplication.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.BaseFragment;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Tao.ZT.Zhang on 2016/8/29.
 */
public class MyBaseAdapter extends BaseAdapter {
    private List<PackageInfo> datas;
    private LayoutInflater layoutInflater;
    public MyBaseAdapter(List<PackageInfo> installedPackages, Context context){
        this.datas = installedPackages;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 没有优化
     * @param position
     * @param view
     * @param viewGroup
     * @return
     */
//    @Override
//    public View getView(int position, View view, ViewGroup viewGroup) {
//        view = layoutInflater.inflate(R.layout.layout_packages,null);
//        TextView tv_pkgName;
//        TextView tv_pkgVersionCode;
//        TextView tv_pkgVersionName;
//
//        tv_pkgName = (TextView) view.findViewById(R.id.tv_pkgName);
//        tv_pkgVersionCode = (TextView) view.findViewById(R.id.tv_VersionCode);
//        tv_pkgVersionName = (TextView) view.findViewById(R.id.tv_VersionName);
//
//        tv_pkgName.setText(datas.get(position).packageName);
//        tv_pkgVersionCode.setText(String.valueOf(datas.get(position).versionCode));
//        tv_pkgVersionName.setText(datas.get(position).packageName);
//        return view;
//    }


    /**
     * 经过优化后效率提高
     * @param position
     * @param convertView
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();

            convertView = layoutInflater.inflate(R.layout.layout_packages,viewGroup,false);
            viewHolder.tv_pkgName = (TextView) convertView.findViewById(R.id.tv_pkgName);
            viewHolder.tv_pkgVersionCode = (TextView) convertView.findViewById(R.id.tv_VersionCode);
            viewHolder.tv_pkgVersionName = (TextView) convertView.findViewById(R.id.tv_VersionName);

            convertView.setTag(viewHolder);
        }else {
             viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.tv_pkgName.setText(datas.get(position).packageName);
        viewHolder.tv_pkgVersionCode.setText(String.valueOf(datas.get(position).versionCode));
        viewHolder.tv_pkgVersionName.setText(datas.get(position).packageName);


        return convertView;
    }


    public class ViewHolder {

        public TextView tv_pkgName;
        public TextView tv_pkgVersionCode;
        public TextView tv_pkgVersionName;
    }


}
