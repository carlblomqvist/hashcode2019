import java.io.File;
import java.io.FileNotFoundException;


object Hashcode {
    fun readFile(filename: String): List<String> = File(filename).readLines()

    var nopdone = 0

    var picture1 = Picture(0, setOf("Söt", "Sweet"), 'H')
    var picture2 = Picture(1, setOf("Sweet", "Swag"), 'V')
    var slide1 = Slide(setOf(picture1, picture2))
    var slideshow = Slideshow(2, listOf(slide1))


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

        System.out.println("\nSlideshow: \n")
        System.out.println(toString())
        System.exit(0)
    }    

    public fun algorithm(line : String) {
        println(line)
        return
    }

    override fun toString(): String{
        val str: StringBuilder = StringBuilder()

        str.append(slideshow.nrOfSlides).append("\n")
        for (slide in slideshow.slides){
            for (picture in slide.pictures) {
                str.append(picture.id)
                if (slide.pictures.size > 1)
                    str.append(" ")
            }
            str.append("\n")
        }

        return str.toString()
    }
}


data class Picture(val id: Int, val tags: Set<String>, val orientation: Char)
data class Slide(val pictures: Set<Picture>)
data class Slideshow(val nrOfSlides: Int, val slides: List<Slide>)