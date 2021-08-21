import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start");
        JFrame windows = new JFrame ("Крестики нолики"); //Главное окно
        windows.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Добавляем кнопку Х, закрывающую окно
        windows.setSize(400, 400); // Задаем размер окна
        windows.setLayout(new BorderLayout()); // Менеджер компановки
        windows.setLocationRelativeTo(null);// Расположение окна по центру экрана
        windows.setVisible(true); // Включаем видимость окна
        TicTacToe game = new TicTacToe();// Создаем объект класса
        windows.add(game);//Добовляем объект класса в окно
        System.out.println("End =(");

    }
}
