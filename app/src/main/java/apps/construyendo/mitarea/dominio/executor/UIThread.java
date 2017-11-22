package apps.construyendo.mitarea.dominio.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;


/**
 * Created by Christian 24 on 18/11/2017.
 */

public class UIThread implements PostExecutionThread {

    private Handler uiHander=new Handler(Looper.getMainLooper());
    @Override
    public void execute(@NonNull Runnable runnable) {
        uiHander.post(runnable);
    }
}
