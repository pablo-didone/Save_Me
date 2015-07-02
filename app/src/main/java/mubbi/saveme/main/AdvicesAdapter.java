package mubbi.saveme.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mubbi.saveme.R;
import mubbi.saveme.configuration.Advice;

/**
 * Created by pdidone on 29/06/2015.
 */
public class AdvicesAdapter extends ArrayAdapter<Advice> {

    private Activity context;
    private ArrayList<Advice> data;

    public AdvicesAdapter(Activity context, ArrayList<Advice> list){
        super(context, R.layout.list_item_advice, list);
        this.context = context;
        this.data = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View item = convertView;
        final ViewHolder holder;

        if (item == null){
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.list_item_advice,null);

            holder = new ViewHolder();
            holder.adviceId = (TextView)item.findViewById(R.id.lblAdviceId);
            holder.adviceName = (TextView)item.findViewById(R.id.lblAdviceName);

            item.setTag(holder);
        }else{
            holder = (ViewHolder)item.getTag();
        }

        holder.adviceId.setText(Integer.toString(data.get(position).getId()).toUpperCase());
        holder.adviceName.setText(data.get(position).getTitle());

        return item;
    }

    static class ViewHolder{
        TextView adviceId;
        TextView adviceName;
    }


}
