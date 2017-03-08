/**
 * DUOC UC 
 * Escuela de Inform&acute;tica y Telecomunicaciones 
 * Sede Padre Alonso de Ovalle
 *
 * Dise&ntilde;o de Estructura de Datos 
 * DED4501
 *
 */
package cl.duoc.ded.test.lista;


import cl.duoc.ded.api.Lista;
import cl.duoc.ded.api.util.Visualizable;
import cl.duoc.ded.exception.ArgumentoInvalidoException;
import cl.duoc.ded.exception.CapacidadExcedidaException;
import cl.duoc.ded.exception.ElementoNuloException;
import cl.duoc.ded.impl.lista.ListaArregloImpl;
import cl.duoc.ded.impl.lista.visor.VisualizarListaEnArchivoImpl;
import cl.duoc.ded.impl.lista.visor.VisualizarListaConsolaImpl;
import org.junit.Test;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 *
 * Clase con pruebas unitarias de la implementaci&oacute;n de Lista con Arreglos.
 *
 * @author o.rodriguezv@profesor.duoc.cl
 */
public class ListaArregloImplTest {

    @Test
    public void testListaArregloImpl() {
        System.out.println("iniciando prueba unitaria del constructor: ListaArregloImpl");
        Lista<String> lista = new ListaArregloImpl(10);

        assertNotNull("el constructor de la lista retornó nulo", lista);

        try {
            lista = new ListaArregloImpl(0);
            fail("el constructor no arrojó excepción al crear una lista para cero elementos");
        } catch (Exception e) {
            assertTrue("la excepción arrojada no es del tipo esperado", (e instanceof ArgumentoInvalidoException));
        }

        try {
            lista = new ListaArregloImpl(-5);
            fail("el constructor no arrojó excepción al crear una lista para -5 elementos");
        } catch (Exception e) {
            assertTrue("la excepción arrojada no es del tipo esperado", (e instanceof ArgumentoInvalidoException));
        }
    }

    @Test
    public void testEstaVacia() {
        System.out.println("iniciando prueba unitaria del método: estaVacia");
        Lista<String> lista = new ListaArregloImpl(10);
        boolean result = lista.isVacia();

        assertEquals("el método no retorna verdadero cuando la lista sí está vacía ", true, result);

        lista.agregarElemento("nuevo elemento");
        result = lista.isVacia();
        assertEquals("el método no retorna falso cuando la lista no está vacía", false, result);
    }

    @Test
    public void testTotalElementos() {
        System.out.println("iniciando prueba unitaria del método: totalElementos");
        Lista<Integer> lista = new ListaArregloImpl(10);
        int expResult = 0;
        int result = lista.getTotalElementos();
        assertEquals("el método no retornó la cantidad correcta elementos, la cual es cero", expResult, result);

        expResult = 5;
        for (int i = 0; i < expResult; i++) {
            lista.agregarElemento(i);
        }
        result = lista.getTotalElementos();
        assertEquals("el método no retornó la cantidad correcta elementos, la cual es cinco", expResult, result);
    }

    @Test
    public void testAgregarElemento() {
        System.out.println("iniciando prueba unitaria del método: agregarElementos");
        Lista<Integer> lista = new ListaArregloImpl(2);

        try {
            lista.agregarElemento(null);
            fail("la lista no arrojó excepción al agregar un elemento nulo");
        } catch (Exception e) {
            assertTrue("la excepción arrojada no es del tipo esperado", (e instanceof ElementoNuloException));
        }

        lista.agregarElemento(1);
        assertEquals("el método no actualizó el número total de elementos correctamente", 1, lista.getTotalElementos());

        lista.agregarElemento(2);
        assertEquals("el método no actualizó el número total de elementos correctamente", 2, lista.getTotalElementos());

        try {
            lista.agregarElemento(3);
            fail("la lista no arrojó excepción al agregar más elementos de los definidos en su constructor");
        } catch (Exception e) {
            assertTrue("la excepción arrojada no es del tipo esperado", (e instanceof CapacidadExcedidaException));
        }

        assertEquals("el método no guardó el elemento en la posición correcta", new Integer(1), lista.getElemento(0));
        assertEquals("el método no guardó el elemento en la posición correcta", new Integer(2), lista.getElemento(1));
    }

