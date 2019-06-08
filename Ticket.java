

import java.util.*;
public class Ticket {

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	String ticketID;					//basic ticket details
	String CustomerRepID;				//basic ticket details
	String DateCreated;					//basic ticket details
	String CustomerserviceRepID;		//basic ticket details
	String ID;							//customer details
	String Name;						//customer details
	int phnNumber;						//customer details	
	String Address;						//customer details
	String ServiceID;					//service details
	String ServiceStartDate;			//service details
	String ServiceEndDate;				//service details	
	boolean Isresolved; 				//service details
	int DepartmentID;					//service details
	Transcript Trans;					//service details
	FlowMonitor Fl;						//service details
				
	Scanner sc=new Scanner(System.in);



}

class PhoneCall extends Ticket{																				//contact handler of phone call.

	public Ticket contactHandler(){


		String transcript="";
		String line;
		String input;

		System.out.println("Customer Calls Company...");														//Assigning details.
		while(true){

			System.out.println("Press I for ID Number \nPress N for Name \nPress P for Phone Number \nPress A for Address \nPress SI for ServiceID \nPress SD for ServiceStartDate \nPress ED for ServiceEndDate \nPress T to add conversation into Transcript \nPress E to exit");
			input=sc.nextLine();
			if(input.equalsIgnoreCase("I")){
				System.out.println("Entering and Updating the ID");
				this.ID=sc.nextLine() ;
			}
			else if(input.equalsIgnoreCase("N")){
				System.out.println("Entering and Updating Name");
				this.Name=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("P")){
				System.out.println("Entering and Updating phone number");
				this.phnNumber=sc.nextInt();
			}
			else if(input.equalsIgnoreCase("A")){
				System.out.println("Entering and Updating address");
				this.Address=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("SI")){
				System.out.println("Entering and Updating ServiceID");
				this.ServiceID=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("SD")){
				System.out.println("Entering and Updating Service Start Date");
				this.ServiceStartDate=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("ED")){
				System.out.println("Entering and Updating Service End Date");
				this.ServiceEndDate=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("E"))
			{break;}

			else if(input.equalsIgnoreCase("T")){																	//Updating transcript.
				System.out.println("Enter and Update Transcript \n");
				while(true){
					line=sc.nextLine();
					transcript+=line;
					if(line.equalsIgnoreCase("")){
						break;}
				}
			}
		}

		Trans=new Transcript(transcript); 																		//Providing decision to the ticket.
		Trans.List();
		System.out.println(Trans.wordList);
		
		System.out.println("Please provide the Decision on this ticket\n Should ticket must be resolved?\n");
		Isresolved=sc.nextBoolean();																			//Checking if it is resolved or not,accordingly rerouted or suspended.
		{
			if(this.Isresolved==true){ // Representative enters whether the ticket is resolved or not , based on user input.
				this.DepartmentID=0;
				System.out.println("Ticket is Resolved");
			}
			else{ 	// Please enter specific department to route the ticket
				System.out.println("Please provide the department ID to route this ticket or enter -1 if this needs to be suspended");
				this.DepartmentID=sc.nextInt();{
					if(DepartmentID== -1){
						System.out.println("The ticket is suspended \n");}
					else{
						System.out.println("The ticket is rerouted to department: "+DepartmentID);} 	
			}
		}
		}

	

		return this;

	}

}

class InPerson extends Ticket{																					//contact handler of inperson.

