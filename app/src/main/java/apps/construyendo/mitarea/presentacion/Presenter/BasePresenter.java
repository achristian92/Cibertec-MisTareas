package apps.construyendo.mitarea.presentacion.Presenter;

import apps.construyendo.mitarea.presentacion.View.BaseView;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class BasePresenter <V extends BaseView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }
}
