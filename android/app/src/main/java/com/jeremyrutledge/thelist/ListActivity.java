package com.jeremyrutledge.thelist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.jeremyrutledge.thelist.R.layout.list_footer;


public class ListActivity extends ActionBarActivity {

    // data
    String title;
    ArrayList<ListItem> arrayOfItems;
    ListAdapter adapter;

    // Layout Objects
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // get items from layout
        list = (ListView) findViewById(R.id.list_item_view);

        // set List name in Action Bar
        setTitle(getIntent().getStringExtra(getString(R.string.intent_variable_list_name)));

        // set adapter
        arrayOfItems = new ArrayList<>();
        adapter = new ListAdapter(this, R.layout.list_item, arrayOfItems);
        list.setAdapter(adapter);
    }

    public void addItem(View view) {
        final View input = getLayoutInflater().inflate(R.layout.input_dialog_contents, null);
        final EditText inputText = (EditText) input.findViewById(R.id.item_name);
        new AlertDialog.Builder(this)
                .setTitle("Add Item")
                .setMessage("Enter a name for the new item")
                .setView(input)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        arrayOfItems.add(new ListItem(inputText.getText().toString()));
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
                    }
                }).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_list, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}
