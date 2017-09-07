/**
 * 
 */
package constants;

/**
 * @author sachin.srivastava
 * Its a class of constants used in the projects.
 *
 */
public class Constants {

	public enum ParkingCommands{
		create_parking_lot,
		park,
		leave,
		status,
		registration_numbers_for_cars_with_colour,
		slot_numbers_for_cars_with_colour,
		slot_number_for_registration_number;
	}
	public enum Messages{
		
		Allocated("Allocated slot number: "),
		NotFound("Not found"),
		ParkingFull("Sorry, parking lot is full"),
		SlotNumber("Slot No."),
		RegistrationNumber("Registration No"),
		Colour("Colour"),
		CreateParkingLot("Created a parking lot with "),
		ParkingSlots(" slots");
		
		private String name;
		private Messages(String name) {
			this.name = name;
		}
		public String getValue() {
			return name;
		}
	}
}
