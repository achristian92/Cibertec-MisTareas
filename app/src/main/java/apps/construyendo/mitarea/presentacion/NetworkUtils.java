package apps.construyendo.mitarea.presentacion;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Christian 24 on 23/11/2017.
 */

public class NetworkUtils { //14..data
    public static boolean hayInternet(Context context){
        boolean hayInternet;

        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        hayInternet=(networkInfo!=null && networkInfo.isConnectedOrConnecting());
        return hayInternet;
    }
}
