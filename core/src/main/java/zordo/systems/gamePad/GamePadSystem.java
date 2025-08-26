package zordo.systems.gamePad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.ScreenUtils;
import zordo.LegendOfZordo;
import zordo.components.gamePad.ControllerComponent;
import zordo.entities.player_interface.menu.game.LevelMenu;

public class GamePadSystem implements ControllerListener {

    // --- Game State ---
    private Controller activeController;
    LegendOfZordo game;

    public GamePadSystem() {
        game = new LegendOfZordo();
        // Check if any controllers are already connected at startup
        Gdx.app.log("Controller", "Available controllers on startup:");
        try {
            this.activeController = Controllers.getCurrent();
        } catch (NullPointerException e) {
            throw e;
        }
        for (int i = 0; i < Controllers.getControllers().size; i++) {
            Controller controller = Controllers.getControllers().get(i);
            Gdx.app.log("Controller", "  -> " + controller.getName() + " | ID: " + controller.getUniqueId());
            if (activeController == null) {
                activeController = controller; // Assign the first one found
                Gdx.app.log("Controller", "Setting startup controller as active: " + activeController.getName());
            }
        }
        if (activeController == null) {
            Gdx.app.log("Controller", "No controllers detected at startup.");
        }
    }

    @Override
    public void connected(Controller controller) {
        Gdx.app.log("Controller", "Connected: " + controller.getName() + " | Instance ID: " + controller.getUniqueId());
        // If we don't have an active controller yet, use this one
        if (activeController == null) {
            Gdx.app.log("Controller", "Setting as active controller: " + controller.getName());
            activeController = controller;
        }
        // You could also iterate Controllers.getControllers() to pick a specific one
    }

    @Override
    public void disconnected(Controller controller) {
        Gdx.app.log("Controller", "Disconnected: " + controller.getName() + " | Instance ID: " + controller.getUniqueId());
        // If the disconnected controller was our active one, clear it
        if (activeController != null && activeController.getUniqueId().equals(controller.getUniqueId())) {
            Gdx.app.log("Controller", "Active controller disconnected.");
            activeController = null;
            // Optional: Try to find another connected controller
            if (!Controllers.getControllers().isEmpty()) {
                activeController = Controllers.getControllers().first(); // Get the next available
                Gdx.app.log("Controller", "Switched active controller to: " + activeController.getName());
            }
        }
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Gdx.app.log("BUTTON DOWN", controller.getName() + " | Button Down: " + buttonCode);

        if (this.game.isOnLevel) {
            handleGameInput(buttonCode);
        } else if (this.game.isOnPauseMenu) {
            handlePauseInput(buttonCode);
        } else if (this.game.isOnLevelMenu) {
            handleLevelMenuInput(buttonCode);
        } else if (this.game.isOnTitleMenu) {
            handleGameStart(buttonCode);
        }
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        Gdx.app.log("BUTTON UP", controller.getName() + " | Button Up: " + buttonCode);
//        buttonSwitch(controller, buttonCode);
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        // We handle axis movement directly in render(), but you could log it here too
        if (Math.abs(value) < 0.2f) return false;
        Gdx.app.log("ControllerInput", controller.getName() + " | Axis Moved: " + axisCode + " | Value: " + value);
        return false;
    }

    public void handleInput(float deltaTime, LegendOfZordo game) {
        this.game = game;
    }

    public void handleGameInput(int buttonCode) {
        if(buttonCode == ControllerComponent.START_BUTTON.getButtonCode()) {

//            ScreenUtils.clear(0, 0, 0.2f, 1);
//            this.game.setScreen(new LevelMenu(game));
        }
    }

    public void handlePauseInput(int buttonCode) {
        if(buttonCode == ControllerComponent.START_BUTTON.getButtonCode()) {

//            ScreenUtils.clear(0, 0, 0.2f, 1);
//            this.game.setScreen(new LevelMenu(game));
        }
    }

    public void handleLevelMenuInput(int buttonCode) {


        if(buttonCode == ControllerComponent.START_BUTTON.getButtonCode()) {

        }
    }

    public void handleGameStart(int buttonCode) {
        if(buttonCode == ControllerComponent.START_BUTTON.getButtonCode()) {
            ScreenUtils.clear(0, 0, 0.2f, 1);
            this.game.isOnTitleMenu = false;
            this.game.isOnLevelMenu = true;
            this.game.setScreen(new LevelMenu(game));
        }
    }

//    private void buttonSwitch(Controller controller, int buttonCode) {
//        if(buttonCode == ControllerComponent.START_BUTTON.getButtonCode()) {
//            Gdx.app.log("START BUTTON", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.SELECT_BUTTON.getButtonCode()) {
//            Gdx.app.log("SELECT BUTTON", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.A_BUTTON.getButtonCode()) {
//            Gdx.app.log("A BUTTON", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.B_BUTTON.getButtonCode()) {
//            Gdx.app.log("B BUTTON", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.X_BUTTON.getButtonCode()) {
//            Gdx.app.log("X BUTTON", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.Y_BUTTON.getButtonCode()) {
//            Gdx.app.log("Y BUTTON", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.D_PAD_DOWN.getButtonCode()) {
//            Gdx.app.log("D PAD DOWN", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.D_PAD_UP.getButtonCode()) {
//            Gdx.app.log("D PAD UP", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.D_PAD_LEFT.getButtonCode()) {
//            Gdx.app.log("D PAD LEFT", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.D_PAD_RIGHT.getButtonCode()){
//            Gdx.app.log("D PAD RIGHT", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.LEFT_STICK.getButtonCode()) {
//            Gdx.app.log("LEFT STICK", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.RIGHT_STICK.getButtonCode()) {
//            Gdx.app.log("RIGHT STICK", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.LEFT_BUMPER.getButtonCode()) {
//            Gdx.app.log("LEFT BUMPER", controller.getName());
//        }
//        if(buttonCode == ControllerComponent.RIGHT_BUMPER.getButtonCode()) {
//            Gdx.app.log("RIGHT BUMPER", controller.getName());
//        }
//    }
}
