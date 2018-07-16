package ctn.example.user.dvectn2.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJO_getdate_eil {

    @SerializedName("dep_id")
    @Expose
    private String depId;
    @SerializedName("app_name")
    @Expose
    private String appName;
    @SerializedName("app_date")
    @Expose
    private String appDate;

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

}

