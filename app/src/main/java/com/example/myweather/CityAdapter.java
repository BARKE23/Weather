package com.example.myweather;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;

import java.util.List;

//关注的城市
public class CityAdapter extends BaseAdapter{
    private Context context;
    private List<City> listCity;
    private CitySQL citySQL;

    public CityAdapter(List<City> listCity, Context context){
        this.listCity = listCity;
        this.citySQL = new CitySQL(context);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){
        ViewHolder viewHolder = null;
        if(context == null){
            context = viewGroup.getContext();
        }
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_item,null); //获取item结构信息构建列表
            viewHolder = new ViewHolder();
            viewHolder.cityName = view.findViewById(R.id.cityName);
            viewHolder.cityDelete = view.findViewById(R.id.cityDelete);
            viewHolder.citySet = view.findViewById(R.id.citySet);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)view.getTag();
        viewHolder.cityName.setText(listCity.get(position).getName());
        viewHolder.citySet.setText("搜索");
        viewHolder.citySet.setOnClickListener(new View.OnClickListener() { //搜索城市的天气信息
            @Override
            public void onClick(View v) {
                Log.d("TAG", listCity.get(position).getName());
                Intent intent =new Intent(context, WeatherActivity.class);
                intent.putExtra("city_name",listCity.get(position).getName());
                context.startActivity(intent);
            }
        });
        viewHolder.cityDelete.setText("点击删除");
        viewHolder.cityDelete.setOnClickListener(new View.OnClickListener() { //删除关注的城市
            @Override
            public void onClick(View v) {
                citySQL = new CitySQL(context);
                citySQL.delete(listCity.get(position).getName());
                listCity = citySQL.getItemCity();
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public int getCount(){
        return listCity.size();
    }

    @Override
    public Object getItem(int i){
        return listCity.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    private class ViewHolder{
        TextView cityName;
        TextView cityDelete;
        TextView citySet;
    }
}
