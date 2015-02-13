package assembly.giraff;

import assembly.giraff.andtinder.model.CardModel;
import assembly.giraff.andtinder.view.CardStackAdapter;
import assembly.giraff.model.CustomCardModel;

/**
 * Created by alouanemed on 09-02-2015.
 */

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;




public final class CustomAdapter extends CardStackAdapter {

    private static final String TAG = "CustomAdapter";
    private  ArrayList<Byte[]> mGifDataList;

    public CustomAdapter(Context mContext, ArrayList<Byte[]> mGifDataList) {
        super(mContext);
        this.mGifDataList = mGifDataList;
    }

    public static class ViewHolder {

        public TextView card_Title;
        public TextView card_description;

        public ImageView gifImageView;

    }


    @Override
    public View getCardView(int position,CustomCardModel model, View convertView, ViewGroup parent) {

        ViewHolder holder;


        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.gif_card, parent, false);
            assert convertView != null;

            holder = new ViewHolder();
            holder.card_Title = (TextView) convertView.findViewById(R.id.image_title);
            holder.card_description = (TextView) convertView.findViewById(R.id.image_description);
            holder.gifImageView = (ImageView) convertView.findViewById(R.id.image_gif);

            convertView.setTag(holder);
        }else holder = (ViewHolder) convertView.getTag();


        if (!TextUtils.isEmpty(model.getTitle())) {
            holder.card_Title.setText(model.getTitle());
            holder.card_Title.setVisibility(View.VISIBLE);
        } else {
            holder.card_Title.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(model.getDescription())) {
            holder.card_description.setText(model.getDescription());
            holder.card_description.setVisibility(View.VISIBLE);
        } else {
            holder.card_description.setVisibility(View.GONE);
        }

        Ion.with(getContext())
                .load(model.getImg_url())
                .withBitmap()
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.dislike)
                .intoImageView(holder.gifImageView);

        return convertView;
    }
}