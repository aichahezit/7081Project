package bcit.aicha.hezit.a7081project.Activites.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import bcit.aicha.hezit.a7081project.Activites.Database.DatabaseHelper;
import bcit.aicha.hezit.a7081project.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
    }

    public void patientView(View view) {

        Intent intent;

        String[] users = db.checkForUser();

        //check if at least one patient exists in database
        if(users.length == 0){
            //if not:
            intent = new Intent(this, EnterItemActivity.class);
        }else{
            intent = new Intent(this, ViewItemActivity.class);
        }

        intent.putExtra(EXTRA_MESSAGE, "patient");
        startActivity(intent);
    }

    public void doctorView(View view) {
        //check if at least one doctor exists in database
        Intent intent;

        String doctors[] = db.getDoctorNames();

        if(doctors.length == 0) {
            //if not:
            intent = new Intent(this, EnterItemActivity.class);
        }else {
            intent = new Intent(this, ViewListActivity.class);
        }

        intent.putExtra(EXTRA_MESSAGE, "doctor");
        startActivity(intent);
    }

    public void visitView(View view) {
        //check if at least one visit exists in database

        Intent intent;

        String visits[] = db.getVisitNames();

        if(visits.length == 0) {
            //if not:
            intent = new Intent(this, EnterItemActivity.class);
        }else {
            intent = new Intent(this, ViewListActivity.class);
        }

        intent.putExtra(EXTRA_MESSAGE, "visit");
        startActivity(intent);
    }
}
