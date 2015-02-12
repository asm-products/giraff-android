package assembly.giraff;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.res.Resources;
import android.widget.ImageView;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private CardContainer mCardContainer;
    private static final String TAG = "MainActivity";
    ImageView mimageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);
        //mimageView = (ImageView) findViewById(R.id.imgx);

        Resources r = getResources();

        ArrayList<Byte[]> mGifDataList = new ArrayList<>();


        CustomAdapter adapter = new CustomAdapter(this,mGifDataList);

        adapter.add(new CardModel("Title1", "Descripti0on goes her0e", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Descr0iption goes her0e", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title3", "Descripti0on goes here", r.getDrawable(R.drawable.picture1)));

        CardModel cardModel = new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1));
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