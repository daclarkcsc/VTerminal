package com.valkryst.VTerminal.TileGridTest;

import com.valkryst.VTerminal.Tile;
import com.valkryst.VTerminal.TileGrid;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Dimension;

public class RemoveChildTest {
    private TileGrid parentGrid;

    @Before
    public void initGrid() {
        /*
         * The grid looks like this:
         *
         * ABCDEF
         * ABCDEF
         * ABCDEF
         * ABCDEF
         * ABCDEF
         * ABCDEF
         */
        parentGrid = new TileGrid(new Dimension(6, 6));

        for (int y = 0 ; y < parentGrid.getHeight() ; y++) {
            final Tile[] row = parentGrid.getRow(y);

            for (int x = 0 ; x < parentGrid.getWidth() ; x++) {
                row[x].setCharacter((char) (x + 65));
            }
        }
    }

    @Test
    public void testRemoveChild_withValidChild() {
        final TileGrid grid = new TileGrid(new Dimension(1, 1));

        parentGrid.addChild(grid);
        Assert.assertTrue(parentGrid.containsChild(grid));

        parentGrid.removeChild(grid);
        Assert.assertFalse(parentGrid.containsChild(grid));
    }

    @Test
    public void testRemoveChild_withValidChildThatIsntAChildOfTheGrid() {
        final TileGrid grid = new TileGrid(new Dimension(1, 1));

        parentGrid.removeChild(grid);
        Assert.assertFalse(parentGrid.containsChild(grid));
    }

    @Test
    public void testRemoveChild_withNullChild() {
        parentGrid.removeChild(null);
    }
}
