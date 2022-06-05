# Grid-Puzzle-Game
 
After finishing an assignment for CSC 115 in which I created a program that navigated, on its own, through a maze using recursion at UVic, I decided that the concept of a game played through the command line on a grid would be interesting and fun to create.

Using only the original concept for converting a grid from a text file to a two dimensional array, I implemented a variety of classes, writing each method from scratch, to allow the user to input commands, either one or several at a time, to navigate through a puzzle.

I plan on adding more levels, new block types, a limit for how many directions can be entered per level, amount of times a user can submit directions per level, and a different platform, with a new user interface and better graphics (either Web or Windows Desktop).


Concepts used include File I/O, recursion, data structures such as stacks and queues, user input, and abstraction.

## To run:
1. Clone or fork this repository to your computer
2. Open your command line and navigate to .../Grid-Puzzle-Game/out/production/Grid-Puzzle-Game/
3. Run the command "java Menu" to begin


## Game Instructions:
Block types:
* a: starting block (user begins each level here)
* x: current block (shows which block the user occupies)
* z: ending block (level is beaten when user navigates to this)
* b: stationary block (user cannot move through this block)
* o: open block (user can move freely through)

Movement:
* WASD: w moves up, a moves left, s moves down, d moves right
* Enter one or more of the available letters at a time then press enter to submit
* Upon receiving a direction, the user moves in a straight line through 'o' blocks until it leaves the grid or contacts another block type
* The game either moves to the next command given, or asks the user to input another if the queue is empty.
