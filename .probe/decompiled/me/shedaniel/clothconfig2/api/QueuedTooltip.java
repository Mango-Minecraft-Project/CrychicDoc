package me.shedaniel.clothconfig2.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import me.shedaniel.math.Point;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.ApiStatus.Internal;

public class QueuedTooltip implements Tooltip {

    private final Point location;

    private final List<FormattedCharSequence> text;

    private QueuedTooltip(Point location, List<FormattedCharSequence> text) {
        this.location = location;
        this.text = Collections.unmodifiableList(text);
    }

    public static QueuedTooltip create(Point location, List<Component> text) {
        return new QueuedTooltip(location, Language.getInstance().getVisualOrder(text));
    }

    public static QueuedTooltip create(Point location, Component... text) {
        return create(location, Arrays.asList(text));
    }

    public static QueuedTooltip create(Point location, FormattedCharSequence... text) {
        return new QueuedTooltip(location, Arrays.asList(text));
    }

    public static QueuedTooltip create(Point location, FormattedText... text) {
        return new QueuedTooltip(location, Language.getInstance().getVisualOrder(Arrays.asList(text)));
    }

    @Override
    public Point getPoint() {
        return this.location;
    }

    @Internal
    @Override
    public List<FormattedCharSequence> getText() {
        return this.text;
    }
}