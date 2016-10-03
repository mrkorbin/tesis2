package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ve.drkorbin.tesis.entities.Suggestion;
import ve.drkorbin.tesis.persister.FireBasePersisterSuggestion;

public class ContactanosActivity extends AppCompatActivity {

EditText email;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);

        email = (EditText) findViewById(R.id.editTextEmail);
        message = (EditText) findViewById(R.id.editTextMessage);

    }

    public void openPrincipalActivity(View view) {
        Intent toPrincipaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(toPrincipaActivity);

    }

    public void registerSuggestion(View view){
        Suggestion suggestion = new Suggestion();
        suggestion.setEmail(email.getText().toString());
        suggestion.setDescription(message.getText().toString());

        FireBasePersisterSuggestion fireBasePersisterSuggestion = new FireBasePersisterSuggestion(this);
        fireBasePersisterSuggestion.registerSuggestion(suggestion);
        clearAll();
    }

    private void clearAll() {
        email.setText("");
        message.setText("");
    }

}
