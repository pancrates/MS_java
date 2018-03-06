public class Board_CL extends Board{

  public Board_CL(){
    super();
  }
  public Board_CL(int size, int mines){
    super(size,mines);
  }
  public void revealBoard(){
    int i=0,j=0;
    for(i=0;i<this.getbEdge();i++){
      for(j=0;j<this.getbEdge();j++){
        System.out.print(" "+this.obscureMine(this.getposValue(i,j))+" ");
      }
      System.out.println();
      System.out.println();
    }
  }
  public void printBoard(){
    int i,j;
    printStartOfBlock();
    printEndOfBlock();
    for(i=0;i<this.getbEdge();i++){
      System.out.print(String.format("%2d", i) +"|");
      for(j=0;j<this.getbEdge();j++){
        if(this.getposVisi(i,j)==1) System.out.print(" "+this.obscureMine(this.getposValue(i,j))+" ");
        else if(this.getposVisi(i,j)==2) System.out.print(" # ");
        else System.out.print(" * ");
      }
      System.out.println();
      System.out.println();
    }
    //printEndOfBlock();
  }
  private void printEndOfBlock(){
    int i;
    System.out.print("   ");
    for(i=0;i<this.getbEdge();i++){
      System.out.print("---");
    }
    System.out.println();
  }
  private void printStartOfBlock(){
    int i;
    System.out.print("   ");
    for(i=0;i<this.getbEdge();i++){
      System.out.print(" "+String.format("%X", i) +" ");
    }
    System.out.println();
  }
  private String obscureMine(int val){
    if(val==9) return "M";
    else return Integer.toString(val);
  }
}
