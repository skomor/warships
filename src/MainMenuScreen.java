

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 */
public class MainMenuScreen extends JPanel {


    private JLabel outputIP;
    private JLabel outputName;
    private JLabel label;
    private JLabel labelIP;

    private JPanel panelCenterCh1 = new JPanel();
    private JPanel panelCenterCh2 = new JPanel();
    private JPanel panelCenterCh3 = new JPanel();
    private JPanel panelCenterCh4 = new JPanel();
    private JPanel panelCenterCh5 = new JPanel();
    private JPanel panelCenterCh6 = new JPanel();
    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();

    private JTextField textField;
    private JTextField textFieldIP;

    private JButton apply;
    private JButton button1;
    private JButton button1IP;

    private String name;
    private String ip;

    private JRadioButton itb;
    private JRadioButton bitb;
    private ButtonGroup group;
    Application application;


    private boolean CzyAdmin  ;


    Server ser;


    /**
     * @param app
     * tworzenie elementow JPanelu
     */
    public MainMenuScreen(Application app ) {

        thehandler handler = new thehandler();
        this.application = app;

        panelNorth.setBackground(Color.red.darker());
        panelNorth.setPreferredSize(new Dimension(80, 30));


        panelCenter.setBackground(Color.darkGray);
        panelCenter.setLayout(new GridLayout(6, 1, 2, 2));

        panelSouth.setBackground(Color.red.darker());
        panelSouth.setPreferredSize(new Dimension(80, 30));

        panelCenterCh1.setLayout(new GridLayout(3, 1, 2, 2));
        panelCenterCh1.setPreferredSize(new Dimension(60, 50));


        name = "";
        ip = "";

        label = new JLabel("Proszę podać swoją nazwę: ");
        textField = new JTextField("wpisz tu nazwe", 10);
        button1 = new JButton("Zatwierdź");


        panelCenterCh1.add(label);
        panelCenterCh1.add(textField);
        panelCenterCh1.add(button1);
        button1.addActionListener(handler);
        textField.addActionListener(handler);

        panelCenterCh2.setLayout(new GridLayout(3, 1, 2, 2));


        labelIP = new JLabel("Proszę podać IP Hosta: ");
        textFieldIP = new JTextField("127.0.0.1", 10);
        button1IP = new JButton("Zatwierdź");

        panelCenterCh2.add(labelIP);
        panelCenterCh2.add(textFieldIP);
        panelCenterCh2.add(button1IP);
        button1IP.addActionListener(handler);
        textFieldIP.addActionListener(handler);

        panelCenterCh3.setLayout(new GridLayout(1, 1, 2, 2));
        panelCenterCh4.setLayout(new GridLayout(1, 1, 2, 2));


        outputName = new JLabel("Twoja nazwa to: " + getName());
        panelCenterCh3.add(outputName);
        outputIP = new JLabel("Ustawione IP: " + getIp());
        panelCenterCh4.add(outputIP);


        panelCenterCh5.setLayout(new GridLayout(2, 1, 2, 2));






        itb = new JRadioButton("Administrator", false);
        bitb = new JRadioButton("Gracz", true);

        group = new ButtonGroup();
        group.add(bitb);
        group.add(itb);

        panelCenterCh5.add(itb);
        panelCenterCh5.add(bitb);
        itb.addItemListener(new CheckBoxHandler( true) );
        bitb.addItemListener(new CheckBoxHandler( false) );


        panelCenterCh6.setLayout(new GridLayout(1, 1, 2, 2));


        apply = new JButton("Rozpocznij gre");
        apply.addActionListener(handler);
        panelCenterCh6.add(apply);


        panelCenter.add(panelCenterCh1);
        panelCenter.add(panelCenterCh2);
        panelCenter.add(panelCenterCh3);
        panelCenter.add(panelCenterCh4);
        panelCenter.add(panelCenterCh5);
        panelCenter.add(panelCenterCh6);


        setLayout(new BorderLayout(4, 4));
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);





    }

    /**
     *
     */
    private class CheckBoxHandler implements ItemListener {
        private boolean tf;

        /**
         * @param isAdmin
         * sprawdza czy check box jest zanzaczony
         */
        public CheckBoxHandler(boolean isAdmin){
            tf=isAdmin;

        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            setCzyAdmin(tf);
            if(tf) {
                textFieldIP.setEditable(false);
                application.setAdmin(true);
                application.setPlayersTrun(true);
            }
            else {
                application.setAdmin(false);
                textFieldIP.setEditable(true);
                application.setPlayersTrun(false);
            }
        }
    }






    private class thehandler implements ActionListener {

        /**
         * @param event
         * uruchamia sie gdy podjeta jest akcja
         */
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == textField || event.getSource() == button1) {

                setName(textField.getText());
                outputName.setText("Twoja nazwa to: " + getName());

            } else if (event.getSource() == textFieldIP || event.getSource() == button1IP) {

                if (isIPv4Address(textFieldIP.getText())) {

                    setIp(textFieldIP.getText());
                    outputIP.setText("Ustawione IP: " + getIp());
                    application.setServerIP(getIp());

                } else {
                    JOptionPane.showMessageDialog(application.getFrame(),
                            "Proszę wprowadzić poprawny adres IP",
                            "Złe pole!",
                            JOptionPane.ERROR_MESSAGE);
                }



            } else if (event.getSource() == apply) {

                if (getName().equals("") || getName().equals("wpisz tu nazwe") && !isIPv4Address(getIp())) {

                    JOptionPane.showMessageDialog(application.getFrame(),
                            "Proszę wprowadzić swoją nazwę i adres IP",
                            "Złe Wszystko!",
                            JOptionPane.ERROR_MESSAGE);


                } else if (!isIPv4Address(getIp())) {
                    JOptionPane.showMessageDialog(application.getFrame(),
                            "Proszę wprowadzić poprawne IP",
                            "Złe IP!",
                            JOptionPane.ERROR_MESSAGE);

                } else if (getName().equals("") || getName().equals("wpisz tu nazwe")) {
                    JOptionPane.showMessageDialog(application.getFrame(),
                            "Proszę wprowadzić swoją nazwę(nie: < wpisz tu nazwe > ) ",
                            "Zła nazwa!",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    ser = new Server(application);
                    application.showBattleScreen();
                }
            }
        }
    }

    /**
     * @return
     * zwraca nazwe
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name
     * ustawia nazwe
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public boolean isCzyAdmin() {
        return CzyAdmin;
    }

    /**
     * @param czyAdmin
     * sprawcza czy uzytkownik jest Adminem
     */
    private void setCzyAdmin(boolean czyAdmin) {
        CzyAdmin = czyAdmin;
        System.out.println("czy admin" + CzyAdmin);
    }

    /**
     * @return
     * zwraca IP
     */
    private String getIp() {
        return ip;
    }

    /**
     * @param ip
     * ustawia Ip
     */
    private void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @param address
     * sprawdza czy ip jest zapisane jako Ipv4 i jesli sie zgadza zwraca true
     * w przeciwnym przypadku zwraca false
     */
    private static Boolean isIPv4Address(String address) {
        if (address.isEmpty()) {
            return false;
        }
        try {
            Object res = InetAddress.getByName(address);
            return res instanceof Inet4Address || res instanceof Inet6Address;
        } catch (final UnknownHostException ex) {
            return false;
        }
    }
}
