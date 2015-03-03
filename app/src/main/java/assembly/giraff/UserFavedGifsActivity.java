package assembly.giraff;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import assembly.giraff.controller.FavedGifsController;
import assembly.giraff.model.CustomCardModel;
import assembly.giraff.model.FavedGifAdapter;

/**
 * Created by alouanemed on 27-02-2015.
 */
public class UserFavedGifsActivity extends BaseActivity {

    ProgressDialog dialog = null;
    FavedGifsController mFavedGifsController;

    GridView mGridview;
    ArrayList<CustomCardModel> mFavedGifsList;
    FavedGifAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_faved_gifs);
        mGridview = (GridView) findViewById(R.id.list);

        mFavedGifsController = new FavedGifsController(this);
        new getFavedGifAsync().execute();

        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


            }
        });

    }

    private class getFavedGifAsync extends AsyncTask<Void, Void, Void> {

        //before executing let's show a dialog to the user
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(UserFavedGifsActivity.this, "", "please wait...", true);

        }

        //do the job in InBackground while the dialog is shown
        @Override
        protected Void doInBackground(Void... arg0) {
            mFavedGifsController.getFAvedGifs();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            Typeface tfBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
            mFavedGifsList = mFavedGifsController.getmFavedGifsList();
            mAdapter = new FavedGifAdapter(UserFavedGifsActivity.this, mFavedGifsList, tfBold, mFavedGifsController);
            mGridview.setAdapter(mAdapter);
            this.cancel(true);
        }

    }


}
