package assembly.giraff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import assembly.giraff.andtinder.model.CardModel;
import assembly.giraff.andtinder.view.CardContainer;
import assembly.giraff.model.CustomCardModel;


public class MainViewFragment extends Fragment {

    private CardContainer mCardContainer;
    private Button mShareButton;
    private static final String TAG = "MainActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        mCardContainer = (CardContainer) rootView.findViewById(R.id.layoutview);
        mShareButton = (Button) rootView.findViewById(R.id.sharebutton);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Byte[]> mGifDataList = new ArrayList<>();

        CustomAdapter adapter = new CustomAdapter(getActivity(), mGifDataList);

        adapter.add(new CustomCardModel("Title1", "Descripti0on goes her0e  1", "http://gifs.joelglovier.com/accidents/wheelbarrel-dump.gif"));
        adapter.add(new CustomCardModel("Title2", "Descr0iption goes her0e 2", "http://gifs.joelglovier.com/fail/cat-fail.gif"));
        adapter.add(new CustomCardModel("Title3", "Descripti0on goes here 3", "http://gifs.joelglovier.com/aha/aha.gif"));

        CustomCardModel cardModel = new CustomCardModel("Title3", "Descripti0on goes here 3", "http://gifs.joelglovier.com/big-lebowski/no-huh-uh.gif");
        cardModel.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
            }
        });
        cardModel.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Log.i("Swipeable Cards", "I like the card");
            }

            @Override
            public void onDislike() {
                Log.i("Swipeable Cards", "I dislike the card");
            }
        });

        adapter.add(cardModel);

        mCardContainer.setAdapter(adapter);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(mCardContainer.getCurrentCard());
            }
        });
    }

    private void share(CardModel cardModel) {
        if (cardModel == null) return;

        if (cardModel instanceof CustomCardModel) {
            String text = String.format("%s %s",
                    cardModel.getDescription(),
                    ((CustomCardModel) cardModel).getImg_url());

            Intent intent = new Intent(getActivity(), ShareActivity.class);
            intent.setType("text/plain");
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_SUBJECT, cardModel.getTitle());
            intent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(intent);
        }
    }
}