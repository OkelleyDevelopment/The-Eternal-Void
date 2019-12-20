package gamePack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceHandler implements ActionListener{

    Game game;


    // Constructor
    public ChoiceHandler(Game g){

        game = g;
    }

    public void actionPerformed(ActionEvent event){

        String yourChoice = event.getActionCommand();

        switch(yourChoice){
            case "start": game.vm.titleToTown(); game.story.launchPad(); break;
            //case "help": game.vm.helpMenu(); break;
            case "c1": game.story.selectPosition(game.nextPos1); break;
            case "c2": game.story.selectPosition(game.nextPos2); break;
            case "c3": game.story.selectPosition(game.nextPos3); break;
            case "c4": game.story.selectPosition(game.nextPos4); break;
        }
    }



}
