package com.valkryst.VTerminal.printer;

import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.component.Screen;

public class LinePrinter {
    /**
     * Prints a line on the screen of a panel.
     *
     * @param panel
     *         The panel.
     *
     * @param fromX
     *         The x-axis (column) coordinate of the start point of the line.
     *
     * @param fromY
     *         The y-axis (row) coordinate of the start point of the line.
     *
     * @param toX
     *         The x-axis (column) coordinate of the end point of the line.
     *
     * @param toY
     *         The y-axis (row) coordinate of the end point of the line.
     */
    public void print(final Panel panel, final int fromX, final int fromY, final int toX, final int toY) {
        print(panel.getCurrentScreen(), fromX, fromY, toX, toY);
    }


    /**
     * Prints a line on a screen using the Bresenham algorithm.
     *
     * @param screen
     *         The screen.
     *
     * @param fromX
     *         The x-axis (column) coordinate of the start point of the line.
     *
     * @param fromY
     *         The y-axis (row) coordinate of the start point of the line.
     *
     * @param toX
     *         The x-axis (column) coordinate of the end point of the line.
     *
     * @param toY
     *         The y-axis (row) coordinate of the end point of the line.
     */
    public void print(final Screen screen, int fromX, int fromY, final int toX, final int toY) {
        // delta of exact value and rounded value of the dependant variable
        int d = 0;

        int dy = Math.abs(toY - fromY);
        int dx = Math.abs(toX - fromX);

        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point

        int ix = fromX < toX ? 1 : -1; // increment direction
        int iy = fromY < toY ? 1 : -1;

        while (true) {
            screen.write('█', fromX, fromY);

            if (dy <= dx) {
                if (fromX == toX) {
                    break;
                }

                fromX += ix;
                d += dy2;

                if (d > dx) {
                    fromY += iy;
                    d -= dx2;
                }
            } else {
                if (fromY == toY) {
                    break;
                }

                fromY += iy;
                d += dx2;

                if (d > dy) {
                    fromX += ix;
                    d -= dy2;
                }
            }
        }
    }
}
