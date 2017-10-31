package com.valkryst.VTerminal;

import com.valkryst.VRadio.Radio;
import com.valkryst.VTerminal.misc.ImageCache;
import com.valkryst.VTerminal.misc.IntRange;
import com.valkryst.VTerminal.shader.ShadeShader;
import com.valkryst.VTerminal.shader.Shader;
import com.valkryst.VTerminal.shader.TintShader;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;

@ToString
public class AsciiString {
    /** The characters of the string. */
    @Getter private AsciiCharacter[] characters;

    /**
     * Constructs a new AsciiString of the specified length with all characters
     * set to ' '.
     *
     * @param length
     *         The length to make the string.
     *
     * @throws IllegalArgumentException
     *        If the length is less than 0.
     */
    public AsciiString(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("The length cannot be below 0.");
        }

        characters = new AsciiCharacter[length];

        for (int columnIndex = 0 ; columnIndex < length ; columnIndex++) {
            characters[columnIndex] = new AsciiCharacter(' ');
        }
    }

    /**
     * Constructs a new AsciiString.
     *
     * @param string
     *         The string.
     *
     * @throws NullPointerException
     *        If the string is null.
     */
    public AsciiString(final @NonNull String string) {
        characters = new AsciiCharacter[string.length()];

        for (int columnIndex = 0 ; columnIndex < string.length() ; columnIndex++) {
            characters[columnIndex] = new AsciiCharacter(string.charAt(columnIndex));
        }
    }

    /**
     * Draws the characters of the string onto the specified context.
     *
     * @param gc
     *         The graphics context to draw with.
     *
     * @param imageCache
     *         The image cache to retrieve character images from.
     *
     * @param rowIndex
     *         The y-axis (row) coordinate where the characters are to be drawn.
     *         Includes the first index and excludes the last index.
     *
     * @throws NullPointerException
     *         If the gc or image cache is null.
     */
    public void draw(final @NonNull Graphics2D gc, final @NonNull ImageCache imageCache, final int rowIndex) {
        draw(gc, imageCache, rowIndex, new Point(0, 0));
    }

    /**
     * Draws the characters of the string onto the specified context.
     *
     * @param gc
     *         The graphics context to draw with.
     *
     * @param imageCache
     *         The image cache to retrieve character images from.
     *
     * @param rowIndex
     *         The y-axis (row) coordinate where the characters are to be drawn.
     *         Includes the first index and excludes the last index.
     *
     * @param offset
     *         The x/y-axis (column/row) offsets to alter the position at which the
     *         string is drawn.
     *
     * @throws NullPointerException
     *         If the gc or image cache is null.
     *
     * @throws IllegalArgumentException
     *         If the row index is below 0.
     */
    public void draw(final @NonNull Graphics2D gc, final @NonNull ImageCache imageCache, final int rowIndex, final Point offset) {
        if (rowIndex < 0) {
            throw new IllegalArgumentException("The row index cannot be below 0.");
        }

        for (int columnIndex = 0; columnIndex < characters.length; columnIndex++) {
            characters[columnIndex].draw(gc, imageCache, columnIndex + offset.x, rowIndex + offset.y);
        }
    }

    /** @return The length of the string. */
    public int length() {
        return characters.length;
    }

    /**
     * Determines whether or not the specified range is valid by throwing an
     * exception if the range is invalid.
     *
     * @param range
     *        The range.
     *
     * @throws NullPointerException
     *        If the range is null.
     *
     * @throws IllegalArgumentException
     *        If the start of the range is less than zero.
     *        If the end of the range is greater than the length of the string.
     */
    public void checkRangeValidity(final @NonNull IntRange range) {
        if (range.getStart() < 0) {
            throw new IllegalArgumentException("The start (" + range.getStart() + ") of the range is less than zero.");
        }

        if (range.getEnd() > characters.length) {
            throw new IllegalArgumentException("The end (" + range.getEnd() + ") of the range is greater than the "
                                              + " length (" + characters.length + ")  of the string.");
        }
    }

    /**
     * Sets a new character in the specified position.
     *
     * @param column
     *         The x-axis (column) coordinate to place the character at.
     *
     * @param character
     *         The new character.
     *
     * @throws NullPointerException
     *         If the character is null.
     *
     * @throws IllegalArgumentException
     *         If the column is less than 0.
     *         If the column is greater than the length of the string.
     */
    public void setCharacter(final int column, final @NonNull AsciiCharacter character) {
        if (column < 0) {
            throw new IllegalArgumentException("The column index " + column + " cannot be below 0.");
        }

        if (column >= characters.length) {
            throw new IllegalArgumentException("The column index " + column + " cannot be greater than or equal to "
                                               + characters.length + ".");
        }

        characters[column] = character;
    }

    /**
     * Sets a new character in the specified position.
     *
     * @param column
     *         The x-axis (column) coordinate to place the character at.
     *
     * @param character
     *         The new character.
     *
     * @throws IllegalArgumentException
     *         If the column is less than 0.
     *         If the column is greater than the length of the string.
     */
    public void setCharacter(final int column, final char character) {
        if (column < 0) {
            throw new IllegalArgumentException("The column index " + column + " cannot be below 0.");
        }

        if (column >= characters.length) {
            throw new IllegalArgumentException("The column index " + column + " cannot be greater than or equal to "
                                               + characters.length + ".");
        }

        characters[column].setCharacter(character);
    }

    /**
     * Sets every character in the specified range to the specified character.
     *
     * @param character
     *         The character to change to.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the change between.
     *         Includes the first index and excludes the last index.
     */
    public void setCharacters(final char character, final IntRange range) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].setCharacter(character);
        }
    }

    /**
     * Sets every character to the specified character.
     *
     * @param character
     *         The character to change to.
     */
    public void setAllCharacters(final char character) {
        for (final AsciiCharacter c : characters) {
            c.setCharacter(character);
        }
    }

    /**
     * Applies a color gradient to the entire string.
     *
     * @param colorFrom
     *         The color to begin the gradient with.
     *
     * @param colorTo
     *         The color to end the gradient with.
     *
     * @param applyToBackground
     *         Whether or not to apply the gradient to the background or foreground of the characters.
     *
     * @throws NullPointerException
     *         If the colorFrom or colorTo is null.
     */
    public void applyColorGradient(final @NonNull Color colorFrom, final @NonNull Color colorTo, final boolean applyToBackground) {
        applyColorGradient(new IntRange(0, characters.length), colorFrom, colorTo, applyToBackground);
    }

    /**
     * Applies a color gradient to a portion of the string.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end
     *         the gradient between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @param colorFrom
     *         The color to begin the gradient with.
     *
     * @param colorTo
     *         The color to end the gradient with.
     *
     * @param applyToBackground
     *         Whether or not to apply the gradient to the background or
     *         foreground of the characters.
     *
     * @throws NullPointerException
     *         If the rande, colorFrom, or colorTo is null.
     */
    public void applyColorGradient(final @NonNull IntRange range, final @NonNull Color colorFrom, final @NonNull Color colorTo, final boolean applyToBackground) {
        checkRangeValidity(range);

        // Determine the difference between the RGB values of the colors:
        final float redDifference = colorTo.getRed() - colorFrom.getRed();
        final float greenDifference = colorTo.getGreen() - colorFrom.getGreen();
        final float blueDifference = colorTo.getBlue() - colorFrom.getBlue();

        // Determine the amount to increment the RGB values by and convert the values to the 0-1 scale:
        final float redChangePerColumn = (redDifference / characters.length) / 255f;
        final float greenChangePerColumn = (greenDifference / characters.length) / 255f;
        final float blueChangePerColumn = (blueDifference / characters.length) / 255f;

        // Set the starting RGB values and convert them to the 0-1 scale:
        float redCurrent = colorFrom.getRed() / 255f;
        float greenCurrent = colorFrom.getGreen() / 255f;
        float blueCurrent = colorFrom.getBlue() / 255f;

        // Set the new color values:
        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            if (applyToBackground) {
                characters[columnIndex].setBackgroundColor(new Color(redCurrent, greenCurrent, blueCurrent));
            } else {
                characters[columnIndex].setForegroundColor(new Color(redCurrent, greenCurrent, blueCurrent));
            }

            redCurrent += redChangePerColumn;
            greenCurrent += greenChangePerColumn;
            blueCurrent += blueChangePerColumn;
        }
    }

    /**
     * Applies a shade gradient to the entire string.
     *
     * @param applyToBackground
     *         Whether or not to apply the gradient to the background or
     *         foreground of the characters.
     *
     * @throws NullPointerException
     *         If the color is null.
     */
    public void applyShadeGradient(final boolean applyToBackground) {
        applyShadeGradient(new IntRange(0, characters.length), applyToBackground);
    }

    /**
     * Applies a shade gradient to a portion of the string.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end
     *         the gradient between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @param applyToBackground
     *         Whether or not to apply the gradient to the background or
     *         foreground of the characters.
     *
     * @throws NullPointerException
     *         If the color is null.
     */
    public void applyShadeGradient(final @NonNull IntRange range, final boolean applyToBackground) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            final ShadeShader shadeShader = new ShadeShader();
            double shadeFactor = (endIndex - columnIndex) / (double) endIndex;

            if (applyToBackground) {
                shadeShader.setShadeBackground(true);
                shadeShader.setBackgroundShadeFactor(shadeFactor);
            } else {
                shadeShader.setShadeForeground(true);
                shadeShader.setForegroundShadeFactor(shadeFactor);
            }

            characters[columnIndex].addShaders(shadeShader);
        }
    }

    /**
     * Applies a tint gradient to the entire string.
     *
     * @param applyToBackground
     *         Whether or not to apply the gradient to the background or
     *         foreground of the characters.
     *
     * @throws NullPointerException
     *         If the color is null.
     */
    public void applyTintGradient(final boolean applyToBackground) {
        applyTintGradient(new IntRange(0, characters.length), applyToBackground);
    }

    /**
     * Applies a tint gradient to a portion of the string.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         gradient between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @param applyToBackground
     *         Whether or not to apply the gradient to the background or
     *         foreground of the characters.
     *
     * @throws NullPointerException
     *         If the range or color is null.
     */
    public void applyTintGradient(final @NonNull IntRange range, final boolean applyToBackground) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            final TintShader tintShader = new TintShader();
            double tintFactor = columnIndex / (double) endIndex;


            if (applyToBackground) {
                tintShader.setTintBackground(true);
                tintShader.setBackgroundTintFactor(tintFactor);
            } else {
                tintShader.setTintForeground(true);
                tintShader.setForegroundTintFactor(tintFactor);
            }

            characters[columnIndex].addShaders(tintShader);
        }
    }

    /**
     * Enables the blink effect for every character.
     *
     * @param millsBetweenBlinks
     *         The amount of time, in milliseconds, before the blink effect can
     *         occur.
     *
     * @param radio
     *         The Radio to transmit a DRAW event to whenever a blink occurs.
     *
     * @throws NullPointerException
     *         If the radio is null.
     */
    public void enableBlinkEffect(final short millsBetweenBlinks, final @NonNull Radio<String> radio) {
        for (final AsciiCharacter c : characters) {
            c.enableBlinkEffect(millsBetweenBlinks, radio);
        }
    }

    /** Disables the blink effect for every character. */
    public void disableBlinkEffect() {
        for (final AsciiCharacter c : characters) {
            c.disableBlinkEffect();
        }
    }

    /**
     * Enables the blink effect for every character in the specified range.
     *
     * @param millsBetweenBlinks
     *         The amount of time, in milliseconds, before the blink effect can
     *         occur.
     *
     * @param radio
     *         The Radio to transmit a DRAW event to whenever a blink occurs.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         change between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @throws NullPointerException
     *         If the radio or range is null.
     */
    public void enableBlinkEffect(final short millsBetweenBlinks, final @NonNull Radio<String> radio, final @NonNull IntRange range) {
        Objects.requireNonNull(radio);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].enableBlinkEffect(millsBetweenBlinks, radio);
        }
    }

    /**
     * Disables the blink effect for every character in the specified range.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         change between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @throws NullPointerException
     *         If the range is null.
     */
    public void disableBlinkEffect(final @NonNull IntRange range) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].disableBlinkEffect();
        }
    }

    /** Swaps the background and foreground colors of every character. */
    public void invertColors() {
        invertColors(new IntRange(0, characters.length));
    }

    /**
     * Swaps the background and foreground colors of every character in the
     * specified range.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         inversion between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @throws NullPointerException
     *         If the range is null.
     */
    public void invertColors(final @NonNull IntRange range) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].invertColors();
        }
    }

    /**
     * Sets the background color of all characters.
     *
     * @param color
     *         The new background color.
     *
     * @throws NullPointerException
     *         If the color is null.
     */
    public void setBackgroundColor(final @NonNull Color color) {
        setBackgroundColor(color, new IntRange(0, characters.length));
    }

    /**
     * Sets the foreground color of all characters.
     *
     * @param color
     *         The new foreground color.
     *
     * @throws NullPointerException
     *         If the color is null.
     */
    public void setForegroundColor(final @NonNull Color color) {
        setForegroundColor(color, new IntRange(0, characters.length));
    }

    /**
     * Sets the background color of the specified range of characters.
     *
     * @param color
     *         The new background color.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         change between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @throws NullPointerException
     *         If the color or range is null.
     */
    public void setBackgroundColor(final @NonNull Color color, final @NonNull IntRange range) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].setBackgroundColor(color);
        }
    }

    /**
     * Sets the foreground color of the specified range of characters.
     *
     * @param color
     *         The new foreground color.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         change between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @throws NullPointerException
     *         If the color or range is null.
     */
    public void setForegroundColor(final @NonNull Color color, final @NonNull IntRange range) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].setForegroundColor(color);
        }
    }

    /**
     * Sets all characters to either be hidden or visible.
     *
     * @param isHidden
     *         Whether or not the characters are to be hidden.
     */
    public void setHidden(final boolean isHidden) {
        for (int columnIndex = 0 ; columnIndex < characters.length ; columnIndex++) {
            characters[columnIndex].setHidden(isHidden);
        }
    }

    /**
     * Sets all characters to either be underlined or not underlined when
     * being drawn.
     *
     * @param underline
     *        Whether or not the characters should be underlined.
     */
    public void setUnderlined(final boolean underline) {
        setUnderlined(new IntRange(0, characters.length), underline);
    }

    /**
     * Sets the specified range of characters to be underlined when being drawn.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         change between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @param underline
     *        Whether or not the characters should be underlined.
     *
     * @throws NullPointerException
     *         If the range is null.
     */
    public void setUnderlined(final @NonNull IntRange range, final boolean underline) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].setUnderlined(underline);
        }
    }

    /**
     * Adds one or more shaders to all characters.
     *
     * @param shaders
     *          The shaders.
     */
    public void addShaders(final @NonNull Shader ... shaders) {
        addShaders(new IntRange(0, characters.length), shaders);
    }

    /**
     * Adds one or more shaders to a range of characters.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         change between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @param shaders
     *          The shaders.
     */
    public void addShaders(final @NonNull IntRange range, final @NonNull Shader ... shaders) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].addShaders(shaders);
        }
    }

    /**
     * Removes one or more shaders from a range of characters.
     *
     * @param shaders
     *          The shaders.
     */
    public void removeShaders(final @NonNull Shader ... shaders) {
        removeShaders(new IntRange(0, characters.length), shaders);
    }

    /**
     * Removes one or more shaders to a range of characters.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         change between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @param shaders
     *          The shaders.
     */
    public void removeShaders(final @NonNull IntRange range, final @NonNull Shader ... shaders) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].removeShaders(shaders);
        }
    }

    /**
     * Removes all of the shaders from a range of characters.
     *
     * @param range
     *         The x-axis (column) coordinates of the characters to begin/end the
     *         change between.
     *
     *         Includes the first index and excludes the last index.
     *
     * @param range
     *          The range.
     */
    public void removeAllShaders(final @NonNull IntRange range) {
        checkRangeValidity(range);

        final int beginIndex = range.getStart();
        final int endIndex = range.getEnd();

        for (int columnIndex = beginIndex ; columnIndex < endIndex ; columnIndex++) {
            characters[columnIndex].removeAllShaders();
        }
    }
}