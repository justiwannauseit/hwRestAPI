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
    private static final int ID = 999999999;

    @BeforeAll
    public static void beforeAll() {
        petStore = new PetStoreService().getPetStore();
    }

    @Test
    public void getPet() throws IOException {

        Pet myPet = new Pet();
        Category categoryMyPet = new Category();

        myPet.setId(ID);
        myPet.setName("Vasiliy");
        categoryMyPet.setName("Wild");
        myPet.setCategory(categoryMyPet);
        myPet.setStatus("sold");

        petStore.addPet(myPet).execute();
        Assertions.assertEquals(myPet, petStore.getPetById(myPet.getId()).execute().body());

    }

    @Test
    public void getNonExistentPet() throws IOException {
        petStore.deletePet(ID).execute();
        Assertions.assertEquals(404, petStore.getPetById(ID).execute().code());
    }

    @Test
    public void getPetByIncorrectId() throws IOException {
        final byte[] incorrectId = {1, 45, 9};
        Assertions.assertEquals(400, petStore.getPetByIncorrectId(incorrectId).execute().code());
    }

}
