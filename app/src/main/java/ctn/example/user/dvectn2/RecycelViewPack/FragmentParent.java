package ctn.example.user.dvectn2.RecycelViewPack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ctn.example.user.dvectn2.Fragment.Fragment_login;
import ctn.example.user.dvectn2.Model.POJO_getdata_parent_member;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_getdata_parent;
import okhttp3.ResponseBody;

public class FragmentParent extends Fragment {
    Context context;
    RecyclerView recyclerView;
    ListView listView;
    RecycleViewAdapter1 recycleViewAdapter;
    TextView tv_name_son;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    List<String> Data_St;
    List<String> Data_Url;
    String userType = "";
    String dep_id = "";
//    List<String> score;
    String score;
    String member_id = "";
    int memberId = -0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parent_layout,container,false);
//        listView =(ListView) view.findViewById(ctn.example.user.dvectn2.R.id.LV_st_1);
        tv_name_son = view.findViewById(R.id.tv_name_naja1);
//        tv_name_son.setText();

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        context = getContext();

        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        memberId = sharedPreferences.getInt(Fragment_login.KEY_member_id,0);
        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);
        userType = sharedPreferences.getString(Fragment_login.KEY_member_type,null);


        FloatingActionButton fab1 = view.findViewById(R.id.fab123472);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.fab123472:

                        Logout();



                        break;
                }
            }

        });

        recyclerView = view.findViewById(R.id.LV_pr);

        Data_St = new ArrayList<>();
        Data_Url = new ArrayList<>();


        new NetworkConnectionManager().callServer_getdata_parent(onNetworkCallback_getdata_parent,memberId);

        return view;
    }


    OnNetworkCallback_getdata_parent onNetworkCallback_getdata_parent = new OnNetworkCallback_getdata_parent() {
        @Override
        public void onResponse(List<POJO_getdata_parent_member> getdataprentmember) {
            for (int i = 0; i< getdataprentmember.size() ;i++){
                    Data_St.add(getdataprentmember.get(i).getDetail());
                    Data_Url.add(getdataprentmember.get(i).getImg());
                    score = getdataprentmember.get(i).getScore();



                }



                recycleViewAdapter = new RecycleViewAdapter1(getContext());

                recycleViewAdapter.Load_data(Data_St,Data_Url,userType,""+memberId,score);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(recycleViewAdapter);


        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {

        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };



//    OnNetworkCallback_getdata_parent onNetworkCallbackGetdataParent = new OnNetworkCallback_getdata_parent() {
//        @Override
//        public void onResponse(List<POJO_getdata_parent_member> getdataprentmember) {
//
//            for (int i = 0; i< getdataprentmember.size() ;i++){
//                Data_St.add(getdataprentmember.get(i).getDetail());
//                Data_Url.add(getdataprentmember.get(i).getImg());
//
//            }
//
//
//
//            recycleViewAdapter = new RecycleViewAdapter1(getContext());
//
//            recycleViewAdapter.Update_Data(Data_St,Data_Url,userType,""+member_id);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setAdapter(recycleViewAdapter);
//
//        }
//
//
//
//        @Override
//        public void onBodyError(ResponseBody responseBodyError) {
//            Toast.makeText(context, "responseBodyError", Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override
//        public void onBodyErrorIsNull() {
//            Toast.makeText(context, "res is null", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onFailure(Throwable t) {
//            Toast.makeText(context, "Err "+t.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    };




    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
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
