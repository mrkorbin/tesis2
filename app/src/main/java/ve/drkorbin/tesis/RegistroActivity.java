package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ve.drkorbin.tesis.entities.User;
import ve.drkorbin.tesis.persister.FireBasePersister;

public class RegistroActivity extends AppCompatActivity {

        EditText nombre;
        EditText usuario;
        EditText clave;
        EditText correo;
    Button registo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //EditText
        nombre = (EditText)findViewById(R.id.editTextnombre);
        usuario = (EditText)findViewById(R.id.editTextusuario);
        clave = (EditText)findViewById(R.id.editTextcontrasena);
        correo = (EditText)findViewById(R.id.editTextcorreo);
        //Boton
        registo = (Button)findViewById(R.id.buttonregistro);

    }


    public void openLogActivity(View view) {
        Intent toLogActivity = new Intent(getApplicationContext(), LogActivity.class);
        startActivity(toLogActivity);

    }

    public void openPrincipalActivity(View view) {
        Intent toPrincipalActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipalActivity);

    }

    public void singUpUser(View view){
        User userToRegister = new User(usuario.toString(),nombre.toString(),clave.toString(),correo.toString());
        FireBasePersister fireBasePersister = new FireBasePersister(this);
        fireBasePersister.validateAndRegister(userToRegister);


    }

//falta mensaje de error por usuario registrado



}
