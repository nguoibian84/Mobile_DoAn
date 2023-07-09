package com.example.doan_oderfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "Login.db";

    public DBHelper( Context context) {

        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("Create table users(username TEXT primary key,password TEXT,isadmin TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int i, int i1) {
        Mydb.execSQL("drop Table if exists users");
    }
    public Boolean insertData(String username,String password,String isadmin){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("isadmin",isadmin);
        long result = Mydb.insert("users", null,contentValues);
        if(result==1) return false;
        else
            return true;

    }
    public Boolean checkusername(String username){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("select * from users where username = ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkpassword(String username,String password){

        Boolean kq = false;
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("select username,password from users where username = ? and password = ?",new String[]{username,password});
        if(cursor.getCount()>0)
            kq = true;
        return kq;
    }

    public boolean checkisAdmin(String username) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("select isadmin FROM users WHERE username = ? ", new String[]{username});
        if (cursor.moveToFirst()) {
            int isadminColumnIndex = cursor.getColumnIndex("isadmin");
            String isadmin = cursor.getString(isadminColumnIndex);
            return "0".equals(isadmin); // Kiểm tra xem trường isAdmin có giá trị bằng "1" hay không
        }
        return false;
    }
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        SQLiteDatabase Mydb = this.getReadableDatabase();
        Cursor cursor = Mydb.rawQuery("SELECT * FROM users", null);

        if (cursor.moveToFirst()) {
            int user = cursor.getColumnIndex("username");
            int pass = cursor.getColumnIndex("password");
            int isad = cursor.getColumnIndex("isadmin");
            do {
                String username = cursor.getString(user);
                String password = cursor.getString(pass);
                String isAdmin = cursor.getString(isad);

                Account account = new Account(username, password, isAdmin);
                accounts.add(account);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return accounts;
    }
    public void deleteAccount(Account account) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Mydb.delete("users", "username = ?", new String[]{account.getUsername()});
        Mydb.close();
    }
    public void updateAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());
        values.put("isadmin", account.getIsadmin());

        db.update("users", values, "username = ?", new String[]{account.getUsername()});

        db.close();
    }
}
