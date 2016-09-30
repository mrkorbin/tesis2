package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminPrincipalActivity extends AppCompatActivity {

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
        Intent openEliminarGuiaActivity = new Intent(getApplicationContext(), EliminarGuiaActivity.class);
        startActivity(openEliminarGuiaActivity);

    }


    public void openEliminarUsuarioActivity(View view) {
        Intent openEliminarUsuarioActivity = new Intent(getApplicationContext(), EliminarUsuarioActivity.class);
        startActivity(openEliminarUsuarioActivity);

    }

    public void openAdminBuzonActivity(View view) {
        Intent openAdminBuzonActivity = new Intent(getApplicationContext(), AdminBuzonActivity.class);
        startActivity(openAdminBuzonActivity);

    }


}
