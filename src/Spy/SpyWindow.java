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
		this.setTitle("El Esp�a");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cerramos con la X, el programa sigue en ejecuci�n, con esta l�nea lo paramos
		this.setSize(240,130); //Tama�o de la ventana
		this.setLocationRelativeTo(null); // Esto hace que se centre la ventana
		
		// Inicializamos la arrayList necesarias, de momento la de los lugares:
		lugarList = new ArrayList<String>();
		iniciarLugares();
		
		//Organizamos los objetos en la ventana:
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("EL ESP�A QUE SE PERDI�");
		northPanel.add(lblTitulo);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		JLabel lblNumPersonas = new JLabel("�Cuantas personas vais a jugar?");
		centerPanel.add(lblNumPersonas);
		
		jTFNumPersonas = new JTextField();
		centerPanel.add(jTFNumPersonas);
		jTFNumPersonas.setColumns(5);
		
		// Bot�n para crear la partida con el n�mero de personas que le indiquemos
		JButton jBCrearPartida = new JButton("Crear partida");
		centerPanel.add(jBCrearPartida);
		//Funci�n del bot�n
		jBCrearPartida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lugar = repartirLugar();
				System.out.println(lugar);
				// Repartimos el rol de esp�a
				spyPlayer = numAleatorio(Integer.parseInt(jTFNumPersonas.getText()));
				// Repartimos el jugador que empezar� a jugar
				startPlayer = numAleatorio(Integer.parseInt(jTFNumPersonas.getText()));
				
				RepartirRoles rr = new RepartirRoles(Integer.parseInt(jTFNumPersonas.getText()), lugar, spyPlayer, lugarList, startPlayer);
				rr.setVisible(true);
			}
		});
		
		JLabel lAll = new JLabel("All");
		
		
	}
	
	/*
	 * iniciarLugares();
	 * M�todo que se encarga de rellenar la ArrayList de lugares
	 * En un inicio hay 28 lugares
	 * @param void
	 * @return void
	 */
	private void iniciarLugares() {
		lugarList.add("Asamblea regional");
		lugarList.add("Avi�n");
		lugarList.add("Banco");
		lugarList.add("Barco pirata");
		lugarList.add("Base militar");
		lugarList.add("Boda");
		lugarList.add("Campo de futbol");
		lugarList.add("Casino");
		lugarList.add("Circo");
		lugarList.add("Colegio");
		lugarList.add("Comisar�a");
		lugarList.add("Crucero");
		lugarList.add("Discoteca");
		lugarList.add("Embajada");
		lugarList.add("Funeral");
		lugarList.add("Estaci�n de servicio");
		lugarList.add("Estaci�n espacial");
		lugarList.add("Estudios de cine");
		lugarList.add("Fiesta de empresa");
		lugarList.add("Hospital");
		lugarList.add("Hotel");
		lugarList.add("Monta�a con r�o");
		lugarList.add("Parque de atracciones");
		lugarList.add("Pista de esqu�");
		lugarList.add("Playa");
		lugarList.add("Restaurante");
		lugarList.add("Spa");
		lugarList.add("Submarino");
		lugarList.add("Supermercado");
		lugarList.add("Teatro");
		lugarList.add("Tren de pasajeros");
		lugarList.add("Universidad");
		lugarList.add("Zool�gico");
	}
	
	/*
	 * repartirLugar();
	 * M�todo que selecciona un lugar aleatorio dentro de todos los lugares insertados en iniciarLugarres();
	 * @param void
	 * @return String lugar
	 */
	private String repartirLugar() {
		String loc = "Vac�o";
		int numLugar = numAleatorio(lugarList.size()-1);
		loc = lugarList.get(numLugar);
		return loc;
	}
	
	/*
	 * numAleatorio();
	 * M�todo que le dices el n�mero m�ximo y te entrega un n�mero aleatorio entre el 0 y el n�mero indicado
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