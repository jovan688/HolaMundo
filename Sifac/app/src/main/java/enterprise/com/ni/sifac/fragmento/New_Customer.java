package enterprise.com.ni.sifac.fragmento;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import enterprise.com.ni.sifac.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class New_Customer extends Fragment {


    public New_Customer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new__customer, container, false);
    }

}
