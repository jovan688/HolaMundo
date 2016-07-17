package enterprise.com.ni.sifac.adaptador;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import enterprise.com.ni.sifac.R;
import enterprise.com.ni.sifac.adaptador.helper.ItemTouchHelperViewHolder;

/**
 * Created by STARK on 19/06/2016.
 */
public class CustomerViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {


    @Bind(R.id.txtviewNum)
    Button txtviewNum;
    @Bind(R.id.txtviewcustomername)
    TextView txtviewcustomername;
    @Bind(R.id.txtviewcity)
    TextView txtviewcity;
    @Bind(R.id.txtviewamount)
    TextView txtviewamount;
    @Bind(R.id.txtviewdatepay)
    TextView txtviewdatepay;

    private  View view;

    public CustomerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.view = itemView;
    }

    /*
    public  void  setClickListener(final Cartera cartera , final OnItemClickListener listener){
    view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onItemClick(cartera);
        }
    });
    }
    */

    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }
}
