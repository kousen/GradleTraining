package gradle.astro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AstroServiceTest {
    private final AstroService astroService = new AstroService();

    @Test
    void getAstronauts() {
        AstroResponse astroResponse = astroService.getAstronauts();
        assertNotNull(astroResponse);
        assertAll(
                () -> assertEquals(astroResponse.number(), astroResponse.people().size()),
                () -> assertTrue(astroResponse.number() > 0),
                () -> assertEquals(astroResponse.message(), "success")
        );
        System.out.println("There are " + astroResponse.number() + " people in space");
    }
}