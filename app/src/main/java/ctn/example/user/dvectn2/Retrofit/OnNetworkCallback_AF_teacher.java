package ctn.example.user.dvectn2.Retrofit;

import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_AF_teacher;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_AF_teacher {
    public void onResponse(List<POJO_AF_teacher> getnite);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
