package ctn.example.user.dvectn2.Retrofit;

import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_trainer;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_trainer {

    public void onResponse(List<POJO_trainer> trainer);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