    @Test
    public void testInsertarElemento() {
        System.out.println("iniciando prueba unitaria del método: insertarElementos");
        Lista<Integer> lista = new ListaArregloImpl(2);

        try {
            lista.insertarElemento(null);
            fail("la lista no arrojó una excepción al agregar un elemento nulo");
        } catch (Exception e) {
            assertTrue("la excepción arrojada no es del tipo esperado", (e instanceof ElementoNuloException));
        }

        lista.insertarElemento(1);
        assertEquals("la lista no actualizó el número total de elementos correctamente", 1, lista.getTotalElementos());

        lista.insertarElemento(2);
        assertEquals("la lista no actualizó el número total de elementos correctamente", 2, lista.getTotalElementos());

        try {
            lista.insertarElemento(3);
            fail("la lista no arrojó excepción al agregar más elementos de los definidos en su constructor");
        } catch (Exception e) {
            assertTrue("la excepción arrojada no es del tipo esperado", (e instanceof CapacidadExcedidaException));
        }

        assertEquals("el método no guardó el elemento en la posición correcta", new Integer(1), lista.getElemento(1));
        assertEquals("el método no guardó el elemento en la posición correcta", new Integer(2), lista.getElemento(0));
    }

    @Test
    public void testEliminarElemento() {

        System.out.println("iniciando prueba unitaria del método: eliminarElementos");

        Lista<Integer> lista = new ListaArregloImpl(3);

        lista.agregarElemento(1);
        lista.agregarElemento(2); //elemento a eliminar
        lista.agregarElemento(3);

        Integer result = lista.eliminarElemento(2);
        assertNotNull("el método no eliminó el elemento indicado.", result);
        assertEquals("el método ha eliminado un elemento erróneo.", new Integer(2), result);
        assertEquals("el método no actualizó el número total de elementos correctamente", 2, lista.getTotalElementos());
    }
    
    @Test
    public void testEliminar() {

        System.out.println("iniciando prueba unitaria del método: eliminarElementos");

        Lista<Integer> lista = new ListaArregloImpl(3);

        lista.agregarElemento(1);
        lista.agregarElemento(2); //elemento a eliminar
        lista.agregarElemento(3);

        Integer result = lista.eliminar(0);
        assertNotNull("el método no eliminó el elemento indicado, porque ha devuelto null (1)", result);
        assertEquals("el método ha eliminado un elemento erróneo.", new Integer(1), result);
        assertEquals("el método no actualizó el número total de elementos correctamente", 2, lista.getTotalElementos());
        
        result = lista.eliminar(0);
        assertNotNull("el método no eliminó el elemento indicado, porque ha devuelto null (2)", result);
        assertEquals("el método ha eliminado un elemento erróneo.", new Integer(2), result);
        assertEquals("el método no actualizó el número total de elementos correctamente", 1, lista.getTotalElementos());
        
        result = lista.eliminar(0);
        assertNotNull("el método no eliminó el elemento indicado, porque ha devuelto null (3)", result);
        assertEquals("el método ha eliminado un elemento erróneo.", new Integer(3), result);
        assertEquals("el método no actualizó el número total de elementos correctamente", 0, lista.getTotalElementos());
    }

