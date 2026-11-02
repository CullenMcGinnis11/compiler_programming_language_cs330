import java.util.List;

public class Starter {

    public static void main(String[] args) {
       
        String code = """
                	            Pokemon pokemon1 = Pokemon("./Pokemons/1.txt");
                Pokemon pokemon2 = Pokemon("./Pokemons/2.txt");
                spelldatabase "./SpellDatabase.txt";

                String name = "";
                show pokemon1;
                show pokemon2;

                while(pokemon1.hp>0 && pokemon2.hp>0)
                {

                    for (int i = 0; i <100; i++)
                    {
                        pokemon1 move1 pokemon2;
                        pokemon1 move1 pokemon1;
                        pokemon2 move4 pokemon1;
                        if(pokemon1.hp<0)
                        {
                            print pokemon1.type + " lost the battle";
                            name = pokemon2.name;
                            break;
                        }
                        elseif(pokemon1.hp<0)
                        {
                            print pokemon2.type + " lost the battle";
                            name = pokemon1.name;
                            break;
                        }
                        else
                        {
                            print "Battle continue";
                            continue;
                        }

                    }

                }
                print name + "win the battle!";

                int number = 100;
                pokemon1.hp = number;
                pokemon1.defend = -5;
                pokemon1.attack = 10;
                pokemon1.hp = pokemon1.hp + 150;
                pokemon1.attack = ((pokemon1.attack * 3 - 3) + 2)/2;

                function void healHp(Pokemon pk, Pokemon pk2)
                {
                    if(pk.hp<0)
                    {
                        pk move4 pk;
                    }
                }
                function int addAttack(Pokemon pk)
                {
                    pk.attack += 5;
                    pk.attack -= 5;

                    int number = 10;
                    return number + pk.attack;
                }
                boolean bool = true;
                boolean bool2 = false;

                bool = (bool && bool2) || bool;
                if(!bool)
                {
                    healHp(pokemon1,pokemon2);
                    int attackvalue = addAttack(pokemon1);
                }


                	            """;

        // init the arraylist for tokens and pass the code into the tokenizer
        List<Token> tokens = Tokenizer.tokenize(code);

        // We use this number to record the number of tokens in the list
        // int number = 1;

        // traverse the list and print the tokens' information
        // for(Token t : tokens)
        // {
        // System.out.println("Token: " + t.getValue() + " Type: " + t.getType() + "
        // Number: " + number);
        // number++;
        // }
        System.out.println(Token.toString(tokens));
        Parser parser = new Parser(tokens);
        ProgramNode program = parser.parseProgram();
        System.out.println("Parse SUCCESS: " + program.statements.size() + " statement(s)");

    }

}
