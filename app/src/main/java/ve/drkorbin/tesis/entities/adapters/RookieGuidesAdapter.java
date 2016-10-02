package ve.drkorbin.tesis.entities.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import ve.drkorbin.tesis.R;
import ve.drkorbin.tesis.entities.Guide;

/**
 * Created by parcka on 20/09/16.
 */
public class RookieGuidesAdapter extends BaseAdapter {


    protected Activity activity;
    protected ArrayList<Guide> items;

    public RookieGuidesAdapter() {
    }

    public RookieGuidesAdapter(Activity activity, ArrayList<Guide> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }



    public void addAll(ArrayList<Guide> guides) {
        for (int i = 0; i < guides.size(); i++) {
            items.add(guides.get(i));
        }
    }



    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_guide, null);
        }

        Guide guide = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(guide.getTitulo());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(guide.getDescripcion());

        


       /* ImageView imagen = (ImageView) v.findViewById(R.id.imageView);
        imagen.setImageDrawable(guide.getImage());*/

        return v;
    }
}