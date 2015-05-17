package com.jeremyrutledge.thelist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // Data model
    private String listName;

    // Layout elements
    private TextView listTitle;
    private EditText listNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize data model
        listName = "";

        // get layout elements
        listTitle = (TextView) findViewById(R.id.app_title_large);
        listNameText = (EditText) findViewById(R.id.list_name_edit_text);

        // set textwatcher on list name edit text
        TextWatcher listNameTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listName = s.toString();
                while (listName.endsWith(" ")) {
                    listName = listName.substring(0,listName.length() - 1);
                }
                if (listName.length() == 0) {
                    listTitle.setText(getString(R.string.app_name));
                } else {
                    listTitle.setText(listName);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        listNameText.addTextChangedListener(listNameTextWatcher);

        // set on enter pressed listener
        TextView.OnEditorActionListener onEnterPressedListener = new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    enterList();
                }
                return true;
            }
        };
        listNameText.setOnEditorActionListener(onEnterPressedListener);
    }

    // listName getter
    public String getListName() {
        return listName;
    }

    // on button press
    public void makeListButtonOnClick(View b) {
        enterList();
    }

    // go to the List Activity
    public void enterList() {
        if (listName.equals("")) {
            Toast.makeText(this, "Please enter a list name.", Toast.LENGTH_SHORT).show();
        } else {
            Intent goToList = new Intent(this, ListActivity.class);
            goToList.putExtra(getString(R.string.intent_variable_list_name), listName);
            startActivity(goToList);
        }
    }
}
