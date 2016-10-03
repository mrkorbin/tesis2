package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.MuscleEnum;
import ve.drkorbin.tesis.persister.FireBasePersister;
import ve.drkorbin.tesis.utils.FireBaseCallBack;
import ve.drkorbin.tesis.utils.TesisConstants;

public class PrincipalActivity extends AppCompatActivity implements FireBaseCallBack {

    int elementClicked;
    FireBasePersister fireBasePersister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        fireBasePersister = new FireBasePersister(this);


    }


    public void openMuscIndivActivity(View view) {
        Intent toMuscIndivActivity = new Intent(getApplicationContext(), MusculoIndivActivity.class);
        startActivity(toMuscIndivActivity);

    }

    public void openRutinaPrincActivity(View view) {
        fireBasePersister.getGuidesByLevel(MuscleEnum.NEWBIE_GUIDE.getDescripcion());
        elementClicked = view.getId();
    }


    public void openRutinaAvanzActivity(View view) {
        fireBasePersister = new FireBasePersister(this);
        fireBasePersister.getGuidesByLevel(MuscleEnum.ADVANCE_GUIDE.getDescripcion());
    }


    public void openContactanosActivity(View view) {
        Intent openContactanosActivity = new Intent(getApplicationContext(), ContactanosActivity.class);
        startActivity(openContactanosActivity);

    }

    public void openAdminPrincipalActivity(View view) {
        Intent openAdminPrincipalActivity = new Intent(getApplicationContext(), AdminPrincipalActivity.class);
        startActivity(openAdminPrincipalActivity);

    }

    @Override
    public void getUpdateFromBD(Object obj) {
        ArrayList<Guide> allGuides = new ArrayList<Guide>((List) obj);
        Intent intentToGenericListGuideActivity = null;
        intentToGenericListGuideActivity = new Intent(getApplicationContext(), GenericListGuideActivity.class);

        intentToGenericListGuideActivity.putExtra(TesisConstants.GUIDE_LIST, allGuides);
        startActivity(intentToGenericListGuideActivity);
    }


}
