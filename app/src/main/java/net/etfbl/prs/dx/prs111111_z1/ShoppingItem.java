package net.etfbl.prs.dx.prs111111_z1;

import android.graphics.drawable.Drawable;

/**
 *
 * Copyright (c) 2017 Elektrotehnički fakultet
 * Patre 5, Banja Luka
 *
 * All Rights Reserved
 *
 * \file ShoppingItem.java
 * \brief
 *      This class is used to model one item of the shopping list.
 *      Every item has a name and a type.
 *
 * Created on 04.04.2017.
 *
 * @Author Dragan Milanović
 *
 * \notes
 *      04.04.2017. - added setChecked() method
 *
 * \history
 */
public class ShoppingItem {

    public boolean iChecked;
    public String iName;
    public Drawable iImage;

    public ShoppingItem(String name, Drawable image) {
        this.iChecked = false;
        this.iName = name;
        this.iImage = image;
    }

    /************************************************************************/
    /**
     *  @brief   Sets the tick on a shopping item.
     *
     *************************************************************************/
 public void setChecked(){
        this.iChecked = true;
    }
}
