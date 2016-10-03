package ve.drkorbin.tesis;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import ve.drkorbin.tesis.persister.FireBasePersister;
import ve.drkorbin.tesis.utils.TesisConstants;

public class GenericListGuideActivity extends AppCompatActivity {

    ListView listViewGuides;
    RookieGuidesAdapter adapter;
    ArrayList<Guide> allGuides;
    boolean isAdmin;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_list_guide);

        allGuides = new ArrayList<Guide>();

        if (getIntent().getExtras().get(TesisConstants.GUIDE_LIST) != null) {

            allGuides = (ArrayList<Guide>) getIntent().getExtras().get(TesisConstants.GUIDE_LIST);
        }

        if (getIntent().getExtras().get(TesisConstants.IS_ADMIN) != null) {

            isAdmin = (boolean) getIntent().getExtras().get(TesisConstants.IS_ADMIN);
        }
//        isAdmin = true; // todo: prueba
        adapter = new RookieGuidesAdapter(this, allGuides);
        paintGuideList();


    }

    public void openPrincipalActivity(View view) {
        Intent toPrincipaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipaActivity);

    }

    private void paintGuideList() {
        listViewGuides = (ListView) findViewById(R.id.listViewGuides);


        listViewGuides.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("error en el click", "SE piso la guia");
                Guide guide = (Guide) parent.getItemAtPosition(position);
                Intent intentToGuide = new Intent(GenericListGuideActivity.this, GuideShow.class);
                intentToGuide.putExtra(TesisConstants.GUIDE, guide);
                startActivity(intentToGuide);
            }
        });

        if (isAdmin) {

            listViewGuides.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final Guide guide = (Guide) parent.getItemAtPosition(position);

                    AlertDialog.Builder builder = new AlertDialog.Builder(GenericListGuideActivity.this);
                    builder.setMessage("Desea eliminar la Guia");
                    builder.setTitle("Informacion");
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FireBasePersister fireBasePersister = new FireBasePersister(GenericListGuideActivity.this);
                            fireBasePersister.deleteGuide(guide);

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();


                    return false;
                }
            });


        }


        listViewGuides.setAdapter(adapter);


    }

}
