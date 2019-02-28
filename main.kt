import java.io.File;
import java.io.FileNotFoundException;

class Hashcode {

    fun readFile(filename: String): List<String> = File(filename).readLines()

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("You must give as an argument the name of the file to use.");
            System.exit(1);
        }

        Scanner in = null;
        try {
            in = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("The file " + args[0] + " is not found.");
        }

        for (;;) {
            algorithm(in); // replace with algorithm
            if (!in.hasNext()) break;
        }

        System.out.println("Ran " + nopdone + " calls to shortestPath. No bugs found.");
        System.exit(0);
    }    
    
    public fun algorithm() {
        
    }

}
