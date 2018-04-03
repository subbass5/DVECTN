package com.example.user.dvectn.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.dvectn.R;


/**
 * Created by Praipran on 3/21/2018.
 */

public class Fragment_AF_Teacherlayout extends Fragment {


    String [] chataterNaJa ={ "นิเทศครั้งที่ 1","นิเทศครั้งที่ 2"};
    Context context;

    String scoreTmp="";
    public static final String TAG_OHNOTH = "OHNOTH";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v4 =inflater.inflate(R.layout.af_teacher_layout,container,false);
        ListView listView =(ListView) v4.findViewById(R.id.list_af_th);
        context = getContext();
        //init session
        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ArrayAdapter ListViewAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,chataterNaJa);
        listView.setAdapter(ListViewAdapter);
//        Toast.makeText(context, "Admin1", Toast.LENGTH_SHORT).show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

                editor.putInt(Fragment_login.KEY_SUPERVISION,position);
                editor.commit();
//                Toast.makeText(context, "Sp  = "+position, Toast.LENGTH_SHORT).show();



               Fragment_AF_Teacherlayout2 af_teacherlayout2 = new Fragment_AF_Teacherlayout2();
               replaceFragment(af_teacherlayout2, null);

            }
        });


        return v4;
    }

    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.replace(R.id.content,fragment).addToBackStack(null).commit();

    }






}

