package mtg.mendonca;


public class Game {
    public static void main(String[] args) {
        Land redLand = new Land("Mountain", "Red", "", "");
        Land redLand2 = new Land("Mountain", "Red", "", "");
        Land greenLand = new Land("Forest", "Green", "", "");
        Land greenLand2 = new Land("Forest", "Green", "", "");
        Land whiteLand = new Land("Plain", "White", "", "");
        Land whiteLand2 = new Land("Plain", "White", "", "");
        Land blueLand = new Land("Island", "Blue", "", "");
        Land blueLand2 = new Land("Island", "Blue", "", "");
        Land blackLand = new Land("Swamp", "Black", "", "");
        Land blackLand2 = new Land("Swamp", "Black", "", "");

        Field field = new Field();

        field.addBasicLandToField(redLand);
        field.addBasicLandToField(redLand2);
        field.addBasicLandToField(greenLand);
        field.addBasicLandToField(greenLand2);
        field.addBasicLandToField(whiteLand);
        field.addBasicLandToField(whiteLand2);
        field.addBasicLandToField(blueLand);
        field.addBasicLandToField(blueLand2);
        field.addBasicLandToField(blackLand);
        field.addBasicLandToField(blackLand2);

        field.tapLands(2,2,2,1,1);

        System.out.println("Untapped red lands: " + field.countNumberOfUntappedColoredLands("Red"));
        System.out.println("Untapped green lands: " + field.countNumberOfUntappedColoredLands("Green"));
        System.out.println("Untapped white lands: " + field.countNumberOfUntappedColoredLands("White"));
        System.out.println("Untapped blue lands: " + field.countNumberOfUntappedColoredLands("Blue"));
        System.out.println("Untapped black lands: " + field.countNumberOfUntappedColoredLands("Black"));
        System.out.println("Untapped lands: " + field.countUntappedLand());
    }
}

