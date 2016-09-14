package ve.drkorbin.tesis.persister;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ve.drkorbin.tesis.R;
import ve.drkorbin.tesis.entities.User;

/**
 * Created by parcka on 14/09/16.
 */
public class FireBasePersister {


    private static final String TAG = FireBasePersister.class.getSimpleName();
    FirebaseDatabase mPostReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public void getUser(User user){
        final User[] userReturned = {new User()};
        database = FirebaseDatabase.getInstance();
        DatabaseReference mPostReference = database.getReference("clave");
        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                userReturned[0] = dataSnapshot.getValue(User.class);
                Log.d(TAG, "Value is: " + userReturned[0]);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}
