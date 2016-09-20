package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.User;
import ve.drkorbin.tesis.persister.FireBasePersister;

public class LogActivity extends AppCompatActivity {

    private static final String TAG = LogActivity.class.getSimpleName();
    FirebaseDatabase mPostReference;

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
        FireBasePersister fireBasePersister = new FireBasePersister(this);

        Guide guide = new Guide();
        guide.setTitulo("Prueba titulo");
        guide.setDescripcion("Prueba Descripcion");
        guide.setUrl("URL del video");
        guide.setMusculo("Prueba Musculo");

        fireBasePersister.createGuideInBd(guide);
        Intent toIngresoActivity = new Intent(getApplicationContext(), IngresoActivity.class);
        startActivity(toIngresoActivity);

    }


}