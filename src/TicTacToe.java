import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JComponent {

    public static final int FIELD_EMPTY = 0; //Пустовое поле
    public static final int FIELD_X = 10; // Поле с Х
    public static final int FIELD_O = 200; // Поле с 0
    int[][] field; //Массив игрового поля
    boolean isXturn; // Переменная которая показывает чей сейчас ход

    public TicTacToe(){
        enableEvents(AWTEvent.MOUSE_EVENT_MASK); // Получения событий от мыши
        field = new int [3][3];
        initGame();
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent){
        super.processMouseEvent(mouseEvent);
        if(mouseEvent.getButton() == MouseEvent.BUTTON1){ // Проверяем что нажата левая клавиша мышки
            int x = mouseEvent.getX(); // Получение координат x клика
            int y = mouseEvent.getY(); // Получение координат у клика
            // Переводим координаты в индексы ячейки в массиве field
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
           /* int i = x / getWidth() * 3;
            int j = y / getHeight() * 3;*/
            // Проверяем пуста ли клетка и туда можно ходить
            if (field[i][j] == FIELD_EMPTY){
                // Проверка чей ход
                field[i][j] = isXturn?FIELD_X:FIELD_O;
                isXturn =!isXturn; // Меняем флаг кода
                repaint(); // Перерисовываем комопоненты, это вызывает меотд paintComponent
                int res = chekState();
                if (res != 0){
                    if(res == FIELD_X * 3){
                        JOptionPane.showMessageDialog(this, "Крестик выйграл, победа Крестам","Победа",JOptionPane.INFORMATION_MESSAGE);
                    } else
                    if(res == FIELD_O * 3){
                        JOptionPane.showMessageDialog(this, "Нолик выйграл, победа Ноликам","Победа",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ничья","Кто-то слишком умный",JOptionPane.INFORMATION_MESSAGE);
                    }
                    initGame();
                    repaint();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.clearRect(0,0, getWidth(), getHeight()); // Очищает холст
        drawGrid(graphics); // Рисует игровую сетку
        drawXO(graphics); // Рисует текущие кристики и нолики
    }

    protected void initGame(){
        for(int i = 0; i <3;++i){
            for (int j = 0; j < 3; ++j){
                field[i][j] = FIELD_EMPTY; // Очищаем массив, заполнив его 0
            }
        }
        isXturn = true;
    }
    private void drawGrid(Graphics graphics){
        int w = getWidth(); //Получаем ширину игрового поля
        int h = getHeight();//Получаем высоту игрового поля
        int dw = w/3;//Делим ширину игрового поля на 3 - получаем ширину одной ячейки
        int dh = h/3;// Делим высоту игрового поля на 3 - получаем высоту одной ячейки
        graphics.setColor(Color.BLACK); //Задаем цвет линии
        for (int i = 1; i <3; i++){
            graphics.drawLine(0, dh*i, w, dh*i);// Отрисовка горизонтальных линий
            graphics.drawLine(dw*i, 0, dw*i, h);//Отрисовка вертикальных линий
        }
    }

    private void drawXO(Graphics graphics){
        for(int i = 0; i < 3; ++i){
            for(int j =0; j < 3; ++j){
                if (field[i][j] == FIELD_X) {
                    drawX(i ,j,graphics);
                }else {
                    if (field[i][j] == FIELD_O) {
                        drawO(i, j, graphics);
                    }
                }
            }
        }
    }
    private void drawX(int i, int j, Graphics graphics){
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawLine(x, y, x + dw, y + dh);
        graphics.drawLine(x, y + dh, x + dw, y);
    }
    private void drawO(int i, int j, Graphics graphics){
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawOval(x + 5 * dw / 100, y, dw * 9/10, dh);
    }

    int chekState(){
        int diag1 = 0;
        int diag2 = 0; // проверяем диагонали
        for (int i = 0; i < 3; i++){
            diag1 += field[i][i];
            diag2 += field[i][2-i];
        }
        if (diag1 == FIELD_X * 3 || diag1 == FIELD_O * 3) {
            return diag1;
        }
        if (diag2 == FIELD_X * 3 || diag2 == FIELD_O * 3) {return diag2;}
        int cheki, chekj;
        boolean hasEmpty = false;
        for (int i = 0; i < 3; i++) {
            cheki = 0;
            chekj = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    hasEmpty = true;
                }
                cheki += field [i][j];
                chekj += field [j][i];
            }
            if (cheki == FIELD_X * 3 || cheki == FIELD_O * 3){return cheki;}
            if (chekj == FIELD_X * 3 || chekj == FIELD_O * 3){return chekj;}
        }
        if (hasEmpty) return 0; else return -1;
    }

}
