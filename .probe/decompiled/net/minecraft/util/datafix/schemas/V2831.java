package net.minecraft.util.datafix.schemas;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.util.datafix.fixes.References;

public class V2831 extends NamespacedSchema {

    public V2831(int int0, Schema schema1) {
        super(int0, schema1);
    }

    public void registerTypes(Schema schema0, Map<String, Supplier<TypeTemplate>> mapStringSupplierTypeTemplate1, Map<String, Supplier<TypeTemplate>> mapStringSupplierTypeTemplate2) {
        super.registerTypes(schema0, mapStringSupplierTypeTemplate1, mapStringSupplierTypeTemplate2);
        schema0.registerType(true, References.UNTAGGED_SPAWNER, () -> DSL.optionalFields("SpawnPotentials", DSL.list(DSL.fields("data", DSL.fields("entity", References.ENTITY_TREE.in(schema0)))), "SpawnData", DSL.fields("entity", References.ENTITY_TREE.in(schema0))));
    }
}