package it.univr.rubikcube.gui;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
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
        // Ensure every frame is at least 5 msec long so that it can be noticed
        this.universe.getViewer().getView().setMinimumFrameCycleTime(5);
        // Create the scene graph.
        this.scene = new BranchGroup();
        // Add an identity transformation we can edit later
        this.sceneTransform = new TransformGroup();
        this.sceneTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        this.scene.addChild(this.sceneTransform);
        // TODO Add all the subcubes here
        // FIXME Fix the scale
        //this.sceneTransform.addChild(new ColorCube(0.4));
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                for (int k = -1; k <= 1; ++k) {
                    TransformGroup tg = new TransformGroup();
                    Transform3D transform = new Transform3D();
                    Vector3f vector = new Vector3f(i, j, k);
                    transform.setTranslation(vector);
                    tg.setTransform(transform);
                    // TODO Add the colors
                    tg.addChild(new ColorCube(0.4));
                    this.sceneTransform.addChild(tg);
                }
            }
        }
        // TODO Add the right side light
        Color3f sideLightColor = new Color3f(0.5f, 0.5f, 0.5f);
        BoundingSphere sideLightBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        Vector3f sideLightDirection = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight sideLight = new DirectionalLight(sideLightColor, sideLightDirection);
        sideLight.setInfluencingBounds(sideLightBounds);
        this.sceneTransform.addChild(sideLight);
        // Move the view back a bit to allow all objects to be viewed
        this.universe.getViewingPlatform().setNominalViewingTransform();
        // Allow the view to be rotated with the mouse
        OrbitBehavior orbit = new OrbitBehavior();
        orbit.setReverseRotate(true);
        orbit.setSchedulingBounds(new BoundingSphere());
        this.universe.getViewingPlatform().setViewPlatformBehavior(orbit);
        // Optimize the scene graph and add it to the universe
        this.scene.compile();
        this.universe.addBranchGraph(this.scene);
    }
}
