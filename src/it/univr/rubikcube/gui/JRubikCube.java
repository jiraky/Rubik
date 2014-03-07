package it.univr.rubikcube.gui;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
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
    private SimpleUniverse universe;
    private BranchGroup scene;
    private TransformGroup sceneTransform;
    
    /**
     * Creates a new instance of the Rubik cube control.
     */
    public JRubikCube() {
        super(SimpleUniverse.getPreferredConfiguration());
        // Create the universe
        this.universe = new SimpleUniverse(this);
        // Move the view back a bit to allow all objects to be viewed
        this.universe.getViewingPlatform().setNominalViewingTransform();
        // Ensure every frame is at least 5 msec long so that it can be noticed
        this.universe.getViewer().getView().setMinimumFrameCycleTime(5);
        // Create the scene graph.
        this.scene = new BranchGroup();
        // Add an identity transformation we can edit later
        this.sceneTransform = new TransformGroup();
        this.sceneTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        this.scene.addChild(this.sceneTransform);
        // TODO Add all the subcubes here
        this.sceneTransform.addChild(new ColorCube(0.4));
        // Optimize the scene graph and add it to the universe
        this.scene.compile();
        this.universe.addBranchGraph(this.scene);
    }
}
