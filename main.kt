import java.io.File;
import java.io.FileNotFoundException;


object Hashcode {

    data class Picture(val id: Int, val tags: MutableSet<String>, val orientation: Char) {
        fun toSlide() : Slide {
            return Slide(this)
        }
    }
    data class Slide @JvmOverloads constructor(val picture1: Picture, 
                                               val picture2: Picture? = null)

    // data class Slide(val picture: Picture)
    // data class Slide(val picture1: Picture, val picture2: Picture)
    data class Slideshow(val nrOfSlides: Int, val slides: ArrayList<Slide>)

    fun readFile(filename: String): List<String> = File(filename).readLines()

    var nopdone = 0
    
    var array = ArrayList<Picture>()
    var id = -1;

    var picture1 = Picture(0, mutableSetOf("Söt", "Sweet"), 'H')
    var picture2 = Picture(1, mutableSetOf("Sweet", "Swag"), 'V')
    var slide1 = Slide(picture1, picture2)
    var slideshow = Slideshow(2, arrayListOf(slide1))


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
    
    public fun algorithm() : Slideshow {
        var idx = 0;
        var slides = ArrayList<Slide>()
        for (i in array.indices) {
            if (i == 0) {
                slides.add(array[i].toSlide())
                array.removeAt(0)
            } else {
                for (j in array.indices) {
                   for (tag in array[j].tags) {
                       if (slides[idx].picture1.tags.contains(tag)) {
                           slides.add(array[j].toSlide()) 
                           array.removeAt(j)
                           idx++
                       }
                   }
                }
            }
            
        }
        return Slideshow(idx + 1, slides)
        
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



    // Scoring ! -------------------------------------
    fun nrOfCommonTags(a: Picture, b: Picture): Int = setOf(a.tags.toString() + b.tags.toString()).size

    fun nrOfUniqueInA(a: Picture, b: Picture): Int =
            setOf(a.tags.toString() + b.tags.toString()).size - b.tags.size

    fun nrOfUniqueInB(a: Picture, b: Picture): Int =
            setOf(a.tags.toString() + b.tags.toString()).size - a.tags.size

    fun score(a: Picture, b: Picture): Int =
            minOf(nrOfCommonTags(a, b), nrOfUniqueInA(a, b), nrOfUniqueInB(a, b))

    override fun toString(): String{
        val str: StringBuilder = StringBuilder()

        str.append(slideshow.nrOfSlides).append("\n")
        for (slide in slideshow.slides){
            if (slide.picture2 == null) {
                str.append(slide.picture1.id)
            } else {
                str.append(slide.picture1.id)
                str.append(" ")
                str.append(slide.picture2.id)
            }
            str.append("\n")
        }

        return str.toString()
    }
}
