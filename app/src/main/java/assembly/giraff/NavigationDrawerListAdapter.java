package assembly.giraff;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

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
            switch (position % 2) {
                case 0:
                    convertView = inflater.inflate(R.layout.navigation_drawer_upgrade, parent,
                            false);
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.navigation_drawer_section_1, parent,
                            false);
                    break;
            }
            if (position == 0) {
                LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id
                        .user_card_layout);
                User userData = ((NavigationDrawerFragment.NavigationDrawerData) parentContext)
                        .getCurrentUser();
            } else {
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
