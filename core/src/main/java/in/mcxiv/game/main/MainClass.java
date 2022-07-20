package in.mcxiv.game.main;

import com.badlogic.gdx.ApplicationAdapter;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class MainClass extends ApplicationAdapter {

//    private static final ArchetypeBuilder PLAYER_ARCHETYPE_BUILDER = new ArchetypeBuilder()
//            .add(Position.class)
//            .add(DynamicObject.class);
//
//    public static final int WIDTH = 600;
//    public static final int HEIGHT = 300;
//    public static final int SIZE = 30;
//
//    public SpriteBatch batch;
//    private Texture image;
//    private Texture pixel;
//    public ShapeDrawer shapeDrawer;
//    OrthographicCamera camera;
//    ExtendViewport viewport;
//    public MapGrid cellMap;
//    int screenX, screenY;
//
//    World world;
//
//    @Override
//    public void create() {
//        batch = new SpriteBatch();
//        image = new Texture("libgdx.png");
//
//        Pixmap map = new Pixmap(1, 1, Pixmap.Format.RGB565);
//        map.setColor(Color.WHITE);
//        map.drawPixel(0, 0);
//        pixel = new Texture(map);
//        shapeDrawer = new ShapeDrawer(batch, new TextureRegion(pixel));
//
//        cellMap = new MapGrid("default_world.bitmap");
//
//        camera = new OrthographicCamera();
//        viewport = new ExtendViewport(WIDTH, HEIGHT, camera);
//        camera.position.set(WIDTH / 2f, HEIGHT / 2f, 0);
//
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean mouseMoved(int screenX, int screenY) {
//                MainClass.this.screenX = screenX;
//                MainClass.this.screenY = screenY;
//                return super.mouseMoved(screenX, screenY);
//            }
//        });
//
//        world = new World(new WorldConfigurationBuilder()
//                .with(
//                        new GravitySystem(),
//                        new BoxRenderer(this),
//                        new MapCellCollision(this)
//                ).build());
//
//
//        Position position = world.getMapper(Position.class).get(world.create(PLAYER_ARCHETYPE_BUILDER.build(world)));
//        Vector2 spawn = cellMap.getSpawn();
//        position.x = spawn.x;
//        position.y = spawn.y;
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        camera.update();
//        batch.setProjectionMatrix(camera.combined);
//
//        batch.begin();
//
//        batch.draw(image, 140, 210);
//
//        shapeDrawer.rectangle(10, 10, WIDTH - 20, HEIGHT - 20, Color.RED);
//
//        for (int i = 0, s = cellMap.cells.size; i < s; i++) {
//            MapCell cell = cellMap.cells.get(i);
//            shapeDrawer.filledRectangle(cell.x * SIZE, cell.y * SIZE, SIZE, SIZE, cell.getColor());
//        }
//
//        world.setDelta(Gdx.graphics.getDeltaTime());
//        world.process();
//
//        batch.end();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }
//
//    @Override
//    public void dispose() {
//        batch.dispose();
//        image.dispose();
//        pixel.dispose();
//    }
}