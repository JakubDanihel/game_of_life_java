import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer; //ak pouzivam aktivny casovac ktory funguje

import java.awt.GridLayout;
import java.util.Random;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {

    int velkost = 50;
    boolean bunkyMapa[][];

    JButton bunky[][];

    public App(){
        //zafixovanie buniek
        bunkyMapa = new boolean[velkost][velkost];
        bunky = new JButton[velkost][velkost];

        //urcenie stavu buniek ak je ziva tak je cierna ak mrtva atak biela
        Random random = new Random();

        //urcenie velkosti okna
        setSize(1000, 1000);
        setLayout(new GridLayout(velkost, velkost));

        for(int i = 0; i < velkost; i++) {
            for(int j = 0; j < velkost; j++) {
                //nahodne generovanie ci je bunka ziva alebo mrtva ak nahodna hodnota je mensia ako 50 tak je ziva ak nie tak je vacsia
                bunkyMapa[i][j] = random.nextInt(100)<40;
                JButton temp = new JButton();
                //System.out.println(bunkyMapa[i][j]);
                
                if(bunkyMapa[i][j]){
                    temp.setBackground(Color.BLACK);
                }else{
                    temp.setBackground(Color.WHITE);
                }
                add(temp);
                bunky[i][j] = temp;
            }
        }

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //sustenie casovaca pre bunky v poli a implementacia praviciel
        Timer timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //docasne pole pre zistenie hodnot
                boolean[][] temp = new boolean[velkost][velkost];
                                
                //prechadzanie polom s bunkami
                for(int i = 0; i < velkost; i++){
                    for(int j = 0; j < velkost; j++){
                        //zistenie poctu zivych susednych buniek v okoli stednej bunky. Pocet susedov je 8 (3x3 -1 (stredna bunka))
                        int pocet = pocetSusedov(i,j);

                        if(bunkyMapa[i][j]){
                            //ak ma bunka menej ako 2 susedov zomiera na samotu
                            if(pocet < 2){
                                temp[i][j] = false;
                            }
                            //ak ma bunka 3 alebo 2 susedov prezije do dalsej generacie
                            if(pocet == 3 || pocet == 2){
                                temp[i][j] = true;
                            }
                            //ak ma bunka viac ako 3 susedov zomiera na preplnenie
                            if(pocet > 3){
                                temp[i][j] = false;
                            }
                        }else if(!bunkyMapa[i][j]){
                            //ak je bunka mrtva a ma presne 3 susedov obzivne
                            if(pocet == 3){
                                temp[i][j] = true;
                            }
                        }

                    }
                }

                //prepisanie ulozenych hodnot z docasneho pola do povodneho pola
                bunkyMapa = temp;
                
                //vykreslenie buniek
                for(int i = 0; i < velkost; i++){
                    for(int j = 0; j < velkost; j++){
                        if(bunkyMapa[i][j]){
                            bunky[i][j].setBackground(Color.BLACK);
                        }else{
                            bunky[i][j].setBackground(Color.WHITE);
                        }
                    }
                }

            }
            
        });

        timer.start();

    }

    //pevne hrany bez pretacania a pretekania na opacnu stranu 
    
    int pocetSusedov(int x, int y){
        int pocet = 0;

        for(int i = x-1; i <= x+1; i++){
            for(int j = y-1; j <= y+1; j++){

                //zabezpecenie aby sme neboli mimo hranic (i = -1)
                try {
                    if(bunkyMapa[i][j]){
                        pocet++;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }

        //zabezpecenie aby sa neratala bunka v strede mapy (3x3)
        if(bunkyMapa[x][y]){
            pocet--;
        }

        return pocet;
    }
    

    //hrana je spojena s opacnou tz: lava hrana je spoje na pravou a vrch je spojeny so spodom
    /* 
    int pocetSusedov(int x, int y){
        int pocet = 0;

        for(int i = x-1; i <= x+1; i++){
            for(int j = y-1; j <= y+1; j++){

                int riadok = (x + i + velkost) % velkost;
                int stlpec = (y + j + velkost) % velkost;

                //zabezpecenie aby sme neboli mimo hranic (i = -1)
                try {
                    if(bunkyMapa[riadok][stlpec]){
                        pocet++;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }

        //zabezpecenie aby sa neratala bunka v strede mapy (3x3)
        if(bunkyMapa[x][y]){
            pocet--;
        }

        return pocet;
    }

*/

    public static void main(String[] args) {
        new App();
    }
}
