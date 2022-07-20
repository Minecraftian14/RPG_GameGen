package in.mcxiv.game.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTextButton;

import static in.mcxiv.game.main.MainEngine.INV_TILE;

public class GdxUtils {

    public static ChangeListener asListener(Runnable action) {
        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                action.run();
            }
        };
    }

    public static Actor createButton(String text, Runnable action) {
        VisTextButton button = new VisTextButton(text);
        button.addListener(asListener(action));
        return button;
    }

    public static void getLowerBoundOfTheVisibleRectangle(Camera camera, Vector3 bound) {
        bound.set(0, Gdx.graphics.getHeight(), 0);
        camera.unproject(bound).scl(INV_TILE).sub(1, 1, 0);
    }

    public static void getUpperBoundOfTheVisibleRectangle(Camera camera, Vector3 bound) {
        bound.set(Gdx.graphics.getWidth(), 0, 0);
        camera.unproject(bound).scl(INV_TILE);
    }

    public static boolean isPointNotInVisibleRectangle(Vector2 position, Vector3 lowerBound, Vector3 upperBound) {
        return position.x < lowerBound.x || position.x > upperBound.x ||
               position.y < lowerBound.y || position.y > upperBound.y;
    }
}
