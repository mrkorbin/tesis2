package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }


    public void openMuscIndivActivity(View view) {
        Intent toMuscIndivActivity = new Intent(getApplicationContext(), MusculoIndivActivity.class);
        startActivity(toMuscIndivActivity);

    }

    public void openRutinaPrincActivity(View view) {
        Intent openRutinaPrincActivity = new Intent(getApplicationContext(), RutinaPrincipianteActivity.class);
        startActivity(openRutinaPrincActivity);

    }


    public void openRutinaAvanzActivity(View view) {
        Intent openRutinaAvanzActivity = new Intent(getApplicationContext(), RutinaAvanzadaActivity.class);
        startActivity(openRutinaAvanzActivity);

    }


    public void openContactanosActivity(View view) {
        Intent openContactanosActivity = new Intent(getApplicationContext(), ContactanosActivity.class);
        startActivity(openContactanosActivity);

    }


}
