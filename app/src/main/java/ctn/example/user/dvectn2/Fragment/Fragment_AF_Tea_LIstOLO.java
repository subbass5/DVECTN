package ctn.example.user.dvectn2.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ctn.example.user.dvectn2.POJO.POJO_getdata_admin;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_getdata_admin;
import okhttp3.ResponseBody;

public class Fragment_AF_Tea_LIstOLO extends Fragment {

    Context context;
    ArrayAdapter ListViewAdapter;
    ProgressDialog progressDialog;
    List<String> namestore;
    List<String> dep_id_list;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String dep_id = "";
    ListView listView;
    String type = "";
    int ClassOfStudent = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v4 =inflater.inflate(ctn.example.user.dvectn2.R.layout.af_teacher_trainer,container,false);
        listView =(ListView) v4.findViewById(ctn.example.user.dvectn2.R.id.list_af_th_OLO);
        context = getContext();

        listView.setAdapter(ListViewAdapter);


        namestore = new ArrayList<>();
        dep_id_list = new ArrayList<>();


        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString(Fragment_AF_teacher_missdate.KEY_time,"yes");
        editor.commit();

        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);
        type = sharedPreferences.getString(Fragment_login.KEY_ClassOfTeachernaja,null);

        getStd();

        return v4;
    }

    private  void getStd(){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading......");
        progressDialog.show();

//        new NetworkConnectionManager().callSrever_trainer(onCallbackList);
        new NetworkConnectionManager().callServer_getdata_admin(onNetworkCallback_getdata_admin,type);


    }OnNetworkCallback_getdata_admin onNetworkCallback_getdata_admin = new OnNetworkCallback_getdata_admin() {
        @Override
        public void onResponse(List<POJO_getdata_admin> getdata_admins) {
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }


            for (int i = 0; i<getdata_admins.size() ;i++){

                namestore.add(getdata_admins.get(i).getName());
                dep_id_list.add(getdata_admins.get(i).getDepId());

            }

            ListViewAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,namestore);
            listView.setAdapter(ListViewAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    editor.putString(Fragment_login.KEY_dep_id,dep_id_list.get(position));
                    editor.commit();


                    Fragment_AF_teacher_missdate missdate = new Fragment_AF_teacher_missdate();
                    replaceFragment(missdate,null);

                }
            });
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
            Toast.makeText(context, "Err "+t.getMessage(), Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

        }
    };
//
//    OnNetworkCallback_trainer onCallbackList = new OnNetworkCallback_trainer() {
//        @Override
//        public void onResponse(List<POJO_trainer> trainer) {
//            if(progressDialog.isShowing()){
//                progressDialog.dismiss();
//            }
//
//
//            for (int i = 0; i<trainer.size() ;i++){
//
//                namestore.add(trainer.get(i).getName());
//                dep_id_list.add(trainer.get(i).getDepId());
//
//            }
//
//            ListViewAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,namestore);
//            listView.setAdapter(ListViewAdapter);
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                    editor.putString(Fragment_login.KEY_dep_id,dep_id_list.get(position));
//                    editor.commit();
//
//                    replaceFragment(new Fragment_Teacher_Trainer(),null);
//
//                }
//            });
//         }
//
//        @Override
//        public void onBodyError(ResponseBody responseBodyError) {
//            Toast.makeText(context, "responseBodyError", Toast.LENGTH_SHORT).show();
//            if(progressDialog.isShowing()){
//                progressDialog.dismiss();
//            }
//        }
//
//        @Override
//        public void onBodyErrorIsNull() {
//            Toast.makeText(context, "res is null", Toast.LENGTH_SHORT).show();
//            if(progressDialog.isShowing()){
//                progressDialog.dismiss();
//            }
//        }
//
//        @Override
//        public void onFailure(Throwable t) {
//            Toast.makeText(context, "Err "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            if(progressDialog.isShowing()){
//                progressDialog.dismiss();
//            }
//        }
//    };


    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.replace(ctn.example.user.dvectn2.R.id.content,fragment).addToBackStack(null).commit();

    }

    private void Logout(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("คำเตือน");
        builder.setMessage("คุณต้องการออกจากระบบ ?");

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