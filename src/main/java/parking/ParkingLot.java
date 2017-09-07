package parking;

import java.util.PriorityQueue;
import java.util.Scanner;

import constants.Constants.Messages;
import constants.Constants.ParkingCommands;
import models.Cars;
import models.Slots;

/**
 * @author sachin.srivastava
 * This class is starting point of execution
 * @params either it accepts the filename or inputs from the console in case of no filename is provided
 */
public class ParkingLot {

	static Slots[] slots; // total slots available
	static int size; // number of cars in the parking
	static PriorityQueue<Integer> slotQueue = new PriorityQueue();// min heap
	// --head
	// would
	// give
	// closet
	// from the
	// entry

	public static void main(String[] args) {
		Welcome welcome = new Welcome();
		System.out.println(welcome.doWelcome());
		Scanner scan = new Scanner(System.in);
		String nextLine = scan.nextLine();
		accept(nextLine, scan);
		scan.close();
		/// add method to pass the parameter

	}

	private static void accept(String input, Scanner scan) {
		if (input == null || input.isEmpty()) {
			System.out.println("Input:");
			String commandInput = scan.nextLine();
			System.out.println();
			System.out.println("Output:");
			System.out.println("comman output");// write the output -TODO
			while (!commandInput.isEmpty()) {
				System.out.println();
				System.out.println("Input:");
				String commandInput2 = scan.nextLine();
				commandInput = commandInput2;
				if (commandInput.isEmpty()) {
					// break the loop and exit from the console when enter any
					// empty
					break;
				}
				System.out.println();
				System.out.println("Output:");
				System.out.println("comman output");// write the output-- TODO
			}

		}
	}

	private static String inputCommand(String commandInput) {
		if (commandInput == null || commandInput.isEmpty()) {
			return null;
		}
		return executeCommand(commandInput);
	}

	private static String executeCommand(String commandInput){
		String[] arr = commandInput.split(" "); //0th element would always be command
		String output = null;
		if(arr.length!=0 && arr[0]!=null && !arr[0].isEmpty()){
			ParkingCommands pc = getCommand(arr[0]);
			switch (pc.name()) {
			case "create_parking_lot":
				output = createParkingLot(Integer.parseInt(arr[1]));
				break;
			case "park":
				output = entryParkingLot(arr[1],arr[2]);
				break;
			case "leave":
				output = exitParkingLot(Integer.parseInt(arr[1]));
				break;
			case "status":
			   showStatus();
			   output = "";
				break;
			case "registration_numbers_for_cars_with_colour":
				output = getAllRegistrations(arr[1]);
				break;

			case "slot_numbers_for_cars_with_colour":
				output = getSlotNumbersForColor(arr[1]);
				break;
			case "slot_number_for_registration_number":
				output = getSlotNumbersForRegistration(arr[1]);
				break;		
			default:
				break;
			}
			return output;
		} else {
			return "";
		}
	}

	public static ParkingCommands getCommand(String command) {
		for (ParkingCommands pc : ParkingCommands.values()) {
			if (pc.name().equals(command)) {
				return pc;
			}
		}
		return null;
	}

	private static String createParkingLot(int numberOfLot) {
		if (numberOfLot < 1) {
			return "";
		}
		slots = new Slots[numberOfLot + 1]; // slot starting from 1 to n 0th
		// would be null
		slots[0] = null;
		for (int i = 1; i <= numberOfLot; i++) {
			slots[i] = new Slots(i, null);
			slotQueue.add(i);
		}
		return Messages.CreateParkingLot.getValue() + numberOfLot + Messages.ParkingSlots.getValue();
	}

	private static String entryParkingLot(String regNum, String color) {
		if (regNum == null || regNum.isEmpty() || color == null || color.isEmpty()) {
			return "";
		}
		if (isParkingFull()) {
			return Messages.ParkingFull.getValue();
		}
		Cars car = new Cars(regNum, color);
		size++;
		int slotNum = getNextSlotNumber();
		Slots s = new Slots(slotNum, car);
		slotQueue.poll();// remove the filled slot from queue
		slots[slotNum] = s;
		return Messages.Allocated.getValue() + slotNum;
	}

	private static String exitParkingLot(int slot) {
		size--;
		slotQueue.add(slot);
		Slots slots = getSlot(slot);
		slots.setCars(null);
		return Messages.Leave.getValue() + slot + Messages.Free.getValue();
	}
	private static void showStatus(){
		System.out.println(Messages.SlotNumber.getValue() + "  " + Messages.RegistrationNumber.getValue() + "  " + Messages.Colour.getValue() );
		for(int i =1 ; i<=slots.length ;i++){
			System.out.print(i + "  " + slots[i].getCars().getRegistrationNumber() + "  " + slots[i].getCars().getColor());
		}
	}

	private static Slots getSlot(int slot) {
		return slots[slot];
	}

	private static int getNextSlotNumber() {
		return slotQueue.peek();
	}

	private static int parkingSize() {
		return size;
	}

	private static boolean isParkingFull() {
		return size == slots.length - 1;
	}

	private static String getAllRegistrations(String color) {
		if (color == null || color.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (Slots s : slots) {
			if (s.getCars().getColor().equals(color)) {
				sb.append(color);
			}
			if (i > 1 && i < slots.length) {
				sb.append(", ");
			}
			i++;
		}
		if(sb.toString().isEmpty()){
			return Messages.NotFound.getValue();
		}
		return sb.toString();
	}
	private static String getSlotNumbersForRegistration(String regisNum) {
		if (regisNum == null || regisNum.isEmpty()) {
			return "";
		}
		for (Slots s : slots) {
			if (s.getCars().getRegistrationNumber().equals(regisNum)) {
				return regisNum;
			}
		}
		return Messages.NotFound.getValue();
	}
	private static String getSlotNumbersForColor(String color) {
		if (color == null || color.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (Slots s : slots) {
			if (s.getCars().getColor().equals(color)) {
				sb.append(s.getSlotNumber());
			}
			if (i > 1 && i < slots.length) {
				sb.append(", ");
			}
			i++;
		}
		return sb.toString();
	}
}
