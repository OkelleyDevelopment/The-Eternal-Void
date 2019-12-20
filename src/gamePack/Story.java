package gamePack;

import enemyPack.Enemy_Ghoul;
import enemyPack.Enemy_Raider;
import enemyPack.Enemy_StrangeMan;
import enemyPack.SuperEnemy;
import weaponPack.Weapon_Knife;
import weaponPack.Weapon_Pistol;
import weaponPack.Weapon_Shotgun;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Nicholas O'Kelley
 *
 * @version 1.0
 *
 * This class contains the story elements of the game. Each method contributes to the story
 * with the reused methods listed toward the end of the file.
 */
public class Story {

    private Game game;
    private UI ui;
    private VisibilityManager vm;

    BufferedImage scaledImage = null;

    Player player = new Player();
    SuperEnemy enemy;



    /**
     * This is the constructor for the story class.
     *
     * @param g the game that will be loaded
     * @param userInterface the GUI setup
     * @param vManager is what deals with the titlescreen to game transition
     *
     */
    protected Story(Game g, UI userInterface, VisibilityManager vManager){

        game = g;
        ui = userInterface;
        vm = vManager;

    }

    /**
     * Called when the player attempts another play through.
     */
    protected void defaultSetup(){

        player.hp = 20;
        ui.hpNumberLabel.setText("" + player.hp);

        player.currentWeapon = new Weapon_Knife();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

    }

    /**
     * Handles the method calls deoending on what choice the player makes.
     *
     * @param nextPosition a variable that contains the next player position.
     */
    public void selectPosition(String nextPosition){

        switch(nextPosition){
            case "launchPad": launchPad(); break;
            case "board": boardShip(); break;
            case "explore": explorePad(); break;
            case "goInside": goInside(); break;
            case "sleep": sleep(); break;
            case "toTitle": toTitleScreen(); break;
            case "contExplore": contExplore(); break;
            case "inspectCrates": inspectCrates(); break;
            case "north": north(); break;
            case "intersection": intersection(); break;
            case "enemyCamp": enemyCamp(); break;
            case "inspectTent": inspectTent(); break;
            case "inspectFirePit": inspectFirePit(); break;
            //Gas station fight
            case "gasStation": gasStation(); break;
            case "gasFight": gasFight(); break;
            case "noRun": noRun(); break;
            case "ghoulFight": ghoulFight(); break;
            //case "playerAttackGhoul": playerAttack(); break;
            //case "ghoulAttack": enemyAttack(); break;
            case "GasStation": gunsAndGas(); break;
            //Final boss for version 1.0  (7/15/2019)
            case "sneakAndLocate": sneakAndLocate(); break;
            case "engage": engage(); break;
            case "engage2": engageDialogue(); break;
            case "fightMan": fightMan(); break;
            case "driveTheTruck": driveTheTruck(); break;
            case "east": east(); break;
            case "encounterRaider": encounterRaider(); break;
            case "inspectJeep": inspectJeep(); break;
            case "south": south(); break;
            case "tossedBox": tossedBox(); break;
            // Fight sequence method calls
            case "fight": fight(); break;
            case "playerAttack": playerAttack(); break;
            case "enemyAttack": enemyAttack(); break;

            case "Lose": lose(); break;
        }
    }

