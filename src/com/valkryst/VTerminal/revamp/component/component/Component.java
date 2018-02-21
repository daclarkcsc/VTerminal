package com.valkryst.VTerminal.revamp.component.component;

import com.valkryst.VTerminal.AsciiCharacter;
import com.valkryst.VTerminal.revamp.component.Screen;
import com.valkryst.VTerminal.revamp.component.TileGrid;
import lombok.Getter;
import lombok.NonNull;

import java.awt.Dimension;
import java.awt.Point;
import java.util.*;

public class Component {
    /** The tiles. */
    @Getter protected final TileGrid tiles;

    /** The event listeners. */
    private final List<EventListener> eventListeners = new LinkedList<>();

    /**
     * Constructs a new Component.
     *
     * @param width
     *          The width.
     *
     * @param height
     *          The height.
     *
     * @param x
     *          The x-axis position of the component within it's parent.
     *
     * @param y
     *          The y-axis position of the component within it's parent.
     */
    public Component(final int width, final int height, final int x, final int y) {
        this(new Dimension(width, height), new Point(x, y));
    }

    /**
     * Constructs a new Component.
     *
     * @param dimensions
     *          The dimensions of the component
     *
     * @param position
     *          The position of the component within it's parent.
     *
     * @throws NullPointerException
     *         If the dimensions or point is null.
     */
    public Component(final @NonNull Dimension dimensions, final @NonNull Point position) {
        tiles = new TileGrid(dimensions, position);
    }

    /**
     * Draws the component onto a screen.
     *
     * @param screen
     *          The screen.
     */
    public void draw(final Screen screen) {
        if (screen == null) {
            return;
        }

        final int xOffset = tiles.getXPosition();
        final int yOffset = tiles.getYPosition();

        for (int y = 0 ; y < tiles.getHeight() ; y++) {
            final int yPosition = yOffset + y;

            for (int x = 0 ; x < tiles.getWidth() ; x++) {
                final int xPosition = xOffset + x;

                final AsciiCharacter componentTile = tiles.getTileAt(x, y);
                final AsciiCharacter screenTile = screen.getTileAt(xPosition, yPosition);
                screenTile.copy(componentTile);
            }
        }
    }

    /**
     * Retrieves an unmodifiable version of the event listeners list.
     *
     * @return
     *          An unmodifiable version of the event listeners list.
     */
    public List<EventListener> getEventListeners() {
        return Collections.unmodifiableList(eventListeners);
    }
}
