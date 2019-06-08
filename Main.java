import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	
	public void a(){
		
		
		ArrayList<Object> Analysis= new ArrayList<Object>();
		int a=0;
		double[] a1={1,2,3};
		double[] a2={1,2,3,4,5,6,7,8,9};
		
		Analysis.add(a);
		for(int i=0;i<3;i++){
			
				Analysis.add(a1[i]);
		}
		
		for(int j=0;j<9;j++){
			
			Analysis.add(a2[j]);
		}

		System.out.println(Analysis);
		Scanner Sc=new Scanner(System.in);
		System.out.println("Enter the type of Analysis \n Press 0 for the Overall Sentiment Scores of the Tickets \n Press 1 for the Average Sentiment Scores of the ticket for Each Customer Representative \n Press 2 for Average Sentiment Scores for the Services \n");
		//int n=toA;		
		int n=Sc.nextInt();
		int count1=3;
		int count2=9;
		int flag=1;
		//ArrayList<Object> Analysis=sentimentAnalysis();

		if(n==0){

			System.out.println("Overall Sentiment Scores of the Tickets : "+Analysis.get(0)+ "\n");
		}

		else if(n==1){
			for(int i=1;i<count1+1;i++)
				
				System.out.println("Average Sentiment Scores of the tickets of Customer Representative-"+i+ ":" +Analysis.get(i)+ "\n");


		}

		else if(n==2){
			for(int i=count1+1;i<count1+count2+1;i++){
				//int flag=1;
				System.out.println("Average Sentiment Scores for the Service-"+ flag+ " : "+Analysis.get(i)+ "\n");
				flag++;
		}
		}
		
		
	}
}
