import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.IOException;

/*
Dark blue rgb(10, 56, 113);
Light blue rgba(243, 245, 252);
Dark gray rgba(73, 80, 87);
Light gray rgb(216, 223, 232);*/

public class ConversorDeTemperatura extends JFrame implements ActionListener {

	private JButton botonConvertir, botonInicio, botonSalir;
	private JLabel lavelResultadoTemp, lavelResultadoError;
	private JComboBox<String> comboDe, comboA;
	private JOptionPane errorResultado;
	private JTextField ingresaCantidad;
	public static Conversor formulario1;
	public static ConversorDeTemperatura formulario3;

	public ConversorDeTemperatura() {

		setLayout(null);

		UIManager.put("JFrame.activeTitleBackground", new Color(243, 245, 252));
		UIManager.put("JFrame.activeTitleForeground", new Color(10, 56, 113));
		getContentPane().setBackground(new java.awt.Color(243, 245, 252));
		// UIManager.put("OptionPane.titleForeground", Color.RED);
		UIManager.put("Panel.background", new Color(243, 245, 252));
		UIManager.put("OptionPane.messageForeground", new Color(10, 56, 113));
		UIManager.put("OptionPane.background", new Color(243, 245, 252));
		UIManager.put("Button.background", new Color(10, 56, 113));
		UIManager.put("Label.foreground", new Color(10, 56, 113));
		UIManager.put("Button.foreground", new Color(243, 245, 252));
		UIManager.put("Button.font", new Font("Arial", Font.BOLD, 16));

		ingresaCantidad = new JTextField();
		ingresaCantidad.setBounds(100, 20, 150, 30);
		// ingresaCantidad.setBackground(new Color(246, 246, 246));
		ingresaCantidad.setForeground(new Color(10, 56, 113));
		ingresaCantidad.setFont(new Font("arial", Font.BOLD, 16));
		ingresaCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		add(ingresaCantidad);

		comboDe = new JComboBox<String>();
		comboDe.setBounds(100, 65, 150, 30);
		// comboDe.setBackground(new Color(246, 246, 246));
		comboDe.setForeground(new Color(10, 56, 113));
		comboDe.setFont(new Font("arial", Font.BOLD, 14));
		add(comboDe);
		String[] temperaturasDe = { "Grados Celsius", "Grados Fahrenheit", "Grados Kelvin" };
		for (String temperaturaGradosDe : temperaturasDe) {
			comboDe.addItem(temperaturaGradosDe);
		}
		comboDe.addActionListener(this);

		comboA = new JComboBox<String>();
		comboA.setBounds(100, 100, 150, 30);
		// comboA.setBackground(new Color(246, 246, 246));
		comboA.setForeground(new Color(10, 56, 113));
		comboA.setFont(new Font("arial", Font.BOLD, 14));
		add(comboA);

		String[] temperaturasA = { "Grados Celsius", "Grados Fahrenheit", "Grados Kelvin" };
		for (String temperaturaGradosA : temperaturasA) {
			comboA.addItem(temperaturaGradosA);
		}
		comboDe.addActionListener(this);

		errorResultado = new JOptionPane();
		add(errorResultado);

		lavelResultadoError = new JLabel("");
		lavelResultadoError.setHorizontalAlignment(SwingConstants.CENTER);
		lavelResultadoError.setVisible(false);
		lavelResultadoError.setBounds(10, 140, 330, 30);
		lavelResultadoError.setFont(new Font("arial", Font.BOLD, 16));
		add(lavelResultadoError);

		lavelResultadoTemp = new JLabel("En espera...");
		lavelResultadoTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lavelResultadoTemp.setVisible(true);
		lavelResultadoTemp.setBounds(10, 140, 330, 30);
		lavelResultadoTemp.setFont(new Font("arial", Font.BOLD, 18));
		add(lavelResultadoTemp);

		botonConvertir = new JButton("Convertir");
		botonConvertir.setVisible(true);
		botonConvertir.setBounds(120, 190, 110, 30);
		add(botonConvertir);
		botonConvertir.addActionListener(this);

		botonInicio = new JButton("Inicio");
		botonInicio.setVisible(true);
		botonInicio.setBounds(120, 225, 110, 30);
		add(botonInicio);
		botonInicio.addActionListener(this);

		botonSalir = new JButton("Salir");
		botonSalir.setVisible(true);
		botonSalir.setBounds(120, 260, 110, 30);
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

		/*
		 * Para convertir de ºC a ºF use la fórmula: ºF = ºC x 1.8 + 32. Para convertir
		 * de ºF a ºC use la fórmula: ºC = (ºF-32) ÷ 1.8. Para convertir de K a ºC use
		 * la fórmula: ºC = K – 273.15 Para convertir de ºC a K use la fórmula: K = ºC +
		 * 273.15. Para convertir de ºF a K use la fórmula: K = 5/9 (ºF – 32) + 273.15.
		 * Para convertir de K a ºF use la fórmula: ºF = 1.8(K – 273.15) + 32.
		 */

	}

