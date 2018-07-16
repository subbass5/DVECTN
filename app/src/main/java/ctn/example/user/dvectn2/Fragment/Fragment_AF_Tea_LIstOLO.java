package ctn.example.user.dvectn2.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_getdata_admin;
import ctn.example.user.dvectn2.R;
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
    String TAG = "<Fragment_AF_Tea_LIstOLO>";
    FragmentManager fragmentManager;

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
        fragmentManager = getActivity().getSupportFragmentManager();

        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);
        type = sharedPreferences.getString(Fragment_login.KEY_ClassOfTeachernaja,null);

        FloatingActionButton fab_back = v4.findViewById(R.id.fab_back);
        fab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.popBackStack();
            }
        });

        getStd();

        return v4;
    }

    private  void getStd(){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading......");
        progressDialog.show();

//        new NetworkConnectionManager().callSrever_trainer(onCallbackList);
        new NetworkConnectionManager().callServer_getdata_admin(onNetworkCallback_getdata_admin,type);


    }
    OnNetworkCallback_getdata_admin onNetworkCallback_getdata_admin = new OnNetworkCallback_getdata_admin() {
        @Override
        public void onResponse(List<POJO_getdata_admin> getdata_admins) {

                if(progressDialog.isShowing())
                         progressDialog.dismiss();

        try {



            for (int i = 0; i<getdata_admins.size() ;i++){

                namestore.add(getdata_admins.get(i).getName());
                dep_id_list.add(getdata_admins.get(i).getDepId());

            }
          if (namestore.get(0) != null)
          {

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


            }else{
                                Toast.makeText(context, "ไม่พบข้อมูล", Toast.LENGTH_SHORT).show();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.popBackStack();
}

        }catch(Exception e){

            Log.e(TAG,e.getMessage());



        }

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

    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.replace(ctn.example.user.dvectn2.R.id.content,fragment).addToBackStack(null).commit();

    }


}