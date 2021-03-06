package com.valkryst.VTerminal.font;

import lombok.NonNull;
import lombok.ToString;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@ToString
public class Font {
    /** The characters provided by the font. */
    private final HashMap<Integer, FontCharacter> fontCharacters;

    /** The dimensions of the maximum character size supplied by the font. */
    private final Dimension maxDimensions;

    /**
     * Constructs a new Font.
     *
     * @param fontCharacters
     *          The font characters.
     *
     * @param scale
     *          The amount to scale each character image by.
     *
     * @throws NullPointerException
     *           If the characterImages is null.
     */
    public Font(final @NonNull HashMap<Integer, FontCharacter> fontCharacters, final double scale) {
        this.fontCharacters = fontCharacters;

        // Count occurrences of width/heights.
        final HashMap<Integer, Integer> widthCounts = new HashMap<>();
        final HashMap<Integer, Integer> heightCounts = new HashMap<>();

        for (final Map.Entry<Integer, FontCharacter> entry : fontCharacters.entrySet()) {
            final FontCharacter fc = entry.getValue();

            final int fcWidth = fc.getWidth();
            final int fcHeight = fc.getHeight();

            final int widthCount = widthCounts.getOrDefault(fcWidth, 0) + 1;
            final int heightCount = heightCounts.getOrDefault(fcHeight, 0) + 1;

            widthCounts.put(fcWidth, widthCount);
            heightCounts.put(fcHeight, heightCount);
        }

        // Determine the maximum width.
        Map.Entry<Integer, Integer> widthEntry = null;
        int width = Integer.MIN_VALUE;

        for (final Map.Entry<Integer, Integer> entry : widthCounts.entrySet()) {
            if (widthEntry == null) {
                widthEntry = entry;
                width = entry.getKey();
                continue;
            }

            if (widthEntry.getValue() < entry.getValue()) {
                widthEntry = entry;
                width = entry.getKey();
            }
        }

        // Determine the maximum height.
        Map.Entry<Integer, Integer> heightEntry = null;
        int height = Integer.MIN_VALUE;

        for (final Map.Entry<Integer, Integer> entry : heightCounts.entrySet()) {
            if (heightEntry == null) {
                heightEntry = entry;
                height = entry.getKey();
                continue;
            }

            if (heightEntry.getValue() < entry.getValue()) {
                heightEntry = entry;
                height = entry.getKey();
            }
        }

        maxDimensions = new Dimension(width, height);

        // Find any characters that are not equal to the max width/height, then resize them.
        for (final Map.Entry<Integer, FontCharacter> entry : fontCharacters.entrySet()) {
            final FontCharacter fontCharacter = entry.getValue();
            final int fcWidth = fontCharacter.getWidth();
            final int fcHeight = fontCharacter.getHeight();

            if (fcWidth != width || fcHeight != height) {
                fontCharacter.resizeImage(width, height);
            }
        }

        // Resize all font images if required.
        resize(scale, scale);
    }

    /**
     * Resizes the images of all characters by some scale percentages.
     *
     * @param scaleWidth
     *          The amount to scale the width by.
     *
     * @param scaleHeight
     *          The amount to scale the height by.
     */
    public void resize(final double scaleWidth, final double scaleHeight) {
        if (scaleWidth == 0 || scaleHeight == 0 || (scaleWidth == 1 && scaleHeight == 1)) {
            return;
        }

        for (final Map.Entry<Integer, FontCharacter> entry : fontCharacters.entrySet()) {
            entry.getValue().resizeImage(scaleWidth, scaleHeight);
        }

        maxDimensions.setSize(maxDimensions.getWidth() * scaleWidth, maxDimensions.getHeight() * scaleHeight);
    }

    /**
     * Determines if a unicode character is supported by the font.
     *
     * @param character
     *          The unicode character value
     *
     * @return
     *         Whether or not the character is supported.
     */
    public boolean isCharacterSupported(final int character) {
        return fontCharacters.containsKey(character);
    }

    /**
     * Retrieves the image associated with a character.
     *
     * @param character
     *          The unicode character value
     *
     * @return
     *          The image.
     */
    public BufferedImage getCharacterImage(final int character) {
        return fontCharacters.get(character).getImage();
    }

    /**
     * Retrieves the maximum width of a character cell.
     *
     * @return
     *         The maximum width of a character cell.
     */
    public int getWidth() {
        return maxDimensions.width;
    }

    /**
     * Retrieves the maximum height of a character cell.
     *
     * @return
     *         The maximum height of a character cell.
     */
    public int getHeight() {
        return maxDimensions.height;
    }
}
