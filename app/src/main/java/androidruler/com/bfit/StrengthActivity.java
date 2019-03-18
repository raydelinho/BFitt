package androidruler.com.bfit;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class StrengthActivity extends AppCompatActivity {

    //public static FragmentManager fragmentManager;
    Button loadFragmentBeginner,loadFragmentIntermediate;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);

        loadFragmentBeginner =(Button)findViewById(R.id.beginner);
        loadFragmentIntermediate =(Button)findViewById(R.id.intermediate);

        loadFragmentBeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.change,new BlankFragment());
                fragmentTransaction.commit();
            }
        });

    }
}
