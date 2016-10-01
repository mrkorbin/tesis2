package ve.drkorbin.tesis;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this, LogActivity.class);
                MainActivity.this.startActivity(mainIntent);
            }
        }, 4000);

    }
//aun en pruebas

    public void openLogActivity(View view) {
        Intent toLogActivity = new Intent(getApplicationContext(), LogActivity.class);
        startActivity(toLogActivity);

    }

}
