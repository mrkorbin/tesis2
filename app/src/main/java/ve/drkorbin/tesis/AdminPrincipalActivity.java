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


}
