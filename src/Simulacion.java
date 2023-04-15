import java.util.ArrayList; 
import java.util.List;

public class Simulacion {
    private Mapa mapa;
    private Jugador jugador;
    private List<Microorganismo> microorganismos;
    private List<Alimento> particulasAlimento;

    public Simulacion() {
        mapa = new Mapa(50, 50);
        jugador = new Jugador(25, 25, 50, 1, 5, 0);
        microorganismos = new ArrayList<>();
        particulasAlimento = new ArrayList<>();
        generarMicroorganismos();
        generarParticulasAlimento();
    }

    private void generarMicroorganismos() {
        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * mapa.getAncho());
            int y = (int) (Math.random() * mapa.getAlto());
            microorganismos.add(new Microorganismo(x, y, 50, 5, 5, 0));
        }
    }

    private void generarParticulasAlimento() {
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * mapa.getAncho());
            int y = (int) (Math.random() * mapa.getAlto());
            particulasAlimento.add(new Alimento(x, y));
        }
    }

    public void correr() {
        while (true) {
            //El jugador se mueve
            //Los microorganismos se mueven
            for (Microorganismo microorg : microorganismos) {
                microorg.moverse(microorganismos);
            }

            //Comprueba las partículas de Alimento consumidas
            for (int i = 0; i < particulasAlimento.size(); i++) {
                Alimento alimento = particulasAlimento.get(i);
                if (alimento.getX() == (jugador.getX()) && alimento.getY() == (jugador.getY())) {
                    jugador.consumirAlimento(alimento);
                    particulasAlimento.remove(i);
                    i--;
                }
            }

            //Comprueba si hay Microorganismos consumidos
            for (int i = 0; i < microorganismos.size(); i++) {
                Microorganismo microorg = microorganismos.get(i);
                if (microorg.getX() == (jugador.getX()) && microorg.getY() == (jugador.getY())) {
                    jugador.consumirMicroorganismo(microorg);
                    microorganismos.remove(i);
                    i--;
                }
            }

            //Genera nuevas partículas Alimento
            while (particulasAlimento.size() <= 20) {
                int x = (int) (Math.random() * mapa.getAncho());
                int y = (int) (Math.random() * mapa.getAlto());
                particulasAlimento.add(new Alimento(x, y));
            }

            //Genera nuevos Microorganismos
            while (microorganismos.size() <= 30) {
                int x = (int) (Math.random() * mapa.getAncho());
                int y = (int) (Math.random() * mapa.getAlto());
                microorganismos.add(new Microorganismo(x, y, 50, 1, 5, 0));
            }
        }
    }
}