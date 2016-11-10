package bcit.aicha.hezit.a7081project.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import bcit.aicha.hezit.a7081project.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ViewItemActivity extends AppCompatActivity {

    private String type;
    private TextView title;
    private TextView subtitle1;
    private TextView subtitle2;
    private TextView subtitle3;
    private TextView subtitle4;
    private TextView subtext1;
    private TextView subtext2;
    private TextView subtext3;
    private TextView subtext4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_item);

        title = (TextView)findViewById(R.id.enterTitleText2);

        subtitle1 = (TextView)findViewById(R.id.textViewView2);
        subtitle2 = (TextView)findViewById(R.id.textViewView4);
        subtitle3 = (TextView)findViewById(R.id.textViewView5);
        subtitle4 = (TextView)findViewById(R.id.textViewView6);

        subtext1 = (TextView)findViewById(R.id.editTextView);
        subtext2 = (TextView)findViewById(R.id.editTextView4);
        subtext3 = (TextView)findViewById(R.id.editTextView5);
        subtext4 = (TextView)findViewById(R.id.editTextView6);

        Intent intent = getIntent();
        type = intent.getStringExtra(EXTRA_MESSAGE);

        //temp data, replace with data from database
        subtext1.setText("Sample data");
        subtext2.setText("Click Me");
        subtext3.setText("Edit Me");
        subtext4.setText("Data");


        switch(type){
            case "patient":
                title.setText("Patient Info");
                subtitle1.setText("Name");
                subtitle2.setText("DOB");
                subtitle3.setText("Gender");
                subtitle4.setText("Address");
                break;

            case "doctor":
                title.setText("Doctor Info");
                subtitle1.setText("Name");
                subtitle2.setText("Specialty");
                subtitle3.setText("Office Address");
                subtitle4.setText("Comments");
                break;

            case "visit":
                title.setText("Visit Info");
                subtitle1.setText("Date");
                subtitle2.setText("Reason");
                subtitle3.setText("Doctor");
                subtitle4.setText("Comments");
                break;

            default:
                //patient
        }
    }

    public void editItem(View v){
        Intent intent = new Intent(this, EnterItemActivity.class);
        intent.putExtra(EXTRA_MESSAGE, type);
        startActivity(intent);
    }

    public void deleteItem(View v){
        Toast.makeText(this, "Item deleted.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ViewListActivity.class);
        intent.putExtra(EXTRA_MESSAGE, type);
        startActivity(intent);
    }
}
