/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;


import java.util.ArrayList;

/**
 * Es la clase que se encaraga de encaragarse con todo auqello relacionado con el grafo
 * @author Estudiante
 */
public class Grafo {
    private ArrayList<Nodo> nodos;
    private ArrayList<Arco> arcos;
    public static final int TAM_NODOS = 20;
    private int[][] adyacencia;
    private int minDistancia;

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
        nodos.add(nodo); //System.out.println(nodo+",    "+nodos.indexOf(nodo));
    }

    public void addArco(Arco arco){
        arcos.add(arco);
    }
    
    public int getMinDistancia(){
        return minDistancia;
    }
    
    public int distancia(int x1, int y1, int x2, int y2){
        return (int) Math.sqrt(Math.pow(x2-x1, 2.0)+Math.pow(y2-y1, 2.0));
    }
    
    public static int distancia(Nodo i, Nodo f){
        return (int) Math.sqrt(Math.pow(f.getX()-i.getX(), 2.0)+Math.pow(f.getY()-i.getY(), 2.0));
    }
    
    /**
     * Metodo que se necraga de encontra el nodo que se le ha pasado como parametro en el ArrayList de nodos
     * @param x el valor de la coordenada en x que se va buscar en el ArrayList
     * @param y el valor de la coordenada en y que se va buscar en el ArrayList
     * @return el nodo que coincide con las caracteristias del nodo pasado por parametro y que esta almacenado en el ArrayList nodos
     */
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
     * Metodo que se necraga de encontra el nodo que se le ha pasado como parametro en el ArrayList de nodos
     * @param n el nodo que se va a buscar
     * @return el nodo que coincide con las caracteristias del nodo pasado por parametro y que esta almacenado en el ArrayList nodos
     */
    public Nodo buscarNodo(Nodo n){
        Nodo nodoR = null;
        for (Nodo nodo : nodos) {
            if (n.getX()>=nodo.getX() && n.getY()>= nodo.getY() && n.getX()<=nodo.getX()+TAM_NODOS && n.getY() <=nodo.getY()+TAM_NODOS) {
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
    public ArrayList<Arco> calcularRuta(Nodo ni, Nodo nf){
        this.minDistancia = 0;
        Arco ai = arcoMasCercano(ni), af = arcoMasCercano(nf);//   System.out.println(printArco(ai)+" - "+printArco(af));
        Ruta r = eleccion(ni,ai,nf,af);
        minDistancia += r.getDistancia();
        ArrayList<Arco> ruta = getArcosRuta(r);
        
        Arco a = new Arco(ni,r.get(0));
        ruta.add(a);
        minDistancia += a.getDist();
        a = new Arco(nf,r.getLast());
        ruta.add(a);
        minDistancia += a.getDist();
        return ruta;
    }
    
    /**
     * Metodo que obtiene todos los arcos que estan entre los vertices del ArrayList ruta del objeto Ruta pasado por parametro
     * @param ruta la ruta solucion de la cual se quieren conocer sus aros
     * @return devulve un ArrayList<Arco> co todos los arcos que constituyen la ruta
     */
    private ArrayList<Arco> getArcosRuta(Ruta ruta){
        ArrayList<Arco> camino = new ArrayList();
        for (int i = 0; i < ruta.getRuta().size()-1; i++) {
            camino.add(getArco(ruta.get(i),ruta.get(i+1)));
        }
        return camino;
    }
    
    /**
     * Metodo que se encraga de obtener el arco que conecta los dos nodos que son pasados como parametros
     * @param n1 nodo 1
     * @param n2 nodo 2
     * @return el arco que une n1 y a n2
     */
    private Arco getArco(Nodo n1, Nodo n2){
        Arco a = null;
        for (Arco arco : arcos) {
            if (arco.nodoInicial.equals(n1) && arco.nodoFinal.equals(n2) || arco.nodoInicial.equals(n2) && arco.nodoFinal.equals(n1)) {
                a = arco;
                break;
            }
        }
        return a;
    }
    
    /**
     * Metodo que se encarga de tomar la eleccion de cual es el mejor nodo para empezar el recorrido y donde debe terminar
     * @param ni nodo donde se inicia el recorrido
     * @param ai el arco deonde se encuentra el nodo donde inicia el recorrido
     * @param nf el nodo donde finalizael recorrido
     * @param af el arco donde se encuentra el nodo donde finaliza el recorrido
     * @return la ruta mas corta
     */
    private Ruta eleccion(Nodo ni,Arco ai,Nodo nf, Arco af){
        ArrayList<Ruta> rutas = new ArrayList();                   
        Nodo nodof = buscarNodo(nf), nodoi;
        if(nodof == null){
            if (distancia(af.getNodoInicial(), nf) <= distancia(af.getNodoFinal(), nf)) {
                nodof = af.getNodoInicial();
            } else {
                nodof = af.getNodoInicial();
            }
        }
        nodoi = buscarNodo(ni);
        if(nodoi == null){System.out.println("\n");
            //Ruta r = dijkstra(ai.getNodoInicial(), nodof); imprimirRuta(r);
            rutas.add(dijkstra(ai.getNodoInicial(), nodof));
            //r = dijkstra(ai.getNodoFinal(), nodof);// imprimirRuta(r);System.out.println("\n");
            rutas.add(dijkstra(ai.getNodoFinal(), nodof));
        }else{
            rutas.add(dijkstra(nodoi, nodof));
        }
        return Ruta.rutaMasCorta(rutas, nodof);
    }
    
    private Nodo nodoMasCercano(Nodo nodo, Arco arco){
        return null;
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
    
    /**
     * Metodo usado para encontrar el factor u usado para hallar la distancia del punto en el que usuario dio click sobre el panel
     * al la arista ar
     * @param c nodo que contiene la informacion de donde el usuario dio click
     * @param ar el arco del cual se hallara su distancia al punto C
     * @return 
     */
    private double factorU(Nodo c, Arco ar){
        Nodo a = ar.getNodoInicial(), b = ar.getNodoFinal();
        double u;
        u = ((c.getX() - a.getX()) * (b.getX() - a.getX()) + (c.getY() - a.getY()) * (b.getY() - a.getY()));
        u = u / (Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
        return u;
    }
    
    /**
     * si el factor u es el adecuado se puede hallar el punto mas cercano del Nodo ni al arco
     * @param ni nodo del cual se quiere hallar su punto mas cercano sobre la arista
     * @return  el nodo ni reflejado sobre su arista mas cercana
     */
    public Nodo puntoP(Nodo ni){
        double x,y,u;
        Arco ar = arcoMasCercano(ni);
        Nodo a = ar.getNodoInicial(), b = ar.getNodoFinal();
        u = factorU(ni, ar);
        x = a.getX() + u * (b.getX() - a.getX());
        y = a.getY() + u * (b.getY() - a.getY());
//        p = new Nodo((int) x, (int) y);    //System.out.println("p = "+p+"\n ar= "+ar);
//        }
        return new Nodo((int) x, (int) y);
    }
    
    private Nodo puntoP(Arco ar,double u){
        double x,y;
        Nodo p = null, a = ar.getNodoInicial(), b = ar.getNodoFinal();
//        if (u >= 0 && u <= 1) {
        x = a.getX() + u * (b.getX() - a.getX());
        y = a.getY() + u * (b.getY() - a.getY());
        p = new Nodo((int) x, (int) y);    //System.out.println("p = "+p+"\n ar= "+ar);
//        }
        return p;
    }
    
    /**
     * Metodo usado para hallar la distancia entre un punto yuna recta
     * @param c 
     * @param ar
     * @return la distancia entre el punto c y la arista ar
     */
    private int distanciaRectaPunto(Nodo c, Arco ar){
        double u = factorU(c, ar),x,y;//   System.out.println("u = "+u);
        double d = Integer.MAX_VALUE;
        Nodo a = ar.getNodoInicial(), b = ar.getNodoFinal();
        if (u >= 0 && u <= 1) {
//            x = a.getX()+u*(b.getX()-a.getX());
//            y = a.getY()+u*(b.getY()-a.getY());
//            p = new Nodo((int)x,(int)y); System.out.println("pd = "+p); this.p = p;
            y = Math.pow(b.getX()-a.getX(),2)+Math.pow(b.getY()-a.getY(),2);
            d = ((b.getX()-a.getX())*(c.getY()-a.getY())-(b.getY()-a.getY())*(c.getX()-a.getX()))/Math.sqrt(y);
        }
        return (int)Math.abs(d);
    }
    
    
    /**
     * Meotod Dijkstra con pequeñas modificaciones usado para poder hallar el camino mas corto entre dos nodos de un grafo
     * @param ni
     * @param nf
     * @return la ruta mas corta
     */
    private Ruta dijkstra(Nodo ni,Nodo nf){
        int[] distancia = new int[nodos.size()];
        boolean[] visto = new boolean[nodos.size()];
        ArrayList<Ruta> rutas = new ArrayList();
        for (Nodo nodo : nodos) {
            int i = nodo.getName();
            distancia[i] = Integer.MAX_VALUE;
            visto[i] = false;
        }
        distancia[ni.getName()] = 0;
        ArrayList<Nodo> cola =  new ArrayList();  // cola de prioridad
        Ruta ruta = new Ruta();
        ruta.add(ni);
        cola.add(ni);  
        rutas.add(ruta);
        while (!cola.isEmpty()) {
            Nodo nod = extraerPrimero(cola);
            int u = nod.getName();
            visto[nod.getName()] = true;
            for (int j = 0; j < nodos.size(); j++) {
                if(adyacencia[u][j] == 1 && distancia[j]>distancia[u]+distancia(nod,nodos.get(j))){
                    distancia[j] = distancia[u]+distancia(nod,nodos.get(j));
                    add(rutas,nod,nodos.get(j));
                    cola.add(nodos.get(j)); //  if(j==nf.getName()){ imprimirRuta(Ruta.rutaMasCorta(rutas,nf));System.out.println("+++++");}
                }
            }
        }
        return Ruta.rutaMasCorta(rutas,nf);
    }
    
    /**
     * Añade un nodo a la subruta mas corta que termine en el nodo nod para añadirle el nodo no
     * @param rutas el ArrayList que contiene todas la rutas armadas hasta ahora ppor el algoritmo Dijkstra
     * @param nod el nodo padre de no
     * @param no el nodo que se añadira a la nueva ruta
     */
    public void add(ArrayList<Ruta> rutas,Nodo nod,Nodo no){
        for (Ruta ruta : rutas) {
            if (ruta.getLast().equals(nod)) {
                ruta.add(no);
                ruta.addDistancia(distancia(nod,no));
                return;
            }
        }
        Ruta r = subRutaMasCorta(rutas,nod);
        r.add(no);
        r.addDistancia(distancia(nod,no));
        rutas.add(r);
    }
    
    /**
     * Metodo que contiene la subruta mas corta dentro del arraylist que le hes pasado por parametro
     * @param rutas arrayList de rutas en el cual se buscara la subruta mas corta
     * @param nod el nodo en el cual debe terminar la subruta
     * @return la subruta que termina en nod y que es la mas corta entre todas las rutas del arrayList
     */
    private Ruta subRutaMasCorta(ArrayList<Ruta> rutas, Nodo nod){
        Ruta min = new Ruta();  min.addDistancia(Integer.MAX_VALUE);
        for (Ruta ruta : rutas) {
            Ruta r = ruta.subRuta(nod);
            if (ruta.contains(nod) && min.compareTo(r)>0) {
                min = r;
                min.addDistancia(distancia(min));
            }
        }
        return min;
    }
    
    /**
     * Metodo que obtiene la distancia que recorre toda la ruta
     * @param ruta la ruta a la cual se le sacara la distancia
     * @return la distancia que hay que recorrer para caminar por toda la ruta
     */
    public static  int distancia(Ruta ruta){
        int distancia = 0;
        ArrayList<Nodo> rut = ruta.getRuta();
        for (int i = 0; i < rut.size()-1; i++) {
            distancia += distancia(rut.get(i),rut.get(i+1));
        }
        return distancia;
    }
    
    /**
     * Metodo que se encarga de obtener la matriz de adyacencia usada por el algoritmo Dijkstra
     */
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
                    adyacencia[nodo.getName()][arco.getNodoFinal().getName()]=1;
                }
                if (arco.getNodoFinal().equals(nodo)) {
//                    adya.add(arco.getNodoInicial());
                    adyacencia[nodo.getName()][arco.getNodoInicial().getName()]=1;
                }
            }
        } 
    }
   
    /**
     * Metodo que se encarga de encontrar, retornar y eliminar el nodo con el adyacente mas cercano de toda la lista  n
     * @param n la lista de todos los nodos que seran comparados
     * @return el nodo con el adyacente mas cercano
     */
    private Nodo extraerMinimo(ArrayList<Nodo> n){
        int min = Integer.MAX_VALUE; Nodo nod= null;
        for (Nodo nodo : n) {
            for (int j = 0; j < nodos.size(); j++) {
                if(adyacencia[nodo.getName()][j] == 1){
                    int dist = distancia(nodo,nodos.get(j));
                    if(dist<min){
                        min = dist;
                        nod = nodo;
                    }
                }
            }
        }
        n.remove(nod);
        return nod;
    }
    
    /**
     * Metodo que se encarga de la extracion de los elemntos de la cola
     * @param n la cola de la que se va a extraer el elemento
     * @return el elemento extraido de la cola
     */
    private Nodo extraerPrimero(ArrayList<Nodo> n){
        Nodo nod = n.get(0);
        n.remove(nod);
        return nod;
    }
    
    /**
     * Un pequeño metodo que imprime en consola los nodos pertencientes a la ruta pasada por parametro
     * @param ruta la ruta a la cual  se le van a mostar sus vertices
     */
    private void imprimirRuta(Ruta ruta){
        for (Object object : ruta.getRuta()) {
            System.out.print(((Nodo)object).getName()+"   ");
        }
        System.out.println("\n");
    }
     
     public String printArco(Arco arco){
         return nodos.indexOf(arco.getNodoInicial())+","+nodos.indexOf(arco.getNodoFinal())+","+arco.getDist();
     }
}

