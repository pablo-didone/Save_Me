package mubbi.saveme;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pdidone on 04/06/2015.
 */
public class ContactsAdapter extends ArrayAdapter<Contact> {

    private Activity context;
    private ArrayList<Row> data;

    public ContactsAdapter(Activity context, ArrayList<Contact> data) {
        super(context, R.layout.list_item_contacts, data);
        this.context = context;
        this.data = new ArrayList<>();
        adaptData(data);
    }

    private void  adaptData(ArrayList<Contact> dataIn){
        Row row;
        for (int i = 0; i < dataIn.size(); i++){
            row = new Row(dataIn.get(i));
            this.data.add(row);
        }
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



        return item;
    }

    static class ViewHolder{
        TextView contactName;
        TextView contactPhone;
        CheckBox selectedContact;
    }

    class Row{

        Contact contact;
        boolean checked;

        public Row(Contact contact){
            this.contact = contact;
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }

}
