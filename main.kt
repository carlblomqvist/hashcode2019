import java.io.File;
import java.io.FileNotFoundException;

object Hashcode {

    data class Picture(val id: Int, val tags: MutableSet<String>, val orientation: Char) {
        fun toSlide() : Slide {
            return Slide(this)
        }

        fun equals(other : Picture): Boolean = this.id == other.id
    }

    data class Slide(val picture1: Picture, val picture2: Picture? = null)
    data class Slideshow(val nrOfSlides: Int, val slides: ArrayList<Slide>)

    fun readFile(filename: String): List<String> = File(filename).readLines()

    var nopdone = 0
    
    var array = ArrayList<Picture>()
    var id = 0

    var firstLine = true
    var nrOfPictures: Int = 0

    var picture1 = Picture(0, mutableSetOf("Söt", "Sweet"), 'H')
    var picture2 = Picture(1, mutableSetOf("Sweet", "Swag", "Kul"), 'V')

    @JvmStatic fun main(args:Array<String>) {

        // if (args.length != 1) {
        //     System.out.println("You must give as an argument the name of the file to use.");
        //     System.exit(1);
        // }

        val lines = readFile(args[0])

        for (line in lines) {
            if (firstLine) {
                nrOfPictures = line.toInt()
                firstLine = false
                continue
            }
            array.add(toPicture(line))
            nopdone
        }

        val showtime = runAlgorithmNr(2)

        System.out.println("Ran " + nopdone + " calls to toPicture.")

        System.out.println("\nScore Test between ${picture1.tags} and ${picture2.tags}: " + score(picture1, picture2))

        System.out.println("\nAlgoritm : \n")
        System.out.println(toString(showtime))

        System.exit(0)
    }    
    
    fun algorithm() : Slideshow {
        var idx = 0
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
                           break
                       }
                   }
                }
            }
            
        }
        return Slideshow(idx+1, slides)
    }

    fun runAlgorithmNr(nr : Int) : Slideshow{
        return when (nr) {
            1 -> algorithm()
            2 -> algorithm2(array)
            else -> Slideshow(0, arrayListOf(Slide(picture1)))
        }
    }

    // Algoritm som delar upp H och V bilder
    fun algorithm2(pictures: ArrayList<Picture>) : Slideshow {
        val verticalPictures : ArrayList<Picture> = ArrayList()
        val horizontalPictures : ArrayList<Picture> = ArrayList()
        val slides : ArrayList<Slide> = ArrayList()

        for (picture in pictures)
            if (picture.orientation == 'H')
                horizontalPictures.add(picture)
            else
                verticalPictures.add(picture)

        for (picture in horizontalPictures)
            slides.add(Slide(picture))

        for (index in (0 until verticalPictures.size - 1) step 2) {
            slides.add(Slide(verticalPictures[index], verticalPictures[index + 1]))
        }

        if (verticalPictures.size % 2 != 0)
            slides.add(Slide(verticalPictures.last()))

        return (Slideshow(slides.size, slides))
    }

    fun toPicture(line : String) : Picture  {
        var dontCare = 0
        var orientation = 'N'
        var nrOfTags = 0
        var tags = mutableSetOf<String>()
        var current = line.split("\\s".toRegex())
        for (i in current.indices) {
            if (i == 0) {
                orientation = current[i][0]
            } else if (i == 1) {
                nrOfTags = current[i].toInt()
            } else {
                tags.add(current[i])
            }
        }

        val picture = Picture(id, tags, orientation)
        id++

        return picture
    }

    fun score(a: Picture, b: Picture): Int = minOf(nrOfCommonTags(a, b), nrOfUniqueInA(a, b), nrOfUniqueInB(a, b))

    fun nrOfCommonTags(a: Picture, b: Picture): Int {
        val fullSize: Int = a.tags.size + b.tags.size
        val set = mutableSetOf<String>()

        set.addAll(a.tags + b.tags)

        return fullSize - set.size
    }

    fun nrOfUniqueInA(a: Picture, b: Picture): Int {
        val set = mutableSetOf<String>()

        set.addAll(a.tags + b.tags)

        return set.size - b.tags.size
    }

    fun nrOfUniqueInB(a: Picture, b: Picture): Int{
        val set = mutableSetOf<String>()

        set.addAll(a.tags + b.tags)

        return set.size - a.tags.size
    }

    fun toString(slideshow : Slideshow): String {
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
