package com.mobileapp.ApiClasses;

import com.mobileapp.POJO.Auth_Model;
import com.mobileapp.POJO.Auth_del;
import com.mobileapp.POJO.BillingModal;
import com.mobileapp.POJO.CreateAuthorized_Model;
import com.mobileapp.POJO.CreateTicketPOJO;
import com.mobileapp.POJO.DeleteDomainPojo;
import com.mobileapp.POJO.Domain;
import com.mobileapp.POJO.LoginPojo;
import com.mobileapp.POJO.PowerPojo;
import com.mobileapp.POJO.Profile_Model;
import com.mobileapp.POJO.ReplyPOJO;
import com.mobileapp.POJO.TicketListPojo;
import com.mobileapp.POJO.Ticket_Specification;
import com.mobileapp.POJO.UpdateProfile_Model;
import com.mobileapp.POJO.Update_account;
import com.mobileapp.POJO.AttachmentOnly;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("account/login")
    Call<LoginPojo> loginData(@Field("username") String Username, @Field("password") String Password);

    //for Create Account
    @FormUrlEncoded
    @POST("account/contact")
    Call<CreateAuthorized_Model> createNew(@Header("X-API-KEY") String BearerToken, @Field("firstname") String firstname, @Field("lastname") String lastname, @Field("company") String company, @Field("email") String email, @Field("phone") String phone, @Field("zipcode") String zipcode ,@Field("password") String password, @Field("address1") String address1, @Field("address2") String address2, @Field("state") String state, @Field("country") String country, @Field("city") String city);

    //    @FormUrlEncoded
    @GET("support/ticket")
    Call<TicketListPojo> ticketList(@Header("X-API-KEY") String BearerToken);

    @GET("device/list")
    Call<ResponseBody> DeviveList(@Header("X-API-KEY") String BearerToken);


    @GET("device/power/{service}")
    Call<PowerPojo> getPowerStatus(@Header("X-API-KEY") String BearerToken, @Path("service") int service);

    @GET("support/ticket/ticket")
    Call<Ticket_Specification> getParticularTicket(@Header("X-API-KEY") String BearerToken, @Query("ticket") String ticket);

    @GET("support/ticket-attachment/{ticketid}")
    Call<AttachmentOnly> attachm(@Header("X-API-KEY") String BearerToken, @Path("ticketid") String ticket_id);



    @GET("dns/domain")
    Call<Domain> Domain_list(@Header("X-API-KEY") String BearerToken);

    //for billing
    @GET("account/invoice")
    Call<BillingModal> billinglist(@Header("X-API-KEY") String BearerToken);

    //for Profile
    @GET("account/contact")
    Call<Auth_Model> Authlist(@Header("X-API-KEY") String BearerToken);



    //for Authorized Contact
    @GET("account/profile")
    Call<Profile_Model> profilelist(@Header("X-API-KEY") String BearerToken);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "account/contact", hasBody = true)
    Call<Auth_del> delete(@Header("X-API-KEY") String BearerToken ,@Field("id") String id_user);


    //    @DELETE("dns/domain/domain")
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "dns/domain", hasBody = true)
    Call<DeleteDomainPojo> DeleteDomain(@Header("X-API-KEY") String BearerToken,@Field("domain") String domain , @Field("domains") String domainName);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "device/console", hasBody = true)
    Call<ResponseBody> resetConsole(@Header("X-API-KEY") String BearerToken,@Field("service") int service);

    //for Update Account Information
    @FormUrlEncoded
    @PUT("account/profile")
    Call<UpdateProfile_Model> UpdateProfile(@Header("X-API-KEY") String BearerToken, @Field("id") String id, @Field("firstname") String firstname, @Field("lastname") String lastname,  @Field("email") String email, @Field("phonenumber") String phonenumber, @Field("postcode") String postcode);

    @FormUrlEncoded
    @PUT("account/contact")
    Call<Update_account> update_info(@Header("X-API-KEY") String BearerToken,
                                     @Field("id") String id,
                                     @Field("firstname") String firstname,
                                     @Field("lastname") String lastname,
                                     @Field("email") String email);


    @Multipart
    @POST("support/ticket")
    Call<ReplyPOJO> replyticket(@Header("X-API-KEY") String BearerToken,
                                       @Part("ticket") RequestBody ticket,
                                       @Part("message") RequestBody message,
                                       @Part MultipartBody.Part attachment);

   @Multipart
    @POST("support/ticket")
    Call<CreateTicketPOJO> create_ticket(@Header("X-API-KEY") String BearerToken,
                                         @Part("subject") RequestBody subject,
                                         @Part("message") RequestBody message,
                                         @Part("dept") RequestBody department,
                                         @Part MultipartBody.Part attachment);

    @GET("device/hardware/{service}")
    Call<PowerPojo> getHardwareStatus(@Header("X-API-KEY") String BearerToken, @Path("service") int service);

    @GET("device/list/{service}")
    Call<ResponseBody> getParticularService(@Header("X-API-KEY") String BearerToken, @Query("service") int service);

    @GET("network/netblock/{service}")
    Call<ResponseBody> getNetworking(@Header("X-API-KEY") String BearerToken, @Query("service") int service);

    @GET("device/console/{service}")
    Call<ResponseBody> launchConsole(@Header("X-API-KEY") String BearerToken, @Query("service") int service);

    @FormUrlEncoded
    @POST("device/power")
    Call<PowerPojo> powerStatusUpdate(@Header("X-API-KEY") String BearerToken,@Field("action") String action, @Field("service") int service);

    @FormUrlEncoded
    @POST("device/power")
    Call<ResponseBody> forcePowerOff(@Header("X-API-KEY") String BearerToken, @Field("action") String action, @Field("service") int service);

    @FormUrlEncoded
    @POST("device/power")
    Call<ResponseBody> powerCycle(@Header("X-API-KEY") String BearerToken,@Field("action") String action, @Field("service") int service);

    @FormUrlEncoded
    @POST("network/bandwidth")
    Call<ResponseBody> graphimage(@Header("X-API-KEY") String BearerToken,@Field("service") int service);


}
