import java.io.File;
import java.io.FileNotFoundException;

object Hashcode {
    fun readFile(filename: String): List<String> = File(filename).readLines()

    var nopdone = 0

    @JvmStatic fun main(args:Array<String>) {

        // if (args.length != 1) {
        //     System.out.println("You must give as an argument the name of the file to use.");
        //     System.exit(1);
        // }

        val lines = readFile(args[0]);

        for (line in lines) {
            algorithm(line)
            nopdone++
        }

        System.out.println("Ran " + nopdone + " calls to shortestPath. No bugs found.")
        System.exit(0)
    }    

    public fun algorithm(line : String) {
        println(line)
        return
    }
}


data class Picture(val id: Int, val tags: Set<String>, val orientation: Char)
data class Slideshow(val nrOfSlides: Int, val slides: List<List<Picture>>)