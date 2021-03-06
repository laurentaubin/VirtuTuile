package gui;

import util.UnitConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SurfaceTab extends JPanel{
    private MainWindow mainWindow;
    private JPanel surfaceTab;
    private JFormattedTextField widthField;
    private JFormattedTextField heightField;
    private JButton combineButton;
    private JButton surfaceColorButton;
    private JPanel dimensionPanel;
    private JCheckBox holeCheckBox;
    private JLabel surfaceTitle;
    private JLabel dimensionLabel;
    private JPanel optionsPanel;
    private JLabel dispositionLabel;
    private JButton chromaticButton;
    private JButton separateButton;
    private JButton horizontallyAlignButton;
    private JButton verticallyAlignButton;
    private JButton leftAlignButton;
    private JButton downButton;
    private JButton topAlignButton;
    private JButton rightButton;
    private JButton verticallyCenterButton;
    private JButton horizontallyCenterButton;
    private JLabel nbTileLabel;
    private JLabel nbBoxLabel;
    private JPanel informationPanel;
    private JPanel distancePanel;
    private JLabel distanceLabel;
    private JFormattedTextField widthDistanceField;
    private JFormattedTextField heightDistanceField;
    private JLabel informationTabTitleLabel;
    private JLabel nbSurfaceLabel;
    private JButton centrerHButton;
    private Color surfaceColor;

    public SurfaceTab(MainWindow mainWindow) throws IOException {
        this.mainWindow = mainWindow;
        this.add(surfaceTab);
        widthField.setValue(0d);
        heightField.setValue(0d);
        surfaceTitle.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
        holeCheckBox.setFocusPainted(true);

        Dimension tabButtonDimesion = new Dimension(20,20);


        surfaceColorButton.setPreferredSize(new Dimension(50, 20));
        chromaticButton.setPreferredSize(new Dimension(20, 20));

        BufferedImage chromImage = ImageIO.read(this.getClass().getResourceAsStream("/image/chromatic.png"));
        Icon chromIcon = new ImageIcon(chromImage.getScaledInstance(15, 15, Image.SCALE_DEFAULT));
        chromaticButton.setIcon(chromIcon);

        surfaceColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Color color = JColorChooser.showDialog(null, "Choose a color", surfaceColor);
                setButtonColor(color);
                setSurfaceColor(color);
            }
        });

        widthField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setEnteredWidthSurfaceDimensions();
            }
        });

        heightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setEnteredHeightSurfaceDimensions();
            }
        });

        combineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                combineSelectedSurface();

            }
        });

        //TODO Peut-être à changer pour ActionListener
        holeCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    setSelectedSurfaceAsHole();
                }
                else if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
                    setSelectedSurfaceAsWhole();
                }
            }
        });

        chromaticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Color color = JColorChooser.showDialog(null, "Choose a color", surfaceColor);
                setButtonColor(color);
                setSurfaceColor(color);
            }
        });

        separateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                separateSelectedSurface();
            }
        });

        verticallyAlignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                verticallyAlignSelectedSurfaces();
            }
        });

        horizontallyAlignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                horizontallyAlignSelectedSurfaces();
            }
        });

        verticallyCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                verticallyCenterSelectedSurfaces();
            }
        });

        horizontallyCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                horizontallyCenterSelectedSurfaces();
            }
        });

        leftAlignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                leftAlignSelectedSurfaces();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rightAlignSelectedSurfaces();
            }
        });

        topAlignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                topAlignSelectedSurfaces();
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bottomAlignSelectedSurfaces();
            }
        });

        widthDistanceField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setEnteredWidthSurfacesDistance();
            }
        });

        heightDistanceField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setEnteredHeightSurfacesDistance();
            }
        });

        //Align Center icon icon by Icons8
        //Align Bottom icon icon by Icons8
        //Align Top icon icon by Icons8

        //Merge Docunemts icon icon by Icons8
        BufferedImage mergeImage = ImageIO.read(this.getClass().getResourceAsStream("/image/merge.png"));
        Icon mergeIcon = new ImageIcon(mergeImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        combineButton.setIcon(mergeIcon);

        //Separate Document icon icon by Icons8
        BufferedImage separateImage = ImageIO.read(this.getClass().getResourceAsStream("/image/separate.png"));
        Icon separateIcon = new ImageIcon(separateImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        separateButton.setIcon(separateIcon);

        //Align Left icon icon by Icons8
        BufferedImage leftImage = ImageIO.read(this.getClass().getResourceAsStream("/image/left.png"));
        Icon leftIcon = new ImageIcon(leftImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        leftAlignButton.setIcon(leftIcon);
        //leftAlignButton.setPreferredSize(tabButtonDimesion);

        //Align Top icon icon by Icons8
        BufferedImage upImage = ImageIO.read(this.getClass().getResourceAsStream("/image/up.png"));
        Icon upIcon = new ImageIcon(upImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        topAlignButton.setIcon(upIcon);
        //topAlignButton.setPreferredSize(tabButtonDimesion);

        //Align Bottom icon icon by Icons8
        BufferedImage downImage = ImageIO.read(this.getClass().getResourceAsStream("/image/down.png"));
        Icon downIcon = new ImageIcon(downImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        downButton.setIcon(downIcon);

        //Align Right icon icon by Icons8
        BufferedImage rightImage = ImageIO.read(this.getClass().getResourceAsStream("/image/right.png"));
        Icon rightIcon = new ImageIcon(rightImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        rightButton.setIcon(rightIcon);

        //Merge Horizontal icon icon by Icons8
        BufferedImage verticallyAlignImage = ImageIO.read(this.getClass().getResourceAsStream("/image/collerVert.png"));
        Icon collerVertIcon = new ImageIcon(verticallyAlignImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        verticallyAlignButton.setIcon(collerVertIcon);

        //Merge Horizontal icon icon by Icons8
        BufferedImage horizontalAlignImage = ImageIO.read(this.getClass().getResourceAsStream("/image/collerHor.png"));
        Icon collerHorIcon = new ImageIcon(horizontalAlignImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        horizontallyAlignButton.setIcon(collerHorIcon);

    }

    private void horizontallyCenterSelectedSurfaces() {
        mainWindow.horizontallyCenterSelectedSurfaces();
    }

    private void verticallyCenterSelectedSurfaces() {
        mainWindow.verticallyCenterSelectedSurfaces();
    }

    private void horizontallyAlignSelectedSurfaces() {
        mainWindow.horizontallyAlignSelectedSurfaces();
    }

    private void verticallyAlignSelectedSurfaces() {
        mainWindow.verticallyAlignSelectedSurfaces();
    }

    private void leftAlignSelectedSurfaces() {
        mainWindow.leftAlignSelectedSurfaces();
    }

    private void rightAlignSelectedSurfaces() {
        mainWindow.rightAlignSelectedSurfaces();
    }

    private void topAlignSelectedSurfaces() {
        mainWindow.topAlignSelectedSurfaces();
    }

    private void bottomAlignSelectedSurfaces() {
        mainWindow.bottomAlignSelectedSurfaces();
    }

    public void setEnteredWidthSurfaceDimensions(){
        double enteredWidth;
        if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.IMPERIAL) {
            String value = widthField.getText();
            try {
                String[] inchArray = getImperialArray(value);
                if (inchArray[0].equals("Format invalide")) {
                    JOptionPane.showMessageDialog(null, "Le format doit être 0\"0/0");
                    return;
                }
                double inchTotal = UnitConverter.stringToInch(inchArray);
                enteredWidth = UnitConverter.convertSelectedUnitToPixel(inchTotal, mainWindow.getCurrentMeasurementMode());
                this.mainWindow.setSelectedSurfaceWidth(enteredWidth);
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("Format invalide");
            }
        }
        else if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.METRIC) {
            enteredWidth = UnitConverter.convertSelectedUnitToPixel(Double.parseDouble(widthField.getText()), mainWindow.getCurrentMeasurementMode());
            this.mainWindow.setSelectedSurfaceWidth(enteredWidth);
        }
    }

    public void setEnteredHeightSurfaceDimensions() {
        double enteredHeight;
        if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.IMPERIAL) {
            String value = heightField.getText();
            try {
                String[] inchArray = getImperialArray(value);
                if (inchArray[0].equals("Format invalide")) {
                    JOptionPane.showMessageDialog(null, "Le format doit être 0\"0/0");
                    return;
                }
                double inchTotal = UnitConverter.stringToInch(inchArray);
                enteredHeight = UnitConverter.convertSelectedUnitToPixel(inchTotal, mainWindow.getCurrentMeasurementMode());
                this.mainWindow.setSelectedSurfaceHeight(enteredHeight);
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("Format invalide");
            }
        }
        else if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.METRIC) {
            enteredHeight = UnitConverter.convertSelectedUnitToPixel(Double.parseDouble(heightField.getText()), mainWindow.getCurrentMeasurementMode());
            this.mainWindow.setSelectedSurfaceHeight(enteredHeight);
        }
    }

    public void setEnteredWidthSurfacesDistance() {
        double enteredWidth;
        if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.IMPERIAL) {
            String value = widthDistanceField.getText();
            try {
                String[] inchArray = getImperialArray(value);
                if (inchArray[0].equals("Format invalide")) {
                    JOptionPane.showMessageDialog(null, "Le format doit être 0\"0/0");
                    return;
                }
                double inchTotal = UnitConverter.stringToInch(inchArray);
                enteredWidth = UnitConverter.convertSelectedUnitToPixel(inchTotal, mainWindow.getCurrentMeasurementMode());
                this.mainWindow.setSelectedSurfacesWidthDistance(enteredWidth);
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("Format invalide");
            }
        }
        else if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.METRIC) {
            enteredWidth = UnitConverter.convertSelectedUnitToPixel(Double.parseDouble(widthDistanceField.getText()), mainWindow.getCurrentMeasurementMode());
            this.mainWindow.setSelectedSurfacesWidthDistance(enteredWidth);
        }
    }

    public void setEnteredHeightSurfacesDistance() {
        double enteredHeight;
        if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.IMPERIAL) {
            String value = heightDistanceField.getText();
            try {
                String[] inchArray = getImperialArray(value);
                if (inchArray[0].equals("Format invalide")) {
                    JOptionPane.showMessageDialog(null, "Le format doit être 0\"0/0");
                    return;
                }
                double inchTotal = UnitConverter.stringToInch(inchArray);
                enteredHeight = UnitConverter.convertSelectedUnitToPixel(inchTotal, mainWindow.getCurrentMeasurementMode());
                this.mainWindow.setSelectedSurfacesHeightDistance(enteredHeight);
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("Format invalide");
            }
        }
        else if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.METRIC) {
            enteredHeight = UnitConverter.convertSelectedUnitToPixel(Double.parseDouble(heightDistanceField.getText()), mainWindow.getCurrentMeasurementMode());
            this.mainWindow.setSelectedSurfacesHeightDistance(enteredHeight);
        }
    }

    public void combineSelectedSurface() {
        this.mainWindow.combineSelectedSurfaces();
    }

    public void setButtonColor(Color color) {
        this.surfaceColor = color;
        surfaceColorButton.setBackground(color);
        surfaceColorButton.setOpaque(true);
    }

    public void setSurfaceColor(Color color){
        mainWindow.setSelectedSurfaceColor(color);
    }

    private Color getSurfaceColor() {
        return this.surfaceColor;
    }

    public void setSurfaceDimensionField(Dimension dimension) {
        MainWindow.MeasurementUnitMode currentMeasurementMode = mainWindow.getCurrentMeasurementMode();
        double width = UnitConverter.convertPixelToSelectedUnit(dimension.width, currentMeasurementMode);
        double height = UnitConverter.convertPixelToSelectedUnit(dimension.height, currentMeasurementMode);

        if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.IMPERIAL) {
            String widthImperial = getImperialFormat(width);
            this.widthField.setText(widthImperial);
            String heightImperial = getImperialFormat(height);
            this.heightField.setText(heightImperial);
        }
        else if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.METRIC) {
            BigDecimal bdWidth = BigDecimal.valueOf(width);
            bdWidth = bdWidth.setScale(2, RoundingMode.HALF_UP);
            String widthString = Double.toString(bdWidth.doubleValue());
            this.widthField.setText(widthString + "m");

            BigDecimal bdHeight = BigDecimal.valueOf(height);
            bdHeight = bdHeight.setScale(2, RoundingMode.HALF_UP);
            String heightString = Double.toString(bdHeight.doubleValue());
            this.heightField.setText(heightString + "m");
        }
    }

    public void setSurfacesDistancesField(Dimension dimension) {
        MainWindow.MeasurementUnitMode current = mainWindow.getCurrentMeasurementMode();
        double width = UnitConverter.convertPixelToSelectedUnit(dimension.width, current);
        double height = UnitConverter.convertPixelToSelectedUnit(dimension.height, current);

        if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.IMPERIAL) {
            String widthImperial = getImperialFormat(width);
            this.widthDistanceField.setText(widthImperial);
            String heightImperial = getImperialFormat(height);
            this.heightDistanceField.setText(heightImperial);
        }

        else if (mainWindow.getCurrentMeasurementMode() == MainWindow.MeasurementUnitMode.METRIC) {
            BigDecimal bdWidth = BigDecimal.valueOf(width);
            bdWidth = bdWidth.setScale(2, RoundingMode.HALF_UP);
            String widthString = Double.toString(bdWidth.doubleValue());
            this.widthDistanceField.setText(widthString + "m");

            BigDecimal bdHeight = BigDecimal.valueOf(height);
            bdHeight = bdHeight.setScale(2, RoundingMode.HALF_UP);
            String heightString = Double.toString(bdHeight.doubleValue());
            this.heightDistanceField.setText(heightString + "m");
        }
    }


    public void setSelectedSurfaceAsWhole() {
        mainWindow.setSelectedSurfaceAsWhole();
    }

    public void setSelectedSurfaceAsHole() {
        mainWindow.setSelectedSurfaceAsHole();
    }

    public void setHoleCheckBox(boolean isSelectedSurfaceAHole, int numberOfSelectedSurfaces) {
        if (numberOfSelectedSurfaces == 1) {
            holeCheckBox.setEnabled(true);
            if (isSelectedSurfaceAHole) {
                holeCheckBox.setSelected(true);
            }
            else{
                holeCheckBox.setSelected(false);
            }
        }
        else  {
            holeCheckBox.setEnabled(false);
        }
    }

    public void separateSelectedSurface() {
        mainWindow.separateSelectedSurface();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private String getImperialFormat(double value) {
        int inch = (int)(value);
        double fraction = value - inch;
        BigDecimal bd = BigDecimal.valueOf(fraction);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String imperialString = Integer.toString(inch) + "\"" + Double.toString(bd.doubleValue());
        return imperialString;
    }

    private String[] getImperialArray(String value) {
        String[] stringArray = new String[2];
        try {
            int inchIndex = value.indexOf("\"");
            String inch = value.substring(0, inchIndex);
            String fraction = value.substring(inchIndex + 1);
            stringArray[0] = inch;
            stringArray[1] = fraction;
            return stringArray;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Format invalide");

            stringArray[0] = "Format invalide";
        }
        return stringArray;
    }

    private void updateNbSurfaceLabel(int nbSurface) {
        String label = nbSurface + " surface";
        if (nbSurface > 1) {label += "s"; }
        nbSurfaceLabel.setText(label);
    }

    public void updateNbTileLabel(int nbTile) {
        String label = nbTile + " tuile";
        if (nbTile > 1) { label += "s"; }
        nbTileLabel.setText(label);
    }

    public void updateNbBoxLabel(int nbBox) {
        String label = nbBox + " boîte";
        if (nbBox > 1) { label += "s"; }
        nbBoxLabel.setText(label);
    }

    public void updateSurfaceInformation(int nbSurface, int nbTile, int nbBox) {
        updateNbSurfaceLabel(nbSurface);
        updateNbTileLabel(nbTile);
        updateNbBoxLabel(nbBox);
    }


    public void showSurfaceInformation() {
        this.informationTabTitleLabel.setText("Information sur la surface");
    }

    public void showProjectInformation() {
        this.informationTabTitleLabel.setText("Information sur le projet");
    }

    public void showMultipleSurfacesInformation() {
        this.informationTabTitleLabel.setText("Information sur les surfaces");
    }
}
