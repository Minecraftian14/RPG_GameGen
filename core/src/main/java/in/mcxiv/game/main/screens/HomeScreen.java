package in.mcxiv.game.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.kotcrab.vis.ui.widget.VisTable;
import in.mcxiv.game.main.MainEngine;
import in.mcxiv.game.util.GdxUtils;

public class HomeScreen extends ScreenAdapter {
    public Stage mainStage;
    public VisTable mainTable;

    @Override
    public void show() {
        mainStage = new Stage(new ExtendViewport(MainEngine.WIDTH_VP, MainEngine.HEIGHT_VP));

        mainTable = new VisTable();
        MainEngine.ifDebugging(() -> mainTable.setDebug(true));
        mainTable.setFillParent(true);
        mainTable.add(GdxUtils.createButton("Map Viewer", () -> MainEngine.getInstance().setScreen(MainEngine.getInstance().srn_mapViewer)));
        mainStage.addActor(mainTable);

        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.act(delta);
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        mainStage.dispose();
    }
}
