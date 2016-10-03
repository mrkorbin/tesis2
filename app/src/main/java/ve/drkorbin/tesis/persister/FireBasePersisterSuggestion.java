package ve.drkorbin.tesis.persister;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.MuscleEnum;
import ve.drkorbin.tesis.entities.Suggestion;
import ve.drkorbin.tesis.entities.User;
import ve.drkorbin.tesis.utils.FireBaseCallBack;

/**
 * Created by parcka on 02/10/16.
 */
public class FireBasePersisterSuggestion {

    ProgressDialog progressDialog;
    Dialog alertDialog;

    private static final String TAG = FireBasePersister.class.getSimpleName();
    FirebaseDatabase mPostReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    AppCompatActivity activityFromCall;

    public FireBasePersisterSuggestion() {
    }

    public FireBasePersisterSuggestion(AppCompatActivity activityFromCall) {
        this.activityFromCall = activityFromCall;
        progressDialog = new ProgressDialog(activityFromCall);
        progressDialog.setMessage("Cargando...");

    }

    public void registerSuggestion(final Suggestion suggestion) {

        DatabaseReference referenceUsersChild = database.getReference("suggestion");

        referenceUsersChild.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean userFound = false;
                        DatabaseReference pushRef = dataSnapshot.getRef().push();
                        pushRef.setValue(suggestion);
                        Toast.makeText(activityFromCall,"Sugerencia Enviada",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "validateAndRegister:onCancelled", databaseError.toException());
                    }
                });
    }

    public void getAllSuggestion() {
        final ArrayList<Suggestion> suggestionArrayList = new ArrayList<Suggestion>();
        progressDialog.show();

        DatabaseReference referenceGuidesChild = database.getReference("suggestion");

        referenceGuidesChild.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {
                            Suggestion suggestionInBd = null;
                            try {
                                suggestionInBd = data.getValue(Suggestion.class);
                            } catch (Exception e) {
                                Log.e(TAG, "Error parseando el objeto de la BD " + e);
                            }
                            suggestionArrayList.add(suggestionInBd);

                        }
                        ((FireBaseCallBack) activityFromCall).getUpdateFromBD(suggestionArrayList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "validateAndRegister:onCancelled", databaseError.toException());
                    }
                });

        progressDialog.dismiss();

    }

    public void getGuidesByMuscle(final String muscle) {
        progressDialog.show();
        final ArrayList<Guide> guideArrayList = new ArrayList<Guide>();

        DatabaseReference referenceGuidesChild = database.getReference("guides");

        referenceGuidesChild.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Guide guideInBd = null;
                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {

                            guideInBd = data.getValue(Guide.class);
                            if (guideInBd.getMusculo() != null && guideInBd.getMusculo().equals(muscle)) {
                                guideArrayList.add(guideInBd);
                            }

                        }

                        ((FireBaseCallBack) activityFromCall).getUpdateFromBD(guideArrayList);
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "validateAndRegister:onCancelled", databaseError.toException());
                    }
                });

        progressDialog.dismiss();

    }

    public void getGuidesByLevel(final String level) {
        progressDialog.show();

        final ArrayList<Guide> guideArrayList = new ArrayList<Guide>();

        DatabaseReference referenceGuidesChild = database.getReference("guides");

        referenceGuidesChild.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Guide guideInBd = null;
                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {

                            guideInBd = data.getValue(Guide.class);

                            if (level.equals(MuscleEnum.ADVANCE_GUIDE.getDescripcion())) {

                                if (guideInBd.getAdvanceGuide()) {
                                    guideArrayList.add(guideInBd);
                                }

                            } else if (level.equals(MuscleEnum.NEWBIE_GUIDE.getDescripcion())) {

                                if (guideInBd.getBasicGuide()) {
                                    guideArrayList.add(guideInBd);
                                }

                            }

                        }

                        ((FireBaseCallBack) activityFromCall).getUpdateFromBD(guideArrayList);
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "validateAndRegister:onCancelled", databaseError.toException());
                    }
                });

        progressDialog.dismiss();

    }

    public void createGuideInBd(final Guide guide) {

        final Guide guideformBd = null;

        DatabaseReference referenceGuidesChild = database.getReference("guides");
        referenceGuidesChild.child(guide.getTitulo()).setValue(guide);
        AlertDialog.Builder builder = new AlertDialog.Builder(activityFromCall);
        builder.setMessage("Registro Guardado");
        builder.setTitle("Informacion");
        alertDialog = builder.create();
        alertDialog.show();
    }

}
