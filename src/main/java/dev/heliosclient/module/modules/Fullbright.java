package dev.heliosclient.module.modules;

import dev.heliosclient.managers.EventManager;
import dev.heliosclient.module.Categories;
import dev.heliosclient.module.Module_;
import dev.heliosclient.module.settings.CycleSetting;
import dev.heliosclient.module.settings.DoubleSetting;
import dev.heliosclient.module.settings.Setting;
import dev.heliosclient.module.settings.SettingGroup;
import dev.heliosclient.util.ISimpleOption;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fullbright extends Module_ {
    private final SettingGroup sgGeneral = new SettingGroup("General");

    List<String> modes = new ArrayList<>(List.of("Gamma", "Night Vision"));
    CycleSetting mode = sgGeneral.add(new CycleSetting.Builder()
            .name("Mode")
            .description("Fullbright mode to apply")
            .module(this)
            .value(modes)
            .defaultValue(modes)
            .listValue(0)
            .build()
    );
    DoubleSetting gamma = sgGeneral.add(new DoubleSetting.Builder()
            .name("Gamma")
            .description("Desired gamma value")
            .module(this)
            .value(15.0)
            .defaultValue(15.0)
            .min(0)
            .max(15)
            .shouldRender(() -> mode.value == 0)
            .roundingPlace(0)
            .build());
    CycleSetting stringSetting = new CycleSetting.Builder()
            .module(this)
            .listValue(0)
            .value(Arrays.asList("Option 1", "Option 2"))
            .defaultValue(Arrays.asList("Option 1", "Option 2"))
            .build();


    public Fullbright() {
        super("Fullbright", "Allows you to see in the dark.", Categories.RENDER);
        EventManager.register(this);

        addSettingGroup(sgGeneral);

        addQuickSettingGroup(sgGeneral);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onSettingChange(Setting setting) {
        super.onSettingChange(setting);
        if (active.value) {
            if (mode.value == 0) {
                ((ISimpleOption<Double>) (Object) mc.options.getGamma()).setValueUnrestricted(gamma.value);
                assert mc.player != null;
                mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
            } else if (mc.player != null && mode.value == 1) {
                mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1000, 254, true, false, false));
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.options.getGamma().setValue(1.0);
        if (mc.player != null)
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }
}
