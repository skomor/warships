
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server   {

    private ServerSocket server;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connection;
    private String serverIP;



    Application app;


    /**
     * @param app
     * konstruktor
     */
    public Server(Application app) {

        this.app=app;

    }

    /**
     * inicjalizacja zmienych:
     * server
     * serverIP
     * Ustawianie wartosc Players turn jesli jest administratorem
     * i ustawianie wartosci players Turn  na false gdy nim nie jest
     */
    void init(){

        if (app.isAdmin()) {

            try {
                server = new ServerSocket(6666, 2);
                waitForConnection();
                setupStreams();


            } catch (IOException iOException) {
                System.out.println("Server ended the connection");
                iOException.printStackTrace();
            }
            app.setPlayersTrun(true);
        } else {
            serverIP =   app.getServerIP();

            try {
                connectToServer();
                setupStreams();

            } catch (EOFException eofException) {
                System.out.println("client terminated the exception");
                eofException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            app.setPlayersTrun(false);


        }
    }

    /**
     * @throws IOException
     * laczy z serwerem
     */
    private void connectToServer() throws IOException{
        System.out.println("attempting connection");
        connection = new Socket(InetAddress.getByName(serverIP), 6666);
        System.out.println("connection established! connected to: " + connection.getInetAddress().getHostName());
    }


    /**
     * zamyka polaczenie
     */
    void closeConnection(){
        System.out.println("closing connection");
        app.setPlayersTrun(false);
        try{
            output.close();
            input.close();
            connection.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * @throws IOException
     * czeka na polaczenie
     */
    private void waitForConnection() throws IOException{
        System.out.println("Waiting for someone to connect");
        connection = server.accept();
        System.out.println("Connected to " + connection.getInetAddress().getHostName());
    }

    /**
     * @throws IOException
     * ustawia strumienie danych
     */
    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        System.out.println("streams are now set up");

        JOptionPane.showMessageDialog(app.getFrame(),
                "połączono! ",
                "Sukces!",
                JOptionPane.PLAIN_MESSAGE);

    }

    /**
     * @param s
     * wysyla wiadomosc
     */
    void sendString(String s) {

        try {
            output.writeObject(s);
            output.flush();
            System.out.println("wysłano wiadomosc:" + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return
     * odbiera wiadomosc
     */
    String getMessage() {
        String string= null;
        try {
            string = (String) input.readObject();
            System.out.println("Otrzymano wiadomosc: " +  string);
        } catch (IOException | ClassNotFoundException e) {
          //  e.printStackTrace();
            System.out.println("błąd połącznenia");
        }
        return string;

    }


    }




