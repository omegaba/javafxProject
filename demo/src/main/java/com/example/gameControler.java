package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

public final class gameControler {
    private boolean tetris;
    private game game;
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
    private int nbwords;
    private String lastgamesavingfile;
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<String> tampon = new ArrayList<>();
    private int countAll = 0;
    private int counter = 0;
    private StringBuilder sb = new StringBuilder();

    // Cet attribut permet de faire des actions sur un runnable selon un interval de
    // temps définit
    private ScheduledExecutorService executor = null;

    /**
     * @param builder va nous servir a instanciez notre classe et ses attributs
     */
    private gameControler(Builder builder) {

        this.game = builder.game;
        this.tetris = builder.tetris;
        this.difficulte = builder.difficulte;
        this.playWwords = builder.playWwords;
        this.multi = builder.multi;
        this.clientOrHost = builder.clientOrHost;
        this.nbwords = builder.nbwords;
        if (!playWwords)
            executor = Executors.newScheduledThreadPool(1);
        init();
        if (!tetris) {

            this.timer = builder.timer;

        } else {
            timer = 5 * Math.pow(0.9, difficulte);
        }
        this.isBlue = false;
        this.isRed = false;
        this.caracterUtile = 0;
        this.timerMinute = 0;
        this.second = 0;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private game game;
        private boolean tetris;
        private double difficulte;
        private boolean playWwords;
        private boolean multi;
        private Object clientOrHost;
        private int nbwords;
        private double timer;

        public gameControler build() {
            return new gameControler(this);
        }

        public Builder game(game game) {
            this.game = game;
            return this;
        }

        public Builder tetris(boolean tetris) {
            this.tetris = tetris;
            return this;
        }

        public Builder difficulte(double difficulte) {
            this.difficulte = difficulte;
            return this;
        }

        public Builder playWwords(boolean playWwords) {
            this.playWwords = playWwords;
            return this;
        }

        public Builder multi(boolean multi) {
            this.multi = multi;
            return this;
        }

        public Builder clientOrHost(Object clientOrHost) {
            this.clientOrHost = clientOrHost;
            return this;
        }

        public Builder nbwords(int nbwords) {
            this.nbwords = nbwords;
            return this;
        }

        public Builder timer(double timer) {
            this.timer = timer;
            return this;
        }
    }

    // Initialise le tampon avec 15 mots
    public final void manageTampon() {
        for (int i = 0; i < 15; i++) {
            addToTampon();
        }
    }

    // Ajoute un mot au tampon
    public final void addToTampon() {
        Random r = new Random();
        int x = r.nextInt(words.size());
        tampon.add(words.get(x));
    }

