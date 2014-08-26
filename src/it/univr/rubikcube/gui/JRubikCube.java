package it.univr.rubikcube.gui;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * 3D Rubik Cube control.
 * @author Alessandro Menti
 */
public class JRubikCube extends Canvas3D {
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Minimum cycle time in Hz.
     */
    private static final long MINIMUM_CYCLE_TIME = 5;
    /**
     * Universe.
     */
    private SimpleUniverse universe;
    /**
     * 3D scene.
     */
    private BranchGroup scene;
    /**
     * Scene transformation.
     */
    private TransformGroup sceneTransform;

    /**
     * Creates a new instance of the Rubik cube control.
     */
    public JRubikCube() {
        super(SimpleUniverse.getPreferredConfiguration());
        // Create the universe
        this.universe = new SimpleUniverse(this);
        // Ensure every frame is at least 5 ms long so that it can be noticed
        this.universe.getViewer().getView()
            .setMinimumFrameCycleTime(MINIMUM_CYCLE_TIME);
        // Create the scene graph.
        this.scene = new BranchGroup();
        // Add an identity transformation we can edit later
        this.sceneTransform = new TransformGroup();
        this.sceneTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        this.scene.addChild(this.sceneTransform);
        // FIXME Add the subcubes
        this.sceneTransform.addChild(new ColorCube(0.4));
        // Move the view back a bit to allow all objects to be viewed
        this.universe.getViewingPlatform().setNominalViewingTransform();
        // Allow the view to be rotated with the mouse
        OrbitBehavior orbit = new OrbitBehavior();
        orbit.setReverseRotate(true);
        orbit.setTranslateEnable(false);
        orbit.setSchedulingBounds(new BoundingSphere());
        this.universe.getViewingPlatform().setViewPlatformBehavior(orbit);
        // Optimize the scene graph and add it to the universe
        this.scene.compile();
        this.universe.addBranchGraph(this.scene);
    }
}
