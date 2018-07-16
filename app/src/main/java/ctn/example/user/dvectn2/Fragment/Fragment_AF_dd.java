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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_getstu;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallBackGetStd;
import okhttp3.ResponseBody;

/**
 * Created by Praipran on 3/21/2018.
 */

public class Fragment_AF_dd extends Fragment {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String dep_id = "";
    Context context;
    ListView listView;
    public static final String TAG_OHNODD = "OHNODD";
    ArrayAdapter ListViewAdapter;
    ProgressDialog progressDialog;
    List<String> nameStd;
    List<String> idStd;
    public static final String KEY_STD_ID = "id_std_res";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v4 = inflater.inflate(R.layout.af_dd_list, container, false);



        listView = v4.findViewById(R.id.list_af_dd);
        nameStd = new ArrayList<>();
        idStd = new ArrayList<>();

        context = getContext();

        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);

        getStd();

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return v4;
    }

    private  void getStd(){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading......");
        progressDialog.show();

        new NetworkConnectionManager().getStudentName(onCallbackList,dep_id);

    }

    OnNetworkCallBackGetStd onCallbackList = new OnNetworkCallBackGetStd() {
        @Override
        public void onResponse(List<POJO_getstu> getstu) {

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            for (int i = 0; i<getstu.size() ;i++){

                idStd.add(getstu.get(i).getMemberId());
                nameStd.add(getstu.get(i).getFirstname()+" "+getstu.get(i).getLastnamename());

            }

            ListViewAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,nameStd);
            listView.setAdapter(ListViewAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    editor.putString(KEY_STD_ID,idStd.get(position));
                    editor.commit();



                    replaceFragment(new Fragment_bt_dd() ,null);


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
        frgTran.replace(R.id.content, fragment).addToBackStack(null).commit();
    }
}
