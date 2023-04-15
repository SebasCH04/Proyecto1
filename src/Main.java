import javax.swing.JFrame; 

public class Main {
    public static void main(String[] args) {
        // Crea el jugador
        Jugador jugador = new Jugador(50, 50, 100, 5, 5, 0);
        
        // Crea el mapa
        Mapa mapa = new Mapa(50, 50);
        
        // Crea algunos microorganismos y los añade al mapa
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 50);
            int y = (int) (Math.random() * 50);
            Microorganismo microorganism = new Microorganismo(x, y, 10, 2, 2, 0);
            mapa.addMicroorganismo(microorganism);
        }
        
        // Crea algunas partículas de comida y las agrega al mapa
        for (int i = 0; i < 30; i++) {
            int x = (int) (Math.random() * 50);
            int y = (int) (Math.random() * 50);
            Alimento food = new Alimento(x, y);
            mapa.addParticulaAlimento(food);
        }
        
        // Crea la GUI e inicia la simulación
        JFrame frame = new GUI(mapa);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}