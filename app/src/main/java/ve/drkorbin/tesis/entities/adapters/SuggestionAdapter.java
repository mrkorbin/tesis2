package ve.drkorbin.tesis.entities.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ve.drkorbin.tesis.R;
import ve.drkorbin.tesis.entities.Guide;
import ve.drkorbin.tesis.entities.Suggestion;

/**
 * Created by parcka on 20/09/16.
 */
public class SuggestionAdapter extends BaseAdapter {


    protected Activity activity;
    protected ArrayList<Suggestion> items;

    public SuggestionAdapter() {
    }

    public SuggestionAdapter(Activity activity, ArrayList<Suggestion> items) {
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



    public void addAll(ArrayList<Suggestion> suggestions) {
        for (int i = 0; i < suggestions.size(); i++) {
            items.add(suggestions.get(i));
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
            v = inf.inflate(R.layout.item_suggestion, null);
        }

        Suggestion suggestion = items.get(position);

        TextView email = (TextView) v.findViewById(R.id.suggestionEmail);
        email.setText(suggestion.getEmail());

        TextView description = (TextView) v.findViewById(R.id.suggestionDescripcion);
        description.setText(suggestion.getDescription());

        return v;
    }
}