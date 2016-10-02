package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MuscSeleccActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musc_selecc);
    }


    public void openPrincipalActivity(View view) {
        Intent toPrincipaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipaActivity);

    }


    public void openMuscIndivActivity(View view) {




        Intent toMuscIndivActivity = new Intent(getApplicationContext(), MusculoIndivActivity.class);
        startActivity(toMuscIndivActivity);

    }

}