package ctn.example.user.dvectn2.RecycelViewPack;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Dev on 3/30/2018.
 */
public interface OnClickRecycleView{

    void onClick(View view, int position, boolean isLongClick, MotionEvent motionEvent);
    void onLongClick (View view, int position, boolean isLongClick, MotionEvent motionEvent);

}