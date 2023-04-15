import java.util.List; 
import java.util.Random;

public class Microorganismo {
    //Constantes para valores minimos y maximos de caracteristicas
    private static final int MIN_ENERGIA = 0;
    private static final int MAX_ENERGIA = 100;
    private static final int MIN_VISION = 1;
    private static final int MAX_VISION = 10;
    private static final int MIN_VELOCIDAD = 1;
    private static final int MAX_VELOCIDAD = 10;
    private static final int MIN_EDAD = 0;
    private static final int MAX_EDAD = 100;
    
    //Instanciar variables
    private int energia;
    private int vision;
    private int velocidad;
    private int edad;
    private int x;
    private int y;
    
    //Constructor
    public Microorganismo(int x, int y, int energia, int velocidad, int vision, int edad) {
        this.x = x;
        this.y = y;
        this.energia = 50;
        this.velocidad = 1;
        this.vision = 5;
        this.edad = 0;
    }
    
    //Getters y setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = Math.max(Math.min(energia, MAX_ENERGIA), MIN_ENERGIA);
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = Math.max(Math.min(vision, MAX_VISION), MIN_VISION);
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = Math.max(Math.min(velocidad, MAX_VELOCIDAD), MIN_VELOCIDAD);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = Math.max(Math.min(edad, MAX_EDAD), MIN_EDAD);
    }
    
    //Metodo para determinar si el Microorganismo puede moverse
    public boolean puedeMoverse() {
        return energia > 0;
    }
    
    //Metodo para mover el Microorganismo
    public void moverse(List<Microorganismo> microorganismos) {
        //Comprueba si el Microorganismo puede moverse
        if (!puedeMoverse()) {
            return;
        }
        
        //Encuentra el Microorganismo mas cercano que se puede comer
        Microorganismo presaMasCercana = null;
        int minDistancia = Integer.MAX_VALUE;
        for (Microorganismo otro : microorganismos) {
            //Se salta a si mismo y los microorganismos que estan demasiado lejos o no se pueden comer
            if (this == otro || !puedeVer(otro) || !puedeComer(otro)) {
                continue;
            }
            
            //Calcula la distancia entre los dos microorganismos
            int distancia = (int) Math.sqrt(Math.pow(this.x - otro.x, 2) + Math.pow(this.y - otro.y, 2));
            
            //Actualiza la presa mas cercana si esta esta mas cerca
            if (distancia < minDistancia) {
                minDistancia = distancia;
                presaMasCercana = otro;
            }
        }
        
        //Se mueve hacia la presa mas cercana, o al azar si no se encuentra ninguna presa
        if (presaMasCercana != null) {
            // calculate the direction towards the closest prey
            int dx = presaMasCercana.x - this.x;
            int dy = presaMasCercana.y - this.y;
            
            //Se mueve en esa direccion si es posible, de lo contrario moverse al azar
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0 && this.x < 50 - 1) {
                    this.x++;
                } else if (dx < 0 && this.x > 0) {
                    this.x--;
                } else if (dy > 0 && this.y < 50 - 1) {
                    this.y++;
                } else if (dy < 0 && this.y > 0) {
                    this.y--;
                } else {
                    moverAleatorio();
                }
            } else {
                if (dy > 0 && this.y < 50 - 1) {
                    this.y++;
                } else if (dy < 0 && this.y > 0) {
                    this.y--;
                } else if (dx > 0 && this.x < 50 - 1) {
                    this.x++;
                } else if (dx < 0 && this.x > 0) {
                    this.x--;
                } else {
                    moverAleatorio();
                }
            }
            
            //Se come a la presa mas cercana si esta adyacente
            if (Math.abs(this.x - presaMasCercana.x) <= 1 && Math.abs(this.y - presaMasCercana.y) <= 1) {
                comer(presaMasCercana);
            }
        } else {
            moverAleatorio();
        }
    }
    
    public void moverAleatorio() {
        Random rand = new Random();
        int nuevoX = x + rand.nextInt(velocidad*2+1) - velocidad;
        int nuevoY = y + rand.nextInt(velocidad*2+1) - velocidad;
        if (nuevoX >= 0 && nuevoX < 50) {
            x = nuevoX;
        }
        if (nuevoY >= 0 && nuevoY < 50) {
            y = nuevoY;
        }
    }

    //Metodo para aumentar la energia
    public void alimentar(int amount) {
        energia += amount;
        energia = Math.min(energia, MAX_ENERGIA);
    }
    
    //Metodo para determinar si el Microorganismo puede ver a otro
    public boolean puedeVer(Microorganismo otro) {
        int distancia = Math.abs(this.energia - otro.energia);
        return distancia <= this.vision + otro.vision;
    }
    
    //Metodo para determinar si el Microorganismo puede comerse a otro
    public boolean puedeComer(Microorganismo otro) {
        return this.energia > otro.energia
                || (this.energia == otro.energia && this.velocidad > otro.velocidad)
                || (this.energia == otro.energia && this.velocidad == otro.velocidad && this.edad > otro.edad)
                || (this.energia == otro.energia && this.velocidad == otro.velocidad && this.edad == otro.edad && Math.random() < 0.5);
    }
    
    //Metodo para comerse a otro Microorganismo y obtener sus caracteristicas
    public void comer(Microorganismo otro) {
        alimentar(otro.energia / 2);
        vision += otro.vision / 2;
        velocidad += otro.velocidad / 2;
    }
    
    //Metodo para aumentar la edad
    public void edad() {
        edad++;
        vision--;
    }
}