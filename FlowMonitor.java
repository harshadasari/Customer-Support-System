
import java.text.*;
import java.util.*;

public class FlowMonitor {

	public FlowMonitor() {
		// TODO Auto-generated constructor stub
	}
	public static String[] arrayRepresentativeIDs={"1","2","3"};							//array for representative IDs
	public static int[] arrayServiceIDs={1,2,3,4,5,6,7,8,9};								//array for representative service IDs
	static ArrayList<Ticket> T= new ArrayList<Ticket>();
	static Scanner Sc=new Scanner(System.in);
	static int ticketID=1000;																//initialising the ticket id

	public static void assignTicket(Ticket ticket,String toT){

		Date dateCreated=new Date();														//getting the current date
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		//int ticketID=1000;

		while(true){
			ticket.ticketID=Integer.toString(ticketID);										//converting the ticket id to string and assigning it to ticket id.
			ticketID++;
			break;
		}
		

		if(toT.equalsIgnoreCase("Phone")){													//if type of ticket is phone
			ticket.CustomerRepID=arrayRepresentativeIDs[0];									//assigning the ticket to representative 0
		}
		else if(toT.equalsIgnoreCase("InPerson")){											//if type of ticket is inperson
			ticket.CustomerRepID=arrayRepresentativeIDs[1];									//assigning the ticket to representative 1
		}
		else if(toT.equalsIgnoreCase("Email")){												//if type of ticket is email
			ticket.CustomerRepID=arrayRepresentativeIDs[2];									//assigning the ticket to representative 2
		}

		ticket.CustomerserviceRepID=Integer.toString(new Random().nextInt(arrayServiceIDs.length));		//randomly assigning the service representative id
		ticket.DateCreated=dateFormat.format(dateCreated);
		


	}


	public static void createTicket(String toT){											//creation of createTicket method

		Ticket total=new Ticket();

		if (toT.equalsIgnoreCase("Phone")){													//type of ticket is phone

			PhoneCall pc = new PhoneCall();													//Creating a phone object
			System.out.println("\n Assign Ticket Details\n");								
			assignTicket(pc,toT);															//assigning the basic ticket details

			total=pc.contactHandler();														//calling the contact handler method

			T.add(total);																	//adding the details in transcript
			//System.out.println("Testing");
			return;

		}

		else if (toT.equalsIgnoreCase("inperson")){											//type of ticket is inperson


			InPerson ip=new InPerson();														//Creating a inperson object
			assignTicket(ip,toT);															//assigning the basic ticket details
			total=ip.contactHandler();														//calling the contact handler method

			T.add(total);																	//adding the details in transcript
			return;
		}

		else if (toT.equalsIgnoreCase("email"));{											//type of ticket is email

			String email="";																
			String line="";
			System.out.println("Enter the email Provided\n");
			System.out.println("Please enter your ID, name,phoneNumber,Address,serviceID,ServiceStartDate,ServiceEndDate , rest of email\n");
			while(true){ // For taking input.Each sentence should end with "." and Representative goes to next line by pressing enter. "Exit" is used to get out of loop
				/*
				 * Please enter the Email in ID,Name,PhnNumber,Address,ServiceID,ServiceStartDate,ServiceEndDate and rest of mail with "." after each line
				 */

				line=Sc.nextLine();  // Infinite loop till "exit" is pressed
				email+=line;
				if(line.equalsIgnoreCase(""))
					break;
			}

			ArrayList<String>sentenceList=new ArrayList<String>(Arrays.asList(email.split("\\.")));						//splitting the email based on "."			

			String ID=sentenceList.get(0);																				//get first element from Sentencelist									
			sentenceList.remove(0);																						//remove the first element
			String Name=sentenceList.get(0);
			sentenceList.remove(0);
			int PhnNumber=Integer.parseInt(sentenceList.get(0));
			sentenceList.remove(0);
			String Address=sentenceList.get(0);
			sentenceList.remove(0);
			String ServiceID=sentenceList.get(0);
			sentenceList.remove(0);
			String ServiceStartDate=sentenceList.get(0);
			sentenceList.remove(0);
			String ServiceEndDate=sentenceList.get(0);
			sentenceList.remove(0);
			String Email=String.join(". ",sentenceList);


			Email em=new Email(ID,Name,PhnNumber,Address,ServiceID,ServiceStartDate,ServiceEndDate);					//creating the email object
			assignTicket(em,toT);																						//assigning the ticket
			total=em.contactHandler(Email);																				//calling the email contacthandler
			T.add(total);																								//adding the ticket to arraylist
		}
		return ;
		//updateTicket();

	}


