package clients.petstore;

import models.pet.Pet;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;


public interface PetStore {

    @POST("/v2/pet")
    Call<Pet> addPet(@Body Pet pet);

    @GET("/v2/pet/{id}")
    Call<Pet> getPetById(@Path("id") int id);

    @GET("/v2/pet/{id}")
    Call<Pet> getPetByIncorrectId(@Path("id") byte[] bytes);

    @PUT("/v2/pet")
    Call<Pet> updatePet(@Body Pet pet);

    @DELETE("/v2/pet/{id}")
    Call<ResponseBody> deletePet(@Path("id") int id);

}
