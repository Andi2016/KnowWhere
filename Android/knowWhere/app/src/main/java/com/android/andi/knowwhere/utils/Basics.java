package com.android.andi.knowwhere.utils;

import com.android.andi.knowwhere.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andi Xu on 4/8/18.
 */

public class Basics {

    public static String stamp2Date(String seconds, String format){

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(Long.valueOf(seconds)));
    }

}
