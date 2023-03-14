package com.teamwork.courseselection;

import com.teamwork.courseselection.DAO.CourseDAO;
import com.teamwork.courseselection.DAO.StudentDAO;
import com.teamwork.courseselection.DAO.impl.CourseDAOImpl;
import com.teamwork.courseselection.DAO.impl.DeselectedRecordDAOImpl;
import com.teamwork.courseselection.DAO.impl.SelectedRecordDAOImpl;
import com.teamwork.courseselection.DAO.impl.StudentDAOImpl;
import com.teamwork.courseselection.Model.*;
import com.teamwork.courseselection.Utils.DAOConnecter;
import com.teamwork.courseselection.Utils.DragWindowHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    public VBox studentWin;
    @FXML
    public PieChart creditPie;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Label selectedLabel;
    @FXML
    public Label availableLabel;
    @FXML
    public TableView selectedTable;
    @FXML
    public TableColumn selectedCourseName;
    @FXML
    public TableColumn selectedTeacherName;
    @FXML
    public TableColumn selectedCourseCredits;
    @FXML
    public TableColumn selectedCourseDepartment;
    @FXML
    public TableColumn selectedCourseType;
    @FXML
    public TableColumn selectedSelectedAmount;
    @FXML
    public TableColumn selectedCourseCapacity;
    @FXML
    public TableColumn selectedCourseDate;
    @FXML
    public TableColumn selectedLanguage;
    @FXML
    public TableView courseTable;
    @FXML
    public TableColumn CourseName;
    @FXML
    public TableColumn TeacherName;
    @FXML
    public TableColumn CourseCredits;
    @FXML
    public TableColumn CourseDepartment;
    @FXML
    public TableColumn CourseType;
    @FXML
    public TableColumn SelectedAmount;
    @FXML
    public TableColumn CourseCapacity;
    @FXML
    public TableColumn CourseDate;
    @FXML
    public TableColumn Language;

    private Stage primaryStage=null;
    private Student currentStudent=null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        primaryStage= StudentApplication.getStage();
        currentStudent= StudentDAOImpl.getStudentDAO().find(Model.getModel().getCurrentUser().getID());
        try {
            Model.getModel().setSelectedList(SelectedRecordDAOImpl.getSelectedRecordDAO().findByStudent(currentStudent.getID()));
            Model.getModel().setAllCourses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //if(primaryStage==null)System.out.println("error");

        //挂载鼠标拖动
        DragWindowHandler handler = new DragWindowHandler(primaryStage);
        studentWin.setOnMousePressed(handler);/* 鼠标按下 */
        studentWin.setOnMouseDragged(handler);/* 鼠标拖动 */

        refreshProfile();

        selectedCourseName.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseName"));
        selectedTeacherName.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("teacherName"));
        selectedCourseCredits.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseCredits"));
        selectedCourseDepartment.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseDepartment"));
        selectedCourseType.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseType"));
        selectedSelectedAmount.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("selectedAmount"));
        selectedCourseCapacity.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseCapacity"));
        selectedCourseDate.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseDate"));
        selectedLanguage.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("language"));
        selectedTable.setItems(Model.getModel().getSelectedData());
        addButtonToTable(selectedTable);


        CourseName.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseName"));
        TeacherName.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("teacherName"));
        CourseCredits.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseCredits"));
        CourseDepartment.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseDepartment"));
        CourseType.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseType"));
        SelectedAmount.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("selectedAmount"));
        CourseCapacity.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseCapacity"));
        CourseDate.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("courseDate"));
        Language.setCellValueFactory(new PropertyValueFactory<DetailedCourseData,String>("language"));
        courseTable.setItems(Model.getModel().getCourseData());
        addButtonToTable(courseTable);
    }

    public void refreshProfile(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("可选学分",currentStudent.getAvailableCredits()),
                new PieChart.Data("已选学分", currentStudent.getSelectedCredits()));
        creditPie.setData(pieChartData);

        welcomeLabel.setText("欢迎，"+currentStudent.getStudentName());
        selectedLabel.setText("当前已选学分："+ currentStudent.getSelectedCredits());
        availableLabel.setText("当前可选学分："+currentStudent.getAvailableCredits());
    }

    private void addButtonToTable(TableView tv) {
        TableColumn<DetailedCourseData, Void> colBtn = new TableColumn("操作");

        Callback<TableColumn<DetailedCourseData, Void>, TableCell<DetailedCourseData, Void>> cellFactory = new Callback<TableColumn<DetailedCourseData, Void>, TableCell<DetailedCourseData, Void>>() {
            @Override
            public TableCell<DetailedCourseData, Void> call(final TableColumn<DetailedCourseData, Void> param) {
                final TableCell<DetailedCourseData, Void> cell = new TableCell<DetailedCourseData, Void>() {

                    private final Button btn = new Button(tv==selectedTable?"退选":"选课");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            DetailedCourseData data = getTableView().getItems().get(getIndex());
                            Course course= CourseDAOImpl.getCourseDAO().find(data.getCourseID());
                            if (tv==selectedTable){
                                SelectedRecord record=null;
                                for(SelectedRecord sr:Model.getModel().getSelectedList()){
                                    if(Objects.equals(sr.getCourseID(), data.getCourseID())){
                                        record=sr;
                                        break;
                                    }
                                }
                                assert record != null;
                                currentStudent.setSelectedCredits(currentStudent.getSelectedCredits()-data.getCourseCredits());
                                currentStudent.setAvailableCredits(currentStudent.getAvailableCredits()+ data.getCourseCredits());
                                course.setSelectedAmount(course.getSelectedAmount()-1);
                                DeselectedRecord deRecord=new DeselectedRecord(
                                        record.getCourseName(),
                                        record.getCourseID(),
                                        record.getStudentName(),
                                        record.getStudentID(),
                                        record.getTeacherName(),
                                        "学生");

                                DeselectedRecordDAOImpl.getDeselectedRecordDAO().add(deRecord);
                                SelectedRecordDAOImpl.getSelectedRecordDAO().delete(record.getID());
                                StudentDAOImpl.getStudentDAO().edit(currentStudent);
                                CourseDAOImpl.getCourseDAO().edit(course);

                                Model.getModel().getCourseList().add(course);
                                Model.getModel().getCourseData().add(data);
                                Model.getModel().getSelectedData().remove(data);
                                Model.getModel().getSelectedCourses().remove(course);
                                Model.getModel().getSelectedList().remove(record);
                                addButtonToTable(selectedTable);
                            }else{
                                if(course.getSelectedAmount()==course.getCourseCapacity()||course.getCourseCredits()>currentStudent.getAvailableCredits()){
                                    Alert err=new Alert(Alert.AlertType.ERROR);
                                    err.setHeight(100);
                                    err.setWidth(100);
                                    err.setTitle("错误");
                                    err.setContentText("选课失败，请检查课程容量/可选学分");
                                    err.show();
                                    return;
                                }else{
                                    SelectedRecord record=new SelectedRecord(
                                            course.getCourseName(),
                                            course.getID(),
                                            currentStudent.getStudentName(),
                                            currentStudent.getID(),
                                            course.getTeacherName());
                                    currentStudent.setSelectedCredits(currentStudent.getSelectedCredits()+data.getCourseCredits());
                                    currentStudent.setAvailableCredits(currentStudent.getAvailableCredits()- data.getCourseCredits());
                                    course.setSelectedAmount(course.getSelectedAmount()+1);

                                    try {
                                        SelectedRecordDAOImpl.getSelectedRecordDAO().add(record);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    StudentDAOImpl.getStudentDAO().edit(currentStudent);
                                    CourseDAOImpl.getCourseDAO().edit(course);

                                    Model.getModel().getCourseList().remove(course);
                                    Model.getModel().getCourseData().remove(data);
                                    Model.getModel().getSelectedData().add(data);
                                    Model.getModel().getSelectedCourses().add(course);
                                    Model.getModel().getSelectedList().add(record);
                                    addButtonToTable(courseTable);
                                }
                            }
                            refreshProfile();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);

        tv.getColumns().add(colBtn);
    }


    //关闭按钮
    public void closeWin(ActionEvent actionEvent) {
        System.exit(0);
    }

    //最小化按钮
    public void minWin(ActionEvent actionEvent) {
        primaryStage.setIconified(true);
    }

    public void logoutButton(ActionEvent actionEvent) throws SQLException, IOException {
        Model.getModel().setCurrentUser(null);
        DAOConnecter.disconnect();
        Model.getModel().setDBConnection(null);
        LoginApplication loginWin = new LoginApplication();
        loginWin.start(primaryStage);
    }
}
