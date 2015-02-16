package assembly.giraff;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception e) {
            onSessionStateChange(session, state, e);
        }
    };

    private UiLifecycleHelper uiHelper;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //When we get launched from the facebook app or the android launcher.
        //A trigger might not be called in that scenario.
        Session session = Session.getActiveSession();
        if(session != null && (session.isOpened() || session.isClosed())){
           onSessionStateChange(session, session.getState(), null);
        }
        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_main, container, false);
        LoginButton authButton = (LoginButton)view.findViewById(R.id.authButton);
        authButton.setFragment(this);

        return view;
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception){
       if (state.isOpened()){
           Log.i(TAG, "Logged in...");
       }else if (state.isClosed()) {
           Log.i(TAG, "logged out...");
       }
    }
}
