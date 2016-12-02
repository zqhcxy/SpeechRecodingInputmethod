package com.example.zqh.speechrecodinginputmethod;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.TextView;

/**
 * searchView 上的语言功能
 */
public class SpeechRecordingActivity3 extends AppCompatActivity {

    private SearchView speech_searchview;
    private TextView speech_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recording3);

        speech_text=(TextView)findViewById(R.id.speech_text);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        speech_searchview = (SearchView) findViewById(R.id.speech_searchview);
        speech_searchview.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        speech_searchview.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        speech_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i("search", query);
            speech_text.setText(query);
//            doMySearch(query);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        // Get the SearchView and set the searchable configuration
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
//        return true;
//    }
}
