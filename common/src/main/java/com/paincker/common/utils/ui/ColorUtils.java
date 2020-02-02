package com.paincker.common.utils.ui;

import android.graphics.Color;

import com.paincker.common.utils.MathUtils;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 颜色工具类
 */
public class ColorUtils {

    public static int getRandomColor() {
        return getRandomRgbColorWithRange(180, 220);
    }

    public static int getRandomLightColor() {
        return getRandomRgbColorWithRange(210, 256);
    }

    public static int getRandomRgbColorWithRange(int min, int max) {
        min = MathUtils.constrain(min, 0, 255);
        max = MathUtils.constrain(max, 0, 255);
        return Color.argb(255, MathUtils.random(min, max), MathUtils.random(min, max), MathUtils.random(min, max));
    }

    /**
     * lab转rgb颜色空间
     *
     * @param lab
     * @return
     */
    public static int[] lab2rgb(double[] lab) {
        return xyz2rgb(lab2xyz(lab));
    }

    /**
     * lab转颜色
     *
     * @param lab
     * @return
     */
    public static int lab2Color(double[] lab) {
        int[] rgb = lab2rgb(lab);
        return Color.rgb(rgb[0], rgb[1], rgb[2]);
    }

    /**
     * rgb转lab颜色空间
     *
     * @param rgb
     * @return
     */
    public static double[] rgb2lab(double[] rgb) {
        return xyz2lab(rgb2xyz(rgb));
    }

    /**
     * 颜色转换为rgb数组
     *
     * @param color
     * @return
     */
    public static int[] color2rgb(int color) {
        return new int[]{
                Color.red(color),
                Color.green(color),
                Color.blue(color)};
    }

    /**
     * 颜色转换为lab数组
     *
     * @param color
     * @return
     */
    public static double[] color2lab(int color) {
        return rgb2lab(new double[]{
                Color.red(color),
                Color.green(color),
                Color.blue(color)});
    }

    /**
     * 两种颜色rgb按比例混合
     *
     * @param c1
     * @param c2
     * @param w1
     * @param w2
     * @return
     */
    public static int colorMix(int c1, int c2, int w1, int w2) {
        final int w = w1 + w2;
        final int r = (Color.red(c1) * w1 + Color.red(c2) * w2) / w;
        final int g = (Color.green(c1) * w1 + Color.green(c2) * w2) / w;
        final int b = (Color.blue(c1) * w1 + Color.blue(c2) * w2) / w;
        return Color.rgb(r, g, b);
    }

    /**
     * 多种颜色按比例混合
     *
     * @param colors
     * @param weights
     * @return 返回颜色的argb分别为输入颜色argb按weights的加权平均值
     */
    public static int colorMixWeight(int[] colors, float[] weights) {
        if (colors == null || colors.length == 0 || weights == null
                || weights.length != colors.length)
            return 0;
        float a = 0, r = 0, g = 0, b = 0, wa = 0;
        final int n = colors.length;
        for (int i = 0; i < n; ++i) {
            int c = colors[i];
            float w = weights[i];
            wa += w;
            a += (float) Color.alpha(c) * w;
            r += (float) Color.red(c) * w;
            g += (float) Color.green(c) * w;
            b += (float) Color.blue(c) * w;
        }
        if (wa == 0)
            return 0;
        a /= wa;
        r /= wa;
        g /= wa;
        b /= wa;
        return Color.argb((int) a, (int) r, (int) g, (int) b);
    }

    /**
     * 多种颜色按比例混合
     *
     * @param colors
     * @return 返回颜色的alpha为输入颜色alpha的平均值,
     * rgb分别为输入颜色rgb按alpha的加权平均值
     */
    public static int colorMixArgb(int... colors) {
        if (colors == null || colors.length == 0)
            return 0;
        int r = 0, g = 0, b = 0, al = 0;
        for (int c : colors) {
            int a = Color.alpha(c);
            r += Color.red(c) * a;
            g += Color.green(c) * a;
            b += Color.blue(c) * a;
            al += a;
        }
        if (al == 0)
            return 0;
        r /= al;
        g /= al;
        b /= al;
        al /= colors.length;
        return Color.argb(al, r, g, b);
    }

    /**
     * 多种颜色按比例混合
     *
     * @param colors
     * @return 返回颜色的alpha为0xFF,
     * rgb分别为输入颜色rgb按alpha的加权平均值
     */
    public static int colorMixRgb(int... colors) {
        if (colors == null || colors.length == 0)
            return 0;
        int r = 0, g = 0, b = 0, al = 0;
        for (int c : colors) {
            int a = Color.alpha(c);
            r += Color.red(c) * a;
            g += Color.green(c) * a;
            b += Color.blue(c) * a;
            al += a;
        }
        if (al == 0)
            return 0;
        r /= al;
        g /= al;
        b /= al;
        return Color.rgb(r, g, b);
    }

    public static int setAlpha(int color, int alpha) {
        return (color & 0xFFFFFF) | ((alpha << 24) & 0xFF000000);
    }

    /**
     * 计算两种颜色对比度
     *
     * @param rgb1
     * @param rgb2
     * @return 0.0 ~ 1.0
     */
    public static float calcContrast(int[] rgb1, int[] rgb2) {
        return (float) (Math.abs(rgb1[0] - rgb2[0])
                + Math.abs(rgb1[1] - rgb2[1]) + Math.abs(rgb1[1] - rgb2[1]))
                / (float) (255 * 3);
    }

