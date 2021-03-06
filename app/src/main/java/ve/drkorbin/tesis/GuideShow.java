package ve.drkorbin.tesis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.utils.TesisConstants;

public class GuideShow extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyAGoEj3CkDk1Akl-rWS-ywnD8phC0k-GEU";
    private String videoId = "VrZ4sMRYimw";

    TextView title;
    TextView description;
    YouTubePlayerView youTubePlayerView;
    private Uri uriYouTube;
    Guide guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_show);

        guide = null;

        title = (TextView) findViewById(R.id.textViewTitle);
        description = (TextView) findViewById(R.id.textViewDescription);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.viewYoutube);

        Intent intent = getIntent();

        if (intent.getExtras().get(TesisConstants.GUIDE) != null) {
            guide = (Guide) intent.getExtras().get(TesisConstants.GUIDE);

        }

        if (guide != null) {
            title.setText(guide.getTitulo());
            description.setText(guide.getDescripcion());
        }

        youTubePlayerView.initialize(API_KEY, this);


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (null == player) return;

        // Start buffering
        if (!wasRestored) {
            player.cueVideo(guide.getUrl());
        }
    }
/*
    *//**
     * Credits for Mohit Gupt             in example
     * http://www.truiton.com/2013/08/android-videoview-example-with-youtube-playback/                                                                                 
     *
     * *//*

    void startPlaying(String url) {
        uriYouTube = Uri.parse(url);
        youtubeVideo.setVideoURI(uriYouTube);
        youtubeVideo.start();
    }




    private class RTSPUrlTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = getRTSPVideoUrl(urls[0]);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            startPlaying(result);
        }

        public String getRTSPVideoUrl(String urlYoutube) {
            try {
                String gdy = "http://gdata.youtube.com/feeds/api/videos/";
                DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
                String id = extractYoutubeId(urlYoutube);
                URL url = new URL(gdy + id);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                Document doc = dBuilder.parse(connection.getInputStream());
                Element el = doc.getDocumentElement();
                NodeList list = el.getElementsByTagName("media:content");
                String cursor = urlYoutube;
                for (int i = 0; i < list.getLength(); i++) {
                    Node node = list.item(i);
                    if (node != null) {
                        NamedNodeMap nodeMap = node.getAttributes();
                        HashMap<String, String> maps = new HashMap<String, String>();
                        for (int j = 0; j < nodeMap.getLength(); j++) {
                            Attr att = (Attr) nodeMap.item(j);
                            maps.put(att.getName(), att.getValue());
                        }
                        if (maps.containsKey("yt:format")) {
                            String f = maps.get("yt:format");
                            if (maps.containsKey("url"))
                                cursor = maps.get("url");
                            if (f.equals("1"))
                                return cursor;
                        }
                    }
                }
                return cursor;
            } catch (Exception ex) {
                return urlYoutube;
            }
        }

        public String extractYoutubeId(String url) throws MalformedURLException {
            String query = new URL(url).getQuery();
            String[] param = query.split("&");
            String id = null;
            for (String row : param) {
                String[] param1 = row.split("=");
                if (param1[0].equals("v")) {
                    id = param1[1];
                }
            }
            return id;
        }
    }*/


}
