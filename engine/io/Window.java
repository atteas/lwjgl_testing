package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
    private int width,  height;
    private String title;
    private long window;
    public int frames;
    public long time;
    public Input input;


    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }


    public void create() {
        //Initialize
        if (!GLFW.glfwInit()){
            System.err.println("Error: GLFW wasn't initialized.");
            return;
        }

        input = new Input();

        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if (window == 0){
            System.err.println("Error: Window wasn't created.");
            return;
        }

        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);
        GLFW.glfwMakeContextCurrent(window);


        //Input
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());


        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);

        time = System.currentTimeMillis();
    }


    public void update() {
        GLFW.glfwPollEvents();

        //Check frames (print every second and reset the counter)
        frames++;
        if (System.currentTimeMillis() > time + 1000){
            GLFW.glfwSetWindowTitle(window, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }


    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }


    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }


    public void destroy() {
        input.destroy();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwTerminate();
    }
}