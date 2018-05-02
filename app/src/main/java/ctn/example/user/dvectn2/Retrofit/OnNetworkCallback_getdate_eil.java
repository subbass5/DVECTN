package ctn.example.user.dvectn2.Retrofit;

import java.util.List;

import ctn.example.user.dvectn2.POJO.POJO_getdate_eil;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_getdate_eil {

    public void onResponse(List<POJO_getdate_eil> getdate_eils);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}