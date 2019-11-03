# Alpha Beta Pruning

This program is an alpha-beta pruning search algorithm meant to utilize machine learning in the game of taking stones.

Game Rules
The game starts with n stones numbered 1, 2, 3, ..., n. Players take turns removing one of the remaining
numbered stones. At a given turn there are some restrictions on which numbers (i.e., stones) are legal
candidates to be taken. 

The restrictions are:

• At the first move, the first player must choose an odd-numbered stone that is strictly less than
n/2. For example, if n = 7 (n/2 = 3.5), the legal numbers for the first move are 1 and 3. If n = 6
(n/2 = 3), the only legal number for the first move is 1.

• At subsequent moves, players alternate turns. The stone number that a player can take must be
a multiple or factor of the last move (note: 1 is a factor of all other numbers). Also, this number
may not be one of those that has already been taken. After a stone is taken, the number is saved
as the new last move. If a player cannot take a stone, he/she loses the game.

An example game is given below for n = 7:<br />
Player 1: 3<br />
Player 2: 6<br />
Player 1: 2<br />
Player 2: 4<br />
Player 1: 1<br />
Player 2: 7<br />
Winner: Player 2<br />

## Usage

```bash
java TakeStones <#stones> <#taken_stones> <list_of_taken_stones> <depth>
```
Example, java Player 7 2 3 6 0
You have 7 stones while 2 stones, numbered 3 and 6, have already been taken
it is the Max player’s turn
If depth is 0, search to end game states, otherwise to that depth.
