package assembly.giraff.controller;


import android.content.Context;


import java.util.ArrayList;

import assembly.giraff.model.CustomCardModel;

/**
 * Created by alouanemed on 27-02-2015.
 */
public class FavedGifsController {

    private ArrayList<CustomCardModel> mFavedGifsList;
    private Context mContext;

    public FavedGifsController( Context mContext) {
        mFavedGifsList = new ArrayList<CustomCardModel>();
        this.mContext = mContext;
    }

    //Let's fill the list with dummy data
    public void getFAvedGifs() {
        mFavedGifsList.add(new CustomCardModel("Title1", "http://gifs.joelglovier.com/accidents/wheelbarrel-dump.gif"));
        mFavedGifsList.add(new CustomCardModel("Title1", "http://gifs.joelglovier.com/accidents/wheelbarrel-dump.gif"));
        mFavedGifsList.add(new CustomCardModel("Title1", "http://gifs.joelglovier.com/accidents/wheelbarrel-dump.gif"));
        mFavedGifsList.add(new CustomCardModel("Title1", "http://gifs.joelglovier.com/accidents/wheelbarrel-dump.gif"));
        mFavedGifsList.add(new CustomCardModel("Title1", "http://gifs.joelglovier.com/accidents/wheelbarrel-dump.gif"));

    }


    public ArrayList<CustomCardModel> getmFavedGifsList() {
        return mFavedGifsList;
    }

    public void setmFavedGifsList(ArrayList<CustomCardModel> mFavedGifsList) {
        this.mFavedGifsList = mFavedGifsList;
    }


}
