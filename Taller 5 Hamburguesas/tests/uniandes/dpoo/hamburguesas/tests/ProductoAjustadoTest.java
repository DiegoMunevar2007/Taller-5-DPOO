package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
        private ProductoAjustado productoAj;

        @BeforeEach
        void setUp() throws Exception {
                ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 20000);
                productoAj = new ProductoAjustado(productoBase);
                productoAj.getAgregados().add(new Ingrediente("Queso", 2000));
                productoAj.getAgregados().add(new Ingrediente("Tocineta", 3000));
                productoAj.getEliminados().add(new Ingrediente("Tomate", 1000));
                productoAj.getEliminados().add(new Ingrediente("Lechuga", 500));
        }

        @Test
        void testGetNombre() throws Exception {
                assertEquals("Hamburguesa", productoAj.getNombre(), "El nombre del producto no es correcto");
        }

        @Test
        void testGetPrecio() throws Exception {
                assertEquals(20000, productoAj.getPrecio(), "El precio del producto es incorrecto");
        }

        @Test
        void testGenerarTextoFactura() throws Exception {
                StringBuffer sb = new StringBuffer();
                sb.append("Hamburguesa");
                sb.append("            " + Integer.toString(20000) + "\n");
                sb.append("    +Queso                " + Integer.toString(2000) + "\n");
                sb.append("    +Tocineta                " + Integer.toString(3000) + "\n");
                sb.append("    -Tomate\n");
                sb.append("    -Lechuga\n");
                sb.append("            " + Integer.toString(25000) + "\n");
                String facturaEsperada = sb.toString();
                String facturaGenerada = productoAj.generarTextoFactura();
                assertEquals(facturaEsperada, facturaGenerada, "La factura generada no es correcta");
        }

        @Test
        void testGetAgregados() throws Exception {
                ArrayList<Ingrediente> agregados = productoAj.getAgregados();
                assertEquals(2, agregados.size(), "El número de ingredientes agregados no es correcto");
                assertEquals("Queso", agregados.get(0).getNombre(),
                                "El nombre del ingrediente agregado no es correcto");
                assertEquals(2000, agregados.get(0).getCostoAdicional(),
                                "El precio del ingrediente agregado no es correcto");
                assertEquals("Tocineta", agregados.get(1).getNombre(),
                                "El nombre del ingrediente agregado no es correcto");
                assertEquals(3000, agregados.get(1).getCostoAdicional(),
                                "El precio del ingrediente agregado no es correcto");
        }
        @Test
        void testGetEliminados() throws Exception {
                ArrayList<Ingrediente> eliminados = productoAj.getEliminados();
                assertEquals(2, eliminados.size(), "El número de ingredientes eliminados no es correcto");
                assertEquals("Tomate", eliminados.get(0).getNombre(),
                                "El nombre del ingrediente eliminado no es correcto");
                assertEquals(1000, eliminados.get(0).getCostoAdicional(),
                                "El precio del ingrediente eliminado no es correcto");
                assertEquals("Lechuga", eliminados.get(1).getNombre(),
                                "El nombre del ingrediente eliminado no es correcto");
                assertEquals(500, eliminados.get(1).getCostoAdicional(),
                                "El precio del ingrediente eliminado no es correcto");
        }
        @Test
        void testSetAgregados() throws Exception {
                ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente>();
                agregados.add(new Ingrediente("Queso", 2000));
                agregados.add(new Ingrediente("Tocineta", 3000));
                agregados.add(new Ingrediente("Cebolla", 500));
                productoAj.setAgregados(agregados);
                assertEquals(3, productoAj.getAgregados().size(), "El número de ingredientes agregados no es correcto");
                assertEquals("Queso", productoAj.getAgregados().get(0).getNombre(),
                                "El nombre del ingrediente agregado no es correcto");
                assertEquals(2000, productoAj.getAgregados().get(0).getCostoAdicional(),
                                "El precio del ingrediente agregado no es correcto");
                assertEquals("Tocineta", productoAj.getAgregados().get(1).getNombre(),
                                "El nombre del ingrediente agregado no es correcto");
                assertEquals(3000, productoAj.getAgregados().get(1).getCostoAdicional(),
                                "El precio del ingrediente agregado no es correcto");
                assertEquals("Cebolla", productoAj.getAgregados().get(2).getNombre(),
                                "El nombre del ingrediente agregado no es correcto");
                assertEquals(500, productoAj.getAgregados().get(2).getCostoAdicional(),
                                "El precio del ingrediente agregado no es correcto");
        }
        @Test
        void testSetEliminados() throws Exception {
                ArrayList<Ingrediente> eliminados = new ArrayList<Ingrediente>();
                eliminados.add(new Ingrediente("Tomate", 1000));
                eliminados.add(new Ingrediente("Lechuga", 500));
                eliminados.add(new Ingrediente("Cebolla", 500));
                productoAj.setEliminados(eliminados);
                assertEquals(3, productoAj.getEliminados().size(),
                                "El número de ingredientes eliminados no es correcto");
                assertEquals("Tomate", productoAj.getEliminados().get(0).getNombre(),
                                "El nombre del ingrediente eliminado no es correcto");
                assertEquals(1000, productoAj.getEliminados().get(0).getCostoAdicional(),
                                "El precio del ingrediente eliminado no es correcto");
                assertEquals("Lechuga", productoAj.getEliminados().get(1).getNombre(),
                                "El nombre del ingrediente eliminado no es correcto");
                assertEquals(500, productoAj.getEliminados().get(1).getCostoAdicional(),
                                "El precio del ingrediente eliminado no es correcto");
                assertEquals("Cebolla", productoAj.getEliminados().get(2).getNombre(),
                                "El nombre del ingrediente eliminado no es correcto");
                assertEquals(500, productoAj.getEliminados().get(2).getCostoAdicional(),
                                "El precio del ingrediente eliminado no es correcto");
        }

}