	public void actionPerformed(ActionEvent eventoEnMemoria) {

		if (eventoEnMemoria.getSource() == botonConvertir) {
			String cantidadStr = ingresaCantidad.getText();
			if (cantidadStr.isEmpty()) {
				lavelResultadoError.setText("¡Error! Debe ingresar una cantidad.");
				errorResultado.showMessageDialog(botonConvertir, "¡Error! Debe ingresar una cantidad.", "Error",
						JOptionPane.ERROR_MESSAGE);
				lavelResultadoError.setVisible(true);
				lavelResultadoTemp.setVisible(false);
				return;
			}

			double cantidad = 0;
			try {
				cantidad = Double.parseDouble(cantidadStr);
			} catch (NumberFormatException e) {
				lavelResultadoError.setText("¡Error! La cantidad ingresada no es válida.");
				errorResultado.showMessageDialog(botonConvertir, "¡Error! La cantidad ingresada no es válida.", "Error",
						JOptionPane.ERROR_MESSAGE);
				lavelResultadoError.setVisible(true);
				lavelResultadoTemp.setVisible(false);
				return;
			}

			String unidadDe = (String) comboDe.getSelectedItem();
			String unidadA = (String) comboA.getSelectedItem();
			double resultado = 0;

			if (unidadDe.equals("Grados Celsius") && unidadA.equals("Grados Fahrenheit")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = cantidad * 1.8 + 32;
			} else if (unidadDe.equals("Grados Celsius") && unidadA.equals("Grados Kelvin")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = cantidad + 273.15;
			} else if (unidadDe.equals("Grados Celsius") && unidadA.equals("Grados Celsius")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = cantidad;
			} else if (unidadDe.equals("Grados Kelvin") && unidadA.equals("Grados Celsius")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = cantidad - 273.15;
			} else if (unidadDe.equals("Grados Kelvin") && unidadA.equals("Grados Fahrenheit")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = (cantidad - 273.15) * 1.8 + 32;
			} else if (unidadDe.equals("Grados Kelvin") && unidadA.equals("Grados Kelvin")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = cantidad;
			} else if (unidadDe.equals("Grados Fahrenheit") && unidadA.equals("Grados Celsius")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = (cantidad - 32) / 1.8;
			} else if (unidadDe.equals("Grados Fahrenheit") && unidadA.equals("Grados Kelvin")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = (cantidad - 32) * 5 / 9 + 273.15;
			} else if (unidadDe.equals("Grados Fahrenheit") && unidadA.equals("Grados Fahrenheit")) {
				lavelResultadoError.setVisible(false);
				lavelResultadoTemp.setVisible(true);
				resultado = cantidad;
			}

			lavelResultadoTemp.setText(String.format("%.2f", resultado) + " " + comboA.getSelectedItem());
		}

		if (eventoEnMemoria.getSource() == botonInicio) {
			Conversor.formulario1.setVisible(true);
			this.setVisible(false);
		}

		if (eventoEnMemoria.getSource() == botonSalir) {
			System.exit(0);

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

		formulario3 = new ConversorDeTemperatura();
		formulario3.setSize(350, 350);
		// formulario3.setBounds(0, 0, 610, 250);
		formulario3.setVisible(true);
		formulario3.setResizable(false);
		formulario3.setLocationRelativeTo(null);
		formulario3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario3.setTitle("Alura Conversor de temperatura");

	}

}
