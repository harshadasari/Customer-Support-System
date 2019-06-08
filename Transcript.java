
import java.util.*;
public class Transcript { 																		

	String transcript;
	public Transcript(String transcript) {

		this.transcript=transcript;
		// TODO Auto-generated constructor stub
	}
	public Transcript() {
		// TODO Auto-generated constructor stub
	}
	public final String[] stopWords={"A","about","above","after","again","against","all","am","an","and","any","are","aren't","as","at","be","because","been","before","being","below","between","both","but","by","can't","cannot","could","couldn't","did","didnt","do","does","doesn't","doing","don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","I","I'd","I'll","I'm","I've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","no","nor","not","of","off","on","once","only","or","other","ouht","our","ours","ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some","such","than","that","that's","the","their","theirs","them","themselves","then","there","here's","these","they","they'd","they'll","they're","they've","this","those","through","to","too","under","until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what","what's","when","when's","whre","where's","which","while","who","who's","whom","why","why's","with","won't","would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves","exit","EXIT","EOM"};
	public final String[] positiveLexicons={"don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","I","I'd","I'll","I'm","I've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more"};
	public final String[] negativeLexicons={"bad","worse","worst","crazy","confuse","confused","confusing","disturb","disturbance","disturbed","angry","annoy","annoyed","annoying","annoyingly","refuse","refsed","regret","reject","rejected","repetitive","repetitively","cancel","discontinue","terminate","mad","unhappy","unhelpful","unlucky"};

	ArrayList<String> sentenceList= new ArrayList<String>();
	ArrayList<String> wordList=new ArrayList<String>();
	//ArrayList<ArrayList<String>> wordlist=new ArrayList<ArrayList<String>>();
	Object[][] totalWorldCount=new Object[1][2];
	double averageSentenceLength;
	String[] topFreqWords= new String[3];

	public String[] getTopFreqWords() {   																	//Getting top 3 frequency words.
		return topFreqWords;
	}


	public void setTopFreqWords(String[] topFreqWords) {													//setting top 3 frequency words.
		this.topFreqWords = topFreqWords;
	}


	public void List(){

		System.out.println("Transcript Creation");															
		this.sentenceList=new ArrayList<String>(Arrays.asList(transcript.split("\\.")));  // Spillting the transcripts into sentences
		System.out.println(sentenceList+"\n");

		String t1=transcript.replaceAll("\\W"," "); // For removing Punctuations
		System.out.println(t1+"\n");
		String t2=t1.replaceAll("\\s+"," ");//For removing Spaces
		System.out.println(t2+"\n");

		ArrayList<String> removeWords=new ArrayList<String>(Arrays.asList(this.stopWords));//For converting stopWords to Arraylist so that we can remove easily
		//System.out.println("StopWords"+removeWords);
		this.wordList=new ArrayList<String>(Arrays.asList(t2.split("\\s+"))); // For Splitting into words
		
		this.wordList.removeAll(removeWords);    //Removing words.
	

	}

	public String[] freqCount(){

		ArrayList<String> words= new  ArrayList<String>();
		ArrayList<Double> freq= new  ArrayList<Double>();
		String[] words1=wordList.toArray(new String[words.size()]);

		Map<String, Double> map = new HashMap<>();																//Using hashmaps to find the top 3 frequencies.
		for (String w : words1) {
			Double n = map.get(w);
			n = (n == null) ? 1 : ++n;																			//Counter to count the frequency of each unique word.
			map.put(w, n);
			// String[] targetArray = values.toArray(new String[values.size()]);
		}
		System.out.println(Arrays.asList(map));
		for(String key : map.keySet()){
			double val = map.get(key);
			words.add(key);																						//Creating arrayList of words.
			freq.add(val);																						//	Creating arraylist of frequencies
		}
		System.out.println(words); 																				//Creating an array of top 3 frequency words of every ticket.
		System.out.println(freq);
		int a = -1,b=-1,c=-1;
		for(int i=0; i<freq.size();i++){
			if(a==-1 || freq.get(i)>=freq.get(a)){
				c=b;
				b=a;
				a=i;
			}else if(b==-1 ||freq.get(i)>=freq.get(b)){
				c=b;
				b=i;
			}
			else if(c==-1 ||freq.get(i)>=freq.get(c)){
				c=i;
			}

		}

		System.out.println("top 3 words are: " + words.get(a)+"," + words.get(b)+","+words.get(c));
		String[] words2={ words.get(a) ,words.get(b) ,words.get(c)};
		System.out.println("\n" +words2[0]+","+words2[1]+","+words2[2]+"\n");
		
		String[] topFreqWords={words2[0],words2[1],words2[2]};
		return topFreqWords;
		
		// These to TopFreqWords using setTopFreqWords
		//setTopFreqWords(String[] words2);
	}


	public double getaverageSentenceLength(){																//	Getting average sentence length.

		return averageSentenceLength;
	}


	public void setaverageSentenceLength(){																	//	setting average sentence length.
		double averageSentenceLength;
		averageSentenceLength= wordList.size() / sentenceList.size(); //average sentence length.
		this.averageSentenceLength = averageSentenceLength;
	}



	public int getSimilarity(Transcript trans ){															//Checking similarity between current ticket and other tickets.

		/*Set<String> firstSet = new HashSet<String>(Arrays.asList(topFreqWords2));
		for(int i=0;i<ttFW.size();i++){
			String[] ttFWS=(String[]) ttFW.get(i).toArray();
		Set<String> secondSet = new HashSet<String>(Arrays.asList(ttFWS));

		}*/
		int count=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){			
				if(topFreqWords[i]==trans.topFreqWords[j]){
					count++;
					//continue;

				}
			}

		}


		return count;
	}
}