    /**
     * 计算两种颜色对比度
     *
     * @param color1
     * @param color2
     * @return 0.0 ~ 1.0
     */
    public static float calcContrast(int color1, int color2) {
        return calcContrast(color2rgb(color1), color2rgb(color2));
    }

    protected static double[] lab2xyz(double[] Lab) {
        double[] XYZ = new double[3];
        double L, a, b;
        double fx, fy, fz;
        double Xn, Yn, Zn;
        Xn = 95.04;
        Yn = 100;
        Zn = 108.89;

        L = Lab[0];
        a = Lab[1];
        b = Lab[2];

        fy = (L + 16) / 116;
        fx = a / 500 + fy;
        fz = fy - b / 200;

        if (fx > 0.2069) {
            XYZ[0] = Xn * Math.pow(fx, 3);
        } else {
            XYZ[0] = Xn * (fx - 0.1379) * 0.1284;
        }

        if ((fy > 0.2069) || (L > 8)) {
            XYZ[1] = Yn * Math.pow(fy, 3);
        } else {
            XYZ[1] = Yn * (fy - 0.1379) * 0.1284;
        }

        if (fz > 0.2069) {
            XYZ[2] = Zn * Math.pow(fz, 3);
        } else {
            XYZ[2] = Zn * (fz - 0.1379) * 0.1284;
        }

        return XYZ;
    }

    protected static double[] xyz2lab(double[] XYZ) {
        double[] Lab = new double[3];
        double X, Y, Z;
        X = XYZ[0];
        Y = XYZ[1];
        Z = XYZ[2];
        double Xn, Yn, Zn;
        Xn = 95.04;
        Yn = 100;
        Zn = 108.89;
        double XXn, YYn, ZZn;
        XXn = X / Xn;
        YYn = Y / Yn;
        ZZn = Z / Zn;

        double fx, fy, fz;

        if (XXn > 0.008856) {
            fx = Math.pow(XXn, 0.333333);
        } else {
            fx = 7.787 * XXn + 0.137931;
        }

        if (YYn > 0.008856) {
            fy = Math.pow(YYn, 0.333333);
        } else {
            fy = 7.787 * YYn + 0.137931;
        }

        if (ZZn > 0.008856) {
            fz = Math.pow(ZZn, 0.333333);
        } else {
            fz = 7.787 * ZZn + 0.137931;
        }

        Lab[0] = 116 * fy - 16;
        Lab[1] = 500 * (fx - fy);
        Lab[2] = 200 * (fy - fz);

        return Lab;
    }

    protected static double[] rgb2xyz(double[] RGB) {
        double[] XYZ = new double[3];
        double sR, sG, sB;
        sR = RGB[0];
        sG = RGB[1];
        sB = RGB[2];
        sR /= 255;
        sG /= 255;
        sB /= 255;

        if (sR <= 0.04045) {
            sR = sR / 12.92;
        } else {
            sR = Math.pow(((sR + 0.055) / 1.055), 2.4);
        }

        if (sG <= 0.04045) {
            sG = sG / 12.92;
        } else {
            sG = Math.pow(((sG + 0.055) / 1.055), 2.4);
        }

        if (sB <= 0.04045) {
            sB = sB / 12.92;
        } else {
            sB = Math.pow(((sB + 0.055) / 1.055), 2.4);
        }

        XYZ[0] = 41.24 * sR + 35.76 * sG + 18.05 * sB;
        XYZ[1] = 21.26 * sR + 71.52 * sG + 7.2 * sB;
        XYZ[2] = 1.93 * sR + 11.92 * sG + 95.05 * sB;

        return XYZ;
    }

    protected static int[] xyz2rgb(double[] XYZ) {
        int[] RGB = new int[3];
        double X, Y, Z;
        double dr, dg, db;
        X = XYZ[0];
        Y = XYZ[1];
        Z = XYZ[2];

        dr = 0.032406 * X - 0.015371 * Y - 0.0049895 * Z;
        dg = -0.0096891 * X + 0.018757 * Y + 0.00041914 * Z;
        db = 0.00055708 * X - 0.0020401 * Y + 0.01057 * Z;

        if (dr <= 0.00313) {
            dr = dr * 12.92;
        } else {
            dr = Math.exp(Math.log(dr) / 2.4) * 1.055 - 0.055;
        }

        if (dg <= 0.00313) {
            dg = dg * 12.92;
        } else {
            dg = Math.exp(Math.log(dg) / 2.4) * 1.055 - 0.055;
        }

        if (db <= 0.00313) {
            db = db * 12.92;
        } else {
            db = Math.exp(Math.log(db) / 2.4) * 1.055 - 0.055;
        }

        dr = dr * 255;
        dg = dg * 255;
        db = db * 255;

        dr = Math.min(255, dr);
        dg = Math.min(255, dg);
        db = Math.min(255, db);

        RGB[0] = (int) (dr + 0.5);
        RGB[1] = (int) (dg + 0.5);
        RGB[2] = (int) (db + 0.5);

        return RGB;
    }
}
