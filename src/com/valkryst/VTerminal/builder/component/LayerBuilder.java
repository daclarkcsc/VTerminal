package com.valkryst.VTerminal.builder.component;

import com.valkryst.VTerminal.component.Layer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.Color;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class LayerBuilder extends ComponentBuilder<Layer> {
    /** The foreground color. */
    @NonNull private Color foregroundColor;
    /** The background color. */
    @NonNull private Color backgroundColor;

    @Override
    public Layer build() {
        checkState();
        return new Layer(this);
    }

    @Override
    public void reset() {
        super.reset();

        super.width = 1;
        super.height = 1;

        backgroundColor = new Color(45, 45, 45, 255);
        foregroundColor = Color.WHITE;
    }

    @Override
    public void parse(final @NonNull JSONObject jsonObject) {
        reset();
        super.parse(jsonObject);


        try {
            this.backgroundColor = getColor((JSONArray) jsonObject.get("backgroundColor"));
        } catch (final NullPointerException ignored) {}

        try {
            this.foregroundColor = getColor((JSONArray) jsonObject.get("foregroundColor"));
        } catch (final NullPointerException ignored) {}
    }
}