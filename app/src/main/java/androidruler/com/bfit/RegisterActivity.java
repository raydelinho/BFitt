package androidruler.com.bfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password;
    Button  registerButton;
    String usernameHolder,passwordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    DatabaseHelper databaseHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //hier maak ik die register knop/en edittexts bruikbaar door de id's op te roepen

        registerButton = (Button)findViewById(R.id.RegisterButtonRegi);

        username = (EditText)findViewById(R.id.RegisterUsernameEditText);
        password = (EditText)findViewById(R.id.RegisterPasswordEditText);



        //ik maak een object van die databasehelper class
        databaseHelper = new DatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating SQLite database if dose n't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if dose n't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                // Method to check Email is already exists or not.
                CheckingEmailAlreadyExistsOrNot();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();


            }
        });






    }
    //maak de edittext leeg na je hebt geregistreerd
    private void EmptyEditTextAfterDataInsert() {
        //maak edittext username leeg
        username.getText().clear();
        //maak editext password leeg
        password.getText().clear();

    }

    private void CheckingEmailAlreadyExistsOrNot() {
        //opening database writepermission
        sqLiteDatabaseObj=databaseHelper.getWritableDatabase();

        //adding search username to query
        cursor=sqLiteDatabaseObj.query(DatabaseHelper.TableName,null,""+DatabaseHelper.User_name + "=?", new String[]{usernameHolder},null,null,null);

        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();
                //als email al voorkomt set emailfound
                F_Result = "username Found";
                //close cursor
                cursor.close();
            }
        }
        //
        CheckFinalResult();
    }

    private void CheckFinalResult() {
        //Checking als email al bestaat
        if (F_Result.equalsIgnoreCase("username Found")){
            // If username exists then toast msg will display.
            Toast.makeText(RegisterActivity.this,"username Already Exists", Toast.LENGTH_LONG).show();
        }
        else {
            //als username niet bestaat
            InsertDataIntoSQLiteDatabase();
        }
        F_Result = "Not_Found" ;
    }

    private void InsertDataIntoSQLiteDatabase() {
        long a;
        //pas als edittext niet leeg is gaat dit executen
        if (EditTextEmptyHolder==true){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",usernameHolder);
            contentValues.put("password",passwordHolder);

            a=databaseHelper.insert(DatabaseHelper.TableName,contentValues,DatabaseHelper.User_id);

            if (a>0){
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
            }
            sqLiteDatabaseObj.close();
        }
    }

    private void CheckEditTextStatus() {
        //hier pak ik de ingevoerde text en convert het tot string en sla het op voor die string variable
        usernameHolder=username.getText().toString();
        passwordHolder=password.getText().toString();
        
        if (TextUtils.isEmpty(usernameHolder)||TextUtils.isEmpty(passwordHolder)){
            EditTextEmptyHolder=false;
        }
        else {
            EditTextEmptyHolder=true;
        }
    }

    private void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + DatabaseHelper.TableName + "(" + DatabaseHelper.User_id + " PRIMARY KEY AUTOINCREMENT NOT NULL, " +  DatabaseHelper.User_name + " VARCHAR, " + DatabaseHelper.User_password + " VARCHAR);");
    }

    private void SQLiteDataBaseBuild() {
        sqLiteDatabaseObj =openOrCreateDatabase(DatabaseHelper.DatabaseName, Context.MODE_PRIVATE,null);
    }
}
