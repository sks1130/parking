package parking;

import java.util.PriorityQueue;
import java.util.Scanner;

import org.hamcrest.core.AllOf;

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
	
	static Slots[] slots;
	static int size;
	static PriorityQueue<Integer> slotQueue = new PriorityQueue<>();//min heap --head would give closet from the entry
	
	public static void main(String[] args) {
		Welcome welcome = new Welcome();
		System.out.println(welcome.doWelcome());
		Scanner scan = new Scanner(System.in);
		String nextLine = scan.nextLine();
		accept(nextLine, scan);
		scan.close();
		///add method to pass the parameter
		
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
					//break the loop and exit from the console when enter any empty
					break;
				}
				System.out.println();
				System.out.println("Output:");
				System.out.println("comman output");// write the output-- TODO
			}

		}
	}
	private static String inputCommand(String commandInput){
		if(commandInput == null || commandInput.isEmpty()){
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
				output = 
				break;
			case "status":
				output = 
				break;
			case "registration_numbers_for_cars_with_colour":
				output = 
				break;
				
			case "slot_numbers_for_cars_with_colour":
				output = 
				break;
			case "slot_number_for_registration_number":
				output = 
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
		for(ParkingCommands pc : ParkingCommands.values()){
			if(pc.name().equals(command)){
				return pc;
			}
		}
		return null;
	}
	private static String createParkingLot(int numberOfLot){
		if(numberOfLot < 1){
			return null;
		}
		slots = new Slots[numberOfLot+1]; //slot starting from 1 to n  0th would be null
		slots[0] = null;
		for(int i = 1 ;i<=numberOfLot;i++){
			slots[i] = new Slots(i, null);
			slotQueue.add(i);
		}
		return Messages.CreateParkingLot.getValue() + numberOfLot + Messages.ParkingSlots.getValue();
	}
	private static String entryParkingLot(String regNum , String color){
		if(regNum == null || regNum.isEmpty() || color == null || color.isEmpty()){
			return null;
		}
		Cars car = new Cars(regNum, color);
		size++;
		int slotNum = getNextSlotNumber();
		Slots s = new Slots(slotNum, car);
		slotQueue.poll();//remove the filled slot from queue
		slots[slotNum] = s;
		return Messages.Allocated.getValue() + slotNum; 
	}
	private static int getNextSlotNumber(){
		return slotQueue.peek();
	}
	
	private static int parkingSize(){
		return size;
	}
	private boolean isParkingFull(){
		return size == slots.length-1 ;
	}
}
