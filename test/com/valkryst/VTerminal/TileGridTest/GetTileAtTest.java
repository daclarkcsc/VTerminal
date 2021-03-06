package com.valkryst.VTerminal.TileGridTest;

import com.valkryst.VTerminal.Tile;
import org.junit.Assert;
import org.junit.Test;

import java.awt.Point;

public class GetTileAtTest {
    @Test
    public void testGetTileAt_withAllValidCoordinates() {
        final int gridWidth = StaticGrid.TILE_GRID.getWidth();
        final int gridHeight = StaticGrid.TILE_GRID.getHeight();

        for (int y = 0 ; y < gridHeight ; y++) {
            for (int x = 0 ; x < gridWidth ; x++) {
                final Tile character = StaticGrid.TILE_GRID.getTileAt(x, y);

                switch (x) {
                    case 0: {
                        Assert.assertEquals('A', character.getCharacter());
                        break;
                    }
                    case 1: {
                        Assert.assertEquals('B', character.getCharacter());
                        break;
                    }
                    case 2: {
                        Assert.assertEquals('C', character.getCharacter());
                        break;
                    }
                    case 3: {
                        Assert.assertEquals('D', character.getCharacter());
                        break;
                    }
                    case 4: {
                        Assert.assertEquals('E', character.getCharacter());
                        break;
                    }
                    case 5: {
                        Assert.assertEquals('F', character.getCharacter());
                        break;
                    }
                }
            }
        }
        final Tile character = StaticGrid.TILE_GRID.getTileAt(0, 0);
        Assert.assertEquals('A', character.getCharacter());
    }

    @Test
    public void testGetTileAt_withXBelowZero() {
        final Tile character = StaticGrid.TILE_GRID.getTileAt(-1, 0);
        Assert.assertNull(character);
    }

    @Test
    public void testGetTileAt_withYBelowZero() {
        final Tile character = StaticGrid.TILE_GRID.getTileAt(0, -1);
        Assert.assertNull(character);
    }

    @Test
    public void testGetTileAt_withXExceedingGridWidth() {
        int gridWidth = StaticGrid.TILE_GRID.getWidth() + 1;
        final Tile character = StaticGrid.TILE_GRID.getTileAt(gridWidth, 0);
        Assert.assertNull(character);
    }

    @Test
    public void testGetTileAt_withYExceedingGridHeight() {
        int gridHeight = StaticGrid.TILE_GRID.getHeight() + 1;
        final Tile character = StaticGrid.TILE_GRID.getTileAt(0, gridHeight);
        Assert.assertNull(character);
    }

    @Test
    public void testGetTileAt_withValidPointCoordinates() {
        final Tile character = StaticGrid.TILE_GRID.getTileAt(new Point(0, 0));
        Assert.assertEquals('A', character.getCharacter());
    }

    @Test
    public void testGetTileAt_withNullPointCoordinates() {
        Assert.assertNull(StaticGrid.TILE_GRID.getTileAt(null));
    }
}
