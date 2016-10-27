package bcit.aicha.hezit.a7081project.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bcit.aicha.hezit.a7081project.R;

public class ViewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_view_item);
    }
}
