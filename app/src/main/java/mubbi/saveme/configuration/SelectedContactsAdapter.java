package mubbi.saveme.configuration;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mubbi.saveme.R;
import mubbi.saveme.contact_list.Contact;

/**
 * Created by Wiliam Bell on 11/06/2015.
 */
public class SelectedContactsAdapter extends ArrayAdapter<Contact> {

    private Activity context;
    private ArrayList<Contact> data;

    public SelectedContactsAdapter(Activity context, ArrayList<Contact> list){
        super(context, R.layout.list_item_selected_contacts, list);
        this.context = context;
        this.data = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View item = convertView;
        final ViewHolder holder;

        if (item == null){
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.list_item_selected_contacts, null);

            holder = new ViewHolder();
            holder.contactName = (TextView)item.findViewById(R.id.lblSelectedContactName);
            holder.contactPhone = (TextView)item.findViewById(R.id.lblSelectedContactPhone);

            item.setTag(holder);
        }else{
            holder = (ViewHolder)item.getTag();
        }

        holder.contactName.setText(data.get(position).getName());
        holder.contactPhone.setText(data.get(position).getPhone());

        return item;
    }

    static class ViewHolder{
        TextView contactName;
        TextView contactPhone;
    }
}
