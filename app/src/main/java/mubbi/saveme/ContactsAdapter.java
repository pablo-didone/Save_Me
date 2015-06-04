package mubbi.saveme;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pdidone on 04/06/2015.
 */
public class ContactsAdapter extends ArrayAdapter<Contact> {

    private Activity context;
    private ArrayList<Contact> data;

    public ContactsAdapter(Activity context, ArrayList<Contact> data) {
        super(context, R.layout.list_item_contacts, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        ViewHolder holder;

        if (item == null){
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.list_item_contacts,null);

            holder = new ViewHolder();
            holder.contactName = (TextView)item.findViewById(R.id.lblContactName);
            holder.contactPhone = (TextView)item.findViewById(R.id.lblContactPhone);

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
