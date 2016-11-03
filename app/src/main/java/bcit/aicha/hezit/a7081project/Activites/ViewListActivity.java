package bcit.aicha.hezit.a7081project.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bcit.aicha.hezit.a7081project.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ViewListActivity extends AppCompatActivity {

    private ListView lv;
    private String type;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_list);

        title = (TextView)findViewById(R.id.listTitle);
        lv = (ListView) findViewById(R.id.itemListView);

        Intent intent = getIntent();
        type = intent.getStringExtra(EXTRA_MESSAGE);

        switch(type){
            case "patient":
                title.setText("Patients");
                break;

            case "doctor":
                title.setText("Doctors");
                break;

            case "visit":
                title.setText("Visits");
                break;

            default:
                //patient
        }

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        List<String> itemList = new ArrayList<String>();
        itemList.add("Item 1");
        itemList.add("Item 2");
        itemList.add("Item 3");
        itemList.add("Item 4");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                itemList);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ViewListActivity.this, ViewItemActivity.class);
                intent.putExtra(EXTRA_MESSAGE, type);
                startActivity(intent);
            }
        });
    }
}
