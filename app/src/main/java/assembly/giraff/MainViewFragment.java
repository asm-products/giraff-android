package assembly.giraff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assembly.giraff.andtinder.model.CardModel;
import assembly.giraff.andtinder.model.Orientations;
import assembly.giraff.andtinder.view.CardContainer;
import assembly.giraff.model.CustomCardModel;


public class MainViewFragment extends Fragment implements View.OnClickListener {

    private CardContainer mCardContainer;
    private static final String TAG = "MainActivity";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main_view, container, false);

        rootView.findViewById(R.id.pass_button).setOnClickListener(this);
        rootView.findViewById(R.id.fave_button).setOnClickListener(this);
        rootView.findViewById(R.id.share_button).setOnClickListener(this);

        mCardContainer = (CardContainer) rootView.findViewById(R.id.card_container);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);

        CustomAdapter adapter = new CustomAdapter(getActivity());

        adapter.add(new CustomCardModel("Title1", "http://gifs.joelglovier.com/accidents/wheelbarrel-dump.gif"));
        adapter.add(new CustomCardModel("Long Long Long Long Long Very Long Long Long Title2", "http://gifs.joelglovier.com/fail/cat-fail.gif"));
        adapter.add(new CustomCardModel("Title3", "http://gifs.joelglovier.com/aha/aha.gif"));

        CustomCardModel cardModel = new CustomCardModel("Title3", "http://gifs.joelglovier.com/big-lebowski/no-huh-uh.gif");
        cardModel.setOnClickListener(new CardModel.OnCardClickListener() {
            @Override
            public void onCardClick() {
                Log.i("Swipeable Cards","I am pressing the card");
            }
        });

        cardModel.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            @Override
            public void onLike() {
                cardLiked();
            }

            @Override
            public void onDislike() {
                cardDisliked();
            }
        });

        adapter.add(cardModel);

        mCardContainer.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pass_button:
                mCardContainer.missCard(-5000, 100, mCardContainer.getTopCard());
                cardDisliked();
                break;
            case R.id.fave_button:
                mCardContainer.missCard(5000, 100, mCardContainer.getTopCard());
                cardLiked();
                break;
            case R.id.share_button:
                //TODO: open share activity
                break;
        }
    }

    private void cardDisliked() {
        Log.i("Swipeable Cards", "I dislike the card");
    }

    private void cardLiked() {
        Log.i("Swipeable Cards", "I like the card");
    }

}