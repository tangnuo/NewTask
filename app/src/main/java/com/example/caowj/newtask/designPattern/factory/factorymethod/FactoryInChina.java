package com.example.caowj.newtask.designPattern.factory.factorymethod;

import android.util.Log;

import com.example.caowj.newtask.designPattern.factory.factorymethod.abstractMethod.FactoryMethod;
import com.example.caowj.newtask.designPattern.factory.simplefactory.MI2;
import com.example.caowj.newtask.designPattern.factory.simplefactory.MI3;
import com.example.caowj.newtask.designPattern.factory.simplefactory.UnKnown;
import com.example.caowj.newtask.designPattern.factory.simplefactory.abstractPhone.Phone;

/**
 * Created by tb on 2017/5/13.
 */

public class FactoryInChina extends FactoryMethod {
    private static final String TAG = "FactoryInChina";

    @Override
    public Phone createPhone(String type) {
        Phone phone = null;
        if (type.equals("mi2")) {
            phone = new MI2("mi2");
        } else if (type.equals("mi3")) {
            phone = new MI3("mi3");
        } else {
            phone = new UnKnown(type);
        }
        printCountry();
        return phone;
    }

    @Override
    public void printCountry() {
        Log.d(TAG, "printCountry: " + TAG);
    }
}
