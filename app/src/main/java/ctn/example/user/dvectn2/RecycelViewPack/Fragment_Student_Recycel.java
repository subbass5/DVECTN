package ctn.example.user.dvectn2.RecycelViewPack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ctn.example.user.dvectn2.Fragment.Fragment_bt_pj2;
import ctn.example.user.dvectn2.Fragment.Fragment_login;
import ctn.example.user.dvectn2.Fragment.Student_save;
import ctn.example.user.dvectn2.POJO.POJO_Stu_naja;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_Stu_naja;
import ctn.example.user.dvectn2.R;
import okhttp3.ResponseBody;

/**
 * Created by User on 7/3/2561.
 */

public class Fragment_Student_Recycel extends Fragment {
    RecyclerView recyclerView;
    RecycleViewAdapter1 recycleViewAdapter;
    List<String> Data_St;
    List<String> Data_Url;
    List<String> Data_score;
    List<String> app_id ;
    TextView tv_uou;
    String userType = "";
    String dep_id = "";
    String member_id = "" ;

    int memberId = 0;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    Context context;
    private Boolean isFabOpen = false;
//    private FloatingActionButton fab,fab1,fab2,fab_test;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    public  static  final String TAG_STU = "DENT";
    private Button mButtonDialog;
    FloatingActionButton fab_test,fab,fab1,fab2;
    public Fragment_Student_Recycel() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View viewtt = inflater.inflate(R.layout.student_page, container, false);


        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


//        tv_name = viewtt.findViewById(R.id.tv_name_naja);
//        tv_name.setText(Da);


        context = getContext();

        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        memberId = sharedPreferences.getInt(Fragment_login.KEY_member_id,0);
        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);
        userType = sharedPreferences.getString(Fragment_login.KEY_member_type,null);

         fab_test = viewtt.findViewById(R.id.fab_test);
         fab = viewtt.findViewById(R.id.fab);
         fab1 = viewtt.findViewById(R.id.fab12);
         fab2 = viewtt.findViewById(R.id.fab_data);




        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);


        close_fab();


        fab_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFAB(viewtt);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.fab:
                        Bundle bnq = new Bundle();
                        bnq.putString(TAG_STU, "11587");
//                        ส่วนภายใน Fragment
                        Snackbar.make(view, "โปรดกรอกข้อมูล", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        Student_save student_save = new Student_save();
                        student_save.setArguments(bnq);
                        replaceFragment(student_save, bnq);
                        break;
                }
            }

        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.fab12:

                        Logout();

//                        Fragment_login fragment_login = new Fragment_login();
//                        fragment_login.setArguments(null);
//                        replaceFragment(fragment_login,null);

                        break;
                }
            }

        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.fab_data:
//                        ส่วนภายใน Fragment


                        Fragment_bt_pj2 Fragment_bt_pj2 = new Fragment_bt_pj2();
                        Fragment_bt_pj2.setArguments(null);
                        replaceFragment(Fragment_bt_pj2, null);
                        break;
                }
            }

        });

        fab2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(context, "แบบประเมิน", Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        recyclerView = viewtt.findViewById(R.id.LV_st_1);

        Data_St = new ArrayList<>();
        Data_Url = new ArrayList<>();
        Data_score=  new ArrayList<>();
        app_id = new ArrayList<>();
        new NetworkConnectionManager().callServer_stu_naja(onCallbackList,""+ memberId);




        return viewtt;
    }

    public void close_fab (){
        fab_test.startAnimation(rotate_backward);
        fab.startAnimation(fab_close);
        fab2.startAnimation(fab_close);
        fab.setClickable(false);
        fab2.setClickable(false);
        isFabOpen = false;
    }

    public void open_fab (){
        fab_test.startAnimation(rotate_forward);
        fab.startAnimation(fab_open);
        fab2.startAnimation(fab_open);
        fab.setClickable(true);
        fab2.setClickable(true);
        isFabOpen = true;
        Log.d("Raj","open");
    }

    public void animateFAB(View vieww){

//        FloatingActionButton fab_test = vieww.findViewById(R.id.fab_test);
//        FloatingActionButton fab = vieww.findViewById(R.id.fab);
//        FloatingActionButton fab2 = vieww.findViewById(R.id.fab_data);

        if(isFabOpen){

            close_fab();

        } else {

            open_fab();

        }
    }

    OnNetworkCallback_Stu_naja onCallbackList = new OnNetworkCallback_Stu_naja() {


        @Override
        public void onResponse(List<POJO_Stu_naja> stu_naja) {

            for (int i = 0; i< stu_naja.size() ;i++){
                  Data_St.add(stu_naja.get(i).getAppDetail());
                  Data_Url.add(stu_naja.get(i).getImgurl());
                  app_id.add(stu_naja.get(i).getAppId());


            }


            recycleViewAdapter = new RecycleViewAdapter1(getContext());

            recycleViewAdapter.Update_Data(Data_St,Data_Url,userType,member_id,app_id);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(recycleViewAdapter);


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

