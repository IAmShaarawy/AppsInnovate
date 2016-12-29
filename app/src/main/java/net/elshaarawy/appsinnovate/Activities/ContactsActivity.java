package net.elshaarawy.appsinnovate.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import net.elshaarawy.appsinnovate.Adapters.ContactsAdapter;
import net.elshaarawy.appsinnovate.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.name;
import static android.R.attr.phoneNumber;
import static android.R.attr.x;

public class ContactsActivity extends AppCompatActivity {


    private final String TAG = this.getClass().getSimpleName();
    private List<Pair<String, String>> contactsPairList;
    private RecyclerView contactsRecyclerView;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] permissionArray = new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS};
        int permissionCode = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permissionCode != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionArray, 1);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoadContacts();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LoadContacts();
                } else {
                    finish();
                }
        }
    }

    private void LoadContacts() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        getContacts(cursor);
        contactsRecyclerView = (RecyclerView) findViewById(R.id.content_contacts);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsAdapter = new ContactsAdapter(this, contactsPairList);
        contactsRecyclerView.setAdapter(contactsAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                ContactsAdapter.ContactHolder holder = ((ContactsAdapter.ContactHolder) viewHolder);
                Intent intent = null;
                switch (swipeDir) {
                    case ItemTouchHelper.LEFT:
                        intent = new Intent(Intent.ACTION_SENDTO);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setType("vnd.android-dir/mms-sms");
                        intent.setData(Uri.parse("sms:" + holder.getNumber().getText().toString()));
                        break;
                    case ItemTouchHelper.RIGHT:
                        intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                                "tel", holder.getNumber().getText().toString(), null));
                        break;
                }
                startActivity(intent);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(contactsRecyclerView);
    }

    private void getContacts(Cursor cursor) {
        contactsPairList = new ArrayList<>(cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactsPairList.add(new Pair<>(name, number));
        }
    }

}
