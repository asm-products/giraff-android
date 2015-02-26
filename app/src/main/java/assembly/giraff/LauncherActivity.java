package assembly.giraff;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.facebook.widget.LoginButton;

import assembly.giraff.facebook.FacebookActivity;


public class LauncherActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Button facebook = (Button)findViewById(R.id.facebookButton);
                facebook.setVisibility(View.VISIBLE);
                Button mainActivity = (Button)findViewById(R.id.mainButton);
                mainActivity.setVisibility(View.VISIBLE);
                RelativeLayout layout = (RelativeLayout)findViewById(R.id.launcherLayout);
                layout.setBackground(null);
            }
        }, 7000);
    }

    public void facebook(View view){
        startActivity(new Intent(this, FacebookActivity.class));
    }

    public void activity(View view){
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
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
