package com.epam.carrental;

import com.epam.carrental.controllers.Controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Captcha implements Controller {
    private static String sessionParamName = "captchaCode";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");
        /*
         * Define number characters contains the captcha image, declare global
         */
        int iTotalChars = 6;

        /*
         * Size image iHeight and iWidth, declare globl
         */
        int iHeight = 40;
        int iWidth = 150;

        /*
         * font style
         */
        Font fntStyle1 = new Font("Arial", Font.BOLD, 30);
        Font fntStyle2 = new Font("Verdana", Font.BOLD, 20);

        /*
         * Possible random characters in the image
         */
        Set<Character> prohibitedChars = new HashSet(Arrays.asList("qwertyuopasdfghjkzxcvbnm23456789".toCharArray()));
        Random randChars = new Random();
        String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36))
                .substring(0, iTotalChars);
        /*
         * BufferedImage is used to create a create new image
         */
        /*
         * TYPE_INT_RGB - does not support transpatency, TYPE_INT_ARGB - support transpatency
         */
        BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();

        // Draw background rectangle and noisey filled round rectangles
        int iCircle = 15;
        for (int i = 0; i < iCircle; i++) {
            g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
            int iRadius = (int) (Math.random() * iHeight / 2.0);
            int iX = (int) (Math.random() * iWidth - iRadius);
            int iY = (int) (Math.random() * iHeight - iRadius);
        }
        g2dImage.setFont(fntStyle1);
        for (int i = 0; i < iTotalChars; i++) {
            g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
            if (i % 2 == 0) {
                g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
            } else {
                g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
            }
        }

        /*
         * create jpeg image and display on the screen
         */
        OutputStream osImage = response.getOutputStream();
        ImageIO.write(biImage, "jpeg", osImage);

        /*
         * Dispose function is used destory an image object
         */
        g2dImage.dispose();

        HttpSession session = request.getSession();
        session.setAttribute(sessionParamName, sImageCode);
    }

    public static boolean isCorrect(HttpServletRequest request, String userInput) {
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute(sessionParamName);
        return captcha.equals(userInput);
    }
}
