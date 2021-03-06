package com.valkryst.VTerminal.TileTest;

import com.valkryst.VTerminal.Tile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

public class SetBackgroundColorTest {
    private Tile character;

    @Before
    public void initializeCharacter() {
        character = new Tile('A');
    }

    @Test
    public void withValidInput() {
        character.setBackgroundColor(Color.RED);
        Assert.assertEquals(character.getBackgroundColor(), Color.RED);
    }

    @Test
    public void withColorThatIsTheSameAsTheExistingBackgroundColor() {
        character.setBackgroundColor(character.getBackgroundColor());
    }
}
