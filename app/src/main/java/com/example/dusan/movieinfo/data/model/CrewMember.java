package com.example.dusan.movieinfo.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CrewMember implements Serializable {

    @SerializedName("name") @Expose private String name;
    @SerializedName("department") @Expose private String department;
    @SerializedName("gender") @Expose private Integer gender;
    @SerializedName("job") @Expose private String job;
    @SerializedName("profile_path") @Expose private String profileUrl;

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getGender() {
        return gender;
    }

    public String getJob() {
        return job;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
