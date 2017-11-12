package zxdmjr.com.uberclone.custom;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by SWAJAN on 11/13/2017.
 */

public class Toaster {

    public static void toast(Context context, String message){

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
