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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import ctn.example.user.dvectn2.POJO.POJO_PJ_P1;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_PJ_P1;
import okhttp3.ResponseBody;

/**
 * Created by User on 20/2/2561.
 */

public class Fragment_bt_pj_1_EIL extends Fragment implements OnClickListener{
    String frg;
    String dep_id = "";
    Spinner spin1 , spin2 , spin3;
    String Age_spin[] = {"-","ไม่เกิน 25 ปี","26 - 35 ปี", "36 - 45 ปี", "46 - 55 ปี", "56 ปีขึ้นไป"};
    String Workage_spin[] = {"-","ไม่เกิน 5 ปี","5 - 10 ปี", "10 - 20 ปี", "20 - 30 ปี", "40 ปีขึ้นไป"};
    String highclass_spin[] = {"-","ประกาศนียบัตรวิชาชีพหรือเทียบเท่า","อนุปริญญาหรือเทียบเท่า", "ปริญญาตรีหรือเทียบเท่า", "สูงกว่าปริญญาตรี", "อื่นๆ"};
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    Context context;


     CheckBox CB1,CB2,CB3,CB4;



     int    ex11_tmp = -1 ,
            ex12_tmp = -1 ,
            memberId =  0 ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.av_bt_pj_1, container, false);
        context = getContext();

        CB1 = view.findViewById(R.id.CB1);
        CB1.setChecked(false);

        CB2 = view.findViewById( R.id.CB2);
        CB2.setChecked(false);

        CB3 = view.findViewById( R.id.CB3);
        CB3.setChecked(false);

        CB4 = view.findViewById( R.id.CB4);
        CB4.setChecked(false);

        spin1 = view.findViewById(R.id.age_spinner);
        ArrayAdapter adaptertest1 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Age_spin);
        spin1.setAdapter(adaptertest1);

        spin2 = view.findViewById(R.id.workage_spinner);
        ArrayAdapter adaptertest2 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Workage_spin);
        spin2.setAdapter(adaptertest2);

        spin3 = view.findViewById(R.id.highclass_spiner);
        ArrayAdapter adaptertest3 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,highclass_spin);
        spin3.setAdapter(adaptertest3);

        view.findViewById(R.id.CB1).setOnClickListener(this);
        view.findViewById( R.id.CB2).setOnClickListener(this);
        view.findViewById( R.id.CB3).setOnClickListener(this);
        view.findViewById( R.id.CB4).setOnClickListener(this);

        view.findViewById( R.id.btn_confirm).setOnClickListener(this);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);
        memberId = sharedPreferences.getInt(Fragment_login.KEY_member_id,0);



        return view;


    }

    private  void ConfirmEx(int ex11,int ex12,int ex13,int ex14,int ex15){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading......");
        progressDialog.show();

        if(memberId > 0 ){
            new NetworkConnectionManager().callServer_pj_p1_EIL(onCallbackList,memberId,ex11,ex12,ex13,ex14,ex15);
        }else {
            Toast.makeText(context, "กรุราตรวจสอบข้อมูล", Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }


    }
    OnNetworkCallback_PJ_P1 onCallbackList = new OnNetworkCallback_PJ_P1() {
        @Override
        public void onResponse(POJO_PJ_P1 getstu) {


            editor.commit();


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
        frgTran.replace( R.id.content,fragment).addToBackStack(null).commit();


    }


    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case  R.id.CB1:
            ex11_tmp = 1;
            CB2.setChecked(false);
            break;
        case  R.id.CB2:
             ex11_tmp = 2;
             CB1.setChecked(false);
            break;
        case  R.id.CB3:
            ex12_tmp = 1;
            CB4.setChecked(false);
            break;
        case  R.id.CB4:
            ex12_tmp = 2;
            CB3.setChecked(false);
            break;
        case  R.id.btn_confirm:



            if(ex11_tmp != -1 && ex12_tmp != -1 && (CB1.isChecked() || CB2.isChecked()) && (CB3.isChecked() || CB4.isChecked())  ){
                ConfirmEx(ex11_tmp,ex12_tmp,getStatus_age(spin1.getSelectedItem().toString()),getStatus_agework(spin2.getSelectedItem().toString())
                        ,getStatus_highclass(spin3.getSelectedItem().toString()));

//                Fragment_bt_pj av_bt_pj_1 = new Fragment_bt_pj();
//                replaceFragment(av_bt_pj_1,null);
//
//                Toast.makeText(context, "กรอกข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "กรุณาเลือกข้อมูล", Toast.LENGTH_SHORT).show();
            }
            break;
    }

    }
    private int getStatus_age(String input){

        if(input.equals("-")){
            return 0;
        }else if (input.equals("ไม่เกิน 25 ปี")){
            return 1;
        }else if (input.equals("26 - 35 ปี")){
            return 2;
        }else if (input.equals("36 - 45 ปี")){
            return 3;
        }else if (input.equals("46 - 55 ปี")){
            return 4;
        }else if (input.equals("56 ปีขึ้นไป")){
            return 5;
        }else {
            return -1;
        }
    }

    private int getStatus_agework(String input){

        if(input.equals("-")){
            return 0;
        }else if (input.equals("ไม่เกิน 5 ปี")){
            return 1;
        }else if (input.equals("5 - 10 ปี")){
            return 2;
        }else if (input.equals("10 - 20 ปี")){
            return 3;
        }else if (input.equals("20 - 30 ปี")){
            return 4;
        }else if (input.equals("40 ปีขึ้นไป")){
            return 5;
        }else {
            return -1;
        }
    }

    private int getStatus_highclass(String input){

        if(input.equals("-")){
            return 0;
        }else if (input.equals("ประกาศนียบัตรวิชาชีพหรือเทียบเท่า")){
            return 1;
        }else if (input.equals("อนุปริญญาหรือเทียบเท่า")){
            return 2;
        }else if (input.equals("ปริญญาตรีหรือเทียบเท่า")){
            return 3;
        }else if (input.equals("สูงกว่าปริญญาตรี")){
            return 4;
        }else if (input.equals("อื่นๆ")){
            return 5;
        }else {
            return -1;
        }
    }
}




