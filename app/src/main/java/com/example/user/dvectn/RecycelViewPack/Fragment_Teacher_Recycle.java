package com.example.user.dvectn.RecycelViewPack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.user.dvectn.Fragment.Fragment_login;
import com.example.user.dvectn.Fragment.Teacher_layout_save;
import com.example.user.dvectn.Fragment.Teacher_spy_save;
import com.example.user.dvectn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/3/2561.
 */

public class Fragment_Teacher_Recycle extends Fragment {
    String str_tch;
    List<String> Data_th;
    List<String> Data_url_th;
    RecyclerView recyclerView;
    RecycleViewAdapter recycleViewAdapter;

    public static final String TAG_TCH ="TCH";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewtch = inflater.inflate(R.layout.teacher_layout,container,false);
//        str_tch = bd_tch.getString(Fragment_login.TAG_user);

        showdawaefah(viewtch);


//        FloatingActionButton fab1 = viewtch.findViewById(R.id.fab1);
//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()){
//                    case R.id.fab1:
//                        Bundle bgh = new Bundle();
//                        bgh.putString(TAG_TCH,"147258");
//
//                        Snackbar.make(view, "โปรดกรอกข้อมูล", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//
//                        Teacher_layout_save fragment_Teacher_Recycle = new Teacher_layout_save();
//                        fragment_Teacher_Recycle.setArguments(bgh);
//                        replaceFragment(fragment_Teacher_Recycle,bgh);
//                        break;
//                }
//            }
//        });
        return viewtch;



    }
    private void showdawaefah(View view) {


        Data_th = new ArrayList<>();
//        Data_url_th = new ArrayList<>();




        Data_th.add("นาย อักษรขจร รถผ่าน");
        Data_th.add("นางสาว ประวิทย์ ฉลาดจุง");
        Data_th.add("นาย ประหยัด จันทร์อังคารพุธ");
//      Data_url_th.add("https://images.pexels.com/photos/52710/matterhorn-zermatt-switzerland-snow-52710.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");



        recyclerView = view.findViewById(R.id.LV_th_1);

        recycleViewAdapter = new RecycleViewAdapter(getContext());
        //Toast.makeText(getContext(), ""+Data_th.size(), Toast.LENGTH_SHORT).show();

        recycleViewAdapter.Update_Data(Data_th);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleViewAdapter);
    }


    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.replace(R.id.content,fragment).addToBackStack(null).commit();


    }






}
