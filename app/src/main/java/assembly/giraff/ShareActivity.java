package assembly.giraff;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShareActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "ShareActivity";
    private ListView mListView;
    private ShareAdapter mAdapter;
    private Intent mBaseIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mListView = (ListView) findViewById(R.id.list_view);

        mBaseIntent = new Intent(getIntent()); // duplicate
        mBaseIntent.setComponent(null);
        logIntent(mBaseIntent, "Base Intent");

        View view = getLayoutInflater().inflate(R.layout.header_share, mListView, false);
        mListView.addHeaderView(view);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(mBaseIntent, 0);
        Collections.sort(resolveInfoList, new ResolveInfo.DisplayNameComparator(pm));
        List<ShareTarget> targets = new ArrayList<>();
        for (ResolveInfo resolveInfo : resolveInfoList) {
            targets.add(ShareTarget.from(this, resolveInfo));
        }


        mAdapter = new ShareAdapter();
        mAdapter.setList(targets);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShareTarget target = mAdapter.getItem(position - mListView.getHeaderViewsCount());
        Intent intent = new Intent(mBaseIntent);
        intent.setComponent(target.getComponentName());
        logIntent(intent, "Fired intent.");
        startActivity(intent);
        finish();
    }

    private void logIntent(Intent intent, String label) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "=== dumping intent: " + label + " ===");
        Log.d(TAG, "action: " + intent.getAction());
        Log.d(TAG, "component: " + intent.getComponent());
        Log.d(TAG, "uri: " + intent.getDataString());
        Log.d(TAG, "extras: ");
        for (String key : bundle.keySet())
            Log.d(TAG, "- " + key + ": " + bundle.get(key));
    }

    private static class ShareAdapter extends BaseAdapter {

        List<ShareTarget> list = new ArrayList<>();

        public void setList(List<ShareTarget> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public ShareTarget getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_share, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            // Data binding
            ShareTarget target = getItem(position);
            holder.text.setText(target.getName());
            holder.text.setCompoundDrawablesWithIntrinsicBounds(target.getIcon(), null, null, null);

            return view;
        }
    }

    private static class ViewHolder {
        TextView text;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.text);
        }
    }

    private static class ShareTarget {
        private CharSequence name;
        private Drawable icon;
        private ComponentName componentName;

        public static ShareTarget from(Context context, ResolveInfo resolveInfo) {
            PackageManager pm = context.getPackageManager();
            ActivityInfo actInfo = resolveInfo.activityInfo;
            ComponentName componentName = new ComponentName(actInfo.packageName, actInfo.name);
            ShareTarget shareTarget = new ShareTarget();
            try {
                shareTarget.componentName = componentName;
                shareTarget.name = actInfo.loadLabel(pm);
                shareTarget.icon = pm.getActivityIcon(componentName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            return shareTarget;
        }

        public CharSequence getName() {
            return name;
        }

        public Drawable getIcon() {
            return icon;
        }

        public ComponentName getComponentName() {
            return componentName;
        }
    }
}
