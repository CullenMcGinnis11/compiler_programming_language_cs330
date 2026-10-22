import java.util.List;

public class TokenizerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//put the code test case in string code
		String testcase1 = """
	            Pokemon pokemon1 = Pokemon("./Pokemons/1.txt");
	            String name = 		"";
	            int a = 100;
				a += 20;
				print a;
				cc(a+=1;
	            """;
		
		String testcase2 = """
	            String Stringa = "abcd";;;
	            spellDatabase
				spelldatabase
				print (abc100};
	            """;
		
		String testcase3 = """
	            int 200a = 200;
				int b = 200a + 200;
	            """;
		
		String testcase4 = """
	            // This is a comment
				Pokemon a + Pokemon("./Pokemons/1.txt");
				pokemon1.hp = 50;   // another comment
				for(int i = 0; i<3;i++){
					name++;
					3 + 5;
				}
				if(o == 3)
				{
					o = 9;
				}
	            """;
		
		String testcase5 = """
				int int ab = 300;
				function int add(int a, int b)
				{
					int c = a + b;
					return c;
				}
				int a = add(10, a);
				spelldatabase "./SpellDatabase.txt";
				show pokemon1;
				print name + "win the battle!";

	            """;
		
		String testcase6 = """
	            while(a>0){
	            	a++;
	            	elseif(pokemon1.hp<0){
	            		show pokemon1;
	            	}
	            	else{
	            		Show showPokemon;
	            	}	            
	            }
	            """;
		
		
		String testcase7 = """
	            (())(((
	            """;
		
		String testcase8 = """
	            Pokemon print = ();;;
	            """;
		
		
		
		String testcase9 = """
	            String @cd = "100";
	            """;
		
		String testcase10 = """
	            What about a sentence?
	            """;
		
		test(testcase1,1);
		test(testcase2,2);
		test(testcase3,3);
		test(testcase4,4);
		test(testcase5,5);
		test(testcase6,6);
		test(testcase7,7);
		test(testcase8,8);
		//test(testcase9,9);
		//test(testcase10,10);

	}
	
	public static void test(String input, int num) {
		//init the arraylist for tokens and pass the code into the tokenizer
		List<Token> tokens = Tokenizer.tokenize(input);
				
		System.out.println("-----------------------Test Case "+ num + "-----------------------");
				
		//We use this number to record the number of tokens in the list
		int number = 1;
				
		//traverse the list and print the tokens' information
		for(Token t : tokens) 
		{
			System.out.println("Token: " + t.getValue() + " Type: " + t.getType() + " Number: " + number);
		    number++;
		}
		
	}

}
