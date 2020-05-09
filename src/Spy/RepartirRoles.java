package Spy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.Timer;

import javax.swing.BoxLayout;

public class RepartirRoles extends JFrame implements KeyListener{
	
	// Constructor vacío
	/*public RepartirRoles() {
		
	}*/
	
	// Panel en el que colocaremos todos los demás JPanels:
	private JPanel pnlPpal;
	
	// Tipos de letras diferentes
	private Font fNegrita = new Font("Calibri", Font.BOLD, 20);
	private Font fNormal = new Font("Calibri",Font.PLAIN, 20);
	private Font fRolLugar = new Font("Calibri", Font.BOLD, 30);
	
	// Controlador de la pantalla en la que estamos
	private int nPantalla = 1;
	
	// Controlador de roles entregados
	private boolean isLastSpyPnl = false;
	private boolean isLastLocPnl = false;
	
	// Atributos de la clase:
	private int nJugadores;
	private ArrayList<String> lugares;
	private String loc;
	private int spy;
	private int jugador;
	private int minutos = 0;
	private int segundos = 0;
	
	// Paneles para la 1ª pantalla
	private JPanel pnlNorth1;
	private JPanel pnlCenter1;
	private JPanel pnlSouth1;
	private JPanel pnlTop1;
	private JPanel pnlBottom1;
	
	// Panel información master
	private JPanel pnlMasterScreen;
	private JLabel lblJugador;
	
	// Panel para el jugador espía
	private JPanel pnlSpy;
	
	// Panel para los demás jugadores
	private JPanel pnlLocation;
	
	// Panel con todos los lugares
	private JPanel pnlAllLoc;
	private JPanel pnlCrono;
	private JLabel lTime;
	private String tiempo;
	private Timer timing;
	private int secs = 0;
	private int mins = 0;
	private String startJugador = "Jugador X";
	
	
	// Constructor de uso
	public RepartirRoles(int numJugadores, String lugar, int espia, ArrayList<String> lugarList, int startPlayer) {
		// ** Nuestra ventana:
		this.setTitle("EL ESPÍA (Juego en marcha)");
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cerramos con la X, el programa sigue en ejecución, con esta línea lo paramos
		this.setSize(550,275); //Tamaño de la ventana
		this.setLocationRelativeTo(null); // Esto hace que se centre la ventana
		
		// Inicializamos los atributos que se nos pasan al crear la clase:
		nJugadores = numJugadores;
		loc = lugar;
		spy = espia;
		jugador = 1;
		lugares = new ArrayList<String>();
		lugares = lugarList;
		
		// Objetos de la ventana:
		// Panel en la parte superior:
		pnlPpal = new JPanel();
		this.setContentPane(pnlPpal);

		pnlNorth1 = new JPanel();
		pnlPpal.add(pnlNorth1, BorderLayout.NORTH);
		
		JLabel lblStartPlay1 = new JLabel("A continuación, se asignarán los roles");
		pnlNorth1.add(lblStartPlay1);
		lblStartPlay1.setFont(fNormal);
		
		// Panel central en el que pondremos dos paneles más:
		pnlCenter1 = new JPanel();
		pnlPpal.add(pnlCenter1, BorderLayout.CENTER);
		pnlCenter1.setLayout(new BoxLayout(pnlCenter1, BoxLayout.Y_AXIS));
		
		pnlTop1 = new JPanel();
		pnlCenter1.add(pnlTop1);
		
		JLabel lblStartPlay2 = new JLabel("a los diferentes jugadores.");
		pnlTop1.add(lblStartPlay2);
		lblStartPlay2.setFont(fNormal);
		
		pnlBottom1 = new JPanel();
		pnlCenter1.add(pnlBottom1);
		
		JLabel lblAdvertisement1 = new JLabel("RECUERDA:");
		pnlBottom1.add(lblAdvertisement1);
		lblAdvertisement1.setFont(fNegrita);
		
		// Panel en la parte inferior:
		pnlSouth1 = new JPanel();
		pnlPpal.add(pnlSouth1, BorderLayout.SOUTH);
		
		JLabel lblAdvertisement2 = new JLabel("solo debes mirar la pantalla cuando sea tu turno");
		pnlSouth1.add(lblAdvertisement2);
		lblAdvertisement2.setFont(fNegrita);
		System.err.println(spy);
		
		//KeyListener para apretar el espacio e ir pasando de pantalla (de roles para los distintos jugadores)
		this.addKeyListener(this);
		
	}

	// Métodos implementados
	@Override // Método que se usa para cuando se ha escrito una letra 
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override // Método que se usa para cuando se presiona una tecla
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override // Método que se usa paracuando se suelta una tecla (se diferencia entre tecla y letra!!)
	public void keyReleased(KeyEvent kE) {
		if(kE.getKeyChar() == ' ') {
		//System.out.println("Has presionado la tecla espacio");
		updateScreen(nPantalla);
		// Cada vez que hagamos un update de pantalla lo controlaremos con este dígito de control
		nPantalla += 1;
		}
	}
	
