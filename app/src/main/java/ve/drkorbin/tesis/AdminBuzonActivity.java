package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminBuzonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_buzon);
    }


    public void openAdminPrincipalActivity(View view) {
        Intent openAdminPrincipalActivity = new Intent(getApplicationContext(), AdminPrincipalActivity.class);
        startActivity(openAdminPrincipalActivity);

    }


}
