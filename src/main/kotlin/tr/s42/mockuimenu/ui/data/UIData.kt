package tr.s42.mockuimenu.ui.data

data class FeatureItem(
    val icon: String,
    val name: String,
    val isNew: Boolean = false,
    val description: String = ""
)

object FeatureData {
    val allFeatures = listOf(
        listOf(
            FeatureItem("📐", "FOV CHANGER", true, "Adjust field of view"),
            FeatureItem("☀", "FULLBRIGHT", false, "Remove darkness"),
            FeatureItem("🏆", "TIERTAGER", true, "Tag tier levels"),
            FeatureItem("📷", "SCREENSHOT", false, "Take screenshots")
        ),
        listOf(
            FeatureItem("📍", "WAYPOINTS", true, "Mark locations"),
            FeatureItem("🔍", "ZOOM", false, "Zoom in/out"),
            FeatureItem("👁", "FREELOOK", false, "Free camera"),
            FeatureItem("🌫", "NOFOG", false, "Remove fog")
        ),
        listOf(
            FeatureItem("💬", "CHAT FILTER", true, "Filter chat"),
            FeatureItem("🗨", "CHAT HEADS", true, "Show chat heads"),
            FeatureItem("📡", "STREAMER MODE", false, "Hide sensitive info"),
            FeatureItem("🏷", "NAMETAGS", false, "Customize nametags")
        ),
        listOf(
            FeatureItem("👤", "PROFILES", true, "User profiles"),
            FeatureItem("⚔", "ITEM MODEL", true, "Custom models"),
            FeatureItem("📝", "AUTOTEXT", false, "Auto typing"),
            FeatureItem("🔄", "RECONNECT", true, "Auto reconnect")
        ),
        listOf(
            FeatureItem("📊", "FPS", false, "Show FPS"),
            FeatureItem("📶", "PING", false, "Show ping"),
            FeatureItem("📦", "ITEM COUNTER", false, "Count items"),
            FeatureItem("📏", "REACH DISPLAY", false, "Show reach")
        ),
        listOf(
            FeatureItem("⚡", "PERFORMANCE", false, "Boost performance"),
            FeatureItem("🎯", "AIM ASSIST", true, "Aim assistance"),
            FeatureItem("🛡", "ANTI CHEAT", false, "Protection"),
            FeatureItem("🔧", "SETTINGS", false, "Configuration")
        )
    )
    
    val categories = listOf("MCREAL", "COSMETICS", "NRC+", "EMOTES", "FRIENDS")
}