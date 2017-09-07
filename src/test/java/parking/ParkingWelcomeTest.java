/**
 * 
 */
package parking;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author sachin.srivastava
 *
 */
public class ParkingWelcomeTest {

	private Welcome wel = new Welcome();
	@Test
	public void parkingWelcome(){
		assertTrue(wel.doWelcome().contains("Welcome"));
	}

}
