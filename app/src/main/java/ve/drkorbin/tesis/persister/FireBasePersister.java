package ve.drkorbin.tesis.persister;

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
import java.util.List;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.User;

/**
 * Created by parcka on 14/09/16.
 */
public class FireBasePersister {

    ProgressDialog progressDialog;

    private static final String TAG = FireBasePersister.class.getSimpleName();
    FirebaseDatabase mPostReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    AppCompatActivity activityFromCall;

    public FireBasePersister() {
    }

    public FireBasePersister(AppCompatActivity activityFromCall) {
        this.activityFromCall = activityFromCall;
        progressDialog = new ProgressDialog(activityFromCall);
        progressDialog.setMessage("Cargando...");

    }

    public User validateAndRegister(final User user) {

        final User userformBd = null;

        DatabaseReference referenceUsersChild = database.getReference("users");

        referenceUsersChild.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean userFound = false;
                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {

                            User userformBd = data.getValue(User.class);
                            Log.d(TAG, "El usuario enconado" + userformBd.toString());
                            if (userformBd.getUserName().equals(user.getUserName())) {
                                userFound = true;
                            }

                            if (userFound) {
                                Toast.makeText(activityFromCall, "Usuario en BD", Toast.LENGTH_LONG).show();
                            } else {
                                dataSnapshot.getRef().child(user.getUserName()).setValue(user);
                            }
                            progressDialog.dismiss();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "validateAndRegister:onCancelled", databaseError.toException());
                    }
                });


        return userformBd;
    }

    public void getAllGuides(final ArrayList<Guide> guideArrayList) {
        progressDialog.show();
        final User guideformBd = null;
        List<Guide> listGuides;

        DatabaseReference referenceGuidesChild = database.getReference("guides");

        referenceGuidesChild.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean userFound = false;
                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {

                            Guide guideInBd = data.getValue(Guide.class);
                            guideArrayList.add(guideInBd);

                            progressDialog.dismiss();

                        }
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

    }

    public void setUser(User user) {
        progressDialog.show();

        user = new User();
        user.setUserName("userName1");
        user.setFullName("fullName");
        user.setPassword("11111");
        user.setEmail("rman@asd.com");

        if (validateAndRegister(user) != null) {
            System.out.printf("USUARIO YA EXISTE");
            Log.d("TAG: ", "Usuario ya existe");
        }
      /*  DatabaseReference usersReferenceBd = database.getReference("users");

        DatabaseReference specificUserRef = usersReferenceBd.child(user.getUserName());
        specificUserRef.setValue(user);*/

    }


}
