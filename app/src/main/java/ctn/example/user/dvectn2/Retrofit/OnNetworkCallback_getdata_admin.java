package ctn.example.user.dvectn2.Retrofit;

import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_getdata_admin;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_getdata_admin {

    public void onResponse(List<POJO_getdata_admin> getdata_admin);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);

}