	public static void updateTicket( ){																					//updateTicket method

		/*ArrayList<ArrayList<String>> totaltopFreqWords=new ArrayList<ArrayList<String>>();

		for(int i=0;i<T.size();i++)
		{
			ArrayList<String> FreqWords=new ArrayList<String>();
			for(int j=0;j<3;j++){
				FreqWords.add(T.get(i).Trans.topFreqWords[j]);}

			totaltopFreqWords.add(FreqWords);
			FreqWords.clear();
		}*/

		Transcript trans=new Transcript();																				//creating an object of type transcript
		//int[] simNum = null;
//		ArrayList<ArrayList<Integer>> simNum=new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> simNum=new ArrayList<Integer>();																//creation of simnum array list 
		for(int j=0;j<T.size();j++){																					//loop for fetching the suspended ticket
			if(T.get(j).DepartmentID==-1){																				//check condition.
				for(int i=0;i<T.size();i++){
					if(T.get(i).DepartmentID!=-1){
						Transcript Trans=T.get(i).Trans;																//fetching data from transcript.
//						ArrayList<Integer>simnum=new ArrayList<Integer>(Arrays.asList(T.get(j).Trans.getSimilarity(Trans)));
						int num =Trans.getSimilarity(Trans);															//checking for similarity by  calling the getsimilarity method.
						simNum.add(num);																				//Adding the result to simnum arraylist to check the similarity.
					}else
						simNum.add(0);
				}
				int max = 0;
				for(int p =0 ; p< simNum.size();p++){																	//loop to check the highest number of similarity. 
					if(simNum.get(p)>simNum.get(max))
						max = p;
				}
				if(simNum.get(max)>0)																					//assigning the department ID of the most similar ticket.
					T.get(j).DepartmentID = T.get(max).DepartmentID;
				else
					System.out.println("No similar ticket found");

				//int n=trans.getSimilarity(T.get(j).Trans.topFreqWords,totaltopFreqWords);
				//T.get(j).DepartmentID=n ;
			}
		}
	}

	public static int getSentiment(Ticket T){																			//Calling the ticket and checking the wordlist with positive and negative lexicons.
		int sentimentScore=0;
		Transcript Trans=new Transcript();
		int positive=0;
		int negative=0;
		//System.out.println("Entering Sentiment Score Calculator");
		//System.out.println("WordList Size"+T.Trans.wordList.size()+"\n");
		for(int i =0;i <T.Trans.wordList.size();i++){																	
			for(int j =0;j< Trans.positiveLexicons.length;j++){
				if(T.Trans.wordList.get(i)==Trans.positiveLexicons[j]){
					positive++;
					System.out.println(T.Trans.wordList.get(i));
				}
			}
			for(int k=0;k < Trans.negativeLexicons.length;k++){
				if(T.Trans.wordList.get(i)==Trans.negativeLexicons[k]){
					negative++;
					System.out.println(T.Trans.wordList.get(i));
				}
			}
		}
		if(positive > negative){ 																							//Assigning sentimentScore according to the number of positive and negative lexicons.
			sentimentScore=3;
		}

		else if(positive < negative){
			sentimentScore=1;
		}
		else{
			sentimentScore=2;
		}
		return sentimentScore;
	}

