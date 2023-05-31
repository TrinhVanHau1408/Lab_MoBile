package com.example.contractcontentprovider;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {


    private Activity context;
    private ArrayList<Contact> contactList = null;
    private LayoutInflater layoutInflater;

    public ContactAdapter(Activity context, ArrayList<Contact> arr) {
        this.context = context;
        contactList = arr;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        if (contact.getDisplayName()!=null) {
            holder.displayName.setText(contact.getDisplayName());
        }
        else holder.displayName.setText("");

        if (contact.getPhone() != null)
        {
            holder.number.setText(contact.getPhone());
        }
        else
        {
            holder.number.setText("");
        }

        if (position%2==0)
        {
            holder.cslParent.setBackgroundResource(R.color.white);
        }
        else
        {
            holder.cslParent.setBackgroundResource(R.color.light_blue);
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView displayName;

        private TextView number;
        private ConstraintLayout cslParent;

        public ContactViewHolder(View itemView) {
            super(itemView);

            displayName = itemView.findViewById(R.id.item_contact_txt_displayName);
            number = itemView.findViewById(R.id.item_contact_txt_number);

            cslParent = itemView.findViewById(R.id.item_contact_csl_parent);
        }
    }
}
