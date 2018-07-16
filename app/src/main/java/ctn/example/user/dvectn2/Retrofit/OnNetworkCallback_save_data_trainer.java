package ctn.example.user.dvectn2.Retrofit;

import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_save_data_trainer;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_save_data_trainer {
    public void onResponse(List<POJO_save_data_trainer> save_data_trainers);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}