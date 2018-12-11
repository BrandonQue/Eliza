package Eliza;

public class QuestionBank {

	private static final int BANK = 20;
	private String[] questions;
	private String[] bank = new String[BANK];
	private int[] rand;
	private int currQ;
	private int numQ;
	
	public QuestionBank(int nq) {
		numQ = nq;
		questions = new String[numQ];
		rand = new int[numQ];
		questions = populateQuestions();
	}
	
	public String[] populateQuestions() {
		bank[0]= "Which three words describe you best?";
		bank[1]= "Which is your best feature?";
		bank[2]= "Which common saying or phrase describes you?";
		bank[3]= "What’s the best thing that’s happened to you this week?";
		bank[4]= "Who was your role model when you were a child?";
		bank[5]= "Who was your favorite teacher and why?";
		bank[6]= "What was your favorite subject at school?";
		bank[7]= "When you were a child, what did you want to be when you grew up?";
		bank[8]= "If you could have one wish come true what would it be?";
		bank[9]= "Which would you prefer, three wishes over five years or one wish right now?";
		bank[10]= "How much wood would a woodchuck chuck if a woodchuck could chuck wood?";
		bank[11]= "What do you do on your free time?";
		bank[12]= "What is your favorite food and why?";
		bank[13]= "What is the meaning of life?";
		bank[14]= "What are you?";
		bank[15]= "what is/are your hobby/hobbies?";
		bank[16]= "Favorite animal(s)?";
		bank[17]= "What is your evil plan to take over the world?";
		bank[18]= "What makes you happy?";
		bank[19]= "What makes you sad?";
		
		rand = randomizeArray();
		String[] q = new String[rand.length];
		for(int i = 0; i < rand.length; i++) {
			q[i] = bank[rand[i]-1];
		}
		return q;
	}
	
	public int[] randomizeArray() {
		int[] arr = new int[numQ];
		for(int i = 0; i < arr.length; i++) {
			int num = (int)(Math.random()*BANK+1);
			while(linearSearch(arr, num)) {
				num = (int)(Math.random()*BANK+1);
			}
			arr[i] = num;
		}
		return arr;
	}
	
	public boolean linearSearch(int[] a, int num) {
		boolean b = false;
		for(int i = 0; i < a.length; i++) {
			if(a[i] == num) {
				b = true;
			}
		}
		return b;
	}
	
	public String getQuestion(int n) {
		return questions[n];
	}
	
}
