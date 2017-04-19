package net.etfbl.prs.dx.prs111111_z1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 *
 * Copyright (c) 2017 Elektrotehnički fakultet
 * Patre 5, Banja Luka
 *
 * All Rights Reserved
 *
 * \file MainActivity.java
 * \brief
 *      This class is used to display all item of the shopping list.
 *      It has a button to add new item and a label to show how many are unchecked.
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
public class MainActivity extends AppCompatActivity {

    String LEFTITEMSTXT;

    public static int REQUEST_CODE = 8;

    StuffAdapter adapter;
    ListView list;
    TextView leftItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LEFTITEMSTXT = getString(R.string.LeftItemsText);;

        adapter = new StuffAdapter(this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        // OnItemClickListener works only if the views in the list are FOCUSABLE = FALSE;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ShoppingItem)adapter.getItem(position)).setChecked();
                adapter.notifyDataSetChanged();
                //    ((CheckBox) view.findViewById(R.id.checkItem)).setChecked(true);
                leftItems.setText(LEFTITEMSTXT + adapter.itemsUnchecked());
            }
        });
        leftItems = (TextView) findViewById(R.id.tvLeftItems);
    }

    /************************************************************************/
    /**
     *  @brief   Opens the Activity for adding a new item.
     *
     *  @param   v - the view that called the method
     *
     *************************************************************************/
    public void onNewItemClick(View v) {
        Intent i = new Intent(this, NewItemActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    /************************************************************************/
    /**
     *  @brief   Handles the data of the new item.
     *
     *  @param   requestCode - ID of request
     *  @param   resultCode - code to check if the result was OK
     *  @param   data - the data of the new item
     *
     *************************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            String itemName =  data.getExtras().getString("name");
            String itemPic = data.getExtras().getString("type");
            Drawable pic;

            switch(itemPic) {
                case "food":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        pic = getResources().getDrawable(R.drawable.food,getTheme());
                    }else{
                        pic = getResources().getDrawable(R.drawable.food);
                    }
                    break;
                case "drink":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        pic = getResources().getDrawable(R.drawable.drink,getTheme());
                    }else{
                        pic = getResources().getDrawable(R.drawable.drink);
                    }
                    break;
                case "cloth":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        pic = getResources().getDrawable(R.drawable.cloth,getTheme());
                    }else{
                        pic = getResources().getDrawable(R.drawable.cloth);
                    }
                    break;
                default:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        pic = getResources().getDrawable(R.drawable.other,getTheme());
                    }else{
                        pic = getResources().getDrawable(R.drawable.other);
                    }
                    break;
            }
            ShoppingItem newItem = new ShoppingItem(itemName, pic);
            adapter.addItem(newItem);
            adapter.notifyDataSetChanged();
            leftItems.setText(LEFTITEMSTXT + adapter.itemsUnchecked());
        }
    }
}
