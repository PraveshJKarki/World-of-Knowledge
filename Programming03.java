
/**
 * Programmer : Pravesh Jung Karki
 * Description : The application import the data file which
 * has list of asian countries records in it . At first it
 * will
 * give 4 option to the user and user can select any options
 * . If the user select option 1 then it will display all
 * the records
 * in the data file . For option 2 , it will ask the user
 * which country they would like to see the data for and
 * display accordingly.
 * For option 3 , it will play the quiz with the user .
 * Application will display the random name of the country
 * and it will ask
 * the user to enter the capital city and it will also keep
 * track of thee number of question , coorect answer and
 * incorrect answers.
 * At last for option 4 the application will display the the
 * number of times the data in a selected field occurs in
 * each of ten ranges.
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

class AsianCountries {

	public String name; // to hold the name

	public String currency; // to hold the currencies

	public String capitalCity; // to hold the capital city

	public int population;// to hold the population

	public double gdp; // to hold the gdp
}

public class Programming03 {

	// scanner keyboard for user input .
	public static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		int option; // to hold four options.
		// creating array of records which can hold1 100 data.
		AsianCountries[] countries = new AsianCountries[100];

		// calling loadFile method .
		int size = loadDataFile(countries);

		System.out.println("Welcome to the World of Knowledge. ");
		System.out.print("This simple program  will provide you various ");
		System.out.println("information about different countries in Asia. ");
		System.out.println("The application will have four option for you. ");
		System.out.println("For the 1st option you can display all the data from the file. ");
		System.out.print("For the 2nd option you can can select");
		System.out.println(" any countries data you would like to display. ");
		System.out.println("For the 3rd option you can play quiz and 4th ");
		System.out.println("option will have a histogram. So let's begin... \n");
		do {
			do {
				displayOption();
				System.out.print("Please select one of the option from the list above : ");
				while (!keyboard.hasNextInt()) {
					System.out.println("ERROR: not a valid input ");
					System.out.print("Please ENTER a valid number : ");
					keyboard.next();
				}
				option = keyboard.nextInt();

				if (option < 1 || option > 5) {
					System.out.println("ERROR ! Not a valid number.  ");
				}

			} while (option < 1 || option > 5);

			switch (option) {
			case 1:
				// calling displayDataFile method
				System.out.println();
				System.out.println("Displaying entire data file...... ");
				DisplayDataFile(countries, size);
				break;
			case 2:
				// calling displayKeyField method

				DisplayKeyField(countries, size);
				break;
			case 3:
				// calling playQuiz method
				System.out.println("Press ENTER to begin quiz...");
				keyboard.nextLine();
				keyboard.nextLine();
				playQuiz(countries, size);
				break;
			case 4:
				System.out.println();
				System.out.println("It displays chart for number of times ");
				System.out.println("the data in GDP field has occured in each of ten ranges. ");
				System.out.println();
				// calling displayHistogram method.
				displayHistogram(countries, size);
				break;
			case 5:
				System.out.println("THANK YOU . Program is now Terminating...");
			}
			System.out.println();
		} while (option != 5);

	}

	/**
	 * The method loadDataFile will take countries as a parameter. The method will
	 * go through the file and initialize every element of the record i.e inside an
	 * array from the file. It will return an integer i.e the size of the file.
	 */

	public static int loadDataFile(AsianCountries[] countries) {
		int number = 0;

		try {

			File file = new File("description.txt");
			Scanner inputFile = new Scanner(file);

			do {
				countries[number] = new AsianCountries();
				countries[number].name = inputFile.next();
				countries[number].capitalCity = inputFile.next();
				countries[number].population = inputFile.nextInt();
				countries[number].gdp = inputFile.nextDouble();
				countries[number].currency = inputFile.next();
				number++;
			} while (countries[number - 1].gdp != 0.0);

		} catch (IOException e) {
			System.out.println("Sorry! The file Cannot be access.");
			number = 0;
		}
		return number - 1;

	}

	/**
	 * The method will take countries and a size of the file as a parameter . If
	 * user select option 1 then this method will display all the data in the file
	 * and hold the screen after every 16 data. The method doesnot return any thing.
	 */

	public static void DisplayDataFile(AsianCountries[] countries, int size) {
		getColumnDesign("\t\t\t Countries \t\t\t\t\t  ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%20s  %25s  %20d  %20.2f %20s \n", countries[i].name, countries[i].capitalCity,
					countries[i].population, countries[i].gdp, countries[i].currency);
			if (i % 15 == 0 && i > 0) {
				System.out.println();
				System.out.println("Press ENTER to continue...");
				keyboard.nextLine();
				keyboard.nextLine();
			}

		}

	}

	/**
	 * The method will take countries and a size of the data as a parameter. If the
	 * user enter option 2 , then it will ask the user the name of the country they
	 * would like to get the data for . And as per request it will display all the
	 * elemnts in that record . The method will not return anything.
	 * 
	 */

	public static void DisplayKeyField(AsianCountries[] countries, int size) {
		String name;

		boolean found = false;

		System.out.println();
		System.out.println("Enter the name of the country you would like to get the data for :");
		name = keyboard.next();
		for (int i = 0; i < size && !found; i++) {
			if (countries[i].name.equalsIgnoreCase(name)) {
				found = true;
				getColumnDesign(" ");
				System.out.printf("%10s  %22d  %17.2f %20s\n", countries[i].capitalCity, countries[i].population,
						countries[i].gdp, countries[i].currency);
			} else
				found = false;

		}

		if (!found) {
			System.out.println("No such record found in the file. ");

		}

	}

	/**
	 * The method repeatProcess will take string as a parameter. It will ask the
	 * user if they want to repeat the process again and return boolean .
	 */

	public static boolean repeatProcess(String message) {

		boolean again = false;
		char character;
		System.out.println(message);
		do {

			System.out.print("Enter y for YES ");
			System.out.println("and n for NO:");
			character = keyboard.next().toLowerCase().charAt(0);
			if (character != 'y' && character != 'n') {
				System.out.println("ERROR! not a valid input ");

			}

			if (character == 'y') {
				again = true;
			} else
				again = false;
		} while (character != 'y' && character != 'n');
		return again;

	}

	/**
	 * The method will take countries and the size as a parameter and it will play
	 * the quiz with the user . If the user select option 3, then it will display
	 * the namee of the country and ask the user to guess the capital city of the
	 * country .
	 */

	public static void playQuiz(AsianCountries[] countries, int size) {

		int questionAsked = 0;
		int correctAnswer = 0;
		int incorrectAnswer = 0;
		String guess;
		int i = 0;
		boolean again = false;

		do {
			System.out.println(countries[i].name);
			System.out.println("GUESS ! the capital city of the country displayed above. ");
			questionAsked++;
			guess = keyboard.next();

			if (guess.equalsIgnoreCase(countries[i].capitalCity)) {

				again = repeatProcess("HEY, you got the right answer. Do you like to play again ?");

				correctAnswer++;
			} else {
				System.out.print("SORRY ! Your guess is wrong.");
				System.out.println(" The right answer is " + countries[i].capitalCity);

				again = repeatProcess(" Do you like to play again ? ");

				incorrectAnswer++;
			}

			i++;
		} while (again && i < size);

		if (i >= size) {

			System.out.println("No more countries available . Here is the result... ");
		}

		System.out.println("  F I N A L       R E S U L T ");
		System.out.println(" -------------------------------");
		System.out.println("Questioned asked ====> " + questionAsked);
		System.out.println("Correct answer  ====> " + correctAnswer);
		System.out.println("Incorrect answer ====> " + incorrectAnswer);

	}

	/**
	 * The method will display the four option to bee seleceted for the user. It
	 * will not return anything .
	 */

	public static void displayOption() {
		System.out.println("Enter 1: To display the entire data file");
		System.out.println("Enter 2: To display the specific record in data file");
		System.out.println("Enter 3: To play the quiz ");
		System.out.println("Enter 4: to get the histogram");
		System.out.println("Enter 5: to exit the program\n");
	}

	/**
	 * The method will get the design of the column that is needed to display the
	 * data in a proper order .
	 */

	public static void getColumnDesign(String message) {
		System.out.print("------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
		System.out.println(message + " CapitalCity \t\t\t    Population \t\t\t     GDP \t\t     Currency");
		System.out.print("------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
	}

	/**
	 * The method getMin will take countries and size as a parameter . It will go
	 * through the loop and calculate minimum number from gdp field and return it.
	 */

	public static double getMin(AsianCountries[] countries, int size) {
		double min = countries[0].gdp;
		for (int i = 0; i < size; i++) {
			if (countries[i].gdp < min) {
				min = countries[i].gdp;
			}

		}

		return min;
	}

	/**
	 * The method getMax will take countries and size as a parameter . It will go
	 * through the loop and calculate maximum number from gdp field and return it.
	 */

	public static double getMax(AsianCountries[] countries, int size) {
		double max = countries[0].gdp;
		for (int i = 0; i < size; i++) {
			if (countries[i].gdp > max) {
				max = countries[i].gdp;
			}

		}
		return max;
	}

	/**
	 * The method getDecadeCount will take countries and size as a parameter and it
	 * will create a decadeCount array of size 10 and then with the help of loop w e
	 * will initialize thee array and return it .
	 */
	public static int[] getDecadeCount(AsianCountries[] countries, int size) {

		int number;
		int[] decadeCount = new int[10];
		int dWidth = getDecadeWidth(countries, size);
		double minimum = getMin(countries, size);

		for (int i = 0; i < size; i++) {

			number = (int) ((countries[i].gdp - minimum) / dWidth);

			decadeCount[number]++;
		}
		return decadeCount;
	}

	/**
	 * The method getDecadeWidth will take countries and size as a parameter and it
	 * will call getMin , getMax function inorder to calculate the range and
	 * decadeWidth and also return it from the function.
	 */
	public static int getDecadeWidth(AsianCountries[] countries, int size) {
		int decadeWidth;
		double range;
		double min;
		double max;
		min = getMin(countries, size);
		max = getMax(countries, size);
		range = (max - min);
		return decadeWidth = (int) Math.ceil(range / 10);

	}

	/**
	 * The method will take countries and a size of the data as a parameter. We will
	 * call the decade Width function and call the minimum function and display the
	 * histogram . It will not return anything from the function.
	 */

	public static void displayHistogram(AsianCountries[] countries, int size) {

		int decade;
		int count;
		int decadeWidth;

		decadeWidth = getDecadeWidth(countries, size);
		int[] decadeCount = getDecadeCount(countries, size);
		double minimum = getMin(countries, size);
		decade = (int) minimum;
		System.out.printf("%-10s %-10s %-10s %n", "Decades of GDP", ":", "Count");

		for (int i = 0; i < 10; i++) {
			System.out.printf("%-10s %-15s %-2s  ", decade, "\t\t: ", decadeCount[i]);
			for (int j = 0; j < decadeCount[i]; j++) {
				System.out.print("*");
			}
			decade = (decade + decadeWidth);
			System.out.println();
		}

	}
}
