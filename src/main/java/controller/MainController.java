package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by marcus on 7/25/14.
 */
public class MainController implements Initializable {

    @FXML
    TextField name1, name2, name3, name4, name5, name7, name8, name9, name10, name11;

    @FXML
    TextField value1, value2, value3, value4, value5, value7, value8, value9, value10, value11;

    @FXML
    TextField contentType, outputLocation, fileName;

    @FXML
    Button btnMakeFile, btnSelFile;

    @FXML
    CheckBox isPrivate;

    @FXML
    BorderPane rootPane;

    @FXML
    Separator separatorTop, separatorMiddle;

    @FXML
    ComboBox testBox;

    private ObservableList<String> options = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
    private StringBuilder mBuilder = new StringBuilder();
    private boolean boolIsPrivate = false;
    private ArrayList<TextField> mNameFields = new ArrayList<TextField>();
    private ArrayList<TextField> mValueFields = new ArrayList<TextField>();
    private FileWriter mOut = null;
    private DirectoryChooser mChooser;
    private Stage stage;

    public MainController() {
        mChooser = new DirectoryChooser();
        mChooser.setTitle("Select output location");
    }

    public void initData(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void selectFile() {
        System.out.println("SelectFile");
        File mFile = mChooser.showDialog(stage);

        if(mFile != null) {
            outputLocation.setText(mFile.getAbsolutePath().toString());
        }
    }

    @FXML
    public void makeFile() {
        System.out.println("MakeFile");
        for(int i = 0; i < mNameFields.size(); i++) {
            if(!mNameFields.get(i).getText().isEmpty()) {
                if(boolIsPrivate) {
                    mBuilder.append("private ");
                } else {
                    mBuilder.append("public ");
                }
                mBuilder.append("static final ");
                mBuilder.append(contentType.getText());
                mBuilder.append(" ");
                mBuilder.append(mNameFields.get(i).getText());
                mBuilder.append(" = ");
                if(contentType.getText().equals("String")) {
                    mBuilder.append("\"");
                }

                mBuilder.append(mValueFields.get(i).getText());

                if(contentType.getText().equals("String")) {
                    mBuilder.append("\"");
                } else if(contentType.getText().equals("long")) {
                    mBuilder.append("L");
                } else if(contentType.getText().equals("float")) {
                    mBuilder.append("f");
                } else if(contentType.getText().equals("double")) {
                    mBuilder.append("d");
                }
                mBuilder.append(";\n");
            }
        }

        BufferedWriter mWriter = null;
        try {
            mOut = new FileWriter(outputLocation.getText() + "/" + fileName.getText() + ".txt");
            mWriter = new BufferedWriter(mOut);
            mWriter.write(mBuilder.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if(mOut != null && mWriter != null) {
                try {
                    mWriter.close();
                    mOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void chkIsPrivate() {
        if(isPrivate.isSelected()) {
            boolIsPrivate = true;
            System.out.println("true");
        } else {
            boolIsPrivate = false;
            System.out.println("false");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField[] names = {name1, name2, name3, name4, name5, name7, name8, name9, name10, name11};
        TextField[] values = {value1, value2, value3, value4, value5, value7, value8, value9, value10, value11};
        mNameFields.addAll(Arrays.asList(names));
        mValueFields.addAll(Arrays.asList(values));
        testBox.setItems(options);

        btnMakeFile.getStyleClass().add("btn");
        btnSelFile.getStyleClass().add("btn");
        rootPane.getStyleClass().add("panel");
        separatorMiddle.getStyleClass().add("separator");
        separatorTop.getStyleClass().add("separator");
    }
}