    // add words to array list
    public final void addToList() {
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

    public final void canRemoveLife() {
        if (tetris) {
            Random r = new Random();
            int next = r.nextInt(5);
            if (next == 2) {
                game.getFirstWordText().setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                game.getFirstWordText().setFill(Color.RED);
                isRed = true;
            }
        }
    }

    /*
     * Si on est en mode tetris, ajout d'une vie lorsqu'un mot en bleu est validé
     * on utilise un random pour déterminer les chance de voir apparaître un mot
     * bleu.
     * Cette probabilité sera ficé à une chance sur 10
     */
    public final void canAddLife() {
        if (tetris) {
            Random r = new Random();
            int next = r.nextInt(10);
            if (next == 2) {
                game.getFirstWordText().setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                game.getFirstWordText().setFill(Color.BLUE);
                isBlue = true;
            }
        }
    }

    /* cette méthode ramènera le joueur au menu principal */
    public static final void toMainMenu() throws IOException {
        gameLaucherController glc = new gameLaucherController(null);
        gameLauncher g = new gameLauncher(null);
        g.setControler(glc);
        glc.setGame(g);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
    }

    /*
     * Dans cette méthode, on va regarder dans un certain fichier;
     * si une game à été sauvegardé, elle est lancée;
     * autrement le bouton n'apparait pas
     */
    public static final void playLastGameRecorded() {

        ArrayList<String> datalist = new ArrayList<>();
        File f = new File("lastGame.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                datalist.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        game g = new game(null);
        double t = Double.parseDouble(datalist.get(0));
        double d = Double.parseDouble(datalist.get(1));
        boolean tet = Boolean.valueOf(datalist.get(2));
        boolean pww = Boolean.valueOf(datalist.get(3));
        int nb = Integer.parseInt(datalist.get(4));
        boolean mult = Boolean.valueOf(datalist.get(5));
        gameControler gc = gameControler.builder().game(g).timer(t).difficulte(d).tetris(tet).playWwords(pww)
                .nbwords(nb).multi(mult).clientOrHost(null).build();

        g.setControler(gc);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
    }

    /*
     * Cette méthode servira à initialiser le panel de jeu
     * à cacher des objets quand le mode de jeu ne s'y prêtera pas
     * mais aussi à initiliser le tampom
     */
    public final void init() {

        if (tetris == false) {
            game.getVie().setVisible(false);
            game.getTetrisCircle().setVisible(false);
            game.getVieValue().setVisible(false);
        } else {
            game.getTetrisCircle().setVisible(true);
            game.getVie().setVisible(true);
            game.getVieValue().setVisible(true);
            game.getVieValue().setText(Integer.toString(vie));
        }

        game.getLastgame().setVisible(false);

        /*
         * Parametrage des données pour le fichier de sauvegarde de la dernière partie
         * jouée
         */

        lastgamesavingfile = String.valueOf(this.timer) + "\n" + String.valueOf(difficulte) + "\n"
                + String.valueOf(tetris) + "\n"
                + String.valueOf(playWwords) + "\n" + String.valueOf(nbwords) + "\n" + String.valueOf(multi);
        try (FileWriter myObj = new FileWriter("lastGame.txt", false)) {
            myObj.write(lastgamesavingfile);
            myObj.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (playWwords) {
            game.getSecond().setText("Words left");
        } else {
            game.getSecond().setText("Seconds");
        }

        if (!multi) {
            this.game.getMenu().setVisible(true);
            this.game.getMenu().setDisable(false);
        } else {
            this.game.getMenu().setVisible(false);
            this.game.getMenu().setDisable(true);
        }

        if (!playWwords) {
            if (tetris) {
                String t = String.format("%.2f", timer);
                this.game.getSecondvalue().setText(t);
            }
        } else {
            this.game.getSecondvalue().setText(String.valueOf(nbwords));
        }

        addToList();
        Collections.shuffle(words);
        manageTampon();
        for (int i = 1; i < tampon.size(); i++) {
            sb.append(tampon.get(i) + " ");
        }
        game.getUserEntry().setText("");
        game.getText().setText(sb.toString());
        game.getFirstWordText().setText(tampon.get(0));

        game.getFirstWordText().setFont(Font.font("Verdana", FontWeight.BOLD, 16));
    }

    /*
     * Cette méthode s'occupe d'afficher les mots en rouge dans le mode multi-joueur
     */
    public final void printRedWord() {
        if (tetris && isRed) {
            isRed = false;
            // envoie du message pour signaler qu'il faut ajouter un mot dans le tampon
            // enemie
            if (clientOrHost instanceof Client) {
                ((Client) clientOrHost).getSock_pw().println("rouge");
            } else {
                ((Server) clientOrHost).getCsock_pw().println("rouge");
            }
            game.getFirstWordText().setFont(Font.font("Verdana", FontWeight.BOLD, 16));
            game.getFirstWordText().setFill(Color.BLACK);
        }
    }

    /*
     * Cette méthode s'occupe de rajouter une vie au joueur si le mot en bleu est
     * validé
     */
    public final void printBlueWord() {
        if (tetris) {
            if (isBlue == true) {
                isBlue = false;
                if (vie < 5) {
                    vie += 1;
                    game.getVieValue().setText(Integer.toString(vie));
                }
                game.getFirstWordText().setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                game.getFirstWordText().setFill(Color.BLACK);
            }
        }
    }

    // affiche game Over dans le textfield
    public final void printGameOver() {

        game.getUserWord().setDisable(true);
        game.getUserWord().setText("Game over");
        if (!multi) {
            game.getLastgame().setVisible(true);
            game.getMenu().setVisible(true);
            game.getMenu().setDisable(false);
        }
        if (!playWwords)
            executor.shutdown();
    }

    /*
     * Cette méthode s'occupe de la validation du mot écrit par l'utilisatieur
     */
    public final void validation() {
        String s = game.getUserWord().getText();
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

            /* tout les 100 mots, la difficulté est augmentée */
            if (counter % 100 == 0) {
                difficulte++;
            }

            /*
             * calcul de la vitesse
             * si le temps ne vaut pas une minute
             */

            if (timerMinute == 0) {
                game.getWordsPerMin().setText(String.valueOf(caracterUtile / 5));
            }
            /*
             * autrement la vitesse est e nombre de caractères utiles, divisé par le temps
             * en minute, divisé encore
             * par 5 (ici, on considère par convention qu’un mot fait en moyenne 5
             * caractères).
             */

            else {
                game.getWordsPerMin().setText(String.valueOf((caracterUtile / timerMinute) / 5));
            }

            // dans le cas où on joue avec un nombre de mot comme délimiteur
            if (playWwords) {
                nbwords -= 1;
                game.getSecondvalue().setText(String.valueOf(nbwords));
                if (nbwords == 0) {
                    printGameOver();
                }
            }
            game.getUserEntry().setFill(Color.GREEN);
            printBlueWord();
            printRedWord();
        }

        /* si le mot tappé est faux */
        else {
            game.getUserEntry().setFill(Color.RED);

            if (tetris) {
                vie -= 1;
                game.getVieValue().setText(Integer.toString(vie));
                if (vie == 0) {
                    printGameOver();
                }
            }
        }

        /*
         * lorsqu'on est en mode tetris, il y une chance de voir apparaître un mot en
         * bleu, qui donnera une vie en plus
         */
        if (tetris) {
            canAddLife();
            if (multi)
                canRemoveLife();
            timer = Math.round((5 * Math.pow(0.9, difficulte)) * 100.0) / 100.0;
        }

        // on met à jour les différents composants
        game.getUserWord().setText("");
        game.getAccuracyvalue().setText(String.valueOf(Math.round((counter * 1.0 / countAll) * 100)));
        game.getUserEntry().setText(s);

        /*
         * on reset le stringbuilder afin d'enlever le premier mot et d'en rajouter un
         * autre à la fin
         */
        sb.setLength(0);
        for (int i = 1; i < tampon.size(); i++) {
            sb.append(tampon.get(i) + " ");
        }

        game.getText().setText(sb.toString());
        game.getFirstWordText().setText(tampon.get(0));

    }

    public final void startGame(KeyEvent ke) {

        if (!playWwords) {
            /*
             * cette portion vérifie si le joueur vient de commencer une partie
             * en particulier si first vaut 1
             * ou bien si il est entrain de jouer, en particulier si first vaut 0
             */
            if (first == 1) {
                first = 0;

                /*cette portion définit l'interval de temps auquel les actions du runable
                *doivent être faites, dans notre cas, c'est tout les secondes
                */
                executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
            }
            if (ke.getCode().equals(KeyCode.SPACE)) {
                validation();
            }
        } else {
            if (ke.getCode().equals(KeyCode.SPACE)) {
                validation();
            }
        }

    }

    /*
     * le runnable nous permet d'implémenter le timer
     * 
     */
    Runnable r = new Runnable() {
        @Override
        public void run() {

            second += 1;
            if (second % 60 == 0) {
                timerMinute += 1;
            }

            if (tetris == false) {
                if (timer > -1) {
                    game.getSecondvalue().setText(String.valueOf((int) timer));
                    timer -= 1;
                } else {
                    if (timer <= 0) {
                        game.getUserWord().setDisable(true);
                        game.getUserWord().setText("Game over");
                    }

                    if (timer == -4) {
                        game.getMenu().setVisible(true);
                        game.getMenu().setDisable(false);
                        executor.shutdown();
                    }

                    timer -= 1;
                }
            } else if (!multi) {
                if (timer > -1) {
                    if (vie == 0)
                        printGameOver();
                    if (timer < 0) {
                        game.getSecondvalue().setText(String.valueOf(0));
                    } else {
                        game.getSecondvalue().setText(String.format("%.2f", timer));
                    }
                    timer -= 1;
                } else {
                    if (timer <= -1) {
                        if (vie == 0)
                            printGameOver();
                        /*
                         * si le tampon est rempli, on force la validation du mot que le joueur écrivait
                         */
                        if (tampon.size() == 15) {
                            validation();
                        } else {
                            /*
                             * quand le timer arive à 0 et que le tampon n'est pas complet, on rajoute un
                             * mot
                             * au tampon et on affiche le mot rajouté
                             */
                            addToTampon();
                            sb.setLength(0);
                            for (int i = 1; i < tampon.size(); i++) {
                                sb.append(tampon.get(i) + " ");
                            }
                            game.getText().setText(sb.toString());
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

    public final void closeServer() {
        if (clientOrHost != null) {
            try {
                if (clientOrHost instanceof Client)
                    ((Client) clientOrHost).getSock().close();
                else {
                    ((Server) clientOrHost).getCsock().close();
                    ((Server) clientOrHost).getSsock().close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final List<String> getTampon() {
        return tampon;
    }

    public final double getDifficulty() {
        return difficulte;
    }

    public final StringBuilder getStringBuilder() {
        return sb;
    }

    public final void setTimer(double t) {
        timer = t;
    }

    public final game getGame() {
        return game;
    }

}
