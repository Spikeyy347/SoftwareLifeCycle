package at.technikum_wien.swlc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void constructor_createsPlayerWithoutErrors() {
       //positive Constructor
        assertDoesNotThrow(() -> new Player('X'));
    }

   @Test
   void constructor_doesNotCreateNullInstance() {
        //negative Constructor
        Player player = new Player('X');

        assertNotNull(player);
   }

   @Test
    void getMarker_returnsCorrectMarker() {
        //positive getMarker
        Player player = new Player('X');

        assertEquals('X', player.getMarker());
   }

   @Test
    void getMarker_doesNotReturnDifferentMarker() {
        //negative getMarker
       Player player = new Player('X');

       assertNotEquals('O', player.getMarker());
   }


}
