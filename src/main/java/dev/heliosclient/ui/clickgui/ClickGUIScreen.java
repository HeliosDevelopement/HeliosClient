package dev.heliosclient.ui.clickgui;

import dev.heliosclient.HeliosClient;
import dev.heliosclient.managers.CategoryManager;
import dev.heliosclient.managers.ModuleManager;
import dev.heliosclient.module.Categories;
import dev.heliosclient.module.sysmodules.ClickGUI;
import dev.heliosclient.ui.clickgui.navbar.NavBar;
import dev.heliosclient.util.InputBox;
import dev.heliosclient.util.MathUtils;
import dev.heliosclient.util.Renderer2D;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Map;

public class ClickGUIScreen extends Screen {
    public static ClickGUIScreen INSTANCE = new ClickGUIScreen();
    static int scrollX = 0;
    static int scrollY = 0;
    public ArrayList<CategoryPane> categoryPanes;
    public SearchBar searchBar;

    public ClickGUIScreen() {
        super(Text.literal("ClickGUI"));
        scrollX = 0;
        scrollY = 0;
        categoryPanes = new ArrayList<CategoryPane>();
        Map<String, Object> panePos = ((Map<String, Object>) HeliosClient.CONFIG.config.get("panes"));

        CategoryManager.getCategories().forEach((s, category) -> {
                    int xOffset = MathUtils.d2iSafe((((Map<String, Object>) panePos.get(category.name)).get("x")));
                    int yOffset = MathUtils.d2iSafe(((Map<String, Object>) panePos.get(category.name)).get("y"));
                    boolean collapsed = (boolean) ((Map<String, Object>) panePos.get(category.name)).get("collapsed");
                    categoryPanes.add(new CategoryPane(category, xOffset, yOffset, collapsed, this));
                }
        );

        searchBar = new SearchBar();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        for (CategoryPane category : categoryPanes) {
            category.mouseScrolled((int) mouseX, (int) mouseY, verticalAmount);
        }
        if (ClickGUI.ScrollTypes.values()[ClickGUI.ScrollType.value] == ClickGUI.ScrollTypes.OLD) {
            // Old mode: scroll the whole screen
            scrollY += (int) verticalAmount;
        }
        scrollX += (int) horizontalAmount;
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        this.renderBackground(drawContext, mouseX, mouseY, delta);
        for (CategoryPane category : categoryPanes) {
            category.y += scrollY * 10;
            category.x += scrollX * 10;
            category.render(drawContext, mouseX, mouseY, delta, textRenderer);
            if (category.category == Categories.SEARCH && !category.collapsed) {
                category.addModule(ModuleManager.INSTANCE.getModuleByNameSearch(searchBar.getValue()));

                if (ModuleManager.INSTANCE.getModuleByNameSearch(searchBar.getValue()).size() == 1) {
                    category.keepOnlyModule(ModuleManager.INSTANCE.getModuleByNameSearch(searchBar.getValue()).get(0));
                }

                if (searchBar.getValue().isEmpty()) {
                    category.removeModules();
                }
            }
        }
        searchBar.render(drawContext, drawContext.getScaledWindowWidth() / 2 - searchBar.width / 2 + 5, drawContext.getScaledWindowHeight() - searchBar.height - 10, mouseX, mouseY, textRenderer);
        Tooltip.tooltip.render(drawContext, textRenderer, mouseX, mouseY);
        NavBar.navBar.render(drawContext, textRenderer, mouseX, mouseY);
        scrollY = 0;
        scrollX = 0;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (CategoryPane category : categoryPanes) {
            category.mouseClicked((int) mouseX, (int) mouseY, button);
        }
        searchBar.mouseClicked(mouseX, mouseY, button);
        NavBar.navBar.mouseClicked((int) mouseX, (int) mouseY, button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (CategoryPane category : categoryPanes) {
            category.mouseReleased((int) mouseX, (int) mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        for (CategoryPane category : categoryPanes) {
            category.charTyped(chr, modifiers);
        }
        return super.charTyped(chr, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return ClickGUI.pause;
    }

    public void onLoad() {
        scrollY = 0;
        scrollX = 0;
    }
}

