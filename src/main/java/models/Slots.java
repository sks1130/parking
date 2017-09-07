/**
 * 
 */
package models;

/**
 * @author sachin.srivastava
 *
 */
public class Slots {
	
	int slotNumber;
	Cars cars;
	
	public Slots(int slot , Cars cars){
		this.slotNumber = slot;
		this.cars = cars;
	}
	
	public Cars getCar(int slotNumber) {
		return cars;
	}
}
