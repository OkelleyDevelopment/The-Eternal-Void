package enemyPack;

public class Enemy_Raider extends SuperEnemy {

    public Enemy_Raider() {

        name = "Raider";
        hp = 15;
        attack = new java.util.Random().nextInt(6);
        atkMsg = "The Raider attacks with his machete!";

    }


}
