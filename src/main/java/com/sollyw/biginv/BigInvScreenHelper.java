package com.sollyw.biginv;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sollyw.biginv.BigInvModInfo.RightmostBehaviour;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.LoomScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class BigInvScreenHelper {
    public static final Identifier MOD_BACKGROUND = BigInv.id("textures/gui/littlebiginv_background.png");
    //public static final Identifier MOD_BACKGROUND = BigInv.id("textures/gui/inventory_mod_background.png");
    public static final Identifier BIG_HOTBAR = BigInv.id("textures/gui/big_hotbar.png");

    public static void patchScreen(DrawContext context, int x, int y, int backgroundWidth, int backgroundHeight,
                                   int mouseX, int mouseY, LivingEntity entity, RightmostBehaviour rightmostBehaviour) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, MOD_BACKGROUND);

        if (entity instanceof PlayerEntity playerEntity) {
            if (((PlayerEntity) entity).currentScreenHandler instanceof CreativeInventoryScreen.CreativeScreenHandler) {
                return;
            }
        }

        // draw main inventory
        context.drawTexture(MOD_BACKGROUND,
                x + backgroundWidth - 230,
                y + backgroundHeight - 83,
                0,
                137,
                230,
                119,
                256,
                256);

        // draw armor bar
        context.drawTexture(MOD_BACKGROUND,
                x + backgroundWidth - 230,
                y + backgroundHeight - 166,
                0,
                0,
                25,
                83,
                256,
                256);

        if (entity instanceof PlayerEntity playerEntity) {
            if (playerEntity.getInventory().contains(Items.CHEST.getDefaultStack())) {
                // draw inv chest
                context.drawTexture(BigInv.id("textures/gui/backpack.png"),
                        x + backgroundWidth - 4,
                        y + backgroundHeight - 166,
                        96,
                        0,
                        65,
                        184,
                        256,
                        256);
            }
            int x0 = x - 11;
            int y0 = y + backgroundHeight - 112;
            int playerEntityDrawSize = 21;
            // if handled screen is instanceof PlayerScreenHandler
            if (playerEntity.currentScreenHandler instanceof PlayerScreenHandler) {
                playerEntityDrawSize = 0; // Instead of drawing the player here and redirecting the original call, I just set the size to 0 and modify the original
                context.drawTexture(MOD_BACKGROUND,
                        x + backgroundWidth - 205,
                        y + backgroundHeight - 166,
                        25,
                        0,
                        79,
                        83,
                        256,
                        256);
            } else if (playerEntity.currentScreenHandler instanceof GenericContainerScreenHandler || ((PlayerEntity) entity).currentScreenHandler instanceof LoomScreenHandler) {
                // if handled screen is instanceof GenericContainerScreenHandler (e.g. chest)
                playerEntityDrawSize = 17;
                x0 = x - 16;
                y0 = y + backgroundHeight - 112;

                context.drawTexture(MOD_BACKGROUND,
                        x + backgroundWidth - 205,
                        y + backgroundHeight - 166,
                        148,
                        0,
                        33,
                        83,
                        256,
                        256);
            } else {
                context.drawTexture(MOD_BACKGROUND,
                        x + backgroundWidth - 205,
                        y + backgroundHeight - 166,
                        112,
                        0,
                        36,
                        83,
                        256,
                        256);
            }
            InventoryScreen.drawEntity(context,
                    x0,
                    y0,
                    playerEntityDrawSize,
                    (float)(x0 - mouseX),
                    (float)(y0 - 50 - mouseY),
                    entity);
        }



/*
        // Bottom right block
        if (rightmostBehaviour == RightmostBehaviour.EXTEND_FROM_RIGHT) {
            DrawableHelper.drawTexture(matrices,
                    x + backgroundWidth - 320,
                    y + backgroundHeight - 83,
                    192,
                    0,
                    320,
                    119,
                    512,
                    256);
        }

        int mainX = backgroundWidth - 338;
        boolean aligned;
        if (mainX >= -100) {
            aligned = true;
            mainX = x - 100;
        } else {
            aligned = false;
            mainX += x;
        }

        // Main inventory segment
        DrawableHelper.drawTexture(matrices,
                mainX,
                y + backgroundHeight - 83,
                0,
                137,
                rightmostBehaviour == RightmostBehaviour.CAP ? 338 : 331,
                119,
                512,
                256);

        // Upper inventory (armour slots etc.)
        DrawableHelper.drawTexture(matrices,
                x - 100,
                y + backgroundHeight - 166,
                339,
                120,
                104,
                83,
                512,
                256);

        {
            int u, width;
            if (aligned) {
                u = 269;
                width = 7;
            } else {
                u = 0;
                width = 238 - backgroundWidth;
            }

            // Piece connecting the upper inventory to the main invenrory
            DrawableHelper.drawTexture(matrices,
                    mainX,
                    y + backgroundHeight - 90,
                    u,
                    129,
                    width,
                    7,
                    512,
                    256);
        }

        // Block on top of the base gui, for short guis such as the hopper
        if (backgroundHeight < 166) {
            DrawableHelper.drawTexture(matrices,
                    x,
                    y + backgroundHeight - 166,
                    191 - backgroundWidth,
                    0,
                    backgroundWidth,
                    170 - backgroundHeight,
                    512,
                    256);

        } else if (backgroundHeight > 166) {
            int xPos = x;
            int width = 4;
            if (backgroundHeight == 167) {
                xPos += 2;
                width = 3;
            } else if (backgroundHeight == 168) {
                xPos += 1;
                width = 3;
            }

            // Piece connecting the upper inventory to the base gui when the base gui is taller
            DrawableHelper.drawTexture(matrices,
                    xPos,
                    y + backgroundHeight - 166,
                    444,
                    120,
                    width,
                    4,
                    512,
                    256);
        }

        */
    }
}
