package assembly.giraff.facebook;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import assembly.giraff.R;
import assembly.giraff.facebook.FacebookFragment;


public class FacebookActivity extends FragmentActivity{

    private FacebookFragment facebookFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            facebookFragment = new FacebookFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, facebookFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            facebookFragment = (FacebookFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
        setContentView(R.layout.activity_dev);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dev, menu);
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
