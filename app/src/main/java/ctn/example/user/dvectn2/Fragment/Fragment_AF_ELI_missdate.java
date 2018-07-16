package ctn.example.user.dvectn2.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_getdate_eil;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.RecycelViewPack.Fragment_bt_ag;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_getdate_eil;
import okhttp3.ResponseBody;

public class Fragment_AF_ELI_missdate extends Fragment {
    ArrayAdapter ListViewAdapter;
    ListView listView;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    Context context;
    List<String> list_date;
    String dep_id = "";
    public static final String KEY_timeing = "time";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.af_date_eli,container,false);
        listView = view.findViewById(R.id.list_af_th_stu1);


        context = getContext();

        listView.setAdapter(ListViewAdapter);
        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,"");

        list_date = new ArrayList<>();

        String time = sharedPreferences.getString(KEY_timeing,"");
        if(time.equals("no")){

            FragmentManager fragment = getActivity().getSupportFragmentManager();
            fragment.popBackStack();

//
//            Fragment_AF_Tea_LIstOLO faggot = new Fragment_AF_Tea_LIstOLO();
//            replaceFragment(faggot, null);

        }else
        new NetworkConnectionManager().callServer_getdate_eil(onNetworkCallback_getdate_eil,dep_id);

        return view;
    }

    OnNetworkCallback_getdate_eil onNetworkCallback_getdate_eil = new OnNetworkCallback_getdate_eil() {
        @Override
        public void onResponse(List<POJO_getdate_eil> getdate_eils) {

            try {


                if(getdate_eils.get(0).getAppDate().equals("null") ){

                    Toast.makeText(context, "ไม่พบข้อมูลที่รอการยืนยัน", Toast.LENGTH_SHORT).show();

                    editor.putString(KEY_timeing,"no");
                    editor.commit();

                    FragmentEstablishment faggotkub = new FragmentEstablishment();
                    replaceFragment(faggotkub, null);


                }else {
                    for (int i = 0; i < getdate_eils.size(); i++) {

                        list_date.add(getdate_eils.get(i).getAppDate());

                    }
                    ListViewAdapter = new ArrayAdapter(getActivity().getApplicationContext(),

                            android.R.layout.simple_list_item_1, list_date) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            TextView textView = view.findViewById(android.R.id.text1);

                            /*YOUR CHOICE OF COLOR*/
                            textView.setTextColor(Color.GRAY);

                            return view;
                        }


                    };

                    listView.setAdapter(ListViewAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //                    editor.putString(Fragment_login.KEY_dep_id,list_date.get(position));
                            editor.putString(KEY_timeing,list_date.get(position));
                            editor.commit();

                            Fragment_bt_ag faggot = new Fragment_bt_ag();
                            replaceFragment(faggot, null);


                        }
                    });
                }

                Log.d("DATA RES getdate_admin","size = "+getdate_eils.size()
                        +" data index 0 = "+getdate_eils.get(0).getAppDate());

            }catch (Exception e){
                Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }





        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Toast.makeText(context, "responseBodyError", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBodyErrorIsNull() {
            Toast.makeText(context, "res is null", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(context, "Err "+t.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };

    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        frgTran.replace(ctn.example.user.dvectn2.R.id.content,fragment).addToBackStack(null).commit();


    }
}
