package prueba3;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigator;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.util.concurrent.CountDownLatch;
import java.util.List;
import javax.swing.border.Border;

public class Prueba3 implements ActionListener {
    private Set<Color> colores=new HashSet<>();
    private List<Distancias> lista=new ArrayList<>();
    private Border borde = BorderFactory.createLineBorder(Color.BLACK, 2);
    Color verdeC=new Color(144, 238, 144);
    JTextPane textPane;
    private JScrollPane scrollPane1, scrol;
    private JTextArea area;
    private int k, elemento, numeroR;
    /***************h*/
    int numberOfColumns = 0;
    private double xP, yP, zP;
    private Map<Integer, Color3f> mapaColores=new HashMap<>();
    private Set<Integer> clasesDiferentes;
    private Random random = new Random();
    String texto="", ms;
    ArrayList<Integer> listaC = new ArrayList<>();
    private int aux2=5;
    private JLabel[] etiquetas;
    /**********************************************************/
    JFrame ventana = new JFrame();
    JPanel panelP, panel, panel3;
    JTextField ruta, vecinos, x, y, z;
    JButton b2, b3, b4, ver;
    String rutita;
    JLabel texR, l1, l11,l2, l22, texR1, a, b, c, l3;
    Canvas3D canvas3D;
    SimpleUniverse universo;
    BranchGroup objetoRaiz;
    Font3D fontX, fontY, fontZ;
    Text3D textX, textY, textZ;
    Shape3D shapeX, shapeY, shapeZ;
    Sphere esfera;
    ArrayList<Sphere> esferas = new ArrayList<>();
    ArrayList<TransformGroup> posicionE = new ArrayList<>();
    Transform3D ejes = new Transform3D();
    Transform3D ejesy, ejesz, posicion1, PN, ejeXT, ejeYT, ejeZT;
    TransformGroup ejesyy, ejeszz, mouseGrupo, PN2, ejeXXT, ejeYYT, ejeZZT;
    MouseRotate mr;
    ArrayList<ArrayList<String>> columnData = new ArrayList<>();
    ArrayList<ArrayList<Double>> colx = new ArrayList<>();
    ArrayList<ArrayList<Double>> coly = new ArrayList<>();
    ArrayList<ArrayList<Double>> colz = new ArrayList<>();
    ArrayList<ArrayList<Integer>> tipo = new ArrayList<>();
    ArrayList<Double> datosT = new ArrayList<>();
    ArrayList<Double> cPuntos = new ArrayList<>();
    ArrayList<Double> datosX = new ArrayList<>();
    ArrayList<Double> datosY = new ArrayList<>();
    ArrayList<Double> datosZ = new ArrayList<>();
    ArrayList<Integer> tipoF = new ArrayList<>(); //que flor es.
    int numHilos = 0, esferasPorHilo = 15, it = 0, cont = 50, size;
    static boolean para = true;
    Thread thread;
    CountDownLatch sinCon;
    double valorMaximo, tamama=5.0;
    Color md= new Color(197,0,255);
    
    public Prueba3() {
        Ventana();
    }

    public void Ventana() {
        ventana.setSize(1300, 760);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setTitle("K-Nearest Neighbours");
        comp();
        ventana.setVisible(true);
    }//end Ventana

    public void comp() {
        panelP();
        panel3();
        textos();
        textF();
        area();
        panel();
        botones();
    }//end comp

    public void panelP() {
        panelP = new JPanel();
        panelP.setLayout(null);
        panelP.setBackground(verdeC);
        panelP.setBounds(950, 0, 350, 760);
        ventana.add(panelP);
    }//end panelP
    
