import javax.swing.*; 
import java.awt.*;
import java.util.List;

public class GUI extends JFrame {

    private static final int ANCHO_VENTANA = 800;
    private static final int ALTO_VENTANA = 800;
    private static final int TAMANIO_CELDA = 40;

    private Mapa mapa;
    private List<Microorganismo> microorganismos;

    public GUI(Mapa mapa) {
        this.mapa = mapa;
        this.microorganismos = mapa.getMicroorganismos();

        setTitle("Simulacion de Microorganismos");
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new DibujarPanel());

        setVisible(true);
    }

    private class DibujarPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);

            for (int x = 0; x < mapa.getAncho(); x++) {
                for (int y = 0; y < mapa.getAlto(); y++) {
                    int xPos = x * TAMANIO_CELDA;
                    int yPos = y * TAMANIO_CELDA;

                    g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(xPos, yPos, TAMANIO_CELDA, TAMANIO_CELDA);
                    g.setColor(Color.WHITE);
                    g.fillRect(xPos + 1, yPos + 1, TAMANIO_CELDA - 1, TAMANIO_CELDA - 1);

                    //Dibuja los microorganismos en esta celda
                    for (Microorganismo microorganismo : microorganismos) {
                        if (microorganismo.getX() == x && microorganismo.getY() == y) {
                            g.setColor(Color.BLUE);
                            g.fillOval(xPos + 5, yPos + 5, TAMANIO_CELDA - 10, TAMANIO_CELDA - 10);
                            g.setColor(Color.WHITE);
                            g.drawString(String.valueOf(microorganismo.getEnergia()), xPos + 15, yPos + 25);
                        }
                    }
                }
            }
        }
    }
}