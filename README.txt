Assignment 7: MusicEditor Version 2.0
-Made by Richard Tu and Phillip Mann 
-Professor: Amit Shesh

Structure:
We still have our View Interface that contains the initialize method used by all the views including our new view,
the CompositeView. The Composite View displays a GUI of the Music Model, but also has a red line that will move along with
the midi part of the view playing the music. This view also has a lot of KeyBoard and Mouse controls linked to it, allowing
the user to make certain actions/changes by clicking/dragging/pressing keys on the keyboard or buttons on the mouse. We
made a second Panel that extends the first GuiView's Panel we made for Assignment 6 (ConcreteGuiViewPanel). The 
CompositeViewPanel we made supers ConcreteGuiViewPanel and also adds a drawRedLine method that will do as the Assignment
described. We also added a second MidiView Implemenetation, which is similar to the first Implementation, but it uses 
sequencers in place of synthesizers. We made it use Sequencers because Sequencer allows for many functionalities that we
want, such as play, pause, loop (for the extra credit) and has a track containing all the midiNotes. This midiView is used
in the CompositeView in combination with the new Gui to make it all happen.
We added a KeyboardListener class that implements KeyListener. This KeyboardListener contains the methods that maps all the
keys to their respective function. For example, the keytypedMap contains functions to add or remove octaves. 
We also added a MusicBuilder class that contains a MusicModel while representing space to add notes where there currently are no
notes. We made this because the GUI is meant to only show notes that are currently in the piece, but we need to add notes outside of the piece. 
This builder class allows us to visually build a model without being restricted to the current notes in the piece.

Controls (Keyboard & Mouse):
Mouse - 
[Left Click Drag and Release] adds a Note, with the starting beat where the mouse was pressed. To set the duration, 
drag the mouse to where you want the note to end, then release the mouse to add it.
Notes cannot be added outside of the boundaries.

[Right Click] removes a Note from the View. To remove a Note, one must right-click on the starting beat of a Note. If multiple
notes occupy the same starting beat position, then it will remove the note "on top" first. 

Keyboard - [Left Arrow] // [Right Arrow] // Up Arrow] // [Down Arrow] // [Home] // [End] // [q] // [a] // [w] // [s] // [d] //
           [SPACE]
[Left Arrow] - Moves the screen to the left by 80 pixels, or one measure
[Right Arrow] - Moves the screen to the right by 80 pixels, or one measure
[Up Arrow] - Moves the screen in the upwards direction by 120 pixels
[Down Arrow] - Moves the screen in the downwards direction by 120 pixels
[Home] - Moves the screen to the far left, or the beginning of the composition
[End] - Moves the screen to the far right, or the end of the composition
[q] - Adds a full octave of pitches above the current highest Occtave
[a] - Adds a full octave of pitches beloow the current lowest Octave
[w] - Removes the highest octave of pitches
[s] - Removes the lowest octave of pitches
[d] - Adds a Measure, or 4 beats
[SPACE] - Play/Pause the Midi, which in turn starts/stops the red line in the GUI from moving

Changes:
As seen from the previous assignment's Self-Eval's comments, we changed the model of the View to a Read-Only Version of
the music model. This makes sure that nothing is being tinkered with/used beyond what we want the controller/views to use.

Jar:
Jar is run using the command prompt:
1. Navigate to the jar folder using "cd <Folder>"
2. Then type "java -jar Music2.jar" into the command prompt
3. Queries for File Path will come up. Enter either the entire path of a text file or "none" for a blank canvas.

Main:
Our Main still runs our project, but this time, it does not allow for a console/gui/midi view as this composite view is
a combination of all the previous mentioned views. The first and only prompt the Controller will as for is the "File path",
which can be specified with thepath ofa  text file, or with the second option "none", which opens up a blank GUI with 12
beats, and 3 octaves to start with. Use the keyboard to enlarge or shrink it.

Tests:

We tested the KeyHandler in the KeyHandlerTest using mock Runnables
We tested the controller's key-mapped functions in the ControllerTest