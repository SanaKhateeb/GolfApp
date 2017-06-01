package sanakhateeb.holeinone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "sanakhateeb.holeinone.preferences";
    private static final String KEY_SCORECOUNT = "key_scorecount";
    private ListView mListView;
    private TextView mEmpty;
    private HoleAdapter adapter;
    private Hole[] mHoles = new Hole[18];
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        initializeHoles();
        mListView = (ListView) findViewById(android.R.id.list);
        mEmpty = (TextView) findViewById(R.id.loading_text);

        adapter = new HoleAdapter(this, mHoles);
        mListView.setEmptyView(mEmpty);
        mListView.setAdapter(adapter);
    }

    private void initializeHoles() {
        int score;
        for(int i = 0; i < mHoles.length; i++)
        {
            score = mSharedPreferences.getInt(KEY_SCORECOUNT + i, 0);
            mHoles[i] = new Hole(i+1, score);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(int i =0; i < mHoles.length; i++)
        {
            mEditor.putInt(KEY_SCORECOUNT + i, mHoles[i].getScore());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mEditor.clear();
            mEditor.apply();
            for(int i = 0; i < mHoles.length; i++)
            {
                mHoles[i].setScore(0);
                adapter.notifyDataSetChanged();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
