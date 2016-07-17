package enterprise.com.ni.sifac.adaptador;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ni.sifac.dao.Cartera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enterprise.com.ni.sifac.R;
import enterprise.com.ni.sifac.adaptador.helper.ItemTouchHelperAdapter;
import enterprise.com.ni.sifac.adaptador.helper.OnStartDragListener;

/**
 * Created by STARK on 19/06/2016.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder>  implements ItemTouchHelperAdapter {

    Context contex;
    List<Cartera> list = new ArrayList<Cartera>();
    private final OnStartDragListener mDragStartListener;


    public CustomerAdapter(Context context, OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;
    }
    public CustomerAdapter(Context context, OnStartDragListener dragStartListener,List<Cartera> dataset) {
        mDragStartListener = dragStartListener;
        this.list = dataset;
    }


    /*
    public  CustomerAdapter(Context context,List<Cartera> dataset){
        this.list = dataset;
        this.contex = context;
    }

    public  CustomerAdapter(Context context,List<Cartera> dataset,OnItemClickListener listener){
        this.list = dataset;
        this.contex = context;
        this.onItemClickListener = listener;
    }


    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cobrolist_item,parent,false);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {
        if(this.list.size() > 0){

            Cartera cartera =  this.list.get(position);

            holder.setClickListener(cartera,onItemClickListener);
            holder.txtviewcustomername.setText(cartera.getNombreCompleto());
            holder.txtviewamount.setText(cartera.getMontoCuota().toString());
            holder.txtviewdatepay.setText(cartera.getFechaAbono().toString());
            holder.txtviewcity.setText("Matagalpa , Comarca el Paisa");
            holder.txtviewNum.setText(String.valueOf(position));


        }
    }
*/
    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public  void  setData(List<Cartera> lista){
        this.list = lista;
    }


    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cobrolist_item, parent, false);
        CustomerViewHolder itemViewHolder = new CustomerViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomerViewHolder holder, int position) {

        Cartera cartera =  this.list.get(position);

        holder.txtviewcustomername.setText(cartera.getNombreCompleto());
        holder.txtviewamount.setText(cartera.getMontoCuota().toString());
        holder.txtviewdatepay.setText(cartera.getFechaAbono().toString());
        holder.txtviewcity.setText("");
        holder.txtviewNum.setText(String.valueOf(position));

        // Start a drag whenever the handle view it touched
        holder.txtviewNum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
