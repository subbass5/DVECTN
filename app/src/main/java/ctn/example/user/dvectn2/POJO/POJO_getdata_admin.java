package ctn.example.user.dvectn2.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POJO_getdata_admin {

    @SerializedName("dep_id")
    @Expose
    private String depId;
    @SerializedName("name")
    @Expose
    private String name;

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}