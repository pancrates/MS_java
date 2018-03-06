import java.util.*;
public interface Interface_Ms_game{

  public int game_loop();
  public void configure_game();
  public void update_display();
  public void new_game_message();
  public int promptUserInput(int err);
  public void executeAction();
  public void setState(int ns);
  public void check_for_win();
  public void showWin();
  public void showLoose();
}
