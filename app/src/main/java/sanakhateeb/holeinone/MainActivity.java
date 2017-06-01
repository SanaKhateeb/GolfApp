package sanakhateeb.holeinone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private TextView mEmpty;
    private HoleAdapter adapter;
    private final static int NUM_HOLES = 18;
    private Hole[] mHoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iniitializeHoles();
        mListView = (ListView) findViewById(android.R.id.list);
        mEmpty = (TextView) findViewById(R.id.loading_text);

        adapter = new HoleAdapter(this, mHoles);
        mListView.setEmptyView(mEmpty);
        mListView.setAdapter(adapter);
    }

    private void iniitializeHoles() {
        mHoles = new Hole[NUM_HOLES];
        for(int i = 0; i < mHoles.length; i++)
        {
            mHoles[i] = new Hole(i+1, 0);
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
