package assembly.giraff;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import assembly.giraff.andtinder.view.CardStackAdapter;
import assembly.giraff.model.CustomCardModel;

/**
 * Created by alouanemed on 09-02-2015.
 */


public final class CustomAdapter extends CardStackAdapter {

    private static final String TAG = "CustomAdapter";

    public CustomAdapter(Context mContext) {
        super(mContext);
    }

    public static class ViewHolder {
        public TextView card_Title;
        //public ImageView gifImageView;
        public VideoView MP4PlayerView;
    }


    @Override
    public View getCardView(int position, CustomCardModel model, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gif_card, parent, false);
            assert convertView != null;

            holder = new ViewHolder();
            holder.card_Title = (TextView) convertView.findViewById(R.id.title);
            //holder.gifImageView = (ImageView) convertView.findViewById(R.id.image_gif);
            holder.MP4PlayerView = (VideoView) convertView.findViewById(R.id.gif_player);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (!TextUtils.isEmpty(model.getTitle())) {
            holder.card_Title.setText(model.getTitle());
            holder.card_Title.setVisibility(View.VISIBLE);
        } else {
            holder.card_Title.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(model.getImg_url())) {

            String link=model.getImg_url() ;
            MediaController mediaController = new MediaController(getContext());
            mediaController.setAnchorView(holder.MP4PlayerView);
            Uri video = Uri.parse(link);
            int height = holder.MP4PlayerView.getHeight();
            int width = holder.MP4PlayerView.getWidth();
            holder.MP4PlayerView.setMinimumWidth(width);
            holder.MP4PlayerView.setMinimumHeight(height);

            holder.MP4PlayerView.setMediaController(mediaController);
            holder.MP4PlayerView.setVideoURI(video);
            holder.MP4PlayerView.setZOrderOnTop(false);


            holder.MP4PlayerView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    System.out.println("onPrepared");
                    mp.setLooping(true);

                }
            });

            holder.MP4PlayerView.setVisibility(View.VISIBLE);
        } else {
            holder.MP4PlayerView.setVisibility(View.GONE);
        }


        /*Ion.with(getContext())
                .load(model.getImg_url())
                .withBitmap()
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.dislike)
                .intoImageView(holder.gifImageView);*/

        return convertView;
    }
}