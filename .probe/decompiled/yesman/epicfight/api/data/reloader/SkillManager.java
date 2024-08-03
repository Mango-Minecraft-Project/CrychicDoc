package yesman.epicfight.api.data.reloader;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.data.loot.SkillBookLootModifier;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.network.server.SPDatapackSyncSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.skill.CapabilitySkill;

public class SkillManager extends SimpleJsonResourceReloadListener {

    private static final Map<ResourceLocation, Skill> SKILLS = Maps.newHashMap();

    private static final Map<ResourceLocation, Skill> LEARNABLE_SKILLS = Maps.newHashMap();

    private static final Map<ResourceLocation, CompoundTag> PARAMETER_MAP = Maps.newHashMap();

    private static final Map<ResourceLocation, Pair<? extends Skill.Builder<?>, Function<? extends Skill.Builder<?>, ? extends Skill>>> BUILDERS = Maps.newConcurrentMap();

    private static final Gson GSON = new GsonBuilder().create();

    private static final Random RANDOM = new Random();

    private static int LAST_PICK = 0;

    public static Stream<ResourceLocation> getLearnableSkillNames(Predicate<Skill.Builder<?>> predicate) {
        return BUILDERS.values().stream().map(map -> (Skill.Builder) map.getFirst()).filter(builder -> predicate.test(builder)).map(builder -> builder.getRegistryName());
    }

    public static Skill getSkill(String name) {
        ResourceLocation rl;
        if (name.indexOf(58) >= 0) {
            rl = new ResourceLocation(name);
        } else {
            rl = new ResourceLocation("epicfight", name);
        }
        return SKILLS.containsKey(rl) ? (Skill) SKILLS.get(rl) : null;
    }

    public static String getRandomLearnableSkillName() {
        List<Skill> values = new ArrayList(LEARNABLE_SKILLS.values());
        LAST_PICK = (LAST_PICK + RANDOM.nextInt(values.size() - 1) + 1) % values.size();
        return ((Skill) values.get(LAST_PICK)).toString();
    }

    public static <T extends Skill, B extends Skill.Builder<T>> void register(Function<B, T> constructor, B builder, String modid, String name) {
        ResourceLocation registryName = new ResourceLocation(modid, name);
        try {
            Pair<? extends Skill.Builder<?>, Function<? extends Skill.Builder<?>, ? extends Skill>> pair = Pair.of(builder.setRegistryName(registryName), constructor);
            BUILDERS.put(registryName, pair);
            EpicFightMod.LOGGER.info("Registered skill " + modid + ":" + registryName);
        } catch (Exception var6) {
            var6.printStackTrace();
            throw new IllegalStateException("Failed to register skill " + modid + ":" + registryName);
        }
    }

    public static void buildAll() {
        SkillBuildEvent onBuild = new SkillBuildEvent(BUILDERS, SKILLS, LEARNABLE_SKILLS);
        MinecraftForge.EVENT_BUS.post(onBuild);
        SkillBookLootModifier.createSkillLootTable(BUILDERS.keySet());
    }

    public static Stream<CompoundTag> getDataStream() {
        return PARAMETER_MAP.entrySet().stream().map(entry -> {
            ((CompoundTag) entry.getValue()).putString("id", ((ResourceLocation) entry.getKey()).toString());
            return (CompoundTag) entry.getValue();
        });
    }

    public static void reloadAllSkillsAnimations() {
        SKILLS.values().forEach(skill -> skill.registerPropertiesToAnimation());
    }

    public static int getParamCount() {
        return PARAMETER_MAP.size();
    }

    public SkillManager() {
        super(GSON, "skill_parameters");
    }

    protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager resourceManager, ProfilerFiller profileFiller) {
        buildAll();
        for (Entry<ResourceLocation, JsonElement> entry : objectIn.entrySet()) {
            CompoundTag tag = null;
            try {
                tag = TagParser.parseTag(((JsonElement) entry.getValue()).toString());
            } catch (CommandSyntaxException var8) {
                var8.printStackTrace();
            }
            if (SKILLS.containsKey(entry.getKey())) {
                ((Skill) SKILLS.get(entry.getKey())).setParams(tag);
                PARAMETER_MAP.put((ResourceLocation) entry.getKey(), tag);
            } else {
                EpicFightMod.LOGGER.warn("Skill " + entry.getKey() + " not exists");
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void processServerPacket(SPDatapackSyncSkill packet) {
        buildAll();
        for (CompoundTag tag : packet.getTags()) {
            if (!SKILLS.containsKey(new ResourceLocation(tag.getString("id")))) {
                EpicFightMod.LOGGER.warn("Failed to syncronize Datapack for skill: " + tag.getString("id"));
            } else {
                ((Skill) SKILLS.get(new ResourceLocation(tag.getString("id")))).setParams(tag);
            }
        }
        LocalPlayerPatch localplayerpatch = ClientEngine.getInstance().getPlayerPatch();
        if (localplayerpatch != null) {
            CapabilitySkill skillCapability = localplayerpatch.getSkillCapability();
            for (String skillName : packet.getLearnedSkills()) {
                skillCapability.addLearnedSkill(getSkill(skillName));
            }
            for (SkillContainer skill : skillCapability.skillContainers) {
                if (skill.getSkill() != null) {
                    skill.setSkill(getSkill(skill.getSkill().toString()), true);
                }
            }
            skillCapability.skillContainers[SkillCategories.BASIC_ATTACK.universalOrdinal()].setSkill(EpicFightSkills.BASIC_ATTACK);
            skillCapability.skillContainers[SkillCategories.AIR_ATTACK.universalOrdinal()].setSkill(EpicFightSkills.AIR_ATTACK);
            skillCapability.skillContainers[SkillCategories.KNOCKDOWN_WAKEUP.universalOrdinal()].setSkill(EpicFightSkills.KNOCKDOWN_WAKEUP);
        }
    }
}