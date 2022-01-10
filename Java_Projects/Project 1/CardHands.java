import java.util.*;
public class CardHands
{
    //2 dimensional array to store all 4 hands with 13 cards each
    private int[][] cards = new int[4][13];
    // first array index is "hand", second is "suit", and third is card value
    private int[][][] faceValue = new int[4][4][13];
    //constants to use as indexes for the suit array in the 2nd dimension of the faceValue array
    private final static int CLUBS = 0;
    private final static int DIAMOND = 1;
    private final static int SPADE = 2;
    private final static int HEART = 3;
    
    public void initArray(int[] deck)
    {
        //initializes array to zero but feels redundant since an array initializes an int array to 0 by default
        int x;
        for (x = 0; x < deck.length; ++x)
        {
            deck[x] = 0;
        }
    }
    //this function generates random numbers between 1-52 and assigns them to each hand (13 numbers per hand)
    //accepts an int array
    public void generateRandomNum(int[] deck)
    {
        //takes the passed array and assigns each element a random number (1-52)
        int x, y;
        double placeHolder;
        for (x = 0; x < deck.length; ++x)
        {
            placeHolder = (Math.random() * 52) + 1;
            deck[x] = (int)placeHolder;
            //this for loop checks all previous elements for a repeat and then will reiterate if it does
            for (y = 0; y < x; ++y)
            {
                if (deck[y] == deck[x])
                {
                    placeHolder = (Math.random() * 52) + 1;
                    deck[x] = (int)placeHolder;
                    y = -1;
                }
            }
        }
    }
    //passes the 52 element array with random numbers (1-52), and sorts them into a 2 dimensional array
    //that uses the hand as the first dimension and the card value as the second dimension
    public void dealCards(int[] deck)
    {
        int hand, card;
        //uses a counter variable to increment the deck so it goes straight up the deck for all 4 hands
        int cardCount = 0;
        for (hand = 0; hand < cards.length; ++hand)
            for (card = 0; card < cards[hand].length; ++card)
            {
                cards[hand][card] = deck[cardCount];
                ++cardCount;
            }
    }
    //sorts all hands in ascending order. No arguments or return value
    public void sortArray()
    {
        int x, y, z;
        //creates an array of 13 elements to hold the sorted values in each hand
        int[] array = new int[13];
        for (x = 0; x < cards.length; ++x)
        {
            for (y = 0; y < cards[x].length; ++y)
                array[y] = cards[x][y];
            //once the values are sorted in "array" they are then fed back into that card hand 
            // and then the for loop goes to the next hand and repeats
            Arrays.sort(array);
            for (z = 0; z < array.length; ++z)
            {
                    cards[x][z] = array[z];
            }
        }
    }
    //No arguments or return values. Prints the card field by hand.
    //prints after the hands are all sorted in ascending order
    public void printHand()
    {
        for (int x = 0; x < cards.length; ++x)
        {
            System.out.print("Hand " + (x + 1) + ": ");
            for (int y = 0; y < cards[x].length; ++y)
                System.out.print(cards[x][y] + " ");
            System.out.println();
        }
    }
    //no arguments or return value. Uses the fields directly. This function uses range checks to sort values 
    //into suits and then uses mod13 to convert values to playing card values.
    public void sortToFaceValue()
    {
        //if the values are 26, 39, or 52, then there is no remainder and that value is forced to become 13
        // each if else statement checks for a range and then assigns the value to the third dimension of 
        //the faceValue multi array    
        int x, y;
        for (x = 0; x < cards.length; ++x)
        {
            for (y = 0; y < cards[x].length; ++y)
            {
                if (cards[x][y] > 0 && cards[x][y] < 14)
                {
                    faceValue[x][CLUBS][y] = cards[x][y];
                }
                else
                {
                    if (cards[x][y] > 13 && cards[x][y] < 27)
                    {
                        if (cards[x][y] == 26)
                            faceValue[x][DIAMOND][y] = 13;
                        else
                            faceValue[x][DIAMOND][y] = cards[x][y] % 13;
                    }
                    else
                    {
                        if (cards[x][y] > 26 && cards[x][y] < 40)
                        {
                            if (cards[x][y] == 39)
                                faceValue[x][SPADE][y] = 13;
                            else
                                faceValue[x][SPADE][y] = cards[x][y] % 13;
                        }
                        else
                        {
                            if (cards[x][y] == 52)
                                faceValue[x][HEART][y] = 13;
                            else
                                faceValue[x][HEART][y] = cards[x][y] % 13;
                        }
                    }
                }
            }
        }
    }
    //no arguments or return value. Displays cards in their respective suits per hand.
    public void printCardsInHand()
    {
        //creates an initialization list of face cards.
        String[] faceCards = {"Ace", "J", "Q", "K"};
        int hand, card;
        //for loop controls 4 for loops that each check for each suit. This uses a switch statement so that if a "1, 11, 12 or 13" 
        //comes up, it's corresponding facecard is printed instead. 
        for (hand = 0; hand < faceValue.length; ++hand)
        {
            System.out.println("***Hand " + (hand + 1) + "***\n");
            System.out.print("Clubs: ");
            for (card = 0; card < faceValue[hand][CLUBS].length; ++card)
            {
                switch (faceValue[hand][CLUBS][card])
                {
                    case 0:
                        break;
                    case 1:
                        System.out.print(faceCards[0] + " ");
                        break;
                    case 11:
                        System.out.print(faceCards[1] + " ");
                        break;
                    case 12:
                        System.out.print(faceCards[2] + " ");
                        break;
                    case 13:
                        System.out.print(faceCards[3] + " ");
                        break;
                    default:
                        System.out.print(faceValue[hand][CLUBS][card] + " ");
                        break;
                }
            }
            System.out.println('\n');
            System.out.print("Diamonds: ");
            for (card = 0; card < faceValue[hand][DIAMOND].length; ++card)
            {
                switch (faceValue[hand][DIAMOND][card])
                {
                    case 0:
                        break;
                    case 1:
                        System.out.print(faceCards[0] + " ");
                        break;
                    case 11:
                        System.out.print(faceCards[1] + " ");
                        break;
                    case 12:
                        System.out.print(faceCards[2] + " ");
                        break;
                    case 13:
                        System.out.print(faceCards[3] + " ");
                        break;
                    default:
                        System.out.print(faceValue[hand][DIAMOND][card] + " ");
                        break;
                }
            }
            System.out.println('\n');
            System.out.print("Hearts: ");
            for (card = 0; card < faceValue[hand][HEART].length; ++card)
            {
                switch (faceValue[hand][HEART][card])
                {
                    case 0:
                        break;
                    case 1:
                        System.out.print(faceCards[0] + " ");
                        break;
                    case 11:
                        System.out.print(faceCards[1] + " ");
                        break;
                    case 12:
                        System.out.print(faceCards[2] + " ");
                        break;
                    case 13:
                        System.out.print(faceCards[3] + " ");
                        break;
                    default:
                        System.out.print(faceValue[hand][HEART][card] + " ");
                        break;
                }
            }
            System.out.println('\n');
            System.out.print("Spades: ");
            for (card = 0; card < faceValue[hand][SPADE].length; ++card)
            {
                switch (faceValue[hand][SPADE][card])
                {
                    case 0:
                        break;
                    case 1:
                        System.out.print(faceCards[0] + " ");
                        break;
                    case 11:
                        System.out.print(faceCards[1] + " ");
                        break;
                    case 12:
                        System.out.print(faceCards[2] + " ");
                        break;
                    case 13:
                        System.out.print(faceCards[3] + " ");
                        break;
                    default:
                        System.out.print(faceValue[hand][SPADE][card] + " ");
                        break;
                }
            }
            System.out.println('\n');
        }
    }
    //no arguments or return value. Sorts the values in descending order.
    public void descendSort()
    {
        //sorts the values ascendingly just as before but then uses another 2 dimen-array to reverse the sort.
        int x, y, z;
        int [][] reverseArr = new int[4][13];
        sortArray();
        for (x = 0; x < cards.length; ++x)
        {
            for (y = 0, z = 12; y < cards[x].length; ++y, --z)
                reverseArr[x][y] = cards[x][z];
        }
        for (x = 0; x < cards.length; ++x)
        {
            for (y = 0; y < cards[x].length; ++y)
                cards[x][y] = reverseArr[x][y];
        }
    }
}