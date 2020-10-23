package main;

import model.Consultora;

import model.Proyecto;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.math.BigDecimal;

/**
 * Ejecutar antes de levantar el servidor por primera vez
 * 
 * @author flbulgarelli
 */
public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {

      Consultora blackmesa = new Consultora("black_mesa", 50);
      Consultora apertureSc = new Consultora("aperture_science", 1);
      Consultora dblandit = new Consultora("dblandit", 10);
      Consultora disenios = new Consultora("2diseños", 15);
      Consultora chakana = new Consultora("chakanalabs", 2);

      Proyecto p1 = new Proyecto("Proyecto1", BigDecimal.valueOf(10));
      Proyecto p2 = new Proyecto("Proyecto2", BigDecimal.valueOf(20));
      Proyecto p3 = new Proyecto("Proyecto3", BigDecimal.valueOf(30));
      Proyecto p4 = new Proyecto("Proyecto4", BigDecimal.valueOf(40));
      Proyecto p5 = new Proyecto("Proyecto5", BigDecimal.valueOf(50));

      blackmesa.asignar(p1);
      blackmesa.asignar(p2);
      blackmesa.asignar(p4);
      blackmesa.asignar(p5);
      dblandit.asignar(p3);

      persist(blackmesa);
      persist(apertureSc);
      persist(dblandit);
      persist(disenios);
      persist(chakana);
      persist(p1);
      persist(p2);
      persist(p3);
      persist(p4);
      persist(p5);

    });
  }

}
