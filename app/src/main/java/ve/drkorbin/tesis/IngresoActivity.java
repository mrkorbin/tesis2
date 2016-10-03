package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ve.drkorbin.tesis.entities.User;
import ve.drkorbin.tesis.persister.FireBasePersister;
import ve.drkorbin.tesis.utils.FireBaseCallBack;

public class IngresoActivity extends AppCompatActivity implements FireBaseCallBack {

    EditText usuario;
    EditText clave;
    boolean isAdmin;

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
        User user = new User();
        user.setUserName(usuario.getText().toString());
        user.setPassword(clave.getText().toString());

        FireBasePersister fireBasePersister = new FireBasePersister(this);
        fireBasePersister.searchUser(user);

    }

    @Override
    public void getUpdateFromBD(Object obj) {
        if (obj != null) {
            User user = (User) obj;
            isAdmin = user.isAdmin();
            Intent toPrincipalActivity;

            if (isAdmin) {
                toPrincipalActivity = new Intent(getApplicationContext(), AdminPrincipalActivity.class);
            } else {
                toPrincipalActivity = new Intent(getApplicationContext(), PrincipalActivity.class);


            }
            startActivity(toPrincipalActivity);
        }else {
            Toast.makeText(this,"Usuario no Encontrado",Toast.LENGTH_LONG).show();
        }

    }
}