	public static ArrayList<Object> sentimentAnalysis(){

		ArrayList<Object> analysis=new ArrayList<Object>();
		double sum=0;
		double[] Rsum=new double[arrayRepresentativeIDs.length];
		int[] Rcount=new int[arrayRepresentativeIDs.length];
		double[] Ssum= new double[arrayServiceIDs.length];
		int[] Scount= new int[arrayServiceIDs.length];

		for(int i=0;i<T.size();i++){ 																							//Calculating the total sentimentScore
			int sentiscore=getSentiment(T.get(i));
			sum+=sentiscore;
		}
		analysis.add(sum/T.size());																								//Finding the average sentiment score of all the tickets.

		for(int j=0;j<T.size();j++){																							//Getting the sentiment Score from the customer representative.
			for(int m=0;m<arrayRepresentativeIDs.length-1;m++){
				if(T.get(j).CustomerRepID.equalsIgnoreCase(arrayRepresentativeIDs[m])){
					Rsum[m]+=getSentiment(T.get(j));
					Rcount[m]++;
				}

			}
		}

		double[] Ranalysis=new double[arrayRepresentativeIDs.length]; 															//Average of sentimentScore based on customer Representative.
		for(int a=0;a<arrayRepresentativeIDs.length-1;a++){
			Ranalysis[a]=Rsum[a]/Rcount[a];
			analysis.add(Ranalysis[a]);
		}
		


		for(int k=0;k<T.size();k++){																						//Total sentimentScore based on customer service.
			for(int l=0;l<arrayServiceIDs.length-1;l++){
				if(T.get(k).CustomerserviceRepID.equalsIgnoreCase(Integer.toString(arrayServiceIDs[l]))){
					Ssum[l]+=getSentiment(T.get(k));
					Scount[l]++;
				}

			}
		}
		double[] Sanalysis=new double[arrayServiceIDs.length];																//Average sentimentScore based on customer service.
		for(int a=0;a<arrayServiceIDs.length-1;a++){
			Sanalysis[a]=Ssum[a]/Scount[a];
			analysis.add(Sanalysis[a]);
		}
		
		
		return analysis ;


	}

	public static void printAnalysis( ){

		System.out.println("Enter the type of Analysis \n Press 0 for the Overall Sentiment Scores of the Tickets \n Press 1 for the Average Sentiment Scores of the ticket for Each Customer Representative \n Press 2 for Average Sentiment Scores for the Services \n");
		//int n=toA;		
		int n=Sc.nextInt();
		int flag=1;
		int count1=arrayRepresentativeIDs.length;
		int count2=count1+arrayServiceIDs.length;
		ArrayList<Object> Analysis=sentimentAnalysis();

		if(n==0){																												//Printing analysis according to user input.

			System.out.println("Overall Sentiment Scores of the Tickets : "+Analysis.get(0)+ "\n");
		}

		else if(n==1){
			for(int i=1;i<arrayRepresentativeIDs.length+1;i++){		
			
			System.out.println("Average Sentiment Scores of the tickets of Customer Representative-"+i+ ":" +Analysis.get(i)+ "\n");

			}
		}

		else if(n==2){
			for(int i=count1+1;i<count2+1;i++)
				System.out.println("Average Sentiment Scores for the Service-"+ flag+ " : "+Analysis.get(i)+ "\n");
			flag++;
		}

		System.out.println("\n \n ");
	}

	public static void printFlowStatus(){
		System.out.println("********************************** \n");
		System.out.println("Printing All the tickets \n \n \n");
		System.out.println("********************************** \n");
		for(int i=0;i<T.size();i++){
			System.out.println("********************************** \n");
			System.out.println("Ticket Details : \n");
			System.out.println("Ticket ID : " + T.get(i).ticketID);
			System.out.println("Date Created : " + T.get(i).DateCreated);
			System.out.println("Customer's Representative ID : " + T.get(i).CustomerRepID);
			System.out.println("Customer's Service ID : " + T.get(i).CustomerserviceRepID+"\n");
			System.out.println("Customer's Details \n");
			System.out.println("Customer's ID " + T.get(i).ID);
			System.out.println("Customer's Name " + T.get(i).Name);
			System.out.println("Customer's Phone Number" + T.get(i).phnNumber);
			System.out.println("Customer's Address" + T.get(i).Address+"\n");
			System.out.println("Customer's Service Details \n");
			System.out.println("Service ID" + T.get(i).ServiceID);
			System.out.println("Start Date of Service" + T.get(i).ServiceStartDate);
			System.out.println("End Date of Service" + T.get(i).ServiceEndDate);
			System.out.println("Department ID"+ T.get(i).DepartmentID);
			System.out.println("Status of Ticket"+T.get(i).Isresolved);
			System.out.println("********************************** \n");
			System.out.println("\n \n ");

		}


	}
}

