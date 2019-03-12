package androidruler.com.bfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database naam
    static final String DatabaseName="RoxtarSport";
    //table 1 naam
    public static final String TableName="user";
    //user informatie
    public static final String User_id="id";
    public static final String User_name="name";
    public static final String User_password="password";
    //constructor here
    public DatabaseHelper(Context context)
    {
        super(context,DatabaseName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        // script voor tabel user
        String CreateTable="CREATE TABLE IF NOT EXISTS " + TableName + "(" + User_id
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + User_name + " VARCHAR," + User_password + " VARCHAR" + ")";
        database.execSQL(CreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //Drop table if exists
        database.execSQL("DROP TABLE IF EXISTS " + TableName);
        //create the table again
        onCreate(database);

    }
    //Voeg gebruiker toe
    public int addUser(String username, String password)
    {
        // getting db instance for writing the user
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(User_name,username);
        contentValues.put(User_password,password);

        long value;
        value = db.update(DatabaseHelper.TableName,contentValues,User_name+"='"+username+ "'AND"+User_password+"='"+password+"'",null);
        // cv.put(User_id,usr.getId());
        //cv.put(User_name,usr.getName());
        //cv.put(User_password,usr.getPassword());
        //inserting row
        //db.insert(Table_Name, null, cv);
        //close the database to avoid any leak
        db.close();
        return(int)value;
    }

    /** public int checkUser(User us)
     {
     int id=-1;
     SQLiteDatabase db=this.getReadableDatabase();
     Cursor cursor=db.rawQuery("SELECT id FROM user WHERE name=? AND password=?",new String[]{us.getName(),us.getPassword()});
     if(cursor.getCount()>0) {
     cursor.moveToFirst();
     id=cursor.getInt(0);
     cursor.close();
     }
     return id;
     }**/
    public long insert(String table,ContentValues contentValues,String wherecolm){
        SQLiteDatabase database = getWritableDatabase();
        long a=database.insert(table,wherecolm,contentValues);
        return a;
    }
}
