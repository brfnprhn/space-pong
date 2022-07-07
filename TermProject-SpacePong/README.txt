--> Image sources/references:

star : https://www.freepik.com/free-vector/golden-stars-collection_787042.htm#page=1&query=star%20icon&position=17

meteorite: https://www.pngrepo.com/svg/216514/meteorite

ufo: https://freeicons.io/free-space-icons/space-ufo-aliens-icon-44048

health: https://www.pngwing.com/en/free-png-yvssc

--------------------------------------------------------------------------------------------------

--> GameTrigger.java contains the main method for this project. There are no command-line arguments necessary. Running this class is sufficient.

--> Game.java contains the main code (and is also the center panel), with the majority of the functionalities.
--> ButtonPanel.java is responsible for creating the buttons and their functionalities in the bottom panel.
--> InfoBoard.java is responsible for updating and displaying the game information in the top panel which consists of the score, lives, time and level.



References & Notes
******************
--> The Java video which was provided in the SpacePong documentation (brick breaker game) was used for the implementations of the animations, the timer, the ball, the paddle and the intersection mechanisms.
--> The implementation of the ball motion was done with the help of the equations given in the documentation (other specific sources were not used).

(not extensively used ones:)
--> Certain sources used to help create the countdown:
https://docs.oracle.com/javase/7/docs/api/javax/swing/Timer.html
https://bip.weizmann.ac.il/course/prog2/tutorial/uiswing/misc/timer.html
--> Source used to find more about GridLayout for the top panel (for design purposes):
https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
--> Sources about displaying images using drawImage:
https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html

Changes
*******
--> Added a health icon to increment the live count by one upon intersection. The live count was decreasing a bit too quickly (with ufo collisions), so I decided to add this icon as well to keep the game going.
--> The paddle slows down the ball (y-component of speed) by a small amount when the ball collides with the paddle, to prevent the speed from getting out of control.
--> The screen size might be different than indicated (for height only) to fit the screen. 
--> Added a reset button which resets the scoreboard and the ball and the paddle (ball and paddle are registered upon starting the game) (not quite at the intended functionality).
--> The "additions" were mentioned in the thread in the discussion board.