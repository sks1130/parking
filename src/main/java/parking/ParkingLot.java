package parking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Scanner;

import constants.Constants.Messages;
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
	static PriorityQueue<Integer> slotQueue = new PriorityQueue<>();// min heap
	// --head
	// would
	// give
	// closet
	// from the
	// entry

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			String nextLine = scan.nextLine();
			accept(nextLine, scan);
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void accept(String input, Scanner scan) throws Exception {
		if (input == null || input.isEmpty()) {
			// when no filename is entered then w'll go by default console input
			// and output till the loop ends when Input : is blank entered
			System.out.println("Input:");
			String commandInput = scan.nextLine();
			System.out.println();
			System.out.println("Output:");
			System.out.println(inputCommand(commandInput));
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
				System.out.println(inputCommand(commandInput));
			}

		} else {
			// when filename is entered if absolute path is provided then it
			// will look into the its root src directory else read file from the
			// absolute path given
			File file = new File(input);
			if (file.exists() && !file.isDirectory()) {
				// read the file and show the output
				try (BufferedReader br = new BufferedReader(new FileReader(input))) {
					String commandInput = br.readLine();
					System.out.println(inputCommand(commandInput));
					while (commandInput != null) {
						commandInput = br.readLine();
						System.out.println(inputCommand(commandInput));
					}
				}
			} else {
				System.out.println("File not found");
			}
		}
	}

	private static String inputCommand(String commandInput) {
		if (commandInput == null || commandInput.isEmpty()) {
			return "";
		}
		try {
			return executeCommand(commandInput);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private static String executeCommand(String commandInput) throws Exception{
		String[] arr = commandInput.split(" "); //0th element would always be command
		String output = null;
		if(arr.length!=0 && arr[0]!=null && !arr[0].isEmpty()){
			switch (arr[0]) {
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
				output = "";
				break;
			}
			return output;
		} else {
			return "";
		}
	}

	private static String createParkingLot(int numberOfLot) {
		try {
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
			return  Messages.CreateParkingLot.getValue() + numberOfLot + Messages.ParkingSlots.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private static String entryParkingLot(String regNum, String color) {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private static String exitParkingLot(int slot) {
		try {
			size--;
			slotQueue.add(slot);
			Slots slots = getSlot(slot);
			slots.setCars(null);
			return Messages.Leave.getValue() + slot + Messages.Free.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	private static void showStatus(){
		try {
			System.out.println(Messages.SlotNumber.getValue() + "\t" + Messages.RegistrationNumber.getValue() + "\t" + Messages.Colour.getValue() );
			for(int i =1 ; i< slots.length ;i++){
				if(slots[i].getCars()!=null){
					System.out.print(i + "\t" + slots[i].getCars().getRegistrationNumber() + "\t" + slots[i].getCars().getColor());
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Slots getSlot(int slot) {
		return slots[slot];
	}

	private static int getNextSlotNumber() {
		return slotQueue.peek();
	}

	@SuppressWarnings("unused")
	private static int parkingSize() {
		return size;
	}

	private static boolean isParkingFull() {
		return size == slots.length - 1;
	}

	private static String getAllRegistrations(String color) {
		try {
			if (color == null || color.isEmpty()) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			int i = 1;
			for (Slots s : slots) {
				if(s!=null && s.getCars()!=null){
					if (s.getCars().getColor().equals(color)) {
						if (i > 1 && i < slots.length) {
							sb.append(", ");
						}
						sb.append(s.getCars().getRegistrationNumber());
					}
					i++;
				}
			}
			if(sb.toString().isEmpty()){
				return Messages.NotFound.getValue();
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	private static String getSlotNumbersForRegistration(String regisNum) {
		try {
			if (regisNum == null || regisNum.isEmpty()) {
				return "";
			}
			for (Slots s : slots) {
				if (s != null && s.getCars() != null && s.getCars().getRegistrationNumber().equals(regisNum)) {
					return String.valueOf(s.getSlotNumber());
				}
			}
			return Messages.NotFound.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	private static String getSlotNumbersForColor(String color) {
		try {
			if (color == null || color.isEmpty()) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			int i = 1;
			for (Slots s : slots) {
				if(s!=null && s.getCars()!=null){
					if (s.getCars().getColor().equals(color)) {
						if (i > 1 && i < slots.length) {
							sb.append(", ");
						}
						sb.append(s.getSlotNumber());
					}
					i++;
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
