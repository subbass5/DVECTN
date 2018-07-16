package ctn.example.user.dvectn2.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import ctn.example.user.dvectn2.Model.POJO_login;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.RecycelViewPack.FragmentMainStudent;
import ctn.example.user.dvectn2.RecycelViewPack.FragmentParent;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallbackLoginListener;
import okhttp3.ResponseBody;

import static ctn.example.user.dvectn2.Util.MyFer.KEY_PWD;
import static ctn.example.user.dvectn2.Util.MyFer.KEY_USR;

/**
 * Created by User on 19/2/2561.
 */

public class Fragment_login extends Fragment implements View.OnClickListener {
    EditText et_user,et_pass;
    String str_user,str_pass;
    RadioButton rab_keep;
    public static String BASE_URL = "http://dve2.ctn-phrae.com/api/";
    public static final String MyPer = "myPer";
    public static final String KEY_member_id = "member_id";
    public static final String KEY_member_firstname = "member_firstname";
    public static final String KEY_member_lastname = "member_lastname";
    public static final String KEY_member_email = "member_email";
    public static final String KEY_dep_id = "dep_id";
    public static final String KEY_member_type = "member_type";
    public static final String KEY_SUPERVISION = "spn";
    public static final String KEY_ClassOfTeachernaja = "spn";


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ProgressDialog progressDialog;
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout,container,false);
        ImageView img = view.findViewById(R.id.imgif_naja);

        view.findViewById(R.id.btn_login).setOnClickListener(this);
        context = getContext();
        rab_keep = view.findViewById(R.id.rab_keep);


        et_user = view.findViewById(R.id.et_user);
        et_pass = view.findViewById(R.id.et_pass);


        sharedPreferences = getActivity().getSharedPreferences(MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        hasNetworkConnection();

        img.setImageDrawable(getResources().getDrawable(R.drawable.logo2));

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return view;

    }
    public  void hasNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(getActivity().getApplicationContext(), "No internet !!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (networkInfo.getTypeName().equalsIgnoreCase("WIFI"))
            if (networkInfo.isConnected())
                Toast.makeText(getActivity().getApplicationContext(), "Mobile internet !!", Toast.LENGTH_SHORT).show();
        if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE"))
            if (networkInfo.isConnected())
                Toast.makeText(getActivity().getApplicationContext(), "Wifi internet !!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        str_user = sharedPreferences.getString(KEY_USR,"");
        str_pass = sharedPreferences.getString(KEY_PWD,"");

        if(!str_user.isEmpty() && !str_pass.isEmpty()) {
            showLoading();
            new NetworkConnectionManager().callServerLogin(listener, str_user, str_pass);
        }
    }

    private void login(){

        str_user = et_user.getText().toString().trim();
        str_pass = et_pass.getText().toString().trim();

        if (rab_keep.isChecked()){
            editor.putString(KEY_USR,str_user);
            editor.putString(KEY_PWD,str_pass);
        }


     if (TextUtils.isEmpty(et_user.getText().toString().trim())|| TextUtils.isEmpty(et_pass.getText().toString().trim())){
            et_user.setError("โปรดกรอกให้ถูกต้อง");
            et_pass.setError("โปรดกรอกรหัสผ่านให้ถูกต้อง");

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }else {

                new NetworkConnectionManager().callServerLogin(listener,str_user,str_pass);

        }

    }


    OnNetworkCallbackLoginListener listener = new OnNetworkCallbackLoginListener() {
        @Override
        public void onResponse(POJO_login loginRes) {

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            try {

                    editor.putInt(KEY_member_id, Integer.parseInt(loginRes.getMemberId()));
                    editor.putString(KEY_member_firstname, loginRes.getMemberFirstname());
                    editor.putString(KEY_member_lastname, loginRes.getMemberLastname());
                    editor.putString(KEY_member_email, loginRes.getMemberEmail());
                    editor.putString(KEY_dep_id, loginRes.getDepId());
                    editor.putString(KEY_member_type, loginRes.getMemberType());
                    editor.commit();

                    String Member_Type = loginRes.getMemberType();


                    if (Member_Type.equals("establishment")) {

                        FragmentEstablishment sec = new FragmentEstablishment();
                        replaceFragment(sec, null);

                    } else if (Member_Type.equals("student")) {

                        FragmentMainStudent sec = new FragmentMainStudent();
                        replaceFragment(sec, null);

                    } else if (Member_Type.equals("teacher")) {

                        FragmentTeacher sec = new FragmentTeacher();
                        replaceFragment(sec, null);


                    } else if (Member_Type.equals("admin")) {
                        FragmentAdminTeacher sec = new FragmentAdminTeacher();
                        replaceFragment(sec, null);

                    } else if (Member_Type.equals("parent")) {
                        FragmentParent sec = new FragmentParent();
                        replaceFragment(sec, null);

                    }

            }catch (Exception e){

                Toast.makeText(context, "เข้าสู่ระบบ ล้มเหลว ", Toast.LENGTH_SHORT).show();

            }

            }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {

//            Log.e("onBodyError",""+responseBodyError);

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
        @Override
        public void onBodyErrorIsNull() {
//            Log.e("onBodyErrorIsNull","Data is Null");
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

        }

        @Override
        public void onFailure(Throwable t) {
//          Log.e("onFailure",t.getMessage());
            Toast.makeText(getContext(), "ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }


        }
    };

    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment oldFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());

        //if oldFragment already exits in fragmentManager use it
        if (oldFragment != null) {
            fragment = oldFragment;
        }

        fragmentTransaction.replace(R.id.content, fragment, fragment.getClass().getName());

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.commit();
    }

    private void showLoading(){

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIcon(R.drawable.logo2);
        progressDialog.setTitle("แจ้งเตือน");
        progressDialog.setMessage("กรุณารอสักครู่");
        progressDialog.show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
//                Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
//            showDialog("Hello");
                showLoading();
//                showLoading();
                login();

                break;
        }
    }

}
