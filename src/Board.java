
import java.util.*;
public class Board{
  private int bEdge;
  private int nMines;
  private int[][] posValue;
  private int[][] posVisi;

  public Board(){
    this(10,15);
  }
  public Board(int size,int mines){
    this.bEdge=size;
    this.nMines=mines;
    this.posValue=new int[this.bEdge][this.bEdge];
    this.posVisi=new int[this.bEdge][this.bEdge];
    generateBoard();
    System.out.println("Board created");

  }
  private void generateBoard(){
    Random r = new Random();
    int i,n,j,jj,ii;
    for(n=0;n<nMines;n++){
      i = r.nextInt(this.bEdge-1);
      j= r.nextInt(this.bEdge-1);
      //System.out.println(i+" "+j);
      if(this.posValue[i][j]==9) n--;
      else {
        this.posValue[i][j]=9;
        for(ii=i-1;ii<=i+1;ii++){
          if((ii<0) || (ii==this.bEdge)) continue;
          for(jj=j-1;jj<=j+1;jj++){
            if(jj<0 || jj==this.bEdge) continue;
            else if(posValue[ii][jj]==9) continue;
            else posValue[ii][jj]+=1;
          }
        }
      }
    }
  }
  public int flagField(int vertical, int horizontal){
    //if(!validIndex(vertical) || !validIndex(horizontal)) return 1;
    if(this.posVisi[vertical][horizontal]==0){
      this.posVisi[vertical][horizontal]=2;
      return 0;
    } return 1;
  }
  public int deFlagField(int vertical, int horizontal){
    //if(!validIndex(vertical) || !validIndex(horizontal)) return 1;
    if(this.posVisi[vertical][horizontal]!=2) return 2;
    else {
      this.posVisi[vertical][horizontal]=0;
      return 0;
    }
  }
  public int openField(int i,int j){
      this.posVisi[i][j]=1;
      if(this.posValue[i][j]==9) return 3;
      return 1;
  }
  public int getposValue(int i,int j){
    if(validIndex(i) && validIndex(j)) return this.posValue[i][j];
    else return -1;

  }
  public int getposVisi(int i,int j){
    if(validIndex(i) && validIndex(j)) return this.posVisi[i][j];
    else return -1;
  }
  public int getbEdge(){
    return this.bEdge;
  }
  public int getnMines(){
    return this.nMines;
  }
  public Boolean validIndex(int index){
    if(index<0 || index>=this.bEdge) return false;
    else return true;
  }
  public Boolean all_clear(){
    int i,j,cnt=0;
    for(i=0;i<this.getbEdge();i++){
      for(j=0;j<this.getbEdge();j++){
        if(this.getposVisi(i,j)==1) cnt++;
      }
    }
    if(this.bEdge*this.bEdge-this.nMines==cnt) return true;
    else return false;
  }
  public int flood_fill(int i,int j){
    if(!validIndex(i) || !validIndex(j)) return 4;
    if(getposVisi(i,j)!=0) return 1;
    if(getposValue(i,j)==0){
      openField(i,j);
      flood_fill(i+1,j);
      flood_fill(i-1,j);
      flood_fill(i,j+1);
      flood_fill(i,j-1);
      flood_fill(i+1,j+1);
      flood_fill(i+1,j-1);
      flood_fill(i-1,j+1);
      flood_fill(i-1,j-1);
      return 1;
    }
    else return openField(i,j);
  }

  public void setPosVisi(int x,int y) {
    this.posVisi[x][y] = 0;
  }
}
