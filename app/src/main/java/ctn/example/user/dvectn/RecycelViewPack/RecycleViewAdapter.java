package ctn.example.user.dvectn.RecycelViewPack;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
/**
 * Created by User on 7/3/2561.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyHoder> {

    Context context0;
    List<String> nData;
//    List<String> nUrl;


    public RecycleViewAdapter(Context context) {

        this.context0 = context;

    }

    public void Update_Data(List<String> nData) {

        this.nData = nData;
//        this.nUrl = nUrl;
//        Toast.makeText(context0, ""+nData.size(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public RecycleViewAdapter.MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View T;
        T = LayoutInflater.from(parent.getContext()).inflate(ctn.example.user.dvectn.R.layout.row_data,
                parent, false);

        return new MyHoder(T, context0);

    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.MyHoder holder, int position) {

        holder.tv_name.setText(nData.get(position));


    }

    @Override
    public int getItemCount() {return nData.size();}


    class MyHoder extends RecyclerView.ViewHolder{

        TextView tv_name;
//        ImageView imgUser;
        Context context;


        public MyHoder(View itemView,Context context) {

            super(itemView);

            tv_name = itemView.findViewById(ctn.example.user.dvectn.R.id.TW_row_st);
//            imgUser = itemView.findViewById(R.id.IV_row_st);
            this.context = context;

        }



    }
}










