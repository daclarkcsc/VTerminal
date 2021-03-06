package com.valkryst.VTerminal.palette;

import com.valkryst.VTerminal.misc.ColorFunctions;
import lombok.Getter;

import java.awt.*;

public class PaleNightColorPalette extends ColorPalette {
    @Getter private final Color defaultBackground = new Color(41, 45, 62, 255);
    @Getter private final Color defaultForeground = new Color(92, 104, 146, 255);

    @Getter private final Color button_defaultBackground = defaultBackground;
    @Getter private final Color button_defaultForeground = new Color(176, 135, 223, 255);
    @Getter private final Color button_hoverBackground = button_defaultForeground;
    @Getter private final Color button_hoverForeground = ColorFunctions.shade(button_hoverBackground, 0.5);
    @Getter private final Color button_pressedBackground = ColorFunctions.shade(button_hoverBackground, 0.25);
    @Getter private final Color button_pressedForeground = ColorFunctions.shade(button_hoverForeground, 0.25);

    @Getter private final Color checkBox_defaultBackground = button_defaultBackground;
    @Getter private final Color checkBox_defaultForeground = button_defaultForeground;
    @Getter private final Color checkBox_hoverBackground = button_hoverBackground;
    @Getter private final Color checkBox_hoverForeground = button_hoverForeground;
    @Getter private final Color checkBox_checkedBackground = button_defaultBackground;
    @Getter private final Color checkBox_checkedForeground = new Color(243, 202, 106, 255);

    @Getter private final Color label_defaultBackground = defaultBackground;
    @Getter private final Color label_defaultForeground = button_defaultForeground;

    @Getter private final Color layer_defaultBackground = defaultBackground;
    @Getter private final Color layer_defaultForeground = Color.WHITE;

    @Getter private final Color progressBar_incompleteBackground = defaultBackground;
    @Getter private final Color progressBar_incompleteForeground = ColorFunctions.shade(new Color(190, 226, 137, 255), 0.5);
    @Getter private final Color progressBar_completeBackground = defaultBackground;
    @Getter private final Color progressBar_completeForeground = new Color(190, 226, 137, 255);

    @Getter private final Color radioButton_defaultBackground = button_defaultBackground;
    @Getter private final Color radioButton_defaultForeground = button_defaultForeground;
    @Getter private final Color radioButton_hoverBackground = button_hoverBackground;
    @Getter private final Color radioButton_hoverForeground = button_hoverForeground;
    @Getter private final Color radioButton_pressedBackground = defaultBackground;
    @Getter private final Color radioButton_pressedForeground = checkBox_checkedForeground;

    @Getter private final Color textArea_defaultBackground = ColorFunctions.tint(defaultBackground, 0.1);
    @Getter private final Color textArea_defaultForeground = new Color(119, 200, 212, 255);
    @Getter private final Color textArea_caretBackground = textArea_defaultForeground;
    @Getter private final Color textArea_caretForeground = textArea_defaultBackground;
}
