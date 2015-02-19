package assembly.giraff;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;


import de.hdodenhof.circleimageview.CircleImageView;
import assembly.giraff.model.User;


public class NavigationDrawerListAdapter extends ArrayAdapter<String> {
    private Activity parentContext;

    public NavigationDrawerListAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        parentContext = (Activity) context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) parentContext.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            User userData = ((NavigationDrawerFragment.NavigationDrawerData) parentContext)
                    .getCurrentUser();
            switch (position % 3) {
                case 0:
                    if (!userData.IsUpgraded()) {
                        convertView = inflater.inflate(R.layout.navigation_drawer_upgrade, parent,
                                false);
                    }else{
                        convertView = inflater.inflate(R.layout.null_item, parent,
                                false);
                    }
                    break;
                case 1:
                    if (userData.IsUpgraded()) {
                        convertView = inflater.inflate(R.layout.navigation_user_card, parent,
                                false);
                    }else{
                        convertView = inflater.inflate(R.layout.null_item, parent,
                                false);
                    }

                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.navigation_drawer_section_1, parent,
                            false);
                    break;
            }
            if (position == 1) {
                if (userData.IsUpgraded()) {
                    LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id
                            .user_card_layout);
                    CircleImageView pictureFrame = (CircleImageView) convertView.findViewById(R.id
                            .user_avatar);

                    //replace this with user avatar bitmap
                    Bitmap profile = BitmapFactory.decodeResource(parentContext.getResources(),
                            R.drawable.ic_derp);
                    pictureFrame.setImageBitmap(profile);
                    Bitmap filteredBitmap = Bitmap.createBitmap(profile.getWidth(),
                            profile.getHeight(), Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(filteredBitmap);
                    Paint gold = new Paint();
                    gold.setColor(parentContext.getResources().getColor(R.color.gold));
                    c.drawRect(0, 0, profile.getWidth(), profile.getHeight(), gold);
                    Paint paint = new Paint();
                    float[] mat = new float[]{
                            0.5f, 0.5f, 0.5f, 0, 0,
                            0.5f, 0.5f, 0.5f, 0, 0,
                            0.5f, 0.5f, 0.5f, 0, 0,
                            0, 0, 0, 0.2f, 0,};
                    ColorMatrixColorFilter filter = new ColorMatrixColorFilter(mat);
                    paint.setColorFilter(filter);
                    c.drawBitmap(profile, 0, 0, paint);
                    ImageView backgroundFrame = (ImageView) convertView.findViewById(R.id.background);
                    backgroundFrame.setImageBitmap(filteredBitmap);

                    TextView username = (TextView) convertView.findViewById(R.id.user_name);
                    username.setText(userData.getUserName());
                    TextView userlevel = (TextView) convertView.findViewById(R.id.user_faved_gifs);
                    userlevel.setText(userData.getUserFavedGifs() +" GIFS SAVED");

                }


            } else if (position == 0) {


                /*LinearLayout linearLayout = (LinearLayout) convertView;
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    if (linearLayout.getChildAt(i) instanceof TextView)
                        ((TextView) linearLayout.getChildAt(i)).setTypeface(robotoMedium);
                }*/
            }
        }

        return convertView;
    }
}
