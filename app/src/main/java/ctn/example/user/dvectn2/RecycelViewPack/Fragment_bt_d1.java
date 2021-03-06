package ctn.example.user.dvectn2.RecycelViewPack;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ctn.example.user.dvectn2.Fragment.Fragment_login;
import ctn.example.user.dvectn2.Model.POJOGetDaily;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_GetStdDaily;
import okhttp3.ResponseBody;

/**
 * Created by User on 9/3/2561.
 */

public class Fragment_bt_d1 extends Fragment {
    RecyclerView recyclerView5;
    RecycleViewAdapter3 recycleViewAdapter5;
    List<String> Data_name;
    List<String> Data_ltname;
    List<String> Data_chan;
    List<String> Data_num;
    List<String>  Data_score;
    List<Integer> Data_member_id;

    String CheckList[] = {"-","ขาด", "ลา", "มา", "มาสาย"};
    String dep_id = "";
    int memberId = 0;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    Context context;
    Spinner spn1;
    Calendar myCalendar;
    TextView tv_date;
    Button btnSelectDate;
    ArrayAdapter adp2;
    public static final String TAG_HEW = "HEW";
     String myFormat = "yyyy-MM-dd"; //In which you need put here

    List<String> nameStd;
    List<String> idStd;
    int resSize_check = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.av_bt_d, container, false);

        tv_date = view1.findViewById(R.id.tv_datetime2);
        tv_date.setText(datenow(myFormat));
        myCalendar = Calendar.getInstance();
        btnSelectDate = view1.findViewById(R.id.btnSelectDate2);
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //clear recycleview

                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


                  }
        });

//        spn1 = view1.findViewById(R.id.spn_checklist);
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,CheckList);
//        spn1.setAdapter(adapter);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        context = getContext();

        resSize_check = 0;

        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        dep_id = sharedPreferences.getString(Fragment_login.KEY_dep_id,null);

//        Toast.makeText(context, ""+dep_id, Toast.LENGTH_SHORT).show();

        memberId = sharedPreferences.getInt(Fragment_login.KEY_member_id,0);

        Data_name = new ArrayList<>();
        Data_ltname = new ArrayList<>();
        Data_chan = new ArrayList<>();
        Data_num = new ArrayList<>();
        Data_score = new ArrayList<>();
        Data_member_id = new ArrayList<>();

        recyclerView5 = view1.findViewById(R.id.review_d);
//        recycleViewAdapter5 = new RecycleViewAdapter3(getContext());
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading......");
        progressDialog.show();

        adp2 =  new ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,CheckList);

        new NetworkConnectionManager().getDataStdDaily(onCallbackList,dep_id,tv_date.toString());

        return view1;

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            //clear recycleview
            Data_name = new ArrayList<>();
            Data_ltname = new ArrayList<>();
            Data_chan = new ArrayList<>();
            Data_num = new ArrayList<>();
            Data_score = new ArrayList<>();
            Data_member_id = new ArrayList<>();

            recycleViewAdapter5.clear();
            sendData();
        }

    };

    public static String datenow(String Format) {

        DateFormat dateFormat = new SimpleDateFormat(Format);
        Date date = new Date();

        return dateFormat.format(date);
    }

    private void sendData() {

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("th", "TH"));

        tv_date.setText(sdf.format(myCalendar.getTime()));
//       Log.d("Date tv",tv_date.toString());

        new NetworkConnectionManager().getDataStdDaily(onCallbackList,dep_id,tv_date.getText().toString());


    }


    OnNetworkCallback_GetStdDaily onCallbackList = new OnNetworkCallback_GetStdDaily() {
        @Override
        public void onResponse(List<POJOGetDaily> getstu) {

                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

            resSize_check = Data_ltname.size();


//            Toast.makeText(context, "Response Size  = "+Data_ltname.size(), Toast.LENGTH_SHORT).show();

                for (int i = 0; i< getstu.size() ;i++){

                    Data_name.add(getstu.get(i).getFirstname());
                    Data_ltname.add(getstu.get(i).getLastnamename());
                    Data_num.add(getstu.get(i).getMemberCode());
                    Data_member_id.add(Integer.parseInt(getstu.get(i).getMemberId()));   // get member id
                    Data_score.add(getstu.get(i).getScore());
//                    Log.d("Data res","Data = "+getstu.get(i).getFirstname()+" \t"
//                                                +getstu.get(i).getMemberCode()+"\t"
//                                                +getstu.get(i).getMemberId()+"\t"
//                                                +getstu.get(i).getScore());

                }

                        recycleViewAdapter5 = new RecycleViewAdapter3(getContext());
                        recycleViewAdapter5.DataStudent(Data_name,
                                                        Data_ltname,
                                                        Data_num,
                                                        Data_score,
                                                        adp2,tv_date.getText().toString());

                        recyclerView5.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView5.setHasFixedSize(true);
                        recyclerView5.setAdapter(recycleViewAdapter5);

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
        frgTran.replace(ctn.example.user.dvectn2.R.id.content, fragment).addToBackStack(null).commit();


    }




}