package com.emoskvc.app;

import sun.nio.ch.Net;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.System.console;

public class Client extends JFrame {

    private JPanel contentPane;

    private String name, address;
    private int port;
    private JTextField txtMessage;
    private JTextArea history;
    private boolean connected = false;
    private Net net = null;

    public Client(String name, String address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
        net = new Net(port);

        connected = net.openConnectiont(address);
        if (!connected) {
            System.out.println("Connection failed ...");
            console("Connection failed ...");
        }
        createWindow();
        String connectionPacket = "/c/" + name;
        net.send(connectionPacket.getBytes());
        console("You are trying to connect to: " + address + ", port:" + port + ", user name:" + name);
    }
    public void createWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Messenger Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{16, 857, 7};
        gbl_contentPane.columnWidths = new int[]{16, 827, 30, 7};
        gbl_contentPane.rowHeights = new int[]{35, 475, 40};
        gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
        contentPane.setLayout(gbl_contentPane);

        history = new JTextArea();
        history.setEditable(false);
        history.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(history);
        GridBagConstraints scrollConstrains = new GridBagConstraints();
        scrollConstrains.insets = new Insets(0, 0, 5, 5);
        scrollConstrains.fill = GridBagConstraints.BOTH;
        scrollConstrains.gridx = 0;
        scrollConstrains.gridy = 0;
        scrollConstrains.gridwidth = 3;
        scrollConstrains.gridheight = 2;
        scrollConstrains.insets = new Insets(0,7, 0, 0);
        contentPane.add(scroll, scrollConstrains);
        txtMessage = new JTextField();
        txtMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ACCEPT) {
                    send(txtMessage);
                }
            }
        });




    }

    public void console(String str) {

    }


}
