package ve.drkorbin.tesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.adapters.RookieGuidesAdapter;
import ve.drkorbin.tesis.persister.FireBasePersister;
import ve.drkorbin.tesis.utils.FireBaseCallBack;
import ve.drkorbin.tesis.utils.TesisConstants;

public class RutinaPrincipianteActivity extends AppCompatActivity implements FireBaseCallBack {

    ListView listViewRookGuides;
    RookieGuidesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_principiante);

        FireBasePersister fireBasePersister = new FireBasePersister(this);
        fireBasePersister.getAllGuides();


    }


    public void openPrincipalActivity(View view) {
        Intent toPrincipaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipaActivity);
            
    }

    @Override
    public void getUpdateFromBD(Object obj) {
        ArrayList<Guide> allGuides = new ArrayList<Guide>((List) obj);
        listViewRookGuides = (ListView) findViewById(R.id.listViewRookieGuides);
        adapter = new RookieGuidesAdapter(this, allGuides);
        listViewRookGuides.setAdapter(adapter);
        listViewRookGuides.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Guide guide = (Guide) parent.getItemAtPosition(position);
                Intent intentToGuide = new Intent(getApplicationContext(), GuideShow.class);
                intentToGuide.putExtra(TesisConstants.GUIDE, guide);
                startActivity(intentToGuide);
//                Log.d("Prueba", "se piso la lista");
            }
        });

    }


}
