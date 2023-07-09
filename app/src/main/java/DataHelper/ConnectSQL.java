package DataHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ConnectSQL extends SQLiteOpenHelper {
    public static final String DBName = "Login.db";
    public ConnectSQL(Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("Create table users(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {
        Mydb.execSQL("drop table if exists users");
    }
    public Boolean insertData(String username,String password){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = Mydb.insert("users", null,contentValues);
        if(result==1) return false;
        else
            return true;

    }
    public Boolean checkusername(String username){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("select * from users where usename = ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkpassword(String username,String password){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("select * from users where username = ? and password = ?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
}
