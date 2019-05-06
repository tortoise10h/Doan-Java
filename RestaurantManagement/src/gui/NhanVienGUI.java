/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bus.BUS_Employee;
import dto.Employee;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class NhanVienGUI extends JPanel {

    private JFileChooser chooser;
    private ArrayList<Employee> dsnv = new ArrayList<>();
    private JLabel imgLabel; // cho hinh anh trong panel
    private JPanel panelAdd, panelEdit, panelManager, panelDefault;
    private JPanel panelMain;

    private JTable table;// cho phan table
    private DefaultTableModel model;
    private JScrollPane scroll;

    private JLabel lbAvata, lbFirstName, lbLastName, lbGenderUser, lbDOB, lbSalary, lbDateWork, lbId;
    private JTextField txtFirstName, txtLastName, txtGenderUser, txtDOB, txtSalary, txtDateWork, txtId;
    private JComboBox comboGenderUser;
    private JButton btnChooser;

    private JLabel btnSumitAdd, btnSumitEdit, btnCancel;

    //component cho panelManager
    private JTextField txtSearch;
    private JComboBox comboDate;
    private JRadioButton radioAll, radioMale, radioFemale;
    private JLabel lbDate;
    private JComboBox comboBox;

    private int sortFirstName = 1;
    private int sortLastName = 1;
    private int sortDOB = 1;
    private int sortDateWork = 1;
    private int sortImg = 1;
    private int sortSalary = 1;
    private int sortGender = 1;
    private int sortId = 1;

    final String ANH = "Ảnh";
    final String MA = "Mã";
    final String HO = "Họ";
    final String TEN = "Tên";
    final String NGAY_SINH = "Ngày sinh";
    final String GIOI_TINH = "Giới tính";
    final String NGAY_VAO_LAM = "Ngày vào làm";
    final String LUONG = "Lương";

    final Font f = new Font("Open Sans", Font.PLAIN, 13);
    final Font f2 = new Font("Open Sans", Font.PLAIN, 14);
    final Font f3 = new Font("Open Sans", Font.PLAIN, 15);

    public NhanVienGUI() {
        initGlobalFrame();
        initGUI();
    }

    public void initGlobalFrame() {
        setBounds(0,0,1300,650);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
    }

    public void initGUI() {
        // day la anh
        imgLabel = new JLabel("img");
        imgLabel.setFont(f);
        imgLabel.setBounds(10, 10, 300, 300); // 310 310
        imgLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        //----------------------------------------------------------------------
        add(imgLabel);
        // day la main panel
        panelMain = new JPanel(null);
        panelMain.setBounds(320, 10, 500, 300); //820 310
        panelMain.setBorder(BorderFactory.createLineBorder(Color.black));
        add(panelMain);
        //----------------------------------------------------------------------
        // day la panel quan li
        panelManager = new JPanel();
        panelManager.setLayout(null);
        panelManager.setBounds(830, 10, 445, 300); //820 310
        panelManager.setBorder(BorderFactory.createLineBorder(Color.black));
        //them cac component cho panel manager
        //button them
        JLabel btnAdd, btnEdit, btnRemove;
        MouseListener MouseAdd = new MouseAdd();
        btnAdd = new JLabel("Thêm");
        btnAdd.setIcon(new ImageIcon("src/Images/application_icon/icons8-plus-40.png"));
        btnAdd.setBounds(10, 10, 100, 40); //110 50
        btnAdd.setFont(new Font("Open Sans", Font.BOLD, 16));
        btnAdd.addMouseListener(MouseAdd);
        panelManager.add(btnAdd);
        //button chinh sua
        MouseListener MouseEdit = new MouseEdit();
        btnEdit = new JLabel("Sửa");
        btnEdit.setIcon(new ImageIcon("src/Images/application_icon/icons8-edit-40.png"));
        btnEdit.setBounds(180, 10, 100, 40);//270 50
        btnEdit.setFont(new Font("Open Sans", Font.BOLD, 16));
        btnEdit.addMouseListener(MouseEdit);
        panelManager.add(btnEdit);

        //button xoa
        MouseListener MouseDeleteClicked = new MouseDeleteClicked();
        btnRemove = new JLabel("Xóa");
        btnRemove.setIcon(new ImageIcon("src/Images/application_icon/icons8-trash-40.png"));
        btnRemove.setFont(new Font("Open Sans", Font.BOLD, 16));
        btnRemove.setBounds(340, 10, 100, 40);
        btnRemove.addMouseListener(MouseDeleteClicked);
        panelManager.add(btnRemove);

        //Combobox va search tim kiem
        txtSearch = new JTextField("", 100);
        JLabel lbSearch = new JLabel("Tìm kiếm");
        lbSearch.setBounds(20, 80, 100, 30);
        lbSearch.setFont(f);
        panelManager.add(lbSearch);

        txtSearch.setBounds(80, 85, 150, 20);
        txtSearch.setHorizontalAlignment(JTextField.LEFT);
        txtSearch.setFont(f);
        panelManager.add(txtSearch);

        JLabel lbFollw = new JLabel("Theo: ");
        lbFollw.setFont(f);
        lbFollw.setBounds(240, 80, 100, 30);
        panelManager.add(lbFollw);
        add(panelManager);

        comboBox = new JComboBox();
        comboBox.addItem("Tất cả");
        comboBox.addItem("Mã");
        comboBox.addItem("Tên nhân viên");
        comboBox.setBounds(300, 80, 100, 30); //400 110
        comboBox.setFont(f);
        panelManager.add(comboBox);

        //Giới tính
        JLabel lbGender = new JLabel("Giới tính");
        lbGender.setFont(f);
        lbGender.setBounds(20, 130, 100, 40);
        panelManager.add(lbGender);

        ButtonGroup group = new ButtonGroup();

        //radioAll = new JRadioButton("Tất cả", new ImageIcon("src/Image/Icons/icons8-check-all-20.png"));
        radioAll = new JRadioButton("Tất cả");
        radioAll.setSelected(true);
        radioAll.setBounds(120, 130, 100, 40);
        radioAll.setFont(f);
        panelManager.add(radioAll);
        group.add(radioAll);

        //radioMale = new JRadioButton("Nam", new ImageIcon("src/Image/Icons/icons8-user-male-20.png"));
        radioMale = new JRadioButton("Nam");
        radioMale.setFont(f);
        radioMale.setBounds(230, 130, 100, 40);
        panelManager.add(radioMale);
        group.add(radioMale);

        //radioFemale = new JRadioButton("Nữ", new ImageIcon("src/Image/Icons/icons8-female-user-20.png"));
        radioFemale = new JRadioButton("Nữ");
        radioFemale.setFont(f);
        radioFemale.setBounds(340, 130, 100, 40); //440 170
        panelManager.add(radioFemale);
        group.add(radioFemale);

        //ngày vào làm
        lbDate = new JLabel("Ngày vào làm");
        lbDate.setBounds(20, 170, 100, 40);
        lbDate.setFont(f);
        panelManager.add(lbDate);
        comboDate = new JComboBox();
        comboDate.addItem("Tất cả");
        comboDate.addItem("2018-04-22");
        comboDate.setFont(f);
        comboDate.setBounds(130, 180, 200, 20);
        panelManager.add(comboDate);
        // nút tìm kiếm
        JLabel btnSearch = new JLabel("Tìm kiếm");
        btnSearch.setFont(new Font("Open Sans", Font.BOLD, 15));
        btnSearch.setIcon(new ImageIcon("src/Images/application_icon/icons8-search-40.png"));
        btnSearch.setBounds(170, 220, 130, 40);
        MouseListener MouseBtnSearchClicked = new MouseBtnSearchClicked();
        btnSearch.addMouseListener(MouseBtnSearchClicked);
        panelManager.add(btnSearch);

        JLabel pdf = new JLabel();
        pdf.setIcon(new ImageIcon("src/Images/application_icon/icons8-microsoft-excel-40.png"));
        pdf.setFont(f);
        pdf.setBounds(300, 220, 100, 40);
        MouseListener MouseExportExcelClicked = new MouseExportExcelClicked();
        pdf.addMouseListener(MouseExportExcelClicked);

        panelManager.add(pdf);

        //----------------------------------------------------------------------
        //day la table
        table = new JTable();
        Vector header = new Vector();
        scroll = new JScrollPane(table);
        //mã,h?,tên,ngày sinh,gi?i tính,ngày vào,luong c? d?nh static
        header.add(ANH);
        header.add(MA);
        header.add(HO);
        header.add(TEN);
        header.add(NGAY_SINH);
        header.add(GIOI_TINH);
        header.add(NGAY_VAO_LAM);
        header.add(LUONG);
        model = new DefaultTableModel(header, 0);
        MouseListener MouseHeaderClicked = new MouseHeaderClicked();
        table.getTableHeader().addMouseListener(MouseHeaderClicked);

        BUS_Employee busEmployee = new BUS_Employee();
        dsnv = busEmployee.fecthDatabase();
        setModelTable(); //set lai model
        table.setFont(f);
        //chinh sua giao dien cho table
        table.getTableHeader().setFont(new Font("Open Sans", Font.PLAIN, 15));
        //add action cho table
        MouseListener MouseTableClicked = new MouseTableClicked();
        table.addMouseListener(MouseTableClicked);
        //----------------------------------------------------------------------
        scroll.setBounds(0, 320, 1300, 290);
        add(scroll);
        //----------------------------------------------------------------------
        //day la cho panel add
        panelAdd = new JPanel(null);
        panelAdd.setBounds(0, 0, 500, 300);
        panelAdd.setBorder(BorderFactory.createLineBorder(Color.black));
        //----------------------------------------------------------------------
        // day la panel sua
        panelEdit = new JPanel(null);
        panelEdit.setBounds(0, 0, 500, 300);
        panelEdit.setBorder(BorderFactory.createLineBorder(Color.black));
        // cac component defaul o day
        comboGenderUser = new JComboBox();
        comboGenderUser.setFont(f);
        comboGenderUser.setBounds(120, 150, 200, 20);
        comboGenderUser.addItem("Nam");
        comboGenderUser.addItem("Nữ");

        MouseListener MouseSubmitAddClicked = new MouseSubmitAddClicked();
        btnSumitAdd = new JLabel("Hoàn tất");
        btnSumitAdd.setIcon(new ImageIcon("src/Images/application_icon/icons8-checked-40.png"));
        btnSumitAdd.setFont(f);
        btnSumitAdd.setBounds(360, 60, 100, 40);
        btnSumitAdd.addMouseListener(MouseSubmitAddClicked);

        btnCancel = new JLabel("Hủy bỏ");
        btnCancel.setIcon(new ImageIcon("src/Images/application_icon/icons8-cancel-40.png"));
        btnCancel.setFont(f);
        btnCancel.setBounds(360, 100, 100, 40);
        btnCancel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setEmptyTextField();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        MouseListener MouseEditSubmitClicked = new MouseEditSubmitClicked();
        btnSumitEdit = new JLabel("Hoàn tất");
        btnSumitEdit.setIcon(new ImageIcon("src/Images/application_icon/icons8-checked-40.png"));
        btnSumitEdit.setFont(f3);
        btnSumitEdit.setBounds(360, 60, 100, 40);
        btnSumitEdit.addMouseListener(MouseEditSubmitClicked);

        lbAvata = new JLabel("Ảnh");
        lbAvata.setIcon(new ImageIcon("src/Images/application_icon/icons8-image-file-20.png"));
        lbAvata.setFont(f);
        lbAvata.setBounds(20, 20, 100, 40);

        lbId = new JLabel("Mã");
        lbId.setFont(f);
        lbId.setBounds(42, 20, 100, 40);
        txtId = new JTextField("");
        txtId.setFont(f);
        txtId.setBounds(120, 30, 200, 20);

        btnChooser = new JButton("Chọn ảnh");
        btnChooser.setBounds(120, 20, 100, 30);
        btnChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        imgLabel.setIcon(new ImageIcon(resizeImage(chooser.getSelectedFile().getAbsolutePath())));
                    }
                } catch (Exception f) {

                }
            }
        });

        lbLastName = new JLabel("Họ");
        lbLastName.setBounds(42, 60, 100, 40);
        lbLastName.setFont(f);
        txtLastName = new JTextField("");
        txtLastName.setBounds(120, 70, 200, 20);
        txtLastName.setFont(f);

        lbFirstName = new JLabel("Tên");
        lbFirstName.setBounds(42, 100, 100, 40);
        lbFirstName.setFont(f);
        txtFirstName = new JTextField("");
        txtFirstName.setBounds(120, 110, 200, 20);
        txtFirstName.setFont(f);

        lbGenderUser = new JLabel("Giới tính");
        lbGenderUser.setBounds(42, 140, 100, 40);
        lbGenderUser.setFont(f);
        txtGenderUser = new JTextField("");
        txtGenderUser.setBounds(120, 150, 200, 20);
        txtGenderUser.setFont(f);

        lbDOB = new JLabel("Ngày sinh");
        lbDOB.setBounds(42, 180, 100, 40);
        lbDOB.setFont(f);
        txtDOB = new JTextField("");
        txtDOB.setBounds(120, 190, 200, 20);
        txtDOB.setFont(f);

        lbSalary = new JLabel("Lương");
        lbSalary.setBounds(42, 220, 100, 40);
        lbSalary.setFont(f);
        txtSalary = new JTextField("");
        txtSalary.setBounds(120, 230, 200, 20);
        txtSalary.setFont(f);

        lbDateWork = new JLabel("Ngày làm");
        lbDateWork.setBounds(42, 260, 100, 40);
        lbDateWork.setFont(f);
        txtDateWork = new JTextField("");
        txtDateWork.setBounds(120, 270, 200, 20);
        txtDateWork.setFont(f);

        panelEdit.add(lbAvata);
        panelEdit.add(btnChooser);
        panelEdit.add(lbLastName);
        panelEdit.add(txtLastName);
        panelEdit.add(lbFirstName);
        panelEdit.add(txtFirstName);
        panelEdit.add(lbGenderUser);
        panelEdit.add(txtGenderUser);
        panelEdit.add(txtDOB);
        panelEdit.add(lbDOB);
        panelEdit.add(txtSalary);
        panelEdit.add(lbSalary);

        //----------------------------------------------------------------------
        //MẶC ĐỊNH LUÔN LUÔN ADD Panel Defautl
        panelDefault = new JPanel(null);
        panelDefault.setBounds(0, 0, 500, 300);
        panelDefault.setBorder(BorderFactory.createLineBorder(Color.black));
        panelMain.add(panelDefault);
    }

    /**
     * Tất cả hàm của class nằm ở đây
     */
    public void setModelTable() {
        model.setRowCount(0);
        for (int i = 0; i < dsnv.size(); i++) {
            Vector data = new Vector();
            data.add(dsnv.get(i).getImg());
            data.add(dsnv.get(i).getId());
            data.add(dsnv.get(i).getLastName());
            data.add(dsnv.get(i).getFirstName());
            data.add(dsnv.get(i).getDob());
            data.add(dsnv.get(i).getSex() == 0 ? "Nam" : "Nữ");
            data.add(dsnv.get(i).getDate_work());
            data.add(dsnv.get(i).getSalary());
            model.addRow(data);
        }
        table.setModel(model);
    }

    public void setEmptyTextField() {
        txtId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtDateWork.setText("");
        txtGenderUser.setText("");
        txtSalary.setText("");
        txtDOB.setText("");
        imgLabel.setIcon(null);
        imgLabel.setText("img");
    }

