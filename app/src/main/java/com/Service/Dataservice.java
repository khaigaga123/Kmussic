package com.Service;

import com.Adapter.BaiHatHotAdapter;
import com.Model.Album;
import com.Model.BatHatHot;
import com.Model.ChuDe;
import com.Model.Chudevatheloai;
import com.Model.DataUser;
import com.Model.PlayList;
import com.Model.QuangCao;
import com.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBaner();
    @GET("playlistcurrenforday.php")
    Call<List<PlayList>> GetPlayListCurrentDay();
    @GET("chudevatheloaitrongnagy.php")
    Call<Chudevatheloai> GetChudevaTheloai();
    @GET("ambumhot.php")
    Call<List<Album>> GetAlbumHot();
    @GET("baihatduocyeuthich.php")
    Call<List<BatHatHot>> GetBaiHatHot();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BatHatHot>> Getdanhsachbaihattheoquangcao(@Field("idquangcao")String idquangcao);
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BatHatHot>> Getdanhsachbaihattheoplaylist(@Field("idplaylist")String idplaylist);
    @GET("danhsachplaylist.php")
    Call<List<PlayList>> Getdanhsachplaylist();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BatHatHot>> Getdanhsachbaihattheoptheloai(@Field("idtheloai")String idtheloai);
    @GET("tatcachude.php")
    Call<List<ChuDe>> Getdanhsachchude();
    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> Getdanhsachtheloaitheochude(@Field("idchude")String idchude);
    @GET("tatcaalbum.php")
    Call<List<Album>> Getdanhsachalbum();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BatHatHot>> Getdanhsachbaihattheoalbum(@Field("idalbum")String idalbum);
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BatHatHot>> Getdanhsachbaihatdayeuthich(@Field("iduser")String iduser);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich")String luotthich,@Field("idbaihat")String idbaihat);
    @FormUrlEncoded
    @POST("giamluotthich.php")
    Call<String> GiamLuotThich(@Field("luotthich")String luotthich,@Field("idbaihat")String idbaihat);

    @FormUrlEncoded
    @POST("sreachbaihat.php")
    Call<List<BatHatHot>> GetSreachBaiHat(@Field("tukhoa")String tukhoa);
    @GET("datauser.php")
    Call<List<DataUser>> GetDataUser();
    @FormUrlEncoded
    @POST("themtkmk.php") // Đặt đúng đường dẫn của API đăng ký tài khoản
    Call<String> RegisterUser(
            @Field("taiKhoan") String taiKhoan,
            @Field("matKhau") String matKhau);
    @FormUrlEncoded
    @POST("thembaihatyeuthich.php")
    Call<String> Updatebaihatyeuthich(@Field("iduser")String iduser,@Field("idbaihat")String idbaihat);
    @FormUrlEncoded
    @POST("xoabaihatyeuthich.php")
    Call<String> Xoabaihatyeuthich(@Field("iduser")String iduser,@Field("idbaihat")String idbaihat);
}
