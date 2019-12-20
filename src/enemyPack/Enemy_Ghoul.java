package enemyPack;

public class Enemy_Ghoul extends SuperEnemy{

    public Enemy_Ghoul(){

        name = "Ghoul";
        hp = 12;
        attack = new java.util.Random().nextInt(6);
        atkMsg = "The ghoul hits you with its claws!";

    }





}
