package com.example.igenieesolution.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "GeniePlex.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TBL_DEVICE_MASTER = "CREATE TABLE tblDeviceMaster (" +
                "RecID INTEGER PRIMARY KEY," +
                "DeviceID TEXT NOT NULL," +
                "DeviceType TEXT NOT NULL," +
                "ModelName TEXT NOT NULL," +
                "alias TEXT NOT NULL," +
                "CategoryID INTEGER," +
                "Remark TEXT NOT NULL" +
                ");";
        db.execSQL(CREATE_TBL_DEVICE_MASTER);

        // Insert initial data
        String INSERT_DATA = "INSERT INTO tblDeviceMaster (RecID, DeviceID, DeviceType, ModelName, alias, CategoryID, Remark) VALUES" +
                "(1, '2c8b817311aec171815494bb467f674f685b533913c487c6d169873c630d353e', 'DEVICE_REFRIGERATOR', '2RETGLVTCN__2', 'Refrigerator', 1, 'Home')," +
                "(2, '5ea5268f7cc437c9903bd6bbbe1fdc32c5dfa85935c027fc8f571b12bb63603c', 'DEVICE_AIR_CONDITIONER', 'RAC_056905_WW', 'Air Conditioner', 2, 'Office Work Station')," +
                "(5, 'c695870f2478056cb2a630e46d3c2a101cc8e93ecfe6a83fca0c4a51e504f1f0', 'DEVICE_WASHER', 'TLVN1CA', 'Top Load Washer', 3, 'Washing Machine');";
        db.execSQL(INSERT_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tblDeviceMaster");
        onCreate(db);
    }

    @SuppressLint("Range")

    public String getDeviceID(String deviceType) {
        SQLiteDatabase db = this.getReadableDatabase();
        String deviceID = null;

        String query = "SELECT DeviceID FROM tblDeviceMaster WHERE DeviceType = ?";
        Cursor cursor = db.rawQuery(query, new String[]{deviceType});

        if (cursor != null && cursor.moveToFirst()) {
            deviceID = cursor.getString(cursor.getColumnIndex("DeviceID"));
            cursor.close();
        }

        db.close();
        return deviceID;
    }
}
