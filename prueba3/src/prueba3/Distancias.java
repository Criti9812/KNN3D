
package prueba3;

public class Distancias implements Comparable<Distancias>{
  int id;
  Double distancia;

  public Distancias(int id, Double distancia){
      this.id=id;
      this.distancia=distancia;
  }

  public int getID(){
      return id;
  }

  public Double getDistancia(){
      return distancia;
  }

//se sobreescribe el metodo compareTo de la interfaz
  public int compareTo(Distancias distancia2) { //regresa un entero porque si es <0: objeto actual es menor que el que se esta comparando, si =0: son iguales, si >0: obj. actual e smayor
       return Double.compare(this.distancia, distancia2.distancia);
   }

}