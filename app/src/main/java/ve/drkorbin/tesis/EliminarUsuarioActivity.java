package ve.drkorbin.tesis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.User;
import ve.drkorbin.tesis.persister.FireBasePersister;
import ve.drkorbin.tesis.utils.FireBaseCallBack;

public class EliminarUsuarioActivity extends AppCompatActivity implements FireBaseCallBack {

    ArrayAdapter<String> adapter;
    ListView listView;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);
        listView = (ListView) findViewById(R.id.listViewDeleteUser);


        FireBasePersister fireBasePersister = new FireBasePersister(this);
        fireBasePersister.getAllUser();


    }

    @Override
    public void getUpdateFromBD(Object obj) {
        ArrayList<User> userArrayList = new ArrayList<User>((List) obj);


        String array[] = new String[userArrayList.size()];
        for(int j =0;j<userArrayList.size();j++){
            array[j] = ((User) userArrayList.get(j)).getUserName();
        }

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array);

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String nameUser = (String) parent.getItemAtPosition(position);
                final    User user = new User();
                user.setUserName(nameUser);

                AlertDialog.Builder builder = new AlertDialog.Builder(EliminarUsuarioActivity.this);
                builder.setMessage("Desea eliminar el Usuario");
                builder.setTitle("Informacion");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FireBasePersister fireBasePersister = new FireBasePersister(EliminarUsuarioActivity.this);
                        fireBasePersister.deleteUser(user);

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

    public void openAdminPrincipalActivity(View view) {
        Intent toAdminPrincipalActivity = new Intent(getApplicationContext(), AdminPrincipalActivity.class);
        startActivity(toAdminPrincipalActivity);

    }


}