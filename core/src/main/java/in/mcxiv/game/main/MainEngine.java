package in.mcxiv.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import in.mcxiv.game.main.screens.HomeScreen;
import in.mcxiv.game.main.screens.MapEditorScreen;
import in.mcxiv.game.main.screens.MapViewerScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class MainEngine extends Game {

    public static final boolean VR_DEBUGGING = true;

    public static void ifDebugging(Runnable action) {
        if (VR_DEBUGGING) action.run();
    }


    public static final int WIDTH_VP = 600;
    public static final int HEIGHT_VP = 300;

    public static final int TILE = 30;
    public static final int HALF_TILE = TILE / 2;
    public static final float INV_TILE = 1f / TILE;

    public static MainEngine INSTANCE = null;

    public static MainEngine getInstance() {
        return INSTANCE;
    }

    public MainEngine() {
        if (INSTANCE != null)
            throw new IllegalStateException("Don't create multiple instances of core application!");
        INSTANCE = this;
    }

    public Resources res;
    public Screen srn_home;
    public Screen srn_mapViewer;

    public Screen srn_mapEditor;

    @Override
    public void create() {
        res = new Resources();

        srn_home = new HomeScreen();
        srn_mapViewer = new MapViewerScreen();
        srn_mapEditor = new MapEditorScreen();

        setScreen(srn_home);
    }

    @Override
    public void dispose() {
        super.dispose();
        res.dispose();
    }
}