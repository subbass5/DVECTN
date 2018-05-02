package ctn.example.user.dvectn2.RecycelViewPack;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ctn.example.user.dvectn2.POJO.POJO_Checkdaily;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_Checkdaily_D1;
import okhttp3.ResponseBody;

/**
 * Created by User on 9/3/2561.
 */

public class   RecycleViewAdapter3 extends RecyclerView.Adapter<RecycleViewAdapter3.MyHoder> {
    ProgressDialog progressDialog;
    Context context;
    List<String> namelist;
    List<String> ltnamelist;
    List<String> numlist;
    List<String> score;
    ArrayAdapter adp2 ;
    View view;
    String datenow;

    public RecycleViewAdapter3(Context context) {

        this.context = context;
    }

    public void DataStudent(List<String> namelist, List<String> ltnamelist,  List<String> numlist,List<String> score , ArrayAdapter adp2 ,String datenow) {

        this.namelist = namelist;
        this.numlist = numlist;
        this.ltnamelist = ltnamelist;
        this.score = score;
        this.adp2 = adp2 ;
        this.datenow = datenow;
    }

    @Override
    public RecycleViewAdapter3.MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
        View U;
        U = LayoutInflater.from(parent.getContext()).inflate(ctn.example.user.dvectn2.R.layout.row_student, parent,
                false);
        return new MyHoder(U, context);
    }

    @Override
    public void onBindViewHolder(final RecycleViewAdapter3.MyHoder holder, int position) {


        holder.tx_1.setText(namelist.get(position));
        holder.tx_2.setText(ltnamelist.get(position));
        holder.tx_3.setText(""+numlist.get(position));
        holder.tx_5.setAdapter(adp2);
//        Log.d("Data score:"+position,""+ score.get(position));
        if(score.get(position) != null)
        {

//            Toast.makeText(context, ""+score.get(position), Toast.LENGTH_SHORT).show();
                    int spinnerPosition = adp2.getPosition(getStatusStr(Integer.parseInt(score.get(position))));
                    holder.tx_5.setSelection(spinnerPosition);
        }

                    holder.tx_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(!holder.tx_5.getSelectedItem().toString().equals("-") )
                            {


                                new NetworkConnectionManager().callServer_Checkdaily(onCallbackList
                                                  ,holder.tx_1.getText().toString()
                                                  ,getStatus(holder.tx_5.getSelectedItem().toString())
                                                  , datenow);


                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

    }



    OnNetworkCallback_Checkdaily_D1 onCallbackList = new OnNetworkCallback_Checkdaily_D1() {
        @Override
        public void onResponse(POJO_Checkdaily getstu) {

//            Toast.makeText(context, "จัดการข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Toast.makeText(context, "responseBodyError", Toast.LENGTH_SHORT).show();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }

        @Override
        public void onBodyErrorIsNull() {

            Toast.makeText(context, "res is null", Toast.LENGTH_SHORT).show();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }

        @Override
        public void onFailure(Throwable t) {

//            Toast.makeText(context, "Err "+t.getMessage(), Toast.LENGTH_SHORT).show();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    };


    public void clear() {

//        final int size = namelist.size();
//        namelist.clear();
//        notifyItemRangeRemoved(0, size);
        final int size = namelist.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                namelist.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }

    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    class  MyHoder extends RecyclerView.ViewHolder{

        TextView tx_1;
        TextView tx_2;
        TextView tx_3;

        Spinner tx_5;
        Context context;

        public MyHoder (View itemView, final Context context){

            super(itemView);
            tx_1 =itemView.findViewById(ctn.example.user.dvectn2.R.id.tw_namlist);
            tx_2 =itemView.findViewById(ctn.example.user.dvectn2.R.id.cccttn);
            tx_3 =itemView.findViewById(ctn.example.user.dvectn2.R.id.tw_numberlist);
            tx_5 =itemView.findViewById(ctn.example.user.dvectn2.R.id.spn_checklist);
            this.context = context;
        }



    }

    private int getStatus(String input){

        if(input.equals("ขาด")){
            return 0;
        }else if (input.equals("ลา")){
            return 1;
        }else if (input.equals("มา")){
            return 2;
        }else if (input.equals("มาสาย")){
            return 3;
        }else {
            return -1;
        }
    }
    private String getStatusStr(int input){

        if(input == 0 ){
            return "ขาด";
        }else if (input== 1){
            return "ลา";
        }else if (input == 2 ){
            return "มา";
        }else if (input == 3){
            return "มาสาย";
        }else {
            return "-";
        }
    }

}
