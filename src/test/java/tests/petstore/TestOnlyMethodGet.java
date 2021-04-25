package tests.petstore;

import clients.petstore.PetStore;
import clients.petstore.PetStoreService;
import models.pet.Category;
import models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestOnlyMethodGet {

    private static PetStore petStore;

    @BeforeAll
    public static void beforeAll() {
        petStore = new PetStoreService().getPetStore();
    }

    @Test
    public void getPet() throws IOException {

        Pet myPet = new Pet();
        Category categoryMyPet = new Category();

        myPet.setId(8800);
        myPet.setName("Vasiliy");
        categoryMyPet.setId(5553535);
        categoryMyPet.setName("Wild");
        myPet.setCategory(categoryMyPet);
        myPet.setStatus("sold");

        petStore.addPet(myPet).execute();
        Assertions.assertEquals(myPet, petStore.getPetById(myPet.getId()).execute().body());

    }

    @Test
    public void getNonExistentPet() throws IOException {
        final int ID = 999999999;
        Assertions.assertEquals(404, petStore.getPetById(ID).execute().code());
    }

    @Test
    public void getPetByIncorrectId() throws IOException {
        final byte[] incorrectId = {1, 45, 9};
        Assertions.assertEquals(400, petStore.getPetByIncorrectId(incorrectId).execute().code());
    }

}
