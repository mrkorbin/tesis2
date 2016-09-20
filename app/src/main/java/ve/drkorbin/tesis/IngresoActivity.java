package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IngresoActivity extends AppCompatActivity {

    EditText usuario;
    EditText clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        usuario = (EditText) findViewById(R.id.editTextUsuarioIng);
        clave = (EditText) findViewById(R.id.editText2PaswIng);

       //TODO: Crear funcionalidad de login y validarla contra la bd

    }

    public void openLogActivity(View view) {
        Intent toLogActivity = new Intent(getApplicationContext(), LogActivity.class);
        startActivity(toLogActivity);

    }


    public void openPrincipalActivity(View view) {
        Intent toPrincipalActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipalActivity);

    }

    
}