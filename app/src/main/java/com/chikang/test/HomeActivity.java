package com.chikang.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HomeActivity extends AppCompatActivity {

    TextView fullName, logout;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        fAuth = FirebaseAuth.getInstance();

        fullName = findViewById(R.id.usernameHome);
        logout = findViewById(R.id.logoutTxtHome);
        if (fAuth.getCurrentUser() == null) {
            fullName.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        } else {
            login = findViewById(R.id.loginBtnHome);
            login.setVisibility(View.GONE);

            fStore = FirebaseFirestore.getInstance();
            userId = fAuth.getCurrentUser().getUid();

            DocumentReference documentReference = fStore.collection("users").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                    if (documentSnapshot.exists()) {
                        fullName.setText(documentSnapshot.getString("fName")+", ");

                    } else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }
                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fAuth.signOut();//logout
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            });
        }
    }

    public void StartButtonClick(View view) {
        Intent game = new Intent(this, GameActivity.class);

        startActivity(game);
    }
    public void SettingButtonClick(View view) {
        Intent setting = new Intent(this, SettingActivity.class);

        startActivity(setting);
    }
    public void LeaderboardButtonClick(View view) {
        Intent lead = new Intent(this, LeaderboardActivity.class);

        startActivity(lead);
    }
    public void HistoryButtonClick(View view) {
        Intent hist = new Intent(this, HistoryActivity.class);

        startActivity(hist);
    }
    public void LoginButtonClick(View view) {
        Intent login = new Intent(this, Login.class);

        startActivity(login);
    }
}
