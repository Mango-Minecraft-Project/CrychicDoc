package net.minecraft.world.scores.criteria;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatType;
import net.minecraft.util.StringRepresentable;

public class ObjectiveCriteria {

    private static final Map<String, ObjectiveCriteria> CUSTOM_CRITERIA = Maps.newHashMap();

    private static final Map<String, ObjectiveCriteria> CRITERIA_CACHE = Maps.newHashMap();

    public static final ObjectiveCriteria DUMMY = registerCustom("dummy");

    public static final ObjectiveCriteria TRIGGER = registerCustom("trigger");

    public static final ObjectiveCriteria DEATH_COUNT = registerCustom("deathCount");

    public static final ObjectiveCriteria KILL_COUNT_PLAYERS = registerCustom("playerKillCount");

    public static final ObjectiveCriteria KILL_COUNT_ALL = registerCustom("totalKillCount");

    public static final ObjectiveCriteria HEALTH = registerCustom("health", true, ObjectiveCriteria.RenderType.HEARTS);

    public static final ObjectiveCriteria FOOD = registerCustom("food", true, ObjectiveCriteria.RenderType.INTEGER);

    public static final ObjectiveCriteria AIR = registerCustom("air", true, ObjectiveCriteria.RenderType.INTEGER);

    public static final ObjectiveCriteria ARMOR = registerCustom("armor", true, ObjectiveCriteria.RenderType.INTEGER);

    public static final ObjectiveCriteria EXPERIENCE = registerCustom("xp", true, ObjectiveCriteria.RenderType.INTEGER);

    public static final ObjectiveCriteria LEVEL = registerCustom("level", true, ObjectiveCriteria.RenderType.INTEGER);

    public static final ObjectiveCriteria[] TEAM_KILL = new ObjectiveCriteria[] { registerCustom("teamkill." + ChatFormatting.BLACK.getName()), registerCustom("teamkill." + ChatFormatting.DARK_BLUE.getName()), registerCustom("teamkill." + ChatFormatting.DARK_GREEN.getName()), registerCustom("teamkill." + ChatFormatting.DARK_AQUA.getName()), registerCustom("teamkill." + ChatFormatting.DARK_RED.getName()), registerCustom("teamkill." + ChatFormatting.DARK_PURPLE.getName()), registerCustom("teamkill." + ChatFormatting.GOLD.getName()), registerCustom("teamkill." + ChatFormatting.GRAY.getName()), registerCustom("teamkill." + ChatFormatting.DARK_GRAY.getName()), registerCustom("teamkill." + ChatFormatting.BLUE.getName()), registerCustom("teamkill." + ChatFormatting.GREEN.getName()), registerCustom("teamkill." + ChatFormatting.AQUA.getName()), registerCustom("teamkill." + ChatFormatting.RED.getName()), registerCustom("teamkill." + ChatFormatting.LIGHT_PURPLE.getName()), registerCustom("teamkill." + ChatFormatting.YELLOW.getName()), registerCustom("teamkill." + ChatFormatting.WHITE.getName()) };

    public static final ObjectiveCriteria[] KILLED_BY_TEAM = new ObjectiveCriteria[] { registerCustom("killedByTeam." + ChatFormatting.BLACK.getName()), registerCustom("killedByTeam." + ChatFormatting.DARK_BLUE.getName()), registerCustom("killedByTeam." + ChatFormatting.DARK_GREEN.getName()), registerCustom("killedByTeam." + ChatFormatting.DARK_AQUA.getName()), registerCustom("killedByTeam." + ChatFormatting.DARK_RED.getName()), registerCustom("killedByTeam." + ChatFormatting.DARK_PURPLE.getName()), registerCustom("killedByTeam." + ChatFormatting.GOLD.getName()), registerCustom("killedByTeam." + ChatFormatting.GRAY.getName()), registerCustom("killedByTeam." + ChatFormatting.DARK_GRAY.getName()), registerCustom("killedByTeam." + ChatFormatting.BLUE.getName()), registerCustom("killedByTeam." + ChatFormatting.GREEN.getName()), registerCustom("killedByTeam." + ChatFormatting.AQUA.getName()), registerCustom("killedByTeam." + ChatFormatting.RED.getName()), registerCustom("killedByTeam." + ChatFormatting.LIGHT_PURPLE.getName()), registerCustom("killedByTeam." + ChatFormatting.YELLOW.getName()), registerCustom("killedByTeam." + ChatFormatting.WHITE.getName()) };

    private final String name;

    private final boolean readOnly;

    private final ObjectiveCriteria.RenderType renderType;

    private static ObjectiveCriteria registerCustom(String string0, boolean boolean1, ObjectiveCriteria.RenderType objectiveCriteriaRenderType2) {
        ObjectiveCriteria $$3 = new ObjectiveCriteria(string0, boolean1, objectiveCriteriaRenderType2);
        CUSTOM_CRITERIA.put(string0, $$3);
        return $$3;
    }

    private static ObjectiveCriteria registerCustom(String string0) {
        return registerCustom(string0, false, ObjectiveCriteria.RenderType.INTEGER);
    }

    protected ObjectiveCriteria(String string0) {
        this(string0, false, ObjectiveCriteria.RenderType.INTEGER);
    }

    protected ObjectiveCriteria(String string0, boolean boolean1, ObjectiveCriteria.RenderType objectiveCriteriaRenderType2) {
        this.name = string0;
        this.readOnly = boolean1;
        this.renderType = objectiveCriteriaRenderType2;
        CRITERIA_CACHE.put(string0, this);
    }

    public static Set<String> getCustomCriteriaNames() {
        return ImmutableSet.copyOf(CUSTOM_CRITERIA.keySet());
    }

    public static Optional<ObjectiveCriteria> byName(String string0) {
        ObjectiveCriteria $$1 = (ObjectiveCriteria) CRITERIA_CACHE.get(string0);
        if ($$1 != null) {
            return Optional.of($$1);
        } else {
            int $$2 = string0.indexOf(58);
            return $$2 < 0 ? Optional.empty() : BuiltInRegistries.STAT_TYPE.getOptional(ResourceLocation.of(string0.substring(0, $$2), '.')).flatMap(p_83619_ -> getStat(p_83619_, ResourceLocation.of(string0.substring($$2 + 1), '.')));
        }
    }

    private static <T> Optional<ObjectiveCriteria> getStat(StatType<T> statTypeT0, ResourceLocation resourceLocation1) {
        return statTypeT0.getRegistry().getOptional(resourceLocation1).map(statTypeT0::m_12902_);
    }

    public String getName() {
        return this.name;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public ObjectiveCriteria.RenderType getDefaultRenderType() {
        return this.renderType;
    }

    public static enum RenderType implements StringRepresentable {

        INTEGER("integer"), HEARTS("hearts");

        private final String id;

        public static final StringRepresentable.EnumCodec<ObjectiveCriteria.RenderType> CODEC = StringRepresentable.fromEnum(ObjectiveCriteria.RenderType::values);

        private RenderType(String p_83632_) {
            this.id = p_83632_;
        }

        public String getId() {
            return this.id;
        }

        @Override
        public String getSerializedName() {
            return this.id;
        }

        public static ObjectiveCriteria.RenderType byId(String p_83635_) {
            return (ObjectiveCriteria.RenderType) CODEC.byName(p_83635_, INTEGER);
        }
    }
}