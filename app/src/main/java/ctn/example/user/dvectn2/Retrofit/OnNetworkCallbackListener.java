package ctn.example.user.dvectn2.Retrofit;

import ctn.example.user.dvectn2.POJO.ResPOJO;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by kritsanrongkaew on 19/3/2018 AD.
 */

public interface OnNetworkCallbackListener {

    public void onResponse(ResPOJO user, Retrofit retrofit);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);


}
