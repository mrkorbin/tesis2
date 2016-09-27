package ve.drkorbin.tesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.utils.TesisConstants;

public class GuideShow extends AppCompatActivity {

    TextView title;
    TextView description;
    VideoView youtubeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_show);

        Guide guide = new Guide();

        title = (TextView) findViewById(R.id.textViewTitle);
        description = (TextView) findViewById(R.id.textViewDescription);

        Intent intent = getIntent();

        if (intent.getExtras().get(TesisConstants.GUIDE) != null) {
            guide = (Guide) intent.getExtras().get(TesisConstants.GUIDE);

        }

        if (guide != null) {
            title.setText(guide.getTitulo());
            description.setText(guide.getDescripcion());
        }


    }
}