    public void panel3(){
    panel3=new JPanel();
    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

    scrollPane1 = new JScrollPane(panel3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane1.setBounds(10,410,300,70);
    panelP.add(scrollPane1);
  }

    public void panel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 950, 760);
        ventana.add(panel);
    }//end panel
    
    public void area(){
        area=new JTextArea();
        area.setBackground(Color.WHITE);
        area.setText("");
        scrol= new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrol.setBounds(10,540,300,100);
        panelP.add(scrol);
    }

    public void textos() {
        texR = new JLabel("Ruta");
        texR.setBounds(150, 60, 200, 30);
        panelP.add(texR);
        
        a= new JLabel("x:");
        a.setBounds(20, 195, 200, 30);
        panelP.add(a);
        
        b = new JLabel("y:");
        b.setBounds(110, 195, 200, 30);
        panelP.add(b);
        
        c = new JLabel("z:");
        c.setBounds(190, 195, 200, 30);
        panelP.add(c);
        
        texR1 = new JLabel("No. vecinos K");
        texR1.setBounds(120, 280, 200, 30);
        panelP.add(texR1);
        
        l1=new JLabel("LEER ARCHIVO");
        l1.setBounds(30,20,250,35);
        l1.setBorder(borde);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setVerticalAlignment(SwingConstants.CENTER);
        panelP.add(l1);
        
        l11=new JLabel("VECINOS MÁS CERCANOS (KNN)");
        l11.setBounds(30,240,250,35);
        l11.setBorder(borde);
        l11.setHorizontalAlignment(SwingConstants.CENTER);
        l11.setVerticalAlignment(SwingConstants.CENTER);
        panelP.add(l11);
        
        l2=new JLabel("CLASES / CLÚSTERS ");
        l2.setBounds(30,370,250,35);
        l2.setBorder(borde);
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        l2.setVerticalAlignment(SwingConstants.CENTER);
        panelP.add(l2);
        
        l22=new JLabel("LISTA DE VECINOS");
        l22.setBounds(30,495,250,35);
        l22.setBorder(borde);
        l22.setHorizontalAlignment(SwingConstants.CENTER);
        l22.setVerticalAlignment(SwingConstants.CENTER);
        panelP.add(l22);
        
        l3=new JLabel("COORDENADAS");
        l3.setBounds(30,140,250,35);
        l3.setBorder(borde);
        l3.setHorizontalAlignment(SwingConstants.CENTER);
        l3.setVerticalAlignment(SwingConstants.CENTER);
        panelP.add(l3);
        
    }//end textos

    public void textF() {
        ruta = new JTextField();
        ruta.setBounds(20, 90, 200, 30);
        panelP.add(ruta);
        
       vecinos = new JTextField();
       vecinos.setBounds(20, 320, 200, 30);
       panelP.add(vecinos);
       
       x = new JTextField();
       x.setBounds(40, 195, 50, 30);
       panelP.add(x);
       
       y = new JTextField();
       y.setBounds(130, 195, 50, 30);
       panelP.add(y);
       
       z = new JTextField();
       z.setBounds(210, 195, 50, 30);
       panelP.add(z);
    }//end textF

    public void botones() {

        b2 = new JButton("Buscar");
        b2.setBounds(225, 90, 90, 30);
        b2.addActionListener(this);
        panelP.add(b2);

        b3 = new JButton("Limpiar");
        b3.setBounds(100, 645, 90, 30);
        b3.addActionListener(this);
        panelP.add(b3);

        b4 = new JButton("Calcular");
        b4.setBounds(225, 320, 90, 30);
        b4.addActionListener(this);
        panelP.add(b4);
        
        ver = new JButton("Ver");
        ver.setBounds(180, 645, 90, 30);
        ver.addActionListener(this);
        panelP.add(ver);

    }//end botones

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == b4) {
            xP=Double.parseDouble(x.getText());
            yP=Double.parseDouble(y.getText());
            zP=Double.parseDouble(z.getText());
            rutita = ruta.getText();
            if (rutita == null || rutita.equals("")||x.getText().equals("")||y.getText().equals("")||z.getText().equals("")||vecinos.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "HAY ESPACIOS EN BLANCO", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //b1.setEnabled(false);
                leer(rutita);
                try{
                 k=Integer.parseInt(vecinos.getText());   
                }catch(NumberFormatException e){
                    System.err.println("Error: Ingrese un número válido.");
                }
               if(k>0){
                  cPuntos.add(xP);
                  cPuntos.add(yP);
                  cPuntos.add(zP);

                  distancias();
                }
               else{
                   JOptionPane.showMessageDialog(null,"Número inválido","Advertencia",JOptionPane.INFORMATION_MESSAGE);
               }
            }
        } else if (ev.getSource() == b2) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                rutita = selectedFile.getAbsolutePath();
                ruta.setText(rutita);
            } else {
                System.out.println("Selección de archivo cancelada");
            }
        } else if (ev.getSource() == b3) {
            limpiarVar();
            limpiarEscena();
        }
        else if(ev.getSource()==ver){
          JOptionPane.showMessageDialog(null,ms,"Fin",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//end actionPerformed
    
    public void limpiarVar(){
        ruta.setText(null);
        vecinos.setText(null);
    }

    public void limpiarEscena() {
        if (objetoRaiz != null) {
            objetoRaiz.detach();
            objetoRaiz = null;
        }
        canvas3D.repaint();

    }//end limpiarEscena


    public void dimensiones() {
        valorMaximo = datosT.get(0);
        for (Double valu : datosT) {
            if (valu > valorMaximo) {
                valorMaximo = valu;
            }
        }
        System.out.println(valorMaximo);
        double redon = redondear(valorMaximo);
        System.out.println(redon);
        double redonn = redon * .1;
        tamama=tamama*redonn;
    }//end canva
    
    public static double redondear(double numero) {
        // Calcula el residuo al dividir por 10
        double residuo = numero % 10;
    
        // Calcula el número redondeado restando el residuo
        double redondeado;
        if (residuo < 1) {
            redondeado = numero - residuo;
        } else {
            redondeado = numero + (10 - residuo);
        }
        return redondeado;
}



    public void canva() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        canvas3D = new Canvas3D(config);
        universo(canvas3D);
        panel.add(canvas3D);
    }//end canva

    public void leer(String rutita) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutita))) {
            String line;
            boolean isFirstRow = true;

            if ((line = reader.readLine()) != null) {
                String[] columnNames = line.split(",");
                numberOfColumns = columnNames.length;
                for (int i = 0; i < numberOfColumns; i++) {
                    columnData.add(new ArrayList<>());
                }
            }

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                for (int i = 0; i < numberOfColumns; i++) {
                    columnData.get(i).add(values[i]);
                    datosT.add(Double.parseDouble(values[i]));
                }
            }

            for (int i = 0; i < numberOfColumns; i++) {
                System.out.println(columnData.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        dimensiones();
        canva();
    }//end leer

    public synchronized void universo(Canvas3D canvas3D) {
        universo = new SimpleUniverse(canvas3D);
        universo.getViewingPlatform().setNominalViewingTransform();
        
        objetoRaiz = branchGroup();
        objetoRaiz.compile();
        universo.addBranchGraph(objetoRaiz);

        View view = universo.getViewer().getView();
        view.setFrontClipDistance(0.1);
        view.setBackClipDistance(100.0);
    }//end universo
    
    public void distancias(){
       for (ArrayList<Double> lista : colx) {
            datosX.addAll(lista);
        }
       for (ArrayList<Double> lista : coly) {
            datosY.addAll(lista);
        }
       for (ArrayList<Double> lista : colz) {
            datosZ.addAll(lista);
        }
       
       for(int i=0; i<size; i++){ 
           double dist=calcDistancia(i);
           lista.add(new Distancias(tipoF.get(i), dist)); //obt de que clase es
       }
       Collections.sort(lista);
       for(int i=0; i<k; i++){
           Distancias distancia = lista.get(i); // Obtener el elemento en la posición i
           double valorDistancia = distancia.getDistancia(); // Obtener la distancia almacenada en el elemento
           texto+="Clase " + distancia.getID() + ": " + valorDistancia+"\n";
           listaC.add(distancia.getID());
       }
       area.setText(texto);
       numeroR= moda(listaC);
       ms="El punto pertenece a la clase: "+numeroR;

        
    }
    
    public int moda(ArrayList<Integer>lista){
       int maxRecuento = 0;
        int numeroMasRepetido = -1;

        for (Integer numero : lista) {
            int recuento = Collections.frequency(lista, numero);
            if (recuento > maxRecuento) {
                maxRecuento = recuento;
                numeroMasRepetido = numero;
            }
        }

        return numeroMasRepetido;
    }
    
    public double calcDistancia(int ind){
    double resultado, d1, d2, d3;
    d1=Math.pow( (cPuntos.get(0)- datosX.get(ind)),2);
    d2=Math.pow( (cPuntos.get(1)- datosY.get(ind)),2);
    d3=Math.pow( (cPuntos.get(2)- datosZ.get(ind)),2);
    resultado=Math.sqrt(d1+d2+d3);
    return resultado;
  }
    
    public Appearance aparienciaRoja(int identif) {
        Appearance apRoja = new Appearance();
        Color3f nose=mapaColores.get(identif);
        Color3f azul = new Color3f(nose);
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(azul);
        apRoja.setColoringAttributes(ca);
        return apRoja;
    }//end aparienciaRoja
    

    
    public Appearance aparienciaNegra() {
        Appearance apNegra = new Appearance();
        Color3f negra = new Color3f(Color.BLACK);
        ColoringAttributes caN = new ColoringAttributes();
        caN.setColor(negra);
        apNegra.setColoringAttributes(caN);
        return apNegra;
    }//end aparienciaVerde

    public Appearance aparienciaMorada() {
        Appearance apVerde = new Appearance();
        Color3f verde = new Color3f(Color.WHITE);
        ColoringAttributes caV = new ColoringAttributes();
        caV.setColor(verde);
        apVerde.setColoringAttributes(caV);
        return apVerde;
    }//end aparienciaVerde

    public void espacio() {
        System.out.println("a ver: "+tamama);
        Vector3d sizes = new Vector3d(tamama, 0.01, tamama);
        com.sun.j3d.utils.geometry.Box rectangulo = new com.sun.j3d.utils.geometry.Box((float) sizes.x, (float) sizes.y,
                (float) sizes.z, new Appearance());

        Vector3d sizess = new Vector3d(0.01, tamama, 0.01);
        com.sun.j3d.utils.geometry.Box z = new com.sun.j3d.utils.geometry.Box((float) sizess.x, (float) sizess.y,
                (float) sizess.z, new Appearance() );

        ejesy = new Transform3D();
        ejesy.setTranslation(new Vector3d(tamama, 0.0, tamama));
        ejesyy = new TransformGroup(ejesy);
        ejesyy.addChild(rectangulo);

        ejesz = new Transform3D();
        ejesz.setTranslation(new Vector3d(0.0, tamama, 0.0));
        ejeszz = new TransformGroup(ejesz);
        ejeszz.addChild(z);

        esfera = new Sphere(0.01f, aparienciaMorada());
        mouseGrupo.addChild(esfera);
        
        fontX = new Font3D(new Font("TimesNewRoman", Font.PLAIN, 1), new FontExtrusion());
        textX = new Text3D(fontX, new String("Y"));
        textX.setAlignment(Text3D.ALIGN_CENTER);
        
        fontY = new Font3D(new Font("TimesNewRoman", Font.PLAIN, 1), new FontExtrusion());
        textY = new Text3D(fontY, new String("Z"));
        textY.setAlignment(Text3D.ALIGN_CENTER);
        
        fontZ = new Font3D(new Font("TimesNewRoman", Font.PLAIN, 1), new FontExtrusion());
        textZ = new Text3D(fontZ, new String("X"));
        textZ.setAlignment(Text3D.ALIGN_CENTER);
        
        shapeX = new Shape3D(textX, new Appearance());
        ejeXT = new Transform3D();
        ejeXT.setTranslation(new Vector3d(tamama*2, 0.0, 0.0));
        ejeXXT = new TransformGroup(ejeXT);
        ejeXXT.addChild(shapeX);
        
        shapeY = new Shape3D(textY, new Appearance());
        ejeYT = new Transform3D();
        ejeYT.setTranslation(new Vector3d(0.0, tamama*2, 0.0));
        ejeYYT = new TransformGroup(ejeYT);
        ejeYYT.addChild(shapeY);
        
        shapeZ = new Shape3D(textZ, new Appearance());
        ejeZT = new Transform3D();
        ejeZT.setTranslation(new Vector3d(0.0, .0, tamama*2));
        ejeZZT = new TransformGroup(ejeZT);
        ejeZZT.addChild(shapeZ);
        
        for(double i=0; i<tamama*2;i=i+.5){
            Vector3d lineas = new Vector3d(0.01, tamama, 0.01);
            com.sun.j3d.utils.geometry.Box lin = new com.sun.j3d.utils.geometry.Box((float) sizess.x, (float) sizess.y,
                (float) sizess.z, new Appearance() );

            Transform3D linea = new Transform3D();
            linea.setTranslation(new Vector3d(0.0, tamama, i));
            TransformGroup lineaa = new TransformGroup(linea);
            lineaa.addChild(lin);
            esfera.addChild(lineaa);
        }
        
        for(double i=0; i<tamama*2;i=i+.5){
            Vector3d lineas = new Vector3d(0.01, tamama, 0.01);
            com.sun.j3d.utils.geometry.Box lin = new com.sun.j3d.utils.geometry.Box((float) sizess.x, (float) sizess.y,
                (float) sizess.z, new Appearance() );

            Transform3D linea = new Transform3D();
            linea.setTranslation(new Vector3d(i, tamama, 0));
            TransformGroup lineaa = new TransformGroup(linea);
            lineaa.addChild(lin);
            esfera.addChild(lineaa);
        }
        
        for(double i=0; i<tamama*2;i=i+.5){
            Vector3d lineas = new Vector3d(0.01, 0.01, tamama);
            com.sun.j3d.utils.geometry.Box lin = new com.sun.j3d.utils.geometry.Box((float) lineas.x, (float) lineas.y,
                (float) lineas.z, new Appearance() );

            Transform3D linea = new Transform3D();
            linea.setTranslation(new Vector3d(0, i, tamama));
            TransformGroup lineaa = new TransformGroup(linea);
            lineaa.addChild(lin);
            esfera.addChild(lineaa);
        }
        
        for(double i=0; i<tamama*2;i=i+.5){
            Vector3d lineas = new Vector3d(tamama, 0.01, 0.01);
            com.sun.j3d.utils.geometry.Box lin = new com.sun.j3d.utils.geometry.Box((float) lineas.x, (float) lineas.y,
                (float) lineas.z, new Appearance() );

            Transform3D linea = new Transform3D();
            linea.setTranslation(new Vector3d(tamama, i, 0));
            TransformGroup lineaa = new TransformGroup(linea);
            lineaa.addChild(lin);
            esfera.addChild(lineaa);
        }
        
        

        esfera.addChild(ejesyy);
        esfera.addChild(ejeszz);
        esfera.addChild(ejeXXT);
        esfera.addChild(ejeYYT);
        esfera.addChild(ejeZZT);
    }//end espacio

    public void agrupacion() {
        if (!columnData.get(2).isEmpty()) {
            int contH = 0;
            int ini = 0;
            size = columnData.get(2).size();
            double numEH = size / 50;
            numHilos = (int) Math.floor(numEH);
            int resto = size - (numHilos * 50);
            System.out.println("" + size);
            System.out.println("num hilos: " + numHilos);

            while (para) {
                colx.add(new ArrayList<>());
                coly.add(new ArrayList<>());
                colz.add(new ArrayList<>());
                tipo.add(new ArrayList<>());

                for (ini = ini + it; ini < cont; ini++) {
                    double cox = Double.parseDouble(columnData.get(0).get(ini));
                    double coy = Double.parseDouble(columnData.get(1).get(ini));
                    double coz = Double.parseDouble(columnData.get(2).get(ini));
                    int t= Integer.parseInt(columnData.get(numberOfColumns-1).get(ini));
                    colx.get(contH).add(cox);
                    coly.get(contH).add(coy);
                    colz.get(contH).add(coz);
                    tipo.get(contH).add(t);
                }

                if (ini == size) {
                    break;
                } else if (contH < numHilos - 1) {
                    contH++;
                    ini = cont;
                    cont = cont + 50;
                } else if (resto != 0) {
                    ini = cont;
                    cont = cont + resto;
                    contH++;
                }
            }
        }
        
                 
        /************************/
        for (ArrayList<Integer> lista : tipo) {
         tipoF.addAll(lista);    
        }
  
       clasesDiferentes=new HashSet<>(tipoF);
        for (Integer elemento : clasesDiferentes) {
            System.out.println(elemento);
        }
        etiquetas=new JLabel[clasesDiferentes.size()];
       for(int i=0; i<clasesDiferentes.size();i++){
           float red=random.nextFloat();
           float green=random.nextFloat();
           float blue=random.nextFloat();
           
           if (clasesDiferentes.contains(i)) {
               elemento=i;
            }
           Color3f randomC=new Color3f(red, green, blue);
           Color rc=new Color(red, green, blue);
           mapaColores.put(elemento, randomC);
           etiquetas[i] = new JLabel("Clase "+i);
           etiquetas[i].setForeground(rc);
           etiquetas[i].setBounds(10, aux2, 100, 20);
           panel3.add(etiquetas[i]);
           aux2+=15;
       }

    }//end agrupacion
    
        public void creacionEsferas() {
        int contadorH = colx.size();
        sinCon = new CountDownLatch(contadorH);
        for (int con = 0; con < contadorH; con++) {
            thread = new Thread(new CrearEsferasRunnable(colx.get(con).size(), colx.get(con), coly.get(con),
                    colz.get(con), aparienciaRoja(con), posicionE, sinCon));
            System.out.println("hilo " + con + " " + colx.get(con).size());
            thread.start();
            System.out.println(con);
        }

        try {
            sinCon.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end creacionEsferas

    public void agregarEsferas() {
        for (TransformGroup numero : posicionE) {
            esfera.addChild(numero);
        }
    }//end agregarEsferas

    public void asignarUniverso() {
        mr = new MouseRotate();
        mr.setTransformGroup(mouseGrupo);
        mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
        KeyNavigatorBehavior knb =new KeyNavigatorBehavior (universo.getViewingPlatform().getViewPlatformTransform());
        BoundingSphere bs = new BoundingSphere(new Point3d(), 1000.0);
        knb.setSchedulingBounds(bs);
        objetoRaiz.addChild(mr);
        objetoRaiz.addChild(knb);
    }//end asignarUniverso
    
        public void crearP(float xP, float yP, float zP){
            Vector3d pc = new Vector3d(0.1, 0.1, 0.1);
            com.sun.j3d.utils.geometry.Box rectangulopc = new com.sun.j3d.utils.geometry.Box((float) pc.x, (float) pc.y,
                (float) pc.z, aparienciaRoja(numeroR));
        
            PN = new Transform3D();
            PN.setTranslation(new Vector3d(yP, zP, xP));
            PN2 = new TransformGroup(PN);
            PN2.addChild(rectangulopc);
            esfera.addChild(PN2);
    }

    public BranchGroup branchGroup() {
        objetoRaiz = new BranchGroup();
        objetoRaiz.setCapability(BranchGroup.ALLOW_DETACH);
        posicion1 = new Transform3D();
        posicion1.setTranslation(new Vector3d(-tamama, -tamama, -tamama*10));
        mouseGrupo = new TransformGroup(posicion1);
        mouseGrupo.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        mouseGrupo.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objetoRaiz.addChild(mouseGrupo);

        espacio();
        agrupacion();
        creacionEsferas();
        agregarEsferas();
        crearP(Float.parseFloat(x.getText()),Float.parseFloat(y.getText()),Float.parseFloat(z.getText()));
        asignarUniverso();
        return objetoRaiz;
    }//end branchGroup

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.setProperty("sun.awt.noerasebackground", "true");
            Prueba3 p = new Prueba3();
        });
    }
}

