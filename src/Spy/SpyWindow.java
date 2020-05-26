package Spy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class SpyWindow extends JFrame{
	private JTextField jTFNumPersonas;
	private ArrayList<String> lugarList;
	//private ArrayList<String> numPersonas;
	private String lugar;
	private int spyPlayer = 0;
	private int startPlayer = 0;
	private JCheckBox cBAll;
	private JCheckBox cBCrono;
	private int timeCrono;
	private static final long serialVersionUID = 42L;
	
	public SpyWindow() {
		// ** Nuestra ventana:
		this.setTitle("El Espía");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cerramos con la X, el programa sigue en ejecución, con esta línea lo paramos
		this.setSize(240,130); //Tamaño de la ventana
		this.setLocationRelativeTo(null); // Esto hace que se centre la ventana
		
		// Inicializamos la arrayList necesarias, de momento la de los lugares:
		lugarList = new ArrayList<String>();
		iniciarLugares();
		
		//Organizamos los objetos en la ventana:
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("EL ESPÍA QUE SE PERDIÓ");
		northPanel.add(lblTitulo);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		JLabel lblNumPersonas = new JLabel("¿Cuantas personas vais a jugar?");
		centerPanel.add(lblNumPersonas);
		
		jTFNumPersonas = new JTextField();
		centerPanel.add(jTFNumPersonas);
		jTFNumPersonas.setColumns(5);
		
		// Botón para crear la partida con el número de personas que le indiquemos
		JButton jBCrearPartida = new JButton("Crear partida");
		centerPanel.add(jBCrearPartida);
		//Función del botón
		jBCrearPartida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lugar = repartirLugar();
				System.out.println(lugar);
				// Repartimos el rol de espía
				spyPlayer = numAleatorio(Integer.parseInt(jTFNumPersonas.getText()));
				// Repartimos el jugador que empezará a jugar
				startPlayer = numAleatorio(Integer.parseInt(jTFNumPersonas.getText()));
				
				RepartirRoles rr = new RepartirRoles(Integer.parseInt(jTFNumPersonas.getText()), lugar, spyPlayer, lugarList, startPlayer);
				rr.setVisible(true);
			}
		});
		
		JLabel lAll = new JLabel("All");
		
		
	}
	
	/*
	 * iniciarLugares();
	 * Método que se encarga de rellenar la ArrayList de lugares
	 * En un inicio hay 28 lugares
	 * @param void
	 * @return void
	 */
	private void iniciarLugares() {
		lugarList.add("Asamblea regional");
		lugarList.add("Avión");
		lugarList.add("Banco");
		lugarList.add("Barco pirata");
		lugarList.add("Base militar");
		lugarList.add("Boda");
		lugarList.add("Campo de futbol");
		lugarList.add("Casino");
		lugarList.add("Circo");
		lugarList.add("Colegio");
		lugarList.add("Comisaría");
		lugarList.add("Crucero");
		lugarList.add("Discoteca");
		lugarList.add("Embajada");
		lugarList.add("Funeral");
		lugarList.add("Estación de servicio");
		lugarList.add("Estación espacial");
		lugarList.add("Estudios de cine");
		lugarList.add("Fiesta de empresa");
		lugarList.add("Hospital");
		lugarList.add("Hotel");
		lugarList.add("Montaña con río");
		lugarList.add("Parque de atracciones");
		lugarList.add("Pista de esquí");
		lugarList.add("Playa");
		lugarList.add("Restaurante");
		lugarList.add("Spa");
		lugarList.add("Submarino");
		lugarList.add("Supermercado");
		lugarList.add("Teatro");
		lugarList.add("Tren de pasajeros");
		lugarList.add("Universidad");
		lugarList.add("Zoológico");
	}
	
	/*
	 * repartirLugar();
	 * Método que selecciona un lugar aleatorio dentro de todos los lugares insertados en iniciarLugarres();
	 * @param void
	 * @return String lugar
	 */
	private String repartirLugar() {
		String loc = "Vacío";
		int numLugar = numAleatorio(lugarList.size()-1);
		loc = lugarList.get(numLugar);
		return loc;
	}
	
	/*
	 * numAleatorio();
	 * Método que le dices el número máximo y te entrega un número aleatorio entre el 0 y el número indicado
	 * @param int x
	 * @return int num
	 */
	private int numAleatorio(int dice) {
		int num = 0;
		Random rnd = new Random();
		num = rnd.nextInt()%dice;
		num = Math.abs(num);
		if(num == 0) {
			num = dice;
		}
		return num;
	}
	
}