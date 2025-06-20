package tr.s42.mockuimenu.event

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW

object SayHello {
    private var hasTriggered = false

    fun initialize() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            val window = MinecraftClient.getInstance().window.handle
            
            val currentF3 = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_F3) == GLFW.GLFW_PRESS
            val currentM = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_M) == GLFW.GLFW_PRESS
            
            if (currentF3 && currentM && !hasTriggered) {
                sendHelloMessage()
                hasTriggered = true
            }
            
            if (!currentF3 || !currentM) {
                hasTriggered = false
            }
        }
    }
    
    private fun sendHelloMessage() {
        val client = MinecraftClient.getInstance()
        if (client.player != null && client.world != null) {
            val message = Text.literal("<S42_> hallo norisk client!!")
            
            client.inGameHud.chatHud.addMessage(message)
            
            println("Message sent: <S42_> hallo norisk client!!")
        } else {
            println("Player or world is null!")
        }
    }
}