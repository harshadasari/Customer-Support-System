/*
 * GROUP 5
 * 
 * Venkata Rama Harsha Dasari - vdasar3@uic.edu
 * Antara Gupta - agupt30@uic.edu
 * Shreayash Shukla - sshukl6@uic.edu
 * 
 */
import java.util.*;
public class ExecutionFlow {

	public static void main(String[] args){

		FlowMonitor Fl=new FlowMonitor();
		Scanner sc=new Scanner(System.in);								//Creating scanner object to take user input

		System.out.println("********************************** \n");
		System.out.println(" Welcome to Customer Support System \n");
		System.out.println("********************************** \n");
		while(true){
			System.out.println("********************************** \n");
			System.out.println(" Please Select the user \n 1.Customer Service Representative \n 2.Customer Services Analyst \n 3.Exit \n");		//Menu for choosing the representative.
			System.out.println("********************************** \n");
			int typeofUser=sc.nextInt();

			if(typeofUser==1){																	//If the user is customer representative.
				System.out.println("Please enter the type of ticket");
				String toT=sc.next();															//Take the type of ticket

				Fl.createTicket(toT);															//Creation of a ticket
				continue;

			}


			else if(typeofUser==2){																//if customer service analyst
				System.out.println("********************************** \n");
				System.out.println("Please Select the Options: \n\n 1.Update Suspened Ticket \n 2.Print Sentiment Analysis \n 3.Print Flow Status of All tickets ");
				System.out.println("********************************** \n");					//Select the above option for performing the analysis.
				int options=sc.nextInt();
				if(options==1){
					Fl.updateTicket();															//selecting option 1
				}

				else if(options==2){															//selecting option 2
					Fl.printAnalysis();
				}

				else if(options==3){															//selecting option 1
					Fl.printFlowStatus();
				}
				continue;

			}

			else if(typeofUser==3){																//for exiting the application
				break;
			}
		}
	}
}