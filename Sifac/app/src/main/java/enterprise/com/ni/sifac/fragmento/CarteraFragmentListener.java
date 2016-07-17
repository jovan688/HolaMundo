package enterprise.com.ni.sifac.fragmento;

import android.support.v7.widget.RecyclerView;

/**
 * Created by STARK on 19/06/2016.
 */
public interface CarteraFragmentListener {
    void executeAction(String action);

    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
