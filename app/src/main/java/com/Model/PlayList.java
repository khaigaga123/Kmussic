package com.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable {

@SerializedName("IdPlayList")
@Expose
private String idPlayList;
@SerializedName("Ten")
@Expose
private String ten;
@SerializedName("HinhPlayList")
@Expose
private Object hinhPlayList;
@SerializedName("Icon")
@Expose
private String icon;

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

public Object getHinhPlayList() {
return hinhPlayList;
}

public void setHinhPlayList(Object hinhPlayList) {
this.hinhPlayList = hinhPlayList;
}

public String getIcon() {
return icon;
}

public void setIcon(String icon) {
this.icon = icon;
}

}