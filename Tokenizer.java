package Tokenizer;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

public class Tokenizer {

	public static List<Token> tokenize(String code) {
		// init the array list for storing the tokens
		List<Token> tokens = new ArrayList<>();
		System.out.println("Code: " + code);
		String startWord = "\\w*";
		Pattern ptStartWord = Pattern.compile(startWord);
		String identifier = "[A-Za-z_]\\w*";
		Pattern ptIdentifier = Pattern.compile(identifier);
		String invalidIdentifier = "[1-9][1-9]*[\\D|_]\\w*";
		Pattern ptInvalidIdentifier = Pattern.compile(invalidIdentifier);
		String integer = "[1-9][1-9]*";
		Pattern ptInteger = Pattern.compile(integer);
		char[] symbolList = new char[22]; // List to encapsulate all symbols
		symbolList[0] = (char) (33);
		symbolList[1] = (char) (34);

		for (int i = 0; i < 10; i++) { // 33, 34, [38, 48] // 11
			symbolList[i + 2] = (char) (i + 38);
		}
		for (int i = 0; i < 4; i++) { // [59, 62]
			symbolList[i + 12] = (char) (i + 59);
		}
		for (int i = 0; i < 3; i++) { // 91, 92, 93
			symbolList[i + 16] = (char) (i + 91);
		}
		for (int i = 0; i < 3; i++) { // 123, 124, 125
			symbolList[i + 19] = (char) (i + 123);
		}
		System.out.println(Arrays.toString(symbolList));

		boolean isSymbol = false;

		try {
			// FileReader Class used
			StringReader stringReader = new StringReader(code);
			int character = 0;
			int nextChar = 0;
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
					System.out.println("Switched " + (char) nextChar);
					character = nextChar;
					secondCharGrabbed = false; // because we now have not grabbed any second char
					secondCharNotUsed = false;
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
					if (character == '=' || character == '|' || character == '&' || character == '!'
							|| character == '<' || character == '>' || character == '/') { // all operators where you
																							// might need something
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
								
								secondCharNotUsed = true;
								continue;
							}
						} else if (character == '/') {
							if (nextChar == '/') {
								tokens.add(new Token("Comment", "//"));
								while (stringReader.read() != (int) '\n') {
								}
								line++;
								continue;
							} else {
								tokens.add(new Token("Operator", "/"));
								secondCharNotUsed = true;
								continue;
							}
						} else if (nextChar == character || (character == '!' && nextChar == '=')) {
							tokens.add(new Token("Boolean Operator",
									Character.toString((char) character).concat(Character.toString((char) nextChar))));
						} else if (character != '=') {
							tokens.add(new Token("Boolean Operator", Character.toString((char) character)));
							
							secondCharNotUsed = true;
							continue;
						} else if (character == '=') {
							tokens.add(new Token("Operator", Character.toString((char) character)));
							
							secondCharNotUsed = true;
							continue;
						} else if (character == '!') {
							if (nextChar == '=') {
								tokens.add(new Token("Boolean Operator", Character.toString((char) character)
										.concat(Character.toString((char) nextChar))));
							} else {
								tokens.add(new Token("Boolean Operator", Character.toString((char) character)));
								
								secondCharNotUsed = true;
								continue;
							}
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
							|| character == '\\'
							|| character == '+'
							|| character == '-') {
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
				} else if (ptStartWord.matcher(Character.toString((char) character)).matches()) { // down path if could
					// classify as an
					// identifier, TODO need to
					// grab all the letters in
					// the word
					StringBuilder wordtest = new StringBuilder();
					StringBuilder word = new StringBuilder();
					word.append((char) character);
					wordtest.append((char) character);
					nextChar = stringReader.read();
					while (ptStartWord.matcher(wordtest.append((char) nextChar)).matches()) {
						word.append((char) nextChar);
						System.out.println(word);
						nextChar = stringReader.read();
					}
					System.out.println();
					System.out.println("Word put in is " + word + ", " + line);

					if (word.toString().equals("boolean")
							|| word.toString().equals("function")
							|| word.toString().equals("void")
							|| word.toString().equals("if")
							|| word.toString().equals("elseif")
							|| word.toString().equals("else")
							|| word.toString().equals("while")
							|| word.toString().equals("for")
							|| word.toString().equals("break")
							|| word.toString().equals("continue")
							|| word.toString().equals("return")
							|| word.toString().equals("print")
							|| word.toString().equals("show")
							|| word.toString().equals("spelldatabase")
							|| word.toString().equals("move1")
							|| word.toString().equals("move2")
							|| word.toString().equals("move3")
							|| word.toString().equals("move4")
							|| word.toString().equals("true")
							|| word.toString().equals("false")
							|| word.toString().equals("String")
							|| word.toString().equals("int")
							|| word.toString().equals("Pokemon")) {
						tokens.add(new Token("Keyword", word.toString()));
					} else if (ptInvalidIdentifier.matcher(word).matches()) {
						System.out.println("INVALID TOKEN " + word + " > ABORTING");
						break;
					} else if (ptIdentifier.matcher(word).matches()){
						tokens.add(new Token("Identifier", word.toString()));
					} else if (ptInteger.matcher(word).matches()){
						tokens.add(new Token("Integer", word.toString()));
					}
					secondCharGrabbed = true;
					secondCharNotUsed = true;
					continue;
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
