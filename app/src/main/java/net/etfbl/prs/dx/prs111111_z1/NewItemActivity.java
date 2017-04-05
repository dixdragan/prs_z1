package net.etfbl.prs.dx.prs111111_z1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 *
 * Copyright (c) 2017 Elektrotehnički fakultet
 * Patre 5, Banja Luka
 *
 * All Rights Reserved
 *
 * \file NewItemActivity.java
 * \brief
 *      This class is used to add a new item of the shopping list.
 *      Checks everything and returns it to the MainActivity.
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

public class NewItemActivity extends AppCompatActivity {

    String rtName = "", rtType = "";
    EditText etName;
    RadioGroup radioGroup;
    RadioButton rbOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        etName = (EditText) findViewById(R.id.etName);
        radioGroup = (RadioGroup) findViewById(R.id.rgTypes);
        rbOther = (RadioButton) findViewById(R.id.rbOther);
    }

    /************************************************************************/
    /**
     *  @brief   Checks the input and returns it to the MainActivity.
     *
     *  @param   v - the view that called the method
     *
     *************************************************************************/
    public void onAddItem(View v) {
        rtName = etName.getText().toString();
        if(TextUtils.isEmpty(rtName)) {
            etName.setError(":(");
            return;
        }else{
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            if(radioButton == null){
                rbOther.setChecked(true);
            }else{
                switch(radioButton.getId()) {
                    case R.id.rbFood:
                        rtType = "food";
                        break;
                    case R.id.rbDrink:
                        rtType = "drink";
                        break;
                    case R.id.rbCloth:
                        rtType = "cloth";
                        break;
                    default: rtType = "other";
                        break;
                }
                // Data intent
                Intent data = new Intent();
                data.putExtra("name", rtName);
                data.putExtra("type", rtType);
                setResult(Activity.RESULT_OK, data);
                this.finish();
            }
        }
    }
    /************************************************************************/
    /**
     *  @brief   Handles the back button press when adding a new item.
     *
     *************************************************************************/
    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }
}
