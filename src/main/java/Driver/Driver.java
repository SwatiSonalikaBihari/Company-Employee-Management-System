package Driver;

import java.util.Scanner;
import Entity.*;
import Service.*;
public class Driver {

	public static void main(String[]args)
	{
		System.out.println("Enter 1.ToSaveRecords");
		System.out.println("Enter 2.ToUpdateRecords");
		System.out.println("Enter 3.ToDeleteRecords");
		System.out.println("Enter 4.ToFetchRecords");
		Scanner scn=new Scanner(System.in);
		int n=scn.nextInt();
		switch(n) {
		case 1:Service.save();
		      break;
		case 2:Service.update();
		       break;
		
		case 3:Service.delete();
		break;
		
		case 4:Service.fetch();
		 default:System.out.println("Invalid input");
		}
	}
}
