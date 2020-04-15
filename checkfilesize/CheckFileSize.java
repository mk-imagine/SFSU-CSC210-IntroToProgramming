package checkfilesize;
import java.io.File;
import java.util.Scanner;
public class CheckFileSize {
	public static void main(String[] args) throws Exception {
            String fName;
            try (Scanner input = new Scanner(System.in)) {
                System.out.print("Enter name of dictionary file: ");
                fName = "words10683.txt";
            }
		File file = new File(fName);
            try (Scanner scanner = new Scanner(file)) {
                int num = 0;
                while (scanner.hasNextLine()) {
                    String temp = scanner.nextLine();
                    System.out.println(temp);
                    num++;
                }
                System.out.println("Number of entries in file: " + num);
            }
	}
}