package ctn.example.user.dvectn2.RecycelViewPack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ctn.example.user.dvectn2.POJO.POJO_Del;
import ctn.example.user.dvectn2.R;
import ctn.example.user.dvectn2.Retrofit.NetworkConnectionManager;
import ctn.example.user.dvectn2.Retrofit.OnNetworkCallback_Del_data;
import okhttp3.ResponseBody;

/**
 * Created by User on 7/3/2561.
 */

public class RecycleViewAdapter1 extends RecyclerView.Adapter<RecycleViewAdapter1.MyHoder> {

    Context context0;
    List<String> nData;
    List<String> nUrl;
    List<String> app_id;
    List<String> Score;
    String type = "";
    String member_id = "";
    String detail = "";
    String score = "";

    AlertDialog dialog;

    public RecycleViewAdapter1(Context context) {

        this.context0 = context;

    }

    public void Update_Data(List<String> nData,    List<String> nUrl , String type , String member_id , List<String> app_id) {

        this.nData = nData;
        this.nUrl = nUrl;
        this.type = type;
        this.member_id = member_id;
        this.app_id = app_id;
//        Toast.makeText(context0, ""+nData.size(), Toast.LENGTH_SHORT).show();
    }

    public  void  Load_data (List<String> nData , List<String> nUrl ,String type , String member_id ,String score){
        this.nData = nData;
        this.nUrl = nUrl;
        this.type = type;
        this.member_id = member_id;
        this.score = score;
    }


    @Override
    public RecycleViewAdapter1.MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View T;
        T = LayoutInflater.from(parent.getContext()).inflate(ctn.example.user.dvectn2.R.layout.row_data2,
                parent, false);

        return new MyHoder(T, context0);

    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter1.MyHoder holder, int position) {

        holder.tv_name.setText(nData.get(position));
        holder.setIMG(nUrl.get(position));
        holder.setOnClickRecycleView(new OnClickRecycleView() {
            @Override
            public void onClick(View view, int position, boolean isLongClick, MotionEvent motionEvent) {

//                Toast.makeText(context0, "onclick", Toast.LENGTH_SHORT).show();



                    showCustomDialog1(context0,nData.get(position),nUrl.get(position),score,type);
//

            }

            @Override
            public void onLongClick(View view, int position, boolean isLongClick, MotionEvent motionEvent) {
//                Toast.makeText(context0, "onlongclick", Toast.LENGTH_SHORT).show();

                if (type.equals("student")) {
                    showCustomDialog(context0,position);
                }

            }


        });

//        holder.setOnClickRecycleView(new OnClickRecycleView() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick, MotionEvent motionEvent) {
//                showCustomDialog1(context0,nData.get(position),nUrl.get(position),score,type);
//            }
//        });



    }
    public void removeItem(int position) {
        nData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {return nData.size();}

    class MyHoder extends RecyclerView.ViewHolder implements  View.OnClickListener
            , View.OnLongClickListener, View.OnTouchListener {

        private OnClickRecycleView onClickRecycleView;
        TextView tv_name;
        ImageView imgUser;
        Context context;

        public MyHoder(View itemView,Context context) {

            super(itemView);

            tv_name = itemView.findViewById(ctn.example.user.dvectn2.R.id.TW_row_st2);
            imgUser = itemView.findViewById(ctn.example.user.dvectn2.R.id.IV_row_st2);
            this.context = context;


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        // ทำหน้าไดอาลอค ขึ้นลบข้อมูล


        public void setOnClickRecycleView(OnClickRecycleView onClickRecycleView){
            this.onClickRecycleView =  onClickRecycleView;

        }

        @Override
        public void onClick(View view) {
            onClickRecycleView.onClick(view, getAdapterPosition(), false, null);


        }

        @Override
        public boolean onLongClick(View view) {
            onClickRecycleView.onLongClick(view, getAdapterPosition(), false, null);


            return true;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            onClickRecycleView.onClick(view, getAdapterPosition(), false, null);
            return true;
        }

        public void setIMG(String url){

            if(!url.isEmpty())
            Picasso.with(context).load(url).into(imgUser);
            else
                imgUser.setImageDrawable(context.getDrawable(ctn.example.user.dvectn2.R.drawable.logo2));

            }
        }

    public void showCustomDialog1(Context context, String detail, String url,  String score,String type) {




//        Toast.makeText(context, "member_id = "+member_id+" detail = "+detail+" url = "+url +" score ="+score, Toast.LENGTH_SHORT).show();
        Log.e("data show","member_id="+member_id+" detail = "+detail+" url="+url+" score "+score);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View handleView = layoutInflater.inflate(R.layout.custom_layout_dialog, null);
        TextView tv_detail = handleView.findViewById(R.id.tv_detail);
        TextView tv_lbl_score = handleView.findViewById(R.id.tv_lbl_score);
        ImageView imageView = handleView.findViewById(R.id.img);


//        builder.requestWindowFeature(Win dow.FEATURE_NO_TITLE);

        final Spinner spinner = handleView.findViewById(R.id.spScore);

//        Toast.makeText(context, ""+score, Toast.LENGTH_SHORT).show();

        if (type.equals("student") || type.equals("parent")) {

            spinner.setVisibility(View.GONE);
//            tv_lbl_score.setVisibility(View.GONE);
            tv_lbl_score.setText("คะแนน = "+score);
            if (score.isEmpty()){
                tv_lbl_score.setText("คะแนน = ยังไม่ได้ตรวจสอบ");
            }
            tv_detail.setText(detail);
        } else {

            spinner.setVisibility(View.VISIBLE);

        }
        //D/Member Type: admin
        //D/Member Type: establishment


        //set detail



        try {

            // set image from url
            if (!url.isEmpty())
                Picasso.with(context).load(url).into(imageView);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(context.getDrawable(R.drawable.logo2));
            }

        } catch (Exception e) {

        }


        builder.setView(handleView);
        builder.show();


    }

    public void showCustomDialog(final Context context , final int po) {



        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View handleView = layoutInflater.inflate(R.layout.custom_dialog_stu, null);
        Button button_yes = handleView.findViewById(R.id.btn_yes);



        builder.setView(handleView);
        button_yes.setOnClickListener(new View.OnClickListener() {

            OnNetworkCallback_Del_data onNetworkCallback_del_data = new OnNetworkCallback_Del_data() {
                @Override
                public void onResponse(POJO_Del del) {

                    removeItem(po);

                    dialog.dismiss();
//                    if (){
                        Toast.makeText(context, "ลบข้อมูลสำเร็จ โปรดออกแล้วเข้าใหม่", Toast.LENGTH_SHORT).show();
//                    }


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

            @Override
            public void onClick(View v) {

                new NetworkConnectionManager().callServer_del(onNetworkCallback_del_data,app_id.get(po).toString());

            }
        });



        Button button_no = handleView.findViewById(R.id.btn_no);
        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 dialog.dismiss();

            }
        });


       dialog =  builder.show();
    }

}










