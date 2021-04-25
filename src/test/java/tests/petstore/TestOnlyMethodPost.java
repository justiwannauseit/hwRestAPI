package tests.petstore;

import clients.petstore.PetStore;
import clients.petstore.PetStoreService;
import models.pet.Category;
import models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestOnlyMethodPost {
    private static PetStore petStore;

    @BeforeAll
    public static void beforeAll() {
        petStore = new PetStoreService().getPetStore();
    }

    @Test
    public void test() throws IOException {

        Pet myPet = new Pet();
        Category categoryMyPet = new Category();

        myPet.setId(1000);
        myPet.setName("Vasiliy");
        categoryMyPet.setName("Wild");
        myPet.setCategory(categoryMyPet);
        myPet.setStatus("sold");

        Pet petAfterRetrofit = petStore.addPet(myPet).execute().body();
        Assertions.assertEquals(myPet, petAfterRetrofit);

    }

}
