/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author Estudiante
 */
public class Grafo {
    ArrayList<Nodo> nodos;   //public Arco ai;      Nodo p;
    ArrayList<Arco> arcos;
    int cantNodos = 0;
    public int TAM_NODOS = 20;
    int matriz[][];

    public Grafo() {
        nodos = new ArrayList();
        arcos = new ArrayList();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Metodo insertar de la clase">   
    /*public void insertar (Graphics g){
        jPanel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(rbNodo.isSelected()){
                    g.setColor(Color.BLACK);
                    g.fillOval(e.getX()-tamNodos/2, e.getY()-tamNodos/2, tamNodos, tamNodos);
                    nodos.add(new Nodo(cantNodos,e.getX()-tamNodos/2,e.getY()-tamNodos/2,Color.red));
                    g.setColor(Color.white);
                    g.drawString(Integer.toString(cantNodos),e.getX(), e.getY());
                    cantNodos++;
                }else{
                    if(nodoInicial==null){
                        nodoInicial = buscarNodo(e.getX(),e.getY());
                        if(nodoInicial!=null){
                            seleccionarNodo(nodoInicial,g,Color.YELLOW);
                        }
                    }else{
                        nodoFinal = buscarNodo(e.getX(),e.getY());
                        if(nodoFinal!=null){
                            seleccionarNodo(nodoFinal,g,Color.YELLOW);
                            if(nodoFinal.getName()!=nodoInicial.getName()){
                                g.setColor(Color.BLACK);
                                g.drawLine(nodoInicial.x+tamNodos/2, nodoInicial.y+tamNodos/2, nodoFinal.x+tamNodos/2, nodoFinal.y+tamNodos/2);
                                int dist = distancia(nodoInicial.x+tamNodos/2, nodoInicial.y+tamNodos/2, nodoFinal.x+tamNodos/2, nodoFinal.y+tamNodos/2);
                                arcos.add(new Arco(nodoInicial.getName(),nodoFinal.name,nodoInicial.x+tamNodos/2, nodoInicial.y+tamNodos/2, nodoFinal.x+tamNodos/2, nodoFinal.y+tamNodos/2,
                                dist));
                                 g.drawString(Integer.toString(distancia(nodoInicial.x+tamNodos/2, nodoInicial.y+tamNodos/2, nodoFinal.x+tamNodos/2, nodoFinal.y+tamNodos/2)),nodoInicial.x+dist/2,nodoInicial.y+5);
                            }else{
                                seleccionarNodo(nodoInicial,g,Color.BLACK);
                            }
                            seleccionarNodo(nodoInicial,g,Color.BLACK);
                            seleccionarNodo(nodoFinal,g,Color.BLACK);
                            nodoInicial = null;
                        }else{
                            seleccionarNodo(nodoInicial,g,Color.BLACK);
                            nodoInicial = null;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                 
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                 
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 
            }
        });
    }*/// </editor-fold>  

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public ArrayList<Arco> getArcos() {
        return arcos;
    }
    
    public int getCantNodos(){
        return nodos.size();
    }
    
    public void addNodo(Nodo nodo){
        nodos.add(nodo);
    }

    public void addArco(Arco arco){
        arcos.add(arco);
    }
    
    public int distancia(int x1, int y1, int x2, int y2){
        return (int) Math.sqrt(Math.pow(x2-x1, 2.0)+Math.pow(y2-y1, 2.0));
    }
    
    public int distancia(Nodo i, Nodo f){
        return (int) Math.sqrt(Math.pow(f.getX()-i.getX(), 2.0)+Math.pow(f.getY()-i.getY(), 2.0));
    }
    
    public Nodo buscarNodo(int x, int y){
        Nodo nodoR = null;
        for (Nodo nodo : nodos) {
            if (x>=nodo.getX() && y>= nodo.getY() && x<=nodo.getX()+TAM_NODOS && y <=nodo.getY()+TAM_NODOS) {
                nodoR= nodo;
                break;
            }
        }
        return nodoR;
    }
    
    /**
     * Funcion que se encarga de hallar la ruta mas corta del punto de partida al punto de llegada
     * @param ni Nodo inicial que es marcado en el mapa como el punto de partida
     * @param nf Nodo final que es marcado en el mapa como punto de llegada
     * @return un ArrayList que almacena la ruta mas corta
     */
    public ArrayList calcularRuta(Nodo ni, Nodo nf){
        Arco ai = arcoMasCercano(ni), af = arcoMasCercano(nf);
        Nodo nodoi = puntoP(ai,factorU(ni,ai)),
                nodof = puntoP(af,factorU(nf,af));
        ArrayList ruta = new ArrayList();
        imprimirRuta(this.dijkstra(nodos.get(60), nodos.get(8)));     System.out.println(printArco(ai)+" - "+printArco(af));
        ruta.add(nodoi);
        ruta.add(ai);
        ruta.add(af);
        ruta.add(nodof);
        return ruta;
    }
    
    /**
     * Funcion que se encarga de encontrar el Arco mas cercano al nodo que se le pasa por parametro
     * @param ni el nodo del cual se quiere encontrar el arco mas cercano
     * @return el arco mas cercano al nodo ni
     */
    private Arco arcoMasCercano(Nodo ni){
        Arco a = null;
        int min = Integer.MAX_VALUE, d;
        for (Arco arco : arcos) {
            d = this.distanciaRectaPunto(ni, arco);
            if(d<min){
                min = d;
                a= arco;
            }
        } //System.out.println("min = "+min);
        return a;
    }
    
    private double factorU(Nodo c, Arco ar){
        Nodo a = ar.getNodoInicial(), b = ar.getNodoFinal();
        double u;
        u = ((c.getX() - a.getX()) * (b.getX() - a.getX()) + (c.getY() - a.getY()) * (b.getY() - a.getY()));
        u = u / (Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
        return u;
    }
    
    private Nodo puntoP(Arco ar,double u){
        double x,y;
        Nodo p = null, a = ar.getNodoInicial(), b = ar.getNodoFinal();
//        if (u >= 0 && u <= 1) {
            x = a.getX()+u*(b.getX()-a.getX());
            y = a.getY()+u*(b.getY()-a.getY());
            p = new Nodo((int)x,(int)y);    //System.out.println("p = "+p+"\n ar= "+ar);
//        }
        return p;
    }
    
    private int distanciaRectaPunto(Nodo c, Arco ar){
        double u = factorU(c, ar),x,y;//   System.out.println("u = "+u);
        double d = Integer.MAX_VALUE;
        Nodo p, a = ar.getNodoInicial(), b = ar.getNodoFinal();
        if (u >= 0 && u <= 1) {
//            x = a.getX()+u*(b.getX()-a.getX());
//            y = a.getY()+u*(b.getY()-a.getY());
//            p = new Nodo((int)x,(int)y); System.out.println("pd = "+p); this.p = p;
            y = Math.pow(b.getX()-a.getX(),2)+Math.pow(b.getY()-a.getY(),2);
            d = ((b.getX()-a.getX())*(c.getY()-a.getY())-(b.getY()-a.getY())*(c.getX()-a.getX()))/Math.sqrt(y);
        }
        return (int)Math.abs(d);
    }
    
    /*
     DIJKSTRA (Grafo G, nodo_fuente s)       
       para u ∈ V[G] hacer
           distancia[u] = INFINITO
           padre[u] = NULL
           visto[u] = false
       distancia[s] = 0
       adicionar (cola, (s, distancia[s]))
       mientras que cola no es vacía hacer
           u = extraer_mínimo(cola)
           visto[u] = true
           para todos v ∈ adyacencia[u] hacer
               si no visto[v] y distancia[v] > distancia[u] + peso (u, v) hacer
                   distancia[v] = distancia[u] + peso (u, v)
                   padre[v] = u
                   adicionar(cola,(v, distancia[v]))
    
    */
    private int[][] adyacencia;
//    private 
//    private 
//    private 
    private Ruta dijkstra(Nodo ni,Nodo nf){
        int[] distancia = new int[nodos.size()];
        Nodo[] padre = new Nodo[nodos.size()];
        boolean[] visto = new boolean[nodos.size()];
        ArrayList<Ruta> rutas = new ArrayList();
        adyacencia();//System.out.println("\n\n\n\n");
        int f = nodos.indexOf(nf);
        for (Nodo nodo : nodos) {
            int i = nodos.indexOf(nodo);
            distancia[i] = Integer.MAX_VALUE;
            padre[i] = null;
            visto[i] = false;
        }
        distancia[nodos.indexOf(ni)] = 0;
        ArrayList<Nodo> cola =  new ArrayList();  // cola de prioridad
        Ruta ruta = new Ruta();     ruta.add(ni);
        cola.add(ni);     rutas.add(ruta);
        while (!cola.isEmpty()) {
            Nodo nod = extraerMinimo(cola);
            int u = nodos.indexOf(nod);
            visto[nodos.indexOf(nod)] = true;
            for (int j = 0; j < nodos.size(); j++) {
                if(adyacencia[u][j] == 1 && !visto[j] &&distancia[j]>distancia[u]+distancia(nod,nodos.get(j))){
                    distancia[j] = distancia[u]+distancia(nod,nodos.get(j));
                    add(rutas,nod,nodos.get(j));
                    padre[j]=nodos.get(j); 
                    cola.add(nodos.get(j));   //if(j==f)return Ruta.rutaMasCorta(rutas,nf);
                }
            }
        }//System.out.println("padre = "+nodos.indexOf(padre[f]));
//        for (int i = 0; i < nodos.size(); i++) {
//            System.out.print(nodos.indexOf(padre[i])+" ");
//        }
        //System.out.println("\n\n\ndistancia = "+distancia[nodos.indexOf(nf)]);
        return Ruta.rutaMasCorta(rutas,nf);
    }
    
    public void add(ArrayList<Ruta> rutas,Nodo nod,Nodo no){
        for (Ruta ruta : rutas) {
            if (ruta.getLast().equals(nod)) {
                ruta.add(no);
                ruta.addDistancia(distancia(nod,no));
//                if (o instanceof Arco) {
//                   ruta.addDistancia(((Arco) o).getDist());
//                }
                break;
            }
        }
        Ruta r = null;
        for (Ruta ruta : rutas) {
            if (ruta.contains(nod)) {
                r = ruta.subRuta(nod);
                r.addDistancia(distancia(r));
                rutas.add(r);
                break;
            }
        }
    }
    
    private int distancia(Ruta ruta){
        int distancia = 0;
        ArrayList<Nodo> rut = ruta.getRuta();
        for (int i = 0; i < rut.size()-1; i++) {
            distancia += distancia(rut.get(i),rut.get(i+1));
        }
        return distancia;
    }
    
    public void adyacencia(){
        adyacencia = new int[nodos.size()][nodos.size()];
        for (int i = 0; i < nodos.size(); i++) {
            for (int j = 0; j < nodos.size(); j++) {
                adyacencia[i][j] = 0;
            }
        }
        for (Nodo nodo : nodos) {
            for (Arco arco : arcos) {
                if (arco.getNodoInicial().equals(nodo)) {
//                    adya.add(arco.getNodoFinal());
                    adyacencia[nodos.indexOf(nodo)][nodos.indexOf(arco.getNodoFinal())]=1;
                }
                if (arco.getNodoFinal().equals(nodo)) {
//                    adya.add(arco.getNodoInicial());
                    adyacencia[nodos.indexOf(nodo)][nodos.indexOf(arco.getNodoInicial())]=1;
                }
            }
        } 
//        for (int i = 0; i < nodos.size(); i++) {
//            for (int j = 0; j < nodos.size(); j++) {
//                System.out.print(adyacencia[i][j]+" ");
//            }
//            System.out.println("");
//        }
    }
   
    private Nodo extraerMinimo(ArrayList<Nodo> n){
        int min = Integer.MAX_VALUE; Nodo nod= null;
        for (Nodo nodo : n) {
            for (int j = 0; j < nodos.size(); j++) {
                if(adyacencia[nodos.indexOf(nodo)][j] == 1){
                    if(distancia(nodo,nodos.get(j))<min){
                        min = distancia(nodo,nodos.get(j));
                        nod = nodo;
                    }
                }
            }
        }
        n.remove(nod);
        return nod;
    }
    
    private void imprimirRuta(Ruta ruta){
        for (Object object : ruta.getRuta()) {
            System.out.print(nodos.indexOf((Nodo)object)+"   ");
        }
        System.out.println("\n");
    }
    
    /*
    private Nodo extraerMinimo(ArrayList<Nodo> n){
        int min = Integer.MAX_VALUE, i=-1;
        for (Nodo nodo : n) {
            if (distancia[nodos.indexOf(nodo)]<min) {
                min = distancia[nodos.indexOf(nodo)];
                i = n.indexOf(nodo);
            }
        }
        Nodo nodo = nodos.get(i);
        n.remove(i);
        return nodo;
    }
    
    
    
    
    function Dijkstra(Graph, source):
2      dist[source] ← 0                                    // Initialization
3
4      create vertex set Q
5
6      for each vertex v in Graph:           
7          if v ≠ source
8              dist[v] ← INFINITY                          // Unknown distance from source to v
9              prev[v] ← UNDEFINED                         // Predecessor of v
10
11         Q.add_with_priority(v, dist[v])
12
13
14     while Q is not empty:                              // The main loop
15         u ← Q.extract_min()                            // Remove and return best vertex
16         for each neighbor v of u:                      // only v that is still in Q
17             alt ← dist[u] + length(u, v) 
18             if alt < dist[v]
19                 dist[v] ← alt
20                 prev[v] ← u
21                 Q.decrease_priority(v, alt)
22
23     return dist[], prev[]
    */
    
    
//    ArrayList rutaMasCorta;
//    int longitudMasCorta = Integer.MAX_VALUE;
//    List<Nodo> listos=null;
//    // encuentra la ruta más corta desde un nodo origen a un nodo destino
//    public ArrayList encontrarRutaMinimaDijkstra(Nodo inicio,  Nodo fin) {
//        // calcula la ruta más corta del inicio a los demás
//        encontrarRutaMinimaDijkstra(inicio);
//        // recupera el nodo final de la lista de terminados
//        if (!listos.contains(fin)) {
//            System.out.println("Error, nodo no alcanzable");
//            return null;
//        }
//        fin = listos.get(listos.indexOf(fin));
//        int distancia = fin; 
//        // crea una pila para almacenar la ruta desde el nodo final al origen
//        Stack<Nodo> pila =  new Stack();
//        while (fin !=  null) {
//            pila.add(fin);
//            fin = fin.procedencia;
//        }
//        ArrayList ruta = new ArrayList();
//        // recorre la pila para armar la ruta en el orden correcto
//        while (!pila.isEmpty()) ruta.add(pila.pop());
//        return ruta;
//    }
//    
//    private ArrayList<Nodo> adyacentes(Nodo nodo){
//        ArrayList<Nodo> adya = new ArrayList();
//        for (Arco arco : arcos) {
//            if(arco.getNodoInicial().equals(nodo)){
//                adya.add(arco.getNodoFinal());
//            }
//            if(arco.getNodoFinal().equals(nodo)){
//                adya.add(arco.getNodoInicial());
//            }
//        }
//        return adya;
//    }
// 
//    // encuentra la ruta más corta desde el nodo inicial a todos los demás
//    public void encontrarRutaMinimaDijkstra(Nodo inicio) {
//        Queue<Nodo> cola =  new PriorityQueue();  // cola de prioridad
//         
//        listos =  new LinkedList(); // lista de nodos ya revisados
//        cola.add(inicio);  // Agregar nodo inicial a la cola de prioridad
//        while (!cola.isEmpty()) {  // mientras que la cola no esta vacia }
//            Nodo tmp = cola.poll();  // saca el primer elemento
//            listos.add(tmp);  // lo manda a la lista de terminados
////            int p = posicionNodo(tmp.id); 
//            ArrayList<Nodo> adyacentes = adyacentes(tmp);
//            for (Nodo nodo : adyacentes) {
//                if (estaTerminado(nodo)) continue;
//                if (!cola.contains(nodo)) {
//                    cola.add(nodo);
//                    continue;
//                }
//                // si ya está en la cola de prioridad actualiza la distancia menor
//                for (Nodo x : cola) {
//                    // si la distancia en la cola es mayor que la distancia calculada
//                    if (x.equals(nodo) && x.distancia> nodo.distancia) {
//                        cola.remove(x);  // remueve el nodo de la cola
//                        cola.add(nodo);  // agrega el nodo con la nueva distancia
//                        break ;  // no sigue revisando
//                    }
//                }
//            }
//        }
//    }
// 
//    // verifica si un nodo ya está en lista de terminados
//    public boolean estaTerminado(Nodo nodo) {
//        return listos.contains(nodo);
//    }
// 
//    // encontrar la ruta mínima por fuerza bruta
//    public void encontrarRutaMinimaFuerzaBruta(Nodo inicio,  Nodo fin) {
//        int p1 = nodos.indexOf(inicio);
//        int p2 = nodos.indexOf(fin);
//        // cola para almacenar cada ruta que está siendo evaluada
//        Stack<Integer> resultado =  new Stack();
//        resultado.push(p1);
//        recorrerRutas(p1, p2, resultado);
//    }
// 
//    // recorre recursivamente las rutas entre un nodo inicial y un nodo final
//    // almacenando en una cola cada nodo visitado
//    private void recorrerRutas(int nodoI, int nodoF, Stack<Integer> resultado) {
//        // si el nodo inicial es igual al final se evalúa la ruta en revisión
//        if(nodoI==nodoF) {
//            int respuesta = evaluar(resultado);
//            if(respuesta < longitudMasCorta) {
//                longitudMasCorta = respuesta;
//                rutaMasCorta = new ArrayList();
//                for(int x: resultado) rutaMasCorta.add(nodos.get(x));
//            }
//            return;
//        }
//        // Si el nodoInicial no es igual al final se crea una lista con todos los nodos
//        // adyacentes al nodo inicial que no estén en la ruta en evaluación
//        List<Integer> lista = new Vector();
//        for(int i=0; i<grafo.length;i++) {
//            if(grafo[nodoI][i]!=0 && !resultado.contains(i))lista.add(i);
//        }
//        // se recorren todas las rutas formadas con los nodos adyacentes al inicial
//        for(int nodo: lista) {
//            resultado.push(nodo);
//            recorrerRutas(nodo, nodoF, resultado);
//            resultado.pop();
//        }
//        Nodo nodo = nodos.get(nodoI);
//        ArrayList<Integer> adya = new ArrayList();
//        for (int i = 0; i < arcos.size(); i++) {
//            Arco arco = arcos.get(i);
//            if (!resultado.contains(i)) {
//                if (arco.getNodoInicial().equals(nodo)) {
//                    adya.add(arco.getNodoFinal());
//                }
//                if (arco.getNodoFinal().equals(nodo)) {
//                    adya.add(arco.getNodoInicial());
//                }
//            }
//        }
//    }
// 
//    // evaluar la longitud de una ruta
//    public int evaluar(Stack<Integer> resultado) {
//        int resp =  0;
//        int [] r =  new int [resultado.size()];
//        int i =  0;
//        for (int x: resultado) r[i++]=x;
//        for (i=1; i<r.length; i++) resp+=grafo[r[i]][r[i-1 ]]]];
//        return resp;
//    }
    
    
     private void calcularMatriz(){
        matriz = new int [nodos.size()][nodos.size()];
        for (Arco arco : arcos) {
//            matriz[arco.getNodoInicial()][arco.getNodoFinal()] = arco.dist;
//            matriz[arco.getNodoFinal()][arco.getNodoInicial()] = arco.dist;
        }
        prim();
    }
     
     private void prim(){
         boolean vector[] = new boolean[nodos.size()];
         vector[0]=true;
         while (todosSeleccionados(vector)) {             
             int min = menor(matriz,vector);
             vector[min] = true;
         }
     }
     
     private int menor(int[][] matriz, boolean[] vector){
         int menor = Integer.MAX_VALUE;
         int fila = -1, col = -1;
         for (int i = 0; i < matriz.length; i++) {
             if (vector[i]) {
                 for (int j = 0; j < matriz.length; j++) {
                     if (matriz[j][i]!=0 && vector[j]==false && matriz[j][i]<=menor) {
                         menor = matriz[j][i];
                         fila = j;
                         col = i;
                     }
                 }
             }
         }
         System.out.println(""+fila+" - "+col);
         return fila;
     }
     
     private boolean todosSeleccionados(boolean[] vector) {
         for (int i = 0; i < vector.length; i++) {
             if(!vector[i]){
                 return true;
             }
         }
         return false;
    }
     
     public String printArco(Arco arco){
         return nodos.indexOf(arco.getNodoInicial())+","+nodos.indexOf(arco.getNodoFinal())+","+arco.getDist();
     }
}

