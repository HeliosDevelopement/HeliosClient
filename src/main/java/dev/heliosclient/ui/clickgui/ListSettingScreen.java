package dev.heliosclient.ui.clickgui;

import dev.heliosclient.module.settings.ListSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

import dev.heliosclient.module.modules.ClickGUI;
import dev.heliosclient.system.ColorManager;
import dev.heliosclient.util.Renderer2D;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class ListSettingScreen extends Screen {
    private ListSetting listSetting;
    private Screen parentScreen;
    int x, x2, y, windowWidth = 180, windowHeight;
    private int[] hoverAnimationTimers;

    public TextButton backButton = new TextButton("< Back");
    static int offsetY = 0;

    public ListSettingScreen(ListSetting listSetting, Screen parentScreen) {
        super(Text.literal(listSetting.name));
        this.listSetting = listSetting;
        this.parentScreen = parentScreen;
        offsetY = 0;
        hoverAnimationTimers = new int[listSetting.options.size()];
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        this.renderBackground(drawContext);

        windowHeight = 52;
        for (String s: listSetting.options) {
            windowHeight += 25;
        }

        int screenHeight = drawContext.getScaledWindowHeight();

        if (drawContext.getScaledWindowHeight() > windowHeight) {
            offsetY = 0;
            y = drawContext.getScaledWindowHeight() / 2 - (windowHeight) / 2;
        } else {
            offsetY = Math.max(Math.min(offsetY, 0), screenHeight - windowHeight);
            y = offsetY;
        }

        x = Math.max(drawContext.getScaledWindowWidth() / 2 - windowWidth / 2, 0);
        x2 = x + windowWidth;
        Renderer2D.drawRoundedRectangle(drawContext, x, y, windowWidth, windowHeight, 5, 0xFF222222);
        Renderer2D.drawRoundedRectangle(drawContext, x, y, true, true, false, false, windowWidth, 18, 5, 0xFF1B1B1B);
        Renderer2D.drawRectangle(drawContext, x, y + 16, windowWidth, 2, ColorManager.INSTANCE.clickGuiSecondary());
        drawContext.drawText(textRenderer, listSetting.name, drawContext.getScaledWindowWidth() / 2 - textRenderer.getWidth(listSetting.name) / 2, y + 4, ColorManager.INSTANCE.clickGuiPaneText(), false);
        drawContext.drawText(textRenderer, "§o" + listSetting.description, drawContext.getScaledWindowWidth() / 2 - textRenderer.getWidth("§o" + listSetting.description) / 2, y + 26, ColorManager.INSTANCE.defaultTextColor(), false);
        backButton.render(drawContext, textRenderer, x + 4, y + 4, mouseX, mouseY);
        int yOffset = y + 55;
        for (int i = 0; i < listSetting.options.size(); i++) {
            String string = listSetting.options.get(i);
            int hitboxY = yOffset - 25 / 2 + 10 / 2;
            if (mouseX >= x && mouseX <= x2 && mouseY >= hitboxY && mouseY <= hitboxY + 25) {
                hoverAnimationTimers[i] = Math.min(hoverAnimationTimers[i] + 1, 40);
            } else {
                hoverAnimationTimers[i] = Math.max(hoverAnimationTimers[i] - 1, 0);
            }
            int fillColor = (int)(34 + 0.85 * hoverAnimationTimers[i]);
            Renderer2D.drawRoundedRectangle(drawContext, x, hitboxY, windowWidth, 25, 5, new Color(fillColor, fillColor, fillColor).getRGB());
            drawContext.drawText(textRenderer, string, x + 16, yOffset + 2, ColorManager.INSTANCE.defaultTextColor(), false);
            drawContext.fill(x2 - 26, yOffset, x2 - 16, yOffset + 10, 0xFFFFFFFF);
            drawContext.fill(x2 - 25, yOffset + 1, x2 - 17, yOffset + 9, 0xFF222222);
            drawContext.fill(x2 - 24, yOffset + 2, x2 - 18, yOffset + 8, value(string) ? 0xFF55FFFF : 0xFF222222);
            yOffset += 25;
        }
        Tooltip.tooltip.render(drawContext, textRenderer, mouseX, mouseY);

    }

    @Override
    public boolean shouldPause() {
        return ClickGUI.pause;
    }

    public boolean value(String option) {
        return listSetting.value.contains(option);
    }

    public void toggleValue(String option) {
        if (listSetting.value.contains(option)) {
            listSetting.value.remove(option);
        } else {
            listSetting.value.add(option);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        backButton.mouseClicked((int) mouseX, (int) mouseY);
        int yOffset = y + 55;
        for (String string: listSetting.options) {
            int hitboxY = yOffset - 25 / 2 + 10 / 2;
            if (mouseX >= x && mouseX <= x2 && mouseY >= hitboxY && mouseY <= hitboxY + 25) {
                toggleValue(string);
            }
            yOffset += 25;
        }
        return false;
    }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_BACKSPACE) {
            MinecraftClient.getInstance().setScreen(parentScreen);
        }
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    public static void onScroll(double horizontal, double vertical) {
        offsetY += vertical * 7;
    }
}