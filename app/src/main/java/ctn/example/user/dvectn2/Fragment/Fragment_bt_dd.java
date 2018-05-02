package ctn.example.user.dvectn2.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ctn.example.user.dvectn2.R;

/**
 * Created by User on 20/2/2561.
 */

public class Fragment_bt_dd extends Fragment implements View.OnClickListener{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view4 =inflater.inflate(R.layout.av_bt_dd,container,false);
        view4.findViewById(R.id.BBTN_1).setOnClickListener(this);
        view4.findViewById(R.id.BBTN_2).setOnClickListener(this);
        view4.findViewById(R.id.BBTN_3).setOnClickListener(this);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return  view4;

    }

    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        frgTran.replace(R.id.content,fragment).addToBackStack(null).commit();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case  R.id.BBTN_1:
                 Fragment_bt_dd_1 av_bt_dd_1 = new Fragment_bt_dd_1();
                replaceFragment(av_bt_dd_1,null);
                break;
            case  R.id.BBTN_2:
                Fragment_bt_dd_2 av_bt_dd_2 = new Fragment_bt_dd_2();
                replaceFragment(av_bt_dd_2,null);
                break;
            case  R.id.BBTN_3:
                Fragment_bt_dd_3 av_bt_dd_3 = new Fragment_bt_dd_3();
                replaceFragment(av_bt_dd_3,null);
                break;
        }

    }
}
