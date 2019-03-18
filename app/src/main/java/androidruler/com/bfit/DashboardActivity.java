package androidruler.com.bfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    Button strengthButton, weatherButton,cardioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        weatherButton=(Button)findViewById(R.id.weatherButton);
        strengthButton=(Button)findViewById(R.id.strenghtButton);
        cardioButton=(Button)findViewById(R.id.cardioknop);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(DashboardActivity.this, WheatherActivity.class);
                startActivity(intent);

            }
        });
        strengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(DashboardActivity.this, StrengthActivity.class);
                startActivity(intent);

            }
        });

        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(DashboardActivity.this, WorkoutManager.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==R.id.logout){
            Intent intent = new Intent(DashboardActivity.this,LoginActivity.class);
            startActivity(intent);

            return false;

        }return super.onOptionsItemSelected(item);
    }
}
