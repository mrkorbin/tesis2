package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MusculoIndivActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musculo_indiv);
    }

    public void openMuscSeleccActivity(View view) {
        Intent toMuscSeleccActivity = new Intent(getApplicationContext(), MuscSeleccActivity.class);
        startActivity(toMuscSeleccActivity);

    }

}
