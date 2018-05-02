package ctn.example.user.dvectn2.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import ctn.example.user.dvectn2.POJO.POJO_DD_P1;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_DD_P1;
import okhttp3.ResponseBody;

/**
 * Created by User on 20/2/2561.
 */

public class Fragment_bt_dd_1 extends Fragment implements View.OnClickListener
{
    Context context;
    String frg;
    String nameList[] = {"-","0","1","2","3","4","5"};
    Spinner spn1, spn2 , spn3 , spn4 , spn5 ;
    ProgressDialog progressDialog;
    String dep_id = "";
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String memberId = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.av_bt_dd_1,container, false);
        context = getContext();




        spn1 = view.findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,nameList);
        spn1.setAdapter(adapter);

        spn2 = view.findViewById(R.id.spinner2);
        ArrayAdapter adapter1 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,nameList);
        spn2.setAdapter(adapter1);

        spn3 = view.findViewById(R.id.spinner3);
        ArrayAdapter adapter2 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,nameList);
        spn3.setAdapter(adapter2);

        spn4 = view.findViewById(R.id.spinner4);
        ArrayAdapter adapter3 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,nameList);
        spn4.setAdapter(adapter3);

        spn5 = view.findViewById(R.id.spinner5);
        ArrayAdapter adapter4 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,nameList);
        spn5.setAdapter(adapter4);




        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ด้านคุณลักษณะอันพึงประสงค์");


        view.findViewById(R.id.bbbtn1).setOnClickListener(this);

        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);
        memberId = sharedPreferences.getString(Fragment_AF_dd.KEY_STD_ID,"");


        return view;
    }


    OnNetworkCallback_DD_P1 onCallbackList = new OnNetworkCallback_DD_P1() {
        @Override
        public void onResponse(POJO_DD_P1 status) {
            Toast.makeText(context, "บันทึกข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Toast.makeText(context, "responseBodyError", Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

        }

        @Override
        public void onBodyErrorIsNull() {

            Toast.makeText(context, "res is null", Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

        }

        @Override
        public void onFailure(Throwable t) {

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

        }
    };

    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        frgTran.replace(R.id.content,fragment).addToBackStack(null).commit();


    }


    private void senddata (){
        String[] tmpSpn = {spn1.getSelectedItem().toString(),spn2.getSelectedItem().toString(),spn3.getSelectedItem().toString(),
                spn4.getSelectedItem().toString(),spn5.getSelectedItem().toString()};

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading......");
        progressDialog.show();


        if(tmpSpn != null){
            if ((!tmpSpn[0].equals("-")) && (!tmpSpn[1].equals("-")) && (!tmpSpn[2].equals("-")) && (!tmpSpn[3].equals("-")) && (!tmpSpn[4].equals("-"))
                     ){
                new NetworkConnectionManager().callServer_dd_p1(onCallbackList,Integer.parseInt(memberId),Integer.parseInt(tmpSpn[0]),Integer.parseInt(tmpSpn[1]),Integer.parseInt(tmpSpn[2]),Integer.parseInt(tmpSpn[3])
                        ,Integer.parseInt(tmpSpn[4]));
            }else {
                Toast.makeText(getContext(),"กรุณากรอกให้ครบ",Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }


        }else {
            Toast.makeText(getContext(), "wrong", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.bbbtn1:

                senddata ();
                break;
        }
    }
}