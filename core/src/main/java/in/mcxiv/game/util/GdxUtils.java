package in.mcxiv.game.util;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTextButton;

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
}
