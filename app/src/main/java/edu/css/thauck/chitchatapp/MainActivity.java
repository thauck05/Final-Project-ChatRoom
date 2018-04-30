package edu.css.thauck.chitchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private static final int RC_SIGN_IN = 123;

    /*
     * Checks if user is signed in. If user is, displays chat room.
     * If user is not, redirects user to sign-in or sign-up screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in or sign up activity
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), RC_SIGN_IN);
        } else {
            // User is already signed in, display a welcome Toast
            Toast.makeText(this, "Welcome " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), Toast.LENGTH_LONG).show();
            // Load chat room contents
            displayChatMessages();
        }
    }

    private void displayChatMessages() {

    }

    /*
     * Override to handle result of an inten
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            // Sign in successfully
            if(resultCode == RESULT_OK) {
                Toast.makeText(this, "Welcome! You are signed in.", Toast.LENGTH_LONG).show();
                displayChatMessages();
            } else {
                // Failed login
                Toast.makeText(this, "Login failed. Please try again.", Toast.LENGTH_LONG).show();
                // Close the app
                finish();
            }
        }

    }
}
