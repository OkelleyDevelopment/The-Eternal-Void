package gamePack;

public class Game {

    ChoiceHandler cHandler = new ChoiceHandler(this);

    UI ui = new UI();

    VisibilityManager vm = new VisibilityManager(ui);

    Story story = new Story(this, ui, vm);

    String nextPos1, nextPos2, nextPos3, nextPos4;


    public Game(){

        ui.createdUI(cHandler);
        story.defaultSetup();
        vm.showTitleScreen();
    }


    public static void main(String[] args) {

        new Game();

    }

}
