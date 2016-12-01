package bcit.aicha.hezit.a7081project.Activites.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bcit.aicha.hezit.a7081project.Activites.Database.DatabaseHelper;
import bcit.aicha.hezit.a7081project.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ViewListActivity extends AppCompatActivity {

    private ListView lv;
    private String type;
    private TextView title;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_list);

        title = (TextView)findViewById(R.id.listTitle);
        lv = (ListView) findViewById(R.id.itemListView);

        Intent intent = getIntent();
        type = intent.getStringExtra(EXTRA_MESSAGE);

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        List<String> itemList = new ArrayList<String>();

        switch(type){
            case "patient":
                title.setText("Patients");
                break;

            case "doctor":
                title.setText("Doctors");

                String[] doctors = db.getDoctorNames();

                for(String name : doctors){
                    itemList.add(name);
                }

                break;

            case "visit":
                title.setText("Visits");

                String[] visits = db.getVisitNames();

                for(String name : visits){
                    itemList.add(name);
                }
                break;

            default:
                //patient
                itemList.add("Item 1");
                itemList.add("Item 2");
                itemList.add("Item 3");
                itemList.add("Item 4");
        }


        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                itemList);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String value = (String)lv.getItemAtPosition(position);
                Intent intent = new Intent(ViewListActivity.this, ViewItemActivity.class);
                intent.putExtra(EXTRA_MESSAGE, type);
                intent.putExtra("PRIMARY_KEY", value);
                startActivity(intent);
            }
        });
    }

    public void addItem(View v){
        Toast.makeText(this, "Item added.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EnterItemActivity.class);
        intent.putExtra(EXTRA_MESSAGE, type);
        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.mainmenu, menu);
//        return true;
//    }
//
//    /**
//     * Let's the user tap the activity icon to go 'home'.
//     * Requires setHomeButtonEnabled() in onCreate().
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case android.R.id.home:
//                // ProjectsActivity is my 'home' activity
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                return true;
//        }
//        return (super.onOptionsItemSelected(menuItem));
//    }
}
