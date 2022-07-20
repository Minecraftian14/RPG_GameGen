package in.mcxiv.game.components;


import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import in.mcxiv.game.main.Resources;

public class MapCell extends Component {

//    private static final Color[] COLORS = new Color[]{Color.SCARLET, Color.LIME, Color.GREEN, Color.CYAN, Color.BROWN, Color.CORAL, Color.ROYAL, Color.FIREBRICK};

    public int type;
    public TextureRegion texture;

    public MapCell construct(int type) {
        this.type = type;
        this.texture = new TextureRegion(Resources.getInstance().plastic_tiles,
                (type % 5) * 64,
                (type / 5) * 64,
                64, 64
        );
        return this;
    }
}
