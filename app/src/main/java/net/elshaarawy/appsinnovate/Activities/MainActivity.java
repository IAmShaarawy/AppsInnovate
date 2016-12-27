package net.elshaarawy.appsinnovate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.elshaarawy.appsinnovate.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView tasksListview;
    private ArrayAdapter<String> tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tasksListview = (ListView) findViewById(R.id.listview_tasks);

        String[] tasksArray = getResources().getStringArray(R.array.tasks);
        List<String> tasksList = new ArrayList<>(Arrays.asList(tasksArray));
        tasksAdapter = new ArrayAdapter<>(this,
                R.layout.list_item_task,
                R.id.list_item_task_textview,
                tasksList);

        tasksListview.setAdapter(tasksAdapter);

        tasksListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this,adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                tasksActions(i);
            }
        });

    }

    private void tasksActions(int i) {
        Intent intent = null;
        switch (i) {
            case 0:
                intent = new Intent(this, CountriesActivity.class);
                break;
            case 1:
                intent = new Intent(this, ContactsActivity.class);
                break;
            case 2:
                intent = new Intent(this, StoresLocationActivity.class);
                break;
            case 3:
                intent = new Intent(this, CalendarActivity.class);
                break;
            case 4:
                sharePhotoToFaceBook();
                break;
            case 5:
                logout();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
        if (intent != null) startActivity(intent);
    }

    private void sharePhotoToFaceBook() {
        Toast.makeText(this, "sharePhotoToFaceBook", Toast.LENGTH_LONG).show();
    }

    private void logout() {
        Toast.makeText(this, "logout", Toast.LENGTH_LONG).show();
    }

}
