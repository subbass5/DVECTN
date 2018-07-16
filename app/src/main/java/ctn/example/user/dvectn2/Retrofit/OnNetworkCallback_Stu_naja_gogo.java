package ctn.example.user.dvectn2.Retrofit;


import java.util.List;

import ctn.example.user.dvectn2.Model.POJO_Stu_naja_gogo;
import okhttp3.ResponseBody;

public interface OnNetworkCallback_Stu_naja_gogo {
    public void onResponse(List<POJO_Stu_naja_gogo> stu_naja_gogo);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}

