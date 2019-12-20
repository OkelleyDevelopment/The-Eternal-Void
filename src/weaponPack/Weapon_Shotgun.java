package weaponPack;

public class Weapon_Shotgun extends SuperWeapon{

    public Weapon_Shotgun() {

        name = "Shotgun";
        damage = new java.util.Random().nextInt(18);

    }
}
