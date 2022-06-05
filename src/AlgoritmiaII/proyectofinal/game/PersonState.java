package AlgoritmiaII.proyectofinal.game;


import AlgoritmiaII.proyectofinal.utils.ImageHelper;

import java.awt.*;

public enum PersonState {
    INFECTED(Color.decode("#f50e0e"), ImageHelper.fromResource("infected.png"), false),
    IMMUNE(Color.blue, ImageHelper.fromResource("sunglasses.png"), false),
    NOT_INFECTED(Color.white, ImageHelper.fromResource("smiling.png"), true),
    DEATH(Color.decode("#000000"), ImageHelper.fromResource("dead.png"), false),
    STATE_SURROUNDED(Color.decode("#f78d36"), ImageHelper.fromResource("surrounded.png"), true),
    MASKED(Color.decode("#4daafc"), ImageHelper.fromResource("masked.png"), true);

    private final Color color;
    private final Image image;
    private final boolean infectable;

    PersonState(Color color, Image image, boolean infectable) {
        this.color = color;
        this.image = image;
        this.infectable = infectable;
    }

    public Color getColor() {
        return color;
    }

    public Image getImage() {
        return image;
    }

    public boolean isInfectable() {
        return infectable;
    }
}
