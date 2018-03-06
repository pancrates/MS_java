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

  public Ms_game_GUI() {
    frame = new JFrame();
    frame.setTitle("Minesweeper");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    new_game_message();
    configure_game();
    b=new Board_GUI(size,mines);
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
}
