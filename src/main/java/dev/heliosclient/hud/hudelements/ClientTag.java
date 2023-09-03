package dev.heliosclient.hud.hudelements;

import dev.heliosclient.HeliosClient;
import dev.heliosclient.hud.HudElement;
import dev.heliosclient.util.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class ClientTag extends HudElement {

    public ClientTag() {
        super("Client Tag", "Shows client watermark");
        this.width = 20;
        this.height = 13;
        this.draggable=false;
        this.renderOutLineBox=false;
    }

    @Override
    public void renderElement(DrawContext drawContext, TextRenderer textRenderer) {
        String text = HeliosClient.clientTag + " " + HeliosClient.versionTag;
        this.width = textRenderer.getWidth(text) + 1;
        this.x = MinecraftClient.getInstance().getWindow().getScaledWidth() - this.width/2 - 3;
        this.y = MinecraftClient.getInstance().getWindow().getScaledHeight() - this.height/2;
        drawContext.drawText(textRenderer, text, this.x-width/2 + 1, this.y+height/2-10, HeliosClient.uiColorA, false);
    }

}