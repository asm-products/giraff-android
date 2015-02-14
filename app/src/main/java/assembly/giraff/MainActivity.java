package assembly.giraff;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.res.Resources;

import assembly.giraff.andtinder.model.CardModel;
import assembly.giraff.andtinder.view.CardContainer;


import java.util.ArrayList;

import assembly.giraff.model.CustomCardModel;


public class MainActivity extends FragmentActivity {

    private MainFragment mainFragment;


    private CardContainer mCardContainer;
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState == null){
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment).commit();
        }else{
            mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }

        setContentView(R.layout.activity_main);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);

        Resources r = getResources();

        ArrayList<Byte[]> mGifDataList = new ArrayList<>();


        CustomAdapter adapter = new CustomAdapter(this,mGifDataList);

        adapter.add(new CustomCardModel("Title1", "Descripti0on goes her0e  1", "http://gifs.joelglovier.com/accidents/wheelbarrel-dump.gif"));
        adapter.add(new CustomCardModel("Title2", "Descr0iption goes her0e 2","http://gifs.joelglovier.com/fail/cat-fail.gif"));
        adapter.add(new CustomCardModel("Title3", "Descripti0on goes here 3", "http://gifs.joelglovier.com/aha/aha.gif"));

        CustomCardModel cardModel = new CustomCardModel("Title3", "Descripti0on goes here 3", "http://gifs.joelglovier.com/big-lebowski/no-huh-uh.gif");
        cardModel.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards","I am pressing the card");
            }
        });

        cardModel.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Log.i("Swipeable Cards","I like the card");
            }

            @Override
            public void onDislike() {
                Log.i("Swipeable Cards","I dislike the card");
            }
        });

        adapter.add(cardModel);

        mCardContainer.setAdapter(adapter);
    }
}
