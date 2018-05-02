package ctn.example.user.dvectn2.RecycelViewPack;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ctn.example.user.dvectn2.Fragment.Fragment_AF_teacher_missdate;
import ctn.example.user.dvectn2.Fragment.Fragment_login;
import ctn.example.user.dvectn2.POJO.POJO_getstuemp;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_getstuemp;
import okhttp3.ResponseBody;

/**
 * Created by User on 8/3/2561.
 */

public class Fragment_Teacher_Trainer extends Fragment {

    RecyclerView recyclerView2;
    RecycleViewAdapter2 recycleViewAdapter2;
    List<String> Data_flstr;
    List<String> Data_member_ID;
    List<String> Data_dep_id;
    List<String> Data_state;

    List<String> Data_img;
    List<String> Data_detail;
    List<String> Data_score;
//    List<String> Data_CK;

    Calendar myCalendar;
    TextView tv_date;
    Button btnSelectDate;
    Context context;
    String dep_id = "";
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    int memberId = 0;
    String userType = "";
    String time = "";
    public static final String TAG_TCH1 ="TCH";
    String myFormat = "yyyy-MM-dd"; //In which you need put here

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewtcher = inflater.inflate(ctn.example.user.dvectn2.R.layout.teacher_fusionjob,container,false);
        context = getContext();

        tv_date = viewtcher.findViewById(R.id.tv_datetime);
        tv_date.setText(datenow());

        myCalendar = Calendar.getInstance();
        btnSelectDate = viewtcher.findViewById(R.id.btnSelectDate);
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);
        memberId = sharedPreferences.getInt(Fragment_login.KEY_member_id,0);
        time = sharedPreferences.getString(Fragment_AF_teacher_missdate.KEY_time,null);
        userType = sharedPreferences.getString(Fragment_login.KEY_member_type,null);
        recyclerView2 = viewtcher.findViewById(ctn.example.user.dvectn2.R.id.LV_thnaja_1);

        Data_flstr = new ArrayList<>();
        Data_state = new ArrayList<>();
        Data_member_ID = new ArrayList<>();
        Data_dep_id = new ArrayList<>();

        Data_img = new ArrayList<>();
        Data_detail = new ArrayList<>();
        Data_score = new ArrayList<>();


        recycleViewAdapter2 = new RecycleViewAdapter2(getContext());

        if(!time.equals("no")){
            tv_date.setText(time);
        }

        new NetworkConnectionManager().callSrever_getstuemp(onCallbackList,dep_id,tv_date.getText().toString());

        return viewtcher;

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            Data_flstr = new ArrayList<>();
            Data_state = new ArrayList<>();
            Data_member_ID = new ArrayList<>();
            Data_dep_id = new ArrayList<>();
            Data_img = new ArrayList<>();
            Data_detail = new ArrayList<>();
            Data_score = new ArrayList<>();

            recycleViewAdapter2.clear();
            sendData();
        }

    };

    public  void reLoad(){

        Data_flstr = new ArrayList<>();
        Data_state = new ArrayList<>();
        Data_member_ID = new ArrayList<>();
        Data_dep_id = new ArrayList<>();
        Data_img = new ArrayList<>();
        Data_detail = new ArrayList<>();
        Data_score = new ArrayList<>();
        recycleViewAdapter2.clear();

        new NetworkConnectionManager().callSrever_getstuemp(onCallbackList,dep_id,tv_date.getText().toString());
    }

    private String datenow() {

        DateFormat dateFormat = new SimpleDateFormat(myFormat);
        Date date = new Date();

        return dateFormat.format(date);
    }

    private void sendData() {


        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("th", "TH"));

        tv_date.setText(sdf.format(myCalendar.getTime()));

        new NetworkConnectionManager().callSrever_getstuemp(onCallbackList,dep_id,tv_date.getText().toString());

    }



    OnNetworkCallback_getstuemp onCallbackList = new OnNetworkCallback_getstuemp() {
        @Override
        public void onResponse(List<POJO_getstuemp> getstuemp) {



            for (int i = 0; i< getstuemp.size() ;i++){

                Data_dep_id.add(getstuemp.get(i).getMemberId());
                Data_member_ID.add(getstuemp.get(i).getMemberId());
                Data_flstr.add(getstuemp.get(i).getFirstname()+"\t"+getstuemp.get(i).getLastname());
                Data_detail.add(getstuemp.get(i).getDetail());
                Data_score.add(getstuemp.get(i).getScore());
                Data_img.add(getstuemp.get(i).getImg());
                Data_state.add(getstuemp.get(i).getCheckNited());


//                Toast.makeText(context, "state ="+Data_state, Toast.LENGTH_SHORT).show();
            }

//            Toast.makeText(context, ""+getstuemp.get(0).getFirstname(), Toast.LENGTH_SHORT).show();

//            recycleViewAdapter2 = new RecycleViewAdapter2(getContext());

            if(getstuemp.get(0).getFirstname().equals("null")){

                Toast.makeText(context, "ไม่พบข้อมูลการอนุมัติ", Toast.LENGTH_SHORT).show();

            }else {
                recycleViewAdapter2.Update_teacher_data(Data_flstr,Data_state,Data_img,Data_detail,Data_score,Data_member_ID,userType,tv_date.getText().toString());
                recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView2.setHasFixedSize(true);
                recyclerView2.setAdapter(recycleViewAdapter2);
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
        frgTran.replace(ctn.example.user.dvectn2.R.id.content,fragment).addToBackStack(null).commit();


    }


}



