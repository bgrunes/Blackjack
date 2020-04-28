/*
Title:  Blackjack
Author: Brandon Grunes
Date:   2/4/2020
*/



import java.util.Scanner;

                            // runs program, which plays an abridged version of blackjack
public class Blackjack {
    public static void main(String[] args)
    {
        int playerHand = 0; // variable to keep track of user's card value in current hand
        int dealerHand;    // variable to keep track of dealer AI's card value in current hand
        int turn = 1;
        int numRand;
        double playerWin = 0.0;
        double dealerWin = 0.0;
        double tieGame = 0.0;
        double avgPlayerWin;
        boolean statOpen = false;

                            // uses external random number generator for use of program
        P1Random rng = new P1Random();

        Scanner input = new Scanner(System.in);
        int response;

                            // begins the game until user ends program
        while (true)
        {
            boolean yourTurn = true;

                            // displays that blackjack game has started
            System.out.println("START GAME #" + turn);
            System.out.println(" ");

            while (yourTurn)
            {
                            // generates random number for player
                numRand = rng.nextInt(13) + 1;

                if (!statOpen)
                {
                    if (numRand >= 2 && numRand <= 10)
                    {
                        System.out.println("Your card is a " + numRand + "!");
                        playerHand = playerHand + numRand;
                        System.out.println("Your hand is: " + playerHand);
                        System.out.println(" ");
                    }
                    else if (numRand == 1)
                    {
                        System.out.println("Your card is a ACE!");
                        playerHand = playerHand + numRand;
                        System.out.println("Your hand is: " + playerHand);
                        System.out.println(" ");
                    }
                    else if (numRand == 11)
                    {
                        System.out.println("Your card is a JACK!");
                        playerHand = playerHand + (numRand - 1);
                        System.out.println("Your hand is: " + playerHand);
                        System.out.println(" ");
                    }
                    else if (numRand == 12)
                    {
                        System.out.println("Your card is a QUEEN!");
                        playerHand = playerHand + (numRand - 2);
                        System.out.println("Your hand is: " + playerHand);
                        System.out.println(" ");
                    }
                    else if (numRand == 13)
                    {
                        System.out.println("Your card is a KING!");
                        playerHand = playerHand + (numRand - 3);
                        System.out.println("Your hand is: " + playerHand);
                        System.out.println(" ");
                    }

                    //will check for blackjack
                    if (playerHand == 21)
                    {
                        System.out.println(" ");
                        System.out.println("BLACKJACK! You win!");
                        System.out.println(" ");
                        playerWin++;
                        turn++;
                        playerHand = 0;
                        yourTurn = false;
                        continue;
                    }

                }

                if (playerHand >= 0 && playerHand < 21)    //will check if game should continue, then display menu
                {
                    System.out.println("1. Get another card");
                    System.out.println("2. Hold hand");
                    System.out.println("3. Print statistics");
                    System.out.println("4. Exit");
                    System.out.println(" ");
                    System.out.print("Choose an option: ");
                    response = input.nextInt();

                    statOpen = false;

                    switch (response)
                    {
                        case 1:
                            System.out.println(" ");
                            break;
                        case 2: //moves to dealer's turn
                            dealerHand = rng.nextInt(11) + 16;

                            System.out.println(" ");
                            System.out.println("Dealer's hand: " + dealerHand);
                            System.out.println("Your hand is: " + playerHand);
                            System.out.println(" ");
                            if (dealerHand > 21)
                            {
                                System.out.println("You win!");
                                System.out.println(" ");
                                playerWin++;
                                turn++;
                                playerHand = 0;
                                yourTurn = false;
                            }
                            else if (dealerHand == playerHand)
                            {
                                System.out.println("It's a tie! No one wins!");
                                System.out.println(" ");
                                tieGame++;
                                turn++;
                                playerHand = 0;
                                yourTurn = false;
                            }
                            else if (dealerHand < playerHand)
                            {
                                System.out.println("You win!");
                                System.out.println(" ");
                                playerWin++;
                                turn++;
                                playerHand = 0;
                                yourTurn = false;
                            }
                            else
                                {
                                System.out.println("Dealer wins!");
                                System.out.println(" ");
                                dealerWin++;
                                turn++;
                                playerHand = 0;
                                yourTurn = false;
                            }
                            break;
                        case 3: //shows statistics
                            turn--;
                            avgPlayerWin = playerWin / turn;

                            System.out.println(" ");
                            System.out.println("Number of Player wins: " + (int) playerWin);
                            System.out.println("Number of Dealer wins: " + (int) dealerWin);
                            System.out.println("Number of tie games: " + (int) tieGame);
                            System.out.println("Total # of games played is: " + turn);
                            System.out.println("Percentage of Player wins: " + avgPlayerWin * 100 + "%");
                            System.out.println(" ");

                            turn++;
                            statOpen = true;
                            break;
                        case 4: //exits game
                            return;
                        default:
                            System.out.println("Invalid input!\nPlease enter an integer value between 1 and 4.\n");
                            statOpen = true;
                    }
                }
                else
                    {
                    System.out.println("You exceeded 21! You lose.");
                    System.out.println(" ");
                    dealerWin++;
                    turn++;
                    playerHand = 0;
                    yourTurn = false;
                }
            }
        }
    }
}