import java.util.*;
public class Ms_game_CL implements Interface_Ms_game{
  public int state;
  public int display;
  public int size;
  public int mines;
  private String[] stateLabel = {"start","playing","win","loose","error"};
  public UserInput ui = new UserInput();
  Scanner reader;
  Board_CL b;

  public Ms_game_CL(){
    this.reader = new Scanner(System.in);
    configure_game();
    this.b = new Board_CL(this.size,this.mines);
    this.game_loop();
  }

  public int game_loop(){
    new_game_message();
    this.state=1;
    while(this.state==1){
      update_display();
      if(0!=promptUserInput(0)) setState(4);
      executeAction();
      if(this.state==1)check_for_win();
    }
    if(this.state==2){
      update_display();
      this.showWin();}
    else if(this.state==3){
      update_display();
      this.showLoose();}
    return 0;
  }
  public void setState(int ns){
    if(ns<0 || ns>4){
      System.out.println("set state error");
      this.state=4;
    }
    this.state=ns;
  }

  public int promptUserInput(int err){
      if(err==1) System.out.println("index out of range try again: ");
      if(err==2) System.out.println("invalid action input try again: ");
      System.out.println("Input: <row> <column> <1/2/3> -> 1=open, 2=flag, 3=deflag");
      ui.row = reader.nextInt();
      ui.col = reader.nextInt(16);
      ui.act = reader.nextInt();
      if(b.validIndex(ui.row) && b.validIndex(ui.col)){
        if(ui.act<1 || ui.act>3) return promptUserInput(2);
        return 0;
      }
      else{
        return promptUserInput(1);
      }
  }
  public void executeAction(){
    //if(ui.act==1) setState(b.openField(ui.row,ui.col));
    if(ui.act==1) setState(b.flood_fill(ui.row,ui.col));
    else if(ui.act==2) b.flagField(ui.row,ui.col);
    else if(ui.act==3) b.deFlagField(ui.row,ui.col);
    else setState(4);
  }
  public void new_game_message(){
    System.out.println("Starting new game "+ b.getbEdge()+"x"+b.getbEdge()+" with "+b.getnMines()+" mines.");
  }
  public void configure_game(){
    this.state=0;
    System.out.println("Enter board size: ");
    this.size = reader.nextInt();
    System.out.println("Enter number of mines: ");
    this.mines = reader.nextInt();
  }
  public void update_display(){
    clearScreen();
    b.printBoard();
  }
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  public void check_for_win() {if(b.all_clear())setState(2);}
  public void showWin(){
    System.out.println("Congrats you won!");
  }
  public void showLoose(){
    System.out.println("Congrats you lost!");
  }
}
