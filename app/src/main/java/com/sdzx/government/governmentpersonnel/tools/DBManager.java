package com.sdzx.government.governmentpersonnel.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class DBManager {
    private String DB_NAME = "sqlite.db";
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }
    //把assets目录下的db文件复制到dbpath下
    public SQLiteDatabase DBManager(String packName) {
        String dbPath = "/data/data/" + packName
                + "/databases/" + DB_NAME;
        File file=new File(dbPath);
        Boolean is=file.exists();
        if (file.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = mContext.getAssets().open("sqlite.db");
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1)
                    out.write(buffer, 0, readBytes);
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }
//    //查询
//    public City query(SQLiteDatabase sqliteDB, String[] columns, String selection, String[] selectionArgs) {
//        City city = null;
//        try {
//            String table = "city";
//            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null, null);
//            if (cursor.moveToFirst()) {
//                String parentCity = cursor.getString(cursor
//                        .getColumnIndex("parent"));
//                String phoneCode = cursor.getString(cursor.getColumnIndex("phone_code"));
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//                String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
//                String cityID = cursor.getString(cursor.getColumnIndex("posID"));
//                String areaCode = cursor.getString(cursor.getColumnIndex("area_code"));
//                city = new City(parentCity, name, pinyin, phoneCode, cityID, areaCode);
//                cursor.moveToNext();
//                cursor.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return city;
//    }
}
