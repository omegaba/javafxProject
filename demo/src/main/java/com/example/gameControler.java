package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class gameControler {
    private boolean tetris;
    private game game;
    private int wordCounter = 0;
    private File saveData;
    private int first = 1;
    private int vie = 5;
    private double timer;
    private double difficulte;
    private boolean isBlue;
    private boolean isRed;
    private boolean playWwords;
    private boolean multi;
    private Object clientOrHost;
    private int caracterUtile;
    private int timerMinute;
    private int second;
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<String> tampon = new ArrayList<>();
    private int countAll = 0;
    private int counter = 0;
    private StringBuilder sb = new StringBuilder();
    private ScheduledExecutorService executor = null;

    public gameControler(game game, double time, double difficulte, boolean tetris, boolean playWw, boolean multi,
            Object clientOrHost) {
        this.game = game;
        this.tetris = tetris;
        this.difficulte = difficulte;
        playWwords = playWw;
        this.multi = multi;
        this.clientOrHost = clientOrHost;
        if (playWwords == true) {

        } else {
            executor = Executors.newScheduledThreadPool(1);
        }
        init();
        if (!tetris) {
            timer = time;
        } else {
            timer = 5 * Math.pow(0.9, difficulte);
        }
        this.isBlue = false;
        this.isRed = false;
        this.caracterUtile = 0;
        this.timerMinute = 0;
        this.second = 0;
    }

    // Initialise le tampon avec 15 mots
    public void manageTampon() {
        for (int i = 0; i < 15; i++) {
            addToTampon();
        }
    }

    // Ajoute un mot au tampon
    public void addToTampon() {
        Random r = new Random();
        int x = r.nextInt(words.size());
        tampon.add(words.get(x));
    }

    // add words to array list
    public void addToList() {
        File f = new File("src/main/java/com/example/wordsList");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void canRemoveLife() {
        if (tetris) {
            Random r = new Random();
            int next = r.nextInt(5);
            if (next == 2) {
                game.firstWordText.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                game.firstWordText.setFill(Color.RED);
                isRed = true;
            }
        }
    }

    // Si on est en mode tetris, ajoute ue vie lorsqu'un cmot en bleu est validé
    public void canAddLife() {
        if (tetris) {
            Random r = new Random();
            int next = r.nextInt(10);
            if (next == 2) {
                game.firstWordText.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                game.firstWordText.setFill(Color.BLUE);
                isBlue = true;
            }
        }
    }

    public void toMainMenu() throws IOException {
        gameLaucherController glc = new gameLaucherController(null);
        gameLauncher g = new gameLauncher(null);
        g.setControler(glc);
        glc.setGame(g);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
    }

    public void creatSavingFile() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        saveData = new File(formatter.format(date).strip() + ".txt");

        try {
            if (saveData.createNewFile()) {
                System.out.println("File created: " + saveData.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {

        if (tetris == false) {
            game.vie.setVisible(false);
            game.tetrisCircle.setVisible(false);
            game.vieValue.setVisible(false);
        } else {
            game.tetrisCircle.setVisible(true);
            game.vie.setVisible(true);
            game.vieValue.setVisible(true);
            game.vieValue.setText(Integer.toString(vie));
        }

        // TODO Auto-generated method stub
        this.game.play.setVisible(true);
        this.game.play.setDisable(false);
        String t = String.format("%.2f", timer);
        this.game.nb1.setText(t);
        addToList();
        Collections.shuffle(words);
        // il faurdra changer pour avoir les 15 premiers mots pour la difficulté
        manageTampon();
        for (int i = 1; i < tampon.size(); i++) {
            sb.append(tampon.get(i) + " ");
        }
        game.pw.setText("");
        game.sp.setText(sb.toString());
        game.firstWordText.setText(tampon.get(0));

        game.firstWordText.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        wordCounter++;
    }

    public void printRedWord() {
        if (tetris && isRed) {
            isRed = false;
            // envoie du message pour signaler qu'il faut ajouter un mot dans le tampon
            // enemie
            if (clientOrHost instanceof Client) {
                ((Client) clientOrHost).sock_pw.println("rouge");
            } else {
                ((Server) clientOrHost).csock_pw.println("rouge");
            }
            game.firstWordText.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
            game.firstWordText.setFill(Color.BLACK);
        }
    }

    public void printBlueWord() {
        if (tetris) {
            if (isBlue == true) {
                isBlue = false;
                if (vie < 5) {
                    vie += 1;
                    game.vieValue.setText(Integer.toString(vie));
                }
                game.firstWordText.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                game.firstWordText.setFill(Color.BLACK);
            }
        }
    }

    // sauvegarde les données dans un fichier
    public void saveInFile() {
        try {
            FileWriter myWriter = new FileWriter(saveData);
            myWriter.write(countAll + ";");
            myWriter.write(counter + ";");
            myWriter.write(String.valueOf(countAll - counter));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // affiche game Over dans le textfield
    public void printGameOver() {
        game.userWord.setText("Game over");
        game.userWord.setDisable(true);
        saveInFile();
        game.play.setVisible(true);
        game.play.setDisable(false);
        executor.shutdown();
    }

    public void validation() {
        String s = game.userWord.getText();
        String real = tampon.remove(0);

        // Un mot sera rajouté selement si la file est à moitié remplie
        if (!tetris || (tetris && tampon.size() < 7)) {
            addToTampon();
        }
        countAll++;

        // Si le mot entreé est correct
        if (s.replaceAll("\\s+", "").equalsIgnoreCase(real.replaceAll("\\s+", ""))) {
            counter++;
            caracterUtile += s.length();
            if (counter % 10 == 0) {
                difficulte++;
            }

            // *****clacul de la vitesse
            // si le temps ne vaut pas une minute
            if (timerMinute == 0) {
                game.wordsPerMin.setText(String.valueOf(caracterUtile / 5));
            }
            // autrement la vitesse est e nombre de caractères utiles, divisé par le temps
            // en minute, divisé encore
            // par 5 (ici, on considère par convention qu’un mot fait en moyenne 5
            // caractères).
            else {
                game.wordsPerMin.setText(String.valueOf((caracterUtile / timerMinute) / 5));
            }
            game.pw.setFill(Color.GREEN);
            printBlueWord();
            printRedWord();
        } else {
            game.pw.setFill(Color.RED);

            if (tetris) {
                vie -= 1;
                game.vieValue.setText(Integer.toString(vie));
                if (vie == 0) {
                    printGameOver();
                }
            }
        }

        // lorqu'on est en mode tetris, il y une chance de voir apparaître un mot en
        // bleu, qui donnera une vie en plus
        if (tetris) {
            canAddLife();
            if (multi)
                canRemoveLife();
            timer = Math.round((5 * Math.pow(0.9, difficulte)) * 100.0) / 100.0;
        }

        // on met à jour les différents composants
        game.userWord.setText("");
        game.accuracy.setText(String.valueOf(Math.round((counter * 1.0 / countAll) * 100)));
        game.pw.setText(s);

        // on reset le stringbuilder afin d'enlever le premier mt et de rajouter un
        // autre à la fin
        sb.setLength(0);
        for (int i = 1; i < tampon.size(); i++) {
            sb.append(tampon.get(i) + " ");
        }

        game.sp.setText(sb.toString());
        game.firstWordText.setText(tampon.get(0));
        wordCounter++;
    }

    public void startGame(KeyEvent ke) {

        if (first == 1) {
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        }

        /*
         * while (ke.getCode().equals(KeyCode.SPACE)) {
         * String s = game.userWord.getText();
         * String real = tampon.remove(0);
         * int c = 0;
         * if (!(ke.getCode().equals(KeyCode.BACK_SPACE))) {
         * if (s.charAt(c) == real.charAt(c)) {
         * game.pw.setFill(Color.GREEN);
         * game.pw.setText(String.valueOf(s.charAt(c)));
         * c++;
         * }else{
         * game.pw.setFill(Color.GREEN);
         * game.pw.setText(String.valueOf(s.charAt(c)));
         * c++;
         * }
         * }else{
         * 
         * }
         * }
         */

        if (ke.getCode().equals(KeyCode.SPACE)) {
            validation();
        }

    }

    Runnable r = new Runnable() {
        @Override
        public void run() {

            second += 1;
            if (second % 60 == 0) {
                timerMinute += 1;
            }

            if (tetris == false) {
                if (timer > -1) {
                    game.nb1.setText(String.format("%.2f", timer));
                    timer -= 1;
                } else {
                    if (timer <= 0) {
                        game.userWord.setDisable(true);
                        game.userWord.setText("Game over");
                        saveInFile();
                    }

                    if (timer == -4) {
                        game.play.setVisible(true);
                        game.play.setDisable(false);
                        executor.shutdown();
                    }

                    timer -= 1;
                }
            } else if (!multi) {
                if (timer > -1) {
                    if (vie == 0)
                        printGameOver();
                    if (timer < 0) {
                        game.nb1.setText(String.valueOf(0));
                    } else {
                        game.nb1.setText(String.format("%.2f", timer));
                    }
                    timer -= 1;
                } else {
                    if (timer <= -1) {
                        if (vie == 0)
                            printGameOver();
                        if (tampon.size() == 15) {
                            validation();
                        } else {
                            // quand le timer arive à 0 et que le tampon est pas complet, on rajoute un mot
                            // au tampon et on affiche le mot rajouté
                            addToTampon();
                            sb.setLength(0);
                            for (int i = 1; i < tampon.size(); i++) {
                                sb.append(tampon.get(i) + " ");
                            }
                            game.sp.setText(sb.toString());
                            timer = Math.round((5 * Math.pow(0.9, difficulte)) * 100.0) / 100.0;
                        }
                    }
                }
            } else {
                if (vie == 0) {
                    printGameOver();
                    // closeServer();
                }
            }
        }
    };

    public void closeServer() {
        if (clientOrHost != null) {
            try {
                if (clientOrHost instanceof Client)
                    ((Client) clientOrHost).sock.close();
                else {
                    ((Server) clientOrHost).csock.close();
                    ((Server) clientOrHost).ssock.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getTampon() {
        return tampon;
    }

    public double getDifficulty() {
        return difficulte;
    }

    public StringBuilder getStringBuilder() {
        return sb;
    }

    public void setTimer(double t) {
        timer = t;
    }

    public game getGame() {
        return game;
    }

}
