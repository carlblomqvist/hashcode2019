import java.io.File;
import java.io.FileNotFoundException;


object Hashcode {

    data class Picture(val id: Int, val tags: MutableSet<String>, val orientation: Char)
    data class Slide(val pictures: Set<Picture>)
    data class Slideshow(val nrOfSlides: Int, val slides: List<Slide>)

    fun readFile(filename: String): List<String> = File(filename).readLines()

    var nopdone = 0
    
    var array = ArrayList<Picture>()
    var id = -1;

    var picture1 = Picture(0, mutableSetOf("Söt", "Sweet"), 'H')
    var picture2 = Picture(1, mutableSetOf("Sweet", "Swag"), 'V')
    var slide1 = Slide(setOf(picture1, picture2))
    var slideshow = Slideshow(2, listOf(slide1))


    @JvmStatic fun main(args:Array<String>) {

        // if (args.length != 1) {
        //     System.out.println("You must give as an argument the name of the file to use.");
        //     System.exit(1);
        // }

        val lines = readFile(args[0]);


        for (line in lines) {
            array.add(toPicture(line))
            nopdone++
        }

        System.out.println("Ran " + nopdone + " calls to toPicture.")

        System.out.println("\nSlideshow: \n")
        System.out.println(toString())
        System.exit(0)
    }    

    public fun toPicture(line : String) : Picture  {
        var dontCare = 0
        var orientation = 'N'
        var nrOfTags = 0
        var tags = mutableSetOf<String>()
        var current = line.split("\\s".toRegex())
        for (i in current.indices) {
            if (i == 0) {
                try {
                    dontCare = current[i].toInt()
                } catch (e : NumberFormatException) {
                    orientation = current[i][0]
                }
            } else if (i == 1) {
                nrOfTags = current[i].toInt()
            } else {
                tags.add(current[i])
            }
        }
        val picture = Picture(id, tags, orientation)
        id++
        println(picture)
        return picture
        // println(line)
        // return
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


