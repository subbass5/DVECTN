package ctn.example.user.dvectn2.Retrofit;

import java.util.List;

import ctn.example.user.dvectn2.POJO.POJOGetDaily;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_GetStdDaily {
    public void onResponse(List<POJOGetDaily> Checkdaily);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
