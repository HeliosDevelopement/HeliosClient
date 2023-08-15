package dev.heliosclient.util;

import org.lwjgl.glfw.GLFW;

public class KeycodeToString {

    public static String translate(Integer keyCode) {
        String keyName = GLFW.glfwGetKeyName(keyCode, 0);
        if (keyName == null) {
            switch (keyCode){
                case GLFW.GLFW_KEY_RIGHT_SHIFT -> {
                    return "RIGHT_SHIFT";
                }
                case GLFW.GLFW_KEY_RIGHT_ALT -> {
                    return "RIGHT_ALT";
                }
                case GLFW.GLFW_KEY_RIGHT_CONTROL -> {
                    return "RIGHT_CTRL";
                }
                case GLFW.GLFW_KEY_LEFT_SHIFT -> {
                    return "LEFT_SHIFT";
                }
                case GLFW.GLFW_KEY_LEFT_CONTROL -> {
                    return "LEFT_CTRL";
                }
                case GLFW.GLFW_KEY_LEFT_ALT -> {
                    return "LEFT_ALT";
                }
                case GLFW.GLFW_KEY_UP -> {
                    return "ARROW_UP";
                }
                case GLFW.GLFW_KEY_DOWN -> {
                    return "ARROW_DOWN";
                }
                case GLFW.GLFW_KEY_LEFT -> {
                    return "ARROW_LEFT";
                }
                case GLFW.GLFW_KEY_RIGHT -> {
                    return "ARROW_RIGHT";
                }
                case GLFW.GLFW_KEY_TAB -> {
                    return "TAB";
                }
                case GLFW.GLFW_KEY_F1 -> {
                    return "F1";
                }
                case GLFW.GLFW_KEY_F2 -> {
                    return "F2";
                }
                case GLFW.GLFW_KEY_F3 -> {
                    return "F3";
                }
                case GLFW.GLFW_KEY_F4 -> {
                    return "F4";
                }
                case GLFW.GLFW_KEY_F5 -> {
                    return "F5";
                }
                case GLFW.GLFW_KEY_F6 -> {
                    return "F6";
                }
                case GLFW.GLFW_KEY_F7 -> {
                    return "F7";
                }
                case GLFW.GLFW_KEY_F8 -> {
                    return "F8";
                }
                case GLFW.GLFW_KEY_F9 -> {
                    return "F9";
                }
                case GLFW.GLFW_KEY_F10 -> {
                    return "F10";
                }
                case GLFW.GLFW_KEY_F11 -> {
                    return "F11";
                }
                case GLFW.GLFW_KEY_F12 -> {
                    return "F12";
                }
                case GLFW.GLFW_KEY_F13 -> {
                    return "F13";
                }
                case GLFW.GLFW_KEY_F14 -> {
                    return "F14";
                }
                case GLFW.GLFW_KEY_F15 -> {
                    return "F15";
                }
                case GLFW.GLFW_KEY_F16 -> {
                    return "F16";
                }
                case GLFW.GLFW_KEY_F17 -> {
                    return "F17";
                }
                case GLFW.GLFW_KEY_F18 -> {
                    return "F18";
                }
                case GLFW.GLFW_KEY_F19 -> {
                    return "F19";
                }
                case GLFW.GLFW_KEY_F20 -> {
                    return "F20";
                }
                case GLFW.GLFW_KEY_F21 -> {
                    return "F21";
                }
                case GLFW.GLFW_KEY_F22 -> {
                    return "F22";
                }
                case GLFW.GLFW_KEY_F23 -> {
                    return "F23";
                }
                case GLFW.GLFW_KEY_F24 -> {
                    return "F24";
                }
                case GLFW.GLFW_KEY_F25 -> {
                    return "F25";
                }
                case GLFW.GLFW_KEY_F -> {
                    return "F2";
                }
                case GLFW.GLFW_KEY_LEFT_SUPER -> {
                    return "LEFT_SUPER";
                }
                case GLFW.GLFW_KEY_RIGHT_SUPER -> {
                    return "RIGHT_SUPER";
                }
                case GLFW.GLFW_KEY_INSERT -> {
                    return "INS";
                }
                case GLFW.GLFW_KEY_HOME -> {
                    return "HOME";
                }
                case GLFW.GLFW_KEY_PAGE_UP -> {
                    return "PAGE_UP";
                }
                case GLFW.GLFW_KEY_PAGE_DOWN -> {
                    return "PAGE_DOWN";
                }
                case GLFW.GLFW_KEY_DELETE -> {
                    return "DELETE";
                }
                case GLFW.GLFW_KEY_END -> {
                    return "END";
                }
                case GLFW.GLFW_KEY_SCROLL_LOCK -> {
                    return "SCROLL_LOCK";
                }
                case GLFW.GLFW_KEY_PRINT_SCREEN -> {
                    return "PRINT_SCREEN";
                }
                case GLFW.GLFW_KEY_PAUSE -> {
                    return "PAUSE";
                }
                case GLFW.GLFW_KEY_ESCAPE -> {
                    return "ESC";
                }
                case GLFW.GLFW_KEY_CAPS_LOCK -> {
                    return "CAPS_LOCK";
                }
                case GLFW.GLFW_KEY_NUM_LOCK -> {
                    return "NUM_LOCK";
                }
                case GLFW.GLFW_KEY_SPACE -> {
                    return "SPACE";
                }
                case GLFW.GLFW_KEY_ENTER -> {
                    return "ENTER";
                }
                default -> {
                    return "KEY_"+keyCode;
                }
            }
        }
        return keyName;
    }

}