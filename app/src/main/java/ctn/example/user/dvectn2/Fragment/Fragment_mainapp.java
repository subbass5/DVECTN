package ctn.example.user.dvectn2.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.RecycelViewPack.Fragment_bt_ag;
import ctn.example.user.dvectn2.RecycelViewPack.Fragment_bt_d1;


/**
 * Created by User on 19/2/2561.
 */

public class Fragment_mainapp extends Fragment implements View.OnClickListener {
    TextView TV_1;
    Bundle bundle1;
    String user;
    String frg;
    SharedPreferences sharedPreferences;

    public static final String TAG_HELL = "Hello";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.av_main_traner, container, false);


        view.findViewById(R.id.BT_D).setOnClickListener(this);
        view.findViewById(R.id.BT_PD).setOnClickListener(this);
        view.findViewById(R.id.BT_DD).setOnClickListener(this);
        view.findViewById(R.id.BT_PJ).setOnClickListener(this);
        view.findViewById(R.id.btn_logout1).setOnClickListener(this);

        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);

//        Toast.makeText(getContext(), ""+Memberid, Toast.LENGTH_SHORT).show();

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return view;


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
        switch (view.getId()) {
            case R.id.BT_D:
                Fragment_bt_d1 av_bt_d = new Fragment_bt_d1();
                replaceFragment(av_bt_d, null);
                break;
            case R.id.BT_PD:
                Fragment_AF_ELI_missdate av_bt_ag = new Fragment_AF_ELI_missdate();
                replaceFragment(av_bt_ag, null);

                break;
            case R.id.BT_DD:
                Fragment_AF_dd af_dd_list = new Fragment_AF_dd();
                replaceFragment(af_dd_list, null);
                break;
            case R.id.BT_PJ:
                Fragment_bt_pj av_bt_pj = new Fragment_bt_pj();
                replaceFragment(av_bt_pj, null);
                break;
            case  R.id.btn_logout1:
                Logout();

        }

    }
    private void Logout(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("คำเตือน");
        builder.setMessage("Logout ?");

        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                fragmentManager.popBackStack();

                Fragment_login fragment_login = new Fragment_login();
                replaceFragment(fragment_login,null);


            }
        });

        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


}
