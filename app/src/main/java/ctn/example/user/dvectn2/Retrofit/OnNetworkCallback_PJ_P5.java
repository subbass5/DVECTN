package ctn.example.user.dvectn2.Retrofit;


import ctn.example.user.dvectn2.POJO.POJO_PJ_P5;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_PJ_P5 {
    public void onResponse(POJO_PJ_P5 assessmentstu5);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
