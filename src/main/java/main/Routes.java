package main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import model.RepositorioConsultoras;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

  public static void main(String[] args) {
    System.out.println("Iniciando servidor");
    
    Spark.port(8080);
    Spark.staticFileLocation("/public");

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    
    Spark.get("/", (request, response) -> {
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("anio", LocalDate.now().getYear());
        modelo.put("consultoras", RepositorioConsultoras.instancia.listarN(3));
        
        return new ModelAndView(modelo, "index.html.hbs");
    }, engine);

    Spark.get("/consultoras", (request, response) -> {
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("anio", LocalDate.now().getYear());
        modelo.put("consultoras", RepositorioConsultoras.instancia.listar());
        return new ModelAndView(modelo, "consultoras.html.hbs");
    }, engine);

      Spark.get("/consultoras/:id", (request, response) -> {
          Map<String, Object> modelo = new HashMap<>();
          modelo.put("anio", LocalDate.now().getYear());
          modelo.put("consultora", RepositorioConsultoras.instancia.buscar(Long.parseLong(request.params(":id"))));
          modelo.put("proyectos", RepositorioConsultoras.instancia.buscar(Long.parseLong(request.params(":id"))).ultimosNProyectos(3));
          return new ModelAndView(modelo, "consultoras_detalle.html.hbs");
      }, engine);

  }

}
