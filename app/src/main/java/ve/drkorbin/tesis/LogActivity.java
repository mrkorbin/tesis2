package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    

    public void openRegistroActivity(View view) {
        Intent toregistroactivity = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(toregistroactivity);

    }


    public void openIngresoActivity(View view) {
        Intent toIngresoActivity = new Intent(getApplicationContext(), IngresoActivity.class);
        startActivity(toIngresoActivity);

    }




}