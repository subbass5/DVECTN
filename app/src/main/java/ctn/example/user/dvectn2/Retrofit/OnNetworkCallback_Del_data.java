package ctn.example.user.dvectn2.Retrofit;

import ctn.example.user.dvectn2.Model.POJO_Del;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_Del_data {
    public void onResponse(POJO_Del del);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
