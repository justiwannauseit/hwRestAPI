package tests.petstore;

import clients.petstore.PetStore;
import clients.petstore.PetStoreService;
import models.pet.Category;
import models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestOnlyMethodPut {
    private static PetStore petStore;
    private static Pet myPet;

    @BeforeAll
    public static void beforeAll() throws IOException {
        petStore = new PetStoreService().getPetStore();

        myPet = new Pet();
        Category categoryMyPet = new Category();

        myPet.setId(88526);
        myPet.setName("IvanGuy");
        categoryMyPet.setName("Wild");
        myPet.setCategory(categoryMyPet);
        myPet.setStatus("sold");
        petStore.addPet(myPet).execute();
    }

    @Test
    public void putPet() throws IOException {

        myPet.setName("PewDiePie");
        petStore.updatePet(myPet).execute();
        petStore.getPetById(myPet.getId()).execute();
        Assertions.assertNotEquals("IvanGuy", petStore.getPetById(myPet.getId()).execute().body().getName());
        System.out.println();
    }

}
