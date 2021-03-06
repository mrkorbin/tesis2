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
import java.util.List;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.MuscleEnum;
import ve.drkorbin.tesis.entities.User;
import ve.drkorbin.tesis.utils.FireBaseCallBack;

/**
 * Created by parcka on 14/09/16.
 */
public class FireBasePersister {

    ProgressDialog progressDialog;
    Dialog alertDialog;

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

        referenceUsersChild.addListenerForSingleValueEvent(
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


                        }

                        if (userFound) {
                            Toast.makeText(activityFromCall, "Usuario ya existe en en BD", Toast.LENGTH_LONG).show();
                        } else {
                            dataSnapshot.getRef().child(user.getUserName()).setValue(user);
                            Toast.makeText(activityFromCall, "Usuario Creado", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "validateAndRegister:onCancelled", databaseError.toException());
                    }
                });


        return userformBd;
    }

    public void getAllGuides() {
        final ArrayList<Guide> guideArrayList = new ArrayList<Guide>();
        progressDialog.show();

        DatabaseReference referenceGuidesChild = database.getReference("guides");

        referenceGuidesChild.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {
                            Guide guideInBd = null;
                            try {
                                guideInBd = data.getValue(Guide.class);
                            } catch (Exception e) {
                                Log.e(TAG, "Error parseando el objeto de la BD " + e);
                            }
                            guideArrayList.add(guideInBd);

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

    public void getAllUser() {
        final ArrayList<User> userArrayList = new ArrayList<User>();
        progressDialog.show();

        DatabaseReference referenceGuidesChild = database.getReference("users");

        referenceGuidesChild.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {
                            User userInBd = null;
                            try {
                                userInBd = data.getValue(User.class);
                            } catch (Exception e) {
                                Log.e(TAG, "Error parseando el objeto de la BD " + e);
                            }
                            userArrayList.add(userInBd);

                        }
                        ((FireBaseCallBack) activityFromCall).getUpdateFromBD(userArrayList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "validateAndRegister:onCancelled", databaseError.toException());
                    }
                });

        progressDialog.dismiss();

    }


    public void searchUser(final User user) {

        progressDialog.show();

        DatabaseReference referenceGuidesChild = database.getReference("users");

        referenceGuidesChild.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User userFound = null;
                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {
                            User userInBd = null;

                            userInBd = data.getValue(User.class);
                            if (userInBd.getUserName().equals(user.getUserName())
                                    && userInBd.getPassword().equals(user.getPassword())) {
                                userFound = userInBd;
                            }


                        }
                        ((FireBaseCallBack) activityFromCall).getUpdateFromBD(userFound);
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

        referenceGuidesChild.addListenerForSingleValueEvent(
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

    public void deleteGuide(final Guide guide) {
        progressDialog.show();


        DatabaseReference referenceGuidesChild = database.getReference("guides");

        referenceGuidesChild.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Guide guideInBd = null;
                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {

                            guideInBd = data.getValue(Guide.class);
                            if (guideInBd.getTitulo() != null && guideInBd.getTitulo().equals(guide.getTitulo())) {
                                data.getRef().removeValue();
                                Toast.makeText(activityFromCall, "Guia Eliminada", Toast.LENGTH_LONG).show();

                            }

                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "validateAndRegister:onCancelled", databaseError.toException());
                    }
                });

        progressDialog.dismiss();

    }

    public void deleteUser(final User user) {
        progressDialog.show();


        DatabaseReference referenceGuidesChild = database.getReference("users");

        referenceGuidesChild.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User userInBd = null;
                        for (DataSnapshot data :
                                dataSnapshot.getChildren()) {

                            userInBd = data.getValue(User.class);
                            if (userInBd.getUserName() != null && userInBd.getUserName().equals(user.getUserName())) {
                                data.getRef().removeValue();
                                Toast.makeText(activityFromCall, "Usuario Eliminada", Toast.LENGTH_LONG).show();

                            }

                        }
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

        referenceGuidesChild.addListenerForSingleValueEvent(
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
