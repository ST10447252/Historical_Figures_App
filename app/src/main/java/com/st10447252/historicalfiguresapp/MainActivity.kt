package com.st10447252.historicalfiguresapp

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat




class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Background
        val videoView = findViewById<VideoView>(R.id.videoView)
        val videoPath = "android.resource://" + packageName + "/" + R.raw.videofile
        videoView.setVideoURI(Uri.parse(videoPath))

        // This ensures that the VideoView Runs Continuously
        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
        }

        // Video Time frame.
        videoView.start()

        //The world history app begins. (Start)

        //declarations
        val deleteButton = findViewById<ImageButton>(R.id.dlt_Button)
        val matchedButton = findViewById<ImageButton>(R.id.searchedButton)
        val numTextNumber = findViewById<EditText>(R.id.numTextNumber)
        val matchedTextView = findViewById<TextView>(R.id.matchedTextView)
        val resultImageView = findViewById<ImageView>(R.id.resultImageView)
        data class Item(val imageResId: Int, val text: String)

        //Array this is where i group both the image as well as the information of the figures.
        val ageItemMap = mapOf(
            78 to Item(
                R.drawable.hugh_masekela,
                "Hugh Masekela,a South African trumpeter, flugelhornist, cornetist, singer and composer who was described as the father of South African jazz. Masekela was known for his jazz compositions and for writing well-known anti-apartheid songs such as Soweto Blues and Bring Him Back Home."
            ),
            95 to Item(
                R.drawable.nelson_mandela,
                "Nelson Rolihlahla Mandela was a South African anti-apartheid revolutionary, politician, and philanthropist who served as President of South Africa from 1994 to 1999. He was the first black South African to hold the office"
            ),
            26 to Item(
                R.drawable.mac_miller,
                "Mac Miller,an American rapper, singer, songwriter, and record producer from Pittsburgh, Pennsylvania. Miller began his career in Pittsburgh's hip hop scene in 2007, at the age of fifteen. In 2010, he became the first independently distributed debut album to top the US Billboard 200 since 1995."
            ),
            82  to Item(
                R.drawable.pele_pele,
                "Edson Arantes do Nascimento, better known by his nickname Pelé, was a Brazilian professional footballer who played as a forward.Widely regarded as one of the greatest players of all-time, he was among the most successful and popular sports figures of the 20th century. "
            ),
            27 to Item(
                R.drawable.jean_michel,
                "Jean Michel Basquiat began painting graffiti in New York in 1977. He always signed his works with SAMO, which means Same Old Shit. His works came to the attention of the American painter Keith Haring, "
            ),
            41 to Item(
                R.drawable.virgil,
                "Virgil Abloh was an American fashion designer and entrepreneur. He began his own line of luxury streetwear clothing, Pyrex Vision, in 2012, and became the chief executive officer of the Milan-based label Off-White, a fashion house he founded in 2013"
            ),
            43 to Item(
                R.drawable.wakanda,
                "Chadwick Aaron Boseman was an American actor. During his two-decade career, Boseman received several accolades, including two Screen Actors Guild Awards, a Golden Globe Award, and a Primetime Emmy Award, along with an Academy Award nomination"
            ),
            74 to Item(
                R.drawable.muhammed,
                "Muhammad Ali, who has died aged 74, was acclaimed by many as the greatest world heavyweight boxing champion the world has ever seen. He was certainly the most charismatic boxer"
            ),
            50 to Item(
                R.drawable.micheal,
                "King of Pop,, was music royalty – one of its biggest stars and holder, for Thriller, of the record for the best-selling album in history. Eventually, however, his bizarre life-style and personal notoriety eclipsed his talent and his numerous achievements."
            ),
            60 to Item(
                R.drawable.lance,
                "played figures of authority with such panache that no matter how many times he was handed such roles, he never seemed typecast. He is best known on film for his part as Charon, the all-seeing fixer in the John Wick movie franchise, but his image was forged playing two ambitious high-level cops on television, Cedric Daniels in The Wire (2002-08) and Irvin Irving in Bosch (2014-21)."
            )


            )

        //While Loop Begins

        matchedButton.setOnClickListener {

            //While statement, while the app is running
            val age = numTextNumber.text.toString().toIntOrNull()

            //if the age of the number is 0

            if (age == null) {
                matchedTextView.text = "Please enter a Valid age "
            } else {
                //if the age is in between
                if (age < 20 || age > 100) {
                    matchedTextView.text = "Enter a age between 20 and 100"

                    numTextNumber.text.clear()
                } else {
                    // Check if the entered age is correct
                    val correctItem = ageItemMap[age]
                    if (correctItem != null) {
                        // Display Text and Image when match has been met
                        resultImageView.setImageResource(correctItem.imageResId)
                        matchedTextView.text = correctItem.text
                    } else {
                        // When a user inputs an age that doesn't match
                        matchedTextView.text = "Seems Like no one hasn't died at this age"

                    }
                    //Reset once the user clicks this button
                    deleteButton.setOnClickListener {
                        // Clear the text of EditText
                        numTextNumber.text.clear()

                        // Clear the text of TextView
                        matchedTextView.text = ""

                        // Reset the image of ImageView
                        resultImageView.setImageResource(android.R.color.transparent)
                    }





                }
            }
        }
    }
    }

