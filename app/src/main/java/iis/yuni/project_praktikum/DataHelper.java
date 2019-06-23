package iis.yuni.project_praktikum;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bulanan.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table pengeluaran(no integer primary key AUTOINCREMENT, nama text null, nominal integer null, tanggal text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        String sql2 = "create table saldo(id integer primary key AUTOINCREMENT, mysaldo integer null);";
        Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql2);

        String insert ="insert into saldo(id, mysaldo) values('1', '50000');";
        db.execSQL(insert);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
