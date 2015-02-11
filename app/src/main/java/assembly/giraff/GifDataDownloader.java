package assembly.giraff;

/**
 * Created by med on 10-02-2015.
 */

import android.os.AsyncTask;
import android.util.Log;

public class GifDataDownloader extends AsyncTask<String, Void, byte[]> {

    private static final String TAG = "GifDataDownloader";

    public GifDataDownloader() {
    }

    @Override
    protected byte[] doInBackground(final String... params) {
        final String gifUrl = params[0];
        Log.e(TAG, "gifUrl >>" + gifUrl);

        if (gifUrl == null)
            return null;

        byte[] gif = null;
        try {
            Log.e(TAG, "try  onnnnn >>" + gifUrl);

            gif = ByteArrayHttpClient.get(gifUrl);
        } catch (Exception e) {
            Log.e(TAG, "GifDecode OOM: " + gifUrl, e);
        }

        return gif;
    }
}