package tr.s42.mockuimenu.ui

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import tr.s42.mockuimenu.ui.components.UIComponents
import tr.s42.mockuimenu.ui.data.FeatureData

class MockUIScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, Containers::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        rootComponent
            .surface(Surface.VANILLA_TRANSLUCENT)
            .horizontalAlignment(HorizontalAlignment.CENTER)
            .verticalAlignment(VerticalAlignment.CENTER)

        val menuContainer = Containers.verticalFlow(Sizing.fill(85), Sizing.fill(80)) 
            .apply {
                surface(Surface.flat(0x00000000)) 
                padding(Insets.of(0))
            }

        val topBar = createTopBar()
        menuContainer.child(topBar)

        val gridContainer = createMainGrid()
        val scrollContainer = Containers.verticalScroll(Sizing.fill(100), Sizing.fill(70), gridContainer)
            .apply {
                surface(Surface.flat(0x30000000.toInt())) 
                padding(Insets.of(20)) 
            }
        menuContainer.child(scrollContainer)

        val bottomBar = createBottomBar()
        menuContainer.child(bottomBar)

        menuContainer.positioning(Positioning.relative(50, 50))
        rootComponent.child(menuContainer)
    }

    private fun createTopBar(): FlowLayout {
        val topBar = Containers.horizontalFlow(Sizing.fill(100), Sizing.content())
            .apply {
                surface(Surface.flat(0x60000000.toInt())) 
                padding(Insets.of(20)) 
                verticalAlignment(VerticalAlignment.CENTER)
            }
        
        topBar.apply {
            
            child(UIComponents.createSearchBar(Sizing.fill(50)))
            
            
            child(Containers.horizontalFlow(Sizing.fill(10), Sizing.content()))
            
            
            child(UIComponents.createIconButton("☰"))
            child(UIComponents.createTextButton("PVP").margins(Insets.left(8)))
            child(UIComponents.createTextButton("HUD").margins(Insets.left(8)))
            
            child(
                UIComponents.createNewBadge("NEW 🔔")
                    .margins(Insets.left(8))
            )
        }
        return topBar
    }

    private fun createMainGrid(): FlowLayout {
        val gridContainer = Containers.verticalFlow(Sizing.fill(100), Sizing.content())
            .apply {
                padding(Insets.of(15)) 
            }

        FeatureData.allFeatures.forEach { rowItems ->
            val row = createGridRow(rowItems)
            gridContainer.child(row)
        }

        return gridContainer
    }

    private fun createGridRow(items: List<tr.s42.mockuimenu.ui.data.FeatureItem>): FlowLayout {
        val row = Containers.horizontalFlow(Sizing.fill(100), Sizing.content())
            .apply {
                horizontalAlignment(HorizontalAlignment.CENTER)
                margins(Insets.bottom(20)) 
            }

        items.forEach { item ->
            val cell = UIComponents.createFeatureCard(item.icon, item.name, item.isNew)
            row.child(cell)
        }

        return row
    }

    private fun createBottomBar(): FlowLayout {
        return Containers.horizontalFlow(Sizing.fill(100), Sizing.content())
            .apply {
                surface(Surface.flat(0x60000000.toInt())) 
                horizontalAlignment(HorizontalAlignment.CENTER)
                padding(Insets.of(20))

                FeatureData.categories.forEach { category ->
                    val isActive = category == "FRIENDS"
                    child(UIComponents.createCategoryTab(category, isActive))
                }
            }
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        if (keyCode == org.lwjgl.glfw.GLFW.GLFW_KEY_B) {
            this.close()
            return true
        }
        return super.keyPressed(keyCode, scanCode, modifiers)
    }

    override fun shouldPause(): Boolean = false
}