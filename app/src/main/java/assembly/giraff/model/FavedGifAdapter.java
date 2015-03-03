package assembly.giraff.model;

/**
 * Created by alouanemed on 27-02-2015.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import assembly.giraff.R;
import assembly.giraff.controller.FavedGifsController;


public class FavedGifAdapter extends BaseAdapter {
    private ArrayList<CustomCardModel> mFavedGifsList;
    private Activity activity;
    private final Typeface tf;
    FavedGifsController mFavedGifsController;

    public FavedGifAdapter(Activity context,
                         ArrayList<CustomCardModel> mFavedGifsList, Typeface tfBold,
                         FavedGifsController mFavedGifsController) {
        super();
        this.mFavedGifsList = mFavedGifsList;
        activity = context;
        this.tf = tfBold;
        this.mFavedGifsController = mFavedGifsController;

    }


    public static class ViewHolder {
        public ImageView IvFavedFigItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater vi =
                    (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_favedgif, null);

            holder = new ViewHolder();

            holder.IvFavedFigItem = (ImageView) v.findViewById(R.id.fvedgif_holder);

            v.setTag(holder);
        } else holder = (ViewHolder) v.getTag();

 
        return v;
    }

    @Override
    public int getCount() {
        return mFavedGifsList == null ? 0 : mFavedGifsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFavedGifsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}