package AlgoritmiaII.proyectofinal.ui;

import AlgoritmiaII.proyectofinal.game.Person;
import AlgoritmiaII.proyectofinal.game.PersonState;
import AlgoritmiaII.proyectofinal.utils.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Cell extends JComponent {
    private PersonState state = PersonState.NOT_INFECTED;
    private Person person;

    final int id;

    BufferedImage buffer;
    boolean invalidateBuffer;

    public Cell(int id) {
        this.id = id;
        setDoubleBuffered(true);

        buffer = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        invalidateBuffer = true;
    }

    public void setState(PersonState personState, Person person) {
        this.person = person;

        Objects.requireNonNull(personState, "El estado no puede ser nulo");

        if (personState == state) return;
        state = personState;
        invalidateBuffer = true;

        repaint();
    }

    @Override
    public synchronized void paint(Graphics g) {
        super.paint(g);
        ensureBufferedIsPainted();
        drawBuffer(g);
    }

    private void drawBuffer(Graphics g) {
        g.drawImage(buffer,
                0, 0, getWidth(), getHeight(),
                0, 0, buffer.getWidth(), buffer.getHeight(),
                this);
    }

    private void ensureBufferedIsPainted() {
        if(!invalidateBuffer) return;

        Graphics g = buffer.createGraphics();
        drawBackground(g, buffer.getWidth(), buffer.getHeight());
        drawStateImage(g, buffer.getWidth(), buffer.getHeight());
        drawBorderState(g, buffer.getWidth(), buffer.getHeight());
        g.dispose();
        invalidateBuffer = false;
    }

    private void drawBackground(Graphics g, int width, int height) {
        g.setColor(state.getColor());
        g.fillRect(0, 0, width, height);
    }

    private void drawBorderState(Graphics g, int width, int height) {
        g.setColor(Color.white);
        g.drawRect(0, 0, width, height);
    }

    private void drawStateImage(Graphics g, int width, int height) {
        final Image image = person != null && person.isMasked() ?
                ImageHelper.fromResource("masked.png") :
                this.state.getImage();
        final Point imageSource = ImageHelper.centerImage(image, new Dimension(width, height));

        if (imageFitsInControl(image)) {
            g.drawImage(image, imageSource.x, imageSource.y, this);
        } else {
            final int margin = (int) (width * 0.10);
            final int sx2 = image.getWidth(this);
            final int sy2 = image.getHeight(this);
            g.drawImage(image,
                    margin, margin, width - margin, height - margin,
                    0, 0, sx2, sy2, this);
        }

    }

    private boolean imageFitsInControl(Image image) {
        return image.getWidth(this) <= this.getWidth()
                && image.getHeight(this) <= this.getHeight();
    }

}