    @Test
    public void testVisualizar() {

        try {
            System.out.println("iniciando prueba unitaria del método: visualizar (por consola)");
            Visualizable<Lista> enConsola = new VisualizarListaConsolaImpl();

            Lista<String> lista = new ListaArregloImpl(5);
            lista.agregarElemento("primerElemento");
            lista.agregarElemento("segundo elemento");
            lista.agregarElemento("3er elemento");
            lista.agregarElemento("último elemento de la lista");

            lista.visualizar(enConsola);

            System.out.println("iniciando prueba unitaria del método: visualizar (por archivo)");
            File createTempFile = File.createTempFile("junit-visualizacion-lista", ".txt");
            System.out.println("creando archivo " + createTempFile.getAbsolutePath() + " para visualizar lista");
            Visualizable<Lista> enArchivo = new VisualizarListaEnArchivoImpl(createTempFile.getAbsolutePath());
            lista.visualizar(enArchivo);
        } catch (Exception ex) {
            Logger.getLogger(ListaArregloImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testVaciar() {
        System.out.println("iniciando prueba unitaria del método: vaciar");

        Lista<Integer> lista = new ListaArregloImpl(5);
        lista.agregarElemento(1);
        lista.agregarElemento(2);
        lista.agregarElemento(3);
        lista.agregarElemento(4);
        
        lista.vaciar();
        
        assertTrue("la lista no debiese tener elementos, pero el método isVacia() dice que no está vacía", lista.isVacia());
        assertTrue("la lista no debiese tener elementos, pero el método getTotalElementos() retorna un valor distinto de cero", lista.getTotalElementos()==0);
    }

    @Test
    public void testExiste() {
        System.out.println("iniciando prueba unitaria del método: existe");

        boolean existe;
        
        Lista<Integer> lista = new ListaArregloImpl(5);
        lista.agregarElemento(1);
        lista.agregarElemento(2);
        lista.agregarElemento(3);
        lista.agregarElemento(4);
        
        existe = lista.existe(3);
        
        assertTrue("el método retorna falso al consultar elemento que SÍ existe",existe);
        
        existe = lista.existe(9);
        assertTrue("el método retorna verdadero al consultar elemento que NO existe",!existe);
    }

    @Test
    public void testGetPrimerElemento() {
        System.out.println("iniciando prueba unitaria del método: getPrimerElemento");

        Integer elemento;
        
        Lista<Integer> lista = new ListaArregloImpl(5);
        lista.agregarElemento(1);
        lista.agregarElemento(2);
        lista.agregarElemento(3);
        lista.agregarElemento(4);
        
        elemento = lista.getPrimerElemento();
        
        assertEquals("el método no retornó el primer elemento que se esperaba.",new Integer(1),elemento);

    }

    @Test
    public void testGetUltimo() {
        System.out.println("iniciando prueba unitaria del método: getUltimoElemento");

        Integer elemento;
        
        Lista<Integer> lista = new ListaArregloImpl(5);
        lista.agregarElemento(1);
        lista.agregarElemento(2);
        lista.agregarElemento(3);
        lista.agregarElemento(4);
        
        elemento = lista.getUltimoElemento();
        
        assertEquals("el método no retornó el último elemento que se esperaba.",new Integer(4),elemento);

    }

    @Test
    public void testGetElemento() {
        System.out.println("iniciando prueba unitaria del método: getPrimerElemento");

        Integer elemento;
        
        Lista<Integer> lista = new ListaArregloImpl(5);
        lista.agregarElemento(1);
        lista.agregarElemento(2);
        lista.agregarElemento(3);
        lista.agregarElemento(4);
        
        elemento = lista.getElemento(2);
        
        assertEquals("el método no retornó el elemento que se esperaba para la 3 posición.",new Integer(3),elemento);

    }
    
    @Test
    public void testIterator() {
        System.out.println("iniciando prueba unitaria de la implementación de Iterable");
        
        Lista<String> lista = new ListaArregloImpl(5);
        lista.agregarElemento("Uno");
        lista.agregarElemento("| |");
        lista.agregarElemento("3");
        lista.agregarElemento("IV");
        
        int i=0;
        
        for(String elemento: lista) {
            System.out.println("\telemento iterado: '"+elemento+"'");
            assertNotNull("el método retornó el elemento "+i+" nulo.",elemento); 
            assertEquals("el método no retornó el elemento esperado en la posición "+i,lista.getElemento(i++), elemento); 
        }
        
        assertEquals("la iteración no recorrió los 4 elementos ",4, i); 
    }

}
