package vazkii.patchouli.client.book.text;

import java.util.List;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

public class Span {

    public final String text;

    public final Style style;

    public final List<Span> linkCluster;

    public final Component tooltip;

    public final Supplier<Boolean> onClick;

    public final int lineBreaks;

    public final int spacingLeft;

    public final int spacingRight;

    public final boolean bold;

    public static Span error(SpanState state, String message) {
        return new Span(state, message, Style.EMPTY.withColor(ChatFormatting.RED));
    }

    public Span(SpanState state, String text) {
        this.text = text;
        this.style = state.peekStyle();
        this.onClick = state.onClick;
        this.linkCluster = state.cluster;
        this.tooltip = state.tooltip;
        this.lineBreaks = state.lineBreaks;
        this.spacingLeft = state.spacingLeft;
        this.spacingRight = state.spacingRight;
        this.bold = this.style.isBold();
        state.lineBreaks = 0;
        state.spacingLeft = 0;
        state.spacingRight = 0;
    }

    private Span(SpanState state, String text, Style style) {
        this.text = text;
        this.style = style;
        this.onClick = null;
        this.linkCluster = null;
        this.tooltip = Component.literal("");
        this.lineBreaks = state.lineBreaks;
        this.spacingLeft = state.spacingLeft;
        this.spacingRight = state.spacingRight;
        this.bold = style.isBold();
        state.lineBreaks = 0;
        state.spacingLeft = 0;
        state.spacingRight = 0;
    }

    public MutableComponent styledSubstring(int start) {
        return Component.literal(this.text.substring(start)).setStyle(this.style);
    }

    public MutableComponent styledSubstring(int start, int end) {
        return Component.literal(this.text.substring(start, end)).setStyle(this.style);
    }
}