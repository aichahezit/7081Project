package bcit.aicha.hezit.a7081project.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import bcit.aicha.hezit.a7081project.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    public void patientView(View view) {
        //check if at least one patient exists in database

        //if not:
        Intent intent = new Intent(this, EnterItemActivity.class);

        //if yes open ViewListActivity

        intent.putExtra(EXTRA_MESSAGE, "patient");
        startActivity(intent);
    }

    public void doctorView(View view) {
        //check if at least one doctor exists in database

        //if not:
        Intent intent = new Intent(this, EnterItemActivity.class);

        //if yes open ViewListActivity

        intent.putExtra(EXTRA_MESSAGE, "doctor");
        startActivity(intent);
    }

    public void visitView(View view) {
        //check if at least one visit exists in database

        //if not:
        Intent intent = new Intent(this, EnterItemActivity.class);

        //if yes open ViewListActivity

        intent.putExtra(EXTRA_MESSAGE, "visit");
        startActivity(intent);
    }
}
