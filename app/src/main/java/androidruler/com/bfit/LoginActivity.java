package androidruler.com.bfit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginButton, registerButton;
    EditText username,password;
    String usernameHolder,passwordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    DatabaseHelper databaseHelper;
    Cursor cursor;
    String TempPassword="NOT FOUND";
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton=(Button)findViewById(R.id.LoginButton);
        registerButton=(Button)findViewById(R.id.RegisterButton);

        username =(EditText)findViewById(R.id.LoginUsernameEditText);
        password =(EditText)findViewById(R.id.LoginPasswordEditText);

        databaseHelper = new DatabaseHelper(this);

        //loginknop een functie geven
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                checkEditTextStatus();

                LoginFunction();


            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });




    }

    private void checkEditTextStatus() {
        usernameHolder = username.getText().toString();
        passwordHolder=password.getText().toString();

        if (TextUtils.isEmpty(usernameHolder)||TextUtils.isEmpty(passwordHolder)){
            EditTextEmptyHolder=false;
        }
        else {
            EditTextEmptyHolder=true;

        }

    }
    //hier is de login functie

    private void LoginFunction() {
        if(EditTextEmptyHolder){
            //Open writepermission database
            sqLiteDatabaseObj=databaseHelper.getWritableDatabase();
            cursor = sqLiteDatabaseObj.query(DatabaseHelper.TableName,null," "+ DatabaseHelper.User_name + "=?",new String[]{usernameHolder},null,null,null);

            while (cursor.moveToNext()){
                if (cursor.isFirst()) {
                    cursor.moveToFirst();

                    TempPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.User_password));
                    cursor.close();
                }
            }CheckFinalResult();
        }else {
            //If any of login EditText empty then this block will be executed.
            Toast.makeText(LoginActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();
        }

    }

    private void CheckFinalResult() {
        if (TempPassword.equalsIgnoreCase(passwordHolder)){
            Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            //proceed to dashb na succesvol inloggen
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            //intent.putExtra(usernameHolder,username);
            startActivity(intent);
        }
        else{
            Toast.makeText(LoginActivity.this,"UserName or Password is Wrong, Please Try Again.", Toast.LENGTH_LONG).show();
        }TempPassword="NOT_FOUND";
    }


}
