package Tokenizer;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Tokenizer {

	public static List<Token> tokenize(String code) {
		// init the array list for storing the tokens
		List<Token> tokens = new ArrayList<>();
		System.out.println("Code: " + code);
		char[] symbolList = new char[30]; // List to encapsulate all symbols
		symbolList[0] = (char) (33);
		symbolList[1] = (char) (34);
		for (int i = 0; i < 19; i++) { // 33, 34, [38, 57] // 21
			symbolList[i + 2] = (char) (i + 38);
		}
		for (int i = 0; i < 3; i++) { // [59, 62]
			symbolList[i + 21] = (char) (i + 59);
		}
		for (int i = 0; i < 3; i++) { // 91, 92, 93
			symbolList[i + 24] = (char) (i + 91);
		}
		for (int i = 0; i < 3; i++) { // 123, 124, 125
			symbolList[i + 27] = (char) (i + 123);
		}
		System.out.println(Arrays.toString(symbolList));

		boolean isSymbol = false;

		try {
			// FileReader Class used
			StringReader stringReader = new StringReader(code);
			int character = 0;
			int nextChar = 0;
			int tempChar = 0;
			int line = 1;
			boolean secondCharNotUsed = false;
			boolean secondCharGrabbed = false;

			while (true) {
				isSymbol = false;
				if (!secondCharGrabbed) {
					character = stringReader.read();
				}
				if (secondCharGrabbed && !secondCharNotUsed) {
					System.out.println("Tripped Second char has been used! Second Char is: " + (char) nextChar);
					character = stringReader.read(); // Because the second char has indeed been used up
					secondCharGrabbed = false;
				}
				if (secondCharGrabbed && secondCharNotUsed) {
					character = nextChar;
				}
				if (character == '\n') { // check new line
					line++;
				}
				for (char symbol : symbolList) {
					if (character == (int) symbol) {
						isSymbol = true;
					}
				}
				if (isSymbol) {
					// if (character == '/') { // still figuring out how to work with this...
					// if (stringReader.read() == '/') {
					// tokens.add(new Token("Comment", "//"));
					// } else {
					// tokens.add(new Token("Operator", "/"));
					// }
					// }
					if (character == '=' || character == '|' || character == '&' || character == '!'
							|| character == '<' || character == '>') { // all operators where you might need something
																		// after it
						if (!secondCharNotUsed) {
							nextChar = stringReader.read(); // TODO could maybe have this flip a boolean so it lets the
															// compiler know to look at next character
							secondCharGrabbed = true;
						}
						if (character == '>' || character == '<' || character == '+' || character == '-') {
							if (nextChar == '=') {
								tokens.add(new Token("Comparison Operator", Character.toString((char) character)
										.concat(Character.toString((char) nextChar))));
							} else {
								tokens.add(new Token("Comparison Operator", Character.toString((char) character)));
								tempChar = nextChar;
								secondCharNotUsed = true;
								continue;
							}
						} else if (nextChar == character || (character == '!' && nextChar == '=')) {
							tokens.add(new Token("Boolean Operator",
									Character.toString((char) character).concat(Character.toString((char) nextChar))));
						} else if (character != '=') {
							tokens.add(new Token("Boolean Operator", Character.toString((char) character)));
							tempChar = nextChar;
							secondCharNotUsed = true;
							continue;
						} else if (character == '=') {
							tokens.add(new Token("Operator", Character.toString((char) character)));
							tempChar = nextChar;
							secondCharNotUsed = true;
							continue;
						} else if (character == '!') {
							if (nextChar == '=') {
								tokens.add(new Token("Boolean Operator", Character.toString((char) character)
										.concat(Character.toString((char) nextChar))));
							} else {
								tokens.add(new Token("Boolean Operator", Character.toString((char) character)));
								tempChar = nextChar;
								secondCharNotUsed = true;
								continue;
							}
						} else if (character == '(' // all operators where it cant be anything but just the single element
						|| character == ')'
						|| character == '"'
						|| character == '['
						|| character == ']'
						|| character == '{'
						|| character == '}'
						|| character == '.'
						|| character == ';'
						|| character == '*'
						|| character == ','
						|| character == '\''
						|| character == '\\') {
					tokens.add(new Token("Operator", Character.toString((char) character)));
					secondCharGrabbed = false;
				} else if (character == '.'
						|| character == ';'
						|| character == ','
						|| character == '\''
						|| character == '\\') {
					tokens.add(new Token("Punctuator", Character.toString((char) character)));
					secondCharGrabbed = false;
				}
						continue;
					} else if (character == '(' // all operators where it cant be anything but just the single element
						|| character == ')'
						|| character == '"'
						|| character == '['
						|| character == ']'
						|| character == '{'
						|| character == '}'
						|| character == '.'
						|| character == ';'
						|| character == '*'
						|| character == ','
						|| character == '\''
						|| character == '\\') {
					tokens.add(new Token("Operator", Character.toString((char) character)));
					secondCharGrabbed = false;
				} else if (character == '.'
						|| character == ';'
						|| character == ','
						|| character == '\''
						|| character == '\\') {
					tokens.add(new Token("Punctuator", Character.toString((char) character)));
					secondCharGrabbed = false;
				}
				} 
				secondCharNotUsed = false;
				secondCharGrabbed = false;
				if (character == -1) {
					break;
				}
			}
			System.out.println("Number of lines detected: " + line);
			stringReader.close();

		} catch (IOException e) {
			System.out.println("Error occured while reading file: " + e.getMessage());
		}
		return tokens;
	}

}
