package ctn.example.user.dvectn2.Retrofit;


import ctn.example.user.dvectn2.Model.POJO_PJ_P6;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_PJ_P6 {
    public void onResponse(POJO_PJ_P6 assessmentstu6);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
