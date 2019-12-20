package gamePack;

public class VisibilityManager extends UI {


    UI ui;


    public VisibilityManager(UI userInterface){

        ui = userInterface;

    }

    public void showTitleScreen(){

        // Show the title screen
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);

        // Hide the game screen
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);
        ui.imagePanel.setVisible(false);
        //add a player panel here

    }

    public void titleToTown(){
        // Hide the title screen.
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        // Show the game screen.
        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);
        ui.imagePanel.setVisible(true);

    }
}
