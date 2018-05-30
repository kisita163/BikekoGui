/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kisita.bikeko.bikekogui.controller;

import static com.kisita.bikeko.bikekogui.item.BikekoValues.PAINTING;
import static com.kisita.bikeko.bikekogui.item.BikekoValues.PAINTING_TECHNIQUES;
import static com.kisita.bikeko.bikekogui.item.BikekoValues.TYPE;
import com.kisita.bikeko.bikekogui.item.Data;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HuguesKi
 */
public class FXMLItemsController implements Initializable {

    @FXML
    private TableView<Data> tableView;
    @FXML
    private TableColumn<Data, String> nameColumn;
    @FXML
    private TableColumn<Data, String> typeColumn;

    @FXML
    private TableColumn<Data, String> priceColumn;

    @FXML
    private TableColumn<Data, String> authorColumn;
        
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField sizeField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField depthField;
    @FXML
    private TextField weightField;
    @FXML
    private ComboBox typeField;
    @FXML
    private ComboBox techniqueField;
    @FXML
    private CheckBox signedCheckBox;
    @FXML
    private CheckBox framedCheckBox;
    @FXML
    private CheckBox uniqueCheckBox;
    @FXML
    private CheckBox availabilityCheckBox;
    
    @FXML
    private Button addButton;

    private Stage stage;

    private ArrayList<String> picturesArray;

    FXMLPicturesController picturesController = null;

    private ObservableList< Data> data = FXCollections
            .observableArrayList();

    private Data currentData;

    private int currentIndex;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        typeField.getItems().addAll(TYPE);

        typeField.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setTechniqueField(newValue);
                data.get(currentIndex).setType(newValue);
            }
        });
        
        techniqueField.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                data.get(currentIndex).setTechnique(newValue);
            }
        });
                
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    priceField.setText(oldValue);
                }
                data.get(currentIndex).setPrice(priceField.getText());
            }
        });

        widthField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    widthField.setText(oldValue);
                }
                data.get(currentIndex).setWidth(widthField.getText());
            }
        });

        heightField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    heightField.setText(oldValue);
                }
                data.get(currentIndex).setHeight(heightField.getText());
            }
        });

        depthField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    depthField.setText(oldValue);
                }
                data.get(currentIndex).setDepth(heightField.getText()); 
            }
        });

        weightField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    weightField.setText(oldValue);
                }
                data.get(currentIndex).setWeight(weightField.getText());
            }
        });

        authorField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Old value is  : " + oldValue + " New valus is  : " + newValue);
                
                data.get(currentIndex).setAuthor(newValue);
            }
        });
        
        
        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Old value is  : " + oldValue + " New valus is  : " + newValue);
                
                data.get(currentIndex).setName(newValue);
            }
        });

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Selected row is " + tableView.getSelectionModel().getSelectedItems().get(0).getName());
                currentData = tableView.getSelectionModel().getSelectedItems().get(0);
                currentIndex = tableView.getSelectionModel().getSelectedIndex();
                setItemDetails(currentData);
            }
        });
        
        signedCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                data.get(currentIndex).setSigned(newValue);            
            }
        });
        
        framedCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                data.get(currentIndex).setFramed(newValue);
            }
        });
        
        uniqueCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                data.get(currentIndex).setUnique(newValue);
            }
        });
        
        availabilityCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                data.get(currentIndex).setAvailability(newValue);
            }
        });


        tableView.setItems(data);
    }

    public void setItemDetails(Data data) {
        //initFields();

        nameField.setText(data.getName());
        priceField.setText(data.getPrice());
        authorField.setText(data.getAuthor());
        widthField.setText(data.getWidth());
        heightField.setText(data.getHeight());
        depthField.setText("");
        weightField.setText(data.getWeight());

        picturesArray = data.getPictures();

        signedCheckBox.setSelected(data.isSigned());
        framedCheckBox.setSelected(data.isFramed());
        uniqueCheckBox.setSelected(data.isUnique());
        availabilityCheckBox.setSelected(data.isAvailable());

        typeField.getSelectionModel().select(data.getType());
        setTechniqueField(data.getType());
        techniqueField.getSelectionModel().select(data.getTechnique());
    }

    @FXML
    protected void newItem(ActionEvent event) {
        /*String size = widthField.getText() + " x " + heightField.getText() + " cm";
        System.out.println("Name     : " + nameField.getText());
        System.out.println("Price    : " + priceField.getText());
        System.out.println("Author   : " + authorField.getText());
        System.out.println("Size     : " + size);
        System.out.println("Weight   : " + weightField.getText());
        System.out.println("Type     : " + typeField.getValue().toString());
        System.out.println("Technique: " + techniqueField.getValue().toString());
        System.out.println("Signed   : " + signedCheckBox.isSelected());
        System.out.println("Framed   : " + framedCheckBox.isSelected());
        System.out.println("Unique   : " + uniqueCheckBox.isSelected());
        System.out.println("Availability : " + availabilityCheckBox.isSelected());

        Data item = new Data(nameField.getText(),
                priceField.getText(),
                authorField.getText(),
                size,
                weightField.getText(),
                typeField.getValue().toString(),
                techniqueField.getValue().toString(),
                signedCheckBox.isSelected(),
                framedCheckBox.isSelected(),
                uniqueCheckBox.isSelected(),
                availabilityCheckBox.isSelected());

        ObservableList<Data> data = tableView.getItems();
        data.add(item);*/

        initFields();
        picturesArray = new ArrayList<>();
        addButton.setDisable(false);
    }

    private void setTechniqueField(String type) {
        switch (type) {
            case PAINTING:
                techniqueField.getItems().addAll(PAINTING_TECHNIQUES);
                break;
            default:
                techniqueField.getItems().clear();
                break;
        }
    }

    private String getTechniqueField(String type, String technique) {

        String ret = "";
        int tech = Integer.valueOf(technique);
        switch (type) {
            case PAINTING:
                ret = PAINTING_TECHNIQUES[tech];
                break;
            case "b":
                break;
            case "c":
                break;
            default:
                break;
        }

        return ret;
    }

    private void initFields() {
        nameField.setText("");
        priceField.setText("");
        authorField.setText("");
        widthField.setText("");
        heightField.setText("");
        depthField.setText("");
        weightField.setText("");

        signedCheckBox.setSelected(false);
        framedCheckBox.setSelected(false);
        uniqueCheckBox.setSelected(false);
        availabilityCheckBox.setSelected(false);
    }

    public void setStage(Stage pStage) {
        stage = pStage;
    }

    @FXML
    protected void handlePictures(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLPictures.fxml"));

            picturesController = new FXMLPicturesController(picturesArray);
            fxmlLoader.setController(picturesController);

            Parent page = (Parent) fxmlLoader.load();

            Stage pictures = new Stage();
            picturesController.setStage(pictures);
            pictures.initModality(Modality.WINDOW_MODAL);
            pictures.initOwner(this.stage);
            pictures.setScene(new Scene(page));
            pictures.setResizable(false);

            pictures.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TableView<Data> getTableView() {
        return tableView;
    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<Data> getItemsList() {
        return this.data;
    }

    public void picturesArray(ArrayList<String> pictures) {
        this.picturesArray = pictures;
    }
    
    @FXML
    void addItem(ActionEvent event) {

    }
    
    @FXML
    void updateFirebase(ActionEvent event) {
        for(Data it : data){
            it.printData();
            System.out.println("\n");
        }
    }
}
