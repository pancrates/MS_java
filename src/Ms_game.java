import java.util.*;
public class Ms_game{
  public int state;
  public int display;
  public int size;
  public int mines;
  private String[] stateLabel = {"start","playing","win","loose","error"};
  public UserInput ui = new UserInput();

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

  public void configure_game(){/*Placeholder for config*/}
  public void update_display(){/*placeholder*/}
  public void new_game_message(){/*placeholder*/}
  public int promptUserInput(int err){return 666;}
  public void executeAction(){/*placeholder*/}
  public void setState(int ns){
    if(ns<0 || ns>4){
      System.out.println("set state error");
      this.state=4;
    }
    this.state=ns;
  }
  public void check_for_win(){/*placeholder*/}
  public void showWin(){/*placeholder*/}
  public void showLoose(){/*placeholder*/}
}
