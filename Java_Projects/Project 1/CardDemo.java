public class CardDemo
{
    public static void main(String[] args)
    {
        //instantiates a new object of type CardHands
        CardHands cards = new CardHands();
        //allocates memory for 52 integers in an array for the deck used throughout the app
        int[] array = new int[52];
        //initializes all elements to 0
        cards.initArray(array);
        //prints array
        cards.printHand();
        //assigns random numbers to array
        cards.generateRandomNum(array);
        //sorts all values into 4 hands
        cards.dealCards(array);
        //prints array
        cards.printHand();
        //sorts all values in ascending order per hand
        cards.sortArray();
        //sorts all cards into their playing card values
        cards.sortToFaceValue();
        //prints each value in its suit per hand
        cards.printCardsInHand();
        //sorts all values in descending order
        cards.descendSort();
        //prints descending
        cards.printHand();
    }
}