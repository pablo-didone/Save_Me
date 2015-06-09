package mubbi.saveme.contact_list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import mubbi.saveme.R;

/**
 * Created by pdidone on 04/06/2015.
 */
public class ContactsAdapter extends ArrayAdapter<ContactRow> {

    private Activity context;
    private ArrayList<ContactRow> data;

    public ContactsAdapter(Activity context, ArrayList<ContactRow> rowList){
        super(context, R.layout.list_item_contacts, rowList);
        this.context = context;
        this.data = rowList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View item = convertView;
        final ViewHolder holder;

        if (item == null){
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.list_item_contacts,null);

            holder = new ViewHolder();
            holder.contactName = (TextView)item.findViewById(R.id.lblContactName);
            holder.contactPhone = (TextView)item.findViewById(R.id.lblContactPhone);
            holder.selectedContact = (CheckBox)item.findViewById(R.id.chkSelectedContact);

            item.setTag(holder);
        }else{
            holder = (ViewHolder)item.getTag();
        }

        holder.contactName.setText(data.get(position).getContact().getName());
        holder.contactPhone.setText(data.get(position).getContact().getPhone());

        holder.selectedContact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.get(position).setChecked(isChecked);
            }
        });

        if(data.get(position).isChecked()){
            holder.selectedContact.setChecked(true);
        }else{
            holder.selectedContact.setChecked(false);
        }

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.selectedContact.isChecked()){
                    holder.selectedContact.setChecked(false);
                    data.get(position).setChecked(false);
                }else{
                    holder.selectedContact.setChecked(true);
                    data.get(position).setChecked(true);
                }
            }
        });

        return item;
    }

    static class ViewHolder{
        TextView contactName;
        TextView contactPhone;
        CheckBox selectedContact;
    }


}
