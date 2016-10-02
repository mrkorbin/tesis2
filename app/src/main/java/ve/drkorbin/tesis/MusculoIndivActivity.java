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
import ve.drkorbin.tesis.entities.MuscleEnum;
import ve.drkorbin.tesis.entities.adapters.RookieGuidesAdapter;
import ve.drkorbin.tesis.persister.FireBasePersister;
import ve.drkorbin.tesis.utils.FireBaseCallBack;
import ve.drkorbin.tesis.utils.TesisConstants;

public class MusculoIndivActivity extends AppCompatActivity implements FireBaseCallBack {

    private String muscleSelected;
    ListView listViewRookGuides;
    RookieGuidesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musculo_indiv);
    }

    public void openMuscSeleccActivity(View view) {

        switch (view.getId()) {
            case R.id.buttonBicep:
                muscleSelected = MuscleEnum.BICEP.getDescripcion();
                break;
            case R.id.buttonTricep:

                muscleSelected = MuscleEnum.TRICEP.getDescripcion();
                break;
            case R.id.buttonCuadricep:

                muscleSelected = MuscleEnum.CUADRICEP.getDescripcion();
                break;
            case R.id.buttonFemoral:

                muscleSelected = MuscleEnum.FEMORAL.getDescripcion();
                break;
            case R.id.buttonPectoral:

                muscleSelected = MuscleEnum.PECTORAL.getDescripcion();
                break;

        }

        FireBasePersister fireBasePersister = new FireBasePersister(this);
        fireBasePersister.getGuidesByMuscle(muscleSelected);

    }

    public void openPrincipalActivity(View view) {
        Intent toPrincipaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipaActivity);

    }

    @Override
    public void getUpdateFromBD(Object obj) {
        ArrayList<Guide> allGuides = new ArrayList<Guide>((List) obj);
        Intent intentToGenericListGuideActivity = new Intent(getApplicationContext(), GenericListGuideActivity.class);
        intentToGenericListGuideActivity.putExtra(TesisConstants.GUIDE_LIST, allGuides);
        startActivity(intentToGenericListGuideActivity);
    }
}
