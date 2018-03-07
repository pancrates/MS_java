import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import static java.lang.System.exit;

public class Ms_game_GUI extends MS_game_Adapter{
  public int state;
  public int size;
  public int mines;
  private String[] stateLabel = {"start","playing","win","loose","error"};
  Board_GUI b;
  JFrame frame;
  Object[] replayOptions = {"Play again",
          "Kill yourself",
          "No eggs, no ham!"};

  public Ms_game_GUI() {
    frame = new JFrame();
    frame.setTitle("Minesweeper");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //////////////
    frame.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent windowEvent){
        //String name = JOptionPane.showInputDialog(mainFramef,"Dont leave I AM ALIVE", null);
        int res=JOptionPane.showConfirmDialog(frame,"Dont leave I AM ALIVE\n Do you have to go?");
        if (res==JOptionPane.YES_OPTION){
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if(res==JOptionPane.NO_OPTION){
          frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
          JOptionPane.showMessageDialog(frame,"Yay ... come play!");
        }
      }
    });
    //////////////
    new_game_message();
    configure_game();
    b=new Board_GUI(this,size,mines);
    frame.add(b.boardContainer);
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void configure_game() {
    boolean k = false;
    while(!k) {
      try {
        int s = Integer.parseInt(JOptionPane.showInputDialog("Input the desired board size\n (lenght of side)"));
        if (s < 4 || s > 30) {
          JOptionPane.showMessageDialog(frame, "Thats not a resonable value try again");
        } else {
          k = true;
          size=s;
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Haha hah ha ... Integer size will do!");
      }
    }
    k = false;
    while(!k) {
      try {
        int m = Integer.parseInt(JOptionPane.showInputDialog("Input the desired number of mines"));
        if (m < 1 || m > size*size-1) {
          JOptionPane.showMessageDialog(frame, "Thats not a resonable value try again");
        } else {
          k = true;
          mines=m;
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Haha hah ha ... Integer size will do!");
      }
    }
    JOptionPane.showMessageDialog(frame, "Good luck");
  }

  @Override
  public void new_game_message() {
    JOptionPane.showMessageDialog(frame, "Welcome to Minesweeper GUI mortal");

  }

  public void restart(){
    b=null;
    frame.getContentPane().removeAll();
    new_game_message();
    configure_game();
    b=new Board_GUI(this,size,mines);
    frame.add(b.boardContainer);
    frame.pack();
    frame.repaint();
    frame.setVisible(true);
  }
  public void replay(){
    int choice = JOptionPane.showOptionDialog(frame,
            "MORE",
            "A Silly Question",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            replayOptions,
            replayOptions[2]);
    if(choice==JOptionPane.YES_OPTION){
      restart();
    }
    else System.exit(0);
  }
}
