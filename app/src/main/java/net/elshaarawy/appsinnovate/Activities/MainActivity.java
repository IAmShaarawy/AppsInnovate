package net.elshaarawy.appsinnovate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import net.elshaarawy.appsinnovate.AsyncTasks.CountriesTask;
import net.elshaarawy.appsinnovate.Managers.ConnectionManager;
import net.elshaarawy.appsinnovate.Managers.MyPreferenceManager;
import net.elshaarawy.appsinnovate.Managers.RealmManager;
import net.elshaarawy.appsinnovate.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.elshaarawy.appsinnovate.Managers.MyPreferenceManager.Keys.*;


public class MainActivity extends AppCompatActivity {

    private ListView tasksListview;
    private ArrayAdapter<String> tasksAdapter;
    private MyPreferenceManager preferenceManager;
    private CountriesTask countriesTask;
    private RealmManager realmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new MyPreferenceManager(this, DEFAULT_SHARED_PREFERENCE);
        if (preferenceManager.getBoolean(PREF_IS_AUTHENTICATED)) {
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            realmManager = RealmManager.getInstance();
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
                    tasksActions(i);
                }
            });
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!preferenceManager.getBoolean(PREF_IS_FIRST_TIME) && ConnectionManager.getInstance().isOnline(this)) {
            countriesTask = new CountriesTask(realmManager, this);
            countriesTask.execute();
        }
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
                logout();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
        if (intent != null) startActivity(intent);
    }


    private void logout() {
        LoginManager.getInstance().logOut();
        preferenceManager.editValue(PREF_IS_AUTHENTICATED,false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
