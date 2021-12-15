package com.example.myweather;


import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;
//关注城市列表的数据库
public class CitySQL {
    private MySQHelper dbHelper;
    private SQLiteDatabase db;

    public CitySQL(Context context) {
        dbHelper = new MySQHelper(context, "CitySQ", null, 1);
        db = dbHelper.getWritableDatabase();
    }

    public CitySQL(Thread thread) {

    }
    //关注城市添加
    public void add(City city) {
        try{
            ContentValues cValue = new ContentValues();
            cValue.put("name",city.getName());
            cValue.put("isSelect",city.getIsSelect());
            db.insert("CitySQ",null, cValue);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //更改关注城市选中或删除的状态
    public void update(City city) {
        try {
            ContentValues cValue = new ContentValues();
            if (city.getIsSelect().equals("否")) {
                cValue.put("isSelect", "是");
            } else {
                cValue.put("isSelect", "否");
            }
            String whereClause = "name=?";
            String[] whereArgs = {city.getName()};
            db.update("CitySQ", cValue, whereClause, whereArgs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //删除关注城市
    public void delete(String name) {
        try{
            db.execSQL("delete from CitySQ where name=?", new String[] { name });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<City> getItemCity() {
        ArrayList<City> citys = new ArrayList<City>();
        Cursor c = db.rawQuery("select * from CitySQ order by isSelect desc", null);
        while (c.moveToNext()) {
            City city = new City();
            city.setName(c.getString(0));
            city.setIsSelect(c.getString(1));
            citys.add(city);
        }
        c.close();
        return citys;
    }
    public City ChangeInfomat(String city_name){
        City city=new City(city_name);
        return city;
    }
    public City getIsSelectCity() {
        Cursor c = db.rawQuery("select * from CitySQ", null);
        City city = null;
        String s1 = "",s2 = "";
        while (c.moveToNext()) {
            s1 = c.getString(0);
            s2 = c.getString(1);
            if(s2.equals("是")){
                break;
            }
        }
        if(s2.equals("是")){
            city = new City(s1,"是");
        }
        c.close();
        if(city == null){
            city = new City("随县 随州 湖北");
            city.setIsSelect("是");
            add(city);
        }
        return city;
    }

    public int getIsExist(String name){
        Cursor c = db.rawQuery("select * from CitySQ", null);
        String s1,s2;
        while (c.moveToNext()) {
            City city = new City();
            s1 = c.getString(0);
            s2 = c.getString(1);
            if(s1.equals(name)){
                c.close();
                return 1;
            }
        }
        c.close();
        return 0;
    }
}
