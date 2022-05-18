package com.example.actividad2

import androidx.datastore.core.DataStore
import com.github.ajalt.timberkt.d
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class Model(private val moviesDataStore: DataStore<MovieStore>) {

    companion object {
        val MOVIES = """
           [
               {
                   "id": 0,
                   "name": "Frozen II",
                   "release": "2019",
                   "playtime": "1 h 43 min",
                   "description": "Frozen II, also known as Frozen 2, is a 2019 American 3D computer-animated musical fantasy film produced by Walt Disney Animation Studios. The 58th animated film produced by the studio, it is the sequel to the 2013 film Frozen and features the return of directors Chris Buck and Jennifer Lee, producer Peter Del Vecho, songwriters Kristen Anderson-Lopez and Robert Lopez, and composer Christophe Beck. Lee also returns as screenwriter, penning the screenplay from a story by her, Buck, Marc E. Smith, Anderson-Lopez, and Lopez,[2] while Byron Howard executive-produced the film.[a][1] Veteran voice cast Kristen Bell, Idina Menzel, Josh Gad, Jonathan Groff, and Ciarán Hinds return as their previous characters, and they are joined by newcomers Sterling K. Brown, Evan Rachel Wood, Alfred Molina, Martha Plimpton, Jason Ritter, Rachel Matthews, and Jeremy Sisto.",
                   "plot": "King Agnarr of Arendelle tells a story to his young children, Elsa and Anna, that their grandfather, King Runeard, established a treaty with the neighboring tribe of Northuldra by building a dam in their homeland, the Enchanted Forest. However, a fight occurs, resulting in Runeard's death. The battle enrages the elemental spirits of Earth, Fire, Water, and Air of the forest. The spirits disappear and a wall of mist traps everyone in the Enchanted Forest. Young Agnarr barely escapes due to the help of an unknown savior.",
                   "poster": "https://user-images.githubusercontent.com/24237865/75087936-5c1d9f80-553e-11ea-81d3-a912634dd8f7.jpg",
                   "gif": "https://media2.giphy.com/media/aQYR1p8saOQla/giphy.gif?cid=ecf05e4701sln9u63lr3z17lh5f3n3h3owrk54zh1183hqmi&rid=giphy.gif&ct=g"
               },
               {
                   "id": 1,
                   "name": "Toy Story 4",
                   "release": "2019",
                   "playtime": "1 h 40 min",
                   "description": "Toy Story 4 is a 2019 American computer-animated comedy film produced by Pixar Animation Studios for Walt Disney Pictures. It is the fourth installment in Pixar's Toy Story series and the sequel to Toy Story 3 (2010). It was directed by Josh Cooley (in his feature directorial debut) from a screenplay by Andrew Stanton and Stephany Folsom; the three also conceived the story alongside John Lasseter, Rashida Jones, Will McCormack, Valerie LaPointe, and Martin Hynes.[2] Tom Hanks, Tim Allen, Annie Potts, Joan Cusack, Wallace Shawn, John Ratzenberger, Estelle Harris, Blake Clark, Bonnie Hunt, Jeff Garlin, Kristen Schaal and Timothy Dalton reprise their character roles from the first three films. They are joined by Tony Hale, Keegan-Michael Key, Jordan Peele, Christina Hendricks, Keanu Reeves, and Ally Maki, who voice the new characters. The film also posthumously features Don Rickles, who appears through use of archived voice recordings.",
                   "plot": "Nine years earlier, following the events of Toy Story 2, Bo Peep and Woody attempt to rescue RC, Andy's remote-controlled car, from a rainstorm. Just as they finish the rescue, Woody watches as Bo is donated to a new owner, and considers going with her, but ultimately decides to remain with Andy. Years later, a teenage Andy donates them to Bonnie, a younger child, before he goes off to college. While the toys are grateful to have a new child, Woody struggles to adapt to an environment where he is not the favorite as he was with Andy, apparent when Bonnie takes Woody's sheriff badge and puts it on Jessie instead, not even bothering to give him a role during her playtime.",
                   "poster": "https://user-images.githubusercontent.com/24237865/75087934-5a53dc00-553e-11ea-94f1-494c1c68a574.jpg",
                   "gif": "https://media0.giphy.com/media/sgswHaZw5yklq/giphy.gif?cid=ecf05e473wd424bhp29tw5i4clg60djlvz4aridyzgxua96q&rid=giphy.gif&ct=g"
               },
               {
                   "id": 2,
                   "name": "Zootopia",
                   "release": "2016",
                   "playtime": "1 h 50 min",
                   "description": "Zootopia (titled Zootropolis in the UK and Ireland)[6] is a 2016 American 3D computer-animated comedy film[7] produced by Walt Disney Animation Studios and released by Walt Disney Pictures. It is the 55th Disney animated feature film, directed by Byron Howard and Rich Moore, co-directed by Jared Bush, and stars the voices of Ginnifer Goodwin, Jason Bateman, Idris Elba, Jenny Slate, Nate Torrence, Bonnie Hunt, Don Lake, Tommy Chong, J. K. Simmons, Octavia Spencer, Alan Tudyk, and Shakira. It details the unlikely partnership between a rabbit police officer and a red fox con artist, as they uncover a criminal conspiracy involving the disappearance of predators.",
                   "plot": "In a world populated by anthropomorphic mammals, rabbit Judy Hopps from rural Bunnyburrow fulfills her childhood dream of becoming a police officer in urban Zootopia. Despite Judy being the academy valedictorian, Chief Bogo doubts her potential and delegates her to parking duty. On her first day, she is hustled by a con artist fox duo, Nick Wilde and Finnick.",
                   "poster": "https://user-images.githubusercontent.com/24237865/75087937-5c1d9f80-553e-11ea-8fc9-a7e520addde0.jpg",
                   "gif": "https://media0.giphy.com/media/mHcEcam5FtKQE/giphy.gif?cid=ecf05e473an8u0cwv7mao8mm6hxb8hsf62l34p0zblcfmmwp&rid=giphy.gif&ct=g"
               },
               {
                   "id": 3,
                   "name": "Bambi",
                   "release": "1942",
                   "playtime": "1 h 10 min",
                   "description": "Bambi is a 1942 American animated film directed by David Hand (supervising a team of sequence directors), produced by Walt Disney and based on the 1923 book Bambi, a Life in the Woods by Austrian author Felix Salten. The film was released by RKO Radio Pictures on August 13, 1942, and is the fifth Disney animated feature film.",
                   "plot": "A doe gives birth to a fawn named Bambi, who will one day take over the position of Great Prince of the Forest, a title currently held by Bambi's father, who guards the woodland creatures against the dangers of hunters. The fawn is quickly befriended by an eager, energetic rabbit named Thumper, who helps to teach him to walk and speak. Bambi grows up very attached to his mother, with whom he spends most of his time. He soon makes other friends, including a young skunk named Flower and a female fawn named Faline. Curious and inquisitive, Bambi frequently asks about the world around him and is cautioned about the dangers of life as a forest creature by his loving mother. One day out in a meadow, Bambi briefly sees The Great Prince but does not realize that he is his father. As the Great Prince wanders uphill, he discovers the human hunter named Man by all the animals is coming and rushes down to the meadow to get everyone to safety. Bambi is briefly separated from his mother during that time but is escorted to her by the Great Prince as the three of them make it back in the forest just as Man fires his gun.",
                   "poster": "https://user-images.githubusercontent.com/24237865/75087801-a56cef80-553c-11ea-9ae5-cf203c6ea8c2.jpg",
                   "gif": "https://media3.giphy.com/media/52SvdzXWPjdOo/giphy.gif?cid=ecf05e47uuv6gjcgscfqe3ec5bc6edasuv2e36278j870i4n&rid=giphy.gif&ct=g"
               },
               {
                   "id": 4,
                   "name": "Coco",
                   "release": "2017",
                   "playtime": "1 h 49 min",
                   "description": "Coco is a 2017 American 3D computer-animated fantasy film produced by Pixar Animation Studios and released by Walt Disney Pictures. Based on an original idea by Lee Unkrich, it is directed by him and co-directed by Adrian Molina. The film's voice cast stars Anthony Gonzalez, Gael García Bernal, Benjamin Bratt, Alanna Ubach, Renée Victor, Ana Ofelia Murguía and Edward James Olmos. The story follows a 12-year-old boy named Miguel who is accidentally transported to the Land of the Dead, where he seeks the help of his deceased musician great-great-grandfather to return him to his family among the living and to reverse his family's ban on music.",
                   "plot": "In Santa Cecilia, Mexico, Miguel dreams of becoming a musician, even though his family strictly forbids it. His great-great-grandmother Imelda was married to a man who left her and their daughter Coco to pursue a career in music, and when he never returned, Imelda banished music from her family's life before starting a shoemaking business. Miguel now lives with the elderly Coco and their family, including Miguel's parents and grandmother, who are all shoemakers. He idolizes famous musician Ernesto de la Cruz, and secretly teaches himself to play guitar from Ernesto's old films. On the Day of the Dead, Miguel accidentally damages the picture frame that holds a photo of Coco with her mother on the family ofrenda, discovering that a hidden section of the photograph shows his great-great-grandfather holding Ernesto's famous guitar. Though the image's face has been torn off, Miguel concludes that Ernesto is his great-great-grandfather. Ignoring his family's objections, he decides to enter a Day of the Dead talent show.",
                   "poster": "https://user-images.githubusercontent.com/24237865/75088277-dea85e00-5542-11ea-961b-7f0942cd8f47.jpg",
                   "gif": "https://media3.giphy.com/media/KFcACgXTXkzKAGDZ05/giphy.gif?cid=ecf05e47ku475ue16rb920a6ykl6roi9l0odvqo00flmikvs&rid=giphy.gif&ct=g"
               },
               {
                   "id": 5,
                   "name": "The Lion King",
                   "release": "2019",
                   "playtime": "1 h 58 min",
                   "description": "The Lion King is a 2019 American animated musical film directed and produced by Jon Favreau, written by Jeff Nathanson, and produced by Walt Disney Pictures. It is a photorealistic computer-animated remake of Disney's traditionally animated 1994 film of the same name. The film stars the voices of Donald Glover, Seth Rogen, Chiwetel Ejiofor, Alfre Woodard, Billy Eichner, John Kani, John Oliver, Florence Kasumba, Eric Andre, Keegan-Michael Key, JD McCrary, Shahadi Wright Joseph, and Beyoncé Knowles-Carter, as well as James Earl Jones reprising his role from the original film. The plot follows Simba, a young lion who must embrace his role as the rightful king of his native land following the murder of his father, Mufasa, at the hands of his uncle, Scar.",
                   "plot": "In the Pride Lands of Africa, a pride of lions rule over the animal kingdom from Pride Rock. King Mufasa's and Queen Sarabi's newborn son, Simba, is presented to the gathering animals by Rafiki the mandrill, the kingdom's shaman and advisor. Mufasa shows Simba the Pride Lands and explains to him the responsibilities of kingship and the circle of life, which connects all living things. Mufasa's younger brother, Scar, covets the throne and plots to eliminate Mufasa and Simba, so he may become king. He tricks Simba and his best friend Nala (to whom it is expected Simba will marry) into exploring a forbidden elephants' graveyard, where they are attacked by spotted hyenas led by the ruthless Shenzi. Mufasa is alerted about the incident by his majordomo, the hornbill Zazu, and rescues the cubs. Though upset with Simba, Mufasa forgives him and explains that the great kings of the past watch over them from the night sky, from which he will one day watch over Simba. Meanwhile, Scar visits the hyenas and manages to convince them to help him overthrow Mufasa in exchange for hunting rights in the Pride Lands.",
                   "poster": "https://user-images.githubusercontent.com/24237865/75087978-cd5d5280-553e-11ea-8e31-aabede902d59.jpg",
                   "gif": "https://media3.giphy.com/media/26tPpStnC0XthM19u/giphy.gif?cid=ecf05e471xnhh2e78pvhmgegmc1y27f06qcfvbg326p98gup&rid=giphy.gif&ct=g"
               },
               {
                   "id": 6,
                   "name": "Finding Dory",
                   "release": "2016",
                   "playtime": "1 h 45 min",
                   "description": "Finding Dory is a 2016 American 3D computer-animated adventure film produced by Pixar Animation Studios and released by Walt Disney Pictures. Directed by Andrew Stanton with co-direction by Angus MacLane,[5][6] the screenplay was written by Stanton and Victoria Strouse.[7] The film is a sequel/spinoff[8][9] to 2003's Finding Nemo and features the returning voices of Ellen DeGeneres and Albert Brooks, with Hayden Rolence (replacing Alexander Gould), Ed O'Neill, Kaitlin Olson, Ty Burrell, Diane Keaton, and Eugene Levy joining the cast. The film focuses on the amnesiac fish Dory, who journeys to be reunited with her parents.[10]",
                   "plot": "Dory, a regal blue tang, gets separated from her parents, Jenny and Charlie, as a child. As she grows up, Dory attempts to search for them, but gradually forgets them due to her short-term memory loss. In a flashback (from Finding Nemo), she joins Marlin – a clownfish looking for his missing son Nemo – after accidentally swimming into him.",
                   "poster": "https://user-images.githubusercontent.com/24237865/75088201-0ba84100-5542-11ea-8587-0c2823b05351.jpg",
                   "gif": "https://media1.giphy.com/media/yUt0xuAPgFpSM/giphy.gif?cid=ecf05e47xl77vojzdfyfsq05jj6lok315g8vdhbwpedj1ja6&rid=giphy.gif&ct=g"
               },
               {
                   "id": 7,
                   "name": "Alice Through the Looking Glass",
                   "release": "2016",
                   "playtime": "1 h 53 min",
                   "description": "Alice Through the Looking Glass is a 2016 American fantasy adventure film directed by James Bobin, written by Linda Woolverton and produced by Tim Burton, Joe Roth, Suzanne Todd, and Jennifer Todd. It is based on the characters created by Lewis Carroll and is the sequel to the 2010 film Alice in Wonderland, a live-action reimagining of Disney's 1951 animated film of the same name. The film stars Johnny Depp, Anne Hathaway, Mia Wasikowska, Matt Lucas, Rhys Ifans, Helena Bonham Carter, and Sacha Baron Cohen and features the voices of Stephen Fry, Michael Sheen, Timothy Spall, and Alan Rickman in his final film role.",
                   "plot": "Alice Kingsleigh has spent the past three years following her father's footsteps and sailing the high seas. Upon her return to London from China, she learns that her ex-fiancé, Hamish Ascot, has taken over his deceased father's company and plans to have Alice sign over her father's ship in exchange for her family home. Alice follows a butterfly she recognizes as the Caterpillar and returns to Wonderland through a mirror. Alice is greeted by the White Queen, the White Rabbit, the Tweedles, the Dormouse, the March Hare, the Bloodhound and the Cheshire Cat. They inform her that the Mad Hatter is acting madder than usual because his family is missing. Alice tries to console him, but he remains certain that his family survived the attack of the Jabberwocky. The White Queen, believing that finding the Hatter's family is the only way to restore his health, sends Alice to consult Time and convince him to save the Hatter's family in the past. The White Queen warns Alice that history will be destroyed if a person's past and present selves meet. Upon entering the Castle of Eternity, Alice finds the Chronosphere, an object that controls all of time in Wonderland.",
                   "poster": "https://user-images.githubusercontent.com/24237865/75088202-0d720480-5542-11ea-85f3-8726e69a9a26.jpg",
                   "gif": "https://media3.giphy.com/media/Y713FQ3mfWDWU/giphy.gif?cid=ecf05e47ksc6l561mp74ljpctaox6u0hkzv0pzap9kt9653g&rid=giphy.gif&ct=g"
               }
           ]
        """.trimIndent()
    }

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    private val _movies = MutableStateFlow<List<Movie>>(listOf())
    val movies = _movies as StateFlow<List<Movie>>

    init {
        coroutineScope.launch {
            //TODO 10 use the moviesDataSTore as will (with coroutines)
            moviesDataStore.data
                .map { it.initialized }
                .filter { !it }
                .first {
                    d { "Initialize data store..." }
                    initDataStore()
                    return@first true
                }
        }

        coroutineScope.launch {
            moviesDataStore.data
                .collect { movieStore ->
                    d { "Movies count: ${movieStore.moviesCount}" }
                    val movies = movieStore.moviesList.map {
                        Movie(it.id, it.name, it.release, it.posterUrl)
                    }
                    _movies.value = movies
                }
        }

    }

    private fun initDataStore() {
        // create moshi parser
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, Movie::class.java)
        val adapter = moshi.adapter<List<Movie>>(type)

        // read the json
        val moviesFromJson: List<Movie> = adapter.fromJson(Model.MOVIES)!!

        // create the storedMovies list
        val moviesToStore = moviesFromJson.map { it.asStoredMovie() }

        // save data to data store
        coroutineScope.launch {
            moviesDataStore.updateData { movieStore ->
                movieStore.toBuilder()
                    .addAllMovies(moviesToStore)
                    .setInitialized(true)
                    .build()
            }
        }

    }

    fun removeMovie(movie: Movie) {
        val toStoreMovies = movies.value
            .filter { it.id != movie.id }
            .map { it.asStoredMovie() }

        coroutineScope.launch {
            moviesDataStore.updateData { movieStore ->
                movieStore.toBuilder()
                    .clearMovies()
                    .addAllMovies(toStoreMovies)
                    .build()
            }
        }
    }

}