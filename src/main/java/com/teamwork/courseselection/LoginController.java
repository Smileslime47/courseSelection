package com.teamwork.courseselection;

import com.teamwork.courseselection.DAO.impl.StudentDAOImpl;
import com.teamwork.courseselection.DAO.impl.TeacherDAOImpl;
import com.teamwork.courseselection.Model.Model;
import com.teamwork.courseselection.Model.Entity;
import com.teamwork.courseselection.Utils.DAOConnecter;
import com.teamwork.courseselection.Utils.DBConnecter;
import com.teamwork.courseselection.Utils.DragWindowHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public VBox logWin;
    @FXML
    public FontIcon switchIcon;
    @FXML
    public ToggleButton switchButton;
    @FXML
    public HBox pswdHBox;
    @FXML
    public HBox acntHBox;
    @FXML
    public Label acntLable;
    @FXML
    public TextField acntText;
    @FXML
    public PasswordField pswdText;
    private Stage primaryStage = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        primaryStage = LoginApplication.getStage();
        //if(primaryStage==null)System.out.println("error");

        //挂载鼠标拖动
        DragWindowHandler handler = new DragWindowHandler(primaryStage);
        logWin.setOnMousePressed(handler);/* 鼠标按下 */
        logWin.setOnMouseDragged(handler);/* 鼠标拖动 */
    }


    //关闭按钮
    public void closeWin(ActionEvent actionEvent) {
        System.exit(0);
    }

    //最小化按钮
    public void minWin(ActionEvent actionEvent) {
        primaryStage.setIconified(true);
    }

    public void loginButton(ActionEvent actionEvent) throws IOException {
        /*
        通过Connecter与DB获取连接
         */
        try {
            Model.getModel().setDBConnection(DBConnecter.getConnection());
            DAOConnecter.connect(DBConnecter.getConnection());
        } catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("err");
            err.show();
            return;
        }
        /*
        登录信息检查
         */
        if(Model.getModel().getId()== Model.Identity.Student){
            if (StudentDAOImpl.getStudentDAO().find(acntText.getText()) == null || !Objects.equals(StudentDAOImpl.getStudentDAO().find(acntText.getText()).getPassword(), pswdText.getText())) {
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setHeight(100);
                err.setWidth(100);
                err.setTitle("错误");
                err.setContentText("用户名或密码错误");
                err.show();
                return;
            }
        }
        else{
            if (TeacherDAOImpl.getTeacherDAO().find(acntText.getText()) == null) {
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setHeight(100);
                err.setWidth(100);
                err.setTitle("错误");
                err.setContentText("用户名或密码错误");
                err.show();
                return;
            }
        }
        /*
        更新当前登录用户信息
         */
        Entity user = Model.getModel().getId() == Model.Identity.Student?
                StudentDAOImpl.getStudentDAO().find(acntText.getText()): TeacherDAOImpl.getTeacherDAO().find(acntText.getText());
        Model.getModel().setCurrentUser(user);
        /*
        切换至对应视窗
         */
        if(Model.getModel().getId()== Model.Identity.Student){
            StudentApplication studentWin = new StudentApplication();
            studentWin.start(primaryStage);
        }else{
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("成功");
            err.setContentText("登陆成功！");
            err.show();
        }

    }

    public void switchLogin(ActionEvent actionEvent) {
        if (Model.getModel().getId() == Model.Identity.Teacher) {
            Model.getModel().setId(Model.Identity.Student);
            switchIcon.setIconLiteral("fltfmz-toggle-left-16");
            switchButton.setText("切换至教师登陆");
            acntLable.setText("学号");
            pswdHBox.setVisible(true);
            pswdHBox.setMaxHeight(33);
            pswdHBox.setMinHeight(33);
            acntHBox.setMaxHeight(33);
            acntHBox.setMinHeight(33);
        } else {
            Model.getModel().setId(Model.Identity.Teacher);
            switchIcon.setIconLiteral("fltfmz-toggle-right-16");
            switchButton.setText("切换至学生登陆");
            acntLable.setText("工号");
            pswdHBox.setVisible(false);
            pswdHBox.setMaxHeight(0);
            pswdHBox.setMinHeight(0);
            acntHBox.setMaxHeight(66);
            acntHBox.setMinHeight(66);
        }
    }
}