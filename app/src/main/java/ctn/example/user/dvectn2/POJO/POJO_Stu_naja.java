package ctn.example.user.dvectn2.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJO_Stu_naja {

    @SerializedName("app_detail")
    @Expose
    private String appDetail;
    @SerializedName("imgurl")
    @Expose
    private String imgurl;
    @SerializedName("app_id")
    @Expose
    private String appId;

    public String getAppDetail() {
        return appDetail;
    }

    public void setAppDetail(String appDetail) {
        this.appDetail = appDetail;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}