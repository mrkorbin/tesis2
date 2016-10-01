package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.MuscleEnum;
import ve.drkorbin.tesis.persister.FireBasePersister;

public class CargarGuiaActivity extends AppCompatActivity {

    EditText guideDescription;
    EditText urlYoutube;
    EditText titleGuide;
    String muscleSelected;
    CheckBox checkAdvance;
    CheckBox checkRookie;
    FireBasePersister fireBasePersister;
    Guide guideToInsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_guia);

        guideDescription = (EditText) findViewById(R.id.editTextContenidoguia);
        urlYoutube = (EditText) findViewById(R.id.editTextCargarLinkVideo);
        titleGuide = (EditText) findViewById(R.id.editTextnombreguia);

        checkAdvance = (CheckBox) findViewById(R.id.checkBoxGuiaAvanzada);
        checkRookie = (CheckBox) findViewById(R.id.checkBoxGuiaPrincipiante);


        fireBasePersister = new FireBasePersister(this);





    }

    private Guide createAndSetGuide() {
        Guide guide = new Guide();
        guide.setMusculo(muscleSelected);
        guide.setUrl(urlYoutube.getText().toString());
        guide.setDescripcion(guideDescription.getText().toString());
        guide.setTitulo(titleGuide.getText().toString());
        setTypeGuideFromCheck(guide);

        return guide;
    }

    private void setTypeGuideFromCheck(Guide guide) {
        if (checkRookie.isChecked())
            guide.setAdvanceGuide(true);

        if (checkAdvance.isChecked())
            guide.setBasicGuide(true);
    }

    public void openAdminPrincipalActivity(View view) {
        Intent toAdminPrincipalActivity = new Intent(getApplicationContext(), AdminPrincipalActivity.class);
        startActivity(toAdminPrincipalActivity);

    }

    public void saveGuideToBd(View view) {

        guideToInsert = createAndSetGuide();
        fireBasePersister.createGuideInBd(guideToInsert);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButtonBicep:
                if (checked)
                    muscleSelected = MuscleEnum.BICEP.getDescripcion();
                break;
            case R.id.radioButtonTricep:
                if (checked)
                    muscleSelected = MuscleEnum.TRICEP.getDescripcion();
                break;
            case R.id.radioButtonCuadriceps:
                if (checked)
                    muscleSelected = MuscleEnum.CUADRICEP.getDescripcion();
                break;
            case R.id.radioButtonFemoral:
                if (checked)
                    muscleSelected = MuscleEnum.FEMORAL.getDescripcion();
                break;
            case R.id.radioButtonPectoral:
                if (checked)
                    muscleSelected = MuscleEnum.PECTORAL.getDescripcion();
                break;

        }
    }


    //TODO: al momento de presionar cargar, se debe mostrar un mensaje indicando que se ha cargado la guia o que ya la misma esta cargada y al salvar se debe volver al meuu de administrador


}