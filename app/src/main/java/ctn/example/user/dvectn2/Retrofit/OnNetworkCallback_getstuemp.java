package ctn.example.user.dvectn2.Retrofit;

import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_getstuemp;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_getstuemp {

    public void onResponse(List<POJO_getstuemp> getstuemp);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
