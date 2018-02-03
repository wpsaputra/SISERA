package com.virtualbox.torchick.rog.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Lenovo 17 on 4/21/2016.
 */
public class DBHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "dbsisera.db";
    private static final int DATABASE_VERSION = 3; //+1

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }



}