    /**
     * This is the method called for the first chunk of story.
     */
    public void launchPad(){

        // Found on stackoverflow but lost the page before a citation could be made 7/13/2019
        try{
            scaledImage = ImageIO.read(new File("src\\res\\launchPad.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        if(player.hasGasCan && player.hasFuel) {
            ui.mainTextArea.setText("Arriving at the launch pad, you get out and begin fueling the ship up.");
        }else{
            ui.mainTextArea.setText("Regaining consciousness, you run outside to the launch pad seeing a truck drive away. \nSomeone has found your ship... ");
        }
        ui.choice1.setText("Board Ship");
        ui.choice2.setText("Explore Area");
        ui.choice3.setText("Find Shelter");
        ui.choice4.setText("");

        game.nextPos1 = "board";
        game.nextPos2 = "explore";
        game.nextPos3 = "goInside";
        game.nextPos4 = "";

    }

    /**
     *  Allows the player to go inside a shelter to regain Health points.
     */
    public void goInside(){

        // Found on stackoverflow but lost the page before a citation could be made 7/13/2019
        try{
            scaledImage = ImageIO.read(new File("src\\res\\shelter.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);


        ui.mainTextArea.setText("Sheltered from the harsh weather you rest. Finding fuel is a necessity.");

        ui.choice1.setText("Sleep");
        ui.choice2.setText("Launch Pad");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "sleep";
        game.nextPos2 = "explore";
        game.nextPos3 = "";
        game.nextPos4 = "";


    }

    /**
     * If the player satisfies the conditions, it is the endgame.
     */
    public void boardShip(){

        if(player.hasFuel && player.hasGasCan){

            toSpace();

        }
        else {
            try{
                scaledImage = ImageIO.read(new File("src\\res\\noFuel.jpg"));
            }catch(IOException e){
                e.printStackTrace();
            }

            // This variable is set to type Image after the scaling is complete.
            Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

            ui.image = new ImageIcon(dimg);
            ui.imageLabel.setIcon(ui.image);
            ui.imagePanel.add(ui.imageLabel);
            ui.window.add(ui.imagePanel);
            ui.imagePanel.setVisible(true);

            ui.mainTextArea.setText("You board the ship only to find that the fuel is missing.");

            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPos1 = "launchPad";
            game.nextPos2 = "";
            game.nextPos3 = "";
            game.nextPos4 = "";
        }

    }


    /**
     * Point for player to investigate or continue traveling.
     */
    public void explorePad(){

        ui.mainTextArea.setText("The pad is empty except for a few crates.");

        ui.choice1.setText("Continue exploring");
        ui.choice2.setText("Inspect Crates");
        ui.choice3.setText("Find Shelter");
        ui.choice4.setText("");

        game.nextPos1 = "contExplore";
        game.nextPos2 = "inspectCrates";
        game.nextPos3 = "goInside";
        game.nextPos4 = "";


    }

    /**
     * Additional details for the player to investigate.
     */
    public void inspectCrates(){

        ui.mainTextArea.setText("The crates are empty...");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "explore";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    /**
     * Further exploration and world building.
     */
    public void contExplore(){

        try{
            scaledImage = ImageIO.read(new File("src\\res\\trail.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        //TODO: Make this different if the player has already been here.

        ui.mainTextArea.setText("You head into dense jungle. The sounds of ghouls echo...");

        ui.choice1.setText("North");
        ui.choice2.setText("East");
        ui.choice3.setText("South");
        ui.choice4.setText("Back");

        game.nextPos1 = "north";
        game.nextPos2 = "east";
        game.nextPos3 = "south";
        game.nextPos4 = "explore";
    }

    /**
     * Player continues to see more of the map being revealed.
     */
    private void north(){
        ui.mainTextArea.setText("You head out of the Jungle. The sounds of the ghouls are louder...");

        ui.choice1.setText("An intersection");
        ui.choice2.setText("back");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "intersection";
        game.nextPos2 = "contExplore";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    /**
     * Choice between going left and right.
     */
    private void intersection(){

        try{
            scaledImage = ImageIO.read(new File("src\\res\\intersection.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        ui.mainTextArea.setText("After walking a bit further, you arrive at an intersection.");

        ui.choice1.setText("Go left");
        ui.choice2.setText("Go right");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "enemyCamp";
        game.nextPos2 = "gasStation";
        game.nextPos3 = "";
        game.nextPos4 = "";

    }

    /**
     * Left brings the player to the enemy camp
     */
    private void enemyCamp(){

        try{
            scaledImage = ImageIO.read(new File("src\\res\\enemyCamp.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);


        ui.mainTextArea.setText("The camp looks abandoned. Only one tent is standing.");

        ui.choice1.setText("Inspect Tent");
        ui.choice2.setText("Inspect Fire pit");
        ui.choice3.setText("");
        ui.choice4.setText("Back");

        game.nextPos1 = "inspectTent";
        game.nextPos2 = "inspectFirePit";
        game.nextPos3 = "";
        game.nextPos4 = "intersection";
    }

    private void inspectTent(){

        ui.mainTextArea.setText("The tent has been used recently. Some weapon parts lie in the back." +
                "\n\nObtained a shotgun!");

        player.currentWeapon = new Weapon_Shotgun();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "enemyCamp";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void inspectFirePit(){
        ui.mainTextArea.setText("The ashes are still warm...");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "enemyCamp";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    /**
     * Player begins the push toward the end of the short adventure.
     */
    private void gasStation(){

        try{
            scaledImage = ImageIO.read(new File("res\\gasStationSign.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        ui.mainTextArea.setText("As you approach the gas station, you can see the shattered windows.\n" +
                "Gun shots are ringing out...");

        ui.choice1.setText("Continue?");
        ui.choice2.setText("Back");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "gasFight";
        game.nextPos2 = "intersection";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void gasFight(){
        try{
            scaledImage = ImageIO.read(new File("src\\res\\ghoul.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        enemy = new Enemy_Ghoul();

        ui.mainTextArea.setText("Closer to the station, you see a ghoul running towards you");

        ui.choice1.setText("Fight");
        ui.choice2.setText("Run!");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "ghoulFight";
        game.nextPos2 = "noRun";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void noRun(){
        ui.mainTextArea.setText("There is no hope in running now!");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "ghoulFight";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void ghoulFight(){
        ui.mainTextArea.setText(enemy.name + ": " + enemy.hp + "\nAct quickly!" );

        ui.choice1.setText("Fight");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "playerAttack";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";

    }

    private void gunsAndGas(){

        try{
            scaledImage = ImageIO.read(new File("src\\res\\StrangeMan.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);


        ui.mainTextArea.setText("Looking in the window you see the figure of a man shooting at some ghouls.");

        ui.choice1.setText("Sneak around back");
        ui.choice2.setText("Engage");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "sneakAndLocate";
        game.nextPos2 = "engage";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void sneakAndLocate(){

        try{
            scaledImage = ImageIO.read(new File("src\\res\\truck.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        ui.mainTextArea.setText("Crawling around the back of the station you see the man left his truck unattended. " +
                "In the bed of the truck lies a tank full of gas!");

        ui.choice1.setText("Drive");
        ui.choice2.setText("Engage");
        ui.choice3.setText("Back");
        ui.choice4.setText("");

        game.nextPos1 = "driveTheTruck";
        game.nextPos2 = "";
        game.nextPos3 = "GasStation";
        game.nextPos4 = "";
    }

    private void driveTheTruck(){

        ui.mainTextArea.setText("Driving as fast as you can you leave the station.");

        player.hasFuel = true;
        player.hasGasCan = true; // Comment this out; To test the program.

        player.hp -= 2;

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "launchPad";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void engage(){

        enemy = new Enemy_StrangeMan();

        ui.mainTextArea.setText("You rush inside as the body of the ghoul dropped to the floor. The man starts to chuckle...");

        ui.choice1.setText("Confront the man");
        ui.choice2.setText("Shoot");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "engage2";
        game.nextPos2 = "fightMan";
        game.nextPos3 = "";
        game.nextPos4 = "";

    }

    private void engageDialogue(){
        ui.mainTextArea.setText( enemy.name +": You sure are persistent. However, our plan to escape will not be stopped by you!");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "fightMan";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void fightMan(){
        if(enemy.hp > 10) {
            ui.mainTextArea.setText(enemy.name + ": " + enemy.hp + "\n\n'Don't make this worse...'");
        }else{
            ui.mainTextArea.setText(enemy.name + ": " + enemy.hp + "\n\n'Accurate, but we will win!'");
        }
        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "playerAttack";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }


    private void east(){

        ui.mainTextArea.setText("You see some wreckage of an old Jeep ahead. The sound of footsteps in the nearby woods might be an issue.");

        ui.choice1.setText("Inspect Wreckage");
        ui.choice2.setText("Back");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "encounterRaider";
        game.nextPos2 = "contExplore";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void encounterRaider(){
        try{
            scaledImage = ImageIO.read(new File("src\\res\\raider.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        enemy = new Enemy_Raider();

        ui.mainTextArea.setText("You encounter a Raider!");

        ui.choice1.setText("Fight");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "fight";
        game.nextPos2 = "contExplore";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    /**
     * A method that takes no parameters. It initializes the 'battle' between the
     * player and the new enemy.
     */
    private void fight(){
        ui.mainTextArea.setText(enemy.name + ": " + enemy.hp + "\nFight to continue or run?");

        ui.choice1.setText("Fight");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "playerAttack";
        game.nextPos2 = "contExplore";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void inspectJeep() {
        try{
            scaledImage = ImageIO.read(new File("src\\res\\gasCan.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        ui.mainTextArea.setText("Searching the wreck, you find gas can.");

        player.hasGasCan = true;

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "contExplore";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";

    }

    // NOTE: In the plans this method was called West.
    private void south(){
        ui.mainTextArea.setText("Walking along a path, you see a box tossed in the high grass.");

        ui.choice1.setText("Continue?");
        ui.choice2.setText("Back");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "tossedBox";
        game.nextPos2 = "contExplore";
        game.nextPos3 = "";
        game.nextPos4 = "";

    }

    private void tossedBox(){

        try{
            scaledImage = ImageIO.read(new File("src\\res\\gunBox.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        ui.mainTextArea.setText("Searching the contents of the box you find a pistol with 12 rounds left.");

        player.currentWeapon = new Weapon_Pistol();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "contExplore";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    private void playerAttack(){

        int playerDamage = player.currentWeapon.damage;

        enemy.hp -= playerDamage;

        ui.mainTextArea.setText("You attacked the " + enemy.name + " and dealt " + playerDamage + " damage!");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if(enemy.hp > 0) {
            game.nextPos1 = "enemyAttack";
            game.nextPos2 = "";
            game.nextPos3 = "";
            game.nextPos4 = "";
        }
        else if(enemy.hp < 0){
            if(enemy.name == "Raider") {
                game.nextPos1 = "inspectJeep";
            } else if(enemy.name == "Ghoul"){
                game.nextPos1 = "GasStation";
            } else if(enemy.name == "Strange Man"){
                game.nextPos1 = "SearchBackPack";
            }
            game.nextPos2 = "";
            game.nextPos3 = "";
            game.nextPos4 = "";
        }
    }


    private void enemyAttack(){

        int enemyDamage = enemy.attack;

        ui.mainTextArea.setText(enemy.atkMsg + " You received " + enemyDamage + " damage!");

        player.hp = player.hp - enemyDamage;
        ui.hpNumberLabel.setText("" + player.hp);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if(player.hp > 0) {
            if(enemy.name == "Raider") {
                game.nextPos1 = "fight";
            }else if(enemy.name == "Ghoul"){
                game.nextPos1 = "ghoulFight";
            }
            else if(enemy.name == "Strange Man"){
                game.nextPos1 = "fightMan";
            }
            game.nextPos2 = "";
            game.nextPos3 = "";
            game.nextPos4 = "";
        }
        else if(player.hp < 0){
            game.nextPos1 = "Lose";
            game.nextPos2 = "";
            game.nextPos3 = "";
            game.nextPos4 = "";
        }


    }

    private void sleep(){

        // Keeps the regen from being zero.
        int regen = new java.util.Random().nextInt(4) + 1;

        if(player.hp < 25){
            ui.mainTextArea.setText("You rest for a few hours and regained " + regen + " HP!");

            player.hp = player.hp + regen;
            ui.hpNumberLabel.setText("" + player.hp);

            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPos1 = "goInside";
            game.nextPos2 = "";
            game.nextPos3 = "";
            game.nextPos4 = "";
        }
        else if(player.hp == 25){
            ui.mainTextArea.setText("You are well rested. It's time to explore!");
            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPos1 = "goInside";
            game.nextPos2 = "";
            game.nextPos3 = "";
            game.nextPos4 = "";
        }
    }

    private void lose(){
        ui.mainTextArea.setText("You failed to escape! \n\n <GAME OVER!>");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "toTitle";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";
    }

    public void toSpace(){
        try{
            scaledImage = ImageIO.read(new File("src\\res\\endcard.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // This variable is set to type Image after the scaling is complete.
        Image dimg = scaledImage.getScaledInstance(ui.imagePanel.getWidth(), ui.imagePanel.getHeight(), Image.SCALE_SMOOTH);

        ui.image = new ImageIcon(dimg);
        ui.imageLabel.setIcon(ui.image);
        ui.imagePanel.add(ui.imageLabel);
        ui.window.add(ui.imagePanel);
        ui.imagePanel.setVisible(true);

        ui.mainTextArea.setText("Traveling off the remains of Earth, you prepare yourself for the journey to Mars.\n\n" +
                "<YOU WIN!>");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPos1 = "toTitle";
        game.nextPos2 = "";
        game.nextPos3 = "";
        game.nextPos4 = "";

    }


    public void toTitleScreen(){

        defaultSetup();
        game.vm.showTitleScreen();
    }
}
