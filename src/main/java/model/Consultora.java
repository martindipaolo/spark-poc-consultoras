package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Consultoras")
public class Consultora {

  @Id
  @GeneratedValue
  private long id;

  private String nombre;

  @Column(name = "cantidad_empleados")
  private int cantidadEmpleados;

  @OneToMany(mappedBy = "consultoraEncargada")
  private Collection<Proyecto> proyectos;

  protected Consultora() {
  }

  public Consultora(String nombre, int cantidadDeEmpleados) {
    this.nombre = nombre;
    this.cantidadEmpleados = cantidadDeEmpleados;
    this.proyectos = new ArrayList<>();
  }

  public void asignar(Proyecto proyecto) {
    proyectos.add(proyecto);
    proyecto.asignarA(this);
  }

  public int getCantidadEmpleados() {
    return cantidadEmpleados;
  }

  public String getNombre() {
    return nombre;
  }

  public Collection<Proyecto> getProyectos() {
    return proyectos;
  }

  public Collection<Proyecto> ultimosNProyectos(int n) {
    return this.getProyectos().stream().sorted(Comparator.comparingLong(Proyecto::getId).reversed()).limit(n).collect(Collectors.toList());
  }

  public long getId() {
    return id;
  }


}
