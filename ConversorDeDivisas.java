
//javac -cp .;json-java.jar ConversorDeDivisas.java
//java -cp .;json-java.jar ConversorDeDivisas

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ConversorDeDivisas extends JFrame implements ActionListener {

	private JButton botonConvertir, botonInicio, botonSalir;
	private JTextField ingresaCantidad;
	private JLabel labelResultado, labelResultado1, labelFecha, labelFecha1;
	private JOptionPane errorCantidad, errorMoneda, errorNumeroValido;
	private JComboBox<String> comboOrigen, comboDestino;
	public static Conversor formulario1;
	public static ConversorDeDivisas formulario2;

	public ConversorDeDivisas() {
		setLayout(null);

		getContentPane().setBackground(new java.awt.Color(243, 245, 252));
		UIManager.put("Panel.background", new Color(243, 245, 252));
		UIManager.put("OptionPane.messageForeground", new Color(10, 56, 113));
		UIManager.put("OptionPane.background", new Color(243, 245, 252));
		UIManager.put("Button.background", new Color(10, 56, 113));
		UIManager.put("Label.foreground", new Color(10, 56, 113));
		UIManager.put("Button.foreground", new Color(216, 223, 232));
		UIManager.put("Button.font", new Font("Arial", Font.BOLD, 16));
		UIManager.put("JFrame.activeTitleBackground", new Color(10, 56, 113));
		UIManager.put("JFrame.activeTitleForeground", new Color(243, 245, 252));

		errorCantidad = new JOptionPane();
		add(errorCantidad);

		errorMoneda = new JOptionPane();
		add(errorMoneda);

		errorNumeroValido = new JOptionPane();
		add(errorNumeroValido);

		ingresaCantidad = new JTextField();
		ingresaCantidad.setBounds(77, 20, 185, 30);
		// ingresaCantidad.setBackground(new Color(246, 246, 246));
		ingresaCantidad.setForeground(new Color(10, 56, 113));
		ingresaCantidad.setFont(new Font("arial", Font.BOLD, 16));
		ingresaCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		add(ingresaCantidad);

		comboOrigen = new JComboBox<String>();
		comboOrigen.setBounds(77, 55, 185, 30);
		// comboOrigen.setBackground(new Color(246, 246, 246));
		comboOrigen.setForeground(new Color(10, 56, 113));
		comboOrigen.setFont(new Font("arial", Font.BOLD, 13));
		add(comboOrigen);

		String[] monedasOrigen = { "Moneda de origen", "Dólares estadounidenses", "Dólares canadienses", "Euros",
				"Francos suizos",
				"Libras esterlinas", "Pesos argentinos", "Pesos colombianos", "Pesos mexicanos", "Reales brasileños",
				"Rublos rusos", "Rupias indias", "Soles", "Wones surcoreanos", "Yenes japonéses", "Yuanes chinos" };

		for (String monedaO : monedasOrigen) {
			comboOrigen.addItem(monedaO);
		}

		comboOrigen.addActionListener(this);

		comboDestino = new JComboBox<String>();
		comboDestino.setBounds(77, 90, 185, 30);
		// comboDestino.setBackground(new Color(246, 246, 246));
		comboDestino.setForeground(new Color(10, 56, 113));
		comboDestino.setFont(new Font("arial", Font.BOLD, 13));
		add(comboDestino);

		String[] monedasDestino = { "Moneda de destino", "Dólares estadounidenses", "Dólares canadienses", "Euros",
				"Francos suizos",
				"Libras esterlinas", "Pesos argentinos", "Pesos colombianos", "Pesos mexicanos", "Reales brasileños",
				"Rublos rusos", "Rupias indias", "Soles", "Wones surcoreanos", "Yenes japonéses", "Yuanes chinos" };

		for (String monedaD : monedasDestino) {
			comboDestino.addItem(monedaD);
		}

		comboDestino.addActionListener(this);

		labelResultado = new JLabel("En espera....");
		labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
		labelResultado.setVisible(true);
		labelResultado.setBounds(5, 120, 330, 30);
		labelResultado.setFont(new Font("arial", Font.BOLD, 16));
		add(labelResultado);

		labelResultado1 = new JLabel("");
		labelResultado1.setHorizontalAlignment(SwingConstants.CENTER);
		labelResultado1.setVisible(true);
		labelResultado1.setBounds(5, 140, 330, 30);
		labelResultado1.setFont(new Font("arial", Font.BOLD, 16));
		add(labelResultado1);

		labelFecha = new JLabel("");
		labelFecha.setHorizontalAlignment(SwingConstants.CENTER);
		labelFecha.setVisible(true);
		labelFecha.setBounds(5, 165, 330, 20);
		labelFecha.setFont(new Font("arial", Font.BOLD, 13));
		add(labelFecha);

		labelFecha1 = new JLabel("");
		labelFecha1.setHorizontalAlignment(SwingConstants.CENTER);
		labelFecha1.setVisible(true);
		labelFecha1.setBounds(5, 180, 330, 20);
		labelFecha1.setFont(new Font("arial", Font.BOLD, 13));
		add(labelFecha1);

		botonConvertir = new JButton("Convertir");
		botonConvertir.setHorizontalAlignment(SwingConstants.CENTER);
		botonConvertir.setVisible(false);
		botonConvertir.setBounds(113, 200, 110, 30);
		add(botonConvertir);
		botonConvertir.addActionListener(this);

		botonInicio = new JButton("Inicio");
		botonInicio.setHorizontalAlignment(SwingConstants.CENTER);
		botonInicio.setVisible(true);
		botonInicio.setBounds(113, 235, 110, 30);
		add(botonInicio);
		botonInicio.addActionListener(this);

		botonSalir = new JButton("Salir");
		botonSalir.setHorizontalAlignment(SwingConstants.CENTER);
		botonSalir.setVisible(true);
		botonSalir.setBounds(113, 270, 110, 30);
		botonSalir.setBackground(new Color(216, 223, 232));
		botonSalir.setForeground(new Color(10, 56, 113));
		add(botonSalir);
		botonSalir.addActionListener(this);

		try {
			BufferedImage image = ImageIO.read(getClass().getResource("/resources/Alura.png"));
			ImageIcon icon = new ImageIcon(image);
			setIconImage(icon.getImage());
		} catch (IOException e) {
			System.err.println("Error al cargar la imagen: " + e.getMessage());
		}

	}

	private JSONObject obtenerCambioMoneda() {
		JSONObject cambioMoneda = null;
		try {
			URL url = new URL("https://v6.exchangerate-api.com/v6/d6077e3583242934fcbf760f/latest/USD");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder resultAPI = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				resultAPI.append(line);
				// System.out.println(resultAPI.toString());
			}
			rd.close();
			cambioMoneda = new JSONObject(resultAPI.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Se ha producido una excepción: " + e.getMessage());
			labelResultado.setText("¡Error! al obtener la información de cambio de moneda." + e.getMessage());
		}
		return cambioMoneda;
	}

	public void actionPerformed(ActionEvent eventoEnMemoria) {

		String textoAlertOrigen = ("Moneda de origen");
		String textoAlertDestino = ("Moneda de destino");

		if (eventoEnMemoria.getSource() == comboOrigen) {
			if ((String) comboOrigen.getSelectedItem() == textoAlertOrigen) {
				labelResultado.setText("¡Error! no ha elegido una moneda.");
				errorMoneda.showMessageDialog(comboOrigen, "¡Error! no ha elegido una moneda.", "Error",
						JOptionPane.ERROR_MESSAGE);
				botonConvertir.setVisible(false);
			} else {
				labelResultado.setText("En espera....");
				labelResultado1.setVisible(false);

			}
		}

		if (eventoEnMemoria.getSource() == comboDestino) {
			if ((String) comboDestino.getSelectedItem() == textoAlertDestino) {
				labelResultado.setText("¡Error! no ha elegido una moneda. ");
				errorMoneda.showMessageDialog(comboDestino, "¡Error! no ha elegido una moneda.", "Error",
						JOptionPane.ERROR_MESSAGE);
				botonConvertir.setVisible(false);
			} else {
				labelResultado.setText("En espera....");
				labelResultado1.setVisible(false);
				botonConvertir.setVisible(true);
			}
		}

		if (eventoEnMemoria.getSource() == botonInicio) {
			Conversor.formulario1.setVisible(true);
			this.setVisible(false);
		}
		if (eventoEnMemoria.getSource() == botonSalir) {
			System.exit(0);

		}

		if (eventoEnMemoria.getSource() == botonConvertir) {

			if (ingresaCantidad.getText().isEmpty()) {
				labelResultado.setText("¡Error! Debe ingresar una cantidad.");
				errorCantidad.showMessageDialog(botonConvertir, "¡Error! Debe ingresar una cantidad.", "Error",
						JOptionPane.ERROR_MESSAGE);
				labelResultado1.setVisible(false);
			} else {
				try {
					double cantidad = Double.parseDouble(ingresaCantidad.getText());
					JSONObject cambioMoneda = obtenerCambioMoneda();
					if (cambioMoneda != null) {
						JSONObject rates = cambioMoneda.getJSONObject("conversion_rates");
						double usdRate = rates.getDouble("USD");
						String monedaOrigen = (String) comboOrigen.getSelectedItem();
						String monedaDestino = (String) comboDestino.getSelectedItem();
						String fecha = cambioMoneda.getString("time_last_update_utc");
						String fechaConDiaYUTC = fecha;
						int indicePrimerEspacio = fechaConDiaYUTC.indexOf(" ");
						String fechaSinDiaYUTC = fechaConDiaYUTC.substring(indicePrimerEspacio + 1,
								fechaConDiaYUTC.length() - 6);

						double tasaCambioOrigen = rates.getDouble(getCodigoMoneda(monedaOrigen));
						double tasaCambioDestino = rates.getDouble(getCodigoMoneda(monedaDestino));
						double resultado = cantidad * tasaCambioDestino / tasaCambioOrigen;
						labelResultado.setText("" + cantidad + " " + monedaOrigen + " equivalen a ");
						labelResultado1.setVisible(true);
						labelResultado1.setText("" + String.format("%.3f", resultado) + " " + monedaDestino + ".");
						labelFecha.setVisible(true);
						labelFecha.setText("Ultima actualización:");
						labelFecha1.setText("UTC " + fechaSinDiaYUTC);

					} else {
						labelResultado.setText("¡Error! al obtener la información de cambio de moneda.");
					}
				} catch (NumberFormatException e) {
					labelResultado.setText("¡Error! Debe ingresar un número válido.");
					errorNumeroValido.showMessageDialog(botonConvertir, "¡Error! Debe ingresar un número válido.",
							"Error", JOptionPane.ERROR_MESSAGE);
					labelResultado1.setVisible(false);

				} catch (org.json.JSONException e) {
					labelResultado.setText("¡Error! no ha elegido una moneda.");
					errorNumeroValido.showMessageDialog(comboDestino, "¡Error! no ha elegido una moneda.", "Error",
							JOptionPane.ERROR_MESSAGE);
					labelResultado1.setVisible(false);
				}
			}
		} // aqui se cierra org.json.JSONException

	}

	private String getCodigoMoneda(String nombreMoneda) {
		switch (nombreMoneda) {
			case "Dólares estadounidenses":
				return "USD";
			case "Euros":
				return "EUR";
			case "Yenes japonéses":
				return "JPY";
			case "Libras esterlinas":
				return "GBP";
			case "Dólares canadienses":
				return "CAD";
			case "Francos suizos":
				return "CHF";
			case "yuanes chinos":
				return "CNY";
			case "Wones surcoreanos":
				return "KRW";
			case "Pesos mexicanos":
				return "MXN";
			case "Rupias indias":
				return "INR";
			case "Rublos rusos":
				return "RUB";
			case "Reales brasileños":
				return "BRL";
			case "Pesos colombianos":
				return "COP";
			case "Pesos argentinos":
				return "ARS";
			case "Soles":
				return "PEN";
			default:
				return "";
		}
	}

	public static void main(String args[]) {

		formulario1 = new Conversor();
		formulario1.setSize(350, 250);
		// formulario1.setBounds(0, 0, 610, 250);
		formulario1.setVisible(false);
		formulario1.setResizable(false);
		formulario1.setLocationRelativeTo(null);
		formulario1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario1.setTitle("Alura Conversor");

		formulario2 = new ConversorDeDivisas();
		formulario2.setSize(350, 350);
		// formulario2.setBounds(0, 0, 610, 250);
		formulario2.setVisible(true);
		formulario2.setResizable(false);
		formulario2.setLocationRelativeTo(null);
		formulario2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario2.setTitle("Alura Conversor de divisas");

	}

}

/*
 * private JSONObject obtenerCambioMoneda() {
 * JSONObject cambioMoneda = null;
 * try {
 * URL url = new URL(
 * "https://api.currencyapi.com/v3/latest?apikey=NLEBJFHxYbI9dEOzd97q4rsjUvQBCUkPDKei4MX2"
 * );
 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 * conn.setRequestMethod("GET");
 * BufferedReader rd = new BufferedReader(new
 * InputStreamReader(conn.getInputStream()));
 * StringBuilder resultAPI = new StringBuilder();
 * String line;
 * while ((line = rd.readLine()) != null) {
 * resultAPI.append(line);
 * // System.out.println(resultAPI.toString());
 * }
 * rd.close();
 * cambioMoneda = new JSONObject(resultAPI.toString());
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * return cambioMoneda;
 * 
 * }
 */
