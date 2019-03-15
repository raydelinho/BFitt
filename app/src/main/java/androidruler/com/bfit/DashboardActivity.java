package androidruler.com.bfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    Button loginButton, weatherButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        weatherButton=(Button)findViewById(R.id.weatherButton);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(DashboardActivity.this, WheatherActivity.class);
                startActivity(intent);

            }
        });
    }
}
