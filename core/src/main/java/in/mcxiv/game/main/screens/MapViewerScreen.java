package in.mcxiv.game.main.screens;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.kotcrab.vis.ui.widget.VisTable;
import in.mcxiv.game.main.MainEngine;
import in.mcxiv.game.main.Resources;
import in.mcxiv.game.systems.MapRenderingSystem;
import in.mcxiv.game.systems.TextureRegionRenderingSystem;
import in.mcxiv.game.util.GdxUtils;
import in.mcxiv.game.util.InputProcessorIAdapter;

import static in.mcxiv.game.main.MainEngine.INV_TILE;
import static in.mcxiv.game.main.MainEngine.TILE;

public class MapViewerScreen extends ScreenAdapter implements InputProcessorIAdapter {

    public static final float CAMERA_MOVEMENT_SPEED = 2.5f;
    private static final WorldConfiguration CONFIGURATION = new WorldConfigurationBuilder()
            .with(
                    new MapRenderingSystem(),
                    new TextureRegionRenderingSystem()
            ).build();

    //////////////////////////

    public Stage mainStage;
    public VisTable mainTable;

    //////////////////////////

    Resources res;
    OrthographicCamera camera;
    ExtendViewport viewport;
    World world;

    //////////////////////////

    Vector3 mouseCoords = new Vector3();
    Vector2[] mouseCoordsSnapped = {/* OLD */ new Vector2(),/* NEW */ new Vector2()};
    boolean[] keys_down = new boolean[256]; // For the 256 keys one can press

    @Override
    public void show() {
        prepareUIStage();
        prepareGDrawing();
    }

    private void prepareUIStage() {
        mainStage = new Stage(new ExtendViewport(MainEngine.WIDTH_VP, MainEngine.HEIGHT_VP));

        mainTable = new VisTable();
        MainEngine.ifDebugging(() -> mainTable.setDebug(true));
        mainTable.setFillParent(true);
        mainTable.add(GdxUtils.createButton("Back", () -> MainEngine.getInstance().setScreen(MainEngine.getInstance().srn_home)))
                .expand().pad(10).align(Align.topLeft);
        mainStage.addActor(mainTable);

        Gdx.input.setInputProcessor(new InputMultiplexer(mainStage, this));

    }

    private void prepareGDrawing() {
        res = MainEngine.getInstance().res;

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(MainEngine.WIDTH_VP, MainEngine.HEIGHT_VP, camera);
        mouseCoords.set(camera.position);

        world = new World(CONFIGURATION);
        world.getSystem(MapRenderingSystem.class)
                .loadMap("default_world.bitmap", world);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        snapMouseCoordinates();
        processInputsBasedStateChanging();

        camera.update();
        res.batch.setProjectionMatrix(camera.combined);
        res.batch.begin();

        world.setDelta(delta);
        world.process();

        res.shapeDrawer.rectangle(mouseCoordsSnapped[0].x, mouseCoordsSnapped[0].y, TILE, TILE, Color.YELLOW);

        res.batch.end();

        mainStage.act(delta);
        mainStage.draw();
    }

    private void snapMouseCoordinates() {
        Vector2 old = mouseCoordsSnapped[1];
        old.set(mouseCoords.x, mouseCoords.y);
        old.scl(INV_TILE);
        old.set(MathUtils.floor(old.x), MathUtils.floor(old.y));
        old.scl(TILE);
        mouseCoordsSnapped[0].lerp(old, 0.15f);
    }

    private void processInputsBasedStateChanging() {
        if (keys_down[Keys.W]) camera.translate(0, CAMERA_MOVEMENT_SPEED);
        if (keys_down[Keys.A]) camera.translate(-CAMERA_MOVEMENT_SPEED, 0);
        if (keys_down[Keys.S]) camera.translate(0, -CAMERA_MOVEMENT_SPEED);
        if (keys_down[Keys.D]) camera.translate(CAMERA_MOVEMENT_SPEED, 0);
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
        viewport.update(width, height);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        camera.unproject(mouseCoords.set(screenX, screenY, 0));
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        camera.zoom += amountY * 0.1;
        camera.zoom = MathUtils.clamp(camera.zoom, 1 / 64f, 10);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.W:
            case Keys.A:
            case Keys.S:
            case Keys.D:
                keys_down[keycode] = true;
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.W:
            case Keys.A:
            case Keys.S:
            case Keys.D:
                keys_down[keycode] = false;
                return true;
        }
        return false;
    }

    @Override
    public void dispose() {
        mainStage.dispose();
    }
}
