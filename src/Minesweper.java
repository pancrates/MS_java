import java.util.*;
public class Minesweper{
  private static Interface_Ms_game g ;
  public static void main(String[] args){
    System.out.println("Welcome to minesweeper");
    if(args.length!=1){
      System.out.println("usage: input 1 for Cl");
      return;
    }
    else if(Integer.parseInt(args[0])==1){
      Interface_Ms_game g = new Ms_game_CL();
    }
    else if(Integer.parseInt(args[0])==2){
      Interface_Ms_game g = new Ms_game_GUI();

    }
    else{
      Interface_Ms_game g = new Ms_game_CL();
    }

  }
}
