package com.valkryst.VTerminal.builder.component;

import com.valkryst.VTerminal.component.TextArea;
import lombok.*;

import java.awt.Color;
import java.util.regex.Pattern;

@EqualsAndHashCode(callSuper=true)
@ToString
public class TextAreaBuilder extends ComponentBuilder<TextArea> {
    /** The width of the text area, in characters. */
    @Getter @Setter private int width;
    /** The height of the text area, in characters. */
    @Getter @Setter private int height;

    /** The maximum number of characters that the field can contain along the x-axis. */
    @Getter @Setter private int maxHorizontalCharacters;
    /** The maximum number of characters that the field can contain along the y-axis. */
    @Getter @Setter private int maxVerticalCharacters;

    /** The foreground color of the caret. */
    @Getter @Setter @NonNull private Color caretForegroundColor;
    /** The background color of the caret. */
    @Getter @Setter @NonNull private Color caretBackgroundColor;

    /** The foreground color of non-caret characters. */
    @Getter @Setter @NonNull private Color foregroundColor;
    /** The background color of non-caret characters. */
    @Getter @Setter @NonNull private Color backgroundColor;

    /** Whether or not the HOME key can be used to move the caret to the first index of the current line. */
    @Getter @Setter private boolean homeKeyEnabled;
    /** Whether or not the END key can be used to move the caret to the last index of the current line. */
    @Getter @Setter private boolean endKeyEnabled;
    /** Whether or not the PAGE UP key can be used to move the caret to the first row. */
    @Getter @Setter private boolean pageUpKeyEnabled;
    /** Whether or nor the PAGE DOWN key can be used to move the caret to the last row. */
    @Getter @Setter private boolean pageDownKeyEnabled;
    /** Whether or not the DELETE key can be used to erase the character that the caret is on. */
    @Getter @Setter private boolean deleteKeyEnabled;
    /** Whether or not the LEFT ARROW key can be used to move the caret one index to the left. */
    @Getter @Setter private boolean leftArrowKeyEnabled;
    /** Whether or not the RIGHT ARROW key can be used to move the caret one index to the right. */
    @Getter @Setter private boolean rightArrowKeyEnabled;
    /** Whether or not the UP ARROW key can be used to move the caret one index up. */
    @Getter @Setter private boolean upArrowKeyEnabled;
    /** Whether or not the DOWN ARROW key can be used to move the caret one index up. */
    @Getter @Setter private boolean downArrowKeyEnabled;
    /** Whether or not the ENTER key can be used to advance the caret to the first position of the next line. */
    @Getter @Setter private boolean enterKeyEnabled;
    /** Whether or not the BACK SPACE key can be used to erase the character before the caret and move the caret backwards. */
    @Getter @Setter private boolean backSpaceKeyEnabled;
    /** Whether or not the TAB key can be used to indent by some number of spaces. */
    @Getter @Setter private boolean tabKeyEnabled;

    /** The amount of spaces to insert when the TAB key is pressed. */
    @Getter private int tabSize;

    /** The pattern used to determine which typed characters can be entered into the field. */
    @Getter @Setter @NonNull private Pattern allowedCharacterPattern;

    @Override
    public TextArea build() {
        checkState();
        return new TextArea(this);
    }

    /**
     * Checks the current state of the builder.
     *
     * @throws IllegalArgumentException
     *          If the width, height, or maximum horizontal/vertical characters is less than one.
     */
    protected void checkState() throws NullPointerException {
        super.checkState();

        if (width < 1) {
            throw new IllegalArgumentException("The width cannot be less than one.");
        }

        if (height < 1) {
            throw new IllegalArgumentException("The height cannot be less than one.");
        }

        if (maxHorizontalCharacters < 1) {
            throw new IllegalArgumentException("The maximum horizontal characters cannot be less than one.");
        }

        if (maxVerticalCharacters < 1) {
            throw new IllegalArgumentException("The maximum vertical characters cannot be less than one.");
        }

        if (tabSize < 1) {
            throw new IllegalArgumentException("The tab size cannot be less than one character.");
        }

        if (maxHorizontalCharacters < width) {
            maxHorizontalCharacters = width;
        }

        if (maxVerticalCharacters < height) {
            maxVerticalCharacters = height;
        }
    }

    /** Resets the builder to it's default state. */
    public void reset() {
        super.reset();

        width = 4;
        height = 4;
        maxHorizontalCharacters = 4;
        maxVerticalCharacters = 4;

        caretForegroundColor = new Color(0xFF8E999E, true);
        caretBackgroundColor = new Color(0xFF68D0FF, true);

        foregroundColor = caretBackgroundColor;
        backgroundColor = caretForegroundColor;

        homeKeyEnabled = true;
        endKeyEnabled = true;
        pageUpKeyEnabled = true;
        pageDownKeyEnabled = true;
        deleteKeyEnabled = true;
        leftArrowKeyEnabled = true;
        rightArrowKeyEnabled = true;
        upArrowKeyEnabled = true;
        downArrowKeyEnabled = true;
        enterKeyEnabled = true;
        backSpaceKeyEnabled = true;
        tabKeyEnabled = true;

        tabSize = 4;

        allowedCharacterPattern = Pattern.compile("^[a-zA-z0-9$-/:-?{-~!\"^_`\\[\\]@# ]$");
    }
}
