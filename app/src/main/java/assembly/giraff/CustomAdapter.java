package assembly.giraff;

import com.andtinder.model.CardModel;

/**
 * Created by med on 09-02-2015.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardStackAdapter;
import com.felipecsl.gifimageview.library.GifImageView;
import com.koushikdutta.ion.Ion;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import assembly.giraff.R;

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
    public View getCardView(int position, CardModel model, View convertView, ViewGroup parent) {

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
                .load("http://gifs.joelglovier.com/aha/aha.gif")
                .withBitmap()
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.dislike)
                .intoImageView(holder.gifImageView);


     /*
        new GifDataDownloader() {
            @Override
            protected void onPostExecute(final byte[] bytes) {
                gifImageView.setBytes(bytes);
                gifImageView.startAnimation();
                Log.d(TAG, "GIF width is " + gifImageView.getGifWidth());
                Log.d(TAG, "GIF height is " + gifImageView.getGifHeight());
            }
        }.execute("http://gifs.joelglovier.com/aha/aha.gif");


        ((TextView) convertView.findViewById(R.id.image_title)).setText(model.getTitle());
        ((TextView) convertView.findViewById(R.id.image_description)).setText(model.getDescription());
        Ion.with(gifImageView)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.dislike)
                .load("http://gifs.joelglovier.com/aha/aha.gif");*/
        return convertView;
    }
}