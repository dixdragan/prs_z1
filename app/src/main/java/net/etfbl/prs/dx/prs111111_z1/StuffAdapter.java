package net.etfbl.prs.dx.prs111111_z1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 * Copyright (c) 2017 Elektrotehnički fakultet
 * Patre 5, Banja Luka
 *
 * All Rights Reserved
 *
 * \file StuffAdapter.java
 * \brief
 *      This class is the adapter for displaying the item of the shopping list.
 *      It has new methods to add an item and to se how many are unchecked.
 *
 * Created on 04.04.2017.
 *
 * @Author Dragan Milanović
 *
 * \notes
 *      05.04.2017. -  logs and checks removed
 *
 * \history
 */
public class StuffAdapter extends BaseAdapter {

    private Context aContext;
    private ArrayList<ShoppingItem> aItems;

    public StuffAdapter(Context context) {
        aItems = new ArrayList<ShoppingItem>();
        aContext = context;
    }

    @Override
    public int getCount() {
        return aItems.size();
    }

    @Override
    public Object getItem(int position) {
        Object rv = null;
        try {
            rv = aItems.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return rv;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.shopping_item_layout, null);
        }

        CheckBox check = (CheckBox) view.findViewById(R.id.checkItem);
        TextView name = (TextView) view.findViewById(R.id.textItem);
        ImageView image = (ImageView) view.findViewById(R.id.imageItem);

        check.setChecked(aItems.get(position).iChecked);
        name.setText(aItems.get(position).iName);
        image.setImageDrawable(aItems.get(position).iImage);

        return view;
    }

    /************************************************************************/
    /**
     *  @brief   Adds a new item to the list.
     *
     *  @param   item - the item to be added
     *
     *************************************************************************/
    public void addItem(ShoppingItem item) {
        aItems.add(item);
    }

    /************************************************************************/
    /**
     *  @brief   Returns the number of items in the list that are not ticked.
     *
     * @return  the number of items in the list that are not ticked
     *
     *************************************************************************/
    public int itemsUnchecked() {
        int rt = aItems.size();
        for (ShoppingItem i:aItems){
            if(i.iChecked) rt--;
        }
        return rt;
    }
}
