package bcit.aicha.hezit.a7081project.Activites.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bcit.aicha.hezit.a7081project.Activites.Database.DatabaseHelper;
import bcit.aicha.hezit.a7081project.Activites.Models.Doctor;
import bcit.aicha.hezit.a7081project.Activites.Models.User;
import bcit.aicha.hezit.a7081project.Activites.Models.Visit;
import bcit.aicha.hezit.a7081project.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static bcit.aicha.hezit.a7081project.R.id.editText;

public class EnterItemActivity extends AppCompatActivity {

    private String type;
    private TextView title;
    private TextView subtitle1;
    private TextView subtitle2;
    private TextView subtitle3;
    private TextView subtitle4;
    private TextView editText1;
    private TextView editText2;
    private TextView editText3;
    private TextView editText4;

    private String editText1Condition;
    private String editText2Condition;
    private String editText3Condition;
    private String editText4Condition;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_enter_item);

        dbHelper = new DatabaseHelper(this);

        title = (TextView)findViewById(R.id.enterTitleText);
        subtitle1 = (TextView)findViewById(R.id.textView2);
        subtitle2 = (TextView)findViewById(R.id.textView4);
        subtitle3 = (TextView)findViewById(R.id.textView5);
        subtitle4 = (TextView)findViewById(R.id.textView6);

        editText1 = (EditText)findViewById(editText);
        editText2 = (EditText)findViewById(R.id.editText4);
        editText3 = (EditText)findViewById(R.id.editText5);
        editText4 = (EditText)findViewById(R.id.editText6);

        Intent intent = getIntent();
        type = intent.getStringExtra(EXTRA_MESSAGE);
        String primaryKey = intent.getStringExtra("PRIMARY_KEY");

        switch(type){
            case "patient":
                title.setText("Patient Info");
                subtitle1.setText("Name");
                subtitle2.setText("DOB");
                subtitle3.setText("Gender");
                subtitle4.setText("Address");

                editText1Condition = "alpha";
                editText2Condition = "date";
                editText3Condition = "gender";
                editText4Condition = "alphanum";

                if(primaryKey != null){
                    User user = dbHelper.getUser(primaryKey);
                    editText1.setText(user.getName());
                    editText2.setText(user.getDOB());
                    editText3.setText(user.getGender());
                    editText4.setText(user.getAddress());
                }

                break;

            case "doctor":
                title.setText("Doctor Info");
                subtitle1.setText("Name");
                subtitle2.setText("Specialty");
                subtitle3.setText("Office Address");
                subtitle4.setText("Comments");

                editText1Condition = "alpha";
                editText2Condition = "alpha";
                editText3Condition = "alphanum";
                editText4Condition = "alphanum";

                if(primaryKey != null){
                    Doctor doctor = dbHelper.getDoctor(primaryKey);

                    editText1.setText(doctor.getName());
                    editText2.setText(doctor.getSpecialty());
                    editText3.setText(doctor.getAddress());
                    editText4.setText(doctor.getComments());
                }

                break;

            case "visit":
                title.setText("Visit Info");
                subtitle1.setText("Reason");
                subtitle2.setText("Date");
                subtitle3.setText("Doctor");
                subtitle4.setText("Comments");

                editText1Condition = "alpha";
                editText2Condition = "date";
                editText3Condition = "alpha";
                editText4Condition = "alphanum";

                if(primaryKey != null){
                    Visit visit = dbHelper.getVisit(primaryKey);

                    editText1.setText(visit.getReason());
                    editText2.setText(visit.getDate());
                    editText3.setText(visit.getDoctor());
                    editText4.setText(visit.getComments());
                }

                break;

            default:
                //patient
        }
    }

    public void saveItem(View v){

        boolean fieldsFilled = true;
        boolean validInput = true;

        if((editText1.getText().toString()).equals("")
                || (editText2.getText().toString()).equals("")
                || (editText3.getText().toString()).equals("")
                || (editText4.getText().toString()).equals("")) {
            fieldsFilled = false;
        }

        if(editText1Condition.equals("alpha")){
            if(isAlpha(editText1.getText().toString()) == false){
                validInput = false;
                Toast.makeText(this, "Invalid first input, must be alphabet.", Toast.LENGTH_LONG).show();
            }
        }else if(editText1Condition.equals("gender")){
            if(!(editText1.getText().toString().equals("male")) ||
                    !(editText1.getText().toString().equals("female")) ){
                validInput = false;
                Toast.makeText(this, "Invalid first input, must be male or female.", Toast.LENGTH_LONG).show();
            }
        }

        if(editText2Condition.equals("alpha")){
            if(isAlpha(editText2.getText().toString()) == false){
                validInput = false;
                Toast.makeText(this, "Invalid second input, must be alphabet.", Toast.LENGTH_LONG).show();
            }
        }else if(editText2Condition.equals("gender")){
            if(!(editText2.getText().toString().equals("male")) ||
                    !(editText2.getText().toString().equals("female")) ){
                validInput = false;
                Toast.makeText(this, "Invalid second input, must be male or female.", Toast.LENGTH_LONG).show();
            }
        }

        if(editText3Condition.equals("alpha")){
            if(isAlpha(editText3.getText().toString()) == false){
                validInput = false;
                Toast.makeText(this, "Invalid third input, must be alphabet.", Toast.LENGTH_LONG).show();
            }
        }else if(editText3Condition.equals("gender")){
            if(!(editText3.getText().toString().equals("male")) ||
                    !(editText3.getText().toString().equals("female")) ){
                validInput = false;
                Toast.makeText(this, "Invalid third input, must be male or female.", Toast.LENGTH_LONG).show();
            }
        }

        if(editText4Condition.equals("alpha")){
            if(isAlpha(editText4.getText().toString()) == false){
                validInput = false;
                Toast.makeText(this, "Invalid fourth input, must be alphabet.", Toast.LENGTH_LONG).show();
            }
        }else if(editText4Condition.equals("gender")){
            if(!(editText4.getText().toString().equals("male")) ||
                    !(editText4.getText().toString().equals("female")) ){
                validInput = false;
                Toast.makeText(this, "Invalid fourth input, must be male or female.", Toast.LENGTH_LONG).show();
            }
        }


        if(fieldsFilled && validInput) {
            Intent intent = new Intent(this, ViewListActivity.class);

            switch (type) {
                case "patient":
                    //get values from edit text
                    User user = new User(editText1.getText().toString(),
                            editText2.getText().toString(),
                            editText3.getText().toString(),
                            editText4.getText().toString());

                    dbHelper.addUser(user);
                    User rcvUser = dbHelper.getUser(user.getName());

                    Toast.makeText(this, "Patient info added.", Toast.LENGTH_LONG).show();
                    intent = new Intent(this, ViewItemActivity.class);

                    break;

                case "doctor":

                    Doctor existing = dbHelper.getDoctor(editText1.getText().toString());

                    if (existing == null) {
                        dbHelper.addDoctor(editText1.getText().toString(),
                                editText2.getText().toString(),
                                editText3.getText().toString(),
                                editText4.getText().toString());
                    } else {
                        dbHelper.updateDoctor(new Doctor(editText1.getText().toString(),
                                editText2.getText().toString(),
                                editText3.getText().toString(),
                                editText4.getText().toString()));
                    }

                    Toast.makeText(this, "Doctor info added.", Toast.LENGTH_LONG).show();
                    intent.putExtra("PRIMARY_KEY", editText1.getText().toString());

                    break;

                case "visit":

                    Visit existingVisit = dbHelper.getVisit(editText1.getText().toString());

                    if (existingVisit == null) {
                        dbHelper.addVisit(editText1.getText().toString(),
                                editText2.getText().toString(),
                                editText3.getText().toString(),
                                editText4.getText().toString());
                    } else {
                        dbHelper.updateVisit(new Visit(editText1.getText().toString(),
                                editText2.getText().toString(),
                                editText3.getText().toString(),
                                editText4.getText().toString()));
                    }

                    Toast.makeText(this, "Visit info added.", Toast.LENGTH_LONG).show();
                    intent.putExtra("PRIMARY_KEY", editText1.getText().toString());

                    break;

                default:
                    //patient
            }

            intent.putExtra(EXTRA_MESSAGE, type);
            startActivity(intent);
        }else{
            Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

}