	// Cada vez que le demos al espacio, entraremos en este método, que cambiará los paneles que se ven en el JFrame en ese momento
	public void updateScreen(int pantalla) {
		
		if( pantalla == nJugadores*2+1) { // Si hemos pasado tantas pantallas como jugadores, mostraremos la última pantalla
			System.out.println("Aquí mostraremos el nombre de todos los lugares que pueden aparecer en el juego de forma estática");
			// Este if lo utilizamos para quitar los JPanels que tienen que ver con el rol de espía
			if(isLastSpyPnl) {
				removeJPanel(pnlSpy);
			}
			// Este if lo utilizamos para quitar los JPanels que tienen que ver con la muestra del lugar
			if(isLastLocPnl) {
				removeJPanel(pnlLocation);
			}
			// Como ya se han mostrado todos los roles, quitamos el keylistener del espacio, así la pantalla se queda mostrando los lugares
			this.removeKeyListener(this);
			// Mostramos la pantalla con todos los lugares
			printAllLocScreen();
			printCronoPanel();
			pnlPpal.repaint();
			timing.start();
			//System.out.println("Paso por el timing start");
			
		} else if(pantalla%2 == 1) { // Mostramos la pantalla que puede mirar el master para dar instrucciones
			//System.out.println("Aquí entraremos cada vez que el nº pantalla a mostrar sea impar");
			System.out.println("MASTER: Que todo el mundo cierre los ojos excepto el jugador "+jugador+".");
			if( pantalla == 1) { // Si es la primera vez, retiramos los JPanels que tienen que ver con la primera pantalla
				removeJPanel(pnlNorth1);
				removeJPanel(pnlCenter1);
				removeJPanel(pnlSouth1);
				// Iniciamos todos los objetos que van a tener los distintos paneles
				initMasterScreen();
				initSpyScreen();
				initLocScreen();
				initAllLocScreen();
				initCronoPanel();
			}
			// If de control para saber si el último panel mostrado fue el de espía
			if(isLastSpyPnl) {
				removeJPanel(pnlSpy);
			}
			// If de control para saber si el útlimo panel mostrado fue el de lugar
			if(isLastLocPnl) {
				removeJPanel(pnlLocation);
			}
			// Cambiamos el número del jugador y mostramos la pantalla del master
			lblJugador.setText("Que abra los ojos el jugador "+ jugador);
			printMasterScreen();
			
		} else if(pantalla%2 == 0) { // Aquí controlamos si tenemos que mostrar la pantalla de rol de jugador
			//System.out.println("Aquí entraremos cada vez que el nº pantalla a mostrar sea par");
			if(jugador == spy) { // Aquí controlamos si el jugador es espía y si lo es, le mostramos el panel de espía
				System.out.println("ESPÍA");
				removeJPanel(pnlMasterScreen);
				printSpyScreen();
				isLastSpyPnl = true;
				isLastLocPnl = false;
			} else { // Si no es espía, mostramos el panel del lugar
				System.out.println(loc);
				removeJPanel(pnlMasterScreen);
				printLocScreen();
				isLastLocPnl = true;
				isLastSpyPnl = false;	
			}
			//removeJPanel(pnlMasterScreen);
			pnlPpal.repaint();
			jugador += 1;
		}
	}
	
	/*private void changePanel(JPanel pnlDesactivar, JPanel pnlActivar) {
		pnlDesactivar.setVisible(false);
		pnlPpal.add(pnlActivar);
		pnlActivar.setVisible(true);
	}*/
	
	/*
	 * masterScreen();
	 * Método que diseña la pantalla que tiene que ver el master
	 * @param void
	 * @return void
	 */
	private void initMasterScreen() {
		pnlMasterScreen = new JPanel();
		pnlMasterScreen.setLayout(new BoxLayout(pnlMasterScreen, BoxLayout.PAGE_AXIS));
		JLabel lblMaster = new JLabel("MASTER:");
		lblMaster.setFont(fNegrita);
		lblMaster.setAlignmentX(CENTER_ALIGNMENT);
		pnlMasterScreen.add(lblMaster);
		JLabel lblCerrarOjos = new JLabel(" Que todos los jugadores cierren los ojos...");
		lblCerrarOjos.setFont(fNormal);
		pnlMasterScreen.add(lblCerrarOjos);
		lblJugador = new JLabel("Que abra los ojos el jugador "+ jugador);
		lblJugador.setFont(fNegrita);
		pnlMasterScreen.add(lblJugador);
		pnlPpal.repaint();
	}
	
	/*
	 * printMasterScreen();
	 * Método que muestra la pantalla del master
	 * @param void
	 * @return void
	 */
	private void printMasterScreen() {
		pnlPpal.add(pnlMasterScreen, BorderLayout.CENTER);
		//pnlPpal.setBackground(Color.GRAY);
		pnlMasterScreen.setVisible(true);
		pnlMasterScreen.repaint();
		pnlPpal.repaint();
	}
	
