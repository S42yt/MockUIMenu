package tr.s42.mockuimenu

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import tr.s42.mockuimenu.ui.UIManager

class MockUIMenuClient : ClientModInitializer {
    override fun onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            while (MockUIMenu.TOGGLE_UI_KEY.wasPressed()) {
                UIManager.toggleUI(client)
            }
        }
    }
}