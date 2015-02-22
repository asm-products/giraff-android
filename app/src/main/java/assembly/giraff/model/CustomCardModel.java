package assembly.giraff.model;

/**
 * Created by alouanemed on 09-02-2015.
 */

import android.graphics.drawable.Drawable;

import assembly.giraff.andtinder.model.CardModel;

public class CustomCardModel extends CardModel {

    private String img_url;

    private OnCardDismissedListener mOnCardDismissedListener = null;

    private OnCardClickListener mOnCardClickListener = null;

    public CustomCardModel(String title, String img_url) {
        super(title, (Drawable) null);
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}