	public Ticket contactHandler(){

		


		String input;

		System.out.println(" Customers Enters Company...\n ");													//Entering details.
		while(true){

			System.out.println("Press I for ID Number \nPress N for Name \nPress P for Phone Number \nPress A for Address \nPress SI for ServiceID \nPress SD for ServiceStartDate \nPress ED for ServiceEndDate  \nPress E to exit");
			input=sc.nextLine();
			if(input.equalsIgnoreCase("I")){
				System.out.println("Entering and Updating the ID");
				this.ID=sc.nextLine() ;
			}
			else if(input.equalsIgnoreCase("N")){
				System.out.println("Entering and Updating Name");
				this.Name=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("P")){
				System.out.println("Entering and Updating phone number");
				this.phnNumber=sc.nextInt();
			}
			else if(input.equalsIgnoreCase("A")){
				System.out.println("Entering and Updating address");
				this.Address=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("SI")){
				System.out.println("Entering and Updating ServiceID");
				this.ServiceID=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("SD")){
				System.out.println("Entering and Updating Service Start Date");
				this.ServiceStartDate=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("ED")){
				System.out.println("Entering and Updating Service End Date");
				this.ServiceEndDate=sc.nextLine();
			}
			else if(input.equalsIgnoreCase("E"))
			{break;}

		}



		System.out.println("Customer Leaves\n");


		System.out.println("Please provide the Decision on this ticket\n Should ticket must be resolved?\n"); 			//Providing decision about resolving.
		Isresolved=sc.nextBoolean();
		{
			if(this.Isresolved==true){ // Representative enters whether the ticket is resolved or not , based on user input.
				this.DepartmentID=0;
				System.out.println("Ticket is Resolved");
			}
			else{ 	// Please enter specific department to route the ticket
				System.out.println("Please provide the department ID to route this ticket or enter -1 if this needs to be suspended");
				this.DepartmentID=sc.nextInt();{
					if(DepartmentID== -1){
						System.out.println("The ticket is suspended \n");}
					else{
						System.out.println("The ticket is rerouted to department: "+DepartmentID);} 	
			}
		}
		}


		System.out.println("Representative Enters what ever he remembers \n"); 											//Adding comments after customer leaves.
		sc.nextLine();   //  
		String transcript=sc.nextLine(); // Enter everything in one line using "." as Line Separator.
		

		Trans=new Transcript(transcript);
		Trans.List();



		return this;

	}
}

class Email extends Ticket{																									//contact handler of email.
	
	public Email(String iD, String name, int phnNumber, String address, String serviceID, String serviceStartDate,
			String serviceEndDate) {
		// TODO Auto-generated constructor stub
		
		this.ID=iD; 																										//Getting details from the customer.
		this.Name=name;
		this.phnNumber=phnNumber;
		this.Address=address;
		this.ServiceID=serviceID;
		this.ServiceStartDate=serviceStartDate;
		this.ServiceEndDate=serviceEndDate;
		//String Email=email;
	}

	public Ticket contactHandler(String email){


		System.out.println("Printing the Email from Customer\n"+email);


		System.out.println("Please provide the Decision on this ticket whether resolved(TRUE or FALSE)"); 					//Proving decision about resolving the issue.
		Isresolved=sc.nextBoolean();
		if(this.Isresolved==true){ // Representative enters whether the ticket is resolved or not , based on user input.
			this.DepartmentID=0;
		}
		else{ 	// Please enter specific department to route the ticket
			System.out.println("Please provide the department ID to route this ticket or enter -1 if this needs to be suspended");
			this.DepartmentID=sc.nextInt();
			if(DepartmentID== -1){
				System.out.println("The ticket is suspended");}
			else{
				System.out.println("The ticket is rerouted to department: "+DepartmentID);} 
		}

		Trans=new Transcript(email);
		Trans.List();
		//this.sentimentScore=Fl.getSentiment(Trans.wordList);

		/*
		this.Trans.sentenceList=new ArrayList<String>(Arrays.asList(email.split("\\.")));  // Spillting the transcripts into sentences

		String t1=email.replaceAll("\\W"," "); // For removing Punctuations
		String t2=t1.replaceAll("\\s+"," ");//For removing Spaces

		ArrayList<String> removeWords=new ArrayList<String>(Arrays.asList(Trans.stopWords)); //For converting stopWords to Arraylist so that we can remove easily
		this.Trans.wordList=new ArrayList<String>(Arrays.asList(t2.split("\\s+"))); // For Splitting into words
		this.Trans.wordList.removeAll(removeWords);
		 */




		return this;

	}
}


