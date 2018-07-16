package ctn.example.user.dvectn2.Retrofit;

import ctn.example.user.dvectn2.Model.POJO_DD_P3;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_DD_P3 {

    public void onResponse(POJO_DD_P3 assessmentstu3);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
