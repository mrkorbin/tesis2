package ve.drkorbin.tesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.adapters.RookieGuidesAdapter;
import ve.drkorbin.tesis.utils.TesisConstants;

public class GenericListGuideActivity extends AppCompatActivity{

    ListView listViewGuides;
    RookieGuidesAdapter adapter;
    ArrayList<Guide> allGuides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_list_guide);

        allGuides = new ArrayList<Guide>();

        if (getIntent().getExtras().get(TesisConstants.GUIDE_LIST) != null) {

            allGuides = (ArrayList<Guide>) getIntent().getExtras().get(TesisConstants.GUIDE_LIST);
        }

        paintGuideList();


    }

    public void openPrincipalActivity(View view) {
        Intent toPrincipaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipaActivity);

    }

    private void paintGuideList(){
        listViewGuides = (ListView) findViewById(R.id.listViewGuides);
        adapter = new RookieGuidesAdapter(this, allGuides);
        listViewGuides.setAdapter(adapter);
        listViewGuides.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("","SE piso la guia");
                Guide guide = (Guide) parent.getItemAtPosition(position);
                Intent intentToGuide = new Intent(GenericListGuideActivity.this, GuideShow.class);
                intentToGuide.putExtra(TesisConstants.GUIDE, guide);
                startActivity(intentToGuide);
            }
        });

    }

}
