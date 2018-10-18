package com.plugra.app;

import com.mysql.jdbc.StringUtils;
import com.plugra.controller.VisualizerController;
import com.plugra.model.DBStrokesModel;
import com.plugra.model.DBUserModel;
import com.plugra.model.Database;
import com.plugra.view.StrokePanel;
import com.plugra.view.VisualizerView;
import jnr.ffi.annotations.In;
import oracle.jvm.hotspot.jfr.JFR;
import sun.tools.jconsole.PlotterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.exit;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Visualizer {



    public static void main(String[] args) {

        // Connection details
        String url = "jdbc:postgresql://pellefant.db.elephantsql.com:5432/xqicwdtc";
        String username = "xqicwdtc";
        String databaseName = "postgres";
        String password = "Wx9dCpXi9kFp29GogCEiAl6CVb1PKu9j";

        // Establish a Database for use with the GUI
        Database database = new Database(
                url,
                databaseName,
                username,
                password
        );

//        DBStrokesModel model = new DBStrokesModel(database);
//        VisualizerView view = new VisualizerView();
//        VisualizerController controller = new VisualizerController(model, view);
//
//        controller.go();








        // Create strokes model which references database
        DBStrokesModel strokesModel = new DBStrokesModel(database);

        // Create frame to host stroke panel
        JFrame frame = new JFrame("Visualizer");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(2000,2000);
        frame.setVisible(true);







        // TODO: this should be obtained from the client
        final String db_username = "tryond";
        final String db_direction = "N";
        final String db_track_type = "trackpad";
        Statement statement = database.getStatement();

        int x, y;

        try {
            ResultSet rs = statement.executeQuery("select * from strokes where " +
                    "username = '" + db_username + "' and " +
//                    "direction = '" + db_direction + "' and " +
                    "cursor_type = '" + db_track_type + "';"
            );

            while (rs.next()) {

                String str1 = rs.getString("points");

                // parse string to get x and y values
//                String str2 = str1.substring(1, str1.length()-1);
                String[] tokens = str1.split("\\D+");

                System.out.println(str1);

                ArrayList<Point> points = new ArrayList<>();

                for (int i = 1; i < tokens.length-1; i += 2) {
                    System.out.println(Integer.parseInt(tokens[i]) + ", " + Integer.parseInt(tokens[i+1]));
                    x = Integer.parseInt(tokens[i]);
                    y = Integer.parseInt(tokens[i+1]);
                    points.add(new Point(x, y));
                }

                StrokePanel panel = new StrokePanel(points); // , 0, 0);
                frame.add(panel);
                frame.setVisible(true);

                // TODO: remove below
                System.in.read();


            }

            rs.close();


        }
        catch (SQLException ex) {
            ex.printStackTrace();
            exit(1);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            exit(1);
        }



    }





}