class CrearEsferasRunnable implements Runnable {
    private int numEsferas;
    private ArrayList<Double> coox;
    private ArrayList<Double> cooy;
    private ArrayList<Double> cooz;
    private Appearance apRoja;
    public static ArrayList<TransformGroup> posicionE;
    private CountDownLatch sinCon;

    public CrearEsferasRunnable(int numEsferas, ArrayList<Double> coox, ArrayList<Double> cooy, ArrayList<Double> cooz,
            Appearance apRoja, ArrayList<TransformGroup> posicionE, CountDownLatch sinCon) {
        this.numEsferas = numEsferas;
        this.coox = coox;
        this.cooy = cooy;
        this.cooz = cooz;
        this.apRoja = apRoja;
        this.posicionE = posicionE;
        this.sinCon = sinCon;
    }

    public void run() {
        for (int i = 0; i < numEsferas; i++) {
            Transform3D posicionCilindro = new Transform3D();
            posicionCilindro.setTranslation(new Vector3d(cooy.get(i), cooz.get(i), coox.get(i)));
            TransformGroup tgCilindro = new TransformGroup(posicionCilindro);
            Sphere esfera1 = new Sphere(0.1f, apRoja);
            tgCilindro.addChild(esfera1);
            posicionE.add(tgCilindro);
        }
        sinCon.countDown();
    }
}//end CrearEsferasRunnable