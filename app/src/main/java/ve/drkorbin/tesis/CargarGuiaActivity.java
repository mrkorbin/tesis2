package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CargarGuiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_guia);
    }

    public void openAdminPrincipalActivity(View view) {
        Intent toAdminPrincipalActivity = new Intent(getApplicationContext(), AdminPrincipalActivity.class);
        startActivity(toAdminPrincipalActivity);

    }
}