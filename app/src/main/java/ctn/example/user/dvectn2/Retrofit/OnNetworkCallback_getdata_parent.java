package ctn.example.user.dvectn2.Retrofit;

import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_getdata_parent_member;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_getdata_parent {

    public void onResponse(List<POJO_getdata_parent_member> getdataprentmember);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);

}
