package assembly.giraff;

import com.andtinder.model.CardModel;

/**
 * Created by med on 09-02-2015.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardStackAdapter;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import assembly.giraff.R;

public final class CustomAdapter extends CardStackAdapter {

    private GifImageView gifImageView;
    private static final String TAG = "CustomAdapter";

    public CustomAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getCardView(int position, CardModel model, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.gif_card, parent, false);
            assert convertView != null;
        }

        gifImageView = (GifImageView) convertView.findViewById(R.id.image_gif);

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

        return convertView;
    }
}