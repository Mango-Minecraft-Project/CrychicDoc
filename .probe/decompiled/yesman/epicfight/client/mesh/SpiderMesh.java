package yesman.epicfight.client.mesh;

import java.util.Map;
import yesman.epicfight.api.client.model.AnimatedMesh;
import yesman.epicfight.api.client.model.Mesh;
import yesman.epicfight.api.client.model.ModelPart;
import yesman.epicfight.api.client.model.VertexIndicator;

public class SpiderMesh extends AnimatedMesh {

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> head;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> middleStomach;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> bottomStomach;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> leftLeg1;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> leftLeg2;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> leftLeg3;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> leftLeg4;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> rightLeg1;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> rightLeg2;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> rightLeg3;

    public final ModelPart<VertexIndicator.AnimatedVertexIndicator> rightLeg4;

    public SpiderMesh(Map<String, float[]> arrayMap, AnimatedMesh parent, Mesh.RenderProperties properties, Map<String, ModelPart<VertexIndicator.AnimatedVertexIndicator>> parts) {
        super(arrayMap, parent, properties, parts);
        this.head = this.getOrLogException(parts, "head");
        this.middleStomach = this.getOrLogException(parts, "middleStomach");
        this.bottomStomach = this.getOrLogException(parts, "bottomStomach");
        this.leftLeg1 = this.getOrLogException(parts, "leftLeg1");
        this.leftLeg2 = this.getOrLogException(parts, "leftLeg2");
        this.leftLeg3 = this.getOrLogException(parts, "leftLeg3");
        this.leftLeg4 = this.getOrLogException(parts, "leftLeg4");
        this.rightLeg1 = this.getOrLogException(parts, "rightLeg1");
        this.rightLeg2 = this.getOrLogException(parts, "rightLeg2");
        this.rightLeg3 = this.getOrLogException(parts, "rightLeg3");
        this.rightLeg4 = this.getOrLogException(parts, "rightLeg4");
    }
}