//    public static void main(String[] args) {
//        Frame f = new Frame();
//        f.setVisible(true);
//    }

    class MouseEdit implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (table.getSelectedRow() >= 0) {
                panelMain.removeAll();
                panelEdit.add(btnSumitEdit);
                panelEdit.add(lbAvata);
                panelEdit.add(btnChooser);
                panelEdit.add(lbLastName);
                panelEdit.add(txtLastName);
                panelEdit.add(lbFirstName);
                panelEdit.add(txtFirstName);
                panelEdit.add(lbGenderUser);
                panelEdit.add(comboGenderUser);
                panelEdit.add(txtDOB);
                panelEdit.add(lbDOB);
                panelEdit.add(txtSalary);
                panelEdit.add(lbSalary);
                panelEdit.add((lbDateWork));
                panelEdit.add((txtDateWork));
                setEmptyTextField();
                setInformationRowSelected();
                panelMain.add(panelEdit);
                panelMain.repaint();
                panelMain.revalidate();
            } else {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    JOptionPane.showMessageDialog(null, "Cần chọn 1 dòng để chỉnh sửa");
                } catch (Exception ex) {
                }
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class MouseAdd implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            panelMain.removeAll();
            panelAdd.add(btnSumitAdd);
            panelAdd.add(btnCancel);
            panelAdd.add(lbAvata);
            panelAdd.add(btnChooser);
            panelAdd.add(lbLastName);
            panelAdd.add(txtLastName);
            panelAdd.add(lbFirstName);
            panelAdd.add(txtFirstName);
            panelAdd.add(lbGenderUser);
            panelAdd.add(comboGenderUser);
            panelAdd.add(txtDOB);
            panelAdd.add(lbDOB);
            panelAdd.add(txtSalary);
            panelAdd.add(lbSalary);
            panelAdd.add((lbDateWork));
            panelAdd.add((txtDateWork));
            panelMain.add(panelAdd);
            setEmptyTextField();
            panelMain.repaint();
            panelMain.revalidate();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class MouseTableClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            panelMain.removeAll();
            panelDefault.add(lbId);
            panelDefault.add(txtId);
            panelDefault.add(lbLastName);
            panelDefault.add(txtLastName);
            panelDefault.add(lbFirstName);
            panelDefault.add(txtFirstName);
            panelDefault.add(lbGenderUser);
            panelDefault.add(txtGenderUser);
            panelDefault.add(txtDOB);
            panelDefault.add(lbDOB);
            panelDefault.add(txtSalary);
            panelDefault.add(lbSalary);
            panelDefault.add(lbDateWork);
            panelDefault.add((txtDateWork));
            panelMain.add(panelDefault);
            panelMain.repaint();
            panelMain.revalidate();

            setInformationRowSelected();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public void setInformationRowSelected() {
        int index = table.getSelectedRow();
        txtId.setText(table.getValueAt(index, 1).toString());
        txtLastName.setText(table.getValueAt(index, 2).toString());
        txtFirstName.setText(table.getValueAt(index, 3).toString());
        txtDOB.setText(table.getValueAt(index, 4).toString());
        txtGenderUser.setText(table.getValueAt(index, 5).toString());
        txtDateWork.setText(table.getValueAt(index, 6).toString());
        txtSalary.setText(table.getValueAt(index, 7).toString());

        ImageIcon imageIcon = new ImageIcon(resizeImage("src/" + table.getValueAt(index, 0).toString()));
        imgLabel.setIcon(imageIcon);
        chooser = new JFileChooser();
        chooser.setSelectedFile(new File(table.getValueAt(index, 0).toString()));
    }

    public Image resizeImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException f) {
            f.printStackTrace();
        }
        Image dimg = img.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),
                Image.SCALE_SMOOTH);
        return dimg;
    }

    class MouseBtnSearchClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            String search = txtSearch.getText();
            String follow = comboBox.getSelectedItem().toString();
            int gender = -1;
            if (radioAll.isSelected()) {
                gender = -1;
            } else if (radioMale.isSelected()) {
                gender = 0;
            } else if (radioFemale.isSelected()) {
                gender = 1;
            }
            Date date_work = null;
            try {
                if (!comboDate.getSelectedItem().equals("Tất cả")) {
//                    System.out.println(comboDate.getSelectedItem().toString());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    date_work = format.parse(comboDate.getSelectedItem().toString());
                }
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            BUS_Employee busEmployee = new BUS_Employee();
            dsnv = busEmployee.filterTable(search, follow, gender, date_work);
            setModelTable();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class MouseSubmitAddClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String firstName = null;
            String lastName = null;
            int gender;
            Date dob = null;
            Date date = null;
            int salary;
            firstName = txtFirstName.getText();
            lastName = txtLastName.getText();
            gender = comboGenderUser.getSelectedItem().toString().equals("Nam") ? 0 : 1;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(txtDateWork.getText());
                dob = new SimpleDateFormat("yyyy-MM-dd").parse(txtDOB.getText());
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            salary = Integer.parseInt(txtSalary.getText());

            try {
//                Files.copy(Paths.get(chooser.getSelectedFile().getAbsolutePath()), Paths.get("C:\\Users\\admin\\Documents\\NetBeansProjects\\Employee\\img\\employee\\" + chooser.getSelectedFile().getName()));
            	Files.copy(Paths.get(chooser.getSelectedFile().getAbsolutePath()), Paths.get("src\\images\\employee" + chooser.getSelectedFile().getName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Employee f = new Employee(null, "images/employee/" + chooser.getSelectedFile().getName(), firstName, lastName, dob, date, gender, salary);
            BUS_Employee busEmployee = new BUS_Employee();
            busEmployee.InsertDatabase(f);
            BUS_Employee d = new BUS_Employee();
            dsnv = d.fecthDatabase();
            setModelTable();
            setEmptyTextField();
//            System.out.println(firstName + " " + lastName + " " + gender + " " + date + " " + salary + " " + dob);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class MouseDeleteClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (table.getSelectedRow() >= 0) {
                int index = table.getSelectedRow();
                String id = table.getValueAt(index, 1).toString();
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa") == 0) {
                    BUS_Employee busEmployee = new BUS_Employee();
                    if (busEmployee.deleteEmployee(id)) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công");
                        dsnv.removeIf(em -> (em.getId().equals(id)));
                        setModelTable();
                        setEmptyTextField();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa không thành công");
                    }
                }
            } else {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    JOptionPane.showMessageDialog(null, "Cần chọn 1 dòng để xóa");
                } catch (Exception ex) {

                }
            }

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class MouseEditSubmitClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            String id = null;
            String firstname = null;
            String lastname = null;
            Date dob = null;
            int sex;
            Date date_work = null;
            int salary;
            String img = null;
            int rowSelected = table.getSelectedRow();
            id = table.getValueAt(rowSelected, 1).toString();
            firstname = txtFirstName.getText();
            lastname = txtLastName.getText();
            try {
                dob = new SimpleDateFormat("yyyy-MM-dd").parse(txtDOB.getText());
                date_work = new SimpleDateFormat("yyyy-MM-dd").parse(txtDateWork.getText());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } catch (ParseException ex) {
                //Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            sex = comboGenderUser.getSelectedItem().equals("Nam") ? 0 : 1;
            salary = Integer.parseInt(txtSalary.getText());
            img = chooser.getSelectedFile().getAbsolutePath();
            Employee f = new Employee(id, img, firstname, lastname, dob, date_work, sex, salary);
            //System.out.println(f.getFirstName() + " " + f.getLastName() + " " + f.getId() + " " + f.getDate_work() + " " + f.getDob() + " " + f.getSalary() + " " + f.getSex() + " " + f.getImg());
            BUS_Employee busEmployee = new BUS_Employee();
            try {
                if (busEmployee.editEmployee(f)) {
                    dsnv = busEmployee.fecthDatabase();
                    setModelTable();
                    table.setRowSelectionInterval(rowSelected, rowSelected);
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công");
                    } catch (Exception n) {

                    }
                }
            } catch (SQLException ex) {
                //Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class MouseHeaderClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int col = table.columnAtPoint(e.getPoint());
            String name = table.getColumnName(col);
            if (name.equals(MA)) {
                if (sortId == 1) {
                    sortId = -1;
                    dsnv.sort((a, b) -> Integer.compare(Integer.parseInt(a.getId()), Integer.parseInt(b.getId())));
                } else {
                    sortId = 1;
                    dsnv.sort((a, b) -> Integer.compare(Integer.parseInt(b.getId()), Integer.parseInt(a.getId())));
                }
            } else if (name.equals(ANH)) {
                if (sortImg == 1) {
                    sortImg = -1;
                    dsnv.sort((a, b) -> a.getImg().compareTo(b.getImg()));
                } else {
                    sortImg = 1;
                    dsnv.sort((a, b) -> b.getImg().compareTo(a.getImg()));
                }
            } else if (name.equals(HO)) {
                if (sortLastName == 1) {
                    sortLastName = -1;
                    dsnv.sort((a, b) -> a.getLastName().compareTo(b.getLastName()));
                } else {
                    sortLastName = 1;
                    dsnv.sort((a, b) -> b.getLastName().compareTo(a.getLastName()));
                }
            } else if (name.equals(TEN)) {
                if (sortFirstName == 1) {
                    sortFirstName = -1;
                    dsnv.sort((a, b) -> a.getFirstName().compareTo(b.getFirstName()));
                } else {
                    sortFirstName = 1;
                    dsnv.sort((a, b) -> b.getFirstName().compareTo(a.getFirstName()));
                }
            } else if (name.equals(NGAY_SINH)) {
                if (sortDOB == 1) {
                    sortDOB = -1;
                    dsnv.sort((a, b) -> (a.getDob().compareTo(b.getDob())));
                } else {
                    sortDOB = 1;
                    dsnv.sort((a, b) -> (b.getDob().compareTo(a.getDob())));
                }

            } else if (name.equals(NGAY_VAO_LAM)) {
                if (sortDateWork == 1) {
                    sortDateWork = -1;
                    dsnv.sort((a, b) -> (a.getDate_work().compareTo(b.getDate_work())));
                } else {
                    sortDateWork = 1;
                    dsnv.sort((a, b) -> (b.getDate_work().compareTo(a.getDate_work())));
                }
            } else if (name.equals(LUONG)) {
                if (sortSalary == 1) {
                    sortSalary = -1;
                    dsnv.sort((a, b) -> Integer.compare(a.getSalary(), b.getSalary()));
                } else {
                    sortSalary = 1;
                    dsnv.sort((a, b) -> Integer.compare(b.getSalary(), a.getSalary()));
                }
            } else if (name.equals(GIOI_TINH)) {
                if (sortGender == 1) {
                    sortGender = -1;
                    dsnv.sort((a, b) -> Integer.compare(a.getSex(), b.getSex()));
                } else {
                    sortGender = 1;
                    dsnv.sort((a, b) -> Integer.compare(b.getSex(), a.getSex()));
                }
            }

            setModelTable();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    class MouseExportExcelClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
//            JOptionPane.showMessageDialog(null, "Ok");
            Date date = new Date();
            String des = "C:\\Users\\admin\\Desktop\\ThongKe.pdf";
            try {
                PdfWriter writer = new PdfWriter(des);
                PdfDocument pdf = new PdfDocument(writer);
                pdf.addNewPage();
                Document document = new Document(pdf);

                float[] cols = {90F, 90F, 90F, 90F, 90F, 90F};
                PdfFont font = PdfFontFactory.createFont("src\\font\\DejaVuSans-Bold.ttf", PdfEncodings.IDENTITY_H, true);
                PdfFont font2 = PdfFontFactory.createFont("src\\font\\DejaVuSans.ttf", PdfEncodings.IDENTITY_H, true);
                Table table = new Table(cols);
                Paragraph p = new Paragraph("Danh sách nhân viên").setFont(font2);
                Paragraph p2 = new Paragraph("Số kết quả tìm được là" + dsnv.size()).setFont(font2);
                document.add(p);
                document.add(p2);

                //Header Table
//                table.addCell(new Cell().add("Mã").setTextAlignment(TextAlignment.CENTER).setFont(font));
//                table.addCell(new Cell().add("Họ").setTextAlignment(TextAlignment.CENTER).setFont(font));
//                table.addCell(new Cell().add("Tên").setTextAlignment(TextAlignment.CENTER).setFont(font));
//                table.addCell(new Cell().add("Giới tính").setTextAlignment(TextAlignment.CENTER).setFont(font));
//                table.addCell(new Cell().add("Ngày sinh").setTextAlignment(TextAlignment.CENTER).setFont(font));
//                table.addCell(new Cell().add("Ngày vào làm").setTextAlignment(TextAlignment.CENTER).setFont(font));
//                for (int i = 0; i < dsnv.size(); i++) {
//                    table.addCell(new Cell().add(dsnv.get(i).getId()).setFont(font2));
//                    table.addCell(new Cell().add(dsnv.get(i).getLastName()).setFont(font2));
//                    table.addCell(new Cell().add(dsnv.get(i).getFirstName()).setFont(font2));
//                    table.addCell(new Cell().add(String.valueOf(dsnv.get(i).getSex() == 0 ? "Nam" : "Nữ")).setFont(font2));
//                    table.addCell(new Cell().add(String.valueOf(dsnv.get(i).getDob())).setFont(font2));
//                    table.addCell(new Cell().add(String.valueOf(dsnv.get(i).getDate_work())).setFont(font2));
//                }
                document.add(table);
                document.close();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Xuất Excel thành công");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
