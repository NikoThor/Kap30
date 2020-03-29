package Exercise30_1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class Task30_1 extends Application {

    static TextArea textArea;

    @Override
    public void start(Stage stage) throws Exception {
        // til linjeskift
        textArea = new TextArea();
        textArea.setWrapText(true);
        Scene scene = new Scene(textArea, 800, 100);
        stage.setScene(scene);
        stage.show();

        // Create tasks
        PrintChar printA = new PrintChar('a', 100);
        PrintChar printB = new PrintChar('b', 100);
        PrintNum print100 = new PrintNum(100);
        // Create Threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        // start Threads
        thread1.start();
        thread2.start();
        thread3.start();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    public static void updateTextArea(String text) {
        textArea.appendText(text);
    }

        // The task for printing a specified character in specified times
        class PrintChar implements Runnable {
            private char charToPrint; // The character to print
            private int times; // The times to repeat

            /** Construct a task with specified character and number of
             *  times to print the character
             */
            public PrintChar(char c, int t) {
                charToPrint = c;
                times = t;
            }

            @Override /** Override the run() method to tell the system
             *  what the task to perform
             */
            public void run() {
                for (int i = 0; i < times; i++) {
                    Platform.runLater(() -> Task30_1.updateTextArea("" + charToPrint));
                }
            }
        }

// The task class for printing number from 1 to n for a given n
    class PrintNum implements Runnable {
        private int lastNum;

        /** Construct a task for printing 1, 2, ... i */
        public PrintNum(int n) {
            lastNum = n;
        }

        @Override /** Tell the thread how to run */
        public void run() {
            for (int i = 1; i <= lastNum; i++) {
                int finalI = i;
                Platform.runLater(() -> Task30_1.updateTextArea("" + finalI));
            }
        }
    }}