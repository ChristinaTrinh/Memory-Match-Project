import java.util.Scanner;
public class MemoryMatch
{    
    public static void main()
    {
          final int NUM_PAIRS = 4;
          int board[] = new int[NUM_PAIRS*2+1];  // ignore the ZERO element spot
          boolean printElement[] = new boolean[NUM_PAIRS*2+1];  // defaulted all to 'false'
          int temp, position1, position2,i;
    
          // fill board will fill with [1,1,2,2,3,3,4,4]
          int k=1;
          for (i=1; i<=NUM_PAIRS*2; i+=2)
          {
            board[i] = k;
            board[i+1] = k; 
            k++;
          }

          // shuffle the board 1000 times
          for (i=1; i<=1000; i++)
          {   // pick 2 random positions to swap
              position1 = (int) (Math.random() * (board.length-1) + 1);
              position2 = (int) (Math.random() * (board.length-1) + 1);   
           
              temp = board[position1];
              board[position1] = board[position2];
              board[position2] = temp;
          }
          
          System.out.print("SHUFFLED:    ");         
          for (i=1; i<board.length; i++)
          {
            System.out.print( "      " + board[i] + "   " );
          }           
          System.out.println("\n\n");
          System.out.print("printElement: ");         
          for (i=1; i<board.length; i++)
          {
            System.out.print( printElement[i] + ",     " );
          }          
          
          // Print out 'board' via 'printElement'
          System.out.println();
          for (i=0; i<board.length; i++)
          {
            if (printElement[i]==true)  // || i==guess1 || i==guess2)
                System.out.print(board[i] +  "  ");
            else
                System.out.print(" * ");
          }
          System.out.println("\n");
          // >>>>>>>>>>>>>>>>>>> ALL Your Code Here  <<<<<<<<<<<<<<<<<<<<<<<

        Scanner console= new Scanner(System.in);
        char ready;
        int count=0;
        int choice1=0, choice2=0;
        boolean match=true;
        boolean error=true;
        int score1=0, score2=0;
        String Player1, Player2;
        System.out.println("Please Enter user name for Player 1: ");
        Player1= console.nextLine();
        System.out.println("Please Enter user name for Player 2: ");
        Player2=console.nextLine();
        while(count<8)
        {
         while(match==true || error==true) //Player 1 loop
         {
         System.out.println("\n"+ Player1+", what is your first choice? ");
         choice1=console.nextInt();
         if(printElement[choice1]==false)
         {
         printElement[choice1]=true;
         count=printBoard(board, printElement, score1, score2, Player1, Player2);//print the board
         if(count==8)
         break;
         System.out.println("\n"+Player1+", what is your second choice?" );
         choice2=console.nextInt();
         if(choice1!=choice2)
         {
         if(printElement[choice2]==false)
         {
         printElement[choice2]=true;
         count=printBoard(board, printElement, score1, score2, Player1, Player2);//print the board
         if(count==8)
         break;
         else if(choice1!=choice2 && board[choice1]!=board[choice2]) //if the two choices are not matched
         {
           System.out.println("NO MATCH! " + Player2 + "'s turn.");
           match=false;
           error=false;
           for(i=1; i<printElement.length; i++)
           printElement[i]=false;
           System.out.println("Are you ready "+Player2+"? (Y/N)");
           ready=console.next().charAt(0);
           if(ready=='Y' || ready== 'y')
           System.out.print("\u000C");
           else if(ready=='N' || ready=='n')
           {
               System.out.println("Thank you for playing!");
               System.exit(0);
           }
         }
         else if(choice1!=choice2 && board[choice1]==board[choice2])//if the two choices are matched, return to main and then come back
           {
            System.out.println("MATCH!! You get to go again!");
            score1++;
           }
         }
         else if(printElement[choice1]==true)
         System.out.println("ERROR! Position already exposed. Try again: "); 
         else if(printElement[choice2]==true)
         System.out.println("ERROR! Position already exposed. Try again: ");
         }
          else if(choice1==choice2)
         System.out.println("ERROR! Positions are the same. Try again: ");
         }
         else if(printElement[choice1]==true)
         System.out.println("ERROR! Position already exposed. Try again: "); 
         else if(printElement[choice2]==true)
         System.out.println("ERROR! Position already exposed. Try again: ");
        
       }
        match=true;
        error=true;
        if(count==8)
        {
            match=false;
            error=false;
            if(score1>score2)
            System.out.println("Congratulations "+Player1+"!!! You won the game!");
            else if(score2>score1)
            System.out.println("Congratulations "+Player2+"!!! You won the game!");
            else
            System.out.println("Game over");
        }
        while(match==true || error==true) //Player 1 loop
        {
        System.out.println("\n"+Player2+", what is your first choice? ");
        choice1=console.nextInt();
        if(printElement[choice1]==false)
        {
        printElement[choice1]=true;
        count=printBoard(board, printElement, score1, score2, Player1, Player2);//print the board
        if(count==8)
          {
            if(score1>score2)
            System.out.println("Congratulations "+Player1+"!!! You won the game!");
            else if(score2>score1)
            System.out.println("Congratulations "+Player2+"!!! You won the game!");
            else
            System.out.println("Game over");
            break;
         }
        System.out.println("\n"+Player2+", what is your second choice?" );
        choice2=console.nextInt();
        if(choice1!=choice2)
         {
        if(printElement[choice2]==false)
        {
        printElement[choice2]=true;
        count=printBoard(board, printElement, score1, score2, Player1, Player2);//print the board
        if(count==8)
        {
            if(score1>score2)
            System.out.println("Congratulations "+Player1+"!!! You won the game!");
            else if(score2>score1)
            System.out.println("Congratulations "+Player2+"!!! You won the game!");
            else
            System.out.println("Game over");
            break;
        }
        if(choice1==choice2 && choice1!=0 && choice2!=0) //if choice are in the same position
        System.out.println("ERROR! Positions are the same. Try again: ");
        else if(choice1!=choice2 && board[choice1]!=board[choice2]) //if the two choices are not matched
        {
          System.out.println("NO MATCH! " + Player1 + "'s turn.");
          match=false;
          error=false;
          for(i=1; i<printElement.length; i++)
              printElement[i]=false; 
           System.out.println("Are you ready "+Player1+"? (Y/N)");
           ready=console.next().charAt(0);
           if(ready=='Y' || ready== 'y')
           System.out.print("\u000C");
           else if(ready=='N' || ready=='n')
           {
               System.out.println("Thank you for playing!");
               System.exit(0);
           }
        }
        else if(choice1!=choice2 && board[choice1]==board[choice2])//if the two choices are matched, return to main and then come back
          {
           System.out.println("MATCH!! You get to go again!");
           score2++;
          }
        }
        else if(printElement[choice1]==true)
        System.out.println("ERROR! Position already exposed. Try again: "); 
        else if(printElement[choice2]==true)
        System.out.println("ERROR! Position already exposed. Try again: ");
        }
        else if(choice1==choice2)//inside
        System.out.println("ERROR! Positions are the same. Try again: ");//inside
        } 
        else if(printElement[choice1]==true)
        System.out.println("ERROR! Position already exposed. Try again: "); 
        else if(printElement[choice2]==true)
        System.out.println("ERROR! Position already exposed. Try again: ");
       }
        match=true;
        error=true;
        }
    }
    
    public static int printBoard(int []a, boolean[]b, int score1, int score2, String Player1, String Player2)
    {
        int count=0;
        //display the player names and current score
        System.out.println("--------------------------------");
        System.out.println("|  Player 1: "+Player1 +"     Score: "+score1+"   |"); 
        System.out.println("|  Player 2: "+Player2 +"     Score: "+score2+"   |");
        System.out.println("--------------------------------");
         
        //display the board
        for(int i=1; i<a.length; i++)
           System.out.print(i+" ");
           
           System.out.println();
        for(int i=1; i<a.length; i++)
        {
           if(b[i]==false)
           System.out.print("* ");
           else if(b[i]==true)
           {
           System.out.print(a[i]+" ");
           count++;
           }
        }
        System.out.println();
        return count;
    }
}
