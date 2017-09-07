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

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public Cars getCars() {
		return cars;
	}

	public void setCars(Cars cars) {
		this.cars = cars;
	}
	
}
