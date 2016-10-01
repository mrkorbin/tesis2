package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GuiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);
    }

    public void openPrincipalActivity(View view) {
        Intent toPrincipaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipaActivity);

    }

}
