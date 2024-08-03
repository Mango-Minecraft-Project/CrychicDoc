package fr.frinn.custommachinery.client.screen.creation.component;

import fr.frinn.custommachinery.api.component.IMachineComponentTemplate;
import fr.frinn.custommachinery.client.screen.BaseScreen;
import fr.frinn.custommachinery.client.screen.popup.PopupScreen;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public abstract class ComponentBuilderPopup<T extends IMachineComponentTemplate<?>> extends PopupScreen {

    public static final Component CONFIRM = Component.translatable("custommachinery.gui.popup.confirm").withStyle(ChatFormatting.GREEN);

    public static final Component CANCEL = Component.translatable("custommachinery.gui.popup.cancel").withStyle(ChatFormatting.RED);

    @Nullable
    private final T baseTemplate;

    private final Consumer<T> onFinish;

    private final Component title;

    public ComponentPropertyListWidget propertyList;

    public ComponentBuilderPopup(BaseScreen parent, @Nullable T template, Consumer<T> onFinish, Component title) {
        super(parent, 256, 196);
        this.onFinish = onFinish;
        this.baseTemplate = template;
        this.title = title;
    }

    public abstract T makeTemplate();

    private void confirm() {
        this.onFinish.accept(this.makeTemplate());
        this.parent.closePopup(this);
    }

    private void cancel() {
        this.parent.closePopup(this);
    }

    public Optional<T> baseTemplate() {
        return Optional.ofNullable(this.baseTemplate);
    }

    @Override
    protected void init() {
        super.init();
        ((StringWidget) this.m_142416_(new StringWidget(this.title, this.f_96547_))).m_264152_(this.x + (this.xSize - this.f_96547_.width(this.title)) / 2, this.y + 5);
        this.propertyList = (ComponentPropertyListWidget) this.m_142416_(new ComponentPropertyListWidget(this.x + 5, this.y + 15, this.xSize - 10, this.ySize - 50, 30));
        this.m_142416_(Button.builder(CONFIRM, b -> this.confirm()).bounds(this.x + this.xSize / 3 - 25, this.y + this.ySize - 30, 50, 20).build());
        this.m_142416_(Button.builder(CANCEL, b -> this.cancel()).bounds(this.x + this.xSize / 3 * 2 - 25, this.y + this.ySize - 30, 50, 20).build());
    }

    public boolean checkLong(String s) {
        if (s.isEmpty()) {
            return true;
        } else {
            try {
                Long.parseLong(s);
                return true;
            } catch (NumberFormatException var3) {
                return false;
            }
        }
    }

    public long parseLong(String s) {
        if (s.isEmpty()) {
            return 0L;
        } else {
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException var3) {
                return 0L;
            }
        }
    }
}