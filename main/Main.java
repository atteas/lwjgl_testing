package main;

import org.lwjgl.glfw.GLFW;

import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.io.Input;
import engine.io.ModelLoader;
import engine.io.Window;
import engine.maths.Vector3f;
import engine.objects.Camera;
import engine.objects.GameObject;

public class Main implements Runnable {
    public Thread game;
    public Window window;
    public Renderer renderer;
    public Shader shader;
    public final int WIDTH = 1280, HEIGHT = 760;

    //mesh
    public Mesh mesh = ModelLoader.loadModel("src/resources/models/dragon.obj", "resources/textures/StandingGuy.png");

    public GameObject object = new GameObject(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);

    public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0));

    public void start(){
        game = new Thread(this, "game");
        game.start();
    }

    public void init(){
        window = new Window(WIDTH, HEIGHT, "Monopoli");
        shader = new Shader("resources/shaders/mainVertex.glsl", "resources/shaders/mainFragment.glsl");
        renderer = new Renderer(window, shader);

        window.setBackgroundColor(0.2f, 0.5f, 0.0f);
        window.create();
        mesh.create();
        shader.create();
    }

    //main loop
    public void run(){
        init();
        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            update();
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
            if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) window.mouseState(true);
        }
        close();
    }

    //updating
    private void update(){
        window.update();
        //camera.update(); //first person
        camera.update(object); //3rd person (center to an object)
    }


    //rendering
    private void render(){
        renderer.renderMesh(object, camera);
        window.swapBuffers();
    }


    //closing
    private void close(){
        window.destroy();
        mesh.destroy();
        shader.destroy();
    }

    public static void main(String[] args){
        new Main().start();
    }
}