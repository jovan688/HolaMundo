package enterprise.com.ni.sifac.fragmento;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ni.sifac.dao.Cartera;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import enterprise.com.ni.sifac.Actividades.DaoAPP;
import enterprise.com.ni.sifac.R;
import enterprise.com.ni.sifac.adaptador.CustomerAdapter;
import enterprise.com.ni.sifac.adaptador.helper.OnStartDragListener;
import enterprise.com.ni.sifac.adaptador.helper.SimpleItemTouchHelperCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarteraFragmentList extends Fragment implements CarteraFragmentListener, OnStartDragListener {

    @Bind(R.id.CustomerRecyclerview)
    RecyclerView recyclerView;
    List<Cartera> dataset = new ArrayList<Cartera>();
    CustomerAdapter adapter = null;
    @Bind(R.id.btn_Search)
    Button btnSearch;
    @Bind(R.id.btn_NewCustormer)
    Button btnNewCustormer;
    @Bind(R.id.btn_NewSale)
    Button btnNewSale;

    private ItemTouchHelper mItemTouchHelper;
    View view = null;

    public CarteraFragmentList() {

        dataset = new ArrayList<Cartera>();
        dataset.addAll(DaoAPP.getSession().getCarteraDao().loadAll());
        for (int i = 0; i <= 20; i++) {
            for (Cartera c : DaoAPP.getSession().getCarteraDao().loadAll()) {
                Log.e("Cartera", c.getNombreCompleto());
                dataset.add(c);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cartera_fragment_list, container, false);

        ButterKnife.bind(this, view);
        return view; //new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        InitAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void InitAdapter() {
        if (adapter == null) {
            adapter = new CustomerAdapter(getActivity(), this, dataset);
        }
    }

    @Override
    public void executeAction(String action) {
        Toast.makeText(getActivity(), action, Toast.LENGTH_SHORT).show();
    }

    public void setData(List<Cartera> list) {
        this.dataset = (list == null ? new ArrayList<Cartera>() : list);
        adapter.setData(this.dataset);
        executeAction("Data Added");
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_NewCustormer)
    public void OnClick_btnNewCustomer() {
        New_Customer fragment = new New_Customer();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }

    @OnClick(R.id.btn_NewSale)
    public void OnClick_btnNewSale() {
        New_Customer fragment = new New_Customer();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
