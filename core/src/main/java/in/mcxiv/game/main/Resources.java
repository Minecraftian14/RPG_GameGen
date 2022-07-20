package in.mcxiv.game.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.kotcrab.vis.ui.VisUI;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Resources implements Disposable {

    public static Resources INSTANCE = null;

    public static Resources getInstance() {
        return INSTANCE;
    }

    public final SpriteBatch batch;
    public final Texture pixel;
    public final ShapeDrawer shapeDrawer;

    public final Texture plastic_tiles;
    public final TextureAtlas atlas;

    public final Animation<TextureRegion> flagAnimation;

    public Resources() {
        if (INSTANCE != null)
            throw new IllegalStateException("Multiple instances of resources is not allowed!");
        INSTANCE = this;

        batch = new SpriteBatch();
        pixel = new Texture("pixel.png");
        shapeDrawer = new ShapeDrawer(batch, new TextureRegion(pixel));

        plastic_tiles = new Texture("images/blocks/plastic_tiles.png");

        atlas = new TextureAtlas("packed/packed.atlas");
        for (TextureAtlas.AtlasRegion region : atlas.getRegions()) {
            System.out.println(region);
        }
        flagAnimation = new Animation<>(1/16f, atlas.findRegions("image_part"), Animation.PlayMode.LOOP);

        VisUI.load();
    }

    @Override
    public void dispose() {
        batch.dispose();
        pixel.dispose();
        VisUI.dispose();
    }
}
