package ve.drkorbin.tesis;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.utils.TesisConstants;

public class GuideShow extends YouTubeBaseActivity {

    public static final String API_KEY = "AIzaSyD9Wd4gwdlUQqXb9sf7M82ZJaUlsshSNLA";

    TextView title;
    TextView description;
    VideoView youtubeVideo;
    private Uri uriYouTube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_show);

        Guide guide = new Guide();

        title = (TextView) findViewById(R.id.textViewTitle);
        description = (TextView) findViewById(R.id.textViewDescription);
//        mWebView = (WebView) findViewById(R.id.WebView);
//        youtubeVideo = (VideoView) findViewById(R.id.viewYoutube);

        Intent intent = getIntent();

        if (intent.getExtras().get(TesisConstants.GUIDE) != null) {
            guide = (Guide) intent.getExtras().get(TesisConstants.GUIDE);

        }

        if (guide != null) {
            title.setText(guide.getTitulo());
            description.setText(guide.getDescripcion());

          /*  WebView engine = (WebView) findViewById(R.id.webView);

            engine.getSettings().setJavaScriptEnabled(true);
            engine.getSettings().setPluginState(WebSettings.PluginState.ON);
            engine.loadUrl("http://www.youtube.com/embed/B08iLAtS3AQ?autoplay=1");*/


            String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/x-u1wHZLezc\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

            WebView displayYoutubeVideo = (WebView) findViewById(R.id.webView);
            displayYoutubeVideo.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            WebSettings webSettings = displayYoutubeVideo.getSettings();
            webSettings.setJavaScriptEnabled(true);
            displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");


          /*  RTSPUrlTask truitonTask = new RTSPUrlTask();
            truitonTask.execute("https://www.youtube.com/watch?v=2zNSgSzhBfM");
*/


            /*//Para reproducir archivos en streaming, como vídeos de Youtube:
            youtubeVideo.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=cORa0CuPXuY             "));
            //Para reproducir archivos almacenados en la memoria SDCard:
//            reproductor.setVideoPath("/mnt/sdcard/videoEjemplo.mp4");
            youtubeVideo.setMediaController(new MediaController(this));
            youtubeVideo.start();
//            mWebView.requestFocus();*/

           /* mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
            String videoID = "Kuz3DUNZaC8";
            mWebView.loadUrl("http://www.youtube.com/embed/" + videoID + "?autoplay=1&vq=small");
            mWebView.setWebChromeClient(new WebChromeClient());*/

            /* MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(youtubeVideo);
            youtubeVideo.setMediaController(mediaController);
            youtubeVideo.setVideoPath("https://www.youtube.com/watch?v=Kuz3DUNZaC8");
            youtubeVideo.requestFocus();
            youtubeVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    youtubeVideo.start();
                }});*/

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
