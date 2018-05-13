import Fabric.FabricListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by potap on 26.06.2017.
 */
public class UI implements FabricListener
{
    JFrame window;
    FabricController f;

    int accStorageSize;
    int engStorageSize;
    int bodyStorageSize;
    int carStorageSize;

    int accessories;
    int engines;
    int bodies;
    int carsMade;
    int carsSold;
    int carsInStorage;

    int dealers;
    int accSuppliers;

    JLabel engineStorageLabel;
    JLabel bodyStorageLabel;
    JLabel accStorageLabel;
    JLabel carsStorageLabel;
    JLabel accSuppliersLabel;
    JLabel dealersLabel;
    JLabel carsMadeLabel;
    JLabel carsSoldLabel;



    public UI(FabricController controller)
    {
        System.out.println("UI constructor started.");

        window = new JFrame();
        window.setSize(800, 400);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Factory");
        window.setLocationRelativeTo(null);

        JSlider engineSlider = new JSlider(JSlider.HORIZONTAL, 200, 8000, 2000);
        engineSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if(!source.getValueIsAdjusting()){
                    int fps = (int)source.getValue();
                    controller.setEngPeriod(fps);
                }
            }
        });

        JSlider bodySlider = new JSlider(JSlider.HORIZONTAL, 200, 8000, 2000);
        bodySlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ce){
                JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting()){
                    int fps = (int)source.getValue();
                    controller.setBodyPeriod(fps);
                }
            }
        });

        JSlider accSlider = new JSlider(JSlider.HORIZONTAL, 200, 8000, 2000);
        bodySlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ce){
                JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting()){
                    int fps = (int)source.getValue();
                    controller.setAccPeriod(fps);
                }
            }
        });

        JSlider dealerSlider = new JSlider(JSlider.HORIZONTAL, 200, 8000, 2000);
        bodySlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ce){
                JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting()){
                    int fps = (int)source.getValue();
                    controller.setDealerPeriod(fps);
                }
            }
        });

        JPanel enginePanel = new JPanel();
        enginePanel.setLayout(new GridLayout(3,1));
        enginePanel.add(new JLabel("Engine"));
        enginePanel.add(engineSlider);
        engineStorageLabel = new JLabel("0/0");
        enginePanel.add(engineStorageLabel);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(3,1));
        bodyPanel.add(new JLabel("Body"));
        bodyPanel.add(bodySlider);
        bodyStorageLabel = new JLabel("0/0");
        bodyPanel.add(bodyStorageLabel);

        JPanel accPanel = new JPanel();
        accPanel.setLayout(new GridLayout(3,1));
        accPanel.add(new JLabel("Accessories"));
        accPanel.add(accSlider);
        JPanel accInformationPanel = new JPanel();
        accInformationPanel.setLayout(new GridLayout(1,2));
        accStorageLabel = new JLabel("0/0");
        accInformationPanel.add(accStorageLabel);
        accSuppliersLabel = new JLabel("0");
        accInformationPanel.add(accSuppliersLabel);
        accPanel.add(accInformationPanel);

        JPanel dealerPanel = new JPanel();
        dealerPanel.setLayout(new GridLayout(3,1));
        dealerPanel.add(new JLabel("Dealers"));
        dealerPanel.add(dealerSlider);
        dealersLabel = new JLabel("0");
        dealerPanel.add(dealersLabel);

        JPanel carsPanel = new JPanel();
        carsPanel.setLayout(new GridLayout(4,1));
        carsPanel.add(new JLabel("Cars"));
        carsMadeLabel = new JLabel("made: 0");
        carsPanel.add(carsMadeLabel);
        carsSoldLabel = new JLabel("sold: 0");
        carsPanel.add(carsSoldLabel);
        carsStorageLabel = new JLabel("0/0");
        carsPanel.add(carsStorageLabel);

        window.setLayout(new GridLayout(1,5));
        window.add(enginePanel);
        window.add(bodyPanel);
        window.add(accPanel);
        window.add(dealerPanel);
        window.add(carsPanel);

        controller.requestInformation();

        window.setVisible(true);

        System.out.println("UI constructor ended.");
    }


    @Override
    public void setAccStorage(int size)
    {
        accStorageSize = size;

        String text = accStorageLabel.getText();
        String[] parts = text.split("/");
        parts[1] = "" + size;
        accStorageLabel.setText(parts[0] + "/" + parts[1]);
    }

    @Override
    public void setAccessories(int accs)
    {
        accessories = accs;

        String text = accStorageLabel.getText();
        String[] parts = text.split("/");
        parts[0] = "" + accs;
        accStorageLabel.setText(parts[0] + "/" + parts[1]);
    }

    @Override
    public void setEngines(int engines)
    {
        this.engines = engines;

        String text = engineStorageLabel.getText();
        String[] parts = text.split("/");
        parts[0] = "" + engines;
        engineStorageLabel.setText(parts[0] + "/" + parts[1]);
    }


    @Override
    public void setEngStorage(int size)
    {
        engStorageSize = size;

        String text = engineStorageLabel.getText();
        String[] parts = text.split("/");
        parts[1] = "" + size;
        engineStorageLabel.setText(parts[0] + "/" + parts[1]);
    }

    @Override
    public void setBodies(int bodies)
    {
        this.bodies = bodies;

        String text = bodyStorageLabel.getText();
        String[] parts = text.split("/");
        parts[0] = "" + bodies;
        bodyStorageLabel.setText(parts[0] + "/" + parts[1]);
    }


    @Override
    public void setBodyStorage(int size)
    {
        bodyStorageSize = size;

        String text = bodyStorageLabel.getText();
        String[] parts = text.split("/");
        parts[1] = "" + size;
        bodyStorageLabel.setText(parts[0] + "/" + parts[1]);
    }

    @Override
    public void setCarMade(int made)
    {
        carsMade = made;

        carsMadeLabel.setText("made: " + made);
    }

    @Override
    public void setCarSold(int sold)
    {
        carsSold = sold;

        carsSoldLabel.setText("sold: " + sold);
    }

    @Override
    public void setCars(int cars)
    {
        this.carsInStorage = cars;

        String text = carsStorageLabel.getText();
        String[] parts = text.split("/");
        parts[0] = "" + cars;
        carsStorageLabel.setText(parts[0] + "/" + parts[1]);
    }

    @Override
    public void setCarStorage(int size)
    {
        carStorageSize = size;

        String text = carsStorageLabel.getText();
        String[] parts = text.split("/");
        parts[1] = "" + size;
        carsStorageLabel.setText(parts[0] + "/" + parts[1]);
    }

    @Override
    public void setDealers(int dealers)
    {
        this.dealers = dealers;

        dealersLabel.setText("" + dealers);
    }

    @Override
    public void setAccSuppliers(int suppliers)
    {
        accSuppliers = suppliers;

        accSuppliersLabel.setText("" + suppliers);
    }
}
