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

public class AdminPrincipalActivity extends AppCompatActivity implements FireBaseCallBack {

    FireBasePersister fireBasePersister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_principal);
    }

    public void openLogActivity(View view) {
        Intent toLogActivity = new Intent(getApplicationContext(), LogActivity.class);
        startActivity(toLogActivity);

    }

    public void openCargarGuiaActivity(View view) {
        Intent openCargarGuiaActivity = new Intent(getApplicationContext(), CargarGuiaActivity.class);
        startActivity(openCargarGuiaActivity);

    }

    public void openEliminarGuiaActivity(View view) {
        fireBasePersister = new FireBasePersister(this);
        fireBasePersister.getAllGuides();
    }


    public void openEliminarUsuarioActivity(View view) {
        Intent openEliminarUsuarioActivity = new Intent(getApplicationContext(), EliminarUsuarioActivity.class);
        startActivity(openEliminarUsuarioActivity);

    }

    public void openAdminBuzonActivity(View view) {
        Intent openAdminBuzonActivity = new Intent(getApplicationContext(), AdminBuzonActivity.class);
        startActivity(openAdminBuzonActivity);

    }

    @Override
    public void getUpdateFromBD(Object obj) {
        ArrayList<Guide> allGuides = new ArrayList<Guide>((List) obj);
        Intent intentToGenericListGuideActivity = null;
        intentToGenericListGuideActivity = new Intent(getApplicationContext(), GenericListGuideActivity.class);

        intentToGenericListGuideActivity.putExtra(TesisConstants.GUIDE_LIST, allGuides);
        intentToGenericListGuideActivity.putExtra(TesisConstants.IS_ADMIN, true);
        startActivity(intentToGenericListGuideActivity);
    }
}
