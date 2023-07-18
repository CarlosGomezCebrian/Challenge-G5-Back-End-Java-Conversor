//javac Conversor.java
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

/*
Dark blue rgb(10, 56, 113);
Light blue rgba(243, 245, 252);
Dark gray rgba(73, 80, 87);
Light gray rgb(216, 223, 232);*/

public class Conversor extends JFrame implements ActionListener {

	private JButton botonTemperatura, botonDivisas, botonSalir;
	private JLabel labelBienvenida;

	public static Conversor formulario1;
	public static ConversorDeDivisas formulario2;
	public static ConversorDeTemperatura formulario3;

	public Conversor() {
		setLayout(null);

		getContentPane().setBackground(new java.awt.Color(243, 245, 252));
		UIManager.put("Panel.background", new Color(243, 245, 252));
		UIManager.put("OptionPane.background", new Color(243, 245, 252));
		UIManager.put("Button.background", new Color(10, 56, 113));
		UIManager.put("Label.foreground", new Color(10, 56, 113));
		UIManager.put("Button.foreground", new Color(216, 223, 232));
		UIManager.put("Button.font", new Font("Arial", Font.BOLD, 16));
		UIManager.put("JFrame.activeTitleBackground", new Color(10, 56, 113));
		UIManager.put("JFrame.activeTitleForeground", new Color(243, 245, 252));

		labelBienvenida = new JLabel("Hola ¿Que requiere convertir?");
		labelBienvenida.setBounds(10, 20, 330, 30);
		labelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		labelBienvenida.setFont(new Font("arial", Font.BOLD, 16));
		add(labelBienvenida);

		botonTemperatura = new JButton("Temperatura");
		// botonTemperatura.setVisible(true);
		botonTemperatura.setBounds(95, 70, 140, 30);
		add(botonTemperatura);
		botonTemperatura.addActionListener(this);

		botonDivisas = new JButton("Divisas");
		// botonDivisas.setVisible(true);
		botonDivisas.setBounds(95, 110, 140, 30);
		add(botonDivisas);
		botonDivisas.addActionListener(this);

		botonSalir = new JButton("Salir.");
		// botonDivisas.setVisible(true);
		botonSalir.setBounds(95, 150, 140, 30);
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

	public void actionPerformed(ActionEvent eventoEnMemoria) {

		if (eventoEnMemoria.getSource() == botonTemperatura) {
			formulario3.setVisible(true);
			this.setVisible(false);

		}

		if (eventoEnMemoria.getSource() == botonDivisas) {
			formulario2.setVisible(true);
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
		formulario1.setVisible(true);
		formulario1.setResizable(false);
		formulario1.setLocationRelativeTo(null);
		formulario1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario1.setTitle("Alura Conversor");

		formulario2 = new ConversorDeDivisas();
		formulario2.setSize(350, 350);
		// formulario2.setBounds(0, 0, 610, 250);
		formulario2.setVisible(false);
		formulario2.setResizable(false);
		formulario2.setLocationRelativeTo(null);
		formulario2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario2.setTitle("Alura Conversor de divisas");

		formulario3 = new ConversorDeTemperatura();
		formulario3.setSize(350, 350);
		// formulario3.setBounds(0, 0, 610, 250);
		formulario3.setVisible(false);
		formulario3.setResizable(false);
		formulario3.setLocationRelativeTo(null);
		formulario3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario3.setTitle("Alura Conversor de temperatura");

	}

}
