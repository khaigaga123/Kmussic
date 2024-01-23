package com.Model;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUser implements Serializable {

@SerializedName("IdUser")
@Expose
private String idUser;
@SerializedName("TaiKhoan")
@Expose
private String taiKhoan;
@SerializedName("MatKhau")
@Expose
private String matKhau;
@SerializedName("Idbaihatdathich")
@Expose
private String idbaihatdathich;

public String getIdUser() {
return idUser;
}

public void setIdUser(String idUser) {
this.idUser = idUser;
}

public String getTaiKhoan() {
return taiKhoan;
}

public void setTaiKhoan(String taiKhoan) {
this.taiKhoan = taiKhoan;
}

public String getMatKhau() {
return matKhau;
}

public void setMatKhau(String matKhau) {
this.matKhau = matKhau;
}
public String getIdbaihatdathich() {
        return idbaihatdathich;}
    public void setIdbaihatdathich(String idbaihatdathich) {
    this.idbaihatdathich = idbaihatdathich;}

}