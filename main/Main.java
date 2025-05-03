package main;

import org.lwjgl.glfw.GLFW;

import engine.io.Input;
import engine.io.Window;

public class Main implements Runnable {
    public Thread game;
    public Window window;
    public final int WIDTH = 800, HEIGHT = 500;

    public void start(){
        game = new Thread(this, "game");
        game.start();
    }

    public void init(){
        System.out.println(("Initializing game."));
        window = new Window(WIDTH, HEIGHT, "Monopoli");
        window.create();
    }

    public void run(){
        init();
        while (!window.shouldClose()) {
            update();
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) return;
        }
        window.destroy();
    }

    private void update(){
        //System.out.println("Updating game.");
        window.update();
    }

    private void render(){
        //System.out.println("Rendering game");
        window.swapBuffers();
    }

    public static void main(String[] args){
        new Main().start();
    }
}