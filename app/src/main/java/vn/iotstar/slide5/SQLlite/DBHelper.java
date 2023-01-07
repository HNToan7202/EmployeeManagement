package vn.iotstar.slide5.SQLlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static  final  String DB_NAME="Demo06"; //ten csdl
    public static final int DB_VERSION = 2; // phien ban csdl

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Truy vấn tạo ra bảng
        String sql = "CREATE TABLE nhanvien(id text primary key, name text not null, salary real not null)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Nâng cấp
        String sql = "DROP TABLE IF EXISTS nhanvien";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
