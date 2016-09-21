package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.adapters.RookieGuidesAdapter;
import ve.drkorbin.tesis.persister.FireBasePersister;

public class RutinaPrincipianteActivity extends AppCompatActivity {

    ListView listViewRookGuides;
    RookieGuidesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_principiante);

        FireBasePersister fireBasePersister = new FireBasePersister(this);
        ArrayList<Guide> allGuides = new ArrayList<Guide>();
        fireBasePersister.getAllGuides(allGuides);

        listViewRookGuides = (ListView) findViewById(R.id.listViewRookieGuides);
        adapter = new RookieGuidesAdapter(this, allGuides);
        listViewRookGuides.setAdapter(adapter);
    }


    public void openPrincipalActivity(View view) {
        Intent toPrincipaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipaActivity);

    }
}
