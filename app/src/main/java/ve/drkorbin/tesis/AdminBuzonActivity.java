package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.Suggestion;
import ve.drkorbin.tesis.entities.adapters.RookieGuidesAdapter;
import ve.drkorbin.tesis.entities.adapters.SuggestionAdapter;
import ve.drkorbin.tesis.persister.FireBasePersister;
import ve.drkorbin.tesis.persister.FireBasePersisterSuggestion;
import ve.drkorbin.tesis.utils.FireBaseCallBack;
import ve.drkorbin.tesis.utils.TesisConstants;

public class AdminBuzonActivity extends AppCompatActivity implements FireBaseCallBack {

    ListView listViewSuggestion;
    SuggestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_buzon);


        FireBasePersisterSuggestion fireBasePersisterSuggestion = new FireBasePersisterSuggestion(this);
        fireBasePersisterSuggestion.getAllSuggestion();
    }


    public void openAdminPrincipalActivity(View view) {
        Intent openAdminPrincipalActivity = new Intent(getApplicationContext(), AdminPrincipalActivity.class);
        startActivity(openAdminPrincipalActivity);

    }

    @Override
    public void getUpdateFromBD(Object obj) {
        ArrayList<Suggestion> allSuggestion = new ArrayList<Suggestion>((List) obj);
        listViewSuggestion = (ListView) findViewById(R.id.listViewSuggestion);
        adapter = new SuggestionAdapter(this, allSuggestion);
        listViewSuggestion.setAdapter(adapter);
        listViewSuggestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              /*  Guide guide = (Guide) parent.getItemAtPosition(position);
                Intent intentToGuide = new Intent(getApplicationContext(), GuideShow.class);
                intentToGuide.putExtra(TesisConstants.GUIDE, guide);
                startActivity(intentToGuide);*/

            }
        });

    }


}