	/*
	 * spyScreen();
	 * Método que diseñará el JPanel para mostrar cuando el jugador al que le toque mirar sea un espía
	 * @param void
	 * @return void
	 */
	private void initSpyScreen() {
		pnlSpy = new JPanel();
		pnlSpy.setLayout(new BoxLayout(pnlSpy, BoxLayout.PAGE_AXIS));
		pnlSpy.setBackground(Color.BLACK);
		JLabel lblSpy = new JLabel("ESPÍA");
		lblSpy.setFont(fRolLugar);
		lblSpy.setForeground(Color.WHITE);
		pnlSpy.add(lblSpy);
	}
	
	/*
	 * printSpyScreen();
	 * Método que mostrará el panel de espía
	 * @param void
	 * @return void
	 */
	private void printSpyScreen() {
		pnlPpal.add(pnlSpy, BorderLayout.CENTER);
		pnlSpy.setVisible(true);
		pnlSpy.repaint();
		pnlPpal.repaint();
	}
	
	/*
	 * locScreen();
	 * Método que generará el JPanel para mostrar la localización del jugador
	 * @param void
	 * @return void
	 */
	private void initLocScreen() {
		pnlLocation = new JPanel();
		pnlLocation.setLayout(new BoxLayout(pnlLocation, BoxLayout.Y_AXIS));
		pnlLocation.setBackground(Color.BLACK);
		JLabel lblLugar = new JLabel(loc);
		lblLugar.setFont(fRolLugar);
		lblLugar.setForeground(Color.WHITE);
		pnlLocation.add(lblLugar);
	}
	
	/*
	 * printLocScreen();
	 * Método que mostrará el Jpanel del lugar al jugador que le toque
	 * @param void 
	 * @return void
	 */ 
	private void printLocScreen() {
		pnlPpal.add(pnlLocation, BorderLayout.CENTER);
		pnlLocation.setVisible(true);
		pnlLocation.repaint();
		pnlPpal.repaint();
	}
	
	/* removeJPanel();
	 * Método que utlizamos para remover los JPanel de otro JPanel para poder mostrar otros JPanel
	 * @param JPanel JPanel que queremos quitar
	 * @return void
	 */
	private void removeJPanel(JPanel pnlQuitar) {
		pnlQuitar.setVisible(false);
		pnlPpal.remove(pnlQuitar);
	}
	
	/* intiAllLocScreen();
	 * Método que inicializa el JPanel que mostrará todas las localizaciones que tiene cargadas el programa
	 * @param void
	 * @return void
	 */
	private void initAllLocScreen() {
		pnlAllLoc = new JPanel();
		pnlAllLoc.setLayout(new GridLayout(10,4));
		for(int i=0; i < lugares.size()-1; i++) {
			JLabel lblSitio = new JLabel(lugares.get(i));
			pnlAllLoc.add(lblSitio);
		}
	}
	
	/* printAllLocScreen();
	 * Método que utilizaremos para mostrar el JPanel con todas las localizaciones del juego
	 * @param void
	 * @return void
	 */
	private void printAllLocScreen() {
		pnlPpal.add(pnlAllLoc, BorderLayout.CENTER);
		pnlAllLoc.setVisible(true);
		pnlAllLoc.repaint();
		pnlPpal.repaint();
	}
	
	private ActionListener cambio = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			secs++;
			if(secs == 60) {
				secs = 0;
				mins++;
			}
			if(mins == 60) {
				mins = 0;
			}
			reprintCronoPanel();
			//System.out.println(mins+":"+secs);
			if(mins == nJugadores) {
				timing.stop();
			}
		}
	};
	
	/*
	 * initCronoPanel();
	 * Método que inicializará el JPanel donde pondremos el cronometro
	 * @param void
	 * @return void
	 */
	private void initCronoPanel() {
		pnlCrono = new JPanel();
		pnlCrono.setLayout(new GridLayout(2,0));
		//pnlCrono.setBackground(Color.BLACK);
		timing = new Timer(1000, cambio);
		tiempo = "0:0";
		lTime = new JLabel(tiempo);
		lTime.setFont(fNegrita);
		pnlCrono.add(lTime);
	}
	
	/*
	 * printCronoPanel();
	 * Método que mostrará por pantalla el JPanel Crono
	 * @param void
	 * @return void
	 */
	private void printCronoPanel() {
		pnlPpal.add(pnlCrono, BorderLayout.SOUTH);
		pnlCrono.setVisible(true);
		pnlCrono.repaint();
		pnlPpal.repaint();
	}
	
	/*
	 * reprintCronoPanel();
	 * Método que actualizará el JLabel que lleva el crono en el JPanel del cronometro
	 * @param void
	 * @return void
	 */
	private void reprintCronoPanel() {
		tiempo = mins+":"+secs;
		lTime.setText(tiempo);
		pnlCrono.repaint();
	}
}
