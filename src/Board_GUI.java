import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
private int bEdge;
private int nMines;
private int[][] posValue;
private int[][] posVisi;
*/

public class Board_GUI extends Board {
  JPanel boardContainer;
  JPanel boardgrid;
  JButton[][] gridButtons;
  Ms_game_GUI g;
  int state=0;

  /////////////////////////////////
  /*public static void main(String[] args) throws Exception {
    JFrame frame = new JFrame();
    frame.setTitle("Minesweeper");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Board_GUI b = new Board_GUI(10,3);
    frame.add(b.boardContainer);
    frame.pack();
    frame.setVisible(true);
  }
*/
  ////////////////////////////////

  public Board_GUI(Ms_game_GUI parent, int size, int mines) {
    super(size, mines);
    g=parent;
    formBoardDisplay();
  }


  public void formBoardDisplay() {
    boardgrid = new JPanel();
    boardContainer = new JPanel();
    boardgrid.setLayout(new GridLayout(getbEdge(), getbEdge()));
    gridButtons = new JButton[getbEdge()][getbEdge()];
    int i, j, n = getbEdge();
    for (i = 0; i < n; i++) {
      for (j = 0; j < n; j++) {
        gridButtons[i][j] = new JButton("");
        gridButtons[i][j].setMargin(new Insets(0, 0, 0, 0));
        gridButtons[i][j].setContentAreaFilled(false);
        gridButtons[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
        gridButtons[i][j].setActionCommand("open");
        gridButtons[i][j].putClientProperty("state", 0);
        gridButtons[i][j].putClientProperty("col", i);
        gridButtons[i][j].putClientProperty("row", j);
        gridButtons[i][j].addMouseListener(new MSButtonListener());
        boardgrid.add(gridButtons[i][j]);

      }
    }
    boardgrid.setPreferredSize(new Dimension(25 * getbEdge(), 25 * getbEdge()));
    boardContainer.add(boardgrid);
  }

  private class MSButtonListener extends MouseAdapter {

    public void flod_fill_GUI(int x,int y){
      int val =getposValue(x,y);

        if(validIndex(x + 1) && validIndex(y) && (getposVisi(x + 1, y) != 1)) {
        gridButtons[x+1][y].dispatchEvent(new MouseEvent(gridButtons[x+1][y], MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false, 1));
        }
        if(validIndex(x-1)&&validIndex(y)&& (getposVisi(x - 1, y) != 1)) {
          gridButtons[x - 1][y].dispatchEvent(new MouseEvent(gridButtons[x - 1][y], MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false, 1));
        }
        if(validIndex(x)&&validIndex(y+1)&& (getposVisi(x, y+1) != 1)) {
          gridButtons[x][y+1].dispatchEvent(new MouseEvent(gridButtons[x][y+1], MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false, 1));
        }
        if(validIndex(x)&&validIndex(y-1)&& (getposVisi(x , y-1) != 1)) {
          gridButtons[x][y-1].dispatchEvent(new MouseEvent(gridButtons[x][y-1], MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false, 1));
        }
        if(validIndex(x+1)&&validIndex(y+1)&& (getposVisi(x + 1, y+1) != 1)) {
          gridButtons[x + 1][y+1].dispatchEvent(new MouseEvent(gridButtons[x + 1][y+1], MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false, 1));
        }
        if(validIndex(x+1)&&validIndex(y-1)&& (getposVisi(x + 1, y-1) != 1)) {
          gridButtons[x + 1][y-1].dispatchEvent(new MouseEvent(gridButtons[x + 1][y-1], MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false, 1));
        }
        if(validIndex(x-1)&&validIndex(y+1)&& (getposVisi(x - 1, y+1) != 1)) {
          gridButtons[x - 1][y+1].dispatchEvent(new MouseEvent(gridButtons[x - 1][y+1], MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false, 1));
        }
        if(validIndex(x-1)&&validIndex(y-1)&& (getposVisi(x - 1, y-1) != 1)) {
          gridButtons[x - 1][y-1].dispatchEvent(new MouseEvent(gridButtons[x - 1][y-1], MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false, 1));
        }
    }

    String tempFormat = "State %d \n Position (%d,%d)";
    public void mouseClicked(MouseEvent mouseEvent) {
      JButton bc= (JButton) mouseEvent.getSource();
      int state = (int) bc.getClientProperty("state");
      int x = (int) bc.getClientProperty("col");
      int y = (int) bc.getClientProperty("row");

      if (SwingUtilities.isRightMouseButton(mouseEvent)) {
        if(state==0){
          flagField(x,y);
          bc.putClientProperty("state",2);
          bc.setText("F");
        }
        else if(state==2){
          deFlagField(x,y);
          bc.putClientProperty("state",0);
          bc.setText("");
        }
        else if(state==1){
          JOptionPane.showMessageDialog(boardgrid, "Cant flag an open field" );
        }
        else{
          JOptionPane.showMessageDialog(boardgrid, "ERROR INVALID STATE ON RIGHT CLICK" );
        }
        //String rcf= "Right Click "+"State "+state+ "\nPosition ("+x+","+y+")";
        //JOptionPane.showMessageDialog(boardgrid, rcf );
      }
      else if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
        if(state==0||state==2){
          deFlagField(x,y);
          bc.putClientProperty("state",1);
          bc.setEnabled(false);
          if(openField(x,y)==3){
            bc.setText("M");
            int i, j, n = getbEdge();
            for (i = 0; i < n; i++) {
              for (j = 0; j < n; j++) {
                int val =getposValue(i,j);
                if(val!=9) gridButtons[i][j].setText(""+val);
                else gridButtons[i][j].setText("M");
              }
            }
            JOptionPane.showMessageDialog(boardgrid, "You Lost");
            g.replay();
          }
          else{
            bc.setText(""+getposValue(x,y));
            if(getposValue(x,y)==0) flod_fill_GUI(x,y);
          }
        }
        else if(state==1){
          //JOptionPane.showMessageDialog(boardgrid, "Cant open an open field" );
        }
        else{
          JOptionPane.showMessageDialog(boardgrid, "ERROR INVALID STATE ON LEFT CLICK" );
        }
        if(all_clear()){
          setPosVisi(x,y);
          JOptionPane.showMessageDialog(boardgrid, "Congrats you won");
          g.replay();
        }

      }
    }

  }

}


