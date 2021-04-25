package tests.petstore;

import clients.petstore.PetStore;
import clients.petstore.PetStoreService;
import models.pet.Category;
import models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestOnlyMethodDelete {

    private static PetStore petStore;
    private static final int PET_ID = 885262656;

    @BeforeAll
    public static void beforeAll() {
        petStore = new PetStoreService().getPetStore();
    }

    @Test
    public void deleteSuccessful() throws IOException {

        Pet myPet = new Pet();
        Category categoryMyPet = new Category();

        myPet.setId(PET_ID);
        myPet.setName("Ivan");
        categoryMyPet.setName("Wild");
        myPet.setCategory(categoryMyPet);
        myPet.setStatus("sold");

        petStore.addPet(myPet).execute();
        Assertions.assertTrue(petStore.deletePet(myPet.getId()).execute().isSuccessful());

    }


    @Test
    public void deleteNonExistentPet() throws IOException {
        Assertions.assertEquals(404, petStore.deletePet(PET_ID).execute().code());
    